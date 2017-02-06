package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_gqdjxx database table.
 * 
 */
@Entity
@Table(name="t_gqdjxx")
@NamedQuery(name="TGqdjxx.findAll", query="SELECT t FROM TGqdjxx t")
public class TGqdjxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String bzxperson;

	private String detail;

	private String execourt;

	private String gqamount;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String status;

	private String xzgstzsnum;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	public TGqdjxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBzxperson() {
		return this.bzxperson;
	}

	public void setBzxperson(String bzxperson) {
		this.bzxperson = bzxperson;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getExecourt() {
		return this.execourt;
	}

	public void setExecourt(String execourt) {
		this.execourt = execourt;
	}

	public String getGqamount() {
		return this.gqamount;
	}

	public void setGqamount(String gqamount) {
		this.gqamount = gqamount;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXzgstzsnum() {
		return this.xzgstzsnum;
	}

	public void setXzgstzsnum(String xzgstzsnum) {
		this.xzgstzsnum = xzgstzsnum;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

}