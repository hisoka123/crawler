package com.crawler.baidu.domain.json;

import java.io.Serializable;

/**
 * Created by zxz on 2015/8/11.
 */
public class CatalogInfo implements Serializable{

    private static final long serialVersionUID = 1L;


    private String title;

    private String parentInPageLink;

    private int catalogLevel;

    private String inPageLink;

    private String content;

    private int listOrder;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentInPageLink() {
        return parentInPageLink;
    }

    public void setParentInPageLink(String parentInPageLink) {
        this.parentInPageLink = parentInPageLink;
    }

    public int getCatalogLevel() {
        return catalogLevel;
    }

    public void setCatalogLevel(int catalogLevel) {
        this.catalogLevel = catalogLevel;
    }

    public String getInPageLink() {
        return inPageLink;
    }

    public void setInPageLink(String inPageLink) {
        this.inPageLink = inPageLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getListOrder() {
        return listOrder;
    }

    public void setListOrder(int listOrder) {
        this.listOrder = listOrder;
    }
}
