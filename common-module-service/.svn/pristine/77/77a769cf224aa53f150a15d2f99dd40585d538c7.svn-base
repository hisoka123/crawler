package com.crawlermanage.service.iecms;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.dailianmeng.domain.json.LoanUnionFeedJson;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.iecms.domain.json.IecmsJson;
import com.crawler.iecms.htmlparser.IecmsInfoParser;
import com.crawler.pbccrc.htmlparser.PbcCreditFeedParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.redis.service.PbccrcPDFCreditReportService;

@Component
public class IecmsService {
	@Autowired
	private CrawlerEngine crawlerEngine;
	@Autowired
	private PbcCreditFeedParser pbcCreditFeedParser;
	@Autowired
	private PbccrcPDFCreditReportService pbccrcPDFCreditReportService;
	private static final Logger LOGGER = LoggerFactory.getLogger(IecmsService.class);
				
	/**
	 * 获取（JSON）
	 * @param corp_code
	 * @param corp_name
	 * @param sc_code 
	 * @param codea 
	 * @return Result<String>
	 * @throws IOException
	 */
	public Result<String> getCname(String corp_code, String corp_name,String sc_code,String codea,String serializedFileName
			,boolean isDebug, String logback) throws IOException{
		LOGGER.info("=============IecmsService.getCname start!=================");
		Result<String> result = new Result<String>();
		Gson gson = new GsonBuilder().create();
		//boolean isDebug=true;
	
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.IECMS_GETGNAME);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://iecms.ec.com.cn/iecms/corp/index4.jsp");
		webParam.setSerializedFileName(serializedFileName);	
		//webParam.setMethod("post");
		webParam.addParam("corp_code", corp_code);
		webParam.addParam("corp_name", corp_name);
		webParam.addParam("sc_code", sc_code);
		webParam.addParam("codea", codea);
		webParam.addParam("keywordXpath1", "//input[@name='corp_code']");
		webParam.addParam("keywordXpath2", "//input[@name='corp_name']");
		webParam.addParam("keywordXpath3", "//input[@name='sc_code']");
		webParam.addParam("imagecodeXpath", "//input[@name='codea']");
		webParam.addParam("loginBtnXpath", "//input[@type='submit']");
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The IecmsService.getSourceHtmlOfIecms functionCallParam is: {}", param);
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		
		result.setData(result.getHtml());
		result.debugMode(isDebug);
		return result;
	}
	
	public Result<List<IecmsJson>> getDataOnce(String keyword, String type,
			boolean isDebug, String logback) {
		Result<List<IecmsJson>> result = new Result<List<IecmsJson>>();
		Gson gson = new GsonBuilder().create();
		String corp_code = "";
		String corp_name = "";
		String sc_code = "";
		if (null != keyword && !"".equals(keyword)) {
			if ("1".equals(type)) {
				corp_name = keyword;
			}
			if ("2".equals(type)) {
				corp_code = keyword;
			}
			if ("3".equals(type)) {
				sc_code = keyword;
			}
		}
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.IECMS_GETGNAMEONEC);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://iecms.ec.com.cn/iecms/corp/index4.jsp");

		// webParam.setMethod("post");
		webParam.addParam("corp_code", corp_code);
		webParam.addParam("corp_name", corp_name);
		webParam.addParam("sc_code", sc_code);
		// webParam.addParam("codea", codea);
		webParam.addParam("keywordXpath1", "//input[@name='corp_code']");
		webParam.addParam("keywordXpath2", "//input[@name='corp_name']");
		webParam.addParam("keywordXpath3", "//input[@name='sc_code']");
		webParam.addParam("imagecodeXpath", "//input[@name='codea']");
		webParam.addParam("loginBtnXpath", "//input[@type='submit']");
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info(
				"The IecmsService.getSourceHtmlOfIecms functionCallParam is: {}",
				param);
		result = crawlerEngine.execute(param, result);
		if (result.getError() != null) {
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
			IecmsInfoParser iecmsInfoParser = new IecmsInfoParser();
			List<IecmsJson> IecmsJsons = iecmsInfoParser.parseResultHtmliecms(resultHtml);				
			   result.setData(IecmsJsons);  
		   
			
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
//		IecmsInfoParser iecmsInfoParser = new IecmsInfoParser();
//		List<IecmsJson> IecmsJsons = iecmsInfoParser.parseResultHtmliecms(resultHtml);
//		if(IecmsJsons.size()==0){
//			ErrorMsg errorMsg = new ErrorMsg();
//			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
//			errorMsg.setErrorName("没有搜索到数据 ");
//			result.setError(errorMsg);
//		}
//		result.setData(IecmsJsons);
		result.debugMode(isDebug);
		return result;
	}
	
	public Result<Map<String,String>> searchPageHandle( boolean isDebug, String logback) {
		LOGGER.info("=============IecmsService.searchPageHandle start!=================");
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		Gson gson = new GsonBuilder().create();
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://iecms.ec.com.cn/iecms/corp/index4.jsp");
		webParam.setCodeImageId("//img[@id='randImage']");			
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.IECMS_GETGNAMEPAGE);
		fcm.setWebEngineParam(webParam);		
		String param = gson.toJson(fcm);
		LOGGER.info("The IecmsService.searchPageHandle functionCallParam is: {}", param);		
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
	

	
	
	
	public com.crawler.iecms.domain.json.UserFeedJson parseResultHtml(String html) {
		String html1=html.replaceAll("<!--", "");
		String html2=html1.replaceAll("-->", "");
		IecmsInfoParser ips=new IecmsInfoParser();
		return ips.parseResultHtml(html2);
	}
		
}

