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
import com.crawler.weixin.domain.json.UserFeedJson;
import com.crawler.weixin.htmlparser.WeixinUserFeedListParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;

@Component
public class WeixinUserSearchService {
	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private WeixinUserFeedListParser weixinUserFeedListParser;
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUserSearchService.class);

	@Cacheable(value = "dataCache", key = "'WeixinUserSearchService.searchUser_' + #url + '&isDebug=' + #isDebug", unless = "#result==null || #isDebug==true")
	public Result<List<UserFeedJson>> searchUser(String url, boolean isDebug, String logback) {

		LOGGER.info("url:{}", url);
		Result<List<UserFeedJson>> result = new Result<List<UserFeedJson>>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CRAWLERENGINE);
		WebParam webParam = new WebParam();
		webParam.setUrl(url);
		webParam.setUnit(UtilDefinition.JSOUP);
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		LOGGER.info("WeixinUserSearchService.searchUser param:{}", param);

		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<UserFeedJson> users = weixinUserFeedListParser.userParser(wpr.getHtml());
		result.setData(users);
		result.debugMode(isDebug);
		
		return result;
	}

}
