package com.crawler.customs.domain.json;

import java.io.Serializable;
import java.util.List;

public class Customs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7521228513011331999L;

	private String cRCode;// 海关注册编码
	private String socialCreditCode;// 社会信用代码
	private String customsName;// 注册海关
	private String recordDate;// 注册日期
	private String barCode;// 组织机构代码
	private String bCName;// 企业中文名称
	private String regAddress;// 工商注册地址
	private String admDivision;// 行政区划
	private String ecoReg;// 经济区划
	private String economicCategory;// 经济类型
	private String busType;// 经营类别
	private String businessLine;// 行业种类
	private String customsValidity;// 报关有效期
	private String customsMarks;// 海关注销标志
	private String annualReport;// 年报情况
	private List<CreditRate> creditRate;// 信用等级
	private List<AdmPuInformation> admPuInformation;// 行政处罚信息

	public String getcRCode() {
		return cRCode;
	}

	public void setcRCode(String cRCode) {
		this.cRCode = cRCode;
	}

	public String getSocialCreditCode() {
		return socialCreditCode;
	}

	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}

	public String getCustomsName() {
		return customsName;
	}

	public void setCustomsName(String customsName) {
		this.customsName = customsName;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getbCName() {
		return bCName;
	}

	public void setbCName(String bCName) {
		this.bCName = bCName;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getAdmDivision() {
		return admDivision;
	}

	public void setAdmDivision(String admDivision) {
		this.admDivision = admDivision;
	}

	public String getEcoReg() {
		return ecoReg;
	}

	public void setEcoReg(String ecoReg) {
		this.ecoReg = ecoReg;
	}

	public String getEconomicCategory() {
		return economicCategory;
	}

	public void setEconomicCategory(String economicCategory) {
		this.economicCategory = economicCategory;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusinessLine() {
		return businessLine;
	}

	public void setBusinessLine(String businessLine) {
		this.businessLine = businessLine;
	}

	public String getCustomsValidity() {
		return customsValidity;
	}

	public void setCustomsValidity(String customsValidity) {
		this.customsValidity = customsValidity;
	}

	public String getCustomsMarks() {
		return customsMarks;
	}

	public void setCustomsMarks(String customsMarks) {
		this.customsMarks = customsMarks;
	}

	public String getAnnualReport() {
		return annualReport;
	}

	public void setAnnualReport(String annualReport) {
		this.annualReport = annualReport;
	}

	public List<CreditRate> getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(List<CreditRate> creditRate) {
		this.creditRate = creditRate;
	}

	public List<AdmPuInformation> getAdmPuInformation() {
		return admPuInformation;
	}

	public void setAdmPuInformation(List<AdmPuInformation> admPuInformation) {
		this.admPuInformation = admPuInformation;
	}

	@Override
	public String toString() {
		return "Customs [cRCode=" + cRCode + ", socialCreditCode="
				+ socialCreditCode + ", customsName=" + customsName
				+ ", recordDate=" + recordDate + ", barCode=" + barCode
				+ ", bCName=" + bCName + ", regAddress=" + regAddress
				+ ", admDivision=" + admDivision + ", ecoReg=" + ecoReg
				+ ", economicCategory=" + economicCategory + ", busType="
				+ busType + ", businessLine=" + businessLine
				+ ", customsValidity=" + customsValidity + ", customsMarks="
				+ customsMarks + ", annualReport=" + annualReport
				+ ", creditRate=" + creditRate + ", admPuInformation="
				+ admPuInformation + "]";
	}

}
