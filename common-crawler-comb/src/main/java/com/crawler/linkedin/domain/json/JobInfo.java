/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 *
 */
public class JobInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String position;
	private String company;
	private String company_link;
	private String company_img;
	private String exp_date; //经历时段
	private String location; //工作地点
	private String desc; //工作描述
	private String desc_html; //工作描述（带有html格式）
	private List<Honoraward> honorawards; //荣誉和获奖经历
	private List<Endorsement> endorsements; //推荐信
	private List<Project> projects; //所做项目
	private List<Organization> orgs; //参与组织
	private List<String> courses; //课程
	private List<TestScore> testScores; //测试成绩
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompany_link() {
		return company_link;
	}
	public void setCompany_link(String company_link) {
		this.company_link = company_link;
	}
	public String getCompany_img() {
		return company_img;
	}
	public void setCompany_img(String company_img) {
		this.company_img = company_img;
	}
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public List<Honoraward> getHonorawards() {
		return honorawards;
	}
	public void setHonorawards(List<Honoraward> honorawards) {
		this.honorawards = honorawards;
	}
	public List<Endorsement> getEndorsements() {
		return endorsements;
	}
	public void setEndorsements(List<Endorsement> endorsements) {
		this.endorsements = endorsements;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<Organization> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<Organization> orgs) {
		this.orgs = orgs;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	public List<TestScore> getTestScores() {
		return testScores;
	}
	public void setTestScores(List<TestScore> testScores) {
		this.testScores = testScores;
	}
	
	@Override
	public String toString() {
		return "JobInfo [position=" + position + ", company=" + company
				+ ", company_link=" + company_link + ", company_img="
				+ company_img + ", exp_date=" + exp_date + ", location="
				+ location + ", desc=" + desc + ", desc_html=" + desc_html
				+ ", honorawards=" + honorawards + ", endorsements="
				+ endorsements + ", projects=" + projects + ", orgs=" + orgs
				+ ", courses=" + courses + ", testScores=" + testScores + "]";
	}

}
