package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_gqczdjxx database table.
 * 
 */
@Entity
@Table(name="t_gqczdjxx")
@NamedQuery(name="TGqczdjxx.findAll", query="SELECT t FROM TGqczdjxx t")
public class TGqczdjxx implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String changesitu;

	private String czgqamount;

	private String czr;

	private String czridnum;

	private String gqczsldjdate;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String pubdate;

	private String regnum;

	private String status;

	private String zqr;

	private String zqridnum;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	public TGqczdjxx() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChangesitu() {
		return this.changesitu;
	}

	public void setChangesitu(String changesitu) {
		this.changesitu = changesitu;
	}

	public String getCzgqamount() {
		return this.czgqamount;
	}

	public void setCzgqamount(String czgqamount) {
		this.czgqamount = czgqamount;
	}

	public String getCzr() {
		return this.czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCzridnum() {
		return this.czridnum;
	}

	public void setCzridnum(String czridnum) {
		this.czridnum = czridnum;
	}

	public String getGqczsldjdate() {
		return this.gqczsldjdate;
	}

	public void setGqczsldjdate(String gqczsldjdate) {
		this.gqczsldjdate = gqczsldjdate;
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

	public String getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getRegnum() {
		return this.regnum;
	}

	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getZqr() {
		return this.zqr;
	}

	public void setZqr(String zqr) {
		this.zqr = zqr;
	}

	public String getZqridnum() {
		return this.zqridnum;
	}

	public void setZqridnum(String zqridnum) {
		this.zqridnum = zqridnum;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

}