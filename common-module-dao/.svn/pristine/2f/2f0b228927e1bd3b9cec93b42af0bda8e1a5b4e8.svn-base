package com.module.dao.entity.gsxt;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the t_dcdydjxx database table.
 * 
 */
@Entity
@Table(name="t_dcdydjxx")
@NamedQuery(name="TDcdydjxx.findAll", query="SELECT t FROM TDcdydjxx t")
public class TDcdydjxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String bdbzqamount;

	private String detail;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String pubdate;

	private String regauthority;

	private String regdate;

	private String regnum;

	private String status;
	
	private String guaranteeddebttype;
	
	private String term;
	
	private String guaranteedscope;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String note;
	
	private String revokedate;
	
	private String revokereason;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	@OneToMany(mappedBy="TDcdydjxx",cascade=CascadeType.ALL)
	private Set<TDyrgk> TDyrgks;
	
	@OneToMany(mappedBy="TDcdydjxx",cascade=CascadeType.ALL)
	private Set<TDywgk> TDywgks;
	
	@OneToMany(mappedBy="TDcdydjxx",cascade=CascadeType.ALL)
	private Set<TDcdydjxxbg> TDcdydjxxbgs;
	
	public TDcdydjxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBdbzqamount() {
		return this.bdbzqamount;
	}

	public void setBdbzqamount(String bdbzqamount) {
		this.bdbzqamount = bdbzqamount;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public String getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getRegauthority() {
		return this.regauthority;
	}

	public void setRegauthority(String regauthority) {
		this.regauthority = regauthority;
	}

	public String getRegdate() {
		return this.regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getRegnum() {
		return this.regnum;
	}

	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

	public String getGuaranteeddebttype() {
		return guaranteeddebttype;
	}

	public void setGuaranteeddebttype(String guaranteeddebttype) {
		this.guaranteeddebttype = guaranteeddebttype;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getGuaranteedscope() {
		return guaranteedscope;
	}

	public void setGuaranteedscope(String guaranteedscope) {
		this.guaranteedscope = guaranteedscope;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRevokedate() {
		return revokedate;
	}

	public void setRevokedate(String revokedate) {
		this.revokedate = revokedate;
	}

	public String getRevokereason() {
		return revokereason;
	}

	public void setRevokereason(String revokereason) {
		this.revokereason = revokereason;
	}

	public Set<TDyrgk> getTDyrgks() {
		return TDyrgks;
	}

	public void setTDyrgks(Set<TDyrgk> tDyrgks) {
		TDyrgks = tDyrgks;
	}

	public Set<TDywgk> getTDywgks() {
		return TDywgks;
	}

	public void setTDywgks(Set<TDywgk> tDywgks) {
		TDywgks = tDywgks;
	}

	public Set<TDcdydjxxbg> getTDcdydjxxbgs() {
		return TDcdydjxxbgs;
	}

	public void setTDcdydjxxbgs(Set<TDcdydjxxbg> tDcdydjxxbgs) {
		TDcdydjxxbgs = tDcdydjxxbgs;
	}
}