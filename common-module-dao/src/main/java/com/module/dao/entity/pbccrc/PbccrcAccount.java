package com.module.dao.entity.pbccrc;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_pbccrc_account", uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
@NamedQuery(name="PbccrcAccount.findAll", query="SELECT e FROM PbccrcAccount e")
public class PbccrcAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "username",length = 50)
    private String username;

    @Column(name = "password",length = 300)
    private String password;

    @Column(name = "tradecode",length = 50)
    private String tradecode;

    @OneToMany(mappedBy = "pbccrcAccount")
    private List<PlainPbccrcJson> plainPbccrcJsons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTradecode() {
        return tradecode;
    }

    public void setTradecode(String tradecode) {
        this.tradecode = tradecode;
    }

    public List<PlainPbccrcJson> getPlainPbccrcJsons() {
        return plainPbccrcJsons;
    }

    public void setPlainPbccrcJsons(List<PlainPbccrcJson> plainPbccrcJsons) {
        this.plainPbccrcJsons = plainPbccrcJsons;
    }
}
