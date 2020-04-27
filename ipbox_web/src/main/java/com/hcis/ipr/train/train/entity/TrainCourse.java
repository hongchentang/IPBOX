package com.hcis.ipr.train.train.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;

public class TrainCourse extends  BaseEntity{

	private static final long serialVersionUID = 8827341087694462367L;

    private String trainId;

    private String courseId;

    private String expertId;

    private String address;

    private BigDecimal countChoose;

    private BigDecimal countCash;

    private BigDecimal countPass;

    private BigDecimal resultScore;

    private BigDecimal satisfyScore;

    private String attachment;



    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId == null ? null : trainId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId == null ? null : expertId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getCountChoose() {
        return countChoose;
    }

    public void setCountChoose(BigDecimal countChoose) {
        this.countChoose = countChoose;
    }

    public BigDecimal getCountCash() {
        return countCash;
    }

    public void setCountCash(BigDecimal countCash) {
        this.countCash = countCash;
    }

    public BigDecimal getCountPass() {
        return countPass;
    }

    public void setCountPass(BigDecimal countPass) {
        this.countPass = countPass;
    }

    public BigDecimal getResultScore() {
        return resultScore;
    }

    public void setResultScore(BigDecimal resultScore) {
        this.resultScore = resultScore;
    }

    public BigDecimal getSatisfyScore() {
        return satisfyScore;
    }

    public void setSatisfyScore(BigDecimal satisfyScore) {
        this.satisfyScore = satisfyScore;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

}