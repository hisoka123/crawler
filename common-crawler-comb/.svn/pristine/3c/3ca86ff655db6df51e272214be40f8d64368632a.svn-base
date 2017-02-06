/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示信息->股东及出资信息
 */
public class EntpubStohrinvestInfo implements Serializable { //QygsGdjczInfo
	private static final long serialVersionUID = 8262947070809730025L;
	
	//股东及出资信息
	private List<EntpubSStohrinvestInfo> stohrInvestInfos; //qygsGdjczGdjczInfos;
	//变更信息
	private List<EntpubStohrinvestChangeInfo> changeInfos; //qygsGdjczBgInfos;
	//html内容
	private String html;
	public List<EntpubSStohrinvestInfo> getStohrInvestInfos() {
		return stohrInvestInfos;
	}
	public void setStohrInvestInfos(List<EntpubSStohrinvestInfo> stohrInvestInfos) {
		this.stohrInvestInfos = stohrInvestInfos;
	}
	public List<EntpubStohrinvestChangeInfo> getChangeInfos() {
		return changeInfos;
	}
	public void setChangeInfos(List<EntpubStohrinvestChangeInfo> changeInfos) {
		this.changeInfos = changeInfos;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "EntpubStohrinvestInfo [stohrInvestInfos=" + stohrInvestInfos
				+ ", changeInfos=" + changeInfos + ", html=" + html + "]";
	}
}
