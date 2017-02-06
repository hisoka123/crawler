package com.crawler.gsxt.domain.json.zhejiang;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 企业年报详情 -> 股东及出资信息
 */
public class StohrInvest {
    String corpid;
    String conReportNo;
    String conId;
    BusWebSysTime conInfoActDate;
    String conInfoInvForm;
    String conInfoName;
    String conInfoPayConAmount;
    String conInfoActConAmount;
    String conInfoLiaForm;
    String conInfoAffairPart;
    String conNo;
    String conInfoActForm;
    String conInfoCountry;
    String conInfoRegNo;
    String conInfoShareNum;
    String conInfoSeq;
    String year;
    BusWebSysTime conInfoPayDate;
    String conInfoInvalidFld;
    String conInfoInvFormName;
    String conInfoActFormName;
    String conInfoPercent;

    public StohrInvest() {
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getConReportNo() {
        return conReportNo;
    }

    public void setConReportNo(String conReportNo) {
        this.conReportNo = conReportNo;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public BusWebSysTime getConInfoActDate() {
        return conInfoActDate;
    }

    public void setConInfoActDate(BusWebSysTime conInfoActDate) {
        this.conInfoActDate = conInfoActDate;
    }

    public String getConInfoInvForm() {
        return conInfoInvForm;
    }

    public void setConInfoInvForm(String conInfoInvForm) {
        this.conInfoInvForm = conInfoInvForm;
    }

    public String getConInfoName() {
        return conInfoName;
    }

    public void setConInfoName(String conInfoName) {
        this.conInfoName = conInfoName;
    }

    public String getConInfoPayConAmount() {
        return conInfoPayConAmount;
    }

    public void setConInfoPayConAmount(String conInfoPayConAmount) {
        this.conInfoPayConAmount = conInfoPayConAmount;
    }

    public String getConInfoActConAmount() {
        return conInfoActConAmount;
    }

    public void setConInfoActConAmount(String conInfoActConAmount) {
        this.conInfoActConAmount = conInfoActConAmount;
    }

    public String getConInfoLiaForm() {
        return conInfoLiaForm;
    }

    public void setConInfoLiaForm(String conInfoLiaForm) {
        this.conInfoLiaForm = conInfoLiaForm;
    }

    public String getConInfoAffairPart() {
        return conInfoAffairPart;
    }

    public void setConInfoAffairPart(String conInfoAffairPart) {
        this.conInfoAffairPart = conInfoAffairPart;
    }

    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }

    public String getConInfoActForm() {
        return conInfoActForm;
    }

    public void setConInfoActForm(String conInfoActForm) {
        this.conInfoActForm = conInfoActForm;
    }

    public String getConInfoCountry() {
        return conInfoCountry;
    }

    public void setConInfoCountry(String conInfoCountry) {
        this.conInfoCountry = conInfoCountry;
    }

    public String getConInfoRegNo() {
        return conInfoRegNo;
    }

    public void setConInfoRegNo(String conInfoRegNo) {
        this.conInfoRegNo = conInfoRegNo;
    }

    public String getConInfoShareNum() {
        return conInfoShareNum;
    }

    public void setConInfoShareNum(String conInfoShareNum) {
        this.conInfoShareNum = conInfoShareNum;
    }

    public String getConInfoSeq() {
        return conInfoSeq;
    }

    public void setConInfoSeq(String conInfoSeq) {
        this.conInfoSeq = conInfoSeq;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getConInfoInvalidFld() {
        return conInfoInvalidFld;
    }

    public void setConInfoInvalidFld(String conInfoInvalidFld) {
        this.conInfoInvalidFld = conInfoInvalidFld;
    }

    public String getConInfoInvFormName() {
        return conInfoInvFormName;
    }

    public void setConInfoInvFormName(String conInfoInvFormName) {
        this.conInfoInvFormName = conInfoInvFormName;
    }

    public String getConInfoActFormName() {
        return conInfoActFormName;
    }

    public void setConInfoActFormName(String conInfoActFormName) {
        this.conInfoActFormName = conInfoActFormName;
    }

    public String getConInfoPercent() {
        return conInfoPercent;
    }

    public void setConInfoPercent(String conInfoPercent) {
        this.conInfoPercent = conInfoPercent;
    }

    public BusWebSysTime getConInfoPayDate() {
        return conInfoPayDate;
    }

    public void setConInfoPayDate(BusWebSysTime conInfoPayDate) {
        this.conInfoPayDate = conInfoPayDate;
    }

    @Override
    public String toString() {
        return "StohrInvest{" +
                "corpid='" + corpid + '\'' +
                ", conReportNo='" + conReportNo + '\'' +
                ", conId='" + conId + '\'' +
                ", conInfoActDate=" + conInfoActDate +
                ", conInfoInvForm='" + conInfoInvForm + '\'' +
                ", conInfoName='" + conInfoName + '\'' +
                ", conInfoPayConAmount='" + conInfoPayConAmount + '\'' +
                ", conInfoActConAmount='" + conInfoActConAmount + '\'' +
                ", conInfoLiaForm='" + conInfoLiaForm + '\'' +
                ", conInfoAffairPart='" + conInfoAffairPart + '\'' +
                ", conNo='" + conNo + '\'' +
                ", conInfoActForm='" + conInfoActForm + '\'' +
                ", conInfoCountry='" + conInfoCountry + '\'' +
                ", conInfoRegNo='" + conInfoRegNo + '\'' +
                ", conInfoShareNum='" + conInfoShareNum + '\'' +
                ", conInfoSeq='" + conInfoSeq + '\'' +
                ", year='" + year + '\'' +
                ", conInfoPayDate=" + conInfoPayDate +
                ", conInfoInvalidFld='" + conInfoInvalidFld + '\'' +
                ", conInfoInvFormName='" + conInfoInvFormName + '\'' +
                ", conInfoActFormName='" + conInfoActFormName + '\'' +
                ", conInfoPercent='" + conInfoPercent + '\'' +
                '}';
    }
}
