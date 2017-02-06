package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_gsxt_html database table.
 * 
 */
@Entity
@Table(name="t_gsxt_html")
@NamedQuery(name="TGsxtHtml.findAll", query="SELECT t FROM TGsxtHtml t")
public class TGsxtHtml implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String gsgsbainfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String gsgsdcdydjinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String gsgsgqczdjinfo;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String gsgsxzcfinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String gsgsjyycinfo;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String gsgsyzwfinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String gsgsccjcinfo;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String qtbmgsxzcfxzcfinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String qtbmgsxzxkxzxkinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String qygsgdjczinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String qygsgqbginfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String qygsqynbinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String qygsxzcfinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String qygsxzxkinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String qygszscqczdjinfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String sfxzgsgdbginfo;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String sfxzgsgqdjinfo;

	//bi-directional many-to-one association to TQyjbxx
	@OneToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	public TGsxtHtml() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGsgsbainfo() {
		return this.gsgsbainfo;
	}

	public void setGsgsbainfo(String gsgsbainfo) {
		this.gsgsbainfo = gsgsbainfo;
	}

	public String getGsgsdcdydjinfo() {
		return gsgsdcdydjinfo;
	}

	public void setGsgsdcdydjinfo(String gsgsdcdydjinfo) {
		this.gsgsdcdydjinfo = gsgsdcdydjinfo;
	}

	public String getGsgsgqczdjinfo() {
		return gsgsgqczdjinfo;
	}

	public void setGsgsgqczdjinfo(String gsgsgqczdjinfo) {
		this.gsgsgqczdjinfo = gsgsgqczdjinfo;
	}

	public String getGsgsxzcfinfo() {
		return this.gsgsxzcfinfo;
	}

	public void setGsgsxzcfinfo(String gsgsxzcfinfo) {
		this.gsgsxzcfinfo = gsgsxzcfinfo;
	}

	public String getGsgsjyycinfo() {
		return gsgsjyycinfo;
	}

	public void setGsgsjyycinfo(String gsgsjyycinfo) {
		this.gsgsjyycinfo = gsgsjyycinfo;
	}

	public String getGsgsyzwfinfo() {
		return this.gsgsyzwfinfo;
	}

	public void setGsgsyzwfinfo(String gsgsyzwfinfo) {
		this.gsgsyzwfinfo = gsgsyzwfinfo;
	}

	public String getGsgsccjcinfo() {
		return gsgsccjcinfo;
	}

	public void setGsgsccjcinfo(String gsgsccjcinfo) {
		this.gsgsccjcinfo = gsgsccjcinfo;
	}

	public String getQtbmgsxzcfxzcfinfo() {
		return this.qtbmgsxzcfxzcfinfo;
	}

	public void setQtbmgsxzcfxzcfinfo(String qtbmgsxzcfxzcfinfo) {
		this.qtbmgsxzcfxzcfinfo = qtbmgsxzcfxzcfinfo;
	}

	public String getQtbmgsxzxkxzxkinfo() {
		return this.qtbmgsxzxkxzxkinfo;
	}

	public void setQtbmgsxzxkxzxkinfo(String qtbmgsxzxkxzxkinfo) {
		this.qtbmgsxzxkxzxkinfo = qtbmgsxzxkxzxkinfo;
	}

	public String getQygsgdjczinfo() {
		return this.qygsgdjczinfo;
	}

	public void setQygsgdjczinfo(String qygsgdjczinfo) {
		this.qygsgdjczinfo = qygsgdjczinfo;
	}

	public String getQygsgqbginfo() {
		return this.qygsgqbginfo;
	}

	public void setQygsgqbginfo(String qygsgqbginfo) {
		this.qygsgqbginfo = qygsgqbginfo;
	}

	public String getQygsqynbinfo() {
		return this.qygsqynbinfo;
	}

	public void setQygsqynbinfo(String qygsqynbinfo) {
		this.qygsqynbinfo = qygsqynbinfo;
	}

	public String getQygsxzcfinfo() {
		return this.qygsxzcfinfo;
	}

	public void setQygsxzcfinfo(String qygsxzcfinfo) {
		this.qygsxzcfinfo = qygsxzcfinfo;
	}

	public String getQygsxzxkinfo() {
		return this.qygsxzxkinfo;
	}

	public void setQygsxzxkinfo(String qygsxzxkinfo) {
		this.qygsxzxkinfo = qygsxzxkinfo;
	}

	public String getQygszscqczdjinfo() {
		return this.qygszscqczdjinfo;
	}

	public void setQygszscqczdjinfo(String qygszscqczdjinfo) {
		this.qygszscqczdjinfo = qygszscqczdjinfo;
	}

	public String getSfxzgsgdbginfo() {
		return this.sfxzgsgdbginfo;
	}

	public void setSfxzgsgdbginfo(String sfxzgsgdbginfo) {
		this.sfxzgsgdbginfo = sfxzgsgdbginfo;
	}

	public String getSfxzgsgqdjinfo() {
		return this.sfxzgsgqdjinfo;
	}

	public void setSfxzgsgqdjinfo(String sfxzgsgqdjinfo) {
		this.sfxzgsgqdjinfo = sfxzgsgqdjinfo;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

}