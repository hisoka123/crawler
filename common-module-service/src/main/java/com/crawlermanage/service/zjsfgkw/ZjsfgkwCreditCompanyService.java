package com.crawlermanage.service.zjsfgkw;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.zjsfgkw.domain.json.CreditJson;
import com.crawler.zjsfgkw.htmlparser.CreditCompanyFeedListParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.htmlunit.definition.UtilDefinition;

@Component
public class ZjsfgkwCreditCompanyService{
	
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private CreditCompanyFeedListParser creditCompanyFeedListParser;
	  
	private static final Logger log = LoggerFactory.getLogger(ZjsfgkwCreditCompanyService.class);
	
	
	//@Cacheable(value="dataCache", key="'ZjsfgkwCreditCompanyService.searchCredit_' + #url + '&isDebug=' + #isDebug", unless="#result==null || #isDebug==true")  
	public Result<List<CreditJson>> searchCreditCompany(String url, boolean isDebug, String logback) {
		
		log.info("url:{}",url);
		
		Result<List<CreditJson>> result = new Result<List<CreditJson>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.ZJSFGKWCREDITCOMPANY);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUrl(url);
		webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("ZjsfgkwCreditCompanyService.searchCreditCompany param:{}", param);
		    
		result = crawlerEngine.execute(param, result);
		System.out.println(result.getHtml());
		
		String resultHtml = result.getHtml();
		Gson gson = new GsonBuilder().create();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		if (statusCodeDef!=null && StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setHtml((String) resultHtmlMap.get("searchPageHtml"));
			result.setError(errorMsg);
		}else{
			List<CreditJson> users;
			try {
				users = creditCompanyFeedListParser.creditCompanyParser((String) resultHtmlMap.get("searchPageHtml"));
				result.setData(users);
				result.debugMode(isDebug);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result; 
	}
	
}
