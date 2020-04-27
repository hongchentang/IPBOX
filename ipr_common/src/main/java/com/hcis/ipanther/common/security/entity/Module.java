package com.hcis.ipanther.common.security.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class Module extends BaseEntity {

	// 版块名称
	private String name;
	//系统类型 MASTER/SLAVE
	private String type;
	// 版块描述
	private String description;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
