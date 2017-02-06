/**
 * 
 */
package com.crawlermanage.service.linkedin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.linkedin.domain.json.UserFeedJson;
import com.crawler.linkedin.htmlparser.LinkedinUserFeedListParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.LogonParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.module.htmlunit.definition.UtilDefinition;

/**
 * @author kingly
 *
 */

@Component
public class LinkedinUserSearchService {
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private LinkedinUserFeedListParser linkedinUserFeedListParser;
	//private static final Logger LOGGER = LoggerFactory.getLogger(LinkedinUserSearchService.class);
	
	@Cacheable(value="dataCache", key="'LinkedinUserSearchService.searchUser_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true || #result.data==null")  
	public Result<List<UserFeedJson>> searchUser(String url, boolean isDebug) {
		Result<List<UserFeedJson>> result = new Result<List<UserFeedJson>>();
		
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.COOKIE_HANDLE_GETPAGE); 
		
		LogonParam logonParam = new LogonParam();
		logonParam.setUrl(url);
		logonParam.setUnit(UtilDefinition.JSOUP);
        logonParam.setDomains(LinkedinUserFeedListParser.COOKIE_DOMAINS);

		fcm.setLogonParam(logonParam);
		String param = fcm.toJson();
		
		result = crawlerEngine.execute(param, result);
		List<UserFeedJson> users = linkedinUserFeedListParser.userSearchParser(result.getHtml()); 
		result.setData(users);
		result.debugMode(isDebug);
		
		return result;
	}
	
	public static final String getUTF8_MARK() {
		return LinkedinUserFeedListParser.UTF8_MARK;
	}
}
