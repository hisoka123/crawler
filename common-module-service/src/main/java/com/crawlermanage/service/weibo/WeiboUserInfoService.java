package com.crawlermanage.service.weibo;

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
import com.crawler.weibo.htmlparser.WeiboUserInfoParser;
import com.crawlermanage.service.aspect.CrawlerEngine;

@Component
public class WeiboUserInfoService {
	
	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private WeiboUserInfoParser weiboUserInfoParser;
	
	private static final Logger log = LoggerFactory.getLogger(WeiboUserInfoService.class);

	@Cacheable(value="dataCache", key="'WeiboUserInfoService.getUserInfo_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<UserFeedJson> getUserInfo(String url, boolean isDebug, String logback) {

		log.info("getUserInfo url:{}", url);

		Result<UserFeedJson> result = new Result<UserFeedJson>();
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
	    log.info("getUserInfo param:{}",param);
	    
		result = crawlerEngine.execute(param, result);
		UserFeedJson user = weiboUserInfoParser.userInfoScriptParser(result.getHtml());
		result.setData(user);
		result.debugMode(isDebug);
		
		return result;
	}
	
}
