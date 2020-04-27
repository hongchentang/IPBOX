package com.hcis.ipanther.common.user.service.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.user.dao.UserStaffHisDao;
import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.user.entity.UserStaffHis;
import com.hcis.ipanther.common.user.service.IUserStaffHisService;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;

@Service
public class UserStaffHisServiceImpl extends BaseServiceImpl<UserStaffHis> implements IUserStaffHisService {
	
	private final static Log log=LogFactory.getLog(UserStaffHisServiceImpl.class);
	
	@Autowired
	private UserStaffHisDao userStaffHisDao;
	@Autowired
	private IUserStaffService userStaffService;
	
	@Override
	public MyBatisDao getBaseDao() {
		return userStaffHisDao;
	}

	@Override
	public int bakUserStaff(String userId, String hisId) {
		try {
			if(StringUtils.isNotEmpty(hisId)) {
				UserStaffHis userStaffHis = new UserStaffHis();
				UserStaff userStaff = userStaffService.read(userId);
				if(null!=userStaff) {
					PropertyUtils.copyProperties(userStaffHis, userStaff);
					userStaffHis.setHisId(hisId);
					userStaffHisDao.insertSelective(userStaffHis);
				}
			}
		} catch (Exception e) {
			log.error(this.getClass(), e);
		}
		return 0;
	}
	
}
