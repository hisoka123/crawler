package com.module.dao.entity.crqp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "j_crqp_result")
@NamedQuery(name = "CreditrRecordQueryPlatform.findAll", query = "SELECT t FROM CreditrRecordQueryPlatform t")
public class CreditrRecordQueryPlatform implements Serializable {

	public CreditrRecordQueryPlatform() {
		super();
	}

	public CreditrRecordQueryPlatform(String companyTitle,
			String keywordDescription, String content) {
		super();
		this.companyTitle = companyTitle;
		this.keywordDescription = keywordDescription;
		this.content = content;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// 主键id

	private String companyTitle;// 企业标题

	private String keywordDescription;// 关键字描述

	private String content;// 内容

	private Date executetime;// 创建时间

	@ManyToOne
	@JoinColumn(name = "creditrrecordqueryplatformcompany_id")
	private CreditrRecordQueryPlatformCompany creditrrecordqueryplatformcompany;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyTitle() {
		return companyTitle;
	}

	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
	}

	public String getKeywordDescription() {
		return keywordDescription;
	}

	public void setKeywordDescription(String keywordDescription) {
		this.keywordDescription = keywordDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CreditrRecordQueryPlatformCompany getCreditrrecordqueryplatformcompany() {
		return creditrrecordqueryplatformcompany;
	}

	public void setCreditrrecordqueryplatformcompany(
			CreditrRecordQueryPlatformCompany creditrrecordqueryplatformcompany) {
		this.creditrrecordqueryplatformcompany = creditrrecordqueryplatformcompany;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

}
