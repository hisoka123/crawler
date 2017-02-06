package com.module.dao.entity.iecms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="m_iecms_result")
@NamedQuery(name="Iecms.findAll", query="SELECT t FROM Iecms t")
public class Iecms implements Serializable {
	public Iecms(){}
	
	
	public Iecms(String businessChineseName, String businessEnglishName,
			String residence, String zipcode, String fax,
			String importExportEnterpriseCode, String unifiedSocialCreditCode) {
		super();
		this.businessChineseName = businessChineseName;
		this.businessEnglishName = businessEnglishName;
		this.residence = residence;
		this.zipcode = zipcode;
		this.fax = fax;
		this.importExportEnterpriseCode = importExportEnterpriseCode;
		this.unifiedSocialCreditCode = unifiedSocialCreditCode;
	}

	public Date getExecutetime() {
		return executetime;
	}
	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	private static final long serialVersionUID = -354061222770902777L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;//主键id
	
	private String businessChineseName;//经营者中文名称
	
	private String businessEnglishName;//经营者英文名称
	
	private String residence;//住所	
	
	private String zipcode;//邮编
	
	private String fax;//传真
	
	private Date executetime;//创建时间
	
	@Column(name="import_export_enterprise_code")
	private String importExportEnterpriseCode;//进出口企业代码
    
	@Column(name="unified_social_credit_code")
	private String unifiedSocialCreditCode;//统一社会信用代码
	
	public String getImportExportEnterpriseCode() {
		return importExportEnterpriseCode;
	}

	public void setImportExportEnterpriseCode(String importExportEnterpriseCode) {
		this.importExportEnterpriseCode = importExportEnterpriseCode;
	}

	public String getUnifiedSocialCreditCode() {
		return unifiedSocialCreditCode;
	}

	public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
		this.unifiedSocialCreditCode = unifiedSocialCreditCode;
	}


	
	@ManyToOne
	@JoinColumn(name="iecmscompany_id")
	private IecmsCompany iecmscompany;
	

	
	

	

	public IecmsCompany getIecmscompany() {
		return iecmscompany;
	}
	public void setIecmscompany(IecmsCompany iecmscompany) {
		this.iecmscompany = iecmscompany;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getBusinessChineseName() {
		return businessChineseName;
	}


	public void setBusinessChineseName(String businessChineseName) {
		this.businessChineseName = businessChineseName;
	}


	public String getBusinessEnglishName() {
		return businessEnglishName;
	}


	public void setBusinessEnglishName(String businessEnglishName) {
		this.businessEnglishName = businessEnglishName;
	}


	public String getResidence() {
		return residence;
	}


	public void setResidence(String residence) {
		this.residence = residence;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Override
	public String toString() {
		return "Iecms [id=" + id + ", businessChineseName="
				+ businessChineseName + ", businessEnglishName="
				+ businessEnglishName + ", residence=" + residence
				+ ", zipcode=" + zipcode + ", fax=" + fax + "]";
	}

}
