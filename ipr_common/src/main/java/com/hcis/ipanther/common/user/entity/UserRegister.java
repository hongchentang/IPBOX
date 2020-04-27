package com.hcis.ipanther.common.user.entity;

import com.hcis.ipanther.core.entity.BaseEntity;
/**
 * 用户注册成为教师/学员流程主表
 * @author wuwentao
 * @date 2015年3月30日
 */
public class UserRegister extends BaseEntity{
	
	private static final long serialVersionUID = 3517064491429571443L;

	//用户ID
	private String userId;
	//类型：人才还是教师 对应枚举 UserRegister.Type
    private String type;
    //流程实例的ID
    private String procInstId;
    //意见/备注
    private String remark;
    //状态：01正在进行02已经结束 对应枚举：UserRegister.Status
    private String status;
    //最终审核的ID
    private String lastFlowId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastFlowId() {
		return lastFlowId;
	}

	public void setLastFlowId(String lastFlowId) {
		this.lastFlowId = lastFlowId;
	}
	
	public enum Status {
		UNDONE("01"), DONE("02");
		private String status;
		private Status(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return status;
		}
	}
	
	public enum Type {
		STUDENT("01"), TEACHER("02");
		private String type;
		private Type(String status) {
			this.type = status;
		}
		@Override
		public String toString() {
			return type;
		}
	}
    
}