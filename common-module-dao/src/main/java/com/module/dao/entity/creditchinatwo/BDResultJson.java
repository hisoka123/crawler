package com.module.dao.entity.creditchinatwo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "x_result_bd")
@NamedQuery(name = "BDResultJson.findAll", query = "SELECT cc FROM BDResultJson cc")
public class BDResultJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443803782657170413L;

	public BDResultJson() {
		super();
	}

	public BDResultJson(String cname, String objectType, String result,
			Date executetime) {
		super();
		this.cname = cname;
		this.objectType = objectType;
		this.result = result;
		this.executetime = executetime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cname;
	private String objectType;
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "text")
	private String result;
	private Date executetime;
	@ManyToOne
	@JoinColumn(name = "credit_company_id")
	private CreditCompany creditCompany;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public CreditCompany getCreditCompany() {
		return creditCompany;
	}

	public void setCreditCompany(CreditCompany creditCompany) {
		this.creditCompany = creditCompany;
	}

	@Override
	public String toString() {
		return "BDResultJson [id=" + id + ", cname=" + cname + ", objectType="
				+ objectType + ", result=" + result + ", executetime="
				+ executetime + ", creditCompany=" + creditCompany + "]";
	}

}