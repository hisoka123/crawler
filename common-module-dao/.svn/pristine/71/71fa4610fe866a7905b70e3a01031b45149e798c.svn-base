package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_rjhsjmx database table.
 * 
 */
@Entity
@Table(name="t_rjhsjmx")
@NamedQuery(name="TRjhsjmx.findAll", query="SELECT t FROM TRjhsjmx t")
public class TRjhsjmx implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String amount;

	private String czdate;

	private String method;

	private String type;
	
	private String gsdate; //公示日期

	//bi-directional many-to-one association to TGdjczxx
	@ManyToOne
	@JoinColumn(name="gdjczxx_id")
	private TGdjczxx TGdjczxx;

	public TRjhsjmx() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCzdate() {
		return this.czdate;
	}

	public void setCzdate(String czdate) {
		this.czdate = czdate;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGsdate() {
		return gsdate;
	}

	public void setGsdate(String gsdate) {
		this.gsdate = gsdate;
	}

	public TGdjczxx getTGdjczxx() {
		return this.TGdjczxx;
	}

	public void setTGdjczxx(TGdjczxx TGdjczxx) {
		this.TGdjczxx = TGdjczxx;
	}

}