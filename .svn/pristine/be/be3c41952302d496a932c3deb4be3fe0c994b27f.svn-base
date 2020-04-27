package com.hcis.ipanther.common.security.entity;

import java.util.List;
import java.util.Objects;

import com.hcis.ipanther.core.entity.BaseEntity;

/**
 * 角色信息
 * @author 梁华璜
 * 
 */
public class Role extends BaseEntity{

	
	private static final long serialVersionUID = 4996633340880648731L;
	
	private String id;
	//角色名称
	private String name;
	//角色描述
	private String description;

	private String roleCode;
	//功能权限
	private List<Privilege> privilegeList;
	//所属模块
	private String module;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Role role = (Role) o;
		return Objects.equals(roleCode, role.roleCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleCode);
	}
}
