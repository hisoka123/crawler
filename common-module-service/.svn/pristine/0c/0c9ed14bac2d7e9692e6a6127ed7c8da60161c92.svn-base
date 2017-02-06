package com.crawlermanage.service.iautos;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.iautos.htmlparser.IautosSearchListParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.iautos.domain.json.UserFeedJson;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;
import com.module.htmlunit.unit.BaseUnit;

@Component
public class IautosSearchService {
	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private IautosSearchListParser iautosSearchListParser;
	private static final Logger LOGGER = LoggerFactory.getLogger(IautosSearchService.class);

	//	@Cacheable(value = "dataCache", key = "'IautosSearchListParser.search_' + #url + '&isDebug=' + #isDebug", unless = "#result==null || #isDebug==true")
	public Result<List<UserFeedJson>> search(String url, boolean isDebug, String logback) {

		LOGGER.info("url:{}", url);
		Result<List<UserFeedJson>> result = new Result<List<UserFeedJson>>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CRAWLERENGINE);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUrl(url);
		webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		LOGGER.info("IautosSearchListParser.search param:{}", param);

		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<UserFeedJson> users = iautosSearchListParser.userParser(wpr.getHtml());
		result.setData(users);
		result.debugMode(isDebug);		
		return result;
	}
	
	
	
	public Result<List<UserFeedJson>> getDataOnce(String city,String keyword, boolean isDebug, String logback) {
		LOGGER.info("keyword:{}",keyword);
		String keywordurl = BaseUnit.encode(keyword, "utf8");
		String url = "http://so.iautos.cn/";		
		if (city==null) {
			url+="quanguo/?kw="+keywordurl;
		}else if (city.equals("quanguo")) {
			url+="quanguo/?kw="+keywordurl;
		}else{
			url+=city+"/?kw="+keywordurl;
		}
		
		Result<List<UserFeedJson>> result = new Result<List<UserFeedJson>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.CRAWLERENGINE);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUrl(url);
		webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		LOGGER.info("IautosSearchListParser.search param:{}", param);

		result = crawlerEngine.execute(param, result);
		Gson gson = new Gson();    
		WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		List<UserFeedJson> users = iautosSearchListParser.userParser(wpr.getHtml());
		
		List<UserFeedJson> users2=new ArrayList<UserFeedJson>();
		int k=0;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getName().contains(keyword)) {
				k++;
				users2.add(users.get(i));
				if (k==5) {
					break;
				}
			}
		}		
		result.setData(users2);
		result.debugMode(isDebug);		
		return result;			
	
	}
	
	
	
}
