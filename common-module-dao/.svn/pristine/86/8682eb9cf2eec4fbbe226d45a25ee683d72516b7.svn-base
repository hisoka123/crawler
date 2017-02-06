package com.module.dao.entity.sipo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="l_sipo_result")
@NamedQuery(name="Sipo.findAll", query="SELECT t FROM Sipo t")
public class Sipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3316131045564918577L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String licenseName; //专利名称
	
	private String  licenseNum;    //授权公告号
	
	private String  licenseDate;    //授权公告日
	
	private String applicationNum;//申请号
	
	private String applicationDate;//申请日
	
	private String patentHolder ;//专利权人
	
	private String inventor;//发明人
	
	private String address;//地址
	
	private String classNumber;//分类号
	
	@Column(length=1024)
	private String summary;//摘要
	
	private String num;//参数
	
	private String img;//图片
		
	private String qrcode;//二维码图片

	@OneToMany(mappedBy="sipo",cascade=CascadeType.ALL)
	private List<TransactionData> transactionDatas= new ArrayList<TransactionData>();

	//执行时间
	private Date executetime; 
	
	
	@ManyToOne
	@JoinColumn(name="sipoKeyword_id")
	private SipoKeyword sipoKeyword;

	
	public Sipo(){}
	
	public Sipo(Long id,String licenseName,String applicationNum,String patentHolder,String inventor,String classNumber,String summary,String num,String img){		
		    this.id=id;
		    this.licenseName=licenseName;
		    this.applicationNum=applicationNum;
		    this.patentHolder= patentHolder;
		    this.inventor=inventor;
		    this.classNumber=classNumber;
		    this.summary=summary;
		    this.num=num;		  
		    this.img=img;		   
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	public String getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getApplicationNum() {
		return applicationNum;
	}

	public void setApplicationNum(String applicationNum) {
		this.applicationNum = applicationNum;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getPatentHolder() {
		return patentHolder;
	}

	public void setPatentHolder(String patentHolder) {
		this.patentHolder = patentHolder;
	}

	public String getInventor() {
		return inventor;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public SipoKeyword getSipoKeyword() {
		return sipoKeyword;
	}

	public void setSipoKeyword(SipoKeyword sipoKeyword) {
		this.sipoKeyword = sipoKeyword;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}
	
	public List<TransactionData> getTransactionDatas() {
		return transactionDatas;
	}

	public void setTransactionDatas(List<TransactionData> transactionDatas) {
		this.transactionDatas = transactionDatas;
	}

	@Override
	public String toString() {
		return "Sipo [id=" + id + ", licenseName=" + licenseName
				+ ", licenseNum=" + licenseNum + ", licenseDate=" + licenseDate
				+ ", applicationNum=" + applicationNum + ", applicationDate="
				+ applicationDate + ", patentHolder=" + patentHolder
				+ ", inventor=" + inventor + ", address=" + address
				+ ", classNumber=" + classNumber + ", summary=" + summary
				+ ", num=" + num + ", img=" + img + ", qrcode=" + qrcode
				+ ", transactionDatas=" + transactionDatas + ", executetime="
				+ executetime + ", sipoKeyword=" + sipoKeyword + "]";
	}

	
	
}
