package com.hcis.ipr.train.train.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.hcis.ipanther.core.entity.BaseEntity;

public class Train extends  BaseEntity{

	private static final long serialVersionUID = 8827341087694462368L;
	
    private String projectId;

    private String trainName;

    private String trainCode;

    private String trainIntro;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date cashStartTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date cashEndTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date studyStartTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date studyEndTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date scoreTime;
    
    private BigDecimal countChoose;

    private BigDecimal countCash;

    private BigDecimal countPass;

    private BigDecimal resultScore;

    private BigDecimal satisfyScore;
    
    private BigDecimal courseEvaluaCount;
    
    private BigDecimal teachingEvaluaCount;
    
    private String isStopApply;
    
    private String isRoom;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date roomStartTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date roomEndTime;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode == null ? null : trainCode.trim();
    }

    public String getTrainIntro() {
        return trainIntro;
    }

    public void setTrainIntro(String trainIntro) {
        this.trainIntro = trainIntro == null ? null : trainIntro.trim();
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

    public Date getCashStartTime() {
        return cashStartTime;
    }

    public void setCashStartTime(Date cashStartTime) {
        this.cashStartTime = cashStartTime;
    }

    public Date getCashEndTime() {
        return cashEndTime;
    }

    public void setCashEndTime(Date cashEndTime) {
        this.cashEndTime = cashEndTime;
    }

    public Date getStudyStartTime() {
        return studyStartTime;
    }

    public void setStudyStartTime(Date studyStartTime) {
        this.studyStartTime = studyStartTime;
    }

    public Date getStudyEndTime() {
        return studyEndTime;
    }

    public void setStudyEndTime(Date studyEndTime) {
        this.studyEndTime = studyEndTime;
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
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

	public BigDecimal getCourseEvaluaCount() {
		return courseEvaluaCount;
	}

	public void setCourseEvaluaCount(BigDecimal courseEvaluaCount) {
		this.courseEvaluaCount = courseEvaluaCount;
	}

	public BigDecimal getTeachingEvaluaCount() {
		return teachingEvaluaCount;
	}

	public void setTeachingEvaluaCount(BigDecimal teachingEvaluaCount) {
		this.teachingEvaluaCount = teachingEvaluaCount;
	}

	public String getIsStopApply() {
		return isStopApply;
	}

	public void setIsStopApply(String isStopApply) {
		this.isStopApply = isStopApply;
	}

	public String getIsRoom() {
		return isRoom;
	}

	public void setIsRoom(String isRoom) {
		this.isRoom = isRoom;
	}

	public Date getRoomStartTime() {
		return roomStartTime;
	}

	public void setRoomStartTime(Date roomStartTime) {
		this.roomStartTime = roomStartTime;
	}

	public Date getRoomEndTime() {
		return roomEndTime;
	}

	public void setRoomEndTime(Date roomEndTime) {
		this.roomEndTime = roomEndTime;
	}
	
}