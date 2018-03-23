package com.lyx.whale.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
@Component
/**
 * 爬取京东商城商品服务，已经重构到JsonAnalysisUtil中
 */
public class SpiderCommentService {
    public static List<String> spiderComments(String goodId, int frequency) {
        List<String> goodComments = new ArrayList<>();
        for (int page = 0; page < frequency; page++) {
            String json = loadJson("http://club.jd.com/productpage/p-" + goodId + "-s-0-t-1-p-" + page + ".html");
            JSONObject jObj = JSONObject.parseObject(json);
            if (jObj != null) {
                JSONArray comments = jObj.getJSONArray("comments");
                if (comments != null && !"".equals(comments)) {
                    for (int i = 0; i < comments.size(); i++) {
                        JSONObject comment = comments.getJSONObject(i);
                        String tempContent = comment.getString("content");
                        if (tempContent != null && !"".equals(tempContent)) {
                            goodComments.add(tempContent);
                        } else {
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
        return goodComments;
    }

    private static String loadJson(String url) {
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
