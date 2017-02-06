/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 *
 */
public class TestScore implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String occup; //应试身份
	private String score;
	private String test_date;
	private String desc;
	private String desc_html;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccup() {
		return occup;
	}
	public void setOccup(String occup) {
		this.occup = occup;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTest_date() {
		return test_date;
	}
	public void setTest_date(String test_date) {
		this.test_date = test_date;
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
		return "TestScore [name=" + name + ", occup=" + occup + ", score="
				+ score + ", test_date=" + test_date + ", desc=" + desc
				+ ", desc_html=" + desc_html + "]";
	}
	
}
