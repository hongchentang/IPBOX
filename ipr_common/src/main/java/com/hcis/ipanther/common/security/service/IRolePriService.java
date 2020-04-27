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
package com.hcis.ipanther.common.security.service;

import java.util.List;
import java.util.Map;

import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.entity.RolePri;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface IRolePriService extends IBaseService<RolePri> {
	
	public List<Map<String, Object>> rolePriTree(Role role,Module module);

	public int updateRolePri(SearchParam searchParam);

	int delete(RolePri rolePri);

	int batchDelete(List<RolePri> ids, String updatedby);
	
	//递归查询
	public Object selectByPriId(Map map);
}
