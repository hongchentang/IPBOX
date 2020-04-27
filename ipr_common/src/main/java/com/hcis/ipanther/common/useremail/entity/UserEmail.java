package com.hcis.ipanther.common.useremail.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class UserEmail extends BaseEntity {

	private String name;
	private String userEmail;
	private String userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
