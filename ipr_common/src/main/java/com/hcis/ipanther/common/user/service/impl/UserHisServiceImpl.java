package com.hcis.ipanther.common.user.service.impl;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.user.dao.UserDao;
import com.hcis.ipanther.common.user.dao.UserHisDao;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserHis;
import com.hcis.ipanther.common.user.service.IUserHisService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;

@Service
public class UserHisServiceImpl extends BaseServiceImpl<UserHis> implements IUserHisService {
	
	private final static Log log=LogFactory.getLog(UserHisServiceImpl.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private UserHisDao userHisDao;
	@Autowired
	private UserDao baseDao;
	
	@Override
	public MyBatisDao getBaseDao() {
		return userHisDao;
	}
	
	@Override
	public String bakUser(String userId) {
		String hisId = "";
		//不能用userServiceImpl.read 以免读到缓存
		User user = baseDao.selectByPrimaryKey(userId);
		String roleCode = user.getRoleCode();
		//学员-存备份数据
		try {
			if(UserConstants.USER_ROLECODE_STUDENT.equals(roleCode)||UserConstants.USER_ROLECODE_STUDENT_TEACHER.equals(roleCode)) {
				hisId = Identities.uuid2();
				UserHis userHis = new UserHis();
				PropertyUtils.copyProperties(userHis, user);
				userHis.setId(hisId);
				userHis.setUserId(userId);
				userHis.setUpdateTime(new Date());
				String updateby = user.getUpdatedby();
				if(StringUtils.isEmpty(updateby)) {
					updateby = user.getCreator();
				}
				userHis.setUpdatedby(updateby);
				userHisDao.insertSelective(userHis);
			}
		} catch (Exception e) {
			hisId = "";
			log.error(this.getClass(), e);
		}
		return hisId;
	}
	
}
