/**
 * 
 */
package com.module.dao.entity.pbccrc;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_credit_card_detail")
@NamedQuery(name="PbcCreditCardDetail.findAll", query="SELECT e FROM PbcCreditCardDetail e")
public class PbcCreditCardDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * isQuasiCreditCard : 否
	 * accountStatus : 正常
	 * currency : 人民币
	 * overdueNo : 1
	 * isOverdue : 是
	 * issueDay : 2014.10.14
	 * abortDay : 2015.12
	 * limit : 10000
	 * usedLimit : 2274
	 * overdueAmount :
	 * overdueForNo : 0
	 * cancellationDay :
	 */

	@Column(name = "is_quasi_credit_card")
	private String isQuasiCreditCard;

	@Column(name = "account_status")
	private String accountStatus;

	@Column(name = "currency")
	private String currency;

	@Column(name = "overdue_no")
	private String overdueNo;

	@Column(name = "is_overdue")
	private String isOverdue;

	@Column(name = "issue_day")
	private String issueDay;

	@Column(name = "abort_day")
	private String abortDay;

	@Column(name = "f_limit")
	private String limit;

	@Column(name = "used_limit")
	private String usedLimit;

	@Column(name = "overdue_amount")
	private String overdueAmount;

	@Column(name = "overdue_for_no")
	private String overdueForNo;

	@Column(name = "cancellation_day")
	private String cancellationDay;

	@ManyToOne
	@JoinColumn(name="json_id")
	private PlainPbccrcJson plainPbccrcJson;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsQuasiCreditCard() {
		return isQuasiCreditCard;
	}

	public void setIsQuasiCreditCard(String isQuasiCreditCard) {
		this.isQuasiCreditCard = isQuasiCreditCard;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOverdueNo() {
		return overdueNo;
	}

	public void setOverdueNo(String overdueNo) {
		this.overdueNo = overdueNo;
	}

	public String getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}

	public String getIssueDay() {
		return issueDay;
	}

	public void setIssueDay(String issueDay) {
		this.issueDay = issueDay;
	}

	public String getAbortDay() {
		return abortDay;
	}

	public void setAbortDay(String abortDay) {
		this.abortDay = abortDay;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(String usedLimit) {
		this.usedLimit = usedLimit;
	}

	public String getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(String overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public String getOverdueForNo() {
		return overdueForNo;
	}

	public void setOverdueForNo(String overdueForNo) {
		this.overdueForNo = overdueForNo;
	}

	public String getCancellationDay() {
		return cancellationDay;
	}

	public void setCancellationDay(String cancellationDay) {
		this.cancellationDay = cancellationDay;
	}

	public PlainPbccrcJson getPlainPbccrcJson() {
		return plainPbccrcJson;
	}

	public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
		this.plainPbccrcJson = plainPbccrcJson;
	}
}
