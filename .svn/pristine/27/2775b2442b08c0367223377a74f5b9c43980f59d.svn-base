package com.hcis.ipanther.common.user.service;

import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.user.entity.UserStaffHis;
import com.hcis.ipanther.core.service.IBaseService;

public interface IUserStaffHisService extends IBaseService<UserStaffHis> {
	
	/**
	 * 备份用户扩展信息
	 * 将UserStaff转换为UserStaffHis存入历史 只有是学员时才需要备份
	 * 注意：
	 * 此方法执行前，一般都会先执行 IUserHisService.bakUser hisId为该方法的返回值
	 * 如果hisId返回值为空，说明User不符合备份条件，无需执行备份逻辑
	 * 此方法必须在UserStaff的create或者update后调用
	 * @param userId
	 * @param hisId
	 * @param userStaff
	 * @param creator
	 * @param isUpdate 如果true，则需要先将userStaff从数据库中取出插入到userStaffHis中，再更新userStaff到userStaffHis中
	 * @return
	 */
	int bakUserStaff(String userId,String hisId);
	
}
