/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月29日
 * 司法协助公示信息->股权冻结信息
 */
public class JudasspubEqufreezeInfo implements Serializable { //SfxzgsGqdjInfo
	private static final long serialVersionUID = 362525332244431456L;
	
	//股权冻结信息列表
	private List<JudasspubEEqufreezeInfo> equFreezeInfos; //sfxzgsGqdjGqdjInfos;
	//html内容
	private String html;
	public List<JudasspubEEqufreezeInfo> getEquFreezeInfos() {
		return equFreezeInfos;
	}
	public void setEquFreezeInfos(List<JudasspubEEqufreezeInfo> equFreezeInfos) {
		this.equFreezeInfos = equFreezeInfos;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "JudasspubEqufreezeInfo [equFreezeInfos=" + equFreezeInfos
				+ ", html=" + html + "]";
	}
}
