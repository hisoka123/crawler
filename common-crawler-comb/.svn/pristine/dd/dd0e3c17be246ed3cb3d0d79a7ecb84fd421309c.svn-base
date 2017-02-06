/**
 * 
 */
package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->登记信息
 */
public class AicpubRegInfo implements Serializable { //GsgsDjInfo
	private static final long serialVersionUID = -4594661102675268286L;

	//基本信息
	private AicpubRegBaseInfo baseInfo; //gsgsDjJbInfo;
	
	//股东信息
	private List<AicpubRegStohrInfo> stohrInfos; //gsgsDjGdInfos;
	
	//变更信息（包括变更信息详细）
	private List<AicpubRegChangeInfo> changeInfos; //gsgsDjBgInfos;
	
	//撤销信息
	private List<AicpubRegRevokeInfo> revokeInfos; //gsgsDjBgInfos;

	public AicpubRegBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(AicpubRegBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public List<AicpubRegStohrInfo> getStohrInfos() {
		return stohrInfos;
	}

	public void setStohrInfos(List<AicpubRegStohrInfo> stohrInfos) {
		this.stohrInfos = stohrInfos;
	}

	public List<AicpubRegChangeInfo> getChangeInfos() {
		return changeInfos;
	}

	public void setChangeInfos(List<AicpubRegChangeInfo> changeInfos) {
		this.changeInfos = changeInfos;
	}
	
	public List<AicpubRegRevokeInfo> getRevokeInfos() {
		return revokeInfos;
	}

	public void setRevokeInfos(List<AicpubRegRevokeInfo> revokeInfos) {
		this.revokeInfos = revokeInfos;
	}

	@Override
	public String toString() {
		return "AicpubRegInfo [baseInfo=" + baseInfo + ", stohrInfos="
				+ stohrInfos + ", changeInfos=" + changeInfos +", revokeInfos=" + revokeInfos + "]";
	}
}
