package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_gqbgxx database table.
 * 
 */
@Entity
@Table(name="t_gqbgxx")
@NamedQuery(name="TGqbgxx.findAll", query="SELECT t FROM TGqbgxx t")
public class TGqbgxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String bgdate;

	private String bghownershipratio;

	private String bgqownershipratio;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String stockholder;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	//bi-directional many-to-one association to TQynb
	@ManyToOne
	@JoinColumn(name="qynb_id")
	private TQynb TQynb;

	public TGqbgxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBgdate() {
		return this.bgdate;
	}

	public void setBgdate(String bgdate) {
		this.bgdate = bgdate;
	}

	public String getBghownershipratio() {
		return this.bghownershipratio;
	}

	public void setBghownershipratio(String bghownershipratio) {
		this.bghownershipratio = bghownershipratio;
	}

	public String getBgqownershipratio() {
		return this.bgqownershipratio;
	}

	public void setBgqownershipratio(String bgqownershipratio) {
		this.bgqownershipratio = bgqownershipratio;
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

	public String getStockholder() {
		return this.stockholder;
	}

	public void setStockholder(String stockholder) {
		this.stockholder = stockholder;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

	public TQynb getTQynb() {
		return this.TQynb;
	}

	public void setTQynb(TQynb TQynb) {
		this.TQynb = TQynb;
	}

}