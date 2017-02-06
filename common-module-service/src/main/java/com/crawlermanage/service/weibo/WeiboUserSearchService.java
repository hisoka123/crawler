package com.crawlermanage.service.weibo;

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
import com.crawler.storm.def.WeiboLogonParam;
import com.crawler.weibo.domain.json.UserFeedJson;
import com.crawler.weibo.htmlparser.WeiboUserFeedListParser;
import com.crawlermanage.service.aspect.CrawlerEngine;

@Component
public class WeiboUserSearchService{
	
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private WeiboUserFeedListParser weiboUserFeedListParser;
	  
	private static final Logger log = LoggerFactory.getLogger(WeiboUserSearchService.class);
	
	
	@Cacheable(value="dataCache", key="'WeiboUserSearchService.searchUser_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<UserFeedJson>> searchUser(String url, boolean isDebug, String logback) {
		
		log.info("url:{}",url);
		
		Result<List<UserFeedJson>> result = new Result<List<UserFeedJson>>();
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.WEIBO_HANDLE_GETPAGE); 
		WeiboLogonParam weiboLogonParam = new WeiboLogonParam();
		weiboLogonParam.setUrl(url); 
		weiboLogonParam.setJsEnable(true);
		fcm.setWeiboLogonParam(weiboLogonParam);
		
	    WebParam webParam = new WebParam();
	    webParam.setLogback(logback);
	    fcm.setWebEngineParam(webParam);
		
		String param = fcm.toJson();
		log.info("searchUser param:{}",param);
		    
		result = crawlerEngine.execute(param, result);
		List<UserFeedJson> users = weiboUserFeedListParser.userScriptParser(result.getHtml());
		result.setData(users);
		result.debugMode(isDebug);
		
		return result; 
	}
	
}
