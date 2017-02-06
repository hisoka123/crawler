package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the t_gdjczxx database table.
 * 
 */
@Entity
@Table(name="t_gdjczxx")
@NamedQuery(name="TGdjczxx.findAll", query="SELECT t FROM TGdjczxx t")
public class TGdjczxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String rjczamount;

	private String rjczdate;

	private String rjczmethod;

	private String sjczamount;

	private String sjczdate;

	private String sjczmethod;

	private String stockholder;
	
	private String investorsType;
	
	private String stockType;

	//bi-directional many-to-one association to TMember
	@OneToOne
	@JoinColumn(name="member_id")
	private TMember TMember;

	//bi-directional many-to-one association to TQygsGdjczxx
	@ManyToOne
	@JoinColumn(name="qygs_gdjczxx_id")
	private TQygsGdjczxx TQygsGdjczxx;

	//bi-directional many-to-one association to TQynb
	@ManyToOne
	@JoinColumn(name="qynb_id")
	private TQynb TQynb;

	//bi-directional many-to-one association to TRjhsjmx
	@OneToMany(mappedBy="TGdjczxx")
	private Set<TRjhsjmx> TRjhsjmxs;

	public TGdjczxx() {
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

	public String getRjczamount() {
		return this.rjczamount;
	}

	public void setRjczamount(String rjczamount) {
		this.rjczamount = rjczamount;
	}

	public String getRjczdate() {
		return this.rjczdate;
	}

	public void setRjczdate(String rjczdate) {
		this.rjczdate = rjczdate;
	}

	public String getRjczmethod() {
		return this.rjczmethod;
	}

	public void setRjczmethod(String rjczmethod) {
		this.rjczmethod = rjczmethod;
	}

	public String getSjczamount() {
		return this.sjczamount;
	}

	public void setSjczamount(String sjczamount) {
		this.sjczamount = sjczamount;
	}

	public String getSjczdate() {
		return this.sjczdate;
	}

	public void setSjczdate(String sjczdate) {
		this.sjczdate = sjczdate;
	}

	public String getSjczmethod() {
		return this.sjczmethod;
	}

	public void setSjczmethod(String sjczmethod) {
		this.sjczmethod = sjczmethod;
	}

	public String getStockholder() {
		return this.stockholder;
	}

	public void setStockholder(String stockholder) {
		this.stockholder = stockholder;
	}

	public TMember getTMember() {
		return this.TMember;
	}

	public void setTMember(TMember TMember) {
		this.TMember = TMember;
	}

	public TQygsGdjczxx getTQygsGdjczxx() {
		return this.TQygsGdjczxx;
	}

	public void setTQygsGdjczxx(TQygsGdjczxx TQygsGdjczxx) {
		this.TQygsGdjczxx = TQygsGdjczxx;
	}

	public TQynb getTQynb() {
		return this.TQynb;
	}

	public void setTQynb(TQynb TQynb) {
		this.TQynb = TQynb;
	}

	public Set<TRjhsjmx> getTRjhsjmxs() {
		return this.TRjhsjmxs;
	}

	public void setTRjhsjmxs(Set<TRjhsjmx> TRjhsjmxs) {
		this.TRjhsjmxs = TRjhsjmxs;
	}

	public TRjhsjmx addTRjhsjmx(TRjhsjmx TRjhsjmx) {
		getTRjhsjmxs().add(TRjhsjmx);
		TRjhsjmx.setTGdjczxx(this);

		return TRjhsjmx;
	}

	public TRjhsjmx removeTRjhsjmx(TRjhsjmx TRjhsjmx) {
		getTRjhsjmxs().remove(TRjhsjmx);
		TRjhsjmx.setTGdjczxx(null);

		return TRjhsjmx;
	}

	public String getInvestorsType() {
		return investorsType;
	}

	public void setInvestorsType(String investorsType) {
		this.investorsType = investorsType;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
}