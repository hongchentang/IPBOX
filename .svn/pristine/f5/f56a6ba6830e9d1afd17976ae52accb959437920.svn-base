/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 2013-9-17
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
*************************************************/
package com.hcis.ipanther.common.user.service;

import java.util.List;
import java.util.Map;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.UserRegions;
import com.hcis.ipanther.core.service.IBaseService;

public interface IUserRegionsService extends IBaseService<UserRegions> {

	/**
	 * 批量新增区域授权
	 * @param userRegionsList
	 * @param loginUser
	 * @return
	 */
	public int batchSaveByRegionsCheck(String userId, List<Map<String,String>> regionsCheckList,LoginUser loginUser);
	
	/**
	 * 批量新增区域授权
	 * @param userRegionsList
	 * @param loginUser
	 * @return
	 */
	public int batchDeleteByUserId(String userId,LoginUser loginUser);
	
	/**
	 * 列出用户配置的区域
	 * @param userId
	 * @return
	 */
	public List<UserRegions> listByUserId(String userId);
	
	
}
