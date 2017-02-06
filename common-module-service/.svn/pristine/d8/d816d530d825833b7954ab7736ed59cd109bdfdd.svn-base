package com.crawlermanage.service.gsxt;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.crawler.gsxt.htmlparser.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.gsxt.domain.json.AicFeedJson;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component
public class GsxtService {
	@Autowired
	private CrawlerEngine crawlerEngine;
	
	@Autowired
	private GsxtBeijingParser gsxtBeijingParser;
	@Autowired
	private GsxtShanxiParser gsxtShanxiParser;
	@Autowired
	private GsxtTianjinParser gsxtTianjinParser;
	@Autowired
	private GsxtJiangsuParser gsxtJiangsuParser;
	@Autowired
	private GsxtJilinParser gsxtJilinParser;
	@Autowired
	private GsxtShandongParser gsxtShandongParser;
	@Autowired
	private GsxtAnhuiParser gsxtAnhuiParser;
	@Autowired
	private GsxtSichuanParser gsxtSichuanParser;
	@Autowired
	private GsxtQinghaiParser gsxtQinghaiParser;	
	@Autowired
	private GsxtXizangParser gsxtXizangParser;
	@Autowired
	private GsxtGuangxiParser gsxtGuangxiParser;
	@Autowired
	private GsxtHenanParser gsxtHenanParser;
	@Autowired
	private GsxtHubeiParser gsxtHubeiParser;
	@Autowired
	private GsxtNingxiaParser gsxtNingxiaParser;
	@Autowired
	private GsxtLiaoningParser gsxtLiaoningParser;
	@Autowired
	private GsxtHebeiParser gsxtHebeiParser;
	@Autowired
	private GsxtGansuParser gsxtGansuParser;
	@Autowired
	private GsxtShaanxiParser gsxtShaanxiParser;
	@Autowired
	private GsxtHeilongjiangParser gsxtHeilongjiangParser;
	
	@Autowired
	private GsxtXinjiangParser gsxtXinjiangParser;
	
	@Autowired
	private GsxtFujianParser gsxtFujianParser;

	@Autowired
	private GsxtShanghaiParser gsxtShanghaiParser;
	
	@Autowired
	private GsxtHunanParser gsxtHunanParser;
	
	@Autowired
	private GsxtGuizhouParser gsxtGuizhouParser;

	@Autowired
	private GsxtGuangdongParser gsxtGuangdongParser;
	
	@Autowired
	private GsxtHainanParser gsxtHainanParser;
	
	@Autowired
	private GsxtJiangxiParser gsxtJiangxiParser;
	
	@Autowired
	private GsxtYunnanParser gsxtYunnanParser;

	@Autowired
	private GsxtChongqingParser gsxtChongqingParser;

	@Autowired
	private GsxtZhejiangParser gsxtZhejiangParser;
	
