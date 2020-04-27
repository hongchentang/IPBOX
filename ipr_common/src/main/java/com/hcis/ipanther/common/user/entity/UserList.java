/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 20132013-3-11
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
package com.hcis.ipanther.common.user.entity;

import java.util.Date;

/**
 * @author lianghuahuang
 *
 */
public class UserList extends User{
	
	private String deptId;
	
	private String deptName;
	
	private String parentDeptId;
	
	private int deptLevel;
	
	private String deptType;
	
	private String province;
	
	private String city;
	
	private String counties;
	
	private String provinceName;
	
	private String cityName;
	
	private String countiesName;
	
	private String postType;

	private String teacherNo;
	private String teachSubject;
	private String teachStage;
	private String highCollege;
	private String highSubject;
    private Date workDt;
	private String highDiploma;
	private String title;
	
	private String dataPosition;
	

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public int getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounties() {
		return counties;
	}

	public void setCounties(String counties) {
		this.counties = counties;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountiesName() {
		return countiesName;
	}

	public void setCountiesName(String countiesName) {
		this.countiesName = countiesName;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeachSubject() {
		return teachSubject;
	}

	public void setTeachSubject(String teachSubject) {
		this.teachSubject = teachSubject;
	}

	public String getTeachStage() {
		return teachStage;
	}

	public void setTeachStage(String teachStage) {
		this.teachStage = teachStage;
	}

	public String getHighCollege() {
		return highCollege;
	}

	public void setHighCollege(String highCollege) {
		this.highCollege = highCollege;
	}

	public String getHighSubject() {
		return highSubject;
	}

	public void setHighSubject(String highSubject) {
		this.highSubject = highSubject;
	}

	public Date getWorkDt() {
		return workDt;
	}

	public void setWorkDt(Date workDt) {
		this.workDt = workDt;
	}

	public String getHighDiploma() {
		return highDiploma;
	}

	public void setHighDiploma(String highDiploma) {
		this.highDiploma = highDiploma;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDataPosition() {
		return dataPosition;
	}

	public void setDataPosition(String dataPosition) {
		this.dataPosition = dataPosition;
	}

}
