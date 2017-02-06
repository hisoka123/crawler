package com.crawler.gsxt.domain.json.chongqing;



public class History {

    private String year;
    private String pubDate;
    private String Id;
    private String firstDate;

    public History() {    }

    public History(String year, String pubDate, String id, String firstDate) {
        this.year = year;
        this.pubDate = pubDate;
        Id = id;
        this.firstDate = firstDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    @Override
    public String toString() {
        return "History{" +
                "year='" + year + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", Id='" + Id + '\'' +
                ", firstDate='" + firstDate + '\'' +
                '}';
    }
}
