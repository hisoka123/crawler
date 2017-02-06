package com.crawlermanage.service.zhihu;

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
import com.crawler.zhihu.domain.json.UserFeedJson;
import com.crawler.zhihu.htmlparser.ZhihuUserFeedListParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;


@Component
public class ZhihuUserSearchService {
	
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private ZhihuUserFeedListParser zhihuUserFeedListParser;
	  
	private static final Logger log = LoggerFactory.getLogger(ZhihuUserSearchService.class);
	
	@Cacheable(value="dataCache", key="'ZhihuUserSearchService.searchUser_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<UserFeedJson>> searchUser(String url, boolean isDebug, String logback) {
		log.info("url:{}",url);
		
		Result<List<UserFeedJson>> result = new Result<List<UserFeedJson>>();
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.CRAWLERENGINE); 
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUrl(url); 
		webParam.setUnit(UtilDefinition.HTMLUNIT);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("searchUser param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<UserFeedJson> users = zhihuUserFeedListParser.userParser(wpr.getHtml());
		result.setData(users);
		result.debugMode(isDebug);
		
		return result; 
	}
	
}
