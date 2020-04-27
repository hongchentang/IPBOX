package com.hcis.ipanther.common.dept.entity;


import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;

public class UnitIntellectual extends BaseEntity{
	private static final long serialVersionUID = 763449713626795119L;
	private String departmentId;
	private Date particularYear;
	private int applyTotalCount;
	private int authorizetotalCount;
	private int patentApply;
	private int patrntAuthorize;
	private int utilityModelApply;
	private int utilityModelAuthorize;
	private int appearanceApply;
	private int appearanceAuthorize;
	private int pctApply;
	private int pctAuthorize;
	private int softwareApply;
	private int compositiveApply;

	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId ) {
		this.departmentId = departmentId;
	}

	public Date getParticularYear() {
		return particularYear;
	}
	public void setParticularYear(Date particularYear ) {
		this.particularYear = particularYear;
	}

	public int getApplyTotalCount() {
		return applyTotalCount;
	}
	public void setApplyTotalCount(int applyTotalCount ) {
		this.applyTotalCount = applyTotalCount;
	}
	
	public int getAuthorizetotalCount() {
		return authorizetotalCount;
	}
	public void setAuthorizetotalCount(int authorizetotalCount ) {
		this.authorizetotalCount = authorizetotalCount;
	}

	public int getPatentApply() {
		return patentApply;
	}
	public void setPatentApply(int patentApply ) {
		this.patentApply = patentApply;
	}

	public int getPatrntAuthorize() {
		return patrntAuthorize;
	}
	public void setPatrntAuthorize(int patrntAuthorize ) {
		this.patrntAuthorize = patrntAuthorize;
	}
	
	public int getUtilityModelApply() {
		return utilityModelApply;
	}
	public void setUtilityModelApply(int utilityModelApply ) {
		this.utilityModelApply = utilityModelApply;
	}
	
	public int getUtilityModelAuthorize() {
		return utilityModelAuthorize;
	}
	public void setUtilityModelAuthorize(int utilityModelAuthorize ) {
		this.utilityModelAuthorize = utilityModelAuthorize;
	}
	
	public int getAppearanceApply() {
		return appearanceApply;
	}
	public void setAppearanceApply(int appearanceApply ) {
		this.appearanceApply = appearanceApply;
	}
	
	public int getAppearanceAuthorize() {
		return appearanceAuthorize;
	}
	public void setAppearanceAuthorize(int appearanceAuthorize ) {
		this.appearanceAuthorize = appearanceAuthorize;
	}

	public int getPctApply() {
		return pctApply;
	}
	public void setPctApply(int pctApply ) {
		this.pctApply = pctApply;
	}
	
	public int getPctAuthorize() {
		return pctAuthorize;
	}
	public void setPctAuthorize(int pctAuthorize ) {
		this.pctAuthorize = pctAuthorize;
	}
	
	public int getSoftwareApply() {
		return softwareApply;
	}
	public void setSoftwareApply(int softwareApply ) {
		this.softwareApply = softwareApply;
	}

	public int getCompositiveApply() {
		return compositiveApply;
	}
	public void setCompositiveApply(int compositiveApply ) {
		this.compositiveApply = compositiveApply;
	}
//	particular_year ,
//	 apply_total_count   ,
//	 authorize_total_count   ,
//	 patent_apply   ,
//	 patrnt_authorize  ,
//	 utility_model_apply   ,
//	 utility_model_authorize   ,
//	 appearance_apply   ,
//	 appearance_authorize   ,
//	 pct_apply   ,
//	 pct_authorize   ,
//	 software_apply   ,
//	 compositive_apply   ,	
}			
