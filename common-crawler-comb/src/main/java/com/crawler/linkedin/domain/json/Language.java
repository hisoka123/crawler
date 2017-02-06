/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;

/**
 * @author kingly
 *
 */
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String profic; //熟练程度

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfic() {
		return profic;
	}
	public void setProfic(String profic) {
		this.profic = profic;
	}
	
	@Override
	public String toString() {
		return "Language [name=" + name + ", profic=" + profic + "]";
	}
}
