package com.hcis.ipanther.common.user.entity;

/**
 * 用户资料扩展信息备份表
 * 与ipanther_user_his是一对一的关系
 * @author wuwentao
 * @date 2015年4月9日
 */
public class UserStaffHis extends UserStaff{
	
	private static final long serialVersionUID = 1631559981172714298L;
	
	private String hisId;

	public String getHisId() {
		return hisId;
	}

	public void setHisId(String hisId) {
		this.hisId = hisId;
	}
    
}