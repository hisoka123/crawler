/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示信息->知识产权出质登记信息->知识产权出质登记信息
 */
public class EntpubIIntellectualproregInfo implements Serializable { //QygsZscqczdjZscqczdjInfo
	private static final long serialVersionUID = 2910875798849194312L;
	
	//注册号
	private String regNum;
	//名称
	private String name;
	//种类
	private String type;
	//出质人名称
	private String mortgagorName; //czrName;
	//质权人名称
	private String mortgageeName; //zqrName;
	//质权登记期限
	private String pledgeRegDeadline; //zqdjDeadline;
	//状态
	private String status;
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
	public String getMortgagorName() {
		return mortgagorName;
	}
	public void setMortgagorName(String mortgagorName) {
		this.mortgagorName = mortgagorName;
	}
	public String getMortgageeName() {
		return mortgageeName;
	}
	public void setMortgageeName(String mortgageeName) {
		this.mortgageeName = mortgageeName;
	}
	public String getPledgeRegDeadline() {
		return pledgeRegDeadline;
	}
	public void setPledgeRegDeadline(String pledgeRegDeadline) {
		this.pledgeRegDeadline = pledgeRegDeadline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "EntpubIIntellectualproregInfo [regNum=" + regNum + ", name="
				+ name + ", type=" + type + ", mortgagorName=" + mortgagorName
				+ ", mortgageeName=" + mortgageeName + ", pledgeRegDeadline="
				+ pledgeRegDeadline + ", status=" + status + ", changeSitu="
				+ changeSitu + ", html=" + html + "]";
	}

}
