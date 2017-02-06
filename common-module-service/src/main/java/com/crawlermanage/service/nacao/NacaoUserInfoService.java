package com.crawlermanage.service.nacao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.dailianmeng.domain.json.UserFeedJson;
import com.crawler.dailianmeng.htmlparser.DaiLianMengUserInfoParser;
import com.crawler.domain.json.Result;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class NacaoUserInfoService {
	
	@Autowired
	private CrawlerEngine crawlerEngine;

//	@Autowired
//	private DaiLianMengUserInfoParser dailianmengUserInfoParser;
	
	private static final Logger log = LoggerFactory.getLogger(NacaoUserInfoService.class);
	
	public Result<Map<String,String>> searchPage(String keyword, boolean isDebug, String logback) {
		log.info("=============NacaoUserInfoService.searchPageHandle start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.nacao.org.cn/");
		webParam.addParam("keyword", keyword);
		//webParam.setCodeImageId("//img[@id='validateImage']");
		return searchPageHandle(webParam, isDebug);
	}
	
	public Result<Map<String,String>> searchPageHandle(WebParam webParam, boolean isDebug) {
		log.info("=============NacaoUserInfoService.searchPageHandle start!=================");
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		Gson gson = new GsonBuilder().create();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.NACAO_GETSERIALIZEDWEBRESPONSE);
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		log.info("The NacaoUserInfoService.searchPageHandle functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		Type mapType = new TypeToken<HashMap<String,String>>(){}.getType();
		Map<String, String> data = gson.fromJson(result.getHtml(), mapType);
		result.setData(data);
		result.debugMode(isDebug);
		
		return result;
	}
	
	/**
	 * 获取（JSON）
	 * @param corp_code
	 * @param corp_name
	 * @param sc_code 
	 * @param codea 
	 * @return Result<String>
	 * @throws IOException
	 */
	public Result<String> queryorgname(String tit2,String serializedFileName,boolean isDebug, String logback) throws IOException{
		log.info("=============NacaoUserInfoService.queryorgname start!=================");
		Result<String> result = new Result<String>();
		Gson gson = new GsonBuilder().create();
		//boolean isDebug=true;
	
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.NACAO_GETSERIALIZEDWEBRESPONSEQURY);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.nacao.org.cn/");
		webParam.setSerializedFileName(serializedFileName);	
		//webParam.setMethod("post");
		webParam.addParam("tit2", tit2);
	
		webParam.addParam("keywordXpath1", "//input[@name='tit2']");

		webParam.addParam("queryBtnXpath", "//input[@type='image'][@onclick='submitForm(2)']");
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		log.info("The NacaoUserInfoService.queryorgname functionCallParam is: {}", param);
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}		
		result.setData(result.getHtml());
		result.debugMode(isDebug);
		return result;
	}
	
//	public Result<String> getSourceHtml(String serializedFileName, String verifycode, boolean isDebug, String logback) {
//		log.info("=============DaiLianMengUserInfoService.getSourceHtml start!=================");
//		Gson gson = new GsonBuilder().create();
//		Result<String> result = new Result<String>();
//		
//		FunctionCallParam fcm = new FunctionCallParam();
//		fcm.setFunction(FunctionDefine.DAILIANMENG_SEARCH);
//		WebParam webParam = new WebParam();
//		webParam.setLogback(logback);
//		webParam.setSearchPage("http://www.dailianmeng.com/");
//		webParam.setSerializedFileName(serializedFileName);
//		webParam.addParam("imagecode", verifycode);
//		webParam.addParam("imagecodeXpath", "//input[@id='SearchForm_verifyCode']");
//		webParam.addParam("submitBtnXpath", "//button[@id='mingdan_button']");
//		fcm.setWebEngineParam(webParam);
//		
//		String param = gson.toJson(fcm);
//		log.info("The DaiLianMengUserInfoService.getSourceHtml functionCallParam is: {}", param);
//		
//		result = crawlerEngine.execute(param, result);
//		if (result.getError()!=null) {
//			return result;
//		}
//		
//		result.setData(result.getHtml());
//		result.debugMode(isDebug);
//		
//		return result;
//	}
//	
//	public UserFeedJson parseResultHtml(String html) {
//		return dailianmengUserInfoParser.parseResultHtml(html);
//	}
	
//	public Result<Map<String,String>> searchDetailPage(String url, boolean isDebug, String logback) {
//		log.info("=============DaiLianMengUserInfoService.searchDetailPage start!=================");
//		
//		Result<Map<String,String>> result = new Result<Map<String,String>>();
//		
//		FunctionCallParam fcm = new FunctionCallParam();  
//		fcm.setFunction(FunctionDefine.DAILIANMENG_DETAIL); 
//		WebParam webParam = new WebParam();
//		webParam.setUrl(url);
//		webParam.setLogback(logback);
//		fcm.setWebEngineParam(webParam);
//		
//		String param = fcm.toJson();
//		log.info("searchUser param:{}",param);
//		
//		result = crawlerEngine.execute(param, result);
//		if (result.getError()!=null) {
//			return result;
//		}
//		
//		Result<Map<String, String>> parseDetailHtml = dailianmengUserInfoParser.parseDetailHtml(result.getHtml());
//		result.setData(parseDetailHtml.getData());
//		result.debugMode(isDebug);
//		
//		return result;
//	}
}
