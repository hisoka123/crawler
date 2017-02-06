package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示->备案信息
 */
public class AicpubArchiveInfo implements Serializable { //GsgsBaInfo
	private static final long serialVersionUID = -2555227658602295391L;

	//主要人员信息
	private List<AicpubArchivePrimemberInfo> priMemberInfos; //gsgsBaZyryInfos
	
	//分支机构信息
	private List<AicpubArchiveBranchInfo> branchInfos; //gsgsBaFzjgInfos
	
	//清算信息
	private AicpubArchiveClearInfo clearInfo; //gsgsBaQsInfo
	
	//主管部门（出资人）信息
	private List<AicpubArchiveMainDeptInfo> mainDeptInfo; //gsgsBaQsInfo
	
	//html内容
	private String html;

	public List<AicpubArchivePrimemberInfo> getPriMemberInfos() {
		return priMemberInfos;
	}

	public void setPriMemberInfos(List<AicpubArchivePrimemberInfo> priMemberInfos) {
		this.priMemberInfos = priMemberInfos;
	}

	public List<AicpubArchiveBranchInfo> getBranchInfos() {
		return branchInfos;
	}

	public void setBranchInfos(List<AicpubArchiveBranchInfo> branchInfos) {
		this.branchInfos = branchInfos;
	}

	public AicpubArchiveClearInfo getClearInfo() {
		return clearInfo;
	}

	public void setClearInfo(AicpubArchiveClearInfo clearInfo) {
		this.clearInfo = clearInfo;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public List<AicpubArchiveMainDeptInfo> getMainDeptInfo() {
		return mainDeptInfo;
	}

	public void setMainDeptInfo(List<AicpubArchiveMainDeptInfo> mainDeptInfo) {
		this.mainDeptInfo = mainDeptInfo;
	}

	@Override
	public String toString() {
		return "AicpubArchiveInfo [priMemberInfos=" + priMemberInfos
				+ ", branchInfos=" + branchInfos + ", clearInfo=" + clearInfo
				+ ", html=" + html + "]";
	}
}
