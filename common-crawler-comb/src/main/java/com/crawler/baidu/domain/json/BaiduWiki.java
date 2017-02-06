package com.crawler.baidu.domain.json;

import java.io.Serializable;

/**
 * Created by zxz on 2015/8/11.
 */
public class BaiduWiki  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String subTitle;

    private String link;

    private String picLink;

    private String introduction;

    private String lastUpdateDate;

    private String entry;

    private Integer listOrder;

    private BaseInfo[] baseInfos;

    private CatalogInfo[] catalogInfos;

    public BaiduWiki() {
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicLink() {
		return picLink;
	}

	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public BaseInfo[] getBaseInfos() {
		return baseInfos;
	}

	public void setBaseInfos(BaseInfo[] baseInfos) {
		this.baseInfos = baseInfos;
	}

	public CatalogInfo[] getCatalogInfos() {
		return catalogInfos;
	}

	public void setCatalogInfos(CatalogInfo[] catalogInfos) {
		this.catalogInfos = catalogInfos;
	}

}
