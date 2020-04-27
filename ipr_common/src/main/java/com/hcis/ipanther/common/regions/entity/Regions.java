package com.hcis.ipanther.common.regions.entity;

import java.math.BigDecimal;

import com.hcis.ipanther.core.entity.BaseEntity;

public class Regions extends BaseEntity {

	private static final long serialVersionUID = 3235204813260175783L;


	private String regionsCode;

	private String regionsName;

	private String parentCode;

	private BigDecimal regionsLevel;


	public String getRegionsCode() {
		return regionsCode;
	}

	public void setRegionsCode(String regionsCode) {
		this.regionsCode = regionsCode;
	}

	public String getRegionsName() {
		return regionsName;
	}

	public void setRegionsName(String regionsName) {
		this.regionsName = regionsName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public BigDecimal getRegionsLevel() {
		return regionsLevel;
	}

	public void setRegionsLevel(BigDecimal regionsLevel) {
		this.regionsLevel = regionsLevel;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Regions) {
			Regions regions = (Regions) obj;
			if(id!=null&&regions.getId()!=null){
			return id.equals(regions.getId());
			}
			else{
				return regionsCode.equals(regions.getRegionsCode())&&parentCode.equals(regions.getParentCode());
			}
		}
		return super.equals(obj);
	}

}