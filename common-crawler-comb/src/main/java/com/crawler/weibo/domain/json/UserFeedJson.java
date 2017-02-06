package com.crawler.weibo.domain.json;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UserFeedJson implements Serializable{
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 464014159520539183L;

	private String uid;
	
	private String nickname;
	  
	private String follow;
	
	private String follow_url;
	
	private String fans;
	
	private String fans_url;
	
	private String statuses;
	
	private String statuses_url;
	
	private String profile;
	
	private String person_info;
	
	private String person_card;
	
	private String profile_image;
	
	private String location;
	
	private String v;
	
	private String gender;
	
	private String birthday;
	
	private String created_at;
	
	private String blog_url;
	
	private String email;
	
	private String[] person_label;
	
	private String[] person_job;
	
	private String[] person_edu;
	
	private JobInfo[] jobinfo;
	 
	private List<EducationInfo> educationinfos;
	
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFollow() {
		return follow;
	}

	public void setFollow(String follow) {
		this.follow = follow;
	}

	public String getFans() {
		return fans;
	}

	public void setFans(String fans) {
		this.fans = fans;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPerson_info() {
		return person_info;
	}

	public void setPerson_info(String person_info) {
		this.person_info = person_info;
	}

	public String[] getPerson_label() {
		return person_label;
	}

	public void setPerson_label(String[] person_label) {
		this.person_label = person_label;
	}

	public String[] getPerson_job() {
		return person_job;
	}

	public void setPerson_job(String[] person_job) {
		this.person_job = person_job;
	}

	public String[] getPerson_edu() {
		return person_edu;
	}

	public void setPerson_edu(String[] person_edu) {
		this.person_edu = person_edu;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public String getFollow_url() {
		return follow_url;
	}

	public void setFollow_url(String follow_url) {
		this.follow_url = follow_url;
	}

	public String getFans_url() {
		return fans_url;
	}

	public void setFans_url(String fans_url) {
		this.fans_url = fans_url;
	}

	public String getStatuses_url() {
		return statuses_url;
	}

	public void setStatuses_url(String statuses_url) {
		this.statuses_url = statuses_url;
	} 

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getBlog_url() {
		return blog_url;
	}

	public void setBlog_url(String blog_url) {
		this.blog_url = blog_url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public JobInfo[] getJobinfo() {
		return jobinfo;
	}

	public void setJobinfo(JobInfo[] jobinfo) {
		this.jobinfo = jobinfo;
	}
  
	public List<EducationInfo> getEducationinfos() {
		return educationinfos;
	}

	public void setEducationinfos(List<EducationInfo> educationinfos) {
		this.educationinfos = educationinfos;
	}
	
	

	public String getPerson_card() {
		return person_card;
	}

	public void setPerson_card(String person_card) {
		this.person_card = person_card;
	}

 

	

	

	
	
	

}
