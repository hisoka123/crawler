package com.module.dao.entity.cnca;

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
@Table(name = "certificate_company", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@NamedQuery(name = "CertificateCompany.findAll", query = "SELECT cf FROM CertificateCompany cf")
public class CertificateCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 988229757870839130L;

	public CertificateCompany() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// 主键
	private String name;// 企业或个人名称
	private int state;// 状态
	@Column(columnDefinition = "int default 0")
	private Integer num;// 数据库存储的版本数量（次数）
	@Column(name = "total_num", columnDefinition = "int default 0")
	private Integer totalNum;// 表示一个企业总共被爬取了多少次
	@Column(columnDefinition = "int default 0")
	private int priority;// 优先级数字越大，爬取优先级越高（默认为0）
	// @OneToMany(mappedBy = "certificateCompany", cascade = CascadeType.ALL)
	// private Set<Organization> organizations;
	@OneToMany(mappedBy = "certificateCompany", cascade = CascadeType.ALL)
	private Set<Certificate> certificates;
	@OneToMany(mappedBy = "certificateCompany", cascade = CascadeType.ALL)
	private Set<CFResultJson> cFResultJsons;

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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Set<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

	public Set<CFResultJson> getcFResultJsons() {
		return cFResultJsons;
	}

	public void setcFResultJsons(Set<CFResultJson> cFResultJsons) {
		this.cFResultJsons = cFResultJsons;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	@Override
	public String toString() {
		return "CertificateCompany [id=" + id + ", name=" + name + ", state="
				+ state + ", num=" + num + ", totalNum=" + totalNum
				+ ", priority=" + priority + ", certificates=" + certificates
				+ ", cFResultJsons=" + cFResultJsons + "]";
	}

}
