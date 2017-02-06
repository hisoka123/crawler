package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_xzcfxxxq database table.
 * 
 */
@Entity
@Table(name="t_xzcfxxxq")
@NamedQuery(name="TXzcfxxxq.findAll", query="SELECT t FROM TXzcfxxxq t")
public class TXzcfxxxq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String legalreprname;

	private String name;

	private String regnum;

	private String wfxwtype;

	private String xzcfcontent;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String xzcfjds;

	private String xzcfjdsnum;

	private String zcxzcfjddate;

	private String zcxzcfjdjgname;

	//bi-directional many-to-one association to TXzcfxx
	@OneToOne
	@JoinColumn(name="xzcfxx_id")
	private TXzcfxx TXzcfxx;

	public TXzcfxxxq() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLegalreprname() {
		return this.legalreprname;
	}

	public void setLegalreprname(String legalreprname) {
		this.legalreprname = legalreprname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegnum() {
		return this.regnum;
	}

	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}

	public String getWfxwtype() {
		return this.wfxwtype;
	}

	public void setWfxwtype(String wfxwtype) {
		this.wfxwtype = wfxwtype;
	}

	public String getXzcfcontent() {
		return this.xzcfcontent;
	}

	public void setXzcfcontent(String xzcfcontent) {
		this.xzcfcontent = xzcfcontent;
	}

	public String getXzcfjds() {
		return this.xzcfjds;
	}

	public void setXzcfjds(String xzcfjds) {
		this.xzcfjds = xzcfjds;
	}

	public String getXzcfjdsnum() {
		return this.xzcfjdsnum;
	}

	public void setXzcfjdsnum(String xzcfjdsnum) {
		this.xzcfjdsnum = xzcfjdsnum;
	}

	public String getZcxzcfjddate() {
		return this.zcxzcfjddate;
	}

	public void setZcxzcfjddate(String zcxzcfjddate) {
		this.zcxzcfjddate = zcxzcfjddate;
	}

	public String getZcxzcfjdjgname() {
		return this.zcxzcfjdjgname;
	}

	public void setZcxzcfjdjgname(String zcxzcfjdjgname) {
		this.zcxzcfjdjgname = zcxzcfjdjgname;
	}

	public TXzcfxx getTXzcfxx() {
		return this.TXzcfxx;
	}

	public void setTXzcfxx(TXzcfxx TXzcfxx) {
		this.TXzcfxx = TXzcfxx;
	}

}