package com.module.dao.entity.qiyezhengxin;

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
@Table(name="k_enterprise_credit_keyword",uniqueConstraints={@UniqueConstraint(columnNames={"company"})})
@NamedQuery(name="ZhengxinKeyword.findAll", query="SELECT t FROM ZhengxinKeyword t")
public class ZhengxinKeyword implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String company;//发布单位
	private Integer state;//状态
	private Integer num;//访问次数
	@Column(name="total_num",columnDefinition="int default 0")
	private Integer totalNum;//访问总数
	private int priority; //优先级  数字越大，爬取优先级越高（默认为0）
	@OneToMany(mappedBy="zhengxinKeyword", cascade=CascadeType.ALL)
	private Set<Zhengxin> zhengxins;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	public Set<Zhengxin> getZhengxins() {
		return zhengxins;
	}
	public void setZhengxins(Set<Zhengxin> zhengxins) {
		this.zhengxins = zhengxins;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}
