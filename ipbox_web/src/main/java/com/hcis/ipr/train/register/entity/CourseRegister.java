package com.hcis.ipr.train.register.entity;

import java.math.BigDecimal;

import com.hcis.ipanther.core.entity.BaseEntity;

public class CourseRegister extends  BaseEntity{
	
	private static final long serialVersionUID = 8625341087694422368L;

    private String courseId;

    private String userId;

    private String trainId;

    private BigDecimal score;
    
    private String result;


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId == null ? null : trainId.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}