package com.module.dao.entity.pbccrc;


import javax.persistence.*;

@Entity
@Table(name = "t_telecomm_arrears")
@NamedQuery(name="TelecommArrears.findAll", query="SELECT e FROM TelecommArrears e")
public class TelecommArrears {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operators")
    private String operators;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "entry_date")
    private String entryDate;

    @Column(name = "opening_time")
    private String openingTime;

    @Column(name = "arrears_amount")
    private String arrearsAmount;

    @ManyToOne
    @JoinColumn(name="json_id")
    private PlainPbccrcJson plainPbccrcJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getArrearsAmount() {
        return arrearsAmount;
    }

    public void setArrearsAmount(String arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
    }

    public PlainPbccrcJson getPlainPbccrcJson() {
        return plainPbccrcJson;
    }

    public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
        this.plainPbccrcJson = plainPbccrcJson;
    }
}
