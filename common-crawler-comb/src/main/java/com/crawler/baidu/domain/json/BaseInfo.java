package com.crawler.baidu.domain.json;

import java.io.Serializable;

/**
 * Created by zxz on 2015/8/11.
 */
public class BaseInfo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String biContent;

    private int listOrder;

    private String location;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getListOrder() {
        return listOrder;
    }

    public void setListOrder(int listOrder) {
        this.listOrder = listOrder;
    }

    public String getBiContent() {
        return biContent;
    }

    public void setBiContent(String biContent) {
        this.biContent = biContent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
