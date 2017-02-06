/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->备案信息->分支机构信息
 */
public class AicpubArchiveBranchInfo implements Serializable { //GsgsBaFzjgInfo
	private static final long serialVersionUID = 5053152534061821543L;

	//统一社会信用代码/注册号
	private String num;
	
	//名称
	private String name;
	
	//登记机关
	private String regAuthority;
	
	//html内容
	private String html;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegAuthority() {
		return regAuthority;
	}

	public void setRegAuthority(String regAuthority) {
		this.regAuthority = regAuthority;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "AicpubArchiveBranchInfo [num=" + num + ", name=" + name
				+ ", regAuthority=" + regAuthority + ", html=" + html + "]";
	}
}
