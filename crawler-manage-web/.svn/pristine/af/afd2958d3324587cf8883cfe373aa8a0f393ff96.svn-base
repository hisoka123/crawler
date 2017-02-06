package com.crawlermanage.controller.api.searchengines;

import com.crawler.domain.json.Result;
import com.crawler.se.haosou.domin.json.SearchEngineHaoSou;
import com.crawlermanage.service.searchengine.SEHaoSouService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by zxz on 2016/1/11.
 */

@Controller
@RequestMapping("/api/se/haosou")
public class SEHaoSouController {

    private static final Logger loger = LoggerFactory.getLogger(SEHaoSouController.class);

    @Autowired
    SEHaoSouService sEHaoSouService;

    @RequestMapping("/search")
    @ResponseBody
    public String search(@RequestParam("q") String crawlerContent, @RequestParam int pn, boolean isDebug, String logback){
        loger.info("=====================================SEHaoSouController.search started!===============================================");
        loger.info("爬取关键词: {}", crawlerContent);

        String searchResult = "";
        @SuppressWarnings("deprecation")
		String url = "http://www.haosou.com/s?q="+URLEncoder.encode(crawlerContent)+"&pn="+pn;
        loger.info("爬取好搜搜索url:{}", url);

        Result<List<SearchEngineHaoSou>> result = sEHaoSouService.getListSearchResult(url, isDebug, logback);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        searchResult = gson.toJson(result);

        return searchResult;

    }
}
