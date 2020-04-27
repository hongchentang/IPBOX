package com.hcis.ipanther.common.security.entity;

import java.math.BigDecimal;
import java.util.List;

import com.hcis.ipanther.core.entity.BaseEntity;

/**
 * 权限信息
 * @author 梁华璜
 */
public class Privilege extends BaseEntity{
    
	private static final long serialVersionUID = -4150801670211901638L;

	//父ID
    private String pid;
    //权限名称
    private String name;
    //权限描述
    private String description;
    //权限所属模块
    private String module;
    //权限对应的操作
    private String permission;
    //是否叶子节点
    private String isLeaf;
    //是否允许显示，主要用于功能菜单
    private String isDisplay;
    //访问地址
    private String url;

    private String target;
    //
    //排序号
    private BigDecimal orderNo;

    private List<Role> roleList;

    /**
     * 下级菜单
     */
    private List<Privilege> nextLevelPrivileges;

    public List<Privilege> getNextLevelPrivileges() {
        return nextLevelPrivileges;
    }

    public void setNextLevelPrivileges(List<Privilege> nextLevelPrivileges) {
        this.nextLevelPrivileges = nextLevelPrivileges;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay == null ? null : isDisplay.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public BigDecimal getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(BigDecimal orderNo) {
        this.orderNo = orderNo;
    }

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}