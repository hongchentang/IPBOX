package com.hcis.ipr.index;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.notice.entity.Notice;
import com.hcis.ipanther.common.notice.service.INoticeService;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.common.user.utils.UserUtils;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

@Controller("commonIndexController")
@RequestMapping("")
public class IndexController extends BaseController{

	@Autowired
	private INoticeService noticeService;
	
	@Autowired
	private ICmsSiteService cmsSiteService;
	
	@RequestMapping("index")
	public ModelAndView index(@ModelAttribute("siteId")String siteId,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		
		//根据cookie判断是否为报名登录，若是，登录成功后跳转报名页面
		Cookie[] cookies=request.getCookies();
		for(Cookie c:cookies){
			if(c.getName().equals("paramType")){
				String globalRegions = (String) request.getSession().getAttribute("globalRegions");
				globalRegions=StringUtils.isBlank(globalRegions)?"":globalRegions;
				String url="/site"+globalRegions+"/train/detail.do?id="+c.getValue();
				Cookie cookie=new Cookie("paramType",null);
				cookie.setMaxAge(0);
				try {
					response.addCookie(cookie);
					response.sendRedirect(url);
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
				return null;
			}
		}
		/*
		 * 如果登录用户是学员或者教师，或者是普通用户
		 * 跳转到个人端
		 */
		LoginUser loginUser=LoginUser.loginUser(request);
		String roleCode = loginUser.getRoleCode();
		if(UserUtils.isSpace(roleCode)) {
			try {
				HttpSession session = request.getSession();
				/*
				 * 设置常用的参数
				 * 关于roleCode和type的区别，请参考 com.hcis.ipanther.common.user.entity.User
				 */
				//判断是否是教师或者学生的角色
				session.setAttribute("isStudent", UserUtils.isStudent(roleCode));
				session.setAttribute("isTeacher", UserUtils.isTeacher(roleCode));
				//判断是否是教师或者学生的身份
				String type = loginUser.getType();
				session.setAttribute("isStudentType", UserUtils.isStudent(type));
				session.setAttribute("isTeacherType", UserUtils.isTeacher(type));
				
				response.sendRedirect("/space/index.do");
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}
		
		CmsSite manageSite=null;
		if (StringUtils.isNotEmpty(siteId)) {
			//指定管理站点
			manageSite=cmsSiteService.read(siteId);
		}else {
			if (getSession().getAttribute("manageSite")!=null) {
				manageSite=(CmsSite)getSession().getAttribute("manageSite");
			}else {
				if(StringUtils.equals(roleCode,UserConstants.USER_ROLECODE_SUPER_ADMIN)){
					//提取主站点
					manageSite=cmsSiteService.selectMainSite();
				}else{
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("regionsCode", loginUser.getRegionsCode());
					map.put("deptLevel", loginUser.getDeptLevel());
					manageSite=cmsSiteService.selectFirstSite(map);
				}
				
				//未指定管理站点  
				if(manageSite==null){
					//提取一级站点
					manageSite=cmsSiteService.selectFirstSite();
				}
			}
		}
		getSession().setAttribute("manageSite", manageSite);
		mav.setViewName("/index");
		return mav;
	}
	
	/**
	 * 首页内容
	 * @return
	 */
	@RequestMapping("index_content")
	public ModelAndView indexContent(){
		ModelAndView mv = new ModelAndView();
		//取出通知公告
		List<Notice> notices = noticeService.indexNotices(new SearchParam(),10);
		mv.addObject("notices", notices);
		return mv;
	}
	
}
