/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingly
 *
 */
public class AdditionalInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> interests;
	private ContactInfo contact;
	private String birth; //生日
	private String mari_status; //婚否
	
	public List<String> getInterests() {
		return interests;
	}
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
	public ContactInfo getContact() {
		return contact;
	}
	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getMari_status() {
		return mari_status;
	}
	public void setMari_status(String mari_status) {
		this.mari_status = mari_status;
	}
	
	@Override
	public String toString() {
		return "AdditionalInfo [interests=" + interests + ", contact="
				+ contact + ", birth=" + birth + ", mari_status=" + mari_status
				+ "]";
	}
	
}
