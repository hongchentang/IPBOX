package com.hcis.ipanther.common.user.service;

import java.util.List;

import com.hcis.ipanther.common.user.entity.UserReward;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface IUserRewardService extends IBaseService<UserReward> {

	public List<UserReward> list(SearchParam searchParam);

	public int save(UserReward userReward);

	
}
