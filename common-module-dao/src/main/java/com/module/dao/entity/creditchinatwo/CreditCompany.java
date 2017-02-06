package com.module.dao.entity.creditchinatwo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "credit_company", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"name", "objectType" }) })
@NamedQuery(name = "CreditCompany.findAll", query = "SELECT cc FROM CreditCompany cc")
public class CreditCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -287964393693976169L;

	public CreditCompany() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// 主键
	private String name;// 企业或个人名称
	private String objectType;// 主体类型 (法人或自然人)===2代表法人1代表自然人
	@Column(columnDefinition = "int default 0")
	private int state;// 状态
	@Column(columnDefinition = "int default 0")
	private Integer num;// 数据库存储的版本数量（次数）
	@Column(name = "total_num", columnDefinition = "int default 0")
	private Integer totalNum;// 表示一个企业总共被爬取了多少次
	@Column(columnDefinition = "int default 0")
	private int priority;// 优先级数字越大，爬取优先级越高（默认为0）
	@OneToMany(mappedBy = "creditCompany", cascade = CascadeType.ALL)
	private Set<CompanyRecord> companyRecords;
	@OneToMany(mappedBy = "creditCompany", cascade = CascadeType.ALL)
	private Set<BDResultJson> bDResultJsons;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Set<CompanyRecord> getCompanyRecords() {
		return companyRecords;
	}

	public void setCompanyRecords(Set<CompanyRecord> companyRecords) {
		this.companyRecords = companyRecords;
	}

	public Set<BDResultJson> getbDResultJsons() {
		return bDResultJsons;
	}

	public void setbDResultJsons(Set<BDResultJson> bDResultJsons) {
		this.bDResultJsons = bDResultJsons;
	}

	@Override
	public String toString() {
		return "CreditCompany [id=" + id + ", name=" + name + ", objectType="
				+ objectType + ", state=" + state + ", num=" + num
				+ ", totalNum=" + totalNum + ", priority=" + priority
				+ ", companyRecords=" + companyRecords + ", bDResultJsons="
				+ bDResultJsons + "]";
	}

}
