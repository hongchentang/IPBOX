package com.hcis.ipanther.common.security.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hcis.ipanther.common.security.dao.RoleDao;
import com.hcis.ipanther.core.utils.BeanLocator;

/**
 * 角色相关通用方法 
 * @date 2015年3月20日
 */
@Component("securityRoleUtils")
public class SecurityRoleUtils {
	
	/**
	 * 判断用户是否有指定的角色
	 * @param roleCode
	 * @param userId
	 * @return
	 */
	public static boolean hasRole(String roleCode,String userId) {
		RoleDao roleDao= (RoleDao) BeanLocator.getBean("roleDao");
		Map<String,Object> args = new HashMap<String,Object>();
		args.put("roleCode", roleCode);
		args.put("userId", userId);
		int count = roleDao.countByUserId(args);
		return count>0?true:false;
	}
	
	/**
	 * 判断用户是否有超级管理员的角色
	 * @param userId
	 * @return
	 */
	public static boolean isSuperAdmin(String userId) {
		return hasRole(RoleConstant.SUPER_ADMIN, userId);
	}
	
	/**
	 * 判断用户是否有区域管理员的角色
	 * @param userId
	 * @return
	 */
	public static boolean isAreaAdmin(String userId) {
		return hasRole(RoleConstant.AREA_ADMIN, userId);
	}
	
	/**
	 * 判断用户是否有单位管理员的角色
	 * @param userId
	 * @return
	 */
	public static boolean isDeptAdmin(String userId) {
		return hasRole(RoleConstant.DEPT_ADMIN, userId);
	}
	
	/**
	 * 判断用户是否有角色的角色
	 * @param userId
	 * @return
	 */
	public static boolean isTeacher(String userId) {
		return hasRole(RoleConstant.TEACHER, userId);
	}
	
	/**
	 * 判断用户是否人才的角色
	 * @param userId
	 * @return
	 */
	public static boolean isStudent(String userId) {
		return hasRole(RoleConstant.STUDENT, userId);
	}
	
	/**
	 * 判断用户是否运维管理员或者超级管理员的角色
	 * @param userId
	 * @return
	 */
	public static boolean isSystemAdmin(String userId) {
		return hasRole(RoleConstant.SUPER_ADMIN, userId)||hasRole(RoleConstant.ADMIN, userId);
	}
	
	/**
	 * 判断用户是否是培训机构的管理员（包括业务员和领导）
	 * @param userId
	 * @return
	 */
	public static boolean isTrainAdmin(String userId) {
		return hasRole(RoleConstant.TRAIN_LEADER, userId)||hasRole(RoleConstant.TRAIN_BUSINESS, userId);
	}
}
