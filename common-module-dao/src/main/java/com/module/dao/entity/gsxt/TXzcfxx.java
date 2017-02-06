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
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the t_xzcfxx database table.
 * 
 */
@Entity
@Table(name="t_xzcfxx")
@NamedQuery(name="TXzcfxx.findAll", query="SELECT t FROM TXzcfxx t")
public class TXzcfxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String detail;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String note;

	private String wfxwtype;

	private String xzcfcontent;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String xzcfjds;

	private String xzcfjdsnum;

	private String zcxzcfjddate;

	private String zcxzcfjdjgname;
	
	private String showDateTime;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	//bi-directional many-to-one association to TXzcfxxxq
	@OneToOne(mappedBy="TXzcfxx")
	private TXzcfxxxq TXzcfxxxq;

	public TXzcfxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getInfotype() {
		return this.infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

	public TXzcfxxxq getTXzcfxxxq() {
		return TXzcfxxxq;
	}

	public void setTXzcfxxxq(TXzcfxxxq tXzcfxxxq) {
		TXzcfxxxq = tXzcfxxxq;
	}

	public String getShowDateTime() {
		return showDateTime;
	}

	public void setShowDateTime(String showDateTime) {
		this.showDateTime = showDateTime;
	}
	
}