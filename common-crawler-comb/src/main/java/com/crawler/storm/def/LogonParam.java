package com.crawler.storm.def;

import java.util.Collection;

public class LogonParam {
	
	private String url;
	
	private Collection<String> domains;
	
	private String unit;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Collection<String> getDomains() {
		return domains;
	}

	public void setDomains(Collection<String> domains) {
		this.domains = domains;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
	
	
	
}
