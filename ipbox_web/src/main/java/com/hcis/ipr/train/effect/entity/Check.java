package com.hcis.ipr.train.effect.entity;

import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;
/**
 * 省级管理机构抽查
 * @author wuwentao
 * @date 2015年4月22日
 */
public class Check extends BaseEntity{
	
	private static final long serialVersionUID = -1291249819397263784L;
	//标题
	private String title;
	//项目的ID，存储json集合
	private String projectIds;
	//教师/专家的ID，存储json集合
    private String teacherIds;
	//备注说明
    private String remark;
	//状态 对应枚举 CheckStatus 对应字典项CHECK_STATUS
    private String status;
	//发布时间
    private Date publishTime;

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds == null ? null : projectIds.trim();
    }

    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds == null ? null : teacherIds.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    
    public enum CheckStatus {
    	UNPUBLISHED("01"),//未发布
		PUBLISHED("02");//已发布
		private String status;
		private CheckStatus(String status){
			this.status = status;
		}
		public String toString(){
			return status;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}