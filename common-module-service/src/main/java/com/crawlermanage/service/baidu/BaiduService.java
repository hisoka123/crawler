package com.crawlermanage.service.baidu;

import com.crawler.baidu.domain.json.BaiduWiki;
import com.crawler.baidu.htmlparser.WikiParser;
import com.crawler.domain.json.Result;
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
 * Created by zxz on 2015/8/10.
 */
@Component
public class BaiduService {


    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduService.class);

    @Autowired
    private CrawlerEngine crawlerEngine;

    @Autowired
    private WikiParser wikiParser;

    
    @Cacheable(value="dataCache", key="'BaiduService.getListSearchResult' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true || #result.data==null")  
    public Result<List<BaiduWiki>> getListSearchResult(String url, boolean isDebug, String logback) {
        LOGGER.info("getBaiduSearch url:{}", url);

        List<BaiduWiki> baiduWikis = null;
        Result<List<BaiduWiki>> result = new Result<List<BaiduWiki>>();
        
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
        baiduWikis = wikiParser.wikiListParser(wpr.getHtml());
        result.setData(baiduWikis);
        result.debugMode(isDebug);
        
        return result;
    }


    @Cacheable(value="dataCache", key="'BaiduService.getDetailSearchResult' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true || #result.data==null")  
    public Result<BaiduWiki> getDetailSearchResult(String url, boolean isDebug, String logback) {
        LOGGER.info("getDetailSearchResult url:{}", url);
        
        BaiduWiki baiduWikis = null;
        Result<BaiduWiki> result = new Result<BaiduWiki>();
        
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
        baiduWikis = wikiParser.parseDetail(url,wpr.getHtml());
        result.setData(baiduWikis);
        result.debugMode(isDebug);

        return result;
    }

}
