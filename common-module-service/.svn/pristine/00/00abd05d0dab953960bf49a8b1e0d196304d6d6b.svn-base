package com.crawlermanage.service.dailianmeng;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.dailianmeng.domain.json.LoanUnionFeedJson;
import com.crawler.dailianmeng.domain.json.UserFeedJson;
import com.crawler.dailianmeng.htmlparser.DaiLianMengUserInfoParser;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class DaiLianMengUserInfoService {
	
	@Autowired
	private CrawlerEngine crawlerEngine;

	@Autowired
	private DaiLianMengUserInfoParser dailianmengUserInfoParser;
	
	private static final Logger log = LoggerFactory.getLogger(DaiLianMengUserInfoService.class);
	
	public Result<Map<String,String>> searchPage(String keyword, boolean isDebug, String logback) {
		log.info("=============DaiLianMengUserInfoService.searchPageHandle start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.dailianmeng.com/xinyong/q/"+keyword+".html");
		webParam.setCodeImageId("//img[@id='yw0']");
		return searchPageHandle(webParam, isDebug);
	}
	
	public Result<Map<String,String>> searchPageHandle(WebParam webParam, boolean isDebug) {
		log.info("=============DaiLianMengUserInfoService.searchPageHandle start!=================");
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		Gson gson = new GsonBuilder().create();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.DAILIANMENG_GETSERIALIZEDWEBRESPONSE);
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		log.info("The DaiLianMengUserInfoService.searchPageHandle functionCallParam is: {}", param);
		
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
	
	public Result<String> getSourceHtml(String serializedFileName, String verifycode, boolean isDebug, String logback) {
		log.info("=============DaiLianMengUserInfoService.getSourceHtml start!=================");
		Gson gson = new GsonBuilder().create();
		Result<String> result = new Result<String>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.DAILIANMENG_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.dailianmeng.com/");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='SearchForm_verifyCode']");
		webParam.addParam("submitBtnXpath", "//button[@id='mingdan_button']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		log.info("The DaiLianMengUserInfoService.getSourceHtml functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		result.setData(result.getHtml());
		result.debugMode(isDebug);
		
		return result;
	}
	
	public UserFeedJson parseResultHtml(String html) {
		return dailianmengUserInfoParser.parseResultHtml(html);
	}
	
	public Result<Map<String,String>> searchDetailPage(String url, boolean isDebug, String logback) {
		log.info("=============DaiLianMengUserInfoService.searchDetailPage start!=================");
		
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.DAILIANMENG_DETAIL); 
		WebParam webParam = new WebParam();
		webParam.setUrl(url);
		webParam.setLogback(logback);
		fcm.setWebEngineParam(webParam);
		
		String param = fcm.toJson();
		log.info("searchUser param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		Result<Map<String, String>> parseDetailHtml = dailianmengUserInfoParser.parseDetailHtml(result.getHtml());
		result.setData(parseDetailHtml.getData());
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<List<LoanUnionFeedJson>> getDataOnce(String keyword, boolean isDebug, String logback) {
		Result<List<LoanUnionFeedJson>> result = new Result<List<LoanUnionFeedJson>>();
		Gson gson = new GsonBuilder().create();
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setCodeImageId("//img[@id='yw0']");
		webParam.addParam("keyword", keyword);
		webParam.addParam("imagecodeXpath", "//input[@id='SearchForm_verifyCode']");
		webParam.addParam("submitBtnXpath", "//button[@id='mingdan_button']");
		
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setWebEngineParam(webParam);
		fcm.setFunction(FunctionDefine.DAILIANMENG_SEARCHWITCHOCR);
		
		String param = fcm.toJson();
		log.info("searchUser param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			List<LoanUnionFeedJson> loanUnionFeedJsons = dailianmengUserInfoParser.parseDetailPage(resultHtml);
			result.setData(loanUnionFeedJsons);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
}
