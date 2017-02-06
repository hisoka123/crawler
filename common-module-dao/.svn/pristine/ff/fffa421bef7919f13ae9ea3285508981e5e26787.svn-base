package com.module.dao.entity.dailianmeng;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="d_loan_union")
@NamedQuery(name="LoanUnion.findAll", query="SELECT t FROM LoanUnion t")
public class LoanUnion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//被执行人姓名/名称
	private String name;
	
	//案号
	private String caseNum;
	
	//年龄
	private String age;

	//性别
	private String sex;
	
	//身份证号/组织机构代码
	private String cardID;
	
	//法定代表人或者负责人
	private String legalPerson;
	
	//执行法院
	private String executeCourt;
	
	//省份
	private String province;
	
	//执行依据文号
	private String executeNum;
	
	//立案时间
	private String caseDate;
	
	//做出执行依据单位
	private String dependCourt;
	
	//生效法律文书确定的义务
	@Column(length=1024)
	private String effectNum;
	
	//被执行人的履行情况
	private String executeSituation;
	
	//已履行
	private String alreadyExecute;
	
	//未履行
	private String noExecute;
	
	//失信被执行人行为情形
	private String behaviorSituation;
	
	//发布时间
	private String pubDate;
	
	//更新时间
	private String updateDate;
	
	//定时任务执行时间
	private Date executetime;
	
	//债务的金额
	private String debtMoney;
	
	//贷款日期
	private String loanDate;
	
	//贷款期限
	private String loanTerm;
	
	//名单类型
	private String listType;
	
	//借款状态
	private String loanState;
	
	//描述
	private String describe;
	
	//出生日期
	private String birthday;
	
	//发证地点
	private String issuePlace;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="company_or_identity_id")
	private CompanyOrIdentity companyOrIdentity;
	
	public LoanUnion() {}
	
	public LoanUnion(String name, String caseNum, String age, String sex,
			String cardID, String legalPerson, String executeCourt,
			String province, String executeNum, String caseDate,
			String dependCourt, String effectNum, String executeSituation,
			String alreadyExecute, String noExecute, String behaviorSituation,
			String pubDate, String updateDate,
			String debtMoney, String loanDate, String loanTerm,
			String listType, String loanState, String describe,
			String birthday, String issuePlace) {
		super();
		this.name = name;
		this.caseNum = caseNum;
		this.age = age;
		this.sex = sex;
		this.cardID = cardID;
		this.legalPerson = legalPerson;
		this.executeCourt = executeCourt;
		this.province = province;
		this.executeNum = executeNum;
		this.caseDate = caseDate;
		this.dependCourt = dependCourt;
		this.effectNum = effectNum;
		this.executeSituation = executeSituation;
		this.alreadyExecute = alreadyExecute;
		this.noExecute = noExecute;
		this.behaviorSituation = behaviorSituation;
		this.pubDate = pubDate;
		this.updateDate = updateDate;
		this.debtMoney = debtMoney;
		this.loanDate = loanDate;
		this.loanTerm = loanTerm;
		this.listType = listType;
		this.loanState = loanState;
		this.describe = describe;
		this.birthday = birthday;
		this.issuePlace = issuePlace;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getExecuteCourt() {
		return executeCourt;
	}

	public void setExecuteCourt(String executeCourt) {
		this.executeCourt = executeCourt;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getExecuteNum() {
		return executeNum;
	}

	public void setExecuteNum(String executeNum) {
		this.executeNum = executeNum;
	}

	public String getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}

	public String getDependCourt() {
		return dependCourt;
	}

	public void setDependCourt(String dependCourt) {
		this.dependCourt = dependCourt;
	}

	public String getEffectNum() {
		return effectNum;
	}

	public void setEffectNum(String effectNum) {
		this.effectNum = effectNum;
	}

	public String getExecuteSituation() {
		return executeSituation;
	}

	public void setExecuteSituation(String executeSituation) {
		this.executeSituation = executeSituation;
	}

	public String getAlreadyExecute() {
		return alreadyExecute;
	}

	public void setAlreadyExecute(String alreadyExecute) {
		this.alreadyExecute = alreadyExecute;
	}

	public String getNoExecute() {
		return noExecute;
	}

	public void setNoExecute(String noExecute) {
		this.noExecute = noExecute;
	}

	public String getBehaviorSituation() {
		return behaviorSituation;
	}

	public void setBehaviorSituation(String behaviorSituation) {
		this.behaviorSituation = behaviorSituation;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public CompanyOrIdentity getCompanyOrIdentity() {
		return companyOrIdentity;
	}

	public void setCompanyOrIdentity(CompanyOrIdentity companyOrIdentity) {
		this.companyOrIdentity = companyOrIdentity;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public String getDebtMoney() {
		return debtMoney;
	}

	public void setDebtMoney(String debtMoney) {
		this.debtMoney = debtMoney;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getLoanState() {
		return loanState;
	}

	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIssuePlace() {
		return issuePlace;
	}

	public void setIssuePlace(String issuePlace) {
		this.issuePlace = issuePlace;
	}
	
}
