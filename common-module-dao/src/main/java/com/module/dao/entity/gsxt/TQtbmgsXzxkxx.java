package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the t_qtbmgs_xzxkxx database table.
 * 
 */
@Entity
@Table(name="t_qtbmgs_xzxkxx")
@NamedQuery(name="TQtbmgsXzxkxx.findAll", query="SELECT t FROM TQtbmgsXzxkxx t")
public class TQtbmgsXzxkxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	//bi-directional many-to-one association to TBgxx
	@OneToMany(mappedBy="TQtbmgsXzxkxx")
	private Set<TBgxx> TBgxxs;

	//bi-directional many-to-one association to TQyjbxx
	@OneToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	//bi-directional many-to-one association to TXzxkxx
	@OneToMany(mappedBy="TQtbmgsXzxkxx")
	private Set<TXzxkxx> TXzxkxxs;

	public TQtbmgsXzxkxx() {
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

	public Set<TBgxx> getTBgxxs() {
		return this.TBgxxs;
	}

	public void setTBgxxs(Set<TBgxx> TBgxxs) {
		this.TBgxxs = TBgxxs;
	}

	public TBgxx addTBgxx(TBgxx TBgxx) {
		getTBgxxs().add(TBgxx);
		TBgxx.setTQtbmgsXzxkxx(this);

		return TBgxx;
	}

	public TBgxx removeTBgxx(TBgxx TBgxx) {
		getTBgxxs().remove(TBgxx);
		TBgxx.setTQtbmgsXzxkxx(null);

		return TBgxx;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

	public Set<TXzxkxx> getTXzxkxxs() {
		return this.TXzxkxxs;
	}

	public void setTXzxkxxs(Set<TXzxkxx> TXzxkxxs) {
		this.TXzxkxxs = TXzxkxxs;
	}

	public TXzxkxx addTXzxkxx(TXzxkxx TXzxkxx) {
		getTXzxkxxs().add(TXzxkxx);
		TXzxkxx.setTQtbmgsXzxkxx(this);

		return TXzxkxx;
	}

	public TXzxkxx removeTXzxkxx(TXzxkxx TXzxkxx) {
		getTXzxkxxs().remove(TXzxkxx);
		TXzxkxx.setTQtbmgsXzxkxx(null);

		return TXzxkxx;
	}

}