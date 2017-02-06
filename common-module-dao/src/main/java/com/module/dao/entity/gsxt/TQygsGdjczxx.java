package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the t_qygs_gdjczxx database table.
 * 
 */
@Entity
@Table(name="t_qygs_gdjczxx")
@NamedQuery(name="TQygsGdjczxx.findAll", query="SELECT t FROM TQygsGdjczxx t")
public class TQygsGdjczxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	//bi-directional many-to-one association to TBgxx
	@OneToMany(mappedBy="TQygsGdjczxx")
	private Set<TBgxx> TBgxxs;

	//bi-directional many-to-one association to TGdjczxx
	@OneToMany(mappedBy="TQygsGdjczxx")
	private Set<TGdjczxx> TGdjczxxs;

	//bi-directional many-to-one association to TQyjbxx
	@OneToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	public TQygsGdjczxx() {
	}

	public Long getId() {
		return id;
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

	public Set<TBgxx> getTBgxxs() {
		return this.TBgxxs;
	}

	public void setTBgxxs(Set<TBgxx> TBgxxs) {
		this.TBgxxs = TBgxxs;
	}

	public TBgxx addTBgxx(TBgxx TBgxx) {
		getTBgxxs().add(TBgxx);
		TBgxx.setTQygsGdjczxx(this);

		return TBgxx;
	}

	public TBgxx removeTBgxx(TBgxx TBgxx) {
		getTBgxxs().remove(TBgxx);
		TBgxx.setTQygsGdjczxx(null);

		return TBgxx;
	}

	public Set<TGdjczxx> getTGdjczxxs() {
		return this.TGdjczxxs;
	}

	public void setTGdjczxxs(Set<TGdjczxx> TGdjczxxs) {
		this.TGdjczxxs = TGdjczxxs;
	}

	public TGdjczxx addTGdjczxx(TGdjczxx TGdjczxx) {
		getTGdjczxxs().add(TGdjczxx);
		TGdjczxx.setTQygsGdjczxx(this);

		return TGdjczxx;
	}

	public TGdjczxx removeTGdjczxx(TGdjczxx TGdjczxx) {
		getTGdjczxxs().remove(TGdjczxx);
		TGdjczxx.setTQygsGdjczxx(null);

		return TGdjczxx;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

}