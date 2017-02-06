package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the t_member database table.
 * 
 */
@Entity
@Table(name="t_member")
@NamedQuery(name="TMember.findAll", query="SELECT t FROM TMember t")
public class TMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String gdtype;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String idnum;

	private String idtype;

	private String infotype;

	private String isgd;

	private String name;

	private String position;
	
	private String sconform; //投资方式

	//bi-directional many-to-one association to TGdjczxx
	@OneToOne(mappedBy="TMember",cascade=CascadeType.ALL)
	private TGdjczxx TGdjczxx;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	public TMember() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGdtype() {
		return this.gdtype;
	}

	public void setGdtype(String gdtype) {
		this.gdtype = gdtype;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getIdnum() {
		return this.idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getIdtype() {
		return this.idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	public String getInfotype() {
		return this.infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getIsgd() {
		return this.isgd;
	}

	public void setIsgd(String isgd) {
		this.isgd = isgd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public TGdjczxx getTGdjczxx() {
		return TGdjczxx;
	}

	public void setTGdjczxx(TGdjczxx tGdjczxx) {
		TGdjczxx = tGdjczxx;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

	public String getSconform() {
		return sconform;
	}

	public void setSconform(String sconform) {
		this.sconform = sconform;
	}

}