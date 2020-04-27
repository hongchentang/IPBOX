package com.hcis.ipanther.common.user.service;

import com.hcis.ipanther.common.user.entity.UserHis;
import com.hcis.ipanther.core.service.IBaseService;

public interface IUserHisService extends IBaseService<UserHis> {

	/**
	 * 备份用户 将User转换为UserHis存入历史 只有是学员时才需要备份
	 * 注意：
	 * 此方法执行后，一般会调用 IUserStaffHisService.bakUserStaff 备份扩展信息
	 * 此方法必须在User的create或者update后调用，如用户有所属机构的变更，则需要在机构更新后执行
	 * @param user
	 * @param creator
	 * @return hisId
	 */
	String bakUser(String userId);
	
}
