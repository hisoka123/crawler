/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * 企业公示->企业年报->生产经营情况
 */
public class EntpubAnnreportManageInfo implements Serializable {
	private static final long serialVersionUID = 2359853476547077103L;
	
	//营业额或营业收入
	private String saleSum;
	//纳税总额
	private String salarySum;
	
	//净利润
	private String netProfit;
	
	//html内容
	private String html;

	public String getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(String saleSum) {
		this.saleSum = saleSum;
	}

	public String getSalarySum() {
		return salarySum;
	}

	public void setSalarySum(String salarySum) {
		this.salarySum = salarySum;
	}

	
	public String getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}


	@Override
	public String toString() {
		return "QygsQynbScjyInfo [saleSum=" + saleSum + ", salarySum="+ salarySum  + "]";
	}
}
