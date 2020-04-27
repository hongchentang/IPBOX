package com.hcis.ipr.intellectual.cost.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhw
 * @date 2019/8/29
 **/
public class PatentCostMonitorListDto {

    /**
     * 监控id
     */
    private String id;

    /**
     * 距离期限天数
     */
    private Integer deadDays;

    /**
     * 申请号
     */
    private String appNumber;

    /**
     * 专利名称
     */
    private String patentName;

    /**
     * 专利权人
     */
    private String applyer;

    /**
     * 专利申请日
     */
    private Date appDate;

    /**
     * 费用名称
     */
    private String costName;

    /**
     * 费用缴费状态
     */
    private Integer costStatus;

    /**
     * 应缴费日期
     */
    private Date feePayableDate;

    /**
     * 缴费绝限日期
     */
    private Date standardDate;

    /**
     *
     */
    private String standardDateFormat;

    /**
     *
     */
    private String feePayableDateFormat;

    /**
     * 执行缴费人
     */
    private String creator;

    /**
     * 核销人
     */
    private String verification;

    /**
     * 期限状态
     */
    private Integer status;

    public String getFeePayableDateFormat() {
        if(this.feePayableDate != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.feePayableDateFormat = format.format(this.feePayableDate);
        }
        return feePayableDateFormat;
    }

    public void setFeePayableDateFormat(String feePayableDateFormat) {
        this.feePayableDateFormat = feePayableDateFormat;
    }

    public String getStandardDateFormat() {
        if(this.standardDate != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.standardDateFormat = format.format(this.standardDate);
        }
        return standardDateFormat;
    }

    public void setStandardDateFormat(String standardDateFormat) {
        this.standardDateFormat = standardDateFormat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeadDays() {
        return deadDays;
    }

    public void setDeadDays(Integer deadDays) {
        this.deadDays = deadDays;
    }

    public String getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(String appNumber) {
        this.appNumber = appNumber;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Integer getCostStatus() {
        return costStatus;
    }

    public void setCostStatus(Integer costStatus) {
        this.costStatus = costStatus;
    }

    public Date getFeePayableDate() {
        return feePayableDate;
    }

    public void setFeePayableDate(Date feePayableDate) {
        this.feePayableDate = feePayableDate;
    }

    public Date getStandardDate() {
        return standardDate;
    }

    public void setStandardDate(Date standardDate) {
        this.standardDate = standardDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
