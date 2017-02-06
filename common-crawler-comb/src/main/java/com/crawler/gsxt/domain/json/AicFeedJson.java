package com.crawler.gsxt.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 * @date 2016年4月20日
 * 工商系统返回数据
 */
public class AicFeedJson implements Serializable { //GsxtFeedJson
	private static final long serialVersionUID = -1206891202574980621L;

	//工商公示信息
	private AicpubInfo aicPubInfo; //gsgsInfo;
	
	//企业公示信息
	private EntpubInfo entPubInfo; //qygsInfo;
	
	//其他部门公示信息
	private OthrdeptpubInfo othrDeptPubInfo; //qtbmgsInfo;
	
	//司法协助公示信息
	private JudasspubInfo judAssPubInfo; //sfxzgsInfo;

	public AicpubInfo getAicPubInfo() {
		return aicPubInfo;
	}

	public void setAicPubInfo(AicpubInfo aicPubInfo) {
		this.aicPubInfo = aicPubInfo;
	}

	public EntpubInfo getEntPubInfo() {
		return entPubInfo;
	}

	public void setEntPubInfo(EntpubInfo entPubInfo) {
		this.entPubInfo = entPubInfo;
	}

	public OthrdeptpubInfo getOthrDeptPubInfo() {
		return othrDeptPubInfo;
	}

	public void setOthrDeptPubInfo(OthrdeptpubInfo othrDeptPubInfo) {
		this.othrDeptPubInfo = othrDeptPubInfo;
	}

	public JudasspubInfo getJudAssPubInfo() {
		return judAssPubInfo;
	}

	public void setJudAssPubInfo(JudasspubInfo judAssPubInfo) {
		this.judAssPubInfo = judAssPubInfo;
	}

	@Override
	public String toString() {
		return "AicFeedJson [aicPubInfo=" + aicPubInfo + ", entPubInfo="
				+ entPubInfo + ", othrDeptPubInfo=" + othrDeptPubInfo
				+ ", judAssPubInfo=" + judAssPubInfo + "]";
	}
}
