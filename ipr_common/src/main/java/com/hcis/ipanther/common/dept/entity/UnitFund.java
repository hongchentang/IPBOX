package com.hcis.ipanther.common.dept.entity;

import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;

public class UnitFund extends BaseEntity{
	private static final long serialVersionUID = 7630449713626795114L;
	private String departmentId;
	private Date particularYear;
	private int nationalCount;
	private int provinceCount;
	private int cityCount;
	private int countiesCount;
	private int othersCount;
	private int totalCount;
	private float nationalAmount;
	private float provinceAmount;
	private float cityAmount;
	private float countiesAmount;
	private float othersAmount;
	private float totalAmount;
	

	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Date getParticularYear() {
		return particularYear;
	}
	public void setParticularYear(Date particularYear) {
		this.particularYear = particularYear;
	}
	
	public int getNationalCount() {
		return nationalCount;
	}
	public void setNationalCount(int nationalCount) {
		this.nationalCount = nationalCount;
	}
	
	public int getProvinceCount() {
		return provinceCount;
	}
	public void setProvinceCount(int provinceCount) {
		this.provinceCount = provinceCount;
	}
	
	public int getCityCount() {
		return cityCount;
	}
	public void setCityCount(int cityCount) {
		this.cityCount = cityCount;
	}

	public int getCountiesCount() {
		return countiesCount;
	}
	public void setCountiesCount(int countiesCount) {
		this.countiesCount = countiesCount;
	}
	
	public int getOthersCount() {
		return othersCount;
	}
	public void setOthersCount(int othersCount) {
		this.othersCount = othersCount;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public float getNationalAmount() {
		return nationalAmount;
	}
	public void setNationalAmount(float nationalAmount) {
		this.nationalAmount = nationalAmount;
	}
	
	public float getProvinceAmount() {
		return provinceAmount;
	}
	public void setProvinceAmount(float provinceAmount) {
		this.provinceAmount = provinceAmount;
	}
	
	public float getCityAmount() {
		return cityAmount;
	}
	public void setCityAmount(float cityAmount) {
		this.cityAmount = cityAmount;
	}

	public float getCountiesAmount() {
		return countiesAmount;
	}
	public void setCountiesAmount(float countiesAmount) {
		this.countiesAmount = countiesAmount;
	}

	public float getOthersAmount() {
		return othersAmount;
	}
	public void setOthersAmount(float othersAmount) {
		this.othersAmount = othersAmount;
	}

	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
}

//`department_id` varchar(64) not null,
//`particular_year` datetime,
// national_count int default 0,
// province_count int default 0,
// city_count int default 0,
// counties_count int default 0,
// others_count int default 0,
// total_count int default 0,
// national_amount decimal(18,2) default 0.0,
// province_amount decimal(18,2) default 0.0,
// city_amount decimal(18,2) default 0.0,
// counties_amount decimal(18,2) default 0.0,
// others_amount decimal(18,2) default 0.0,
// total_amount decimal(18,2) default 0.0,
 			
