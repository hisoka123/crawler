package com.crawler.weibo.domain.json;

import java.io.Serializable;
import java.util.List;

public class EducationInfo implements Serializable{
	
	private String flag;
	
	private List<School> school;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<School> getSchool() {
		return school;
	}

	public void setSchool(List<School> school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "EducationInfo [flag=" + flag + ", school=" + school + "]";
	}

	
	
	

}
