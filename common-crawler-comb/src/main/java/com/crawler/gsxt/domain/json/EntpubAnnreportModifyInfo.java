/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示->企业年报->修改记录
 */
public class EntpubAnnreportModifyInfo implements Serializable { //QygsQynbXgjlInfo
	private static final long serialVersionUID = 2359853476547077103L;
	
	//修改事项
	private String item; //xgItem;
	//修改前
	private String preContent; //xgqContent;
	//修改后
	private String postContent; //xghContent;
	//修改日期
	private String dateTime; //xgDate;
	//html内容
	private String html;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getPreContent() {
		return preContent;
	}
	public void setPreContent(String preContent) {
		this.preContent = preContent;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "EntpubAnnreportModifyInfo [item=" + item + ", preContent="
				+ preContent + ", postContent=" + postContent + ", dateTime="
				+ dateTime + ", html=" + html + "]";
	}
}
