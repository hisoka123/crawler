package com.crawler.gsxt.domain.json.chongqing;


import java.util.List;

public class Investor {

    private String oid;
    private String pripid;
    private String invtypeno;
    private String invtype;
    private String inv;
    private String certypeno;
    private String certype;
    private String cerno;
    private String blictype;
    private String blicno;

    private String lisubconam;
    private String liacconam;

    private String name;
    private String sconform;

    private List<GInvaccon> gInvaccon;
    private List<GInvsubcon> gInvsubcon;



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

    public String getInvtypeno() {
        return invtypeno;
    }

    public void setInvtypeno(String invtypeno) {
        this.invtypeno = invtypeno;
    }

    public String getInvtype() {
        return invtype;
    }

    public void setInvtype(String invtype) {
        this.invtype = invtype;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    public String getCertypeno() {
        return certypeno;
    }

    public void setCertypeno(String certypeno) {
        this.certypeno = certypeno;
    }

    public String getCertype() {
        return certype;
    }

    public void setCertype(String certype) {
        this.certype = certype;
    }

    public String getCerno() {
        return cerno;
    }

    public void setCerno(String cerno) {
        this.cerno = cerno;
    }

    public String getBlictype() {
        return blictype;
    }

    public void setBlictype(String blictype) {
        this.blictype = blictype;
    }

    public String getBlicno() {
        return blicno;
    }

    public void setBlicno(String blicno) {
        this.blicno = blicno;
    }

    public List<GInvaccon> getgInvaccon() {
        return gInvaccon;
    }

    public void setgInvaccon(List<GInvaccon> gInvaccon) {
        this.gInvaccon = gInvaccon;
    }

    public List<GInvsubcon> getgInvsubcon() {
        return gInvsubcon;
    }

    public void setgInvsubcon(List<GInvsubcon> gInvsubcon) {
        this.gInvsubcon = gInvsubcon;
    }

    public String getLisubconam() {
        return lisubconam;
    }

    public void setLisubconam(String lisubconam) {
        this.lisubconam = lisubconam;
    }

    public String getLiacconam() {
        return liacconam;
    }

    public void setLiacconam(String liacconam) {
        this.liacconam = liacconam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSconform() {
        return sconform;
    }

    public void setSconform(String sconform) {
        this.sconform = sconform;
    }
}
