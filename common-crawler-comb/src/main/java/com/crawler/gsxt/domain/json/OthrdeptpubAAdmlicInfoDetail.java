package com.crawler.gsxt.domain.json;

/**
 * 样本 ： 重庆烟草滤嘴材料厂
 * 其他部门公示信息->行政许可信息->行政许可信息->详情
 */
public class OthrdeptpubAAdmlicInfoDetail {

    //变更事项
    private String alterItem;
    //变更日期
    private String alterDate;
    //变更前
    private String preAlter;
    //变更后
    private String postAlter;

    public String getAlterItem() {
        return alterItem;
    }

    public void setAlterItem(String alterItem) {
        this.alterItem = alterItem;
    }

    public String getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(String alterDate) {
        this.alterDate = alterDate;
    }

    public String getPreAlter() {
        return preAlter;
    }

    public void setPreAlter(String preAlter) {
        this.preAlter = preAlter;
    }

    public String getPostAlter() {
        return postAlter;
    }

    public void setPostAlter(String postAlter) {
        this.postAlter = postAlter;
    }

    @Override
    public String toString() {
        return "OthrdeptpubAAdmlicInfoDetail{" +
                "alterItem='" + alterItem + '\'' +
                ", alterDate='" + alterDate + '\'' +
                ", preAlter='" + preAlter + '\'' +
                ", postAlter='" + postAlter + '\'' +
                '}';
    }
}
