/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 *
 */
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String endorse_num; //认可数
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEndorse_num() {
		return endorse_num;
	}
	public void setEndorse_num(String endorse_num) {
		this.endorse_num = endorse_num;
	}
	
	@Override
	public String toString() {
		return "Skill [name=" + name + ", endorse_num=" + endorse_num + "]";
	}
	
}
