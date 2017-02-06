/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示->企业年报->企业资产状况信息
 */
public class EntpubAnnreportAssetInfo implements Serializable { //QygsQynbQyzczkInfo
	private static final long serialVersionUID = -7761602910821655324L;
	
	//资产总额
	private String assetAmount;
	//负债总额
	private String liabilityAmount;
	//销售总额
	private String salesAmount;
	//利润总额
	private String profitAmount;
	//销售总额中主营业务收入
	private String priBusiIncomeInSalesAmount; //xszezzyywsr;
	//净利润
	private String netProfit;
	//纳税总额
	private String taxesAmount;
	//所有者权益合计
	private String totalEquity; //syzqyhj;
	//获得政府扶持资金、补助
	private String governmentFunds; 
	//金融贷款
	private String financialLoan; 
	//html内容
	private String html;
	public String getAssetAmount() {
		return assetAmount;
	}
	public void setAssetAmount(String assetAmount) {
		this.assetAmount = assetAmount;
	}
	public String getLiabilityAmount() {
		return liabilityAmount;
	}
	public void setLiabilityAmount(String liabilityAmount) {
		this.liabilityAmount = liabilityAmount;
	}
	public String getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(String salesAmount) {
		this.salesAmount = salesAmount;
	}
	public String getProfitAmount() {
		return profitAmount;
	}
	public void setProfitAmount(String profitAmount) {
		this.profitAmount = profitAmount;
	}
	public String getPriBusiIncomeInSalesAmount() {
		return priBusiIncomeInSalesAmount;
	}
	public void setPriBusiIncomeInSalesAmount(String priBusiIncomeInSalesAmount) {
		this.priBusiIncomeInSalesAmount = priBusiIncomeInSalesAmount;
	}
	public String getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}
	public String getTaxesAmount() {
		return taxesAmount;
	}
	public void setTaxesAmount(String taxesAmount) {
		this.taxesAmount = taxesAmount;
	}
	public String getTotalEquity() {
		return totalEquity;
	}
	public void setTotalEquity(String totalEquity) {
		this.totalEquity = totalEquity;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getGovernmentFunds() {
		return governmentFunds;
	}
	public void setGovernmentFunds(String governmentFunds) {
		this.governmentFunds = governmentFunds;
	}
	public String getFinancialLoan() {
		return financialLoan;
	}
	public void setFinancialLoan(String financialLoan) {
		this.financialLoan = financialLoan;
	}
	@Override
	public String toString() {
		return "EntpubAnnreportAssetInfo [assetAmount=" + assetAmount
				+ ", liabilityAmount=" + liabilityAmount + ", salesAmount="
				+ salesAmount + ", profitAmount=" + profitAmount
				+ ", priBusiIncomeInSalesAmount=" + priBusiIncomeInSalesAmount
				+ ", netProfit=" + netProfit + ", taxesAmount=" + taxesAmount
				+ ", totalEquity=" + totalEquity + ", html=" + html + "]";
	}
}
