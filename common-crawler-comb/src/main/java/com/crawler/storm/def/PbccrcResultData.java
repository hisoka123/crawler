package com.crawler.storm.def;

import java.io.Serializable;
import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;

public class PbccrcResultData implements Serializable {
	private static final long serialVersionUID = 4536734028153927198L;
	public String username;
	public String ip;
	public String hostName;
	private String statusCode;
	private String message;
	private Set<Cookie> cookies;
	private String imageUrl;
	private String reportFileURL;
	private String html;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public Set<Cookie> getCookies() {
		return cookies;
	}
	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getReportFileURL() {
		return reportFileURL;
	}
	public void setReportFileURL(String reportFileURL) {
		this.reportFileURL = reportFileURL;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "PbccrcResultData [username=" + username + ", ip=" + ip
				+ ", hostName=" + hostName + ", statusCode=" + statusCode
				+ ", message=" + message + ", cookies=" + cookies
				+ ", imageUrl=" + imageUrl + ", reportFileURL=" + reportFileURL
				+ ", html=" + html + "]";
	}
}
