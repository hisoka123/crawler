package com.module.dao.entity.pbccrc;


import javax.persistence.*;

@Entity
@Table(name = "t_penalty")
@NamedQuery(name="Penalty.findAll", query="SELECT e FROM Penalty e")
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "institution")
    private String institution;

    @Column(name = "docum_number")
    private String documNumber;

    @Column(name = "content")
    private String content;

    @Column(name = "amount")
    private String amount;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "isreconsider")
    private String isreconsider;

    @Column(name = "result")
    private String result;

    @ManyToOne
    @JoinColumn(name="json_id")
    private PlainPbccrcJson plainPbccrcJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDocumNumber() {
        return documNumber;
    }

    public void setDocumNumber(String documNumber) {
        this.documNumber = documNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getIsreconsider() {
        return isreconsider;
    }

    public void setIsreconsider(String isreconsider) {
        this.isreconsider = isreconsider;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public PlainPbccrcJson getPlainPbccrcJson() {
        return plainPbccrcJson;
    }

    public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
        this.plainPbccrcJson = plainPbccrcJson;
    }
}
