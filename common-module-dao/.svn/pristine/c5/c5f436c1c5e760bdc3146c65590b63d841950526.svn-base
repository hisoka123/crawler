package com.module.dao.entity.gsxt;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the t_qyjbxx database table.
 * 
 */
@Entity
@Table(name="t_qyjbxx")
@NamedQuery(name="TQyjbxx.findAll", query="SELECT t FROM TQyjbxx t")
public class TQyjbxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String address;

	private String approvaldate;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String businessscope;

	private String enddate;

	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String infotype;

	private String legalrepr;

	private String name;

	private String num;

	private String regauthority;

	private String registeredcapital;

	private String registereddate;
	
	private String formtype;

	private String regstatus;

	private String revokedate;

	private String startdate;

	private String type;
	
	private Date executetime;

	//bi-directional many-to-one association to TBgxx
	@OneToMany(mappedBy="TQyjbxx")
	private Set<TBgxx> TBgxxs;
	
	@OneToMany(mappedBy="TQyjbxx")
	private Set<TCxxx> TCxxxs;

	//bi-directional many-to-one association to TCcjcxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TCcjcxx> TCcjcxxs;

	//bi-directional many-to-one association to TDcdydjxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TDcdydjxx> TDcdydjxxs;

	//bi-directional many-to-one association to TFzjgxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TFzjgxx> TFzjgxxs;

	//bi-directional many-to-one association to TGdbgxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TGdbgxx> TGdbgxxs;

	//bi-directional many-to-one association to TGqbgxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TGqbgxx> TGqbgxxs;

	//bi-directional many-to-one association to TGqczdjxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TGqczdjxx> TGqczdjxxs;

	//bi-directional many-to-one association to TGqdjxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TGqdjxx> TGqdjxxs;

	//bi-directional many-to-one association to TGsxtHtml
	@OneToOne(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private TGsxtHtml TGsxtHtml;

	//bi-directional many-to-one association to TJyychyzwfxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TJyychyzwfxx> TJyychyzwfxxs;

	//bi-directional many-to-one association to TMember
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TMember> TMembers;

	//bi-directional many-to-one association to TQsxx
	@OneToOne(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private TQsxx TQsxx;

	//bi-directional many-to-one association to TQtbmgsXzxkxx
	@OneToOne(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private TQtbmgsXzxkxx TQtbmgsXzxkxx;

	//bi-directional many-to-one association to TQygsGdjczxx
	@OneToOne(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private TQygsGdjczxx TQygsGdjczxx;

	//bi-directional many-to-one association to TQynb
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TQynb> TQynbs;

	//bi-directional many-to-one association to TXzcfxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TXzcfxx> TXzcfxxs;

	//bi-directional many-to-one association to TXzxkxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TXzxkxx> TXzxkxxs;

	//bi-directional many-to-one association to TZscqczdjxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TZscqczdjxx> TZscqczdjxxs;

	//bi-directional many-to-one association to TZyryxx
	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TZyryxx> TZyryxxs;

	@OneToMany(mappedBy="TQyjbxx",cascade=CascadeType.ALL)
	private Set<TZgbmxx> TZgbmxxs;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="company_id")
	private Company Company;
	
	public TQyjbxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApprovaldate() {
		return this.approvaldate;
	}

	public void setApprovaldate(String approvaldate) {
		this.approvaldate = approvaldate;
	}

	public String getBusinessscope() {
		return this.businessscope;
	}

	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope;
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

	public String getLegalrepr() {
		return this.legalrepr;
	}

	public void setLegalrepr(String legalrepr) {
		this.legalrepr = legalrepr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getRegauthority() {
		return this.regauthority;
	}

	public void setRegauthority(String regauthority) {
		this.regauthority = regauthority;
	}

	public String getRegisteredcapital() {
		return this.registeredcapital;
	}

	public void setRegisteredcapital(String registeredcapital) {
		this.registeredcapital = registeredcapital;
	}

	public String getRegistereddate() {
		return this.registereddate;
	}

	public void setRegistereddate(String registereddate) {
		this.registereddate = registereddate;
	}
	

	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}

	public String getRegstatus() {
		return this.regstatus;
	}

	public void setRegstatus(String regstatus) {
		this.regstatus = regstatus;
	}

	public String getRevokedate() {
		return this.revokedate;
	}

	public void setRevokedate(String revokedate) {
		this.revokedate = revokedate;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public Set<TCcjcxx> getTCcjcxxs() {
		return this.TCcjcxxs;
	}

	public void setTCcjcxxs(Set<TCcjcxx> TCcjcxxs) {
		this.TCcjcxxs = TCcjcxxs;
	}

	public Set<TBgxx> getTBgxxs() {
		return this.TBgxxs;
	}

	public void setTBgxxs(Set<TBgxx> TBgxxs) {
		this.TBgxxs = TBgxxs;
	}

	public TBgxx addTBgxx(TBgxx TBgxx) {
		getTBgxxs().add(TBgxx);
		TBgxx.setTQyjbxx(this);

		return TBgxx;
	}

	public TBgxx removeTBgxx(TBgxx TBgxx) {
		getTBgxxs().remove(TBgxx);
		TBgxx.setTQyjbxx(null);

		return TBgxx;
	}
	
	public Set<TCxxx> getTCxxxs() {
		return this.TCxxxs;
	}

	public void setTCxxxs(Set<TCxxx> TCxxxs) {
		this.TCxxxs = TCxxxs;
	}

	public TCxxx addTCxxx(TCxxx TCxxx) {
		getTCxxxs().add(TCxxx);
		TCxxx.setTQyjbxx(this);

		return TCxxx;
	}

	public TCxxx removeTCxxx(TCxxx TCxxx) {
		getTCxxxs().remove(TCxxx);
		TCxxx.setTQyjbxx(null);

		return TCxxx;
	}

	public TCcjcxx addTCcjcxx(TCcjcxx TCcjcxx) {
		getTCcjcxxs().add(TCcjcxx);
		TCcjcxx.setTQyjbxx(this);

		return TCcjcxx;
	}

	public TCcjcxx removeTCcjcxx(TCcjcxx TCcjcxx) {
		getTCcjcxxs().remove(TCcjcxx);
		TCcjcxx.setTQyjbxx(null);

		return TCcjcxx;
	}

	public Set<TDcdydjxx> getTDcdydjxxs() {
		return this.TDcdydjxxs;
	}

	public void setTDcdydjxxs(Set<TDcdydjxx> TDcdydjxxs) {
		this.TDcdydjxxs = TDcdydjxxs;
	}

	public TDcdydjxx addTDcdydjxx(TDcdydjxx TDcdydjxx) {
		getTDcdydjxxs().add(TDcdydjxx);
		TDcdydjxx.setTQyjbxx(this);

		return TDcdydjxx;
	}

	public TDcdydjxx removeTDcdydjxx(TDcdydjxx TDcdydjxx) {
		getTDcdydjxxs().remove(TDcdydjxx);
		TDcdydjxx.setTQyjbxx(null);

		return TDcdydjxx;
	}

	public Set<TFzjgxx> getTFzjgxxs() {
		return this.TFzjgxxs;
	}

	public void setTFzjgxxs(Set<TFzjgxx> TFzjgxxs) {
		this.TFzjgxxs = TFzjgxxs;
	}

	public TFzjgxx addTFzjgxx(TFzjgxx TFzjgxx) {
		getTFzjgxxs().add(TFzjgxx);
		TFzjgxx.setTQyjbxx(this);

		return TFzjgxx;
	}

	public TFzjgxx removeTFzjgxx(TFzjgxx TFzjgxx) {
		getTFzjgxxs().remove(TFzjgxx);
		TFzjgxx.setTQyjbxx(null);

		return TFzjgxx;
	}

	public Set<TGdbgxx> getTGdbgxxs() {
		return this.TGdbgxxs;
	}

	public void setTGdbgxxs(Set<TGdbgxx> TGdbgxxs) {
		this.TGdbgxxs = TGdbgxxs;
	}

	public TGdbgxx addTGdbgxx(TGdbgxx TGdbgxx) {
		getTGdbgxxs().add(TGdbgxx);
		TGdbgxx.setTQyjbxx(this);

		return TGdbgxx;
	}

	public TGdbgxx removeTGdbgxx(TGdbgxx TGdbgxx) {
		getTGdbgxxs().remove(TGdbgxx);
		TGdbgxx.setTQyjbxx(null);

		return TGdbgxx;
	}

	public Set<TGqbgxx> getTGqbgxxs() {
		return this.TGqbgxxs;
	}

	public void setTGqbgxxs(Set<TGqbgxx> TGqbgxxs) {
		this.TGqbgxxs = TGqbgxxs;
	}

	public TGqbgxx addTGqbgxx(TGqbgxx TGqbgxx) {
		getTGqbgxxs().add(TGqbgxx);
		TGqbgxx.setTQyjbxx(this);

		return TGqbgxx;
	}

	public TGqbgxx removeTGqbgxx(TGqbgxx TGqbgxx) {
		getTGqbgxxs().remove(TGqbgxx);
		TGqbgxx.setTQyjbxx(null);

		return TGqbgxx;
	}

	public Set<TGqczdjxx> getTGqczdjxxs() {
		return this.TGqczdjxxs;
	}

	public void setTGqczdjxxs(Set<TGqczdjxx> TGqczdjxxs) {
		this.TGqczdjxxs = TGqczdjxxs;
	}

	public TGqczdjxx addTGqczdjxx(TGqczdjxx TGqczdjxx) {
		getTGqczdjxxs().add(TGqczdjxx);
		TGqczdjxx.setTQyjbxx(this);

		return TGqczdjxx;
	}

	public TGqczdjxx removeTGqczdjxx(TGqczdjxx TGqczdjxx) {
		getTGqczdjxxs().remove(TGqczdjxx);
		TGqczdjxx.setTQyjbxx(null);

		return TGqczdjxx;
	}

	public Set<TGqdjxx> getTGqdjxxs() {
		return this.TGqdjxxs;
	}

	public void setTGqdjxxs(Set<TGqdjxx> TGqdjxxs) {
		this.TGqdjxxs = TGqdjxxs;
	}

	public TGqdjxx addTGqdjxx(TGqdjxx TGqdjxx) {
		getTGqdjxxs().add(TGqdjxx);
		TGqdjxx.setTQyjbxx(this);

		return TGqdjxx;
	}

	public TGqdjxx removeTGqdjxx(TGqdjxx TGqdjxx) {
		getTGqdjxxs().remove(TGqdjxx);
		TGqdjxx.setTQyjbxx(null);

		return TGqdjxx;
	}

	public TGsxtHtml getTGsxtHtml() {
		return TGsxtHtml;
	}

	public void setTGsxtHtml(TGsxtHtml tGsxtHtml) {
		TGsxtHtml = tGsxtHtml;
	}

	public Set<TJyychyzwfxx> getTJyychyzwfxxs() {
		return this.TJyychyzwfxxs;
	}

	public void setTJyychyzwfxxs(Set<TJyychyzwfxx> TJyychyzwfxxs) {
		this.TJyychyzwfxxs = TJyychyzwfxxs;
	}

	public TJyychyzwfxx addTJyychyzwfxx(TJyychyzwfxx TJyychyzwfxx) {
		getTJyychyzwfxxs().add(TJyychyzwfxx);
		TJyychyzwfxx.setTQyjbxx(this);

		return TJyychyzwfxx;
	}

	public TJyychyzwfxx removeTJyychyzwfxx(TJyychyzwfxx TJyychyzwfxx) {
		getTJyychyzwfxxs().remove(TJyychyzwfxx);
		TJyychyzwfxx.setTQyjbxx(null);

		return TJyychyzwfxx;
	}

	public Set<TMember> getTMembers() {
		return this.TMembers;
	}

	public void setTMembers(Set<TMember> TMembers) {
		this.TMembers = TMembers;
	}

	public TMember addTMember(TMember TMember) {
		getTMembers().add(TMember);
		TMember.setTQyjbxx(this);

		return TMember;
	}

	public TMember removeTMember(TMember TMember) {
		getTMembers().remove(TMember);
		TMember.setTQyjbxx(null);

		return TMember;
	}


	public TQsxx getTQsxx() {
		return TQsxx;
	}

	public void setTQsxx(TQsxx tQsxx) {
		TQsxx = tQsxx;
	}

	public TQtbmgsXzxkxx getTQtbmgsXzxkxx() {
		return TQtbmgsXzxkxx;
	}

	public void setTQtbmgsXzxkxx(TQtbmgsXzxkxx tQtbmgsXzxkxx) {
		TQtbmgsXzxkxx = tQtbmgsXzxkxx;
	}

	public TQygsGdjczxx getTQygsGdjczxx() {
		return TQygsGdjczxx;
	}

	public void setTQygsGdjczxx(TQygsGdjczxx tQygsGdjczxx) {
		TQygsGdjczxx = tQygsGdjczxx;
	}

	public Set<TQynb> getTQynbs() {
		return this.TQynbs;
	}

	public void setTQynbs(Set<TQynb> TQynbs) {
		this.TQynbs = TQynbs;
	}

	public TQynb addTQynb(TQynb TQynb) {
		getTQynbs().add(TQynb);
		TQynb.setTQyjbxx(this);

		return TQynb;
	}

	public TQynb removeTQynb(TQynb TQynb) {
		getTQynbs().remove(TQynb);
		TQynb.setTQyjbxx(null);

		return TQynb;
	}

	public Set<TXzcfxx> getTXzcfxxs() {
		return this.TXzcfxxs;
	}

	public void setTXzcfxxs(Set<TXzcfxx> TXzcfxxs) {
		this.TXzcfxxs = TXzcfxxs;
	}

	public TXzcfxx addTXzcfxx(TXzcfxx TXzcfxx) {
		getTXzcfxxs().add(TXzcfxx);
		TXzcfxx.setTQyjbxx(this);

		return TXzcfxx;
	}

	public TXzcfxx removeTXzcfxx(TXzcfxx TXzcfxx) {
		getTXzcfxxs().remove(TXzcfxx);
		TXzcfxx.setTQyjbxx(null);

		return TXzcfxx;
	}

	public Set<TXzxkxx> getTXzxkxxs() {
		return this.TXzxkxxs;
	}

	public void setTXzxkxxs(Set<TXzxkxx> TXzxkxxs) {
		this.TXzxkxxs = TXzxkxxs;
	}

	public TXzxkxx addTXzxkxx(TXzxkxx TXzxkxx) {
		getTXzxkxxs().add(TXzxkxx);
		TXzxkxx.setTQyjbxx(this);

		return TXzxkxx;
	}

	public TXzxkxx removeTXzxkxx(TXzxkxx TXzxkxx) {
		getTXzxkxxs().remove(TXzxkxx);
		TXzxkxx.setTQyjbxx(null);

		return TXzxkxx;
	}

	public Set<TZscqczdjxx> getTZscqczdjxxs() {
		return this.TZscqczdjxxs;
	}

	public void setTZscqczdjxxs(Set<TZscqczdjxx> TZscqczdjxxs) {
		this.TZscqczdjxxs = TZscqczdjxxs;
	}

	public TZscqczdjxx addTZscqczdjxx(TZscqczdjxx TZscqczdjxx) {
		getTZscqczdjxxs().add(TZscqczdjxx);
		TZscqczdjxx.setTQyjbxx(this);

		return TZscqczdjxx;
	}

	public TZscqczdjxx removeTZscqczdjxx(TZscqczdjxx TZscqczdjxx) {
		getTZscqczdjxxs().remove(TZscqczdjxx);
		TZscqczdjxx.setTQyjbxx(null);

		return TZscqczdjxx;
	}

	public Set<TZyryxx> getTZyryxxs() {
		return this.TZyryxxs;
	}

	public void setTZyryxxs(Set<TZyryxx> TZyryxxs) {
		this.TZyryxxs = TZyryxxs;
	}

	public TZyryxx addTZyryxx(TZyryxx TZyryxx) {
		getTZyryxxs().add(TZyryxx);
		TZyryxx.setTQyjbxx(this);

		return TZyryxx;
	}

	public TZyryxx removeTZyryxx(TZyryxx TZyryxx) {
		getTZyryxxs().remove(TZyryxx);
		TZyryxx.setTQyjbxx(null);

		return TZyryxx;
	}

	public Company getCompany() {
		return Company;
	}

	public void setCompany(Company company) {
		Company = company;
	}

	public Set<TZgbmxx> getTZgbmxxs() {
		return TZgbmxxs;
	}

	public void setTZgbmxxs(Set<TZgbmxx> tZgbmxxs) {
		TZgbmxxs = tZgbmxxs;
	}

	@Override
	public String toString() {
		return "TQyjbxx [id=" + id + ", address=" + address + ", approvaldate="
				+ approvaldate + ", businessscope=" + businessscope
				+ ", enddate=" + enddate + ", html=" + html + ", infotype="
				+ infotype + ", legalrepr=" + legalrepr + ", name=" + name
				+ ", num=" + num + ", regauthority=" + regauthority
				+ ", registeredcapital=" + registeredcapital
				+ ", registereddate=" + registereddate + ", formtype="
				+ formtype + ", regstatus=" + regstatus + ", revokedate="
				+ revokedate + ", startdate=" + startdate + ", type=" + type
				+ ", executetime=" + executetime + ", TBgxxs=" + TBgxxs
				+ ", TCxxxs=" + TCxxxs + ", TCcjcxxs=" + TCcjcxxs
				+ ", TDcdydjxxs=" + TDcdydjxxs + ", TFzjgxxs=" + TFzjgxxs
				+ ", TGdbgxxs=" + TGdbgxxs + ", TGqbgxxs=" + TGqbgxxs
				+ ", TGqczdjxxs=" + TGqczdjxxs + ", TGqdjxxs=" + TGqdjxxs
				+ ", TGsxtHtml=" + TGsxtHtml + ", TJyychyzwfxxs="
				+ TJyychyzwfxxs + ", TMembers=" + TMembers + ", TQsxx=" + TQsxx
				+ ", TQtbmgsXzxkxx=" + TQtbmgsXzxkxx + ", TQygsGdjczxx="
				+ TQygsGdjczxx + ", TQynbs=" + TQynbs + ", TXzcfxxs="
				+ TXzcfxxs + ", TXzxkxxs=" + TXzxkxxs + ", TZscqczdjxxs="
				+ TZscqczdjxxs + ", TZyryxxs=" + TZyryxxs + ", TZgbmxxs="
				+ TZgbmxxs + ", Company=" + Company + "]";
	}
}