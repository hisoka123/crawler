package com.crawler.storm.def;

import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.GsonBuilder;

public class CreditchinaParam {

	private String areas;
	private String keyword;
	private String objectType;
	private String page;
	private String sources;
	private Set<Cookie> cookies;

	public CreditchinaParam() {
		super();
	}

	public CreditchinaParam(String areas, String keyword, String objectType,
			String page, String sources, Set<Cookie> cookies) {
		this.areas = areas;
		this.keyword = keyword;
		this.objectType = objectType;
		this.page = page;
		this.sources = sources;
		this.cookies = cookies;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public Set<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}

	@Override
	public String toString() {
		return "CreditchinaParam [areas=" + areas + ", keyword=" + keyword
				+ ", objectType=" + objectType + ", page=" + page
				+ ", sources=" + sources + ", cookies=" + cookies + "]";
	}

	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}

	public CreditchinaParam fromJson(String creditchinaParam) {
		return new GsonBuilder().create().fromJson(creditchinaParam,
				CreditchinaParam.class);
	}

}
