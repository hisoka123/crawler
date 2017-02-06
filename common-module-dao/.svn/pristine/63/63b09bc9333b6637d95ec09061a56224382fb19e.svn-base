package com.module.dao.entity.zjsfgkw;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.module.dao.entity.qiyezhengxin.ZhengxinKeyword;


@Entity
@Table(name="g_zjsfgkw")
@SequenceGenerator(name="zjsfgkwSeq",sequenceName="zjsfgkw_sequence",allocationSize=1)
public class Zjsfgkw implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String caseState;//案件状态
	private String name;//姓名,当事人
	private String idNo;//证件号码
	private String address;//地址
	private String enforceBasis;//执行依据
	private String caseNo;//案号
	private String executReason;//执行案由
	private String court;//执行法院,法院
	private String amountNotExecuted;//未执行金额
	private String caseDate;//立案日期
	private String targetAmount;//标的金额
	private String creditDate;//曝光日期
	private Date executetime;//创建时间
	@ManyToOne
	@JoinColumn(name="zjsfgkwKeyword_id")
	private ZjsfgkwKeyword zjsfgkwKeyword;
	
	public String getCaseState() {
		return caseState;
	}
	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}
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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Date getExecutetime() {
		return executetime;
	}
	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}
	public ZjsfgkwKeyword getZjsfgkwKeyword() {
		return zjsfgkwKeyword;
	}
	public void setZjsfgkwKeyword(ZjsfgkwKeyword zjsfgkwKeyword) {
		this.zjsfgkwKeyword = zjsfgkwKeyword;
	}
	public Zjsfgkw(String caseState, String name, String idNo,
			String address, String enforceBasis, String caseNo,
			String executReason, String court, String amountNotExecuted,
			String caseDate, String targetAmount, String creditDate
			) {
		super();
		this.caseState = caseState;
		this.name = name;
		this.idNo = idNo;
		this.address = address;
		this.enforceBasis = enforceBasis;
		this.caseNo = caseNo;
		this.executReason = executReason;
		this.court = court;
		this.amountNotExecuted = amountNotExecuted;
		this.caseDate = caseDate;
		this.targetAmount = targetAmount;
		this.creditDate = creditDate;
	}

	public Zjsfgkw(){}
}
