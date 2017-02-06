package com.module.aws;


import java.util.ArrayList;
import java.util.List;

public class Permission {

    private List<IpPermissionVO> saveRules = new ArrayList<>();
    private List<IpPermissionVO> delRules  = new ArrayList<>();

    public List<IpPermissionVO> getSaveRules() {
        return saveRules;
    }

    public void setSaveRules(List<IpPermissionVO> saveRules) {
        this.saveRules = saveRules;
    }

    public List<IpPermissionVO> getDelRules() {
        return delRules;
    }

    public void setDelRules(List<IpPermissionVO> delRules) {
        this.delRules = delRules;
    }
}
