package com.hcis.ipanther.common.user.service;

import java.util.List;

import com.hcis.ipanther.common.user.entity.UserResearch;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface IUserResearchService extends IBaseService<UserResearch> {

	public List<UserResearch> list(SearchParam searchParam);

	public int save(UserResearch userResearch);

	
}
