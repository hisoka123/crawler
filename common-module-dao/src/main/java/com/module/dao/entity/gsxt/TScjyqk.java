package com.module.dao.entity.gsxt;

import java.io.Serializable;

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

@Entity
@Table(name="t_scjyqk")
@NamedQuery(name="TScjyqk.findAll", query="SELECT t FROM TScjyqk t")
public class TScjyqk implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String infoType;
	
	private String saleSum;
	
	private String salarySum;
	
	private String netProfit;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;
	
	@ManyToOne
	@JoinColumn(name="qynb_id")
	private TQynb TQynb;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(String saleSum) {
		this.saleSum = saleSum;
	}

	public String getSalarySum() {
		return salarySum;
	}

	public void setSalarySum(String salarySum) {
		this.salarySum = salarySum;
	}

	public String getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public TQynb getTQynb() {
		return TQynb;
	}

	public void setTQynb(TQynb tQynb) {
		TQynb = tQynb;
	}
	
}
