package com.crawler.storm.def;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;

public class StormResultData implements Serializable {
	private static final long serialVersionUID = 1344090629550212746L;
	private String ip;
	private String hostName; 
	private String statusCode;
	private String message;
	private WebParam webParam; 		//参数
	private Set<Cookie> hucookies; 	//htmlunit cookies
	private Map<String,String> jpcookies; //jsoup cookies
	private String imageUrl; 		//验证码URL
	private String html;			//原始页面
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public WebParam getWebParam() {
		return webParam;
	}
	public void setWebParam(WebParam webParam) {
		this.webParam = webParam;
	}
	public Set<Cookie> getHucookies() {
		return hucookies;
	}
	public void setHucookies(Set<Cookie> hucookies) {
		this.hucookies = hucookies;
	}
	public Map<String, String> getJpcookies() {
		return jpcookies;
	}
	public void setJpcookies(Map<String, String> jpcookies) {
		this.jpcookies = jpcookies;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "StormResultData [ip=" + ip + ", hostName=" + hostName
				+ ", statusCode=" + statusCode + ", message=" + message
				+ ", webParam=" + webParam + ", hucookies=" + hucookies
				+ ", jpcookies=" + jpcookies + ", imageUrl=" + imageUrl
				+ ", html=" + html + "]";
	}
}
