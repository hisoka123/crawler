package com.crawlermanage.service.shixin;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawler.domain.json.Result;
import com.crawler.iecms.htmlparser.IecmsInfoParser;
import com.crawler.pbccrc.htmlparser.PbcCreditFeedParser;
import com.crawler.shixin.domain.json.ShiXinJson;
import com.crawler.shixin.htmlparser.ShixinWebParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.WebParam;
import com.crawler.zhengxin.domain.json.ZhengxinJson;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.module.htmlunit.definition.UtilDefinition;
import com.module.redis.service.PbccrcPDFCreditReportService;

@Component
public class ShixinService {
	@Autowired
	private CrawlerEngine crawlerEngine;
	@Autowired
	private ShixinWebParser shixinWebParser;
	@Autowired
	private PbccrcPDFCreditReportService pbccrcPDFCreditReportService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ShixinService.class);
				
	/**
	 * 获取（JSON）
	 * @param corp_code
	 * @param corp_name
	 * @param sc_code 
	 * @param codea 
	 * @return Result<String>
	 * @throws IOException
	 */
	public Result<List<Object>> getResult(String pName, String pCardNum,String pCode,String serializedFileName, String logback) throws IOException{
		LOGGER.info("=============ShixinService.getResult start!=================");
		Result<List<Object>> result = new Result<List<Object>>();
		Gson gson = new GsonBuilder().create();
		boolean isDebug=true;
	
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.SHIXIN);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://shixin.court.gov.cn");
		webParam.setSerializedFileName(serializedFileName);	
		webParam.addParam("pName", pName);
		webParam.addParam("pCardNum", pCardNum);
		webParam.addParam("pCode", pCode);
		webParam.setUnit(UtilDefinition.HTMLUNIT);
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The ShixinService.getResult functionCallParam is: {}", param);
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		System.out.println(result.getHtml());
		List<Object> shixinJsons = shixinWebParser.shiXinJsonParser(result.getHtml());
		
		result.setData(shixinJsons);
		result.debugMode(isDebug);
		return result;
	}
	
	public Result<Map<String,String>> searchPageHandle( boolean isDebug, String logback) {
		LOGGER.info("=============ShixinService.searchPageHandle start!=================");
		Result<Map<String,String>> result = new Result<Map<String,String>>();
		Gson gson = new GsonBuilder().create();
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://shixin.court.gov.cn");
		webParam.setCodeImageId("//img[@id='captchaImg']");			
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.SHIXINPCODE);
		fcm.setWebEngineParam(webParam);		
		String param = gson.toJson(fcm);
		LOGGER.info("The ShixinService.searchPageHandle functionCallParam is: {}", param);		
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
		
	
	public Result<List<ShiXinJson>> getShixinDataOnce(String pName, String pCardNum,String logback) throws IOException{
		LOGGER.info("=============ShixinService.getResult start!=================");
		Result<List<ShiXinJson>> result = new Result<List<ShiXinJson>>();
		Gson gson = new GsonBuilder().create();
		boolean isDebug=true;
	
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.SHIXINDATAONCE);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.setSearchPage("http://shixin.court.gov.cn");
		webParam.setCodeImageId("//img[@id='captchaImg']");			
//		webParam.setSerializedFileName(serializedFileName);	
		webParam.addParam("pName", pName);
		webParam.addParam("pCardNum", pCardNum);
//		webParam.addParam("pCode", pCode);
		webParam.setUnit(UtilDefinition.HTMLUNIT);
		
		fcm.setWebEngineParam(webParam);
		String param = gson.toJson(fcm);
		LOGGER.info("The ShixinService.getResult functionCallParam is: {}", param);
		result = crawlerEngine.execute(param, result);
		if (result.getError()!=null) {
			return result;
		}
		System.out.println(result.getHtml());
		List<ShiXinJson> shixinJsons = shixinWebParser.shiXinJsonParser2(result.getHtml());
		
		result.setData(shixinJsons);
		result.debugMode(isDebug);
		return result;
	}
	
}

