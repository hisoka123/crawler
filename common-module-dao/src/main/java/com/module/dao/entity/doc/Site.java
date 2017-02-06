package com.module.dao.entity.doc;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/** 
 * @author hmy
 * @date：Jan 6, 2016 
 * 
 */


@Entity
@Table(name="Site")
@SequenceGenerator(name="siteSeq",sequenceName="site_sequence",allocationSize=30)
public class Site implements Serializable{
	
	private static final long serialVersionUID = -4261905303847487304L;
      
	@Id
	@GeneratedValue(generator="siteSeq",strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String pyName;   //名字的拼音，或英文名字
	private int isEnabled;  //1可用，0不可用
	private String siteInfo;   //站点描述
	private String siteLogo;    //站点logo url
	private String type;        //站点类型
	private String linkUrl;    //站内应用界面
	private String siteURL;    //网站地址
	private String ownerTaskUrl;   //站内‘我的任务’url
	
	public Site(){}
	
	public Site(String type){
		this.type=type;
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
	public String getPyName() {
		return pyName;
	}
	public void setPyName(String pyName) {
		this.pyName = pyName;
	}
	public int getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getSiteInfo() {
		return siteInfo;
	}
	public void setSiteInfo(String siteInfo) {
		this.siteInfo = siteInfo;
	}
	public String getSiteLogo() {
		return siteLogo;
	}
	public void setSiteLogo(String siteLogo) {
		this.siteLogo = siteLogo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	
	
	public String getSiteURL() {
		return siteURL;
	}

	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

	
	
	public String getOwnerTaskUrl() {
		return ownerTaskUrl;
	}

	public void setOwnerTaskUrl(String ownerTaskUrl) {
		this.ownerTaskUrl = ownerTaskUrl;
	}

	@Override
	public String toString() {
		return "Site [id=" + id + ", name=" + name + ", pyName=" + pyName
				+ ", isEnabled=" + isEnabled + ", siteInfo=" + siteInfo
				+ ", siteLogo=" + siteLogo + ", type=" + type + ", linkUrl="
				+ linkUrl + ", siteURL=" + siteURL + ", ownerTaskUrl="
				+ ownerTaskUrl + "]";
	}

}
