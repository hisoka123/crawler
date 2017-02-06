/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示信息->股东及出资信息->股东及出资信息
 */
public class EntpubSStohrinvestInfo implements Serializable { //QygsGdjczGdjczInfo
	private static final long serialVersionUID = -4022261660433290319L;
	//股东
	private String stockholder;
	//投资人类型
	private String investorsType;
	public String getInvestorsType() {
		return investorsType;
	}
	public void setInvestorsType(String investorsType) {
		this.investorsType = investorsType;
	}
	//认缴额（万元）
	private String subAmount; //rjAmount;
	//实缴额（万元）
	private String paidAmount; //sjAmount;
	//html内容
	private String html;

	//认缴明细  和  实缴明细
	public class Detail {
		//出资方式
		public String method;
		//出资额（万元）
		public String amount;
		//出资日期
		public String dateTime; //date;		
		//公示日期
		public String showDate;//新加
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		public String getShowDate() {
			return showDate;
		}
		public void setShowDate(String showDate) {
			this.showDate = showDate;
		}

		@Override
		public String toString() {
			return "Detail{" +
					"method='" + method + '\'' +
					", amount='" + amount + '\'' +
					", dateTime='" + dateTime + '\'' +
					", showDate='" + showDate + '\'' +
					'}';
		}
	}
	private List<Detail> subDetails; //rjDetail;
	private List<Detail> paidDetails; //sjDetail;
	public String getStockholder() {
		return stockholder;
	}
	public void setStockholder(String stockholder) {
		this.stockholder = stockholder;
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
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public List<Detail> getSubDetails() {
		return subDetails;
	}
	public void setSubDetails(List<Detail> subDetails) {
		this.subDetails = subDetails;
	}
	public List<Detail> getPaidDetails() {
		return paidDetails;
	}
	public void setPaidDetails(List<Detail> paidDetails) {
		this.paidDetails = paidDetails;
	}
	@Override
	public String toString() {
		return "EntpubSStohrinvestInfo [stockholder=" + stockholder
				+ ", subAmount=" + subAmount + ", paidAmount=" + paidAmount
				+ ", html=" + html + ", subDetails=" + subDetails
				+ ", paidDetails=" + paidDetails + "]";
	}
	
}
