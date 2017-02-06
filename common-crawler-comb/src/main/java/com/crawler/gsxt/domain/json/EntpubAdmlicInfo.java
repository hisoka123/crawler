/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示信息->行政许可信息
 */
public class EntpubAdmlicInfo implements Serializable { //QygsXzxkInfo
	private static final long serialVersionUID = -7511264478928121053L;
	
	//行政许可信息列表
	private List<EntpubAAdmlicInfo> admlicInfos; //qygsXzxkXzxkInfos;
	//html内容
	private String html;
	
	public List<EntpubAAdmlicInfo> getAdmlicInfos() {
		return admlicInfos;
	}
	public void setAdmlicInfos(List<EntpubAAdmlicInfo> admlicInfos) {
		this.admlicInfos = admlicInfos;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	@Override
	public String toString() {
		return "EntpubAdmlicInfo [admlicInfos=" + admlicInfos + ", html="
				+ html + "]";
	}
}
