/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->严重违法信息->严重违法信息
 */
public class AicpubSSerillegalInfo implements Serializable { //GsgsYzwfYzwfInfo
	private static final long serialVersionUID = 401406471270684252L;
	
	//列入严重违法企业名单原因
	private String includeCause; //lryzwfqymdCause;
	//列入日期
	private String includeDateTime; //lrDate;
	//移出严重违法企业名单原因
	private String removeCause; //ycyzwfqymdCause;
	//移出日期
	private String removeDateTime; //ycDate;
	//作出决定机关
	private String deciAuthority; //zcjdAuthority;
	//html内容
	private String html;
	public String getIncludeCause() {
		return includeCause;
	}
	public void setIncludeCause(String includeCause) {
		this.includeCause = includeCause;
	}
	public String getIncludeDateTime() {
		return includeDateTime;
	}
	public void setIncludeDateTime(String includeDateTime) {
		this.includeDateTime = includeDateTime;
	}
	public String getRemoveCause() {
		return removeCause;
	}
	public void setRemoveCause(String removeCause) {
		this.removeCause = removeCause;
	}
	public String getRemoveDateTime() {
		return removeDateTime;
	}
	public void setRemoveDateTime(String removeDateTime) {
		this.removeDateTime = removeDateTime;
	}
	public String getDeciAuthority() {
		return deciAuthority;
	}
	public void setDeciAuthority(String deciAuthority) {
		this.deciAuthority = deciAuthority;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "AicpubSSerillegalInfo [includeCause=" + includeCause
				+ ", includeDateTime=" + includeDateTime + ", removeCause="
				+ removeCause + ", removeDateTime=" + removeDateTime
				+ ", deciAuthority=" + deciAuthority + ", html=" + html + "]";
	}

}
