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
import com.crawler.storm.def.LogonParam;
import com.crawler.storm.def.WebParam;
import com.crawler.zhihu.domain.json.UserFeedJson;
import com.crawler.zhihu.domain.json.ZhihuAnswer;
import com.crawler.zhihu.domain.json.ZhihuQuestion;
import com.crawler.zhihu.htmlparser.ZhihuUserFeedListParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;

@Component
public class ZhihuUserProfileService {
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private ZhihuUserFeedListParser zhihuUserFeedListParser;
	  
	private static final Logger log = LoggerFactory.getLogger(ZhihuUserProfileService.class);
	
	@Cacheable(value="dataCache", key="'ZhihuUserFeedListParser.userProfile_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<UserFeedJson> userProfile(String url, boolean isDebug) {
		log.info("url:{}",url);
		
		Result<UserFeedJson> result = new Result<UserFeedJson>();
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.CRAWLERENGINE); 
		WebParam webParam = new WebParam();
		webParam.setUrl(url); 
		webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("userProfile param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		UserFeedJson user = zhihuUserFeedListParser.profileParser(wpr.getHtml());
		result.setData(user);
		result.debugMode(isDebug);
		
		return result; 
	}
	
	@Cacheable(value="dataCache", key="'ZhihuUserFeedListParser.userFollowees_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")
	public Result<List<UserFeedJson>> userFollowees(String url, boolean isDebug, String logback) {
		log.info("url:{}",url);
		
		Result<List<UserFeedJson>> result = new Result<List<UserFeedJson>>();
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.COOKIE_HANDLE_GETPAGE); 
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		
		LogonParam logonParam = new LogonParam();
		logonParam.setUrl(url);
		logonParam.setUnit(UtilDefinition.JSOUP);
		logonParam.setDomains(ZhihuUserFeedListParser.COOKIE_DOMAINS);
		
	    fcm.setLogonParam(logonParam);
		String param = fcm.toJson();
		log.info("userFollowees param:{}", param);
		
		
		result = crawlerEngine.execute(param, result);
		List<UserFeedJson> followees = zhihuUserFeedListParser.followeesParser(result.getHtml());
		result.setData(followees);
		result.debugMode(isDebug);
		
		return result; 
	}
	
	@Cacheable(value="dataCache", key="'ZhihuUserFeedListParser.userAsks_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true") 
	public Result<List<ZhihuQuestion>> userAsks(String url, boolean isDebug, String logback) {
		log.info("url:{}",url);
		
		Result<List<ZhihuQuestion>> result = new Result<List<ZhihuQuestion>>();
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.CRAWLERENGINE); 
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUrl(url); 
		webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("userAsks param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<ZhihuQuestion> questions = zhihuUserFeedListParser.asksParser(wpr.getHtml());
		result.setData(questions);
		result.debugMode(isDebug);
		
		return result; 
	}
	
	@Cacheable(value="dataCache", key="'ZhihuUserFeedListParser.userAnswers_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")
	public Result<List<ZhihuAnswer>> userAnswers(String url, boolean isDebug, String logback) {
		log.info("url:{}",url);
		
		Result<List<ZhihuAnswer>> result = new Result<List<ZhihuAnswer>>();
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.CRAWLERENGINE); 
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUrl(url); 
		webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("userAnswers param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<ZhihuAnswer> answers = zhihuUserFeedListParser.answersParser(wpr.getHtml());
		result.setData(answers);
		result.debugMode(isDebug);
		
		return result; 
	}
	
}
