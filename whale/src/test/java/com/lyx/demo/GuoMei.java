package com.lyx.demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lyx.whale.common.HttpClientUtils;
import com.lyx.whale.entity.GoodsInfo;
import com.lyx.whale.service.SpiderCommentService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GuoMei {
    @Autowired
    private SpiderCommentService spiderCommentService;

    public static void main(String[] args) {
        GuoMei guoMei = new GuoMei();
        Map<String, String> params = Maps.newHashMap();
        params.put("question", "热水器");
        params.put("page", "3");

        guoMei.spiderData("https://search.gome.com.cn/search", params);
    }

    public void spiderData(String url, Map<String, String> params) {
        String html = HttpClientUtils.sendGet(url, null, params);
        if (!StringUtils.isBlank(html)) {
            List<GoodsInfo> goodsInfoList = parseHtml(html);
        }
    }

    private List<GoodsInfo> parseHtml(String html) {
        List<GoodsInfo> goods = Lists.newArrayList();
        Document document = Jsoup.parse(html);
        // 获取产品列表信息
        Element elementP = document.getElementById("product-box");
        // 获取产品列
        Elements elements = elementP.select("li[class=product-item]");
        for (Element element : elements) {
            String goodsName = element.select("p[class=item-name]")
                    .select("a[class=emcodeItem item-link]").attr("title");
            System.out.println(goodsName);
            String imgUrl = element.select("p[class=item-pic]").select("a").select("img").attr("gome-src");
            System.out.println(imgUrl);
            String producthref = element.select("p[class=item-name]")
                    .select("a[class=emcodeItem item-link]").attr("href");
            String href = producthref.substring(19, producthref.length());
            System.out.println(href.split("-")[0]);
            System.out.println(href.startsWith("."));
            System.out.println((href.split("-")[1]).substring(0, (href.split("-")[1]).length()-5));
            String goodsPrice = spiderGuoMeiData("https://ss.gome.com.cn/search/v1/price/single/9134291566/1123231090/25010000/flag/item");
            System.out.println(goodsPrice);
            String productStatus = element.select("p[class=item-pic]")
                    .select("a[class=item-link]").attr("title");
            System.out.println(productStatus);
        }
        return goods;
    }

    private String spiderGuoMeiData(String url) {
        String json = loadJson(url);
        String goodsName = null;
        JSONObject jObj = JSONObject.parseObject(json);
        if (jObj != null) {
            JSONObject result = jObj.getJSONObject("result");
            goodsName = result.getString("price");
            return goodsName;
        } else {
            return goodsName;
        }
    }

    private String loadJson(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    uc.getInputStream(), "GBK"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
