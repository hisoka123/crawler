package com.module.dao.entity.pbccrc;


import javax.persistence.*;

@Entity
@Table(name = "t_taxes_owed")
@NamedQuery(name="TaxesOwed.findAll", query="SELECT e FROM TaxesOwed e")
public class TaxesOwed {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tax_authority")
    private String taxAuthority;

    @Column(name = "moment")
    private String moment;

    @Column(name = "taxes_total")
    private String taxesTotal;

    @Column(name = "identify_code")
    private String identifyCode;

    @ManyToOne
    @JoinColumn(name="account_id")
    private PlainPbccrcJson plainPbccrcJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public String getTaxesTotal() {
        return taxesTotal;
    }

    public void setTaxesTotal(String taxesTotal) {
        this.taxesTotal = taxesTotal;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public PlainPbccrcJson getPlainPbccrcJson() {
        return plainPbccrcJson;
    }

    public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
        this.plainPbccrcJson = plainPbccrcJson;
    }
}
