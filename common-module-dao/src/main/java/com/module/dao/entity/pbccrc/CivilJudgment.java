package com.module.dao.entity.pbccrc;


import javax.persistence.*;

@Entity
@Table(name = "t_civil_judgment")
@NamedQuery(name="CivilJudgment.findAll", query="SELECT e FROM CivilJudgment e")
public class CivilJudgment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filing_court")
    private String filingCourt;

    @Column(name = "reference")
    private String reference;

    @Column(name = "cause")
    private String cause;

    @Column(name = "filing_time")
    private String filingTime;

    @Column(name = "subject")
    private String subject;

    @Column(name = "subject_amount")
    private String subjectAmount;

    @Column(name = "closed_way")
    private String closedWay;

    @Column(name = "judgment_result")
    private String judgmentResult;

    @Column(name = "decision_time")
    private String decisionTime;

    @ManyToOne
    @JoinColumn(name="json_id")
    private PlainPbccrcJson plainPbccrcJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilingCourt() {
        return filingCourt;
    }

    public void setFilingCourt(String filingCourt) {
        this.filingCourt = filingCourt;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectAmount() {
        return subjectAmount;
    }

    public void setSubjectAmount(String subjectAmount) {
        this.subjectAmount = subjectAmount;
    }

    public String getClosedWay() {
        return closedWay;
    }

    public void setClosedWay(String closedWay) {
        this.closedWay = closedWay;
    }

    public String getJudgmentResult() {
        return judgmentResult;
    }

    public void setJudgmentResult(String judgmentResult) {
        this.judgmentResult = judgmentResult;
    }

    public String getDecisionTime() {
        return decisionTime;
    }

    public void setDecisionTime(String decisionTime) {
        this.decisionTime = decisionTime;
    }

    public PlainPbccrcJson getPlainPbccrcJson() {
        return plainPbccrcJson;
    }

    public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
        this.plainPbccrcJson = plainPbccrcJson;
    }
}
