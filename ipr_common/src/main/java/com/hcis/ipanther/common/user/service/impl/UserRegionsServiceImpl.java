/**
 * 
 */
package com.hcis.ipanther.common.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.dao.UserRegionsDao;
import com.hcis.ipanther.common.user.entity.UserRegions;
import com.hcis.ipanther.common.user.service.IUserRegionsService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chao
 *
 */
@Service("userRegionsService")
public class UserRegionsServiceImpl extends BaseServiceImpl<UserRegions> implements IUserRegionsService {

	@Resource(name="userRegionsDao")
	private UserRegionsDao baseDao;
	
	@Override
	public int batchSaveByRegionsCheck(String userId, List<Map<String, String>> regionsCheckList, LoginUser loginUser) {
		int count=0;
		//删除此用户所有授权
		this.batchDeleteByUserId(userId, loginUser);
		//按新列表增加授权
		if(CollectionUtils.isNotEmpty(regionsCheckList)){
			for(Map<String,String> map:regionsCheckList){
				UserRegions userRegions=new UserRegions();
				userRegions.setUserId(userId);
				userRegions.setRegionsCode(map.get("regionsCode"));
				userRegions.setHasChild(map.get("hasChild"));
				count=count+this.create(userRegions, loginUser.getId());
			}
		} 
		return count;
	}

	@Override
	public int batchDeleteByUserId(String userId, LoginUser loginUser) {
		if(StringUtils.isNotBlank(userId)){
			UserRegions userRegions=new UserRegions();
			userRegions.setUserId(userId);
			if (loginUser != null) {
				userRegions.setUpdatedby(loginUser.getId());
			}
			userRegions.setUpdateTime(new Date());
			baseDao.deleteByUserId(userRegions);
		}
		return 0;
	}

	@Override
	public List<UserRegions> listByUserId(String userId) {
		if(StringUtils.isNotBlank(userId)){
			SearchParam searchParam=new SearchParam();
			searchParam.getParamMap().put("userId",userId);
			searchParam.setPageAvailable(false);
			return this.list(searchParam);
		}
		return null;
	}

	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}

}
