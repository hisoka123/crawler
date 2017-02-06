package com.module.dao.entity.gsxt;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_wzhwdxx database table.
 * 
 */
@Entity
@Table(name="t_wzhwdxx")
@NamedQuery(name="TWzhwdxx.findAll", query="SELECT t FROM TWzhwdxx t")
public class TWzhwdxx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="text")
	private String html;

	private String name;

	private String type;

	private String website;

	//bi-directional many-to-one association to TQynb
	@ManyToOne
	@JoinColumn(name="qynb_id")
	private TQynb TQynb;

	public TWzhwdxx() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public TQynb getTQynb() {
		return this.TQynb;
	}

	public void setTQynb(TQynb TQynb) {
		this.TQynb = TQynb;
	}

}