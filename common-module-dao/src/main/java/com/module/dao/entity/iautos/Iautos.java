package com.module.dao.entity.iautos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="c_iautos_result")
@NamedQuery(name="Iautos.findAll", query="SELECT t FROM Iautos t")
public class Iautos  implements Serializable{
	private static final long serialVersionUID = -4614475196817774665L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 名称
	private String name;

	// 公里数
	private String timekm;
	// 型号
	private String price;
	// 图片地址
	private String profile_image;
	// 详情地址
	private String url;

	// 品牌认证，没有为空
	private String certificate;
	
	
	private Date executetime; 

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="iautoKeyword_id")
	private IautosKeyword iautosKeyword;
	
	
	public Iautos(){}
	
	public Iautos(String name,String timekm,String price,
			           String profile_image,String url,String certificate){		
		    this.name=name;
		    this.timekm=timekm;
		    this.price=price;
		    this.profile_image=profile_image;
		    this.url=url;
		    this.certificate=certificate;		   
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

	public String getTimekm() {
		return timekm;
	}

	public void setTimekm(String timekm) {
		this.timekm = timekm;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	public IautosKeyword getIautosKeyword() {
		return iautosKeyword;
	}

	public void setIautosKeyword(IautosKeyword iautosKeyword) {
		this.iautosKeyword = iautosKeyword;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}
    
}
