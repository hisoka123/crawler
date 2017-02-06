package com.crawler.customs.domain.json;

import java.io.Serializable;
import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;

public class SearchDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8899098888024160210L;

	private Set<Cookie> cookies;
	private String seqNo;
	private String saicSysNo;
	private String html;
	private String bCName;// 企业中文名称
	private String cRCode;// 海关注册编码
	private String customsName;// 注册海关
	private String busType;// 经营类别

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getbCName() {
		return bCName;
	}

	public void setbCName(String bCName) {
		this.bCName = bCName;
	}

	public String getcRCode() {
		return cRCode;
	}

	public void setcRCode(String cRCode) {
		this.cRCode = cRCode;
	}

	public String getCustomsName() {
		return customsName;
	}

	public void setCustomsName(String customsName) {
		this.customsName = customsName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public SearchDetail() {
		super();
	}

	public SearchDetail(Set<Cookie> cookies, String seqNo, String saicSysNo) {
		this.cookies = cookies;
		this.seqNo = seqNo;
		this.saicSysNo = saicSysNo;
	}

	@Override
	public String toString() {
		return "ResultData [cookies=" + cookies + ", seqNo=" + seqNo
				+ ", saicSysNo=" + saicSysNo + "]";
	}

	public Set<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSaicSysNo() {
		return saicSysNo;
	}

	public void setSaicSysNo(String saicSysNo) {
		this.saicSysNo = saicSysNo;
	}

	public void debugMode(boolean isDebug) {
		if (!isDebug)
			this.html = null;
	}

}
