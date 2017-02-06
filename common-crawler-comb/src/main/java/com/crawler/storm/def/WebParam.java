package com.crawler.storm.def;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;

public class WebParam{
	
	private String url;
	
	private String unit;
	
	private String method; //post get
	
	private Map<String,String> params; //请求参数
	
	private String searchPage; //搜索页
	
	private String codeImageId; //验证码图片id
	
	private Map<String,String> requestHeaders; //请求头
	
	private Set<Cookie> cookies; //htmlunit cookies
	
	private Map<String,String> jsoupCookies; //jsoup cookies
	
	private String serializedFileName;
	
	private String logback;
	
	public WebParam(){
		
	}
	 

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	  
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getSearchPage() {
		return searchPage;
	}

	public void setSearchPage(String searchPage) {
		this.searchPage = searchPage;
	}

	public String getCodeImageId() {
		return codeImageId;
	}

	public void setCodeImageId(String codeImageId) {
		this.codeImageId = codeImageId;
	}

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public Set<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}

	public Map<String, String> getJsoupCookies() {
		return jsoupCookies;
	}

	public void setJsoupCookies(Map<String, String> jsoupCookies) {
		this.jsoupCookies = jsoupCookies;
	}

	public String getSerializedFileName() {
		return serializedFileName;
	}

	public void setSerializedFileName(String serializedFileName) {
		this.serializedFileName = serializedFileName;
	}
	
	public String getLogback() {
		return logback;
	}

	public void setLogback(String logback) {
		this.logback = logback;
	}

	@Override
	public String toString() {
		return "WebParam [url=" + url + ", unit=" + unit + ", method=" + method
				+ ", params=" + params + ", searchPage=" + searchPage
				+ ", codeImageId=" + codeImageId + ", requestHeaders="
				+ requestHeaders + ", cookies=" + cookies + ", jsoupCookies="
				+ jsoupCookies + ", serializedFileName=" + serializedFileName
				+ ", logback=" + logback + "]";
	}

	
	public String toJson(){
		Gson gson = new Gson();   
		return gson.toJson(this);
	}
	
	public WebParam fromParam(String json){
		Gson gson = new Gson();  
		WebParam param = gson.fromJson(json, WebParam.class);
		return param;
	}

	public void addParam(String name, String value) {
		if (params==null) {
			params = new HashMap<String,String>();
		}
		params.put(name, value);
	}
	
	public void addHtmlUnitCookie(Cookie cookie) {
		if (cookies==null) {
			cookies = new HashSet<Cookie>();
		}
		cookies.add(cookie);
	}
	
	public void addRequestHeader(String name, String value) {
		if (requestHeaders==null) {
			requestHeaders = new HashMap<String,String>();
		}
		requestHeaders.put(name, value);
	}
}
