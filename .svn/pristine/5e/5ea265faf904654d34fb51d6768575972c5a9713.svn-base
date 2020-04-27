/*************************************************
*SurveyUser.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.entity;

import java.util.List;

import com.hcis.ipanther.core.entity.BaseEntity;

public class SurveyUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private Survey survey;
	
	private String surveyId;
	
	private String userId;
	
	private String relationId;
	
	private String completionStatus;
	
	private float sumScore;
	
	private List<SurveySubmission> surveySubmissions;

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public float getSumScore() {
		return sumScore;
	}

	public void setSumScore(float sumScore) {
		this.sumScore = sumScore;
	}

	public List<SurveySubmission> getSurveySubmissions() {
		return surveySubmissions;
	}

	public void setSurveySubmissions(List<SurveySubmission> surveySubmissions) {
		this.surveySubmissions = surveySubmissions;
	} 
	
	public enum CompletionStatus {
		UNKNOWN("unknown"),
		NOTATTEMPED("notAttemped"),
		COMPLETE("complete");	
		private String completionStatus;
		private CompletionStatus(String completionStatus){
			this.completionStatus = completionStatus;
		}
		public String toString(){
			return completionStatus;
		}
	}
}
