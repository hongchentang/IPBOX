package com.hcis.ipanther.core.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.log4j.Logger;
import org.springside.modules.utils.Collections3;

import com.hcis.ipanther.common.login.utils.LoginConstants;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Privilege;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IPrivilegeService;
import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.StringUtils;

public class AuthFilter implements Filter {

	private static final Logger log = Logger.getLogger(AuthFilter.class);

	public void destroy() {
		log.info("authfilter destroy");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// System.out.println("authfilter dofilter");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String url = request.getRequestURI().toString();
		if (session == null || session.getAttribute(LoginConstants.LOGIN_USER) == null) {
			if ((!(url.indexOf("login") > 0))) {
				log.info("the url is :" + url);
				response.sendRedirect(request.getContextPath() + "/ialumni/main.do");
			}
			else if (url.indexOf("attachment") > 0) {
				log.info("the url is :" + url);
				response.sendRedirect(request.getContextPath() + "/ialumni/main.do");
			}
		}
		else{
			boolean flg=this.checkUrl(req);
			if(!flg){
				response.sendRedirect(request.getContextPath() + "/jsp/common/403.html");
			}
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		log.info("authfilter init");
	}

	/**
	 * url过滤方法
	 * @param request
	 * @return
	 */
	private boolean checkUrl(ServletRequest request){
		IPrivilegeService privilegeService=(IPrivilegeService) BeanLocator.getBean("privilegeService");
		IUserRoleService userRoleService=(IUserRoleService)BeanLocator.getBean("userRoleService");
		boolean permitted =false;
		HttpServletRequest req = (HttpServletRequest) request;
		//获取当前用户
		LoginUser loginUser=(LoginUser) req.getSession().getAttribute(LoginConstants.LOGIN_USER);
		if(loginUser!=null){
			User user=new User();
			user.setId(loginUser.getId());
			
			/*
			 * 1.获取当前链接对应的resource
			 * 2.找到resource对应的role
			 * 3.检查user的role中，是否和此resource的role有交集
			 * 4.包括-返回true，不包括-返回false
			 */
			
			String uri = req.getRequestURI();
			List<String> roleIdList=new ArrayList<String>();
			List<Privilege> privilegeList=privilegeService.listAllPrivilegeRole();
			for(Privilege privilege:privilegeList){
				String accessUrl=privilege.getUrl();
				if(StringUtils.startsWith(uri,accessUrl)){
					List<Role> priRoleList=privilege.getRoleList(); 
					for(Role role:priRoleList){
						roleIdList.add(role.getId());
					}
				}
			}
	//		List<String> userRoleNameList=userManager.getUserRoleNameList(user.getId());
			if(CollectionUtils.isNotEmpty(roleIdList)){
				List<Role> userRoleList= userRoleService.selectRoleByUser(user);
				List<String> userIdList=Collections3.extractToList(userRoleList, "id");
				List<String> intersection=ListUtils.intersection(userIdList,roleIdList);
				if(CollectionUtils.isNotEmpty(intersection)){
					permitted=true;
				}
			}
			else{
				permitted=true;
			}
		}
		return permitted;
		
	}
	
	
}