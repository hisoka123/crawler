package com.module.dao.entity.qiyezhengxin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="k_enterprise_credit")
@NamedQuery(name="Zhengxin.findAll", query="SELECT t FROM Zhengxin t")
public class Zhengxin implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String date;//发布时间
	private String company;//发布单位
	private String content;//档案内容
	private Date executetime;//创建时间
	private String type;//（行政奖罚信息，行业协会(社会组织)评价信息）
	private String title;//档案标题
	private String link;//标题连接
	private String property;//档案性质
	@ManyToOne
	@JoinColumn(name="zhengxinKeyword_id")
	private ZhengxinKeyword zhengxinKeyword;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ZhengxinKeyword getZhengxinKeyword() {
		return zhengxinKeyword;
	}
	public void setZhengxinKeyword(ZhengxinKeyword zhengxinKeyword) {
		this.zhengxinKeyword = zhengxinKeyword;
	}
	public Date getExecutetime() {
		return executetime;
	}
	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Zhengxin(){}
	public Zhengxin(String date, String company, String content, String type,
			String title, String link, String property) {
		super();
		this.date = date;
		this.company = company;
		this.content = content;
		this.type = type;
		this.title = title;
		this.link = link;
		this.property = property;
	}

	

}
