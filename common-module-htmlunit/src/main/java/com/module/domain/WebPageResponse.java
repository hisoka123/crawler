package com.module.domain;

import java.util.Collection;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;

public class WebPageResponse {
	
	public String html;
	
	public int httpStatusCode;
	
	public String url;
	
	public String unit;
	
	public Collection<Cookie> cookies;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
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

	public Collection<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(Collection<Cookie> cookies) {
		this.cookies = cookies;
	}
	
	public String toJson(){
		Gson gson = new Gson();   
		return gson.toJson(this);
	}

	@Override
	public String toString() {
		return "WebPageResponse [html=" + html + ", httpStatusCode="
				+ httpStatusCode + ", url=" + url + ", unit=" + unit
				+ ", cookies=" + cookies + "]";
	}

}
