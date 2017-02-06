package com.crawler.shixin.domain.json;

import java.io.Serializable;
/**

* 失信网bean

* @author dyx

*/
public class ShiXinJson implements Serializable{

	private static final long serialVersionUID = 1L;
	private String iname;//姓名
	private String sexy;//性别
	private String age;//年龄
	private String cardNum;//身份证号码/组织机构代码
	private String courtName;//执行法院
	private String areaName;//省份
	private String gistId;//执行依据文号
	private String regDate;//立案时间
	private String caseCode;//案号
	private String gistUnit;//做出执行依据单位
	private String duty;//生效法律文书确定的义务
	private String performance;//被执行人的履行情况
	private String disruptTypeName;//失信被执行人行为具体情形
	private String publishDate;//发布时间
	private String name;//数据库返回前端页面用（姓名）
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getSexy() {
		return sexy;
	}
	public void setSexy(String sexy) {
		this.sexy = sexy;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getGistId() {
		return gistId;
	}
	public void setGistId(String gistId) {
		this.gistId = gistId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getGistUnit() {
		return gistUnit;
	}
	public void setGistUnit(String gistUnit) {
		this.gistUnit = gistUnit;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getDisruptTypeName() {
		return disruptTypeName;
	}
	public void setDisruptTypeName(String disruptTypeName) {
		this.disruptTypeName = disruptTypeName;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
