package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年4月20日
 * 工商公示信息->登记信息->股东信息->股东及出资信息
 */
public class AicpubRegStohrStohrinvestInfo implements Serializable { //GsgsDjGdGdjczInfo
	private static final long serialVersionUID = 5754434566409512909L;

	//股东
	private String stockholder;
	
	//发起人类型
	private String stockType;
	
	//认缴额（万元）
	private String subAmount;
	
	//实缴额（万元）
	private String paidAmount;
	
	//认缴明细 和 实缴明细
	public class AmountDetail {
		//出资方式
		public String investMethod; //method;
		//出资额（万元）
		public String investAmount; //amount;
		//出资日期
		public String investDateTime; //czDate;
		public String getInvestMethod() {
			return investMethod;
		}
		public void setInvestMethod(String investMethod) {
			this.investMethod = investMethod;
		}
		public String getInvestAmount() {
			return investAmount;
		}
		public void setInvestAmount(String investAmount) {
			this.investAmount = investAmount;
		}
		public String getInvestDateTime() {
			return investDateTime;
		}
		public void setInvestDateTime(String investDateTime) {
			this.investDateTime = investDateTime;
		}
		
		
		
		
	}
	private List<AmountDetail> subAmountDetails;
	private List<AmountDetail> paidAmountDetails;
	
	//html内容
	private String html;

	public String getStockholder() {
		return stockholder;
	}

	public void setStockholder(String stockholder) {
		this.stockholder = stockholder;
	}
	
	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getSubAmount() {
		return subAmount;
	}

	public void setSubAmount(String subAmount) {
		this.subAmount = subAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public List<AmountDetail> getSubAmountDetails() {
		return subAmountDetails;
	}

	public void setSubAmountDetails(List<AmountDetail> subAmountDetails) {
		this.subAmountDetails = subAmountDetails;
	}

	public List<AmountDetail> getPaidAmountDetails() {
		return paidAmountDetails;
	}

	public void setPaidAmountDetails(List<AmountDetail> paidAmountDetails) {
		this.paidAmountDetails = paidAmountDetails;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "AicpubRegStohrStohrinvestInfo [stockholder=" + stockholder				
				+ ", stockType=" + stockType+ ", subAmount=" + subAmount  + ", paidAmount=" + paidAmount
				+ ", subAmountDetails=" + subAmountDetails
				+ ", paidAmountDetails=" + paidAmountDetails + ", html=" + html
				+ "]";
	}
}
