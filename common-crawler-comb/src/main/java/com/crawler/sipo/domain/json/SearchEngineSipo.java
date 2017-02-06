package com.crawler.sipo.domain.json;

import java.io.Serializable;
import java.util.List;

public class SearchEngineSipo implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private String licenseName; //专利名称
	
	private String  licenseNum;    //授权公告号
	
	private String  licenseDate;    //授权公告日
	
	private String applicationNum;//申请号
	
	private String applicationDate;//申请日
	
	private String patentHolder ;//专利权人
	
	private String inventor;//发明人
	
	private String address;//地址
	
	private String classNumber;//分类号
	
	private String summary;//摘要
	
	private String num;//参数
	
	private String  img;//图片
		
	private String  qrcode;//二维码图片
	private List<TransactionDataSipo> transactionDatas;  //事务数据类型
	

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

	public List<TransactionDataSipo> getTransactionDatas() {
		return transactionDatas;
	}

	public void setTransactionDatas(List<TransactionDataSipo> transactionDatas) {
		this.transactionDatas = transactionDatas;
	}

}
