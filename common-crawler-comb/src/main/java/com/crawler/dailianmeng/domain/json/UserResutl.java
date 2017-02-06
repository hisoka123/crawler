package com.crawler.dailianmeng.domain.json;

import java.io.Serializable;
import java.util.List;

public class UserResutl implements Serializable{
	private String tipMessage;
	
	private List<String> thead;
	
	private List<String> tbody;

	public String getTipMessage() {
		return tipMessage;
	}

	public void setTipMessage(String tipMessage) {
		this.tipMessage = tipMessage;
	}

	public List<String> getThead() {
		return thead;
	}

	public void setThead(List<String> thead) {
		this.thead = thead;
	}

	public List<String> getTbody() {
		return tbody;
	}

	public void setTbody(List<String> tbody) {
		this.tbody = tbody;
	}

}
