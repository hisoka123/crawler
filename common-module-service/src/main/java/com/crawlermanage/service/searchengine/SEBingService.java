package com.crawlermanage.service.searchengine;

import com.crawler.domain.json.Result;
import com.crawler.se.bing.domin.json.SearchEngineBing;
import com.crawler.se.bing.htmlparser.BingSearchParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zxz on 2016/1/19.
 */
@Component("sEBingService")
public class SEBingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SEBingService.class);

    @Autowired
    private CrawlerEngine crawlerEngine;

    @Autowired
    private BingSearchParser bingSearchParser;

    @Cacheable(value="dataCache", key="'SEBingService.getListSearchResult' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true || #result.data==null")
    public Result<List<SearchEngineBing>> getListSearchResult(String url, boolean isDebug, String logback) {
        LOGGER.info("getListSearchResult url:{}", url);

        List<SearchEngineBing> searchEngineBings = null;
        Result<List<SearchEngineBing>> result = new Result<List<SearchEngineBing>>();

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

        searchEngineBings = bingSearchParser.searchListParser(wpr.getHtml());

        result.setData(searchEngineBings);
        result.debugMode(isDebug);

        return result;
    }
}
