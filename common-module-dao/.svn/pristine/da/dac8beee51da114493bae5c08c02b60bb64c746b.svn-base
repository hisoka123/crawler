package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the t_bgxx database table.
 * 
 */
@Entity
@Table(name="t_bgxx")
@NamedQuery(name="TBgxx.findAll", query="SELECT t FROM TBgxx t")
public class TBgxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String bgdate;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String bghcontent;

	private String bgitem;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String bgqcontent;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	//bi-directional many-to-one association to TQtbmgsXzxkxx
	@ManyToOne
	@JoinColumn(name="qtbmgs_xzxkxx_id")
	private TQtbmgsXzxkxx TQtbmgsXzxkxx;

	//bi-directional many-to-one association to TQygsGdjczxx
	@ManyToOne
	@JoinColumn(name="qygs_gdjczxx_id")
	private TQygsGdjczxx TQygsGdjczxx;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	//bi-directional many-to-one association to TQynb
	@ManyToOne
	@JoinColumn(name="qynb_id")
	private TQynb TQynb;

	//bi-directional many-to-one association to TBgxxxq
	@OneToMany(mappedBy="TBgxx",cascade=CascadeType.ALL)
	private Set<TBgxxxq> TBgxxxqs;

	@ManyToOne
	@JoinColumn(name="xzxkxx_id")
	private TXzxkxx TXzxkxx;
	
	public TBgxx() {
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

	public String getBghcontent() {
		return this.bghcontent;
	}

	public void setBghcontent(String bghcontent) {
		this.bghcontent = bghcontent;
	}

	public String getBgitem() {
		return this.bgitem;
	}

	public void setBgitem(String bgitem) {
		this.bgitem = bgitem;
	}

	public String getBgqcontent() {
		return this.bgqcontent;
	}

	public void setBgqcontent(String bgqcontent) {
		this.bgqcontent = bgqcontent;
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

	public TQtbmgsXzxkxx getTQtbmgsXzxkxx() {
		return this.TQtbmgsXzxkxx;
	}

	public void setTQtbmgsXzxkxx(TQtbmgsXzxkxx TQtbmgsXzxkxx) {
		this.TQtbmgsXzxkxx = TQtbmgsXzxkxx;
	}

	public TQygsGdjczxx getTQygsGdjczxx() {
		return this.TQygsGdjczxx;
	}

	public void setTQygsGdjczxx(TQygsGdjczxx TQygsGdjczxx) {
		this.TQygsGdjczxx = TQygsGdjczxx;
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

	public Set<TBgxxxq> getTBgxxxqs() {
		return this.TBgxxxqs;
	}

	public void setTBgxxxqs(Set<TBgxxxq> TBgxxxqs) {
		this.TBgxxxqs = TBgxxxqs;
	}

	public TBgxxxq addTBgxxxq(TBgxxxq TBgxxxq) {
		getTBgxxxqs().add(TBgxxxq);
		TBgxxxq.setTBgxx(this);

		return TBgxxxq;
	}

	public TBgxxxq removeTBgxxxq(TBgxxxq TBgxxxq) {
		getTBgxxxqs().remove(TBgxxxq);
		TBgxxxq.setTBgxx(null);

		return TBgxxxq;
	}

	public TXzxkxx getTXzxkxx() {
		return TXzxkxx;
	}

	public void setTXzxkxx(TXzxkxx tXzxkxx) {
		TXzxkxx = tXzxkxx;
	}
}