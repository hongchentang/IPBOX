/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 2013-8-23
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
*************************************************/
package com.hcis.ipanther.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 675694438032697086L;
	
	protected String id;
	
	protected String creator;
	
	protected String updatedby;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected String isDeleted = "N";
	
	protected int version;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public void setDefaultValue(){
		this.createTime = new Date();
		this.isDeleted = "N";
		this.updateTime = new Date();
		this.version = 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
