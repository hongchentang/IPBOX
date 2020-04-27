package com.hcis.ipanther.common.security.service;

import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.core.service.IBaseService;

public interface IModuleService extends IBaseService<Module>{
	public String getModuleCheck(String entityName,String roleId);
}
