package com.crawler.linkedin.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * 教育背景
 * @author kingly
 *
 */
public class EducationInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private School school;
	private String major; //专业
	private String degree; //学历
	private String edu_date;
	private String desc; //描述
	private String desc_html;
	private List<String> activities; //社团活动
	
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getEdu_date() {
		return edu_date;
	}
	public void setEdu_date(String edu_date) {
		this.edu_date = edu_date;
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
	public List<String> getActivities() {
		return activities;
	}
	public void setActivities(List<String> activities) {
		this.activities = activities;
	}
	
	@Override
	public String toString() {
		return "EducationInfo [school=" + school + ", major=" + major
				+ ", degree=" + degree + ", edu_date=" + edu_date + ", desc="
				+ desc + ", desc_html=" + desc_html + ", activities="
				+ activities + "]";
	}
	
}
