package com.storm.domian;

import java.io.Serializable;
import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.GsonBuilder;

public class CustomsParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1549280899216619414L;
	private String copName;
	private String codeImageValue; // 查询验证码
	private Set<Cookie> cookies; // htmlunitCookies

	public CustomsParam() {
		super();
	}

	public CustomsParam(String copName, String codeImageValue, Set<Cookie> cookies) {
		this.copName = copName;
		this.codeImageValue = codeImageValue;
		this.cookies = cookies;
	}

	public String getCopName() {
		return copName;
	}

	public void setCopName(String copName) {
		this.copName = copName;
	}

	public Set<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}

	@Override
	public String toString() {
		return "PbccrcParam [copName=" + copName + ", codeImageValue="
				+ codeImageValue + ", cookies=" + cookies + "]";
	}

	public String getCodeImageValue() {
		return codeImageValue;
	}

	public void setCodeImageValue(String codeImageValue) {
		this.codeImageValue = codeImageValue;
	}

	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}

	public CustomsParam fromJson(String customsParam) {
		return new GsonBuilder().create().fromJson(customsParam,
				CustomsParam.class);
	}

}
