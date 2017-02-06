package com.crawler.zhihu.domain.json;

import java.io.Serializable;

public class UserSearchJson implements Serializable {
	private static final long serialVersionUID = 1L;
	private String[] htmls;

	public String[] getHtmls() {
		return htmls;
	}

	public void setHtmls(String[] htmls) {
		this.htmls = htmls;
	}
	
	

}
