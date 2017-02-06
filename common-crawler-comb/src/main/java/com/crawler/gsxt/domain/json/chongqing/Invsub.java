package com.crawler.gsxt.domain.json.chongqing;


import java.util.ArrayList;
import java.util.List;

public class Invsub {

    private List<JInvinfo> JInvinfoList = new ArrayList<>();
    private List<Modifie> modifies = new ArrayList<>();

    public Invsub() {    }

    public Invsub(List<JInvinfo> JInvinfoList, List<Modifie> modifies) {
        this.JInvinfoList = JInvinfoList;
        this.modifies = modifies;
    }

    public List<JInvinfo> getJInvinfoList() {
        return JInvinfoList;
    }

    public void setJInvinfoList(List<JInvinfo> JInvinfoList) {
        this.JInvinfoList = JInvinfoList;
    }

    public List<Modifie> getModifies() {
        return modifies;
    }

    public void setModifies(List<Modifie> modifies) {
        this.modifies = modifies;
    }

    @Override
    public String toString() {
        return "Invsub{" +
                "JInvinfoList=" + JInvinfoList +
                ", modifies=" + modifies +
                '}';
    }
}
