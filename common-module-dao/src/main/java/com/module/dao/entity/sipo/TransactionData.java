package com.module.dao.entity.sipo;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="l_sipo_transactionData")
@NamedQuery(name="TransactionData.findAll", query="SELECT t FROM TransactionData t")
public class TransactionData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6745371771472410053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String num;   //申请（专利）号
	 
	private String date;  //事务数据公告日
	
	private String type;  //事务数据类型
	
	@Column(length=1024)
	private String content;  //内容
	
	//执行时间
	private Date executetime; 

	@ManyToOne
	@JoinColumn(name="sipo_id")
	private Sipo sipo;
	
	public TransactionData(){}
	
	public TransactionData(Long id,String num,String date,String type,String content){
		
		  this.id=id;
		  this.num=num;
		  this.date=date;
		  this.type=type;
		  this.content=content; 		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public Sipo getSipo() {
		return sipo;
	}

	public void setSipo(Sipo sipo) {
		this.sipo = sipo;
	}

	@Override
	public String toString() {
		return "TransactionData [id=" + id + ", num=" + num + ", date=" + date
				+ ", type=" + type + ", content=" + content + ", executetime="
				+ executetime + ", sipo=" + sipo + "]";
	}
   
	
}
