package com.lyx.whale.tokenizers;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.kennycason.kumo.nlp.tokenizer.WordTokenizer;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String args[]) throws IOException {
        final WordTokenizer parser = new CustomTokenizer();
        List<Term> terms= HanLP.segment("一下子爆发一巴掌一般杀人罪");
        for(Term tern:terms){
            System.out.println(tern);
        }
        List<String> words = parser.tokenize("一下子爆发一巴掌一般杀人罪");
        for(String s:words){
            System.out.println(s);
        }
    }
}
