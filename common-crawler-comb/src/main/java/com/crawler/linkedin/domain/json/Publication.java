/**
 * 
 */
package com.crawler.linkedin.domain.json;

import java.io.Serializable;
import java.util.List;

/**
 * 出版作品
 * @author kingly
 *
 */
public class Publication implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String link;
	private String press; //出版社
	private String pub_date; //出版日期
	private String desc; //描述
	private String desc_html;
	private List<UserFeedJson> authors; //作者
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc_html() {
		return desc_html;
	}
	public void setDesc_html(String desc_html) {
		this.desc_html = desc_html;
	}
	public List<UserFeedJson> getAuthors() {
		return authors;
	}
	public void setAuthors(List<UserFeedJson> authors) {
		this.authors = authors;
	}
	
	@Override
	public String toString() {
		return "Publication [name=" + name + ", link=" + link + ", press="
				+ press + ", pub_date=" + pub_date + ", desc=" + desc
				+ ", desc_html=" + desc_html + ", authors=" + authors + "]";
	}
	
}
