package com.crawler.customs.domain.json;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;

public class CustomsResultData implements Serializable {

	private static final long serialVersionUID = 4536734028153927198L;
	private String statusCode;
	private String message;
	private Set<Cookie> cookies;
	private String imageUrl;
	//private String codeImageValue;
	private String html;
	private List<SearchDetail> searchArgsList;

	public CustomsResultData() {
		super();
	}

	public CustomsResultData(String statusCode, String message,
			Set<Cookie> cookies, String imageUrl) {
		this.statusCode = statusCode;
		this.message = message;
		this.cookies = cookies;
		this.imageUrl = imageUrl;
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

	/*public String getCodeImageValue() {
		return codeImageValue;
	}

	public void setCodeImageValue(String codeImageValue) {
		this.codeImageValue = codeImageValue;
	}*/

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	public List<SearchDetail> getSearchArgsList() {
		return searchArgsList;
	}

	public void setSearchArgsList(List<SearchDetail> searchArgsList) {
		this.searchArgsList = searchArgsList;
	}

	@Override
	public String toString() {
		return "CustomsResultData [statusCode=" + statusCode + ", message="
				+ message + ", cookies=" + cookies + ", imageUrl=" + imageUrl
				+ ", html=" + html + "]";
	}
	
	public void debugMode(boolean isDebug) {
		if (!isDebug)
			this.html = null;
	}

}
