/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;

/**
 * 荣誉和获奖
 * @author kingly
 *
 */
public class Honoraward implements Serializable {
	private static final long serialVersionUID = 1L;
	private String summary; //简介
	private String org; //组织
	private String honors_date; //时间
	private String desc; //描述
	private String desc_html; //描述（带有html格式）
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getHonors_date() {
		return honors_date;
	}
	public void setHonors_date(String honors_date) {
		this.honors_date = honors_date;
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
		return "Honoraward [summary=" + summary + ", org=" + org
				+ ", honors_date=" + honors_date + ", desc=" + desc + "]";
	}

}
