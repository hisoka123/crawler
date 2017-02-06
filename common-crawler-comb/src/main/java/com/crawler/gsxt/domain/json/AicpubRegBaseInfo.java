package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年4月20日
 * 工商公示信息->登记信息->基本信息
 */
public class AicpubRegBaseInfo implements Serializable { //GsgsDjJbInfo
	private static final long serialVersionUID = -8557571467478180312L;

	//注册号 或 信用代码
	private String num;
	
	//统一社会信用代码
	private String creditNum;
	
	//名称
	private String name;
	
	//类型
	private String type;
	
	//法定代表人/经营者
	private String legalRepr;
	
	//注册资本
	private String regCapital; //registeredCapital;
	
	//成立日期
	private String regDateTime; //registeredDate;
	
	//组成形式
	private String formType;  //新加（组成形式）
	
	//经营场所/住所
	private String address;
	
	//营业期限自（即营业开始日期）
	private String startDateTime; //startDate;
	
	//营业期限至（即营业结束日期）
	private String endDateTime; //endDate;
	
	//经营范围
	private String businessScope;
	
	//登记机关
	private String regAuthority;
	
	//核准日期
	private String approvalDateTime; //approvalDate;
	
	//登记状态
	private String regStatus;
	
	//吊销日期
	private String revokeDate;
	
	//html内容
	private String html;
    
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLegalRepr() {
		return legalRepr;
	}

	public void setLegalRepr(String legalRepr) {
		this.legalRepr = legalRepr;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public String getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(String regDateTime) {
		this.regDateTime = regDateTime;
	}

	
	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getRegAuthority() {
		return regAuthority;
	}

	public void setRegAuthority(String regAuthority) {
		this.regAuthority = regAuthority;
	}

	public String getApprovalDateTime() {
		return approvalDateTime;
	}

	public void setApprovalDateTime(String approvalDateTime) {
		this.approvalDateTime = approvalDateTime;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getRevokeDate() {
		return revokeDate;
	}

	public void setRevokeDate(String revokeDate) {
		this.revokeDate = revokeDate;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	public String getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}


	@Override
	public String toString() {
		return "AicpubRegBaseInfo [num=" + num + ", name=" + name + ", type="
				+ type + ", legalRepr=" + legalRepr + ", regCapital="
				+ regCapital + ", regDateTime=" + regDateTime+ ", formType="
				+ formType  + ", address="
				+ address + ", startDateTime=" + startDateTime
				+ ", endDateTime=" + endDateTime + ", businessScope="
				+ businessScope + ", regAuthority=" + regAuthority
				+ ", approvalDateTime=" + approvalDateTime + ", regStatus="
				+ regStatus + ", revokeDate=" + revokeDate + ", html=" + html
				+ "]";
	}
	
}
