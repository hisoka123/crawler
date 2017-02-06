package com.crawler.pbccrc.domain.json;

import java.io.Serializable;
import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;

public class LoginData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3350030291792254007L;
	
	private String statusCode;
	private String message;
	private Set<Cookie> cookies;
	private String codeImageUrl;
	private String codeValue;

	public LoginData() {
		super();
	}
	public LoginData(String statusCode, String message, Set<Cookie> cookies) {
		this.statusCode = statusCode;
		this.message = message;
		this.cookies = cookies;
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

	@Override
	public String toString() {
		return "LoginData [statusCode=" + statusCode + ", message=" + message
				+ ", cookies=" + cookies + ", codeImageUrl=" + codeImageUrl
				+ ", codeValue=" + codeValue + "]";
	}
}
