package com.lyx.whale.service;


import com.google.common.collect.Lists;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.hankcs.hanlp.utility.TextUtility;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
//import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.nlp.tokenizer.WordTokenizer;
import com.lyx.whale.common.HttpClientUtils;
import com.lyx.whale.constant.SysConstant;
import com.lyx.whale.entity.GoodsInfo;
import com.lyx.whale.tokenizers.CustomTokenizer;
import com.lyx.whale.utility.JsonAnalysisUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SpiderService {
    //初始化分类器所需要的分词器
    //情感词汇路径
    private static String emotionDictionaryPath = "C:\\Users\\zoe\\Documents\\SentimentAnalysis\\Dic\\emotionDic";
    private static FrequencyAnalyzer frequencyAnalyzer;
    private static IClassifier classifier;
//    @Autowired
//    private SpiderCommentService spiderCommentService;
    private static Logger logger = LoggerFactory
            .getLogger(SpiderService.class);
    private static String HTTPS_PROTOCOL = "https:";

    /**
     *制作词云的CustomerTokenizer继承了KUMO的WordTokenizer，但是为了更精确的分词，CustomerTokenizer实际上使用了HanLP的NotionalTokenizer分词
     * 以下对NotionalTokenizer初始化，包括停用词处理与自定义分词词典（增加情感词汇作为分词）
     * 作用域：1.词云制作过程的分词（确定）
     */
    static {
        //读取分类器-begin
        NaiveBayesModel model = (NaiveBayesModel) IOUtil
                .readObjectFrom(SysConstant.MODEL_PATH);
        classifier = new NaiveBayesClassifier(model);
        //读取分类器-end
        // 词频统计器初始化-begin KUMO
        frequencyAnalyzer = new FrequencyAnalyzer();
        WordTokenizer customTokenizer = new CustomTokenizer();
        frequencyAnalyzer.setWordFrequenciesToReturn(20);
        frequencyAnalyzer.setMinWordLength(2);
        //frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
        frequencyAnalyzer.setWordTokenizer(customTokenizer);
        //词频统计器初始化-end
        CoreStopWordDictionary.add("热水器");
        CoreStopWordDictionary.add("零食");
        CoreStopWordDictionary.add("安装");
        NotionalTokenizer.SEGMENT.enableCustomDictionary(true);
        if (emotionDictionaryPath == null) throw new IllegalArgumentException("参数 folderPath == null");
        File root = new File(emotionDictionaryPath);
        if (!root.exists()) throw new IllegalArgumentException(String.format("目录 %s 不存在", root.getAbsolutePath()));
        if (!root.isDirectory())
            throw new IllegalArgumentException(String.format("目录 %s 不是一个目录", root.getAbsolutePath()));
        File[] filess = root.listFiles();
        for (File file : filess) {
            if (!file.isFile()) continue;
            FileReader fr = null;
            try {
                fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String words;
                while ((words = br.readLine()) != null) {
                    if (!TextUtility.isAllChinese(words)) continue;
                    CustomDictionary.add(words);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    //初始化分词器部分结束


    public List<GoodsInfo> spiderData(String url, Map<String, String> params) {
        String html = HttpClientUtils.sendGet(url, null, params);
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        if (!StringUtils.isBlank(html)) {
            goodsInfos = parseHtml(html);
        }
        return goodsInfos;
    }

    public List<GoodsInfo> spiderGomeData(String url, Map<String, String> params) {
        String html = HttpClientUtils.sendGet(url, null, params);
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        if (!StringUtils.isBlank(html)) {
            goodsInfos = parseGome(html);
            System.out.println(goodsInfos);
        }
        return goodsInfos;
    }

    /**
     * 解析html
     *
     * @param html
     */


    private List<GoodsInfo> parseHtml(String html) {
        // 商品集合
        List<GoodsInfo> goods = Lists.newArrayList();
        /**
         * 获取dom并解析
         */
        Document document = Jsoup.parse(html);
        Elements elements = document.select("ul[class=gl-warp clearfix]")
                .select("li[class=gl-item]");
        int index = 0;
        for (Element element : elements) {
            String goodsPrice = element.select("div[class=p-price]")
                    .select("strong").select("i").text();
            if (goodsPrice.isEmpty() || goodsPrice == null) {
                continue;
            }
            String goodsId = element.attr("data-sku");
            String goodsName = element
                    .select("div[class=p-name p-name-type-2]").select("em")
                    .text();
            String imgUrl = HTTPS_PROTOCOL
                    + element.select("div[class=p-img]").select("a")
                    .select("img").attr("src");
            List<String> spiderComments = JsonAnalysisUtil.getJDComments(goodsId, 2);
            // 评论分词获取词频
            List<WordFrequency> wordFrequencies = frequencyAnalyzer
                    .load(spiderComments);
            //各极性数量
            int posNum = 0;
            int negNum = 0;
            int midNum = 0;
            //分类结果
            String result;
            for (String comment : spiderComments) {
                result = classifier.classify(comment);
                switch (result) {
                    case "positive":
                        posNum = posNum + 1;
                        break;
                    case "negtive":
                        negNum = negNum + 1;
                        break;
                    case "mid":
                        midNum = midNum + 1;
                        break;
                }

            }
            GoodsInfo goodsInfo = new GoodsInfo(goodsId, goodsName, imgUrl,
                    goodsPrice, posNum, negNum, midNum,
                    wordFrequencies);
            goods.add(goodsInfo);
            logger.info("成功爬取【" + goodsName + "】的基本信息 ");
            logger.info(goodsPrice);
            if (index++ == 9) {
                break;
            }
        }
        return goods;
    }

    private List<GoodsInfo> parseGome(String html) {
        int index = 0;
        // 商品集合
        List<GoodsInfo> goods = Lists.newArrayList();
        Document document = Jsoup.parse(html);
        // 获取产品列表信息
        Element elementP = document.getElementById("product-box");
        // 获取产品列
        Elements elements = elementP.select("li[class=product-item]");
        for (Element element : elements) {
            String goodsName = element.select("p[class=item-name]")
                    .select("a[class=emcodeItem item-link]").attr("title");
            String imgUrl = element.select("p[class=item-pic]").select("a").select("img").attr("gome-src");
            String goodsHref = element.select("p[class=item-name]")
                    .select("a[class=emcodeItem item-link]").attr("href");
            //获取商品链接的pid和skuid，字符串末包含.html
            String pidJoinSkuid = goodsHref.substring(19, goodsHref.length());
            //未处理skuid，后缀有.html字符
            String unHandleSkuid = pidJoinSkuid.split("-")[1];
            String skuid = unHandleSkuid.substring(0, unHandleSkuid.length() - 5);
            //普通id加上skuid制作成唯一商品id
            String goodsId = pidJoinSkuid.split("-")[0] + skuid;
            //商品简单id，用于根据简单id查找商品评论页面
            String simpleGoodsId = pidJoinSkuid.split("-")[0];
            String goodsPrice = JsonAnalysisUtil.getGoMePrice(simpleGoodsId + "/" + skuid);
            List<WordFrequency> wordFrequencies = frequencyAnalyzer
                    .load(JsonAnalysisUtil.getGoMeComments(simpleGoodsId, 2));
            //各极性数量
            int posNum = 0;
            int negNum = 0;
            int midNum = 0;
            //分类结果
            String result;
            for (String comment : JsonAnalysisUtil.getGoMeComments(simpleGoodsId, 2)) {
                result = classifier.classify(comment);
                switch (result) {
                    case "positive":
                        posNum = posNum + 1;
                        break;
                    case "negtive":
                        negNum = negNum + 1;
                        break;
                    case "mid":
                        midNum = midNum + 1;
                        break;
                }

            }
            GoodsInfo goodsInfo = new GoodsInfo(goodsId, goodsName, imgUrl,
                    goodsPrice, posNum, negNum, midNum,
                    wordFrequencies);
            goods.add(goodsInfo);
            logger.info("成功爬取【" + goodsName + "】的基本信息 ");
            logger.info(goodsPrice);
            if (index++ == 9) {
                break;
            }
        }
        return goods;

    }

}
