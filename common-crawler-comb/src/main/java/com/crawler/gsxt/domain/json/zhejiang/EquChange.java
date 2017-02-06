package com.crawler.gsxt.domain.json.zhejiang;

/**
 * 企业公示->企业年报->股权变更
 */
public class EquChange {
    String stockHolder;
    String corpid;
    String stockBeforePercent;
    BusWebSysTime stockChangeDate;
    String stockNo;
    String stockId;
    String year;
    String stockAfterPercent;
    String stockReportNo;

    public EquChange() {
    }

    public String getStockHolder() {
        return stockHolder;
    }

    public void setStockHolder(String stockHolder) {
        this.stockHolder = stockHolder;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getStockBeforePercent() {
        return stockBeforePercent;
    }

    public void setStockBeforePercent(String stockBeforePercent) {
        this.stockBeforePercent = stockBeforePercent;
    }

    public BusWebSysTime getStockChangeDate() {
        return stockChangeDate;
    }

    public void setStockChangeDate(BusWebSysTime stockChangeDate) {
        this.stockChangeDate = stockChangeDate;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStockAfterPercent() {
        return stockAfterPercent;
    }

    public void setStockAfterPercent(String stockAfterPercent) {
        this.stockAfterPercent = stockAfterPercent;
    }

    public String getStockReportNo() {
        return stockReportNo;
    }

    public void setStockReportNo(String stockReportNo) {
        this.stockReportNo = stockReportNo;
    }

    @Override
    public String toString() {
        return "EquChange{" +
                "stockHolder='" + stockHolder + '\'' +
                ", corpid='" + corpid + '\'' +
                ", stockBeforePercent='" + stockBeforePercent + '\'' +
                ", stockChangeDate=" + stockChangeDate +
                ", stockNo='" + stockNo + '\'' +
                ", stockId='" + stockId + '\'' +
                ", year='" + year + '\'' +
                ", stockAfterPercent='" + stockAfterPercent + '\'' +
                ", stockReportNo='" + stockReportNo + '\'' +
                '}';
    }
}
