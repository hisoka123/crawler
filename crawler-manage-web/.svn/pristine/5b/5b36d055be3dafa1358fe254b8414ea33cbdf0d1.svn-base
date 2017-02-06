package com.crawlermanage.controller.api.searchengines;

import com.crawler.domain.json.Result;
import com.crawler.se.baidu.domin.json.SearchEngineBaidu;
import com.crawlermanage.service.searchengine.SEBaiDuService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zxz on 2016/1/11.
 */

@Controller
@RequestMapping("/api/se/baidu")
public class SEBaiDuController {

    private static final Logger loger = LoggerFactory.getLogger(SEBaiDuController.class);

    @Autowired
    SEBaiDuService seBaiDuService;

    @RequestMapping("/search")
    @ResponseBody
    public String search(@RequestParam("q") String crawlerContent, @RequestParam("pn") int pn, boolean isDebug, String logback){
        loger.info("=====================================SEBaiduController.search started!===============================================");
        loger.info("爬取关键词: {}", crawlerContent);

        String searchResult = "";
        crawlerContent = BaseUnit.encode(crawlerContent, "utf8");
        String url = "http://www.baidu.com/s?wd="+crawlerContent+"&pn="+(pn-1)+"0";
        loger.info("爬取百度搜索url:{}", url);

        Result<List<SearchEngineBaidu>> result = seBaiDuService.getListSearchResult(url, isDebug, logback);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        searchResult = gson.toJson(result);

        return searchResult;

    }
}
