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
import com.crawler.weibo.domain.json.TweetJson;
import com.crawler.weibo.htmlparser.WeiboStatusListParser;
import com.crawlermanage.service.aspect.CrawlerEngine;

@Component
public class WeiboTweetService {
	
	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private WeiboStatusListParser weiboStatusListParser;
	
	private static final Logger log = LoggerFactory.getLogger(WeiboTweetService.class);
	
	@Cacheable(value="dataCache", key="'WeiboTweetService.getTweets_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<TweetJson>> getTweets(String url, boolean isDebug, String logback) {

		log.info("getTweets url:{}", url);

		Result<List<TweetJson>> result = new Result<List<TweetJson>>(); 
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
	    log.info("getTweets param:{}",param);

	    result = crawlerEngine.execute(param, result);
		List<TweetJson> tweets = weiboStatusListParser.tweetScriptParser(result.getHtml());
		result.setData(tweets);
		result.debugMode(isDebug);
		
		return result;
	}
	
}
