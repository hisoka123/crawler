package com.module.dao.entity.gsxt;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the t_xzxkxx database table.
 * 
 */
@Entity
@Table(name="t_xzxkxx")
@NamedQuery(name="TXzxkxx.findAll", query="SELECT t FROM TXzxkxx t")
public class TXzxkxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String detail;

	private String enddate;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String startdate;

	private String status;

	private String pubDateTime;
	
	private String xkauthority;

	@Column(length=1024)
	private String xkcontent;

	private String xkwjname;

	private String xkwjnum;

	private String expiryDate; //有效日期
	
	//bi-directional many-to-one association to TQtbmgsXzxkxx
	@ManyToOne
	@JoinColumn(name="qtbmgs_xzxkxx_id")
	private TQtbmgsXzxkxx TQtbmgsXzxkxx;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	@ManyToOne
	@JoinColumn(name="qynb_id")
	private TQynb TQynb;
	
	@OneToMany(mappedBy="TXzxkxx",cascade=CascadeType.ALL)
	private Set<TBgxx> TBgxxs;
	
	public TXzxkxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
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

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXkauthority() {
		return this.xkauthority;
	}

	public void setXkauthority(String xkauthority) {
		this.xkauthority = xkauthority;
	}

	public String getXkcontent() {
		return this.xkcontent;
	}

	public void setXkcontent(String xkcontent) {
		this.xkcontent = xkcontent;
	}

	public String getXkwjname() {
		return this.xkwjname;
	}

	public void setXkwjname(String xkwjname) {
		this.xkwjname = xkwjname;
	}

	public String getXkwjnum() {
		return this.xkwjnum;
	}

	public void setXkwjnum(String xkwjnum) {
		this.xkwjnum = xkwjnum;
	}

	public TQtbmgsXzxkxx getTQtbmgsXzxkxx() {
		return this.TQtbmgsXzxkxx;
	}

	public void setTQtbmgsXzxkxx(TQtbmgsXzxkxx TQtbmgsXzxkxx) {
		this.TQtbmgsXzxkxx = TQtbmgsXzxkxx;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

	public TQynb getTQynb() {
		return TQynb;
	}

	public void setTQynb(TQynb tQynb) {
		TQynb = tQynb;
	}

	public Set<TBgxx> getTBgxxs() {
		return TBgxxs;
	}

	public void setTBgxxs(Set<TBgxx> tBgxxs) {
		TBgxxs = tBgxxs;
	}

	public String getPubDateTime() {
		return pubDateTime;
	}

	public void setPubDateTime(String pubDateTime) {
		this.pubDateTime = pubDateTime;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}