package com.crawler.customs.domain.json;

import java.io.Serializable;

public class CreditRate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -443171348567577121L;
	
	private String cId;//序号
	private String identifyTime;//认定时间
	private String legalNumber;//法律文书编号
	private String quatityRate;//信用等级
	
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getIdentifyTime() {
		return identifyTime;
	}
	public void setIdentifyTime(String identifyTime) {
		this.identifyTime = identifyTime;
	}
	public String getLegalNumber() {
		return legalNumber;
	}
	public void setLegalNumber(String legalNumber) {
		this.legalNumber = legalNumber;
	}
	public String getQuatityRate() {
		return quatityRate;
	}
	public void setQuatityRate(String quatityRate) {
		this.quatityRate = quatityRate;
	}
	
}
