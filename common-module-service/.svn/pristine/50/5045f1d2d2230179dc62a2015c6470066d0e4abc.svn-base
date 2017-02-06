/**
 * 
 */
package com.crawlermanage.service.linkedin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawler.linkedin.htmlparser.LinkedinUserInfoParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.LogonParam;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.module.htmlunit.definition.UtilDefinition;

/**
 * @author kingly
 *
 */
@Component
public class LinkedinUserInfoService {
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private LinkedinUserInfoParser linkedinUserInfoParser;
	//private static final Logger LOGGER = LoggerFactory.getLogger(LinkedinUserInfoService.class);
	
	@Cacheable(value="dataCache", key="'LinkedinUserInfoService.getUserInfo_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true || #result.data.name==null || #result.data.profile==null")
	public Result<UserFeedJson> getUserInfo(String url, boolean isDebug, String logback) {
		Result<UserFeedJson> result = new Result<UserFeedJson>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.COOKIE_HANDLE_GETPAGE); 
		
		LogonParam logonParam = new LogonParam();
		logonParam.setUrl(url);
		logonParam.setUnit(UtilDefinition.HTMLUNIT);
		logonParam.setDomains(LinkedinUserInfoParser.COOKIE_DOMAINS);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		
		fcm.setWebEngineParam(webParam);
		fcm.setLogonParam(logonParam);
		String param = fcm.toJson();
		
		result = crawlerEngine.execute(param, result);
		UserFeedJson user = linkedinUserInfoParser.userParser(result.getHtml());
		result.setData(user);
		result.debugMode(isDebug);
		
		return result;
	}
	
}
