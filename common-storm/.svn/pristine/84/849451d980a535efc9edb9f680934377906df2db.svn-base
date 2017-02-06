package com.storm.function;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import com.crawler.domain.json.StatusCodeDef;
import com.crawler.storm.def.StormResultData;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.htmlunit.WebCrawler;
import com.module.log.redis.ChannelLogger;
import com.module.log.redis.ChannelLoggerFactory;
import com.storm.domian.WebParam;
import com.storm.util.IPUtil;

public class FahaiccFunction {
	private static final String LOGINPAGE = "http://www.fahaicc.com/login.php";
	private static final String SEARCH_URL = "http://www.fahaicc.com/searchlist.php";
	
	@SuppressWarnings({ "deprecation" })
	private static void clearCookies(WebClient webClient, URL url) throws MalformedURLException {
		CookieManager manager = webClient.getCookieManager();
		Set<Cookie> cookies = manager.getCookies(url);
		Set<String> cookieNames = new HashSet<String>();
		for (Cookie cookie : cookies) {
			cookieNames.add(cookie.getName());
		}
		for (String cookieName : cookieNames) {
			Cookie cookie = manager.getCookie(cookieName);
			manager.removeCookie(cookie);
		}
	}
	private static void addCookies(WebClient webClient, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			if (StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			webClient.getCookieManager().addCookie(cookie);
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public String login(Map<String,String> loginParamMap, WebParam webParam) throws IOException {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(FahaiccFunction.class, webParam.getLogback());
		LOGGER.info("===================FahaiccFunction.login start!=======================");
		
		Gson gson = new GsonBuilder().create();
		StormResultData resultData = new StormResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		WebRequest webRequest = new WebRequest(new URL(LOGINPAGE), HttpMethod.GET);
		HtmlPage loginPage = webClient.getPage(webRequest);

		//清除之前的cookies
		clearCookies(webClient, new URL(LOGINPAGE));
		
		HtmlTextInput usernameInput = (HtmlTextInput) loginPage.querySelector("#txt_account");
		HtmlPasswordInput passwordInput = (HtmlPasswordInput) loginPage.querySelector("#txt_password");
		usernameInput.setText(loginParamMap.get("txt_account"));
		passwordInput.setText(loginParamMap.get("txt_password"));
		
		HtmlElement loginBtn = (HtmlElement) loginPage.querySelector("#input_login");
		HtmlPage loggedPage = loginBtn.click();
		
		LOGGER.info("====================================================================================");
		LOGGER.info(loggedPage.asText());
		
		if(loggedPage.asText().contains("忘记登录密码")) {
			LOGGER.info("================================用户名或密码错误！==========================================");
			resultData.setStatusCode(StatusCodeDef.USERNAME_OR_PASSWORD_ERROR);
			resultData.setMessage("用户名或密码错误！");
			resultData.setHtml(loggedPage.asXml());
			resultData.setIp(IPUtil.getIp());
			resultData.setHostName(IPUtil.getHostName());
			
			LOGGER.returnRedisResource();
			return gson.toJson(resultData);
		}
		
		resultData.setStatusCode(StatusCodeDef.SCCCESS);
		resultData.setMessage("登陆成功！");
		resultData.setHtml(loggedPage.asXml());
		resultData.setHucookies(loggedPage.getWebClient().getCookieManager().getCookies(new URL(LOGINPAGE)));
		LOGGER.info("================================登陆成功！==========================================");
		
		LOGGER.returnRedisResource();
		return gson.toJson(resultData);
	}
	
	public String search(WebParam webParam) throws Exception {
		ChannelLogger LOGGER = ChannelLoggerFactory.getLogger(FahaiccFunction.class, webParam.getLogback());
		
		LOGGER.info("===================FahaiccFunction.search start!=======================");
		Gson gson = new GsonBuilder().create();
		StormResultData resultData = new StormResultData();
		WebClient webClient = WebCrawler.getInstance().getWebClient();
		
		//cookie
		Set<Cookie> cookies = webParam.getCookies();
		addCookies(webClient, cookies);
		
		//请求参数
		Map<String, String> paramMap = webParam.getParams();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Entry<String, String> paramEntry : paramMap.entrySet()) {
			params.add(new NameValuePair(paramEntry.getKey(), paramEntry.getValue()));
		}
		
		//请求
		WebRequest webRequest = new WebRequest(new URL(SEARCH_URL), HttpMethod.GET);
		webRequest.setRequestParameters(params);
		HtmlPage listPage = webClient.getPage(webRequest);
		
		String msg = WebCrawler.getAlertMsg();
		if(msg.contains("检索结果数量过大")) {
			LOGGER.info("================================检索结果数量过大！==========================================");
			resultData.setStatusCode(StatusCodeDef.ILLEGAL_PARAM);
			resultData.setMessage("符合您输入关键字的检索结果数量过大，请您重新输入更加详细的关键字信息。");
			resultData.setHtml(listPage.asXml());
			resultData.setIp(IPUtil.getIp());
			resultData.setHostName(IPUtil.getHostName());
			
			LOGGER.returnRedisResource();
			return gson.toJson(resultData);
		}
		if (listPage.asText().contains("找到 0 条关于")) {
			LOGGER.info("================================找到 0 条关于“"+paramMap.get("q")+"”的数据!==========================================");
			resultData.setStatusCode(StatusCodeDef.NO_DATA_FOUND);
			resultData.setMessage("找到 0 条关于“"+URLDecoder.decode(paramMap.get("q"))+"”的数据!");
			resultData.setHtml(listPage.asXml());
			resultData.setIp(IPUtil.getIp());
			resultData.setHostName(IPUtil.getHostName());
			
			LOGGER.returnRedisResource();
			return gson.toJson(resultData);
		}
		
		//返回数据
		resultData.setStatusCode(StatusCodeDef.SCCCESS);
		resultData.setMessage("查询成功！");
		resultData.setHtml(listPage.asXml());
		resultData.setHucookies(listPage.getWebClient().getCookieManager().getCookies());
		
		LOGGER.returnRedisResource();
		
		return  gson.toJson(resultData);
	}
	
}
