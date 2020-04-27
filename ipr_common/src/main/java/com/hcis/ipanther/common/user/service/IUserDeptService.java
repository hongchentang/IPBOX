package com.hcis.ipanther.common.user.service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.UserDept;
import com.hcis.ipanther.core.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IUserDeptService extends IBaseService<UserDept>{

	public int deleteByUserIdLogic(UserDept userDept);
	
	public int deleteByUserIdPhysic(UserDept userDept);
	
	/**
	 * 根据用户ID和deptid更新
	 * @param userDept
	 * @return
	 */
	public int updateByUserDeptId(UserDept userDept,LoginUser loginUser);

	/**
	 * 获取名称集合
	 * @param userIds
	 * @return
	 */
    Map<String, List<UserDept>> getUsersDept(List<String> userIds);

	/**
	 * 获取名称字符串
	 * @param list
	 * @return
	 */
    String getUserDeptNameStr(List<UserDept> list);

}
