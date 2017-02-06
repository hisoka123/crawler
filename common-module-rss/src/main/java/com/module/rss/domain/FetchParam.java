package com.module.rss.domain;

import java.io.Serializable;

public class FetchParam implements Serializable{
	
	private String url;
	
	private String source;
	
	public FetchParam(){
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
	
	
}
