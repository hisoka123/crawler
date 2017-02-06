package com.crawlermanage.service.sxjlcxpt;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.dailianmeng.htmlparser.DaiLianMengUserInfoParser;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawler.iecms.htmlparser.IecmsInfoParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.sxjlcxpt.domain.json.CreditrRecordQueryPlatformJson;
import com.crawler.sxjlcxpt.domain.json.UserFeedJson;
import com.crawler.sxjlcxpt.htmlparser.SxjlcxptInfoParser;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.domain.WebPageResponse;
import com.module.htmlunit.definition.UtilDefinition;

@Component
public class SxjlcxptUserInfoService {
	
	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private SxjlcxptInfoParser sxjlcxptInfoParser;
	
	private static final Logger log = LoggerFactory.getLogger(SxjlcxptUserInfoService.class);
	
	public UserFeedJson searchPage(String keyword, boolean isDebug) {
		log.info("=============SxjlcxptUserInfoService.searchPageHandle start!=================");
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.SXJLCXPTQUERYKEY );
		WebParam webParam = new WebParam();
		webParam.setUnit(UtilDefinition.HTMLUNIT);
		String urlkeygbk="";
		try {
			urlkeygbk=URLEncoder.encode(keyword, "gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webParam.setUrl("http://www.315cx.org.cn/search/?searchkey="+urlkeygbk+"&searchtype=1");
		//webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("DaiLianMengUserInfoService.searchPage param:{}", param);
		result = crawlerEngine.execute(param, result);
		//Gson gson = new Gson();    
		//WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		//List<UserFeedJson> users = weixinUserFeedListParser.userParser(wpr.getHtml());
		UserFeedJson  users=sxjlcxptInfoParser.parseResultHtml(result.getHtml());
		//UserFeedJson  users=sxjlcxptInfoParser.parseResultHtml((String)new GsonBuilder().create().fromJson(result.getHtml(), Map.class).get("html"));
		//result.setData(users);
		result.debugMode(isDebug);
		return users;
	}
	
	public Result<UserFeedJson> searchPagesx(String keyword, boolean isDebug, String logback) {
		log.info("=============SxjlcxptUserInfoService.searchPageHandle start!=================");
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.SXJLCXPTQUERYKEY );
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUnit(UtilDefinition.HTMLUNIT);
		String urlkeygbk="";
		try {
			urlkeygbk=URLEncoder.encode(keyword, "gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webParam.setUrl("http://www.315cx.org.cn/search/?searchkey="+urlkeygbk+"&searchtype=1");
		//webParam.setUnit(UtilDefinition.JSOUP);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("DaiLianMengUserInfoService.searchPage param:{}", param);
		result = crawlerEngine.execute(param, result);
		//Gson gson = new Gson();    
		//WebPageResponse wpr = gson.fromJson(result.getHtml(), WebPageResponse.class); 
		//List<UserFeedJson> users = weixinUserFeedListParser.userParser(wpr.getHtml());
		
		UserFeedJson  users=sxjlcxptInfoParser.parseResultHtml(result.getHtml());
		Result<UserFeedJson> my=new Result<UserFeedJson>(users, result.getHtml(), isDebug);
		//UserFeedJson  users=sxjlcxptInfoParser.parseResultHtml((String)new GsonBuilder().create().fromJson(result.getHtml(), Map.class).get("html"));
		//result.setData(users);
		result.debugMode(isDebug);
		return my;
	}

	
	public Result<List<CreditrRecordQueryPlatformJson>> getDataOnce(String keyword, boolean isDebug, String logback) {
		log.info("=============SxjlcxptUserInfoService.searchPageHandle start!=================");
		Result<List<CreditrRecordQueryPlatformJson>> result = new Result<List<CreditrRecordQueryPlatformJson>>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.SXJLCXPTQUERYKEY );
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setUnit(UtilDefinition.HTMLUNIT);
		String urlkeygbk="";
		try {
			urlkeygbk=URLEncoder.encode(keyword, "gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webParam.setUrl("http://www.315cx.org.cn/search/?searchkey="+urlkeygbk+"&searchtype=1");		
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		log.info("SxjlcxptUserInfoService.searchPage param:{}", param);
		result = crawlerEngine.execute(param, result);

		List<CreditrRecordQueryPlatformJson> creditrRecordQueryPlatformJsons = sxjlcxptInfoParser.parseResultHtmlSxjlcxpt(result.getHtml());
		if(creditrRecordQueryPlatformJsons.size()==0){
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorName("没有搜索到数据 ");
			result.setError(errorMsg);
		}
		result.setData(creditrRecordQueryPlatformJsons);						
				
		result.debugMode(isDebug);
		return result;
	}


}
