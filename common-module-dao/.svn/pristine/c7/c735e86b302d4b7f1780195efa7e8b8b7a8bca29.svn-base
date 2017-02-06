package com.module.dao.entity.cnca;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "rz_certificate")
@NamedQuery(name = "Certificate.findAll", query = "SELECT cf FROM Certificate cf")
public class Certificate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 190194817850460954L;

	public Certificate() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// 主键
	private String certificateNo;// 证书编号
	private String certificateStatus;// 证书状态
	@Column(length = 4000)
	private String certificateBasis;// 认证依据的标准和技术要求
	@Column(length = 4000)
	private String businessScope;// 认证覆盖的业务范围
	private String dueDate;// 证书到期日期
	private String productCategory;// 产品类别
	private Date executetime;// 版本时间
	// @ManyToOne
	// @JoinColumn(name = "organization_id")
	// private Organization organization;// 外键
	@ManyToOne
	@JoinColumn(name = "certificate_company_id")
	private CertificateCompany certificateCompany;// 关键字

	public Certificate(String certificateNo, String certificateStatus,
			String certificateBasis, String businessScope, String dueDate,
			String productCategory) {
		super();
		this.certificateNo = certificateNo;
		this.certificateStatus = certificateStatus;
		this.certificateBasis = certificateBasis;
		this.businessScope = businessScope;
		this.dueDate = dueDate;
		this.productCategory = productCategory;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getCertificateStatus() {
		return certificateStatus;
	}

	public void setCertificateStatus(String certificateStatus) {
		this.certificateStatus = certificateStatus;
	}

	public String getCertificateBasis() {
		return certificateBasis;
	}

	public void setCertificateBasis(String certificateBasis) {
		this.certificateBasis = certificateBasis;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
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
		return "Certificate [id=" + id + ", certificateNo=" + certificateNo
				+ ", certificateStatus=" + certificateStatus
				+ ", certificateBasis=" + certificateBasis + ", businessScope="
				+ businessScope + ", dueDate=" + dueDate + ", productCategory="
				+ productCategory + ", executetime=" + executetime
				+ ", certificateCompany=" + certificateCompany + "]";
	}

}
