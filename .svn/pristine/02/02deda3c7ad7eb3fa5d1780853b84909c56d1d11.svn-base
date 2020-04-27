package com.hcis.ipanther.common.security.entity;

import java.util.List;

import com.hcis.ipanther.core.entity.BaseEntity;

public class UserRole extends BaseEntity {
	
	private static final long serialVersionUID = -5362502838822505400L;

	private String userId;

    private String roleId;
    
    /*
     * 非数据库映射字段
     */
    //用来传递用户可授权的角色代码
    private List<String> roleCodes;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}
}