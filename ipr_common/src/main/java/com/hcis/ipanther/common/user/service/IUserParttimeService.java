package com.hcis.ipanther.common.user.service;

import java.util.List;

import com.hcis.ipanther.common.user.entity.UserParttime;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface IUserParttimeService extends IBaseService<UserParttime> {

	public List<UserParttime> list(SearchParam searchParam);

	public int save(UserParttime userParttime);

	
}
