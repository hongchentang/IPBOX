package com.hcis.ipanther.common.user.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class AdminDept extends BaseEntity {

    private String userId;

    private String deptId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}