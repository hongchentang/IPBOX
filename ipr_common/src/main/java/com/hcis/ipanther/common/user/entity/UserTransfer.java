package com.hcis.ipanther.common.user.entity;

import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;

public class UserTransfer extends BaseEntity {
	
	private static final long serialVersionUID = 4244406157014901305L;

	private String id;

    private String userId;

    private String outDeptId;

    private Date outTime;

    private String outOperator;

    private String outComment;

    private String inDeptId;

    private Date inTime;

    private String inComment;

    private String inOperator;

    private String isTransfered;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOutDeptId() {
        return outDeptId;
    }

    public void setOutDeptId(String outDeptId) {
        this.outDeptId = outDeptId == null ? null : outDeptId.trim();
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getOutOperator() {
        return outOperator;
    }

    public void setOutOperator(String outOperator) {
        this.outOperator = outOperator == null ? null : outOperator.trim();
    }

    public String getOutComment() {
        return outComment;
    }

    public void setOutComment(String outComment) {
        this.outComment = outComment == null ? null : outComment.trim();
    }

    public String getInDeptId() {
        return inDeptId;
    }

    public void setInDeptId(String inDeptId) {
        this.inDeptId = inDeptId == null ? null : inDeptId.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getInComment() {
        return inComment;
    }

    public void setInComment(String inComment) {
        this.inComment = inComment == null ? null : inComment.trim();
    }

    public String getInOperator() {
        return inOperator;
    }

    public void setInOperator(String inOperator) {
        this.inOperator = inOperator == null ? null : inOperator.trim();
    }

    public String getIsTransfered() {
        return isTransfered;
    }

    public void setIsTransfered(String isTransfered) {
        this.isTransfered = isTransfered == null ? null : isTransfered.trim();
    }

}