package com.hcis.ipanther.common.user.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

/**
 * 人才管理-用户兼职
 * @author wuwentao
 * @date 2015年3月13日
 */
public class UserParttime extends BaseEntity{
	
	private static final long serialVersionUID = -5129745397757400786L;
	//用户ID
    private String userId;
    //类型：社会兼职/团体兼职
    private String type;
    //兼职名称
    private String name;
    //学术团体名称
    private String termName;
    //学术团体级别
    private String termLevel;
    //学术团体隶属或主管单位
    private String termDept;
    //学术兼职职务
    private String job;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName == null ? null : termName.trim();
    }

    public String getTermLevel() {
        return termLevel;
    }

    public void setTermLevel(String termLevel) {
        this.termLevel = termLevel == null ? null : termLevel.trim();
    }

    public String getTermDept() {
        return termDept;
    }

    public void setTermDept(String termDept) {
        this.termDept = termDept == null ? null : termDept.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }
}