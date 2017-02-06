package com.crawler.dailianmeng.domain.json;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserFeedJson implements Serializable{
	private String tipMessage;
	
	private List<Map<String, String>> data;

	public String getTipMessage() {
		return tipMessage;
	}

	public void setTipMessage(String tipMessage) {
		this.tipMessage = tipMessage;
	}

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}

}
