package com.hcis.ipr.train.requirement.entity;

import java.util.Date;

import com.hcis.survey.entity.Survey;

public class RequirementSurvey extends Survey{
	
	private static final long serialVersionUID = -16393772855240285L;
	//关联的问卷ID
    private String surveyId;
    //参与区域，存储json集合
    private String regionsCodes;
    //参与项目，存储json集合
    private String projectIds;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    
    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId == null ? null : surveyId.trim();
    }
    
    public String getRegionsCodes() {
        return regionsCodes;
    }

    public void setRegionsCodes(String regionsCodes) {
        this.regionsCodes = regionsCodes == null ? null : regionsCodes.trim();
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds == null ? null : projectIds.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}