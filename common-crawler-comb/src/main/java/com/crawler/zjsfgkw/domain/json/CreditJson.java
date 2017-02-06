package com.crawler.zjsfgkw.domain.json;

import java.io.Serializable;
/**

* 浙法网曝光台信息查询bean

* @author dyx

*/
public class CreditJson implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;//姓名
	private String idNo;//证件号码
	private String address;//地址
	private String enforceBasis;//执行依据
	private String caseNo;//案号
	private String executReason;//执行案由
	private String court;//执行法院
	private String amountNotExecuted;//未执行金额
	private String caseDate;//立案日期
	private String targetAmount;//标的金额
	private String creditDate;//曝光日期
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEnforceBasis() {
		return enforceBasis;
	}
	public void setEnforceBasis(String enforceBasis) {
		this.enforceBasis = enforceBasis;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getExecutReason() {
		return executReason;
	}
	public void setExecutReason(String executReason) {
		this.executReason = executReason;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getAmountNotExecuted() {
		return amountNotExecuted;
	}
	public void setAmountNotExecuted(String amountNotExecuted) {
		this.amountNotExecuted = amountNotExecuted;
	}
	public String getCaseDate() {
		return caseDate;
	}
	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}
	public String getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(String targetAmount) {
		this.targetAmount = targetAmount;
	}
	public String getCreditDate() {
		return creditDate;
	}
	public void setCreditDate(String creditDate) {
		this.creditDate = creditDate;
	}


}
