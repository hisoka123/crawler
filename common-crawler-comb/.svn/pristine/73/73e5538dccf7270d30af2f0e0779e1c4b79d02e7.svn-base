/**
 * 
 */
package com.crawler.pbccrc.domain.json;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author kingly
 * @date 2016年1月6日
 * 
 */
public class CreditRecord implements Serializable {
	private static final long serialVersionUID = -4785449156703737264L;
	private CreditSummary creditSummary; 		//信息概要
	private List<CreditCardDetail> creditCardDetails; 	//信用卡明细
	private List<Map<String, Object>> parsedCreditCardDetails; //被解析过的信用卡明细
	private List<HousingLoanDetail> housingLoanDetails; //住房贷款明细
	private List<OtherLoanDetail> otherLoanDetails; 	//其他贷款明细
	private List<Map<String, Object>> parsedLoanDetails; //被解析过的贷款明细
	private Map<String, String> guaranteeInfoDetails; //为他人担保信息  key:序号  value:内容
	private List<Map<String, Object>> parsedGuaranteeInfoDetails; //被解析过的 为他人担保信息
	
	public CreditSummary getCreditSummary() {
		return creditSummary;
	}
	public void setCreditSummary(CreditSummary creditSummary) {
		this.creditSummary = creditSummary;
	}
	public List<CreditCardDetail> getCreditCardDetails() {
		return creditCardDetails;
	}
	public void setCreditCardDetails(List<CreditCardDetail> creditCardDetails) {
		this.creditCardDetails = creditCardDetails;
	}
	public List<Map<String, Object>> getParsedCreditCardDetails() {
		return parsedCreditCardDetails;
	}
	public void setParsedCreditCardDetails(
			List<Map<String, Object>> parsedCreditCardDetails) {
		this.parsedCreditCardDetails = parsedCreditCardDetails;
	}
	public List<HousingLoanDetail> getHousingLoanDetails() {
		return housingLoanDetails;
	}
	public void setHousingLoanDetails(List<HousingLoanDetail> housingLoanDetails) {
		this.housingLoanDetails = housingLoanDetails;
	}
	public List<OtherLoanDetail> getOtherLoanDetails() {
		return otherLoanDetails;
	}
	public void setOtherLoanDetails(List<OtherLoanDetail> otherLoanDetails) {
		this.otherLoanDetails = otherLoanDetails;
	}
	public List<Map<String, Object>> getParsedLoanDetails() {
		return parsedLoanDetails;
	}
	public void setParsedLoanDetails(List<Map<String, Object>> parsedLoanDetails) {
		this.parsedLoanDetails = parsedLoanDetails;
	}
	public Map<String, String> getGuaranteeInfoDetails() {
		return guaranteeInfoDetails;
	}
	public void setGuaranteeInfoDetails(Map<String, String> guaranteeInfoDetails) {
		this.guaranteeInfoDetails = guaranteeInfoDetails;
	}
	public List<Map<String, Object>> getParsedGuaranteeInfoDetails() {
		return parsedGuaranteeInfoDetails;
	}
	public void setParsedGuaranteeInfoDetails(
			List<Map<String, Object>> parsedGuaranteeInfoDetails) {
		this.parsedGuaranteeInfoDetails = parsedGuaranteeInfoDetails;
	}
	
	@Override
	public String toString() {
		return "CreditRecord [creditSummary=" + creditSummary
				+ ", creditCardDetails=" + creditCardDetails
				+ ", parsedCreditCardDetails=" + parsedCreditCardDetails
				+ ", housingLoanDetails=" + housingLoanDetails
				+ ", otherLoanDetails=" + otherLoanDetails
				+ ", parsedLoanDetails=" + parsedLoanDetails
				+ ", guaranteeInfoDetails=" + guaranteeInfoDetails
				+ ", parsedGuaranteeInfoDetails=" + parsedGuaranteeInfoDetails
				+ "]";
	}
}
