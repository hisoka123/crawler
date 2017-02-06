package com.crawler.gsxt.domain.json.chongqing;


import java.util.ArrayList;
import java.util.List;

public class PleInfo {

    private String entNo;
    private List<PleInnerInfo> Pleinfolist = new ArrayList<>();

    public String getEntNo() {
        return entNo;
    }

    public void setEntNo(String entNo) {
        this.entNo = entNo;
    }

    public List<PleInnerInfo> getPleinfolist() {
        return Pleinfolist;
    }

    public void setPleinfolist(List<PleInnerInfo> pleinfolist) {
        Pleinfolist = pleinfolist;
    }
}
