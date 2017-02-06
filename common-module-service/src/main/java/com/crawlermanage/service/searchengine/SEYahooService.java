package com.crawlermanage.service.searchengine;

import com.crawler.domain.json.Result;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.se.yahoo.domin.json.SearchEngineYahoo;
import com.crawler.se.yahoo.htmlparser.YahooSearchParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;

import java.util.List;

/**
 * Created by zxz on 2016/1/19.
 */
@Component("sEYahooService")
public class SEYahooService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SEYahooService.class);

    @Autowired
    private CrawlerEngine crawlerEngine;

    @Autowired
    private YahooSearchParser yahooSearchParser;

    @Cacheable(value="dataCache", key="'SEYahooService.getListSearchResult' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true || #result.data==null")
    public Result<List<SearchEngineYahoo>> getListSearchResult(String url, boolean isDebug, String logback) {
        LOGGER.info("getListSearchResult url:{}", url);

        List<SearchEngineYahoo> SearchEngineYahoos = null;
        Result<List<SearchEngineYahoo>> result = new Result<List<SearchEngineYahoo>>();

        FunctionCallParam fcm = new FunctionCallParam();
        fcm.setFunction(FunctionDefine.CRAWLERENGINE);
        WebParam wep = new WebParam();
        wep.setLogback(logback);
        wep.setUrl(url);
        wep.setUnit(UtilDefinition.JSOUP);
        fcm.setWebEngineParam(wep);

        String param = fcm.toJson();
        LOGGER.info("getArticles param:{}", param);

        result = crawlerEngine.execute(param, result);

        Gson gson = new Gson();
        WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class);

        SearchEngineYahoos = yahooSearchParser.searchListParser(wpr.getHtml());

//        for(SearchEngineYahoo SearchEngineYahoo:SearchEngineYahoos){
//            String originUrl = "";
//            wep.setUrl(SearchEngineYahoo.getLinkUrl());
//            param = fcm.toJson();
//            Result<String> originUrlPageResutl = new Result<String>();
//
//            originUrlPageResutl = crawlerEngine.execute(param, originUrlPageResutl);
//            WebPageResponse wpr2 = gson.fromJson(originUrlPageResutl.getHtml(), WebPageResponse.class);
//            if (wpr2==null) {
//            	continue;
//            }
//            originUrl = wpr2.getUrl();
//            if (originUrl==null) {
//            	continue;
//            }
//            originUrl = originUrl.replaceAll("[?]from=yahoo","");
//            SearchEngineYahoo.setLinkUrl(originUrl);
//        }

        result.setData(SearchEngineYahoos);
        result.debugMode(isDebug);

        return result;
    }
}