	@Autowired
	private GsxtNeimengguParser gsxtNeimengguParser;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GsxtService.class);
	
	
	public Result<Map<String,String>> searchPageHandle(WebParam webParam, boolean isDebug) {
		LOGGER.info("=============GsxtService.searchPageHandle start!=================");
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		Gson gson = new GsonBuilder().create();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_GETSERIALIZEDWEBRESPONSE);
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.searchPageHandle functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		Type mapType = new TypeToken<LinkedHashMap<String,String>>(){}.getType();
		Map<String, String> data = gson.fromJson(result.getHtml(), mapType);
		
		//
		String statusCodeDef = data.get("statusCodeDef");
		String isImageNull = data.get("isImageNull");
		String ip = data.get("ip");
		String hostName = data.get("hostName");
		if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			String exceptionCode = crawlerEngine.getExceptionCode("StatusCodeDef.FREQUENCY_LIMITED"); //访问过于频繁或非正常访问
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(exceptionCode);
			errorMsg.setErrorName("StatusCodeDef.FREQUENCY_LIMITED");
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
			result.setData(null);
			result.debugMode(isDebug);
			return result;
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef) && "true".equals(isImageNull)) {
			ErrorMsg errorMsg = new ErrorMsg();
			String exceptionCode = crawlerEngine.getExceptionCode("StatusCodeDef.IMAGECODE_ERROR"); //验证码错误
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(exceptionCode);
			errorMsg.setErrorName("StatusCodeDef.IMAGECODE_ERROR");
			errorMsg.setErrorMsg("没有获取到验证码图片");
			result.setError(errorMsg);
			result.setData(null);
			result.debugMode(isDebug);
			return result;
		}
		//
		
		result.setData(data);
		result.debugMode(isDebug);
		
		return result;
	}
	
	//
	public Result<Map<String,String>> searchPageHandleOfBeijing(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfBeijing start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!toIndex.dhtml");//("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml");
		webParam.setCodeImageId("//img[@id='MzImgExpPwd']");
		return searchPageHandle(webParam, isDebug);
	}
	
	public Result<Map<String, String>> searchPageHandleOfTianjin(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfTianjin start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://tjcredit.gov.cn/platform/saic/index.ftl");
		webParam.setCodeImageId("//img[@id='vcodeimg']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfHebei(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfHebei start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.hebscztxyxx.gov.cn/notice/");
		webParam.setCodeImageId("//img[@id='cpt-img']");
		webParam.addParam("imagecodeIframeSrc", "http://www.hebscztxyxx.gov.cn/notice/search/popup_captcha");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfShanxi(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfShanxi start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.fc12319.com/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfNeimenggu(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfNeimenggu start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.nmgs.gov.cn:7001/aiccips/");
		webParam.setCodeImageId("//img[@id='vimg']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfJilin(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfJilin start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://211.141.74.198:8081/aiccips/");
		webParam.setCodeImageId("//img[@id='secimg']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfHeilongjiang(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfHeilongjiang start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.hljaic.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfJiangsu(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfJiangsu start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.jsgsj.gov.cn:58888/province/");
		webParam.setCodeImageId("//span[@id='updateVerifyCode1']/img");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfZhejiang(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfZhejiang start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.zjaic.gov.cn/search/doGetAppSearchResult.do");
		webParam.setCodeImageId("//img[@id='kaptchaImg']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfAnhui(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfAnhui start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.ahcredit.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfShandong(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfShandong start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://218.57.139.24/");
		webParam.setCodeImageId("//img[@id='secimg']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfGuangdong(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfGuangdong start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.gdgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='vimg']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfGuangxi(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfGuangxi start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gxqyxygs.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
	    return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfHainan(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfHainan start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://aic.hainan.gov.cn:1888/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfHenan(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfHenan start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://222.143.24.157/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfHubei(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfHubei start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://xyjg.egs.gov.cn/ECPS_HB/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfSichuan(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfSichuan start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.scaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime()); //1459248471141
		webParam.setCodeImageId("//img[@id='img']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfXizang(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfXizang start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.xzaic.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfShaanxi(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfShaanxi start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://xygs.snaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime());
		webParam.setCodeImageId("//img[@id='img']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfGansu(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfGansu start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://xygs.gsaic.gov.cn/gsxygs/main.jsp");
		webParam.setCodeImageId("//img[@id='code_']");
		return searchPageHandle(webParam, isDebug);
	}
	
	public Result<Map<String, String>> searchPageHandleOfNingxia(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfNingxia start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.ngsh.gov.cn/ECPS/index.jsp");
		webParam.setCodeImageId("//img[@id='verificationCode1']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfQinghai(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfQinghai start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://218.95.241.36/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfXinjiang(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfQinghai start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=index&random="+new Date().getTime());
		webParam.setCodeImageId("//img[@id='img']");
		return searchPageHandle(webParam, isDebug);
	}
	
	public Result<Map<String, String>> searchPageHandleOfJiangxi(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfJiangxi start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.jxaic.gov.cn/ECPS/");
		webParam.setCodeImageId("//img[@id='imgYzmSearch']");
		return searchPageHandle(webParam, isDebug);
	}
	
	public Result<Map<String, String>> searchPageHandleOfChongqing(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfChongqing start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.cqgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='auth-code-img']");
		return searchPageHandle(webParam, isDebug);
	}
	
	public Result<Map<String, String>> searchPageHandleOfGuizhou(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfGuizhou start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.gzgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='imgCode']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfLiaoning(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfLiaoning start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/entPublicity/search/searchmain.jsp");
		webParam.setCodeImageId("//img[@id='jcaptcha']");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfShanghai(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfShanghai start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("https://www.sgs.gov.cn/notice/home");
		webParam.setCodeImageId("//img[@id='cpt-img']");
		webParam.addParam("imagecodeIframeSrc", "https://www.sgs.gov.cn/notice/search/popup_captcha");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfFujian(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfFujian start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://wsgs.fjaic.gov.cn/creditpub/home");
		webParam.setCodeImageId("//img[@id='cpt-img']");
		webParam.addParam("imagecodeIframeSrc", "http://wsgs.fjaic.gov.cn/creditpub/search/popup_captcha");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfHunan(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfHunan start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.hnaic.gov.cn/notice/");
		webParam.setCodeImageId("//img[@id='cpt-img']");
		webParam.addParam("imagecodeIframeSrc", "http://gsxt.hnaic.gov.cn/notice/search/popup_captcha");
		return searchPageHandle(webParam, isDebug);
	}

	public Result<Map<String, String>> searchPageHandleOfYunnan(boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.searchPageHandleOfYunnan start!=================");
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.ynaic.gov.cn/notice/");
		webParam.setCodeImageId("//img[@id='cpt-img']");
		webParam.addParam("imagecodeIframeSrc", "http://gsxt.ynaic.gov.cn/notice/search/popup_captcha");
		return searchPageHandle(webParam, isDebug);
	}

	
	public Result<AicFeedJson> getDataOfBeijing(String serializedFileName, String keyword, String verifycode, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getSourceHtmlOfBeijing start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml");
		webParam.setCodeImageId("//img[@id='MzImgExpPwd']");
		
		//webParam.setSearchPage("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "beijing");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkcodeAlt']");
		webParam.addParam("loginBtnXpath", "//li[@class='denglu-an']/a[text()='搜索']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfBeijing functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtBeijingParser.beijingResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfBeijingOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfBeijingOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!toIndex.dhtml");
		webParam.setCodeImageId("//img[@id='MzImgExpPwd']");
		
		webParam.addParam("area", "beijing");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkcodeAlt']");
		webParam.addParam("loginBtnXpath", "//li[@class='denglu-an']/a[text()='搜索']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfBeijing functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtBeijingParser.beijingResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
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
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		} 
		
		result.debugMode(isDebug);
		return result;
	}
	
	//得到解析数据  山西
	public Result<AicFeedJson> getDataOfShanxi(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfShanxi start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "shanxi");//
		webParam.setSearchPage("http://gsxt.fc12319.com/search.jspx");
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfShanxi functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtmls = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtShanxiParser.shanxiResultParser(resultHtmls, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		} 
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfShanxiOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfShanxiOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.fc12319.com/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		
		webParam.addParam("area", "shanxi");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfShanxi functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtShanxiParser.shanxiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
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
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		} 
		
		result.debugMode(isDebug);
		return result;
	}
	
	//得到解析数据  陕西
	public Result<AicFeedJson> getDataOfShaanxi(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfShaanxi start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "shaanxi");//
		webParam.setSearchPage("http://xygs.snaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime());
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfShanxi functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtmls = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtShaanxiParser.shaanxiResultParser(resultHtmls, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfShaanxiOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfShaanxiOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://xygs.snaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime());
		webParam.setCodeImageId("//img[@id='img']");
		
		webParam.addParam("area", "shaanxi");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfShaanxi functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtShaanxiParser.shaanxiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
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
	
	//得到解析数据  上海
	public Result<AicFeedJson> getDataOfShanghai(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfShanghai start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "shanghai");//
		webParam.setSearchPage("https://www.sgs.gov.cn/notice/home");
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfShanxi functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtmls = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtShanghaiParser.shanghaiResultParser(resultHtmls, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(ip);
			errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfShanghaiOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfShanghaiOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("https://www.sgs.gov.cn/notice/home");
		webParam.setCodeImageId("//img[@id='cpt-img']");
		
		webParam.addParam("area", "shanghai");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecodeIframeSrc", "https://www.sgs.gov.cn/notice/search/popup_captcha");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfShanghai functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtShanghaiParser.shanghaiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
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

	//得到解析数据  天津
	public Result<AicFeedJson> getDataOfTianjin(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfTianjin start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "tianjin");//
		webParam.setSearchPage("http://tjcredit.gov.cn/platform/saic/index.ftl");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='queryName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='vcode']");
		webParam.addParam("loginBtnXpath", "//input[@type='submit'][@value='查询']");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfTianjin functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson aicFeedJson =  gsxtTianjinParser.tianjinParser(resultHtml,true);
			result.setData(aicFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	
	//得到解析数据  天津
		public Result<AicFeedJson> getDataOfTianjinOnce(String keyword, boolean isDebug, String logback) {
			LOGGER.info("=============GsxtService.getDataOfHenan start!=================");
			Gson gson = new GsonBuilder().create();
			Result<AicFeedJson> result = new Result<AicFeedJson>();
			
			//
			FunctionCallParam fcm = new FunctionCallParam();
			fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
			
			WebParam webParam = new WebParam();
			webParam.setLogback(logback);
			webParam.setCodeImageId("//img[@id='vcodeimg']");
			webParam.addParam("area", "tianjin");//
			webParam.setSearchPage("http://tjcredit.gov.cn/platform/saic/index.ftl");
			webParam.addParam("keyword", keyword);
			webParam.addParam("keywordXpath", "//input[@id='queryName']");
			webParam.addParam("imagecodeXpath", "//input[@id='vcode']");
			webParam.addParam("loginBtnXpath", "//input[@type='submit'][@value='查询']");
			
			fcm.setWebEngineParam(webParam);
			String param = gson.toJson(fcm);
			LOGGER.info("The GsxtService.getDataOfHenan functionCallParam is: {}", param);
			//
			
			result = crawlerEngine.execute(param, result);
			if (result.getError()!=null) {
				return result;
			}
			
			String resultHtml = result.getHtml();
			Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
			String imagecode = (String) resultHtmlMap.get("imagecode");
			String imageUrl = (String) resultHtmlMap.get("imageUrl");
			String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
			String ip = (String) resultHtmlMap.get("ip");
			String hostName = (String) resultHtmlMap.get("hostName");
			if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
				AicFeedJson gsxtFeedJson = gsxtTianjinParser.tianjinParser(resultHtml,isDebug);
				result.setData(gsxtFeedJson);
			} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
				String isImageNull = (String) resultHtmlMap.get("isImageNull");
				String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
				searchPageHtml = gson.toJson(searchPageHtml);
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
				if ("true".equals(isImageNull)) {
					errorMsg.setErrorName("没有获取到验证码图片");
					errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
				} else {
					errorMsg.setErrorName("验证码识别错误 ");
					errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				}
				result.setError(errorMsg);
			} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
				errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("没检索到关键字 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FAILURE);
				errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("请求数据失败 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				result.setError(errorMsg);
			}
			
			result.debugMode(isDebug);
			
			return result;
		}
	
	//得到解析数据  广西
	public Result<AicFeedJson> getDataOfGuangxi(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfGuangxi start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "guangxi");//
		webParam.setSearchPage("http://gxqyxygs.gov.cn/search.jspx");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='zmdid']/div[@id='woaicss_con1']/ul/li[@class='denglu-an']/a");
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfGuangxi functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson aicFeedJson = gsxtGuangxiParser.guangxiParser(resultHtml,true);
			result.setData(aicFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	
	//得到解析数据  广西
		public Result<AicFeedJson> getDataOfGuangxiOnce(String keyword, boolean isDebug, String logback) {
			LOGGER.info("=============GsxtService.getDataOfHenan start!=================");
			Gson gson = new GsonBuilder().create();
			Result<AicFeedJson> result = new Result<AicFeedJson>();
			
			//
			FunctionCallParam fcm = new FunctionCallParam();
			fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
			
			WebParam webParam = new WebParam();
			webParam.setLogback(logback);
			webParam.setCodeImageId("//img[@id='valCode']");
			webParam.addParam("area", "guangxi");//
			webParam.setSearchPage("http://gxqyxygs.gov.cn/search.jspx");
			webParam.addParam("keyword", keyword);
			webParam.addParam("keywordXpath", "//input[@id='entName']");
			webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
			webParam.addParam("loginBtnXpath", "//div[@id='zmdid']/div[@id='woaicss_con1']/ul/li[@class='denglu-an']/a");
			
			fcm.setWebEngineParam(webParam);
			String param = gson.toJson(fcm);
			LOGGER.info("The GsxtService.getDataOfHenan functionCallParam is: {}", param);
			//
			
			result = crawlerEngine.execute(param, result);
			if (result.getError()!=null) {
				return result;
			}
			
			String resultHtml = result.getHtml();
			Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
			String imagecode = (String) resultHtmlMap.get("imagecode");
			String imageUrl = (String) resultHtmlMap.get("imageUrl");
			String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
			String ip = (String) resultHtmlMap.get("ip");
			String hostName = (String) resultHtmlMap.get("hostName");
			if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
				AicFeedJson gsxtFeedJson = gsxtGuangxiParser.guangxiParser(resultHtml,isDebug);
				result.setData(gsxtFeedJson);
			} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
				String isImageNull = (String) resultHtmlMap.get("isImageNull");
				String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
				searchPageHtml = gson.toJson(searchPageHtml);
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
				if ("true".equals(isImageNull)) {
					errorMsg.setErrorName("没有获取到验证码图片");
					errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
				} else {
					errorMsg.setErrorName("验证码识别错误 ");
					errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				}
				result.setError(errorMsg);
			} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
				errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("没检索到关键字 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FAILURE);
				errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("请求数据失败 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				result.setError(errorMsg);
			}
			
			result.debugMode(isDebug);
			
			return result;
		}

	//得到解析数据  河南
	public Result<AicFeedJson> getDataOfHenan(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHenan start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "henan");//
		webParam.setSearchPage("http://222.143.24.157/search.jspx");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='zmdid']/div[@id='woaicss_con1']/ul/li[@class='denglu-an']/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfHenan functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson aicFeedJson = gsxtHenanParser.henanParser(resultHtml,true);
			result.setData(aicFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	//得到解析数据  河南
			public Result<AicFeedJson> getDataOfHenanOnce(String keyword, boolean isDebug, String logback) {
				LOGGER.info("=============GsxtService.getDataOfHenan start!=================");
				Gson gson = new GsonBuilder().create();
				Result<AicFeedJson> result = new Result<AicFeedJson>();
				
				//
				FunctionCallParam fcm = new FunctionCallParam();
				fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
				
				WebParam webParam = new WebParam();
				webParam.setLogback(logback);
				webParam.setCodeImageId("//img[@id='valCode']");
				webParam.addParam("area", "henan");//
				webParam.setSearchPage("http://222.143.24.157/search.jspx");
				webParam.addParam("keyword", keyword);
				webParam.addParam("keywordXpath", "//input[@id='entName']");
				webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
				webParam.addParam("loginBtnXpath", "//div[@id='zmdid']/div[@id='woaicss_con1']/ul/li[@class='denglu-an']/a");
				
				fcm.setWebEngineParam(webParam);
				String param = gson.toJson(fcm);
				LOGGER.info("The GsxtService.getDataOfHenan functionCallParam is: {}", param);
				//
				
				result = crawlerEngine.execute(param, result);
				if (result.getError()!=null) {
					return result;
				}
				
				String resultHtml = result.getHtml();
				Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
				String imagecode = (String) resultHtmlMap.get("imagecode");
				String imageUrl = (String) resultHtmlMap.get("imageUrl");
				String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
				String ip = (String) resultHtmlMap.get("ip");
				String hostName = (String) resultHtmlMap.get("hostName");
				if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {//gsxtJiangsuParser.getJiangsuResultObj(resultHtml, isDebug)
					AicFeedJson gsxtFeedJson =gsxtHenanParser.henanParser(resultHtml,isDebug);//gsxtHenanParser.henanParser(resultHtml,isDebug);
					result.setData(gsxtFeedJson);
				} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
					String isImageNull = (String) resultHtmlMap.get("isImageNull");
					String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
					searchPageHtml = gson.toJson(searchPageHtml);
					ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
					errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
					if ("true".equals(isImageNull)) {
						errorMsg.setErrorName("没有获取到验证码图片");
						errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
					} else {
						errorMsg.setErrorName("验证码识别错误 ");
						errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
					}
					result.setError(errorMsg);
				} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
					ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
					errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
					errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
					errorMsg.setErrorName("没检索到关键字 ");
					result.setError(errorMsg);
				} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
					ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
					errorMsg.setErrorCode(StatusCodeDef.FAILURE);
					errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
					errorMsg.setErrorName("请求数据失败 ");
					result.setError(errorMsg);
				} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
					ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
					errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
					errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
					errorMsg.setErrorName("可能访问过于频繁或非正常访问");
					result.setError(errorMsg);
				}
				
				result.debugMode(isDebug);
				
				return result;
			}
			
			//内蒙古
			public Result<AicFeedJson> getDataOfNeimengguOnce(String keyword, boolean isDebug, String logback) {
				LOGGER.info("=============GsxtService.getDataOfHunanOnce start!=================");
				Gson gson = new GsonBuilder().create();
				Result<AicFeedJson> result = new Result<AicFeedJson>();
				
				FunctionCallParam fcm = new FunctionCallParam();
				fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
				WebParam webParam = new WebParam();
				
				webParam.setLogback(logback);
				webParam.setCodeImageId("//img[@id='vimg']");
				webParam.addParam("keyword", keyword);
				webParam.setSearchPage("http://www.nmgs.gov.cn:7001/aiccips/");
				webParam.addParam("area", "neimenggu");//
				webParam.addParam("keywordXpath", "//input[@id='textfield']");
				webParam.addParam("imagecodeXpath", "//input[@id='code']");
				webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
				
				fcm.setWebEngineParam(webParam);
				
				
				String param = gson.toJson(fcm);
				LOGGER.info("The GsxtService.getSourceHtmlOfhunan functionCallParam is: {}", param);
				
				result = crawlerEngine.execute(param, result);
				if (result.getError()!=null) {
					return result;
				}
				String resultHtml = result.getHtml();
				
				Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
				String imagecode = (String) resultHtmlMap.get("imagecode");
				String imageUrl = (String) resultHtmlMap.get("imageUrl");
				String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
				String ip = (String) resultHtmlMap.get("ip");
				String hostName = (String) resultHtmlMap.get("hostName");
				if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
					AicFeedJson aicFeedJson =  gsxtNeimengguParser.neimengguParser(resultHtml,isDebug);
					result.setData(aicFeedJson);
				} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
					String isImageNull = (String) resultHtmlMap.get("isImageNull");
					String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
					searchPageHtml = gson.toJson(searchPageHtml);
					
					ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
					errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
					if ("true".equals(isImageNull)) {
						errorMsg.setErrorName("没有获取到验证码图片");
						errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
					} else {
						errorMsg.setErrorName("验证码识别错误 ");
						errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
					}
					result.setError(errorMsg);
				} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
					ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
					errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
					errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
					errorMsg.setErrorName("没检索到关键字 ");
					result.setError(errorMsg);
				} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
					ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
					errorMsg.setErrorCode(StatusCodeDef.FAILURE);
					errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
					errorMsg.setErrorName("请求数据失败 ");
					result.setError(errorMsg);
				} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
					ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
					errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
					errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
					errorMsg.setErrorName("可能访问过于频繁或非正常访问");
					result.setError(errorMsg);
				}
				
				result.debugMode(isDebug);
				return result;
			}

	//得到解析数据  湖北
	public Result<AicFeedJson> getDataOfHubei(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHubei start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "hubei");//
		webParam.setSearchPage("http://xyjg.egs.gov.cn/ECPS_HB/search.jspx");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNo']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfHubei functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}

		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson aicFeedJson = gsxtHubeiParser.hubeiParser(resultHtml,true);
			result.setData(aicFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	//得到解析数据  湖北
		public Result<AicFeedJson> getDataOfHubeiOnce(String keyword, boolean isDebug, String logback) {
			LOGGER.info("=============GsxtService.getDataOfHubeiOnce start!=================");
			Gson gson = new GsonBuilder().create();
			Result<AicFeedJson> result = new Result<AicFeedJson>();
			
			//
			FunctionCallParam fcm = new FunctionCallParam();
			fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
			
			WebParam webParam = new WebParam();
			webParam.setLogback(logback);
			webParam.setCodeImageId("//img[@id='valCode']");
			webParam.addParam("area", "hubei");//
			webParam.setSearchPage("http://xyjg.egs.gov.cn/ECPS_HB/search.jspx");
			webParam.addParam("keyword", keyword);
			webParam.addParam("keywordXpath", "//input[@id='entName']");
			webParam.addParam("imagecodeXpath", "//input[@id='checkNo']");
			webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
			
			fcm.setWebEngineParam(webParam);
			String param = gson.toJson(fcm);
			LOGGER.info("The GsxtService.getDataOfHenan functionCallParam is: {}", param);
			//
			
			result = crawlerEngine.execute(param, result);
			if (result.getError()!=null) {
				return result;
			}
			
			String resultHtml = result.getHtml();
			Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
			String imagecode = (String) resultHtmlMap.get("imagecode");
			String imageUrl = (String) resultHtmlMap.get("imageUrl");
			String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
			String ip = (String) resultHtmlMap.get("ip");
			String hostName = (String) resultHtmlMap.get("hostName");
			if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
				AicFeedJson gsxtFeedJson = gsxtHubeiParser.hubeiParser(resultHtml,isDebug);
				result.setData(gsxtFeedJson);
			} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
				String isImageNull = (String) resultHtmlMap.get("isImageNull");
				String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
				searchPageHtml = gson.toJson(searchPageHtml);
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
				if ("true".equals(isImageNull)) {
					errorMsg.setErrorName("没有获取到验证码图片");
					errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
				} else {
					errorMsg.setErrorName("验证码识别错误 ");
					errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				}
				result.setError(errorMsg);
			} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
				errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("没检索到关键字 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FAILURE);
				errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("请求数据失败 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				result.setError(errorMsg);
			}
			
			result.debugMode(isDebug);
			
			return result;
		}
		
		

		//得到解析数据  云南
		public Result<AicFeedJson> getDataOfYunnanOnce(String keyword, boolean isDebug, String logback) {
			LOGGER.info("=============GsxtService.getDataOfHenan start!=================");
			Gson gson = new GsonBuilder().create();
			Result<AicFeedJson> result = new Result<AicFeedJson>();
			
			//
			FunctionCallParam fcm = new FunctionCallParam();
			fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
			
			WebParam webParam = new WebParam();
			webParam.setLogback(logback);
			webParam.setCodeImageId("//img[@id='cpt-img']");
			webParam.addParam("imagecodeIframeSrc", "http://gsxt.ynaic.gov.cn/notice/search/popup_captcha");
			webParam.addParam("area", "yunnan");//
			webParam.setSearchPage("http://gsxt.ynaic.gov.cn/notice/");
			webParam.addParam("keyword", keyword);	
			webParam.addParam("keywordXpath", "//input[@id='keyword']");
			webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
			webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
			
			fcm.setWebEngineParam(webParam);
			String param = gson.toJson(fcm);
			LOGGER.info("The GsxtService.getDataOfHenan functionCallParam is: {}", param);
			//
			
			result = crawlerEngine.execute(param, result);
			if (result.getError()!=null) {
				return result;
			}
			
			String resultHtml = result.getHtml();
			Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
			String imagecode = (String) resultHtmlMap.get("imagecode");
			String imageUrl = (String) resultHtmlMap.get("imageUrl");
			String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
			String ip = (String) resultHtmlMap.get("ip");
			String hostName = (String) resultHtmlMap.get("hostName");
			if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
				AicFeedJson gsxtFeedJson = gsxtYunnanParser.yunnanParser(resultHtml, isDebug);
				result.setData(gsxtFeedJson);
			} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
				String isImageNull = (String) resultHtmlMap.get("isImageNull");
				String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
				searchPageHtml = gson.toJson(searchPageHtml);
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
				if ("true".equals(isImageNull)) {
					errorMsg.setErrorName("没有获取到验证码图片");
					errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
				} else {
					errorMsg.setErrorName("验证码识别错误 ");
					errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				}
				result.setError(errorMsg);
			} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
				errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("没检索到关键字 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FAILURE);
				errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("请求数据失败 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				result.setError(errorMsg);
			}
			
			result.debugMode(isDebug);
			
			return result;
		}

	//得到数据 江苏
	public Result<AicFeedJson> getDataOfJiangsu(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfJiangsu start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "jiangsu");//
		webParam.setSearchPage("http://www.jsgsj.gov.cn:58888/province/");
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='name']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='verifyCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfJiangsu functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
        String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtJiangsuParser.getJiangsuResultObj(resultHtml,isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("该IP在一天内超过了查询的限定次数，限制其访问3天！");
			errorMsg.setErrorName("请求过于频繁");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	//得到数据 吉林
	public Result<AicFeedJson> getDataOfJilin(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfJilin start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "jilin");//
		webParam.setSearchPage("http://211.141.74.198:8081/aiccips/");
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='searchtxt']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='yzminput']");
		webParam.addParam("loginBtnXpath", "//a[@id='searchbtn']");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfJilin functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
        String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtJilinParser.getJilinResultObj(resultHtml,isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("该IP在一天内超过了查询的限定次数，限制其访问3天！");
			errorMsg.setErrorName("请求过于频繁");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	//得到数据 山东
	public Result<AicFeedJson> getDataOfShandong(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfShandong start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "shandong");//
		webParam.setSearchPage("http://218.57.139.24/");
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='searchtxt']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='yzminput']");
		webParam.addParam("loginBtnXpath", "//a[@id='searchbtn']");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfShandong functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtmls = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtShandongParser.shandongResultParser(resultHtmls, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfShandongOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfShandongOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://218.57.139.24/");
		webParam.setCodeImageId("//img[@id='secimg']");
		
		webParam.addParam("area", "shandong");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='searchtxt']");
		webParam.addParam("imagecodeXpath", "//input[@id='yzminput']");
		webParam.addParam("loginBtnXpath", "//a[@id='searchbtn']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfshangdong functionCallParam is: {}", param);
		


		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtShandongParser.shandongResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
	
	//得到数据 安徽
	public Result<AicFeedJson> getDataOfAnhui(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfAnhui start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.ahcredit.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "anhui");//
	//	webParam.setSearchPage("http://www.ahcredit.gov.cn/search.jspx");
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfAnhui functionCallParam is: {}", param);
		//
		
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();	
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtAnhuiParser.anhuiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		} 
		
		result.debugMode(isDebug);
		
		return result;		
	}
	
	
	public Result<AicFeedJson> getDataOfAnhuiOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfAnhuiOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.ahcredit.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");

		webParam.addParam("area", "anhui");//
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfAnhuiOnce functionCallParam is: {}", param);
		//	
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();	
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtAnhuiParser.anhuiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		} 

		return result;		
	}
	
	//得到数据 四川
	public Result<AicFeedJson> getDataOfSichuan(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfSichuan start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.scaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime()); //1459248471141
		webParam.setCodeImageId("//img[@id='img']");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "sichuan");//
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfSichuan functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
        String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtSichuanParser.sichuanResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}

 public Result<AicFeedJson> getDataOfSichuanOnce(String keyword, boolean isDebug, String logback) {
		
		LOGGER.info("=============GsxtService.getDataOfSichuanOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.scaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime()); //1459248471141
		webParam.setCodeImageId("//img[@id='img']");
		
		webParam.addParam("area", "sichuan");//	
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entname']");	
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfSichuan functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
        String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtSichuanParser.sichuanResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;	
		
	}	
	
	//得到数据 青海
	public Result<AicFeedJson> getDataOfQinghai(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfQinghai start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setCodeImageId("//img[@id='valCode']");
		webParam.setSearchPage("http://218.95.241.36/search.jspx");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "qinghai");//

		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");			
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfQinghai functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
	    String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtQinghaiParser.qinghaiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符，请查证！");
			errorMsg.setErrorName("查询条件中含有非法字符，请查证！");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);			
		return result;
		
	}
	
	
	public Result<AicFeedJson> getDataOfQinghaiOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfQinghai start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://218.95.241.36/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		
		webParam.addParam("area", "qinghai");//
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");			
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfQinghai functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
	    String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtQinghaiParser.qinghaiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}	
		result.debugMode(isDebug);			
		return result;
		
	}	
	
	//得到数据 西藏
	public Result<AicFeedJson> getDataOfXizang(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfXizang start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.xzaic.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "xizang");//
	
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfXizang functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		 String resultHtml = result.getHtml();
			
			Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
			String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
			String ip = (String) resultHtmlMap.get("ip");
			String hostName = (String) resultHtmlMap.get("hostName");
			if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
				AicFeedJson gsxtFeedJson = gsxtXizangParser.xizangResultParser(resultHtml, isDebug);
				result.setData(gsxtFeedJson);
			} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
				errorMsg.setErrorMsg("验证码错误");
				errorMsg.setErrorName("验证码错误");
				result.setError(errorMsg);
			} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
				errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
				errorMsg.setErrorName("没检索到关键字");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FAILURE);
				errorMsg.setErrorMsg("请求数据失败,稍后再试");
				errorMsg.setErrorName("请求数据失败");
				result.setError(errorMsg);
			} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
				errorMsg.setErrorMsg("查询条件中含有非法字符，请查证！");
				errorMsg.setErrorName("查询条件中含有非法字符，请查证！");
				result.setError(errorMsg);
			}
			result.debugMode(isDebug);			
			return result;
	}
	
	public Result<AicFeedJson> getDataOfXizangOnce( String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfXizang start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.xzaic.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");

		webParam.addParam("area", "xizang");//
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfXizang functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		   String resultHtml = result.getHtml();
			
			Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 			
			String imagecode = (String) resultHtmlMap.get("imagecode");
			String imageUrl = (String) resultHtmlMap.get("imageUrl");
			String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
			String ip = (String) resultHtmlMap.get("ip");
			String hostName = (String) resultHtmlMap.get("hostName");
			if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
				AicFeedJson gsxtFeedJson = gsxtXizangParser.xizangResultParser(resultHtml, isDebug);
				result.setData(gsxtFeedJson);
			} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
				String isImageNull = (String) resultHtmlMap.get("isImageNull");
				String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
				searchPageHtml = gson.toJson(searchPageHtml);
				
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
				if ("true".equals(isImageNull)) {
					errorMsg.setErrorName("没有获取到验证码图片");
					errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
				} else {
					errorMsg.setErrorName("验证码识别错误 ");
					errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				}
				result.setError(errorMsg);
			} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
				errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("没检索到关键字 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FAILURE);
				errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("请求数据失败 ");
				result.setError(errorMsg);
			} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
				errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("可能访问过于频繁或非正常访问");
				result.setError(errorMsg);
			} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
				ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
				errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
				errorMsg.setErrorMsg("查询条件中含有非法字符，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
				errorMsg.setErrorName("查询条件中含有非法字符");
				result.setError(errorMsg);
			}
			result.debugMode(isDebug);			
			return result;
	}
	public Result<AicFeedJson> getDataOfNingxia(String serializedFileName, String keyword, String verifycode, boolean isDebug) {
		LOGGER.info("=============GsxtService.getDataOfNingxia start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setSearchPage("http://gsxt.ngsh.gov.cn/ECPS/index.jsp");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "ningxia");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='selectValue']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='_password']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfNingxia functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtNingxiaParser.getNingxiaResultObj(resultHtml,isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfLiaoning(String serializedFileName, String keyword, String verifycode, boolean isDebug) {
		LOGGER.info("=============GsxtService.getDataOfLiaoning start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setSearchPage("http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/entPublicity/search/searchmain.jsp");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "liaoning");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='solrCondition']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='authCode-test']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[3]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfLiaoning functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtLiaoningParser.getLiaoningResultObj(resultHtml,isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfGansu(String serializedFileName, String keyword, String verifycode, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfGansu start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://xygs.gsaic.gov.cn/gsxygs/main.jsp");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "gansu");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='text_query']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='text_code']");
		webParam.addParam("loginBtnXpath", "//a[@id='btn_search']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfGansu functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtGansuParser.getGansuResultObj(resultHtml,isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}  else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	/**
	 * 河北
	 * @param serializedFileName
	 * @param keyword
	 * @param verifycode
	 * @param isDebug
	 * @param logback
	 * @return
	 */
	public Result<AicFeedJson> getDataOfHebei(String serializedFileName, String keyword, String verifycode, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHebei start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.hebscztxyxx.gov.cn/notice/");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "hebei");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfHebei functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtHebeiParser.getHebeiResultObj(resultHtml,isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		} 
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	//得到数据 福建
	public Result<AicFeedJson> getDataOfFujian(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfFujianstart!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "fujian");//
		webParam.setSearchPage("http://wsgs.fjaic.gov.cn/creditpub/home");
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[4]/a");
					
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfSichuan functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtmls = result.getHtml();
				
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtFujianParser.getFujianResultObj(resultHtmls,true);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfFujianOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfFujianOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://wsgs.fjaic.gov.cn/creditpub/home");
		webParam.setCodeImageId("//img[@id='cpt-img']");

		
		webParam.addParam("area", "fujian");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[4]/a");
		fcm.setWebEngineParam(webParam);

		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOffujiang functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtFujianParser.getFujianResultObj(resultHtml,true);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
			
	//得到数据 新疆
	public Result<AicFeedJson> getDataOfXinjiang(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfXinjiang start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "xinjiang");//
		webParam.setSearchPage("http://gsxt.scaic.gov.cn/ztxy.do?method=index&random="+new Date().getTime());
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
					
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfSichuan functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtmls = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtXinjiangParser.xinjiangResultParser(resultHtmls, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfXinjiangOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfXinjiangOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=index&random="+new Date().getTime());
		webParam.setCodeImageId("//img[@id='img']");
		
		webParam.addParam("area", "xinjiang");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entname']");
		webParam.addParam("imagecodeXpath", "//input[@id='yzm']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);							
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfXinjiang functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtXinjiangParser.xinjiangResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
	
			
	//黑龙江数据
	public Result<AicFeedJson> getDataOfHeilongjiang(String serializedFileName, String keyword, String verifycode, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHeilongjiang start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.hljaic.gov.cn/search.jspx");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "heilongjiang");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
		
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfHeilongjiang functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtHeilongjiangParser.heilongjiangResultParser(resultHtml,isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfHeilongjiangOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHeilongjiangOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.hljaic.gov.cn/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		
		webParam.addParam("area", "heilongjiang");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
		
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfheilongjiang functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtHeilongjiangParser.heilongjiangResultParser(resultHtml,isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
	
	//得到解析数据  湖南
	public Result<AicFeedJson> getDataOfHunan(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHunan start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);

		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "hunan");//
		webParam.setSearchPage("http://gsxt.hnaic.gov.cn/notice/");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfhunan functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson aicFeedJson =  gsxtHunanParser.hunanParser(resultHtml,isDebug);
			result.setData(aicFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfHunanOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHunanOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.hnaic.gov.cn/notice/");
		webParam.setCodeImageId("//img[@id='cpt-img']");

		
		webParam.addParam("area", "hunan");
		webParam.addParam("keyword", keyword);
        webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		fcm.setWebEngineParam(webParam);
		
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfhunan functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson aicFeedJson =  gsxtHunanParser.hunanParser(resultHtml,isDebug);
			result.setData(aicFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
	

	//得到数据 贵州
	public Result<AicFeedJson> getDataOfGuizhou(String serializedFileName, String verifycode, String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfGuizhou start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.gzgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='imgCode']");
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "guizhou");//
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='q']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='validCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='codeWindow']/div/ul/li[4]/a/img");			
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfGuizhou functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
	    String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtGuizhouParser.getGuizhouResultObj(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ");
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);			
		return result;
		
	}
	
	public Result<AicFeedJson> getDataOfGuizhouOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfGuizhouOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		//
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.gzgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='imgCode']");
		webParam.addParam("area", "guizhou");//
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='q']");
		webParam.addParam("imagecodeXpath", "//input[@id='validCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='codeWindow']/div/ul/li[4]/a/img");			
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfGuizhouOnce functionCallParam is: {}", param);
		//
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
	    String resultHtml = result.getHtml();
		
	    Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtGuizhouParser.getGuizhouResultObj(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);			
		return result;		
	}
	
	public Result<AicFeedJson> getDataOfGuangdongOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfGaungdongOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.gdgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='vimg']");
		
		webParam.addParam("area", "guangdong");//
		webParam.addParam("keyword", keyword);	
		webParam.addParam("keywordXpath", "//input[@id='textfield']");
		webParam.addParam("imagecodeXpath", "//input[@id='code']");
		webParam.addParam("loginBtnXpath", "//a[@id='checkBtn']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getDataOfGuangdongOnce functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtGuangdongParser.guangdongResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
	
	// 江苏工商网
	public Result<AicFeedJson> getDataOfJiangsuOnce(String keyword, boolean isDebug, String logback) {
		
		LOGGER.info("=============GsxtService.getDataOfJiangsuOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.jsgsj.gov.cn:58888/province/");
		webParam.setCodeImageId("//span[@id='updateVerifyCode1']/img");
		webParam.addParam("area", "jiangsu");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='name']");
		webParam.addParam("imagecodeXpath", "//input[@id='verifyCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfJiangsu functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError() != null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtJiangsuParser.getJiangsuResultObj(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		
		return result;
		
	}
	
	// 吉林工商网  OCR
	public Result<AicFeedJson> getDataOfJilinOnce(String keyword, boolean isDebug, String logback) {
		
		LOGGER.info("=============GsxtService.getDataOfJilinOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://211.141.74.198:8081/aiccips/");
		webParam.setCodeImageId("//img[@id='secimg']");
		webParam.addParam("area", "jilin");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='searchtxt']");
		webParam.addParam("imagecodeXpath", "//input[@id='yzminput']");
		webParam.addParam("loginBtnXpath", "//a[@id='searchbtn']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfJilin functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError() != null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtJilinParser.getJilinResultObj(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		
		return result;
		
	}
	
	public Result<AicFeedJson> getDataOfNingxiaOnce(String keyword, boolean isDebug, String logback) {
		
		LOGGER.info("=============GsxtService.getDataOfNingxiaOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.ngsh.gov.cn/ECPS/index.jsp");
		webParam.setCodeImageId("//img[@id='verificationCode1']");
		webParam.addParam("area", "ningxia");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='selectValue']");
		webParam.addParam("imagecodeXpath", "//input[@id='_password']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfNingxia functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError() != null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtNingxiaParser.getNingxiaResultObj(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		
		return result;
		
	}
	
	/**
	 * 河北工商网    ocr
	 * @param keyword
	 * @param isDebug
	 * @param logback
	 * @return
	 */
	public Result<AicFeedJson> getDataOfHebeiOnce(String keyword, boolean isDebug, String logback) {
		
		LOGGER.info("=============GsxtService.getDataOfHebeiOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://www.hebscztxyxx.gov.cn/notice/");
		webParam.setCodeImageId("//img[@id='cpt-img']");
		webParam.addParam("area", "hebei");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='keyword']");
		webParam.addParam("imagecodeXpath", "//input[@id='cpt-input']");
		webParam.addParam("loginBtnXpath", "//div[@id='captcha']/div[2]/a");
		webParam.addParam("imagecodeIframeSrc", "http://www.hebscztxyxx.gov.cn/notice/search/popup_captcha");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfHebei functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError() != null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtHebeiParser.getHebeiResultObj(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		
		return result;
		
	}
	
	/**
	 * 辽宁工商网
	 * @param keyword
	 * @param isDebug
	 * @param logback
	 * @return
	 */
	public Result<AicFeedJson> getDataOfLiaoningOnce(String keyword, boolean isDebug, String logback) {
		
		LOGGER.info("=============GsxtService.getDataOfLiaoningOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/entPublicity/search/searchmain.jsp");
		webParam.setCodeImageId("//img[@id='jcaptcha']");
		webParam.addParam("area", "liaoning");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='solrCondition']");
		webParam.addParam("imagecodeXpath", "//input[@id='authCode-test']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[3]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfLiaoning functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError() != null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtLiaoningParser.getLiaoningResultObj(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		
		return result;
		
	}
	
	public Result<AicFeedJson> getDataOfGansuOnce(String keyword, boolean isDebug, String logback) {
		
		LOGGER.info("=============GsxtService.getDataOfGansuOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://xygs.gsaic.gov.cn/gsxygs/main.jsp");
		webParam.setCodeImageId("//img[@id='code_']");
		webParam.addParam("area", "gansu");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='text_query']");
		webParam.addParam("imagecodeXpath", "//input[@id='text_code']");
		webParam.addParam("loginBtnXpath", "//a[@id='btn_search']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfGansu functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError() != null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtGansuParser.getGansuResultObj(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		} else if (StatusCodeDef.ILLEGAL_CHAR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_CHAR);
			errorMsg.setErrorMsg("查询条件中含有非法字符，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("查询条件中含有非法字符");
			result.setError(errorMsg);
		}
		result.debugMode(isDebug);
		
		return result;
		
	}
	
	public Result<AicFeedJson> getDataOfHainan(String serializedFileName, String keyword, String verifycode, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHainan start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://aic.hainan.gov.cn:1888/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");
		
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "hainan");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
				
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfhainan functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtHainanParser.hainanResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	
	
	
	public Result<AicFeedJson> getDataOfHainanOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfHainanOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://aic.hainan.gov.cn:1888/search.jspx");
		webParam.setCodeImageId("//img[@id='valCode']");		
		
		webParam.addParam("area", "hainan");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='entName']");
		webParam.addParam("imagecodeXpath", "//input[@id='checkNoShow']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfHainan functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtHainanParser.hainanResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}
	
    //得到江西数据
	public Result<AicFeedJson> getDataOfJiangxi(String serializedFileName, String keyword, String verifycode, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getSourceHtmlOfJiangxi start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.jxaic.gov.cn/ECPS/");
		webParam.setCodeImageId("//img[@id='imgYzmSearch']");
		
		
		webParam.setSerializedFileName(serializedFileName);
		webParam.addParam("area", "jiangxi");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='search']");
		webParam.addParam("imagecode", verifycode);
		webParam.addParam("imagecodeXpath", "//input[@id='yzmSearch']");
		webParam.addParam("loginBtnXpath", "//a[@id='butSearch']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfJiangxi functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtJiangxiParser.jiangxiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			errorMsg.setErrorMsg("验证码错误");
			errorMsg.setErrorName("验证码错误");
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果");
			errorMsg.setErrorName("没检索到关键字");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试");
			errorMsg.setErrorName("请求数据失败");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		
		return result;
	}
	
	public Result<AicFeedJson> getDataOfJiangxiOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfJiangxiOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();
		
		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.jxaic.gov.cn/ECPS/");
		webParam.setCodeImageId("//img[@id='imgYzmSearch']");
		
		webParam.addParam("area", "jiangxi");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='search']");
		webParam.addParam("imagecodeXpath", "//input[@id='yzmSearch']");
		webParam.addParam("loginBtnXpath", "//a[@id='butSearch']");
		fcm.setWebEngineParam(webParam);
		
		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfJiangxi functionCallParam is: {}", param);
		
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();
		
		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType()); 
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtJiangxiParser.jiangxiResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);
			
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}
		
		result.debugMode(isDebug);
		return result;
	}


	public Result<AicFeedJson> getDataOfChongqingOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfChongqingOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();

		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.cqgs.gov.cn/");
		webParam.setCodeImageId("//img[@id='auth-code-img']");

		webParam.addParam("area", "chongqing");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='search-keyword']");
		webParam.addParam("imagecodeXpath", "//input[@id='auth-code']");
		webParam.addParam("loginBtnXpath", "//a[@id='search-button']");
		fcm.setWebEngineParam(webParam);

		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfChongqing functionCallParam is: {}", param);

		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();

		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType());
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			AicFeedJson gsxtFeedJson = gsxtChongqingParser.chongqingResultParser(resultHtml, isDebug);
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);

			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}

		result.debugMode(isDebug);
		return result;
	}

	public Result<AicFeedJson> getDataOfZhejiangOnce(String keyword, boolean isDebug, String logback) {
		LOGGER.info("=============GsxtService.getDataOfZhejiangOnce start!=================");
		Gson gson = new GsonBuilder().create();
		Result<AicFeedJson> result = new Result<AicFeedJson>();

		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.GSXT_SEARCH_WITH_OCR);
		WebParam webParam = new WebParam();

		webParam.setLogback(logback);
		webParam.setSearchPage("http://gsxt.zjaic.gov.cn/search/doGetAppSearchResult.do");
		webParam.setCodeImageId("//img[@id='kaptchaImg']");

		webParam.addParam("area", "zhejiang");
		webParam.addParam("keyword", keyword);
		webParam.addParam("keywordXpath", "//input[@id='name']");
		webParam.addParam("imagecodeXpath", "//input[@id='verifyCode']");
		webParam.addParam("loginBtnXpath", "//div[@id='woaicss_con1']/ul/li[4]/a");
		fcm.setWebEngineParam(webParam);

		String param = gson.toJson(fcm);
		LOGGER.info("The GsxtService.getSourceHtmlOfZhejiang functionCallParam is: {}", param);

		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		String resultHtml = result.getHtml();

		Map<String, Object> resultHtmlMap = gson.fromJson(resultHtml, new TypeToken<Map<String, Object>>(){}.getType());
		String imagecode = (String) resultHtmlMap.get("imagecode");
		String imageUrl = (String) resultHtmlMap.get("imageUrl");
		String statusCodeDef = (String) resultHtmlMap.get("statusCodeDef");
		String ip = (String) resultHtmlMap.get("ip");
		String hostName = (String) resultHtmlMap.get("hostName");
		if (StatusCodeDef.SCCCESS.equals(statusCodeDef)) {
			long startTime = System.currentTimeMillis();
			System.out.println("==start==:" + startTime + "ms]");
			AicFeedJson gsxtFeedJson = gsxtZhejiangParser.zhejiangResultParser(resultHtml, isDebug);
			System.out.println("==zhejiangResultParser==:"  + "[用时：" +  (System.currentTimeMillis()-startTime) + "ms]");
			result.setData(gsxtFeedJson);
		} else if (StatusCodeDef.IMAGECODE_ERROR.equals(statusCodeDef)) {
			String isImageNull = (String) resultHtmlMap.get("isImageNull");
			String searchPageHtml = (String) resultHtmlMap.get("searchPageHtml");
			searchPageHtml = gson.toJson(searchPageHtml);

			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.IMAGECODE_ERROR);
			if ("true".equals(isImageNull)) {
				errorMsg.setErrorName("没有获取到验证码图片");
				errorMsg.setErrorMsg("没有获取到验证码图片: " + searchPageHtml);
			} else {
				errorMsg.setErrorName("验证码识别错误 ");
				errorMsg.setErrorMsg("验证码错误，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			}
			result.setError(errorMsg);
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("查询此关键字没有搜索到结果 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("没检索到关键字 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FAILURE.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FAILURE);
			errorMsg.setErrorMsg("请求数据失败,稍后再试 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("请求数据失败 ");
			result.setError(errorMsg);
		} else if (StatusCodeDef.FREQUENCY_LIMITED.equals(statusCodeDef)) {
			ErrorMsg errorMsg = new ErrorMsg();errorMsg.setIp(ip);errorMsg.setHostName(hostName);
			errorMsg.setErrorCode(StatusCodeDef.FREQUENCY_LIMITED);
			errorMsg.setErrorMsg("可能访问过于频繁或非正常访问 ，imageurl：" + imageUrl + "  验证码识别结果：" + imagecode);
			errorMsg.setErrorName("可能访问过于频繁或非正常访问");
			result.setError(errorMsg);
		}

		result.debugMode(isDebug);
		return result;
	}
	
	
}
