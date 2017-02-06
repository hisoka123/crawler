package com.module.dao.entity.pbccrc;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_plain_pbccrc_json")
@NamedQuery(name="PlainPbccrcJson.findAll", query="SELECT e FROM PlainPbccrcJson e")
public class PlainPbccrcJson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plain_pbccrc_json",columnDefinition = "TEXT",unique = true)
    private String plainPbccrcJson;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "tsf75e5b")
    private String tsf75e5b;

    @ManyToOne
    @JoinColumn(name="account_id")
    private PbccrcAccount pbccrcAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlainPbccrcJson() {
        return plainPbccrcJson;
    }

    public void setPlainPbccrcJson(String plainPbccrcJson) {
        this.plainPbccrcJson = plainPbccrcJson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PbccrcAccount getPbccrcAccount() {
        return pbccrcAccount;
    }

    public void setPbccrcAccount(PbccrcAccount pbccrcAccount) {
        this.pbccrcAccount = pbccrcAccount;
    }

	public String getTsf75e5b() {
		return tsf75e5b;
	}

	public void setTsf75e5b(String tsf75e5b) {
		this.tsf75e5b = tsf75e5b;
	}
}
