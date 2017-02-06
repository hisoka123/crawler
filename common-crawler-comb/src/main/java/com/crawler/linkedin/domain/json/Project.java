/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * 所做项目
 * @author kingly
 *
 */
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String role;
	private String pro_date;
	private String link;
	private List<UserFeedJson> members;
	private String desc;
	private String desc_html;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPro_date() {
		return pro_date;
	}
	public void setPro_date(String pro_date) {
		this.pro_date = pro_date;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<UserFeedJson> getMembers() {
		return members;
	}
	public void setMembers(List<UserFeedJson> members) {
		this.members = members;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc_html() {
		return desc_html;
	}
	public void setDesc_html(String desc_html) {
		this.desc_html = desc_html;
	}
	
	@Override
	public String toString() {
		return "Project [name=" + name + ", role=" + role + ", pro_date="
				+ pro_date + ", link=" + link + ", members=" + members
				+ ", desc=" + desc + ", desc_html=" + desc_html + "]";
	}
	
}
