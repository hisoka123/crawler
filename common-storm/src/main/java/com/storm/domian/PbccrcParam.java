/**
 * 
 */
package com.storm.domian;

import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.GsonBuilder;

/**
 * @author kingly
 * @date 2016年1月11日
 * 
 */
public class PbccrcParam {
	private String username;
	private String password;
	private String imageCode; //登录验证码或获取PDF文档验证码
	private String tradeCode; //手机验证码
	private Set<Cookie> cookies; //htmlunitCookies
	
	public PbccrcParam() {
		super();
	}
	public PbccrcParam(String username, String password, String imageCode,
			String tradeCode, Set<Cookie> cookies) {
		this.username = username;
		this.password = password;
		this.imageCode = imageCode;
		this.tradeCode = tradeCode;
		this.cookies = cookies;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public Set<Cookie> getCookies() {
		return cookies;
	}
	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}
	
	@Override
	public String toString() {
		return "PbccrcParam [username=" + username + ", password=" + password
				+ ", imageCode=" + imageCode + ", tradeCode=" + tradeCode
				+ ", cookies=" + cookies + "]";
	}

	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}
	
	public PbccrcParam fromJson(String pbccrcParam) {
		return new GsonBuilder().create().fromJson(pbccrcParam, PbccrcParam.class);
	}
}
