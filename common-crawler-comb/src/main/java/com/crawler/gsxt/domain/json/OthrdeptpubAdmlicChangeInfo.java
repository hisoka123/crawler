/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月29日
 * 其他部门公示信息->行政许可信息->变更信息
 */
public class OthrdeptpubAdmlicChangeInfo implements Serializable { //QtbmgsXzxkBgInfo
	private static final long serialVersionUID = -3462356101752127636L;
	
	//变更事项
	private String item; //bgItem;
	//变更时间
	private String dateTime; //bgDate;
	//变更前内容
	private String preContent; //bgqContent;
	//变更后内容
	private String postContent; //bghContent;
	//html内容
	private String html;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "OthrdeptpubAdmlicChangeInfo [item=" + item + ", dateTime="
				+ dateTime + ", preContent=" + preContent + ", postContent="
				+ postContent + ", html=" + html + "]";
	}
}
