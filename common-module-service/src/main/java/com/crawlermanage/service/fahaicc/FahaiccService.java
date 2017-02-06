/**
 * 
 */
package com.crawlermanage.service.fahaicc;

import java.lang.reflect.Type;
import java.util.*;

import com.crawler.fahaicc.domain.json.FahaiccLogin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.fahaicc.domain.json.FahaiItemFeed;
import com.crawler.fahaicc.htmlparser.FahaiccListParser;
import com.crawler.storm.def.FunctionCallParam;
import com.crawler.storm.def.FunctionDefine;
import com.crawler.storm.def.StormResultData;
import com.crawler.storm.def.WebParam;
import com.crawlermanage.service.aspect.CrawlerEngine;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author kingly
 * @date 2016年2月25日
 * 
 */
@Component
public class FahaiccService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FahaiccService.class);
	@Autowired
	private CrawlerEngine crawlerEngine;
	@Autowired
	private FahaiccListParser fahaiccListParser;
	
	public Result<StormResultData> login(String username, String password, boolean isDebug, String logback) {
		LOGGER.info("==============FahaiccService.login start!==============");
		Result<StormResultData> result = new Result<StormResultData>();
		Gson gson = new GsonBuilder().create();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.FAHAICC_LOGIN);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.addParam("txt_account", username);
		webParam.addParam("txt_password", password);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		
		result = crawlerEngine.execute(param, result);
		StormResultData data = gson.fromJson(result.getHtml(), StormResultData.class);
		
		if (StatusCodeDef.USERNAME_OR_PASSWORD_ERROR.equals(data.getStatusCode())) {
			LOGGER.info("==============FahaiccService.getDataOnce 用户名或密码错误！==============");
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(data.getIp());
			errorMsg.setHostName(data.getHostName());
			errorMsg.setErrorCode(StatusCodeDef.USERNAME_OR_PASSWORD_ERROR);
			errorMsg.setErrorMsg("用户名：" + username + "，" + data.getMessage());
			errorMsg.setErrorName("用户名或密码错误 ");
			result.setError(errorMsg);
			result.setHtml(result.getHtml());
			result.debugMode(isDebug);
			return result;
		}
		
		data.setHtml(null);
		result.setData(data);
		result.debugMode(isDebug);
		
		return result;
	}

	public Result<List<FahaiItemFeed>> search(String cookies, String q, String pg, boolean isDebug, String logback) {
		LOGGER.info("==============FahaiccService.login start!==============");
		Result<List<FahaiItemFeed>> result = new Result<List<FahaiItemFeed>>();
		Gson gson = new GsonBuilder().create();
		
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.FAHAICC_SEARCH);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		//参数
		webParam.addParam("q", q);
		webParam.addParam("pg", pg);
		webParam.addParam("tp", "");
		//cookies
		Type cookieSetType = new TypeToken<HashSet<Cookie>>(){}.getType();
		Set<Cookie> cookieSet = gson.fromJson(cookies, cookieSetType);
		webParam.setCookies(cookieSet);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		
		result = crawlerEngine.execute(param, result);
		StormResultData stormResultData = gson.fromJson(result.getHtml(), StormResultData.class);
		
		if (StatusCodeDef.ILLEGAL_PARAM.equals(stormResultData.getStatusCode())) {
			LOGGER.info("==============FahaiccService.getDataOnce 关键字信息不够详细！==============");
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(stormResultData.getIp());
			errorMsg.setHostName(stormResultData.getHostName());
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_PARAM);
			errorMsg.setErrorMsg(stormResultData.getMessage());
			errorMsg.setErrorName("关键字信息不够详细");
			result.setError(errorMsg);
			result.setHtml(result.getHtml());
			result.debugMode(isDebug);
			return result;
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(stormResultData.getStatusCode())) {
			LOGGER.info("==============FahaiccService.getDataOnce 没有搜索到关键字！==============");
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(stormResultData.getIp());
			errorMsg.setHostName(stormResultData.getHostName());
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg(stormResultData.getMessage());
			errorMsg.setErrorName("没有搜索到关键字");
			result.setError(errorMsg);
			result.setHtml(result.getHtml());
			result.debugMode(isDebug);
			return result;
		}
		
		String html = stormResultData.getHtml(); //
		List<FahaiItemFeed> data = fahaiccListParser.searchListParser(html);
		result.setData(data);
		result.debugMode(isDebug);
		
		return result;
	}


	public Result<List<FahaiItemFeed>> getDataOnce(String username, String password, String q,
			String pg, boolean isDebug, String logback) {
		Gson gson = new GsonBuilder().create();
		Result<List<FahaiItemFeed>> result2 = new Result<List<FahaiItemFeed>>();
		
		//登录
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) { //获取账户
			FahaiccLogin fahaiccLogin = FahaiccService.getRandomLoginInfo();
			username = fahaiccLogin.getName();
			password = fahaiccLogin.getPassword();
		}
		LOGGER.info("==============username:"+username+"==============");
		Result<StormResultData> result1 = new Result<StormResultData>();
		FunctionCallParam fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.FAHAICC_LOGIN);
		WebParam webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.addParam("txt_account", username);
		webParam.addParam("txt_password", password);
		fcm.setWebEngineParam(webParam);
		String param = fcm.toJson();
		result1 = crawlerEngine.execute(param, result1);
		StormResultData loginData = gson.fromJson(result1.getHtml(), StormResultData.class);
		
		if (StatusCodeDef.USERNAME_OR_PASSWORD_ERROR.equals(loginData.getStatusCode())) {
			LOGGER.info("==============FahaiccService.getDataOnce 用户名或密码错误！==============");
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(loginData.getIp());
			errorMsg.setHostName(loginData.getHostName());
			errorMsg.setErrorCode(StatusCodeDef.USERNAME_OR_PASSWORD_ERROR);
			errorMsg.setErrorMsg("用户名：" + username + "，" + loginData.getMessage());
			errorMsg.setErrorName("用户名或密码错误 ");
			result2.setError(errorMsg);
			result2.setHtml(result1.getHtml());
			result2.debugMode(isDebug);
			return result2;
		}
		
		//查询
		fcm = new FunctionCallParam();
		fcm.setFunction(FunctionDefine.FAHAICC_SEARCH);
		webParam = new WebParam();
		webParam.setLogback(logback);
		webParam.addParam("q", q);
		webParam.addParam("pg", pg);
		webParam.addParam("tp", "");
		webParam.addParam("st", ""); //全文搜索参数（去掉后为非全文搜索）
		webParam.setCookies(loginData.getHucookies());
		fcm.setWebEngineParam(webParam);
		param = fcm.toJson();
		
		result2 = crawlerEngine.execute(param, result2);
		StormResultData stormResultData = gson.fromJson(result2.getHtml(), StormResultData.class);
		if (StatusCodeDef.ILLEGAL_PARAM.equals(stormResultData.getStatusCode())) {
			LOGGER.info("==============FahaiccService.getDataOnce 关键字信息不够详细！==============");
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(stormResultData.getIp());
			errorMsg.setHostName(stormResultData.getHostName());
			errorMsg.setErrorCode(StatusCodeDef.ILLEGAL_PARAM);
			errorMsg.setErrorMsg("用户名：" + username + "，" + stormResultData.getMessage());
			errorMsg.setErrorName("关键字信息不够详细");
			result2.setError(errorMsg);
			result2.setHtml(result2.getHtml());
			result2.debugMode(isDebug);
			return result2;
		} else if (StatusCodeDef.NO_DATA_FOUND.equals(stormResultData.getStatusCode())) {
			LOGGER.info("==============FahaiccService.getDataOnce 没有搜索到关键字！==============");
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setIp(stormResultData.getIp());
			errorMsg.setHostName(stormResultData.getHostName());
			errorMsg.setErrorCode(StatusCodeDef.NO_DATA_FOUND);
			errorMsg.setErrorMsg("用户名：" + username + "，" + stormResultData.getMessage());
			errorMsg.setErrorName("没有搜索到关键字");
			result2.setError(errorMsg);
			result2.setHtml(result2.getHtml());
			result2.debugMode(isDebug);
			return result2;
		}
		
		String html = stormResultData.getHtml(); //
		LOGGER.info("===============FahaiccService.getDataOnce search result html: ===================: " + html);
		List<FahaiItemFeed> data = fahaiccListParser.searchListParser(html);
		for (FahaiItemFeed fahaiItemFeed : data) {
			fahaiItemFeed.setUsername(username);
		}
		result2.setData(data);
		result2.debugMode(isDebug);
		
		return result2;
	}
	
	
	/**
	 * 随机获一个登录账号
	 * @return
	 */
	public static FahaiccLogin getRandomLoginInfo(){
		List<FahaiccLogin> fahaiccLoginList = new ArrayList<FahaiccLogin>();
		FahaiccLogin fl1 = new FahaiccLogin();
		fl1.setName("感同身受");
		fl1.setPassword("159357");
		FahaiccLogin fl2 = new FahaiccLogin();
		fl2.setName("你好法海");
		fl2.setPassword("159357");
		FahaiccLogin fl3 = new FahaiccLogin();
		fl3.setName("你好会员");
		fl3.setPassword("159357");
		fahaiccLoginList.add(fl1);
		fahaiccLoginList.add(fl2);
		fahaiccLoginList.add(fl3);

		return fahaiccLoginList.get(new Random().nextInt(fahaiccLoginList.size()));
	}

}
