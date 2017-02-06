/**
 * 
 */
package com.module.dao.entity.pbccrc;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_loan_detail")
@NamedQuery(name="PbcLoanDetail.findAll", query="SELECT e FROM PbcLoanDetail e")
public class PbcLoanDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	/**
	 * currency : ¸öÈËÉÌÓÃ·¿£¨°üÀ¨ÉÌ×¡Á½ÓÃ£©´û¿î
	 * accountStatus : ÓâÆÚ
	 * overdueNo : 19
	 * isOverdue : ÊÇ
	 * issueDay : 2003.6.27
	 * abortDay :
	 * actualDay :
	 * conteactAmount : 100000
	 * loanBalance :
	 * overdueAmount :
	 * overdueForNo : 0
	 * settleDay : 2013.3
	 */

	@Column(name = "currency")
	private String currency;

	@Column(name = "account_status")
	private String accountStatus;

	@Column(name = "overdue_no")
	private String overdueNo;

	@Column(name = "is_overdue")
	private String isOverdue;

	@Column(name = "issue_day")
	private String issueDay;

	@Column(name = "abort_day")
	private String abortDay;

	@Column(name = "actual_day")
	private String actualDay;

	@Column(name = "conteact_amount")
	private String conteactAmount;

	@Column(name = "loan_balance")
	private String loanBalance;

	@Column(name = "overdue_amount")
	private String overdueAmount;

	@Column(name = "overdue_for_no")
	private String overdueForNo;

	@Column(name = "settle_day")
	private String settleDay;


	@ManyToOne
	@JoinColumn(name="json_id")
	private PlainPbccrcJson plainPbccrcJson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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

	public String getActualDay() {
		return actualDay;
	}

	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}

	public String getConteactAmount() {
		return conteactAmount;
	}

	public void setConteactAmount(String conteactAmount) {
		this.conteactAmount = conteactAmount;
	}

	public String getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(String loanBalance) {
		this.loanBalance = loanBalance;
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

	public String getSettleDay() {
		return settleDay;
	}

	public void setSettleDay(String settleDay) {
		this.settleDay = settleDay;
	}

	public PlainPbccrcJson getPlainPbccrcJson() {
		return plainPbccrcJson;
	}

	public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
		this.plainPbccrcJson = plainPbccrcJson;
	}
}
