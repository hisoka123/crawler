package com.crawlermanage.service.renfawang;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.renfawang.domain.json.PeopleCourtFeedJson;
import com.crawler.renfawang.htmlparser.RenFaWangInfoParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Component
public class RenFaWangService {
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private RenFaWangInfoParser rfwInfoParser;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RenFaWangService.class);
	
	public Result<Map<String,String>> searchPageHandle( boolean isDebug, String logback) {
		LOGGER.info("=============RenFaWangService.searchPageHandle start!=================");
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		Gson gson = new GsonBuilder().create();
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://zhixing.court.gov.cn/search/");
		webParam.setCodeImageId("//img[@id='captchaImg']");			
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.RENFAWANG_GETSERIALIZEDWEBRESPONSE);
		fcm.setWebEngineParam(webParam);		
		String param = gson.toJson(fcm);
		LOGGER.info("The RenFaWangService.searchPageHandle functionCallParam is: {}", param);		
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
	
	public Result<List<Map<String, String>>> getSearchData(String pname, String cardNum, String j_captcha, 
			String serializedFileName, boolean isDebug, String logback) {
		Result<String> sourseHtml = getSourceHtml(pname, cardNum, j_captcha, serializedFileName, isDebug, logback);
		
		String html = sourseHtml.getData();
		
		Result<List<Map<String, String>>> result = rfwInfoParser.parseHtml(html);
		if (isDebug) {
			result.setHtml(html);
		}
		return result;
	}
	
	public Result<String> getSourceHtml(String pname, String cardNum, String j_captcha, 
			String serializedFileName, boolean isDebug, String logback) {
		LOGGER.info("=============RenFaWangService.getSourceHtml start!=================");
		Gson gson = new GsonBuilder().create();
		Result<String> result = new Result<String>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.RENFAWANG_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://zhixing.court.gov.cn/search/");
		webParam.setUrl("http://zhixing.court.gov.cn/search/newsearch");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("pname", pname);
		webParam.addParam("cardNum", cardNum);
		webParam.addParam("j_captcha", j_captcha);
		webParam.addParam("searchCourtName", "全国法院（包含地方各级法院）");
		webParam.addParam("selectCourtId", "1");
		webParam.addParam("selectCourtArrange", "1");
		webParam.addParam("pnameXpath", "//input[@id='pname']");
		webParam.addParam("cardNumXpath", "//input[@id='cardNum']");
		webParam.addParam("searchCourtNameXpath", "//input[@id='searchCourtName']");
		webParam.addParam("selectCourtIdXpath", "//input[@id='selectCourtId']");
		webParam.addParam("selectCourtArrangeXpath", "//input[@id='selectCourtArrange']");
		webParam.addParam("imagecodeXpath", "//input[@id='j_captcha']");
		webParam.addParam("submitBtnXpath", "//button[@id='button']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The RenFaWangService.getSourceHtml functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		result.setData(result.getHtml());
		result.debugMode(isDebug);
		
		return result;
		
	}
	
	public Result<Map<String, String>> getDetailData(String pNum, String serializedFileName, boolean isDebug, String logback) {
		LOGGER.info("=============RenFaWangService.getDetailData start!=================");
		
		Result<Map<String, String>> result = new Result<Map<String, String>>();
		
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setFunction(FunctionDefine.RENFAWANG_DETAIL); 
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.setUrl("http://zhixing.court.gov.cn/search/newdetail?id="+pNum);
		fcm.setWebEngineParam(webParam);
		
		String param = fcm.toJson();
		LOGGER.info("getDetailData param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		LOGGER.info("renfawangDetailData:{}",result.getHtml());
		
		String resHtml = result.getHtml();		
		JsonObject obj = new JsonParser().parse(resHtml).getAsJsonObject();
		String ccrs = obj.get("html").getAsString();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map resMap = gson.fromJson(ccrs, Map.class);
		result.setData(resMap);
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<List<PeopleCourtFeedJson>> getDataOnce(String keyword, String queryType, boolean isDebug, String logback) {
		Result<List<PeopleCourtFeedJson>> result = new Result<List<PeopleCourtFeedJson>>();
		Gson gson = new GsonBuilder().create();
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://zhixing.court.gov.cn/search/");
		webParam.setCodeImageId("//img[@id='captchaImg']");
		webParam.addParam("keyword", keyword);
		webParam.addParam("queryType", queryType);
		
		FunctionCallParam fcm = new FunctionCallParam();  
		fcm.setWebEngineParam(webParam);
		fcm.setFunction(FunctionDefine.RENFAWANG_SEARCHWITCHOCR);
		
		String param = fcm.toJson();
		LOGGER.info("getDetailData param:{}",param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();		
		LOGGER.info("renfawangDetailData:{}", resultHtml);
		
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			List<PeopleCourtFeedJson> peopleCourtFeedJsons = rfwInfoParser.parseDetailPage(resultHtml);
			result.setData(peopleCourtFeedJsons);
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
		} else if (StatusCodeDef.ILLEGAL_PARAM.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_PARAM);
			errorMsg.setErrorMsg("非法身份证号 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("非法身份证号");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
}

