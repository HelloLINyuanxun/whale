package com.lyx.whale.utility;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class JsonAnalysisUtil {
    public static List<String> getJDComments(String goodsId, int page) {
        List<String> comments = new ArrayList<>();
        for (int i = 0; i < page; i++) {
            String json = loadJson("http://club.jd.com/productpage/p-" + goodsId + "-s-0-t-1-p-" + page + ".html","GBK");
            if (json.isEmpty()) continue;
            JSONObject jdJSON = JSONObject.parseObject(json);
            if (jdJSON.isEmpty()) continue;
            JSONArray jdComments = jdJSON.getJSONArray("comments");
            if (jdComments.isEmpty()) continue;
            for (int j = 0; j < jdComments.size(); j++) {
                JSONObject comment = jdComments.getJSONObject(j);
                if (comment.isEmpty()) continue;
                String content = comment.getString("content");
                if (content.isEmpty()) continue;
                comments.add(content);
            }
        }
        return comments;
    }

    public static String getGoMePrice(String comboId) {
        String price = null;
        String json = loadJson("https://ss.gome.com.cn/search/v1/price/single/" + comboId + "/25010000/flag/item","utf-8");
        if (!json.isEmpty()) {
            JSONObject gomeJSON = JSONObject.parseObject(json);
            JSONObject result = gomeJSON.getJSONObject("result");
            if (!result.isEmpty()) {
                price = result.getString("price");
            }
        }
        return price;
    }

    public static List<String> getGoMeComments(String goodsId, int page) {
        List<String> comments = new ArrayList<>();
        for (int i = 0; i < page; i++) {
            String json = loadJson("https://ss.gome.com.cn/item/v1/prdevajsonp/appraiseNew/" + goodsId + "/1/all/0/flag/appraise","utf-8");
            if (json.isEmpty()) continue;
            JSONObject gomeJSON = JSONObject.parseObject(json);
            if (gomeJSON.isEmpty()) continue;
            JSONObject evaList = gomeJSON.getJSONObject("evaList");
            if (evaList.isEmpty()) continue;
            JSONArray Evalist = evaList.getJSONArray("Evalist");
            if (Evalist.isEmpty()) continue;
            for (int j = 0; j < Evalist.size(); j++) {
                JSONObject goodsDetail = Evalist.getJSONObject(j);
                if (goodsDetail.isEmpty()) continue;
                String comment = goodsDetail.getString("appraiseElSum");
                if (comment.isEmpty()) continue;
                comments.add(comment);
            }
        }
        return comments;
    }

    public static String loadJson(String url,String charset) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    uc.getInputStream(), charset));
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
