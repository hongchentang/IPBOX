/**
 * 
 */
package com.hcis.ipanther.common.login.service.impl;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.impl.UserServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcis.ipanther.common.login.service.ILoginService;
import com.hcis.ipanther.common.login.utils.LoginConstants;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.privilege.shiro.IShiroLoginService;
import com.hcis.ipanther.common.user.service.IUserRegionsService;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class ShiroLoginServiceImpl implements IShiroLoginService {
	
	private static final Logger logger=LoggerFactory.getLogger(ShiroLoginServiceImpl.class);
	
	@Resource
	private ILoginService loginService;
	@Resource
	private IUserRegionsService userRegionsService;
	@Resource
	private IDepartmentService departmentService;
	@Resource
	private IUserService userService;

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.common.privilege.shiro.IShiroLoginService#onLoginSuccess(org.apache.shiro.authc.UsernamePasswordToken, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public boolean onLoginSuccess(UsernamePasswordToken token,
			ServletRequest request, ServletResponse response) {
		LoginUser loginUserSession=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		loginService.setRoleList(loginUserSession);
		//读取权限
//		loginService.setPrivilegeList(loginUser);
		//根据用户角色生成菜单
		loginService.setMenu(loginUserSession);
		//数据授权
		//loginUserSession.setUserRegionsList(userRegionsService.listByUserId(loginUserSession.getId()));
		List<String> deptIds = userService.getUserDeptIds(loginUserSession.getId());
		if(CollectionUtils.isNotEmpty(deptIds)){
			loginUserSession.setDeptIds(deptIds);
			loginUserSession.setFirstDeptId(deptIds.get(0));
		}
		//查询组织最高级别的id
		Department department = departmentService.read(loginUserSession.getFirstDeptId());
		loginUserSession.setCompanyId(department.getUnitId());

		((HttpServletRequest)request).getSession().setAttribute(LoginConstants.LOGIN_USER, loginUserSession);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.common.privilege.shiro.IShiroLoginService#onLoginFailure(org.apache.shiro.authc.UsernamePasswordToken, org.apache.shiro.authc.AuthenticationException, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public boolean onLoginFailure(UsernamePasswordToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		logger.error(e.getMessage(),e);
		request.setAttribute("errorMsg", e.getMessage());
		return true;
	}

}
