/**
 * 
 */
package com.crawler.zhihu.domain.json;

import java.io.Serializable;

/**
 * @author kingly 
 *
 */
public class ZhihuArticle implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String titile;
	private String content;
	private String link;
	private String agree_num;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAgree_num() {
		return agree_num;
	}
	public void setAgree_num(String agree_num) {
		this.agree_num = agree_num;
	}
	
	@Override
	public String toString() {
		return "ZhihuArticle [id=" + id + ", titile=" + titile + ", content="
				+ content + ", link=" + link + ", agree_num=" + agree_num + "]";
	}
	
}
