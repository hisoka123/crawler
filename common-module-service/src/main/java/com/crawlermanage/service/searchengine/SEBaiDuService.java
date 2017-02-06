package com.crawlermanage.service.searchengine;

import com.crawler.domain.json.Result;
import com.crawler.se.baidu.domin.json.SearchEngineBaidu;
import com.crawler.se.baidu.htmlparser.BaiduSearchParser;
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
 * Created by zxz on 2016/1/11.
 */
@Component("sEBaiDuService")
public class SEBaiDuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SEBaiDuService.class);

    @Autowired
    private CrawlerEngine crawlerEngine;

    @Autowired
    private BaiduSearchParser baiduSearchParser;

    @Cacheable(value="dataCache", key="'SEBaiduService.getListSearchResult' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true || #result.data==null")
    public Result<List<SearchEngineBaidu>> getListSearchResult(String url, boolean isDebug, String logback) {
        LOGGER.info("getBaiduSearch url:{}", url);

        List<SearchEngineBaidu> searchEngineBaidus = null;
        Result<List<SearchEngineBaidu>> result = new Result<List<SearchEngineBaidu>>();

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

        searchEngineBaidus = baiduSearchParser.searchListParser(wpr.getHtml());

//        for(SearchEngineBaidu searchEngineBaidu:searchEngineBaidus){
//            String originUrl = "";
//            wep.setUrl(searchEngineBaidu.getLinkUrl());
//            param = fcm.toJson();
//            Result<String> originUrlPageResutl = new Result<String>();
//
//            originUrlPageResutl = crawlerEngine.execute(param, originUrlPageResutl);
//            originUrlPageResutl = crawlerEngine.execute(param, originUrlPageResutl);
//            WebPageResponse wpr2 = gson.fromJson(originUrlPageResutl.getHtml(), WebPageResponse.class);
//            if (wpr2==null) {
//            	continue;
//            }
//            originUrl = wpr2.getUrl();
//            originUrl = originUrl.replaceAll("[?]from=baidu","");
//            searchEngineBaidu.setLinkUrl(originUrl);
//        }

        result.setData(searchEngineBaidus);
        result.debugMode(isDebug);

        return result;
    }

}
