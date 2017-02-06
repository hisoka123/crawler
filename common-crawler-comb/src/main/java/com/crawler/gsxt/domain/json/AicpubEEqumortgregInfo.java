package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->股权出质登记信息->股权出质登记信息
 */
public class AicpubEEqumortgregInfo implements Serializable { //GsgsGqczdjGqczdjInfo
	private static final long serialVersionUID = -9171333649006347318L;

	//登记编号
	private String regNum;
	//出质人
	private String mortgagorName; //czr;
	//证照/证件号码（出质人）
	private String mortgagorIdNum; //czrIdNum;
	//出质股权数额
	private String mortgAmount; //czgqAmount;
	//质权人
	private String mortgageeName; //zqr;
	//证照/证件号码
	private String mortgageeIdNum; //zqrIdNum;
	//股权出质设立登记日期
	private String regDateTime; //gqczsldjDate;
	//状态
	private String status;
	//公示时间
	private String pubDate;
	//变化情况
	private String changeSitu;
	//html内容
	private String html;
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getMortgagorName() {
		return mortgagorName;
	}
	public void setMortgagorName(String mortgagorName) {
		this.mortgagorName = mortgagorName;
	}
	public String getMortgagorIdNum() {
		return mortgagorIdNum;
	}
	public void setMortgagorIdNum(String mortgagorIdNum) {
		this.mortgagorIdNum = mortgagorIdNum;
	}
	public String getMortgAmount() {
		return mortgAmount;
	}
	public void setMortgAmount(String mortgAmount) {
		this.mortgAmount = mortgAmount;
	}
	public String getMortgageeName() {
		return mortgageeName;
	}
	public void setMortgageeName(String mortgageeName) {
		this.mortgageeName = mortgageeName;
	}
	public String getMortgageeIdNum() {
		return mortgageeIdNum;
	}
	public void setMortgageeIdNum(String mortgageeIdNum) {
		this.mortgageeIdNum = mortgageeIdNum;
	}
	public String getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(String regDateTime) {
		this.regDateTime = regDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getChangeSitu() {
		return changeSitu;
	}
	public void setChangeSitu(String changeSitu) {
		this.changeSitu = changeSitu;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	@Override
	public String toString() {
		return "AicpubEEqumortgregInfo [regNum=" + regNum + ", mortgagorName="
				+ mortgagorName + ", mortgagorIdNum=" + mortgagorIdNum
				+ ", mortgAmount=" + mortgAmount + ", mortgageeName="
				+ mortgageeName + ", mortgageeIdNum=" + mortgageeIdNum
				+ ", regDateTime=" + regDateTime + ", status=" + status
				+ ", pubDate=" + pubDate + ", changeSitu=" + changeSitu
				+ ", html=" + html + "]";
	}
	
}
