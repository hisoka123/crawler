package com.crawlermanage.dao.result;

import java.io.Serializable;

import com.crawler.domain.json.Result;
import com.crawler.gsxt.domain.json.AicFeedJson;

public class GsxtResult implements Serializable {

	   private static final long serialVersionUID = 6057024303851134506L;
	   
	   private int state;  //爬取企业状态码     0,排队等候；1，成功；7，无关键词
	   private int existCode;  //定时任务中企业存在状态码，0，否；1，在
	   private long company_id; //定时任务中企业存在时的id;
	   private AicFeedJson aicFeedJson;  //企业详情
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getExistCode() {
		return existCode;
	}
	public void setExistCode(int existCode) {
		this.existCode = existCode;
	}
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	public AicFeedJson getAicFeedJson() {
		return aicFeedJson;
	}
	public void setAicFeedJson(AicFeedJson aicFeedJson) {
		this.aicFeedJson = aicFeedJson;
	}
	@Override
	public String toString() {
		return "GsxtResult [state=" + state + ", existCode=" + existCode
				+ ", company_id=" + company_id + ", aicFeedJson=" + aicFeedJson
				+ "]";
	}
	   
	   
}
