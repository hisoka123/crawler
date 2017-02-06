package com.module.dao.entity.pbccrc;


import javax.persistence.*;

@Entity
@Table(name = "t_enforcement")
@NamedQuery(name="Enforcement.findAll", query="SELECT e FROM Enforcement e")
public class Enforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "court")
    private String court;

    @Column(name = "reference")
    private String reference;

    @Column(name = "cause")
    private String cause;

    @Column(name = "filing_time")
    private String filingTime;

    @Column(name = "apply_implement")
    private String applyimplement;

    @Column(name = "apply_amount")
    private String applyAmount;

    @Column(name = "closed_way")
    private String closedWay;

    @Column(name = "case_status")
    private String caseStatus;

    @Column(name = "implemented")
    private String implemented;

    @Column(name = "implemented_amount")
    private String implementedAmount;

    @Column(name = "closed_time")
    private String closedTime;

    @ManyToOne
    @JoinColumn(name="json_id")
    private PlainPbccrcJson plainPbccrcJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getFilingTime() {
        return filingTime;
    }

    public void setFilingTime(String filingTime) {
        this.filingTime = filingTime;
    }

    public String getApplyimplement() {
        return applyimplement;
    }

    public void setApplyimplement(String applyimplement) {
        this.applyimplement = applyimplement;
    }

    public String getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(String applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getClosedWay() {
        return closedWay;
    }

    public void setClosedWay(String closedWay) {
        this.closedWay = closedWay;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getImplemented() {
        return implemented;
    }

    public void setImplemented(String implemented) {
        this.implemented = implemented;
    }

    public String getImplementedAmount() {
        return implementedAmount;
    }

    public void setImplementedAmount(String implementedAmount) {
        this.implementedAmount = implementedAmount;
    }

    public String getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(String closedTime) {
        this.closedTime = closedTime;
    }

    public PlainPbccrcJson getPlainPbccrcJson() {
        return plainPbccrcJson;
    }

    public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
        this.plainPbccrcJson = plainPbccrcJson;
    }
}
