/**
 * 
 */
package com.module.dao.entity.fahaicc;

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
@Table(name="f_result")
@NamedQuery(name="FahaiccResult.findAll", query="SELECT t FROM FahaiccResult t")
public class FahaiccResult implements Serializable {
	private static final long serialVersionUID = 3579843155384125306L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//用户名
	private String username;
	
	//标题
	private String title;
	
	//链接
	private String linkUrl;
	
	//立案时间
	private String filingDate;
	
	//开庭日期
	private String courtDate;
	
	//审结日期
	private String conclusionDate;
	
	//发布日期
	private String pubDate;
	
	//机构
	private String authority;
	
	//类型
	private String type;

	//文本内容
	@Column(length=1024)
	private String content;
	
	//HTML内容
	@Column(length=1024)
	private String contentHtml;

	//执行时间
	private Date executeTime;
	
	@ManyToOne
	@JoinColumn(name="root_id")
	private FahaiccRoot fahaiccRoot;
	
	
	public FahaiccResult(){}
	
	public FahaiccResult(String title,String linkUrl,String filingDate,String courtDate,
			             String conclusionDate,String pubDate,String authority,String type){
		
		   this.title=title;
		   this.linkUrl=linkUrl;
		   this.filingDate=filingDate;
		   this.courtDate=courtDate;
		   this.conclusionDate=conclusionDate;
		   this.pubDate=pubDate;
		   this.authority=authority;
		   this.type=type;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}

	public String getCourtDate() {
		return courtDate;
	}

	public void setCourtDate(String courtDate) {
		this.courtDate = courtDate;
	}

	public String getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(String conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
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

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FahaiccRoot getFahaiccRoot() {
		return fahaiccRoot;
	}

	public void setFahaiccRoot(FahaiccRoot fahaiccRoot) {
		this.fahaiccRoot = fahaiccRoot;
	}
	
	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	@Override
	public String toString() {
		return "FahaiccResult [id=" + id + ", username=" + username
				+ ", title=" + title + ", linkUrl=" + linkUrl + ", filingDate="
				+ filingDate + ", courtDate=" + courtDate + ", conclusionDate="
				+ conclusionDate + ", pubDate=" + pubDate + ", authority="
				+ authority + ", type=" + type + ", content=" + content
				+ ", contentHtml=" + contentHtml + ", executeTime="
				+ executeTime + "]";
	}
}
