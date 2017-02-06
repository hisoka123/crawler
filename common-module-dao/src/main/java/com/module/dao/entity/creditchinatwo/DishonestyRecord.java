package com.module.dao.entity.creditchinatwo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 失信记录
 */
@Entity
@Table(name = "x_dishonesty_record")
@NamedQuery(name = "DishonestyRecord.findAll", query = "SELECT d FROM DishonestyRecord d")
public class DishonestyRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4216675145374471835L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;// 主键
	@Column(length = 1000)
	private String inforSource;// 信息来源
	private String releaseTime;// 发布时间
	private String companyName;// 企业/自然人名称
	private String codeNumber;// 组织机构代码/身份证号
	private String caseNumber;// 案号
	private String filingTime;// 立案时间
	private String sex;// 性别
	private String age;// 年龄
	private String exeCourt;// 执行法院
	private String province;// 省份
	@Column(length = 4000)
	private String performNum;// 执行依据文号
	private String performUnit;// 作出执行依据单位
	@Column(length = 4000)
	private String lawEffect;// 法律生效文书确定的义务
	@Column(length = 4000)
	private String execution;// 被执行人的履行情况
	@Column(length = 4000)
	private String situation;// 失信被执行人具体情形
	private String legalPerson;// 法定代表人或者负责人姓名
	@Column(length = 2000)
	private String executed;// 已履行
	@Column(length = 2000)
	private String nonperforming;// 未履行
	@ManyToOne
	@JoinColumn(name = "company_record_id")
	private CompanyRecord companyRecord;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInforSource() {
		return inforSource;
	}

	public void setInforSource(String inforSource) {
		this.inforSource = inforSource;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getFilingTime() {
		return filingTime;
	}

	public void setFilingTime(String filingTime) {
		this.filingTime = filingTime;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getExeCourt() {
		return exeCourt;
	}

	public void setExeCourt(String exeCourt) {
		this.exeCourt = exeCourt;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPerformNum() {
		return performNum;
	}

	public void setPerformNum(String performNum) {
		this.performNum = performNum;
	}

	public String getPerformUnit() {
		return performUnit;
	}

	public void setPerformUnit(String performUnit) {
		this.performUnit = performUnit;
	}

	public String getLawEffect() {
		return lawEffect;
	}

	public void setLawEffect(String lawEffect) {
		this.lawEffect = lawEffect;
	}

	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getExecuted() {
		return executed;
	}

	public void setExecuted(String executed) {
		this.executed = executed;
	}

	public String getNonperforming() {
		return nonperforming;
	}

	public void setNonperforming(String nonperforming) {
		this.nonperforming = nonperforming;
	}

	public CompanyRecord getCompanyRecord() {
		return companyRecord;
	}

	public void setCompanyRecord(CompanyRecord companyRecord) {
		this.companyRecord = companyRecord;
	}

	@Override
	public String toString() {
		return "DishonestyRecord [id=" + id + ", inforSource=" + inforSource
				+ ", releaseTime=" + releaseTime + ", companyName="
				+ companyName + ", codeNumber=" + codeNumber + ", caseNumber="
				+ caseNumber + ", filingTime=" + filingTime + ", sex=" + sex
				+ ", age=" + age + ", exeCourt=" + exeCourt + ", province="
				+ province + ", performNum=" + performNum + ", performUnit="
				+ performUnit + ", lawEffect=" + lawEffect + ", execution="
				+ execution + ", situation=" + situation + ", legalPerson="
				+ legalPerson + ", executed=" + executed + ", nonperforming="
				+ nonperforming + ", companyRecord=" + companyRecord + "]";
	}

}
