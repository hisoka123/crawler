package com.crawler.gsxt.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 * @date 2016年3月28日
 * 工商公示信息->经营异常信息
 */
public class AicpubOperanomaInfo implements Serializable { //GsgsJyycInfo
	private static final long serialVersionUID = 6690323392119745976L;
	
	//经营异常信息列表
	private List<AicpubOOperanomaInfo> operAnomaInfos;
	//html内容
	private String html;
	public List<AicpubOOperanomaInfo> getOperAnomaInfos() {
		return operAnomaInfos;
	}
	public void setOperAnomaInfos(List<AicpubOOperanomaInfo> operAnomaInfos) {
		this.operAnomaInfos = operAnomaInfos;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	@Override
	public String toString() {
		return "AicpubOperanomaInfo [operAnomaInfos=" + operAnomaInfos
				+ ", html=" + html + "]";
	}
}
