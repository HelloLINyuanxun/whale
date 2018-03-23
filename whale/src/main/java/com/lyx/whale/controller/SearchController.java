package com.lyx.whale.controller;

import com.google.common.collect.Maps;
import com.lyx.whale.constant.SysConstant;
import com.lyx.whale.entity.GoodsInfo;
import com.lyx.whale.service.SpiderService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.hankcs.hanlp.utility.Predefine.logger;

@Controller
public class SearchController {
    @Autowired
    private SpiderService spiderService;

    @RequestMapping("/search")
    public String findGoods(Model model, String keyword) {
        //爬取商品结合
        List<GoodsInfo> bigGoods = new ArrayList<>();
        List<GoodsInfo> bigGomeGoods = new ArrayList<>();
        // 使用现线程池提交任务
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 引入countDownLatch进行线程同步，使主线程等待线程池的所有任务结束，便于计时
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i < 11; i += 2) {
            final Map<String, String> params = Maps.newHashMap();
            params.put("keyword", keyword);
            params.put("enc", "utf-8");
            params.put("wc", keyword);
            params.put("page", i + "");
            final Map<String, String> gomeParams = Maps.newHashMap();
            gomeParams.put("question", keyword);
            gomeParams.put("page", i + "");
            executorService.submit(() -> {
                List<GoodsInfo> smallGoods;
                smallGoods = spiderService.spiderData(SysConstant.BASE_URL,
                        params);
                bigGoods.addAll(smallGoods);
                List<GoodsInfo> gomeSmallGoods;
                gomeSmallGoods = spiderService.spiderGomeData(SysConstant.GOME_BASE_URL,
                        gomeParams);
                bigGomeGoods.addAll(gomeSmallGoods);
                countDownLatch.countDown();
            });

            // 非lamda表达式线程
//            executorService.submit(new Runnable() {
//                public void run() {
//                    smallGoods = spiderService.spiderData(SysConstant.BASE_URL,
//                            params);
//                    biglGoods.addAll(smallGoods);
//                    countDownLatch.countDown();
//                }
//            });
            //

        }
        model.addAttribute("bigGoods", bigGoods);
        System.out.println(bigGomeGoods.isEmpty());
        model.addAttribute("bigGomeGoods", bigGomeGoods);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("爬虫结束");
        return "result";
    }
}
