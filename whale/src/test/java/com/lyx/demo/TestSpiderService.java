package com.lyx.demo;

import com.google.common.collect.Maps;
import com.lyx.whale.entity.GoodsInfo;
import com.lyx.whale.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestSpiderService {
    @Autowired
    private SpiderService spiderService;

    public static void main(String[] args) {
        List<GoodsInfo> bigGomeGoods = new ArrayList<>();
        final Map<String, String> gomeParams = Maps.newHashMap();
        gomeParams.put("question", "零食");
        gomeParams.put("page", "2");
    }

}
