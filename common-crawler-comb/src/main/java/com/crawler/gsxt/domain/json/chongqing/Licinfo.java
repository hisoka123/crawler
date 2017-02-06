package com.crawler.gsxt.domain.json.chongqing;


import java.util.ArrayList;
import java.util.List;

public class Licinfo {

    private String entNo;
    private List<ListInfo2> list = new ArrayList<>();

    public Licinfo() {    }

    public Licinfo(String entNo, List<ListInfo2> list) {
        this.entNo = entNo;
        this.list = list;
    }

    public String getEntNo() {
        return entNo;
    }

    public void setEntNo(String entNo) {
        this.entNo = entNo;
    }

    public List<ListInfo2> getList() {
        return list;
    }

    public void setList(List<ListInfo2> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Licinfo{" +
                "entNo='" + entNo + '\'' +
                ", list=" + list +
                '}';
    }
}
