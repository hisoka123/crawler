package com.module.dao.entity.iecms;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "m_iecms_company", uniqueConstraints = { @UniqueConstraint(columnNames = { "keyword" }) })
@NamedQuery(name = "IecmsCompany.findAll", query = "SELECT t FROM IecmsCompany t")
public class IecmsCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String keyword;// 输入关键字

	private String type;// 1代表//经营者名称 2代表//13位经营者代码 3代表//统一社会信用代码

	private Integer state;

	private Integer num;

	// 优先级 数字越大，爬取优先级越高（默认为0）
	private int priority;
	// 标示企业共趴取了多少次
	private Integer totalNum;

	@Column(name = "total_num", columnDefinition = "int default 0")
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	@OneToMany(mappedBy = "iecmscompany", cascade = CascadeType.ALL)
	private Set<Iecms> iecms;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Iecms> getIecms() {
		return iecms;
	}

	public void setIecms(Set<Iecms> iecms) {
		this.iecms = iecms;
	}

}
