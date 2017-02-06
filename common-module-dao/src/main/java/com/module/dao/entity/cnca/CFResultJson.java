package com.module.dao.entity.cnca;

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
@Table(name = "rz_result_json")
@NamedQuery(name = "CFResultJson.findAll", query = "SELECT cf FROM CFResultJson cf")
public class CFResultJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5071639968272684384L;

	public CFResultJson() {
		super();
	}

	public CFResultJson(String cname, String result, Date executetime) {
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
	@JoinColumn(name = "certificate_company_id")
	private CertificateCompany certificateCompany;

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

	public CertificateCompany getCertificateCompany() {
		return certificateCompany;
	}

	public void setCertificateCompany(CertificateCompany certificateCompany) {
		this.certificateCompany = certificateCompany;
	}

	@Override
	public String toString() {
		return "CCResultJson [id=" + id + ", cname=" + cname + ", result="
				+ result + ", executetime=" + executetime
				+ ", certificateCompany=" + certificateCompany + "]";
	}

}