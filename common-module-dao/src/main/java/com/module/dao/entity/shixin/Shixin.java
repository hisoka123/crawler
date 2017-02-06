package com.module.dao.entity.shixin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="s_dishonesty")
@SequenceGenerator(name="shixinSeq",sequenceName="shixin_sequence",allocationSize=20)
public class Shixin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5258010405614390063L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;//姓名
	private String sexy;//性别
	private String age;//年龄
	private String cardNum;//身份证号码/组织机构代码
	private String courtName;//执行法院
	private String areaName;//省份
	private String gistId;//执行依据文号
	private String regDate;//立案时间
	private String caseCode;//案号
	private String gistUnit;//做出执行依据单位
	private String duty;//生效法律文书确定的义务
	private String performance;//被执行人的履行情况
	private String disruptTypeName;//失信被执行人行为具体情形
	private String publishDate;//发布时间
	private Date executetime;//创建时间
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="shixinKeyword_id")
	private ShixinKeyword shixinKeyword;
	
	public Shixin(){}
	
	public Shixin(String name,String sexy,String age,String cardNum,
			      String courtName,String areaName,String gistId,
			      String regDate,String caseCode,String gistUnit,
			      String duty,String performance,String disruptTypeName,
			      String publishDate){
		
		     this.name=name;
		     this.sexy=sexy;
		     this.age=age;
		     this.cardNum=cardNum;
		     this.courtName=courtName;
		     this.areaName=areaName;
		     this.gistId=gistId;
		     this.regDate=regDate;
		     this.caseCode=caseCode;
		     this.gistUnit=gistUnit;
		     this.duty=duty;
		     this.performance=performance;
		     this.disruptTypeName=disruptTypeName;
		     this.publishDate=publishDate;
		
		
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
	public String getSexy() {
		return sexy;
	}
	public void setSexy(String sexy) {
		this.sexy = sexy;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getGistId() {
		return gistId;
	}
	public void setGistId(String gistId) {
		this.gistId = gistId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getGistUnit() {
		return gistUnit;
	}
	public void setGistUnit(String gistUnit) {
		this.gistUnit = gistUnit;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getDisruptTypeName() {
		return disruptTypeName;
	}
	public void setDisruptTypeName(String disruptTypeName) {
		this.disruptTypeName = disruptTypeName;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public Date getExecutetime() {
		return executetime;
	}
	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}
	public ShixinKeyword getShixinKeyword() {
		return shixinKeyword;
	}
	public void setShixinKeyword(ShixinKeyword shixinKeyword) {
		this.shixinKeyword = shixinKeyword;
	}

	
}
