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
public class School implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String link;
	private String logo;
	private List<Project> projects; //所做项目
	private List<Honoraward> honorawards; //荣誉和奖项
	private List<String> courses; //课程
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<Honoraward> getHonorawards() {
		return honorawards;
	}
	public void setHonorawards(List<Honoraward> honorawards) {
		this.honorawards = honorawards;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return "School [name=" + name + ", link=" + link + ", logo=" + logo
				+ ", projects=" + projects + ", honorawards=" + honorawards
				+ ", courses=" + courses + "]";
	}
	
}
