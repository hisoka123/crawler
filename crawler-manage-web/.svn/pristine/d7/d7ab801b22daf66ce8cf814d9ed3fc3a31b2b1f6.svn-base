package com.crawlermanage.controller.api.searchengines;

import com.crawler.domain.json.Result;
import com.crawler.se.bing.domin.json.SearchEngineBing;
import com.crawler.se.haosou.domin.json.SearchEngineHaoSou;
import com.crawlermanage.service.searchengine.SEBingService;
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
 * Created by zxz on 2016/1/19.
 */
@Controller
@RequestMapping("/api/se/bing")
public class SEBingController {

    private static final Logger loger = LoggerFactory.getLogger(SEBingController.class);

    @Autowired
    SEBingService seBingService;

    @RequestMapping("/search")
    @ResponseBody
    public String search(@RequestParam("q") String crawlerContent, @RequestParam int pn, boolean isDebug, String logback){ //与火狐浏览器搜索结果一致
        loger.info("=====================================SEHaoSouController.search started!===============================================");
        loger.info("爬取关键词: {}", crawlerContent);

        String searchResult = "";
        crawlerContent = BaseUnit.encode(crawlerContent, "utf8");
        String url = "http://cn.bing.com/search?q="+crawlerContent+"&first="+(pn-1)+"1";
        loger.info("爬取好搜搜索url: {}", url);

        Result<List<SearchEngineBing>> result = seBingService.getListSearchResult(url, isDebug, logback);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        searchResult = gson.toJson(result);

        return searchResult;

    }
}
