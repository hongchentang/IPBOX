package com.hcis.ipanther.common.user.entity;

import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;
/**
 * 用户注册成为教师/人才流转日志
 * 申请与审批同一条记录
 * 如流程超过一个步骤，则改用流程机
 * @author wuwentao
 * @date 2015年3月23日
 */
public class UserRegisterFlow extends BaseEntity{
	
	private static final long serialVersionUID = 933305095542920424L;
	//当时人才日志时，是用户的ID，当是教师的日志时，是ipr_user_register的ID
	private String userId;
	//环节名称
    private String taskName;
    //环节标识：01人才申请 教师申请对应流程机的taskId
    private String taskId;
    //审核部门、操作部门
    private String auditDept;
    //审核时间、操作时间
    private Date auditTime;
    //审核人、操作人
    private String auditUser;
    //审核结果 字典：FLOW_AUDIT_RESULT  FlowConstants.AUDIT_RESULT
    private String auditResult;
    //审核内容、备注信息
    private String auditContent;
    //状态：00待办理01已办理 对应枚举 UserRegisterFlow.Status
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getAuditDept() {
        return auditDept;
    }

    public void setAuditDept(String auditDept) {
        this.auditDept = auditDept == null ? null : auditDept.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser == null ? null : auditUser.trim();
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult == null ? null : auditResult.trim();
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent == null ? null : auditContent.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public enum Status {
		UNDONE("00"), DONE("01");
		private String status;
		private Status(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return status;
		}
	}
    
    public enum TaskId {
		STUDENT_DEFAULT("01");//学员时固定值，教师时为流程机的task_id
		private String taskId;
		private TaskId(String taskId) {
			this.taskId = taskId;
		}
		@Override
		public String toString() {
			return taskId;
		}
	}
    
    public enum TaskName {
		STUDENT_DEFAULT("区域管理员审核");//学员时固定值，教师时为流程机的task_id
		private String taskName;
		private TaskName(String taskName) {
			this.taskName = taskName;
		}
		@Override
		public String toString() {
			return taskName;
		}
	}
}