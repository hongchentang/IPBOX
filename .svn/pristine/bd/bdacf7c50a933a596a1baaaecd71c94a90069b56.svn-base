package com.hcis.ipr.train.effect.entity;

import com.hcis.ipanther.core.entity.BaseEntity;
/**
 * 培训需求调研问卷结果
 * @author wuwentao
 * @date 2015年4月22日
 */
public class CheckResult extends BaseEntity{
	
	private static final long serialVersionUID = -1413309185674221005L;
	//抽查的ID
	private String checkId;
	//项目的ID
	private String projectId;
	//教师/专家的ID
    private String teacherId;
    //评估意见 
    private String result;
    //状态 对应枚举 CheckResultStatus 对应字典项 CHECK_RESULT_STATUS
    private String status;
    
    /*
     * 无数据库对应字段
     */
    //项目名称
    private String projectName;
    //教师名称
    private String teacherName;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public enum CheckResultStatus {
    	UNDONE("01"),//未评估
		DONE("02");//已评估
		private String status;
		private CheckResultStatus(String status){
			this.status = status;
		}
		public String toString(){
			return status;
		}
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}