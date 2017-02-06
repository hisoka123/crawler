package com.crawlermanage.controller.api.baidu;

import com.crawler.baidu.domain.json.BaiduWiki;
import com.crawler.domain.json.Result;
import com.crawlermanage.service.baidu.BaiduService;
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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by zxz on 2015/8/14.
 */
@Controller
@RequestMapping("/api/baidu")
public class BaiduPublishController {

    private static final Logger loger = LoggerFactory.getLogger(BaiduPublishController.class);

    @Autowired
    private BaiduService baiduService;

    @RequestMapping(value = "/entry")
    @ResponseBody
    public String home(@RequestParam("q") String crawlerContent, boolean isDebug, String logback) {
        loger.info("=====================================BaiduController.entry started!===============================================");

        String searchResult = "";
        crawlerContent = BaseUnit.encode(crawlerContent, "utf8");
        loger.info("爬取词条关键词:{}" + BaseUnit.encode(crawlerContent, "utf-8"));
        String url = "http://baike.baidu.com/search?word="+crawlerContent;
        loger.info("爬取文章url:{}"+url);
        
        Result<List<BaiduWiki>> result = baiduService.getListSearchResult(url, isDebug, logback);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        searchResult = gson.toJson(result);
        loger.info("爬取文章结果:{}"+searchResult);
        
        return searchResult;
    }


    @RequestMapping(value = "/url")
    @ResponseBody
    public String detail(@RequestParam("url") String url, boolean isDebug, String logback) {
        loger.info("=====================================BaiduController.detail started!===============================================");

        String searchResult = "";

        loger.info("爬取页面:{}" + url, "utf-8");

        Result<BaiduWiki> searchDetailResult = baiduService.getDetailSearchResult(url, isDebug, logback);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        searchResult = gson.toJson(searchDetailResult);
        loger.info("爬取词条详细页面结果:{}"+searchResult);
        
        return searchResult;
    }

}
