package com.module.dao.entity.customs;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "h_credit_level")
@NamedQuery(name = "CreditLevel.findAll", query = "SELECT cu FROM CreditLevel cu")
public class CreditLevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2641208439998444507L;

	public CreditLevel() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// 主键
	private String identifyDate;// 认定时间
	private String legalNo;// 法律文书编号
	private String creditLevel;// 信用等级
	@ManyToOne
	@JoinColumn(name = "reginformation_id")
	private RegInformation regInformation;// 外键

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentifyDate() {
		return identifyDate;
	}

	public void setIdentifyDate(String identifyDate) {
		this.identifyDate = identifyDate;
	}

	public String getLegalNo() {
		return legalNo;
	}

	public void setLegalNo(String legalNo) {
		this.legalNo = legalNo;
	}

	public String getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}

	public RegInformation getRegInformation() {
		return regInformation;
	}

	public void setRegInformation(RegInformation regInformation) {
		this.regInformation = regInformation;
	}

	@Override
	public String toString() {
		return "CreditLevel [id=" + id + ", identifyDate=" + identifyDate
				+ ", legalNo=" + legalNo + ", creditLevel=" + creditLevel
				+ ", regInformation=" + regInformation + "]";
	}

}
