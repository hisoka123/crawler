package com.module.dao.entity.doc;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author kingly
 * @date 2015年12月21日
 */

@Entity
@Table(name = "WikiDocBase")
@SequenceGenerator(name="wikiDocBaseSeq",sequenceName="wikiDocBase_sequence",allocationSize=60)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WikiDocBase implements Serializable, Comparable<WikiDocBase> {
	private static final long serialVersionUID = -8316302633011286906L;

	@Id
	@GeneratedValue(generator="wikiDocBaseSeq",strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String title;
	private String titleIcon;
	private String path;
	private String params;
	
	private Long site_id;
	private String requestMethod;
	
	/*@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private Set<WikiDocBase> children = new HashSet<WikiDocBase>(); // 子节点
*/	

	public WikiDocBase() {
		super();
	}

	public WikiDocBase(Long id, String name,String title,String titleIcon,String path,String params,Long site_id,String requestMethod){
		      this.id=id;
		      this.name=name;
		      this.title=title;
		      this.titleIcon=titleIcon;
		      this.path=path;
		      this.params=params;
		      this.site_id=site_id;
		      this.requestMethod=requestMethod;
		      
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleIcon() {
		return titleIcon;
	}

	public void setTitleIcon(String titleIcon) {
		this.titleIcon = titleIcon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getSite_id() {
		return site_id;
	}

	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	@Override
	public String toString() {
		return "WikiDocBase [id=" + id + ", name=" + name + ", title=" + title
				+ ", titleIcon=" + titleIcon + ", path=" + path + ", params="
				+ params + ", site_id=" + site_id + ", requestMethod="
				+ requestMethod + "]";
	}
	
	

	@Override
	public int compareTo(WikiDocBase wiki) {
		if (this.getId() < wiki.getId()) {
			return 1;
		} else if (this.getId() > wiki.getId()) {
			return -1;
		} else {
			return this.toString().compareTo(wiki.toString());
		}
	}
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj==null) {
			return false;
		}
		if (this==obj) {
			return true;
		}
		if (!(obj instanceof WikiDocBase)) {
			return false;
		}
		WikiDocBase wiki = (WikiDocBase) obj;
		if (this.getId()==wiki.getId() && this.getName().equals(wiki.getName()) && this.getParams().equals(wiki.getParams()) && this.getPath().equals(wiki.getPath()) && this.getTitle().equals(wiki.getTitle()) && this.getTitleIcon().equals(wiki.getTitleIcon())) {
			return true;
		} else {
			return false;
		}
	}
}
