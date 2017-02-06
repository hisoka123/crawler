package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_jyychyzwfxx database table.
 * 
 */
@Entity
@Table(name="t_jyychyzwfxx")
@NamedQuery(name="TJyychyzwfxx.findAll", query="SELECT t FROM TJyychyzwfxx t")
public class TJyychyzwfxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private Boolean isjyyc;

	private String lrcause;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String lrcauseDetail;
	
	private String serialNumber;

	private String lrdate;

	private String lrzcjdauthority;

	private String yccause;

	private String ycdate;

	private String yczcjdauthority;

	private String zcjdauthority;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	public TJyychyzwfxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getIsjyyc() {
		return this.isjyyc;
	}

	public void setIsjyyc(Boolean isjyyc) {
		this.isjyyc = isjyyc;
	}

	public String getLrcause() {
		return this.lrcause;
	}

	public void setLrcause(String lrcause) {
		this.lrcause = lrcause;
	}

	public String getLrdate() {
		return this.lrdate;
	}

	public void setLrdate(String lrdate) {
		this.lrdate = lrdate;
	}

	public String getLrzcjdauthority() {
		return this.lrzcjdauthority;
	}

	public void setLrzcjdauthority(String lrzcjdauthority) {
		this.lrzcjdauthority = lrzcjdauthority;
	}

	public String getYccause() {
		return this.yccause;
	}

	public void setYccause(String yccause) {
		this.yccause = yccause;
	}

	public String getYcdate() {
		return this.ycdate;
	}

	public void setYcdate(String ycdate) {
		this.ycdate = ycdate;
	}

	public String getYczcjdauthority() {
		return this.yczcjdauthority;
	}

	public void setYczcjdauthority(String yczcjdauthority) {
		this.yczcjdauthority = yczcjdauthority;
	}

	public String getZcjdauthority() {
		return this.zcjdauthority;
	}

	public void setZcjdauthority(String zcjdauthority) {
		this.zcjdauthority = zcjdauthority;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

	public String getLrcauseDetail() {
		return lrcauseDetail;
	}

	public void setLrcauseDetail(String lrcauseDetail) {
		this.lrcauseDetail = lrcauseDetail;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}