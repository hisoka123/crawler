package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author fzy
 * @date 2016年5月24日
 * 工商公示->动产抵押登记信息->详情->抵押权人概况
 */
public class AicpubCChatMortgPersonInfo implements Serializable{
	private static final long serialVersionUID = -4022589306514522257L;

	//抵押权人名称
	private String name;
	//抵押权人证照/证件类型
	private String type;
	//证照/证件号码
	private String num;
	//html
	private String html;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	@Override
	public String toString() {
		return "AicpubCChatMortgPersonInfo [name=" + name 
				+ ", type=" + type 
				+ ", num=" + num 
				+ ", html=" + html + "]";
	}
}
