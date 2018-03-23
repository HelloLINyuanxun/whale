package com.lyx.whale.tokenizers;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.hankcs.hanlp.utility.TextUtility;
import com.kennycason.kumo.nlp.tokenizer.WordTokenizer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 该分词器是继承KUMO的分词接口，用于生成词频，不属于HanLP的分词器，只是利用了HanLp的分词制作成了KUMO的分词
 */
public class CustomTokenizer implements WordTokenizer {

    public CustomTokenizer() {

    }


    @Override
    public List<String> tokenize(String sentence) {
        char[] charArray = sentence.toCharArray();
        List<Term> termList = NotionalTokenizer.segment(charArray);
        final List<String> tokens = new ArrayList<>();
        for (Term term : termList) {
            tokens.add(term.word);
        }
        return tokens;
    }

    //添加情感词汇到用户自定义分词词典
//    private  void assemblingCustomDictionary(String emotionDictionaryPath)  {
//        //添加stop word
//        CoreStopWordDictionary.add("热水器");
//        CoreStopWordDictionary.add("零食");
//        NotionalTokenizer.SEGMENT.enableCustomDictionary(true);
//        if (emotionDictionaryPath == null) throw new IllegalArgumentException("参数 folderPath == null");
//        File root = new File(emotionDictionaryPath);
//        if (!root.exists()) throw new IllegalArgumentException(String.format("目录 %s 不存在", root.getAbsolutePath()));
//        if (!root.isDirectory())
//            throw new IllegalArgumentException(String.format("目录 %s 不是一个目录", root.getAbsolutePath()));
//        File[] filess = root.listFiles();
//        if (filess == null) {
//            System.out.println("目录为空！");
//            return;
//        }
//        for (File file : filess) {
//            if (!file.isFile()) continue;
//            FileReader fr = null;
//            try {
//                fr = new FileReader(file);
//                BufferedReader br = new BufferedReader(fr);
//                String words;
//                while ((words = br.readLine()) != null) {
//                    if (!TextUtility.isAllChinese(words)) continue;
//                    CustomDictionary.add(words);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
