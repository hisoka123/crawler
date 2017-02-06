/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示信息->股权变更信息
 */
public class EntpubEquchangeInfo implements Serializable { //QygsGqbgInfo
	private static final long serialVersionUID = 6537674897061207096L;
	
	//股权变更信息列表
	private List<EntpubEEquchangeInfo> equChangeInfos; //qygsGqbgGqbgInfos;
	//html内容
	private String html;
	public List<EntpubEEquchangeInfo> getEquChangeInfos() {
		return equChangeInfos;
	}
	public void setEquChangeInfos(List<EntpubEEquchangeInfo> equChangeInfos) {
		this.equChangeInfos = equChangeInfos;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "EntpubEquchangeInfo [equChangeInfos=" + equChangeInfos
				+ ", html=" + html + "]";
	}
}
