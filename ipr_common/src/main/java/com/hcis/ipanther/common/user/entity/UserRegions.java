package com.hcis.ipanther.common.user.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class UserRegions extends BaseEntity {

	private static final long serialVersionUID = 128212005094005911L;

	private String module;

	private String userId;

	private String regionsCode;

	private String hasChild;

	private String note;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module == null ? null : module.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getRegionsCode() {
		return regionsCode;
	}

	public void setRegionsCode(String regionsCode) {
		this.regionsCode = regionsCode == null ? null : regionsCode.trim();
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild == null ? null : hasChild.trim();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}
	
}
