package com.module.dao.entity.pbccrc;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_credit_record_profile")
@NamedQuery(name="CreditRecordProfile.findAll", query="SELECT e FROM CreditRecordProfile e")
public class CreditRecordProfile implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type",length = 50)
    private String type;

    @Column(name="account_num",length = 50)
    private Integer accountNum;

    @Column(name="active_num",length = 50)
    private Integer activeNum;

    @Column(name="overdue_num",length = 50)
    private Integer overdueNum;

    @Column(name="overdue90_num",length = 50)
    private Integer overdue90Num;

    @Column(name="guarantee_num",length = 50)
    private Integer  guaranteeNum;

    @ManyToOne
    @JoinColumn(name="json_id")
    private PlainPbccrcJson plainPbccrcJson;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public Integer getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(Integer activeNum) {
        this.activeNum = activeNum;
    }

    public Integer getOverdueNum() {
        return overdueNum;
    }

    public void setOverdueNum(Integer overdueNum) {
        this.overdueNum = overdueNum;
    }

    public Integer getOverdue90Num() {
        return overdue90Num;
    }

    public void setOverdue90Num(Integer overdue90Num) {
        this.overdue90Num = overdue90Num;
    }

    public Integer getGuaranteeNum() {
        return guaranteeNum;
    }

    public void setGuaranteeNum(Integer guaranteeNum) {
        this.guaranteeNum = guaranteeNum;
    }

    public PlainPbccrcJson getPlainPbccrcJson() {
        return plainPbccrcJson;
    }

    public void setPlainPbccrcJson(PlainPbccrcJson plainPbccrcJson) {
        this.plainPbccrcJson = plainPbccrcJson;
    }
}
