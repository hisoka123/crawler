package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author fzy
 * @date 2016年5月24日
 * 工商公示->动产抵押登记信息->详情
 */
public class AicpubCChatMortgDetail implements Serializable{
	private static final long serialVersionUID = 4468027065378760879L;

	//动产抵押登记信息
	private AicpubCChatMortgRegInfo mortgRegInfo;
	
	//抵押权人概况
	private List<AicpubCChatMortgPersonInfo> mortgPersonInfos;
	
	//被担保债权概况
	private AicpubCChatMortgGuaranteedInfo mortgGuaranteedInfo;
	
	//抵押物概况
	private List<AicpubCChatMortgGoodsInfo> mortgGoodsInfos;
	
	//变更
	private List<AicpubCChatMortgChangeInfo> mortgChangeInfos;
	
	//注销
	private AicpubCChatMortgRevokeInfo mortgRevokeInfo;
	
	//html
	private String html;

	public AicpubCChatMortgRegInfo getMortgRegInfo() {
		return mortgRegInfo;
	}

	public void setMortgRegInfo(AicpubCChatMortgRegInfo mortgRegInfo) {
		this.mortgRegInfo = mortgRegInfo;
	}

	public List<AicpubCChatMortgPersonInfo> getMortgPersonInfos() {
		return mortgPersonInfos;
	}

	public void setMortgPersonInfos(
			List<AicpubCChatMortgPersonInfo> mortgPersonInfos) {
		this.mortgPersonInfos = mortgPersonInfos;
	}

	public AicpubCChatMortgGuaranteedInfo getMortgGuaranteedInfo() {
		return mortgGuaranteedInfo;
	}

	public void setMortgGuaranteedInfo(
			AicpubCChatMortgGuaranteedInfo mortgGuaranteedInfo) {
		this.mortgGuaranteedInfo = mortgGuaranteedInfo;
	}

	public List<AicpubCChatMortgGoodsInfo> getMortgGoodsInfos() {
		return mortgGoodsInfos;
	}

	public void setMortgGoodsInfos(List<AicpubCChatMortgGoodsInfo> mortgGoodsInfos) {
		this.mortgGoodsInfos = mortgGoodsInfos;
	}

	public List<AicpubCChatMortgChangeInfo> getMortgChangeInfos() {
		return mortgChangeInfos;
	}

	public void setMortgChangeInfos(
			List<AicpubCChatMortgChangeInfo> mortgChangeInfos) {
		this.mortgChangeInfos = mortgChangeInfos;
	}

	public AicpubCChatMortgRevokeInfo getMortgRevokeInfo() {
		return mortgRevokeInfo;
	}

	public void setMortgRevokeInfo(AicpubCChatMortgRevokeInfo mortgRevokeInfo) {
		this.mortgRevokeInfo = mortgRevokeInfo;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return "AicpubCChatMortgDetail [mortgRegInfo=" + mortgRegInfo 
				+ ", mortgPersonInfos=" + mortgPersonInfos 
				+ ", mortgGuaranteedInfo=" + mortgGuaranteedInfo 
				+ ", mortgGoodsInfos=" + mortgGoodsInfos 
				+ ", mortgChangeInfos=" + mortgChangeInfos 
				+ ", mortgRevokeInfo=" + mortgRevokeInfo 
				+ ", html=" + html + "]";
	}
}
