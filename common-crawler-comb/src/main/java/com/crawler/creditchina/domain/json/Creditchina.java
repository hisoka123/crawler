package com.crawler.creditchina.domain.json;

import java.io.Serializable;

public class Creditchina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1067786085618736712L;

	private String name;// 企业或个人名称
	private Integer objectType;// 主体类型 (法人或自然人)===2代表法人1代表自然人
	private String encryStr;// 唯一标识符
	private String nameUrl;// 基础信息
	private Integer goodCount;// 优良记录数
	private Integer badCount;// 不良记录数
	private Integer dishonestyCount;// 失信记录数
	private String goodUrl;// 优良记录
	private String badUrl;// 不良记录
	private String dishonestyUrl;// 失信记录

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public String getEncryStr() {
		return encryStr;
	}

	public void setEncryStr(String encryStr) {
		this.encryStr = encryStr;
	}

	public String getNameUrl() {
		return nameUrl;
	}

	public void setNameUrl(String nameUrl) {
		this.nameUrl = nameUrl;
	}

	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}

	public Integer getBadCount() {
		return badCount;
	}

	public void setBadCount(Integer badCount) {
		this.badCount = badCount;
	}

	public Integer getDishonestyCount() {
		return dishonestyCount;
	}

	public void setDishonestyCount(Integer dishonestyCount) {
		this.dishonestyCount = dishonestyCount;
	}

	public String getGoodUrl() {
		return goodUrl;
	}

	public void setGoodUrl(String goodUrl) {
		this.goodUrl = goodUrl;
	}

	public String getBadUrl() {
		return badUrl;
	}

	public void setBadUrl(String badUrl) {
		this.badUrl = badUrl;
	}

	public String getDishonestyUrl() {
		return dishonestyUrl;
	}

	public void setDishonestyUrl(String dishonestyUrl) {
		this.dishonestyUrl = dishonestyUrl;
	}

	@Override
	public String toString() {
		return "Creditchina [name=" + name + ", objectType=" + objectType
				+ ", encryStr=" + encryStr + ", nameUrl=" + nameUrl
				+ ", goodCount=" + goodCount + ", badCount=" + badCount
				+ ", dishonestyCount=" + dishonestyCount + ", goodUrl="
				+ goodUrl + ", badUrl=" + badUrl + ", dishonestyUrl="
				+ dishonestyUrl + "]";
	}

}
