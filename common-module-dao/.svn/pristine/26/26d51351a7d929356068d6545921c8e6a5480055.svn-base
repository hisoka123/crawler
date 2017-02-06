package com.module.dao.entity.renfawang;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="r_people_court")
@NamedQuery(name="PeopleCourt.findAll", query="SELECT t FROM PeopleCourt t")
public class PeopleCourt implements Serializable {

	private static final long serialVersionUID = -3638074618985929224L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//被执行人姓名/名称
	private String pname;

	//身份证号/组织机构代码
	private String partyCardNum;
	
	//执行法院
	private String execCourtName;
	
	//立案时间
	private String caseCreateTime;
	
	//案号
	private String caseCode;
	
	//执行标的
	private String execMoney;
	
	//定时任务执行时间
	private Date executetime;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="company_or_identity_id")
	private CompanyOrID companyOrIdentity;
	
	public PeopleCourt(){}
	
	public PeopleCourt(String pname,String partyCardNum,String execCourtName,
			           String caseCreateTime,String caseCode,String execMoney){
		
		
		    this.pname=pname;
		    this.partyCardNum=partyCardNum;
		    this.execCourtName=execCourtName;
		    this.caseCreateTime=caseCreateTime;
		    this.caseCode=caseCode;
		    this.execMoney=execMoney;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPartyCardNum() {
		return partyCardNum;
	}

	public void setPartyCardNum(String partyCardNum) {
		this.partyCardNum = partyCardNum;
	}

	public String getExecCourtName() {
		return execCourtName;
	}

	public void setExecCourtName(String execCourtName) {
		this.execCourtName = execCourtName;
	}

	public String getCaseCreateTime() {
		return caseCreateTime;
	}

	public void setCaseCreateTime(String caseCreateTime) {
		this.caseCreateTime = caseCreateTime;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getExecMoney() {
		return execMoney;
	}

	public void setExecMoney(String execMoney) {
		this.execMoney = execMoney;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public CompanyOrID getCompanyOrIdentity() {
		return companyOrIdentity;
	}

	public void setCompanyOrIdentity(CompanyOrID companyOrIdentity) {
		this.companyOrIdentity = companyOrIdentity;
	}

	@Override
	public String toString() {
		return "PeopleCourt [id=" + id + ", pname=" + pname + ", partyCardNum="
				+ partyCardNum + ", execCourtName=" + execCourtName
				+ ", caseCreateTime=" + caseCreateTime + ", caseCode="
				+ caseCode + ", execMoney=" + execMoney + ", executetime="
				+ executetime + ", companyOrIdentity=" + companyOrIdentity
				+ "]";
	}

	
}
