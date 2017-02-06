package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author fzy
 * @date 2016年5月24日
 * 工商公示->动产抵押登记信息->详情->注销
 */
public class AicpubCChatMortgRevokeInfo implements Serializable{
	private static final long serialVersionUID = 8099912809086708378L;

	//注销日期
	private String revokeDate;
	//注销原因
	private String revokeReason;
	//html
	private String html;
	public String getRevokeDate() {
		return revokeDate;
	}
	public void setRevokeDate(String revokeDate) {
		this.revokeDate = revokeDate;
	}
	public String getRevokeReason() {
		return revokeReason;
	}
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	@Override
	public String toString() {
		return "AicpubCChatMortgRevokeInfo [revokeDate=" + revokeDate 
				+ ", revokeReason=" + revokeReason 
				+ ", html=" + html + "]";
	}
}
