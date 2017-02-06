package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_zscqczdjxx database table.
 * 
 */
@Entity
@Table(name="t_zscqczdjxx")
@NamedQuery(name="TZscqczdjxx.findAll", query="SELECT t FROM TZscqczdjxx t")
public class TZscqczdjxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String changesitu;

	private String czrname;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String name;

	private String regnum;

	private String status;

	private String type;

	private String zqdjdeadline;

	private String zqrname;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	public TZscqczdjxx() {
	}

	public Long getId() {
		return this.id;
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

	public String getCzrname() {
		return this.czrname;
	}

	public void setCzrname(String czrname) {
		this.czrname = czrname;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZqdjdeadline() {
		return this.zqdjdeadline;
	}

	public void setZqdjdeadline(String zqdjdeadline) {
		this.zqdjdeadline = zqdjdeadline;
	}

	public String getZqrname() {
		return this.zqrname;
	}

	public void setZqrname(String zqrname) {
		this.zqrname = zqrname;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

}