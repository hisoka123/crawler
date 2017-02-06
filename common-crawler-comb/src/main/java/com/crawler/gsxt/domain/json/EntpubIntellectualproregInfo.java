/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示信息->知识产权出质登记信息
 */
public class EntpubIntellectualproregInfo implements Serializable { //QygsZscqczdjInfo
	private static final long serialVersionUID = 6947594715443120449L;
	
	//知识产权出质登记信息列表
	private List<EntpubIIntellectualproregInfo> intellectualProRegInfos; //qygsZscqczdjZscqczdjInfos;
	//html内容
	private String html;
	public List<EntpubIIntellectualproregInfo> getIntellectualProRegInfos() {
		return intellectualProRegInfos;
	}
	public void setIntellectualProRegInfos(
			List<EntpubIIntellectualproregInfo> intellectualProRegInfos) {
		this.intellectualProRegInfos = intellectualProRegInfos;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "EntpubIntellectualproregInfo [intellectualProRegInfos="
				+ intellectualProRegInfos + ", html=" + html + "]";
	}
}
