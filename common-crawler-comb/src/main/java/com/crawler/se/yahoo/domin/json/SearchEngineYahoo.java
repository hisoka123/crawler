package com.crawler.se.yahoo.domin.json;

import java.io.Serializable;

/**
 * Created by zxz on 2016/1/11.
 */
public class SearchEngineYahoo implements Serializable{

    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private String linkUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
