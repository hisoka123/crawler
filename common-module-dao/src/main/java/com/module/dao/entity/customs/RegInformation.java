package com.module.dao.entity.customs;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "h_reg_information")
@NamedQuery(name = "RegInformation.findAll", query = "SELECT cu FROM RegInformation cu")
public class RegInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7719802654424617295L;

	public RegInformation() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// 主键
	private String customsRegNo;// 海关注册编码
	private String socialCreditNo;// 社会信用代码
	private String regDate;// 注册日期
	private String barCode;// 组织机构代码
	private String chineseCompanyName;// 企业中文名称
	private String regCustoms;// 注册海关
	private String regAddress;// 工商注册地址
	private String admDivision;// 行政区划
	private String enconomicDivision;// 经济区划
	private String enconomicType;// 经济类型
	private String businessType;// 经营类别
	private String businessLine;// 行业种类
	private String customsValidity;// 报关有效期
	private String customsCancelMarks;// 海关注销标志
	private String annualReport;// 年报情况
	private Date executetime;// 数据录入时间
	@OneToMany(mappedBy = "regInformation", cascade = CascadeType.ALL)
	private Set<CreditLevel> creditLevels;// 信用等级
	@OneToMany(mappedBy = "regInformation", cascade = CascadeType.ALL)
	private Set<PunishInformation> punishInformations;// 行政处罚信息
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "customs_company_id")
	private CustomsCompany customsCompany;// 关键字

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomsRegNo() {
		return customsRegNo;
	}

	public void setCustomsRegNo(String customsRegNo) {
		this.customsRegNo = customsRegNo;
	}

	public String getSocialCreditNo() {
		return socialCreditNo;
	}

	public void setSocialCreditNo(String socialCreditNo) {
		this.socialCreditNo = socialCreditNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getChineseCompanyName() {
		return chineseCompanyName;
	}

	public void setChineseCompanyName(String chineseCompanyName) {
		this.chineseCompanyName = chineseCompanyName;
	}

	public String getRegCustoms() {
		return regCustoms;
	}

	public void setRegCustoms(String regCustoms) {
		this.regCustoms = regCustoms;
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

	public String getEnconomicDivision() {
		return enconomicDivision;
	}

	public void setEnconomicDivision(String enconomicDivision) {
		this.enconomicDivision = enconomicDivision;
	}

	public String getEnconomicType() {
		return enconomicType;
	}

	public void setEnconomicType(String enconomicType) {
		this.enconomicType = enconomicType;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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

	public String getCustomsCancelMarks() {
		return customsCancelMarks;
	}

	public void setCustomsCancelMarks(String customsCancelMarks) {
		this.customsCancelMarks = customsCancelMarks;
	}

	public String getAnnualReport() {
		return annualReport;
	}

	public void setAnnualReport(String annualReport) {
		this.annualReport = annualReport;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public Set<CreditLevel> getCreditLevels() {
		return creditLevels;
	}

	public void setCreditLevels(Set<CreditLevel> creditLevels) {
		this.creditLevels = creditLevels;
	}

	public Set<PunishInformation> getPunishInformations() {
		return punishInformations;
	}

	public void setPunishInformations(Set<PunishInformation> punishInformations) {
		this.punishInformations = punishInformations;
	}

	public CustomsCompany getCustomsCompany() {
		return customsCompany;
	}

	public void setCustomsCompany(CustomsCompany customsCompany) {
		this.customsCompany = customsCompany;
	}

	@Override
	public String toString() {
		return "RegInformation [id=" + id + ", customsRegNo=" + customsRegNo
				+ ", socialCreditNo=" + socialCreditNo + ", regDate=" + regDate
				+ ", barCode=" + barCode + ", chineseCompanyName="
				+ chineseCompanyName + ", regCustoms=" + regCustoms
				+ ", regAddress=" + regAddress + ", admDivision=" + admDivision
				+ ", enconomicDivision=" + enconomicDivision
				+ ", enconomicType=" + enconomicType + ", businessType="
				+ businessType + ", businessLine=" + businessLine
				+ ", customsValidity=" + customsValidity
				+ ", customsCancelMarks=" + customsCancelMarks
				+ ", annualReport=" + annualReport + ", executetime="
				+ executetime + ", creditLevels=" + creditLevels
				+ ", punishInformations=" + punishInformations
				+ ", customsCompany=" + customsCompany + "]";
	}

}
