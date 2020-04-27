package com.hcis.ipanther.common.user.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class UserDeptKey extends BaseEntity {

	private static final long serialVersionUID = 5842792050181458327L;

	private String userId;

    private String deptId;
    
    /*
     * 无数据库对应字段
     */
    private String newDeptId;
    /**
     * 部门名称
     */
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

	public String getNewDeptId() {
		return newDeptId;
	}

	public void setNewDeptId(String newDeptId) {
		this.newDeptId = newDeptId;
	}
}