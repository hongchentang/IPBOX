package com.hcis.ipr.intellectual.patent.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;

public class PatentAttachment extends BaseEntity {
    private String id;

    private String patentAppNumber;

    private String patentId;

    private Integer type;

    private String fileName;

    private String fileFullName;

    private String filePath;

    private String fileSuffix;

    private Double fileSize;

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public String getPatentAppNumber() {
        return patentAppNumber;
    }

    public void setPatentAppNumber(String patentAppNumber) {
        this.patentAppNumber = patentAppNumber == null ? null : patentAppNumber.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileFullName() {
        return fileFullName;
    }

    public void setFileFullName(String fileFullName) {
        this.fileFullName = fileFullName == null ? null : fileFullName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix == null ? null : fileSuffix.trim();
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

}