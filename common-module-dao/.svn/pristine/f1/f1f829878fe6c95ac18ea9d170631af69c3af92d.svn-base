package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_dwtgbzdbxx database table.
 * 
 */
@Entity
@Table(name="t_dwtgbzdbxx")
@NamedQuery(name="TDwtgbzdbxx.findAll", query="SELECT t FROM TDwtgbzdbxx t")
public class TDwtgbzdbxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String creditor;

	private String debtor;

	private String exedebtdeadline;

	private String guaranteemethod;

	private String guaranteeperiod;

	private String guaranteescope;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")	
	private String html;

	private String infotype;

	private String pricredrightamount;

	private String pricredrighttype;

	//bi-directional many-to-one association to TQynb
	@ManyToOne
	@JoinColumn(name="qynb_id")
	private TQynb TQynb;

	public TDwtgbzdbxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreditor() {
		return this.creditor;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public String getDebtor() {
		return this.debtor;
	}

	public void setDebtor(String debtor) {
		this.debtor = debtor;
	}

	public String getExedebtdeadline() {
		return this.exedebtdeadline;
	}

	public void setExedebtdeadline(String exedebtdeadline) {
		this.exedebtdeadline = exedebtdeadline;
	}

	public String getGuaranteemethod() {
		return this.guaranteemethod;
	}

	public void setGuaranteemethod(String guaranteemethod) {
		this.guaranteemethod = guaranteemethod;
	}

	public String getGuaranteeperiod() {
		return this.guaranteeperiod;
	}

	public void setGuaranteeperiod(String guaranteeperiod) {
		this.guaranteeperiod = guaranteeperiod;
	}

	public String getGuaranteescope() {
		return this.guaranteescope;
	}

	public void setGuaranteescope(String guaranteescope) {
		this.guaranteescope = guaranteescope;
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

	public String getPricredrightamount() {
		return this.pricredrightamount;
	}

	public void setPricredrightamount(String pricredrightamount) {
		this.pricredrightamount = pricredrightamount;
	}

	public String getPricredrighttype() {
		return this.pricredrighttype;
	}

	public void setPricredrighttype(String pricredrighttype) {
		this.pricredrighttype = pricredrighttype;
	}

	public TQynb getTQynb() {
		return this.TQynb;
	}

	public void setTQynb(TQynb TQynb) {
		this.TQynb = TQynb;
	}

}