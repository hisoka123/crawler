/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;

/**
 * 资格认证
 * @author kingly
 *
 */
public class Certification implements Serializable {
	private static final long serialVersionUID = 1L;
	private String certi_name;
	private String org_name;
	private String certi_date;
	
	public String getCerti_name() {
		return certi_name;
	}
	public void setCerti_name(String certi_name) {
		this.certi_name = certi_name;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getCerti_date() {
		return certi_date;
	}
	public void setCerti_date(String certi_date) {
		this.certi_date = certi_date;
	}
	
	@Override
	public String toString() {
		return "Certification [certi_name=" + certi_name + ", org_name="
				+ org_name + ", certi_date=" + certi_date + "]";
	}
	
}
