package com.module.dao.entity.gsxt;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * gsxt查询返回结果
 */
@Entity
@Table(name="t_result_json")
@NamedQuery(name="TResultJson.findAll", query="SELECT t FROM TResultJson t")
public class TResultJson implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public TResultJson() {
		super();
	}
	public TResultJson(String cname, String result, Date executetime) {
		super();
		this.cname = cname;
		this.result = result;
		this.executetime = executetime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String cname;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String result;
	
	private Date executetime;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company Company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public Company getCompany() {
		return Company;
	}

	public void setCompany(Company company) {
		Company = company;
	}

	@Override
	public String toString() {
		return "TResultJson [id=" + id + ", cname=" + cname + ", result="
				+ result + ", executetime=" + executetime + "]";
	}
}