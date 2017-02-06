package com.crawlermanage.service.weixin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.weixin.domain.json.WeixinArticle;
import com.crawler.weixin.htmlparser.WeixinArticleParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;


@Component
public class WeixinArticleService {
	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private WeixinArticleParser weixinArticleParser;
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinArticleService.class);
	
	@Cacheable(value="dataCache", key="'WeixinArticleService.getArticles_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<WeixinArticle>> getArticles(String url, boolean isDebug) {
		LOGGER.info("getArticles url:{}", url);

		Result<List<WeixinArticle>> result = new Result<List<WeixinArticle>>(); 
		FunctionCallParam fcm = new FunctionCallParam(); 
		fcm.setFunction(FunctionDefine.CRAWLERENGINE);
        WebParam wep = new WebParam();
        wep.setUrl(url);
        wep.setUnit(UtilDefinition.JSOUP);
        fcm.setWebEngineParam(wep); 
	    
	    String param = fcm.toJson();
	    LOGGER.info("getArticles param:{}", param);

	    result = crawlerEngine.execute(param, result);
	    Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<WeixinArticle> articles = weixinArticleParser.articleParser(wpr.getHtml());
		result.setData(articles);
		result.debugMode(isDebug);
		
		return result;
	}
	
}
