package com.crawlermanage.service.weibo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.weibo.domain.json.TweetJson;
import com.crawler.weibo.htmlparser.WeiboTopicParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;

@Component
public class WeiboTopicService {

	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private WeiboTopicParser weiboTopicParser;

	private static final Logger log = LoggerFactory.getLogger(WeiboTopicParser.class);

	// @Cacheable(value="dataCache", key="'_getTweets_'+#url",
	// unless="#result == null")
	public Result<List<TweetJson>> getTopicTweets(String url, boolean isDebug, String logback) {
		log.info("getTopicTweets url:{}", url);

		Result<List<TweetJson>> result = new Result<List<TweetJson>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CRAWLERENGINE);
		WebParam wep = new WebParam();
		wep.setUrl(url);
		wep.setUnit(UtilDefinition.JSOUP);
		wep.setLogback(logback);
		fcm.setWebEngineParam(wep);

		String param = fcm.toJson();
		log.info("getTopicTweets param:{}", param);

		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<TweetJson> tweets = weiboTopicParser.tweetScriptParser(wpr.getHtml());
		result.setData(tweets);
		result.debugMode(isDebug);
		
		return result;
	}

}
