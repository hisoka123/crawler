package com.crawler.pbccrc.domain.json;

import java.io.Serializable;
import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;

/**
 * @author kingly
 * @date 2016年1月8日
 * 操作失败返回数据
 * 
 */
public class ResultData implements Serializable {
	private static final long serialVersionUID = -8993659315153900086L;
	private String statusCode;
	private String message;
	private Set<Cookie> cookies;
	private String codeImageUrl;
	private String codeValue;
	private String reportFileURL;
	
	public ResultData() {
		super();
	}
	public ResultData(String statusCode, String message, Set<Cookie> cookies,
			String codeImageUrl) {
		this.statusCode = statusCode;
		this.message = message;
		this.cookies = cookies;
		this.codeImageUrl = codeImageUrl;
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
	public String getCodeImageUrl() {
		return codeImageUrl;
	}
	public void setCodeImageUrl(String codeImageUrl) {
		this.codeImageUrl = codeImageUrl;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getReportFileURL() {
		return reportFileURL;
	}
	public void setReportFileURL(String reportFileURL) {
		this.reportFileURL = reportFileURL;
	}
	
	@Override
	public String toString() {
		return "ResultData [statusCode=" + statusCode + ", message=" + message
				+ ", cookies=" + cookies + ", codeImageUrl=" + codeImageUrl
				+ ", codeValue=" + codeValue + ", reportFileURL="
				+ reportFileURL + "]";
	}
}	
