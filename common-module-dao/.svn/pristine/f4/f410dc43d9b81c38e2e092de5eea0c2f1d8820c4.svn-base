package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the t_qynb database table.
 * 
 */
@Entity
@Table(name="t_qynb")
@NamedQuery(name="TQynb.findAll", query="SELECT t FROM TQynb t")
public class TQynb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String address;

	private String assetamount;

	private String email;

	private String empnum;

	private String haswzhwd;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="html_jbxx",columnDefinition="text")
	private String htmlJbxx;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="html_qyzczkxx",columnDefinition="text")
	private String htmlQyzczkxx;

	private String infotype;

	private String istzxxhgmqtgsgq;

	private String isyxzrgsbndgdgqzr;
	
	private String operatingStatus;

	private String liabilityamount;

	private String name;

	private String netprofit;

	private String num;

	private String profitamount;

	private String pubdate;
	
	private String firstdate;

	private String salesamount;

	private String submityear;

	private String syzqyhj;

	private String taxesamount;

	private String tel;

	private String xszezzyywsr;

	private String zipcode;
	
	//法定代表人/经营者
	private String legalrepr;
	
	//注册资本
	private String registeredcapital;
	
	//资金数额
	private String capitalamount;
	
	//隶属关系
	private String affiliation;
	
	//合作社名称
	private String cooperativeName; 
	
	//成员人数
	private String membersNum;

	//获得政府扶持资金、补助 
	private String governmentFunds;
	
	//金融贷款
	private String financialLoan;
	
	//bi-directional many-to-one association to TBgxx
	@OneToMany(mappedBy="TQynb",cascade=CascadeType.ALL)
	private Set<TBgxx> TBgxxs;

	//bi-directional many-to-one association to TDwtgbzdbxx
	@OneToMany(mappedBy="TQynb",cascade=CascadeType.ALL)
	private Set<TDwtgbzdbxx> TDwtgbzdbxxs;

	//bi-directional many-to-one association to TDwtzxx
	@OneToMany(mappedBy="TQynb",cascade=CascadeType.ALL)
	private Set<TDwtzxx> TDwtzxxs;

	//bi-directional many-to-one association to TGdjczxx
	@OneToMany(mappedBy="TQynb",cascade=CascadeType.ALL)
	private Set<TGdjczxx> TGdjczxxs;

	//bi-directional many-to-one association to TGqbgxx
	@OneToMany(mappedBy="TQynb",cascade=CascadeType.ALL)
	private Set<TGqbgxx> TGqbgxxs;

	//bi-directional many-to-one association to TQyjbxx
	@ManyToOne
	@JoinColumn(name="qyjbxx_id")
	private TQyjbxx TQyjbxx;

	//bi-directional many-to-one association to TWzhwdxx
	@OneToMany(mappedBy="TQynb",cascade=CascadeType.ALL)
	private Set<TWzhwdxx> TWzhwdxxs;

	@OneToMany(mappedBy="TQynb",cascade=CascadeType.ALL)
	private Set<TXzxkxx> TXzxkxxs;
	
	@OneToMany(mappedBy="TQynb",cascade=CascadeType.ALL)
	private Set<TScjyqk> TScjyqks;
	
	public TQynb() {
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

	public String getAssetamount() {
		return this.assetamount;
	}

	public void setAssetamount(String assetamount) {
		this.assetamount = assetamount;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpnum() {
		return this.empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}

	public String getHaswzhwd() {
		return this.haswzhwd;
	}

	public void setHaswzhwd(String haswzhwd) {
		this.haswzhwd = haswzhwd;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getHtmlJbxx() {
		return this.htmlJbxx;
	}

	public void setHtmlJbxx(String htmlJbxx) {
		this.htmlJbxx = htmlJbxx;
	}

	public String getHtmlQyzczkxx() {
		return this.htmlQyzczkxx;
	}

	public void setHtmlQyzczkxx(String htmlQyzczkxx) {
		this.htmlQyzczkxx = htmlQyzczkxx;
	}

	public String getInfotype() {
		return this.infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getIstzxxhgmqtgsgq() {
		return this.istzxxhgmqtgsgq;
	}

	public void setIstzxxhgmqtgsgq(String istzxxhgmqtgsgq) {
		this.istzxxhgmqtgsgq = istzxxhgmqtgsgq;
	}

	public String getIsyxzrgsbndgdgqzr() {
		return this.isyxzrgsbndgdgqzr;
	}

	public void setIsyxzrgsbndgdgqzr(String isyxzrgsbndgdgqzr) {
		this.isyxzrgsbndgdgqzr = isyxzrgsbndgdgqzr;
	}

	public String getOperatingStatus() {
		return operatingStatus;
	}

	public void setOperatingStatus(String operatingStatus) {
		this.operatingStatus = operatingStatus;
	}

	public String getLiabilityamount() {
		return this.liabilityamount;
	}

	public void setLiabilityamount(String liabilityamount) {
		this.liabilityamount = liabilityamount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetprofit() {
		return this.netprofit;
	}

	public void setNetprofit(String netprofit) {
		this.netprofit = netprofit;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getProfitamount() {
		return this.profitamount;
	}

	public void setProfitamount(String profitamount) {
		this.profitamount = profitamount;
	}

	public String getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getSalesamount() {
		return this.salesamount;
	}

	public void setSalesamount(String salesamount) {
		this.salesamount = salesamount;
	}

	public String getSubmityear() {
		return this.submityear;
	}

	public void setSubmityear(String submityear) {
		this.submityear = submityear;
	}

	public String getSyzqyhj() {
		return this.syzqyhj;
	}

	public void setSyzqyhj(String syzqyhj) {
		this.syzqyhj = syzqyhj;
	}

	public String getTaxesamount() {
		return this.taxesamount;
	}

	public void setTaxesamount(String taxesamount) {
		this.taxesamount = taxesamount;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getXszezzyywsr() {
		return this.xszezzyywsr;
	}

	public void setXszezzyywsr(String xszezzyywsr) {
		this.xszezzyywsr = xszezzyywsr;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Set<TBgxx> getTBgxxs() {
		return this.TBgxxs;
	}

	public void setTBgxxs(Set<TBgxx> TBgxxs) {
		this.TBgxxs = TBgxxs;
	}

	public TBgxx addTBgxx(TBgxx TBgxx) {
		getTBgxxs().add(TBgxx);
		TBgxx.setTQynb(this);

		return TBgxx;
	}

	public TBgxx removeTBgxx(TBgxx TBgxx) {
		getTBgxxs().remove(TBgxx);
		TBgxx.setTQynb(null);

		return TBgxx;
	}

	public Set<TDwtgbzdbxx> getTDwtgbzdbxxs() {
		return this.TDwtgbzdbxxs;
	}

	public void setTDwtgbzdbxxs(Set<TDwtgbzdbxx> TDwtgbzdbxxs) {
		this.TDwtgbzdbxxs = TDwtgbzdbxxs;
	}

	public TDwtgbzdbxx addTDwtgbzdbxx(TDwtgbzdbxx TDwtgbzdbxx) {
		getTDwtgbzdbxxs().add(TDwtgbzdbxx);
		TDwtgbzdbxx.setTQynb(this);

		return TDwtgbzdbxx;
	}

	public TDwtgbzdbxx removeTDwtgbzdbxx(TDwtgbzdbxx TDwtgbzdbxx) {
		getTDwtgbzdbxxs().remove(TDwtgbzdbxx);
		TDwtgbzdbxx.setTQynb(null);

		return TDwtgbzdbxx;
	}

	public Set<TDwtzxx> getTDwtzxxs() {
		return this.TDwtzxxs;
	}

	public void setTDwtzxxs(Set<TDwtzxx> TDwtzxxs) {
		this.TDwtzxxs = TDwtzxxs;
	}

	public TDwtzxx addTDwtzxx(TDwtzxx TDwtzxx) {
		getTDwtzxxs().add(TDwtzxx);
		TDwtzxx.setTQynb(this);

		return TDwtzxx;
	}

	public TDwtzxx removeTDwtzxx(TDwtzxx TDwtzxx) {
		getTDwtzxxs().remove(TDwtzxx);
		TDwtzxx.setTQynb(null);

		return TDwtzxx;
	}

	public Set<TGdjczxx> getTGdjczxxs() {
		return this.TGdjczxxs;
	}

	public void setTGdjczxxs(Set<TGdjczxx> TGdjczxxs) {
		this.TGdjczxxs = TGdjczxxs;
	}

	public TGdjczxx addTGdjczxx(TGdjczxx TGdjczxx) {
		getTGdjczxxs().add(TGdjczxx);
		TGdjczxx.setTQynb(this);

		return TGdjczxx;
	}

	public TGdjczxx removeTGdjczxx(TGdjczxx TGdjczxx) {
		getTGdjczxxs().remove(TGdjczxx);
		TGdjczxx.setTQynb(null);

		return TGdjczxx;
	}

	public Set<TGqbgxx> getTGqbgxxs() {
		return this.TGqbgxxs;
	}

	public void setTGqbgxxs(Set<TGqbgxx> TGqbgxxs) {
		this.TGqbgxxs = TGqbgxxs;
	}

	public TGqbgxx addTGqbgxx(TGqbgxx TGqbgxx) {
		getTGqbgxxs().add(TGqbgxx);
		TGqbgxx.setTQynb(this);

		return TGqbgxx;
	}

	public TGqbgxx removeTGqbgxx(TGqbgxx TGqbgxx) {
		getTGqbgxxs().remove(TGqbgxx);
		TGqbgxx.setTQynb(null);

		return TGqbgxx;
	}

	public TQyjbxx getTQyjbxx() {
		return this.TQyjbxx;
	}

	public void setTQyjbxx(TQyjbxx TQyjbxx) {
		this.TQyjbxx = TQyjbxx;
	}

	public Set<TWzhwdxx> getTWzhwdxxs() {
		return this.TWzhwdxxs;
	}

	public void setTWzhwdxxs(Set<TWzhwdxx> TWzhwdxxs) {
		this.TWzhwdxxs = TWzhwdxxs;
	}

	public TWzhwdxx addTWzhwdxx(TWzhwdxx TWzhwdxx) {
		getTWzhwdxxs().add(TWzhwdxx);
		TWzhwdxx.setTQynb(this);

		return TWzhwdxx;
	}

	public TWzhwdxx removeTWzhwdxx(TWzhwdxx TWzhwdxx) {
		getTWzhwdxxs().remove(TWzhwdxx);
		TWzhwdxx.setTQynb(null);

		return TWzhwdxx;
	}

	public String getLegalrepr() {
		return legalrepr;
	}

	public void setLegalrepr(String legalrepr) {
		this.legalrepr = legalrepr;
	}

	public String getRegisteredcapital() {
		return registeredcapital;
	}

	public void setRegisteredcapital(String registeredcapital) {
		this.registeredcapital = registeredcapital;
	}

	public String getCapitalamount() {
		return capitalamount;
	}

	public void setCapitalamount(String capitalamount) {
		this.capitalamount = capitalamount;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public Set<TXzxkxx> getTXzxkxxs() {
		return TXzxkxxs;
	}

	public void setTXzxkxxs(Set<TXzxkxx> tXzxkxxs) {
		TXzxkxxs = tXzxkxxs;
	}

	public Set<TScjyqk> getTScjyqks() {
		return TScjyqks;
	}

	public void setTScjyqks(Set<TScjyqk> tScjyqks) {
		TScjyqks = tScjyqks;
	}

	public String getFirstdate() {
		return firstdate;
	}

	public void setFirstdate(String firstdate) {
		this.firstdate = firstdate;
	}

	public String getCooperativeName() {
		return cooperativeName;
	}

	public void setCooperativeName(String cooperativeName) {
		this.cooperativeName = cooperativeName;
	}

	public String getMembersNum() {
		return membersNum;
	}

	public void setMembersNum(String membersNum) {
		this.membersNum = membersNum;
	}

	public String getGovernmentFunds() {
		return governmentFunds;
	}

	public void setGovernmentFunds(String governmentFunds) {
		this.governmentFunds = governmentFunds;
	}

	public String getFinancialLoan() {
		return financialLoan;
	}

	public void setFinancialLoan(String financialLoan) {
		this.financialLoan = financialLoan;
	}
}