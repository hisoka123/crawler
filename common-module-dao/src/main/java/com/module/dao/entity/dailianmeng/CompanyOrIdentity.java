package com.module.dao.entity.dailianmeng;

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

/*
 * 公司名称或身份证号作为关键字
 */
@Entity
@Table(name="d_company_or_identity", uniqueConstraints={@UniqueConstraint(columnNames={"keyword", "type"})})
@NamedQuery(name="CompanyOrIdentity.findAll", query="SELECT t FROM CompanyOrIdentity t")
public class CompanyOrIdentity implements Serializable {

	private static final long serialVersionUID = 6476792176581703145L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//公司名称或身份证号
	private String keyword;
	
	//类型（两种：1.公司名称  2.身份证号）
	private String type;
	
	//表示一个企业在一次定时任务中被爬取的次数（比如验证码错误之后，该企业会被再次爬取），每次添加我的任务，对应企业的num会被置为0
	private Integer num;
	
	//表示一个企业总共被爬取了多少次
	@Column(name="total_num", columnDefinition="int default 0")
	private Integer totalNum;
	
	//数据执行优先级
	private Integer priority;
	
	//数据执行状态
	private Integer state;
	
	@OneToMany(mappedBy="companyOrIdentity",cascade=CascadeType.ALL)
	private Set<LoanUnion> loanUnions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<LoanUnion> getLoanUnions() {
		return loanUnions;
	}

	public void setLoanUnions(Set<LoanUnion> loanUnions) {
		this.loanUnions = loanUnions;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}
