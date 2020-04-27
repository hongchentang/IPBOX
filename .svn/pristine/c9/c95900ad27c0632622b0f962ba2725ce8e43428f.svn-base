package com.hcis.ipanther.common.log.web;

import java.util.List;

import com.hcis.ipanther.common.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.haoyu.ipanther.common.login.entity.UserLoginLog;
import com.haoyu.ipanther.common.login.service.IUserLoginLogService;
import com.hcis.ipanther.common.log.entity.CommonLog;
import com.hcis.ipanther.common.log.service.ICommonLogService;

@RequestMapping("/common/log")
@Controller
public class CommonLogController extends BaseController {

	@Autowired
	private ICommonLogService commonLogService;
	@Autowired
	private IUserLoginLogService userLoginLogService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("listLog")
	public ModelAndView listLog(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();

		//权限数据
		searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
		searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());

		List<CommonLog> list= commonLogService.list(searchParam);
		modelAndView.addObject("resultList", list);

		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("loginLog")
	public ModelAndView loginLog(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();

		//权限数据
		searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
		searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());

		List<UserLoginLog> list= userLoginLogService.list(searchParam);
		modelAndView.addObject("resultList", list);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
}
