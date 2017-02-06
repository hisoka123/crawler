/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示->企业年报->对外提供保证担保信息
 */
public class EntpubAnnreportExtguaranteeInfo implements Serializable { //QygsQynbDwtgbzdbInfo
	private static final long serialVersionUID = 6789960983028566211L;
	
	//债权人
	private String creditor;
	//债务人
	private String debtor;
	//主债权种类
	private String priCredRightType;
	//主债权数额
	private String priCredRightAmount;
	//履行债务的期限
	private String exeDebtDeadline; //ExeDebtDeadline;
	//保证的期间
	private String guaranteePeriod;
	//保证的方式
	private String guaranteeMethod;
	//保证担保的范围
	private String guaranteeScope;
	//html内容
	private String html;
	public String getCreditor() {
		return creditor;
	}
	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}
	public String getDebtor() {
		return debtor;
	}
	public void setDebtor(String debtor) {
		this.debtor = debtor;
	}
	public String getPriCredRightType() {
		return priCredRightType;
	}
	public void setPriCredRightType(String priCredRightType) {
		this.priCredRightType = priCredRightType;
	}
	public String getPriCredRightAmount() {
		return priCredRightAmount;
	}
	public void setPriCredRightAmount(String priCredRightAmount) {
		this.priCredRightAmount = priCredRightAmount;
	}
	public String getExeDebtDeadline() {
		return exeDebtDeadline;
	}
	public void setExeDebtDeadline(String exeDebtDeadline) {
		this.exeDebtDeadline = exeDebtDeadline;
	}
	public String getGuaranteePeriod() {
		return guaranteePeriod;
	}
	public void setGuaranteePeriod(String guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}
	public String getGuaranteeMethod() {
		return guaranteeMethod;
	}
	public void setGuaranteeMethod(String guaranteeMethod) {
		this.guaranteeMethod = guaranteeMethod;
	}
	public String getGuaranteeScope() {
		return guaranteeScope;
	}
	public void setGuaranteeScope(String guaranteeScope) {
		this.guaranteeScope = guaranteeScope;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "EntpubAnnreportExtguaranteeInfo [creditor=" + creditor
				+ ", debtor=" + debtor + ", priCredRightType="
				+ priCredRightType + ", priCredRightAmount="
				+ priCredRightAmount + ", exeDebtDeadline=" + exeDebtDeadline
				+ ", guaranteePeriod=" + guaranteePeriod + ", guaranteeMethod="
				+ guaranteeMethod + ", guaranteeScope=" + guaranteeScope
				+ ", html=" + html + "]";
	}
}
