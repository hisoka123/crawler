package com.crawler.iecms.domain.json;

import java.io.Serializable;
import java.util.Date;

public class IecmsJson implements Serializable {
	private static final long serialVersionUID = -354061222770902777L;

	private Long id;//主键id
	
	private String businessChineseName;//经营者中文名称
	
	private String businessEnglishName;//经营者英文名称
	
	private String residence;//住所	
	
	private String zipcode;//邮编
	
	private String fax;//邮编
	
	private String importExportEnterpriseCode;//进出口企业代码
    
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



	private Date executetime;//创建时间
	
	

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



	public Date getExecutetime() {
		return executetime;
	}



	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}



	@Override
	public String toString() {
		return "Iecms [id=" + id + ", businessChineseName="
				+ businessChineseName + ", businessEnglishName="
				+ businessEnglishName + ", residence=" + residence
				+ ", zipcode=" + zipcode + ", fax=" + fax + "]";
	}

}
