/*************************************************
*SurveySubmission.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class SurveySubmission extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String response;
	
	private float score;
	
	private String surveyUserId;
	
	public String getSurveyUserId() {
		return surveyUserId;
	}

	public void setSurveyUserId(String surveyUserId) {
		this.surveyUserId = surveyUserId;
	}

	public SurveySubmission(){
		
	}
	
	public SurveySubmission(String id,String response){
		this.id = id;
		this.response = response;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
