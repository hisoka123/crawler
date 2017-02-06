package com.module.dao.entity.creditchinatwo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "x_company_record")
@NamedQuery(name = "CompanyRecord.findAll", query = "SELECT x FROM CompanyRecord x")
public class CompanyRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1966036164267416675L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// 主键
	private Date executetime;// 数据录入时间
	private String name;// 企业或个人名称
	private String objectType;// 主体类型 (法人或自然人)===2代表法人1代表自然人
	private Integer badCount;// 不良记录数
	private Integer dishonestyCount;// 失信记录数
	@OneToMany(mappedBy = "companyRecord", cascade = CascadeType.ALL)
	private Set<BadRecord> badRecords;
	@OneToMany(mappedBy = "companyRecord", cascade = CascadeType.ALL)
	private Set<DishonestyRecord> dishonestyRecords;
	@ManyToOne
	@JoinColumn(name = "credit_company_id")
	private CreditCompany creditCompany;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Integer getBadCount() {
		return badCount;
	}

	public void setBadCount(Integer badCount) {
		this.badCount = badCount;
	}

	public Integer getDishonestyCount() {
		return dishonestyCount;
	}

	public void setDishonestyCount(Integer dishonestyCount) {
		this.dishonestyCount = dishonestyCount;
	}

	public Set<BadRecord> getBadRecords() {
		return badRecords;
	}

	public void setBadRecords(Set<BadRecord> badRecords) {
		this.badRecords = badRecords;
	}

	public Set<DishonestyRecord> getDishonestyRecords() {
		return dishonestyRecords;
	}

	public void setDishonestyRecords(Set<DishonestyRecord> dishonestyRecords) {
		this.dishonestyRecords = dishonestyRecords;
	}

	public CreditCompany getCreditCompany() {
		return creditCompany;
	}

	public void setCreditCompany(CreditCompany creditCompany) {
		this.creditCompany = creditCompany;
	}

	@Override
	public String toString() {
		return "CompanyRecord [id=" + id + ", executetime=" + executetime
				+ ", name=" + name + ", objectType=" + objectType
				+ ", badCount=" + badCount + ", dishonestyCount="
				+ dishonestyCount + ", badRecords=" + badRecords
				+ ", dishonestyRecords=" + dishonestyRecords
				+ ", creditCompany=" + creditCompany + "]";
	}

}
