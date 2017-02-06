package com.crawler.storm.def;

import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.GsonBuilder;

public class CustomsParam {

	private String copName;
	private String codeImageValue; // 查询验证码
	private Set<Cookie> cookies; // htmlunitCookies

	public String getCopName() {
		return copName;
	}

	public void setCopName(String copName) {
		this.copName = copName;
	}

	public String getCodeImageValue() {
		return codeImageValue;
	}

	public void setCodeImageValue(String codeImageValue) {
		this.codeImageValue = codeImageValue;
	}

	public Set<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}

	public CustomsParam() {
		super();
	}

	public CustomsParam(String copName, String codeImageValue, Set<Cookie> cookies) {
		this.copName = copName;
		this.codeImageValue = codeImageValue;
		this.cookies = cookies;
	}

	@Override
	public String toString() {
		return "PbccrcParam [copName=" + copName + ", codeImageValue=" + codeImageValue
				+ ", cookies=" + cookies + "]";
	}

	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}

	public CustomsParam fromJson(String pbccrcParam) {
		return new GsonBuilder().create().fromJson(pbccrcParam,
				CustomsParam.class);
	}

}
