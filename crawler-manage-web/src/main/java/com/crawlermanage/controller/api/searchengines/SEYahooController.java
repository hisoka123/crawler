package com.crawlermanage.controller.api.searchengines;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.crawler.domain.json.Result;
import com.crawler.se.yahoo.domin.json.SearchEngineYahoo;
import com.crawlermanage.service.searchengine.SEYahooService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.unit.BaseUnit;

/**
 * Created by zxz on 2016/1/19.
 */
@Controller
@RequestMapping("/api/se/yahoo")
public class SEYahooController {
	private static final Logger loger = LoggerFactory.getLogger(SEYahooController.class);

    @Autowired
    private SEYahooService seYahooService;
    
    @RequestMapping("/search")
    @ResponseBody
    public String search(@RequestParam("q") String crawlerContent, @RequestParam("pn") int pn, boolean isDebug, String logback){
        loger.info("=====================================SEYahooController.search started!===============================================");
        loger.info("爬取关键词: {}", crawlerContent);

        String searchResult = "";
        crawlerContent = BaseUnit.encode(crawlerContent, "utf8");
        String url = "http://sg.search.yahoo.com/search?q="+crawlerContent+"&b="+(pn-1)+"1";
        loger.info("爬取雅虎搜索url:{}", url);

        Result<List<SearchEngineYahoo>> result = seYahooService.getListSearchResult(url, isDebug, logback);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        searchResult = gson.toJson(result);

        return searchResult;

    }
}
