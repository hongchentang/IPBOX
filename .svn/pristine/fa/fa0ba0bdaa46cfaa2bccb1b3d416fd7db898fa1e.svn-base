package com.hcis.ipanther.common.security.realm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springside.modules.utils.Collections3;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Privilege;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IPrivilegeService;


@Component("frameperms")
public class FramePermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

	public Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private CacheManager shiroCacheManager;
	@Resource
	private IPrivilegeService privilegeService;

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		boolean permitted = false;
		Subject userSubject = SecurityUtils.getSubject();
		LoginUser user = (LoginUser) userSubject.getPrincipal();

		/*
		 * Session session = user.getSession(false); Cache<Object, Object> cache
		 * = shiroCacheManager.getCache(GlobalStatic.authenticationCacheName);
		 * String cachedSessionId =
		 * cache.get(GlobalStatic.authenticationCacheName
		 * +"-"+shiroUser.getAccount()).toString(); String sessionId=(String)
		 * session.getId(); if(!sessionId.equals(cachedSessionId)){
		 * user.logout(); }
		 */
		/*
		 * 1.获取当前链接对应的resource
		 * 2.找到resource对应的role
		 * 3.检查user的role中，是否和此resource的role有交集
		 * 4.包括-返回true，不包括-返回false
		 */

		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		String uri = req.getRequestURI();
//		logger.info("uri:"+uri);
		String requestURL = req.getRequestURL().toString();
//		logger.info("url:"+requestURL);
		List<String> roleIdList=new ArrayList<String>();
		//取出所有resource中的链接包含在requestUrl中的
		List<Privilege> privilegeList=privilegeService.listAllPrivilegeRole();
		for(Privilege privilege:privilegeList){
			String accessUrl=privilege.getUrl();
			if(StringUtils.startsWith(accessUrl,uri)){
//				System.out.println(uri+"       "+accessUrl);
				List<Role> priRoleList=privilege.getRoleList(); 
				for(Role role:priRoleList){
					roleIdList.add(role.getId());
				}
			}
		}
//		List<String> userRoleNameList=userManager.getUserRoleNameList(user.getId());
		if(CollectionUtils.isNotEmpty(roleIdList)){
			List<Role> userRoleList=user.getRoleList();
			List<String> userIdList=Collections3.extractToList(userRoleList, "id");
			List<String> intersection=ListUtils.intersection(userIdList,roleIdList);
			if(CollectionUtils.isNotEmpty(intersection)){
				permitted=true;
			}
		}
		else{
			permitted=true;
		}
		
		
		/*String contextPath = req.getContextPath();
		if (uri.endsWith("/pre")) {// 去掉pre
			uri = uri.substring(0, uri.length() - 4);
		}
		int i = uri.indexOf(contextPath);
		if (i > -1) {
			uri = uri.substring(i + contextPath.length());
		}
		if (StringUtils.isBlank(uri)) {
			uri = "/";
		}
		if ("/".equals(uri)) {
			permitted = true;
		} else {
			permitted = subject.isPermitted(uri);
		}*/
		return permitted;

	}

	public CacheManager getShiroCacheManager() {
		return shiroCacheManager;
	}

	public void setShiroCacheManager(CacheManager shiroCacheManager) {
		this.shiroCacheManager = shiroCacheManager;
	}
}
