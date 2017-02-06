/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 企业公示->企业年报->行政许可情况
 */
public class EntpubAnnreportAdmlicenseInfo implements Serializable {
	private static final long serialVersionUID = 2359853476547077103L;
	
	//许可文件名称
	private String licenseName;
	//有效期至
	private String licenseDate;
	
	//html内容
	private String html;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	


	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public String getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}

	@Override
	public String toString() {
		return "QygsQynbXzxkInfo [licenseName=" + licenseName + ", licenseDate="+ licenseDate  + "]";
	}
}
