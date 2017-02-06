/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示->企业年报->对外投资信息
 */
public class EntpubAnnreportExtinvestInfo implements Serializable { //QygsQynbDwtzInfo
	private static final long serialVersionUID = -5895732085026936050L;
	
	//投资设立企业或购买股权企业名称
	private String enterpriseName; //tzslqyhgmgqqyName;
	//注册号
	private String regNum;
	//html内容
	private String html;
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "EntpubAnnreportExtinvestInfo [enterpriseName=" + enterpriseName
				+ ", regNum=" + regNum + ", html=" + html + "]";
	}
}
