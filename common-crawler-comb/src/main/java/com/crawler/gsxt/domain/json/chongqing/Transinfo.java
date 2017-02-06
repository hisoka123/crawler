package com.crawler.gsxt.domain.json.chongqing;


import java.util.ArrayList;
import java.util.List;

public class Transinfo {

    private String entNo;
    private List<ListInfo> list = new ArrayList<>();

    public Transinfo() {    }

    public Transinfo(String entNo, List<ListInfo> list) {
        this.entNo = entNo;
        this.list = list;
    }

    public String getEntNo() {
        return entNo;
    }

    public void setEntNo(String entNo) {
        this.entNo = entNo;
    }

    public List<ListInfo> getList() {
        return list;
    }

    public void setList(List<ListInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Transinfo{" +
                "entNo='" + entNo + '\'' +
                ", list=" + list +
                '}';
    }
}
