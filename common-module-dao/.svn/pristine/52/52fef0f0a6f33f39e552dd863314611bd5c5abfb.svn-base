package com.module.dao.entity.crqp;

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
@Table(name = "j_crqp_company", uniqueConstraints = { @UniqueConstraint(columnNames = { "keyword" }) })
@NamedQuery(name = "CreditrRecordQueryPlatformCompany.findAll", query = "SELECT t FROM CreditrRecordQueryPlatformCompany t")
@SequenceGenerator(name = "creditrrecordqueryplatformcompanySeq", sequenceName = "creditrrecordqueryplatformcompany_sequence", allocationSize = 1)
public class CreditrRecordQueryPlatformCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String keyword;// 搜索关键字

	private Integer state;// 状态

	private Integer num;// 查询时间

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

	@OneToMany(mappedBy = "creditrrecordqueryplatformcompany", cascade = CascadeType.ALL)
	private Set<CreditrRecordQueryPlatform> creditrrecordqueryplatforms;

	public Set<CreditrRecordQueryPlatform> getCreditrrecordqueryplatforms() {
		return creditrrecordqueryplatforms;
	}

	public void setCreditrrecordqueryplatforms(
			Set<CreditrRecordQueryPlatform> creditrrecordqueryplatforms) {
		this.creditrrecordqueryplatforms = creditrrecordqueryplatforms;
	}

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

}
