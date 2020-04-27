package com.hcis.ipr.evalua.effect.entity;

import java.math.BigDecimal;

import com.hcis.ipanther.core.entity.BaseEntity;

public class TeachingEvalua extends BaseEntity{
 
	private static final long serialVersionUID = 8648441754856521677L;

	private String projectId;

    private String userId;

    private String courseId;

    private String trainId;

    private String teacherId;

    private BigDecimal inseparable;

    private BigDecimal accuracy;

    private BigDecimal lively;

    private BigDecimal deepGoing;

    private BigDecimal clear;

    private BigDecimal insideDopester;

    private BigDecimal properly;

    private BigDecimal bodyLanguage;

    private BigDecimal appropriate;

    private BigDecimal totalNum;

    private BigDecimal active;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId == null ? null : trainId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public BigDecimal getInseparable() {
        return inseparable;
    }

    public void setInseparable(BigDecimal inseparable) {
        this.inseparable = inseparable;
    }

    public BigDecimal getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(BigDecimal accuracy) {
        this.accuracy = accuracy;
    }

    public BigDecimal getLively() {
        return lively;
    }

    public void setLively(BigDecimal lively) {
        this.lively = lively;
    }

    public BigDecimal getDeepGoing() {
        return deepGoing;
    }

    public void setDeepGoing(BigDecimal deepGoing) {
        this.deepGoing = deepGoing;
    }

    public BigDecimal getClear() {
        return clear;
    }

    public void setClear(BigDecimal clear) {
        this.clear = clear;
    }

    public BigDecimal getInsideDopester() {
        return insideDopester;
    }

    public void setInsideDopester(BigDecimal insideDopester) {
        this.insideDopester = insideDopester;
    }

    public BigDecimal getProperly() {
        return properly;
    }

    public void setProperly(BigDecimal properly) {
        this.properly = properly;
    }

    public BigDecimal getBodyLanguage() {
        return bodyLanguage;
    }

    public void setBodyLanguage(BigDecimal bodyLanguage) {
        this.bodyLanguage = bodyLanguage;
    }

    public BigDecimal getAppropriate() {
        return appropriate;
    }

    public void setAppropriate(BigDecimal appropriate) {
        this.appropriate = appropriate;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getActive() {
        return active;
    }

    public void setActive(BigDecimal active) {
        this.active = active;
    }
}