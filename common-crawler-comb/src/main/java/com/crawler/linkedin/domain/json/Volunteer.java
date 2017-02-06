package com.crawler.linkedin.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * 志愿经历
 * @author kingky
 *
 */
public class Volunteer implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Organization> parti_orgs;
	private List<String> intens; //服务意向
	private List<String> interests; //关心的公益话题
	private List<Organization> supt_orgs; //支持的组织机构
	
	public List<Organization> getParti_orgs() {
		return parti_orgs;
	}
	public void setParti_orgs(List<Organization> parti_orgs) {
		this.parti_orgs = parti_orgs;
	}
	public List<String> getIntens() {
		return intens;
	}
	public void setIntens(List<String> intens) {
		this.intens = intens;
	}
	public List<String> getInterests() {
		return interests;
	}
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
	public List<Organization> getSupt_orgs() {
		return supt_orgs;
	}
	public void setSupt_orgs(List<Organization> supt_orgs) {
		this.supt_orgs = supt_orgs;
	}
	
	@Override
	public String toString() {
		return "Volunteer [parti_orgs=" + parti_orgs + ", intens=" + intens
				+ ", interests=" + interests + ", supt_orgs=" + supt_orgs + "]";
	}
	
}
