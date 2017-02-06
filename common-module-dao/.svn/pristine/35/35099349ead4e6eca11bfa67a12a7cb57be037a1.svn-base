package com.module.dao.entity.auth;

import com.module.dao.entity.data.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zxz on 2015/11/4.
 */

@Entity
@Table(name = "Menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu extends IdEntity {

    // 父级ID
    private String fatherId;
    //序号
    private long recNo;
    // 菜单名称
    private String menuName;
    // 菜单URL
    private String menuUrl;
    // 菜单级别
    private long menuLevel;
    // 菜单状态
    private long menuStatus;
    //菜单类型(1,菜单,2,按扭)
    private String type;
    //创建人
    private String createMan;
    // 创建时间
    private String createDate;
    //更新人
    private String updateMan;
    //更新时间
    private String updateDate;
    // 备注
    private String remarks;


    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public long getRecNo() {
        return recNo;
    }

    public void setRecNo(long recNo) {
        this.recNo = recNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public long getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(long menuLevel) {
        this.menuLevel = menuLevel;
    }

    public long getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(long menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateMan() {
        return updateMan;
    }

    public void setUpdateMan(String updateMan) {
        this.updateMan = updateMan;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
