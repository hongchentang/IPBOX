package com.hcis.ipr.intellectual.call.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class PatentType extends BaseEntity {
	private String year;
	private String invent;

	private String appearance;

	private String utility;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getInvent() {
		return invent;
	}

	public void setInvent(String invent) {
		this.invent = invent;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public String getUtility() {
		return utility;
	}

	public void setUtility(String utility) {
		this.utility = utility;
	}

}
