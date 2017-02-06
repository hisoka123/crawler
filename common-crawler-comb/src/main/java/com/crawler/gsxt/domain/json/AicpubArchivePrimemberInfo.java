/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->备案信息->主要人员信息
 */
public class AicpubArchivePrimemberInfo implements Serializable { //GsgsBaZyryInfo
	private static final long serialVersionUID = -4622855152462508238L;

	//姓名
	private String name;
	
	//职务
	private String position;
	
	//html内容
	private String html;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "AicpubArchivePrimemberInfo [name=" + name + ", position="
				+ position + ", html=" + html + "]";
	}
	
}
