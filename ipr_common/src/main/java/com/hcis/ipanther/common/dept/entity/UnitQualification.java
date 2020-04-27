package com.hcis.ipanther.common.dept.entity;


import com.hcis.ipanther.core.entity.BaseEntity;

public class UnitQualification extends BaseEntity{
	private static final long serialVersionUID = 7630449713626795118L;
	private String departmentId;
	private int departmentIsQualification;
	private int departmentIsDistrict;
	private String departmentIsOthers;

	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId ) {
		this.departmentId = departmentId;
	}

	public int getDepartmentIsQualification() {
		return departmentIsQualification;
	}
	public void setDepartmentIsQualification(int departmentIsQualification) {
		this.departmentIsQualification = departmentIsQualification;
	}
	
	public int getDepartmentIsDistrict() {
		return departmentIsDistrict;
	}
	public void setDepartmentIsDistrict(int departmentIsDistrict) {
		this.departmentIsDistrict = departmentIsDistrict;
	}
	
	public String getDepartmentIsOthers() {
		return departmentIsOthers;
	}
	public void setDepartmentIsOthers(String departmentIsOthers) {
		this.departmentIsOthers = departmentIsOthers;
	}
}

//department_id   
// man_type   
// man_name   
//man_education   		
//man_phone   		
//man_mobile   	
//man_fax   		
//man_email   
//man_card_type   	
//man_card_no   	 
//man_job   
//man_homepage   	
//man_im   			
