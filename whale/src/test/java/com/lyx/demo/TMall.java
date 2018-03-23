package com.lyx.demo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lyx.whale.common.HttpClientUtils;
import com.lyx.whale.entity.GoodsInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Map;

public class TMall {

    public static void main(String[] args) {
        TMall guoMei = new TMall();
        Map<String, String> params = Maps.newHashMap();
        params.put("q", "热水器");
        params.put("page", "10");

        guoMei.spiderData("https://list.tmall.com/search_product.htm", params);
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
        Elements elements = document.getElementById("J_ItemList").select("div[class=product]");
        System.out.println(elements.isEmpty());
//        // 获取产品列
//        Elements elements = elementP.select("li[class=product-item]").select("div[class=item-tab-warp]");
        for (Element element : elements) {
            String goodsName = element.select("div[class=productTitle productTitle-spu]")
                    .select("a").text();
            System.out.println(goodsName);
            String imgUrl = element.select("div[class=productImg-wrap]").select("a[class=productImg]").select("img").attr("src");
            System.out.println(imgUrl);
            String goodsPrice=element.select("p[class=productPrice]").select("em").attr("title");
            System.out.println(goodsPrice);
            //String goodsPrice = spiderGuoMeiData("https://ss.gome.com.cn/search/v1/price/single/9134291566/1123231090/25010000/flag/item");
            // System.out.println(goodsPrice);
//            String producthref = element.select("p[class=item-name]")
//                    .select("a[class=emcodeItem item-link]").attr("href");
//            String productStatus = element.select("p[class=item-pic]")
//                    .select("a[class=item-link]").attr("title");
            //System.out.println(productStatus);
        }
        return goods;
    }
}
