package com.module.dao.entity.customs;

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
@Table(name = "h_result_json")
@NamedQuery(name = "CUResultJson.findAll", query = "SELECT cc FROM CUResultJson cc")
public class CUResultJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 344727245835238402L;

	public CUResultJson() {
		super();
	}

	public CUResultJson(String cname, String result, Date executetime) {
		super();
		this.cname = cname;
		this.result = result;
		this.executetime = executetime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cname;
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "text")
	private String result;
	private Date executetime;
	@ManyToOne
	@JoinColumn(name = "customs_company_id")
	private CustomsCompany customsCompany;

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

	public CustomsCompany getCustomsCompany() {
		return customsCompany;
	}

	public void setCustomsCompany(CustomsCompany customsCompany) {
		this.customsCompany = customsCompany;
	}

	@Override
	public String toString() {
		return "CUResultJson [id=" + id + ", cname=" + cname + ", result="
				+ result + ", executetime=" + executetime + ", customsCompany="
				+ customsCompany + "]";
	}

}