package com.crawler.gsxt.domain.json.chongqing;


import java.util.List;

public class Qlicinfo {


    /**
     * oid : 2C67F8E33C9A1BFAE0532106A8C0B365
     * pripid : 5001080000011272
     * licno : 150268
     * regno : 500108100005362
     * licname : 固体废物转移许可证
     * valfrom : 2015-03-03
     * valto : 2015-05-05
     * licanth : 重庆市环境保护局
     * licitem : 危险废物市内转移
     * isgs : 1
     * gstype : 1
     * alters : []
     */

    private String oid;
    private String pripid;
    private String licno;
    private String regno;
    private String licname;
    private String valfrom;
    private String valto;
    private String licanth;
    private String licitem;
    private String isgs;
    private String gstype;
    private String type;
    private List<QlicinfoAlter> alters;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPripid() {
        return pripid;
    }

    public void setPripid(String pripid) {
        this.pripid = pripid;
    }

    public String getLicno() {
        return licno;
    }

    public void setLicno(String licno) {
        this.licno = licno;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getLicname() {
        return licname;
    }

    public void setLicname(String licname) {
        this.licname = licname;
    }

    public String getValfrom() {
        return valfrom;
    }

    public void setValfrom(String valfrom) {
        this.valfrom = valfrom;
    }

    public String getValto() {
        return valto;
    }

    public void setValto(String valto) {
        this.valto = valto;
    }

    public String getLicanth() {
        return licanth;
    }

    public void setLicanth(String licanth) {
        this.licanth = licanth;
    }

    public String getLicitem() {
        return licitem;
    }

    public void setLicitem(String licitem) {
        this.licitem = licitem;
    }

    public String getIsgs() {
        return isgs;
    }

    public void setIsgs(String isgs) {
        this.isgs = isgs;
    }

    public String getGstype() {
        return gstype;
    }

    public void setGstype(String gstype) {
        this.gstype = gstype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<QlicinfoAlter> getAlters() {
        return alters;
    }

    public void setAlters(List<QlicinfoAlter> alters) {
        this.alters = alters;
    }
}
