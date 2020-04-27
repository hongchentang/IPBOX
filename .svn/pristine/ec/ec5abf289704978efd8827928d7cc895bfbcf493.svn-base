package com.hcis.ipr.intellectual.call.web;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.call.entity.PatentType;
import com.hcis.ipr.intellectual.call.service.ProcedureService;

@RequestMapping("/intellectual/call")
@Controller
public class ProcedureController extends BaseController {
	@Autowired
	private ProcedureService procedureService;
	@Autowired
	private IUserService userService;

	@RequestMapping("/patentType")
	@ResponseBody
	public Object patentType() {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String ID = loginUser.getFirstDeptId();
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("ID", ID);
		List<String> deptIds = userService.getDeptIdsByUserId();
		String searchUserId = userService.getRoleUserId();
		String ids = "";
		if (deptIds.size() > 0) {
			ids = deptIds.get(0);
		}

		List<PatentType> list = procedureService.getPatentType(ID, ids, searchUserId);

		procedureService.getPatentInvent(ID);

		/*
		 * JSONArray json = JSONArray.fromObject(list);
		 */ return list;
	}

	@RequestMapping("/getindexCost")
	@ResponseBody
	public Object getindexCost() {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String ID = loginUser.getFirstDeptId();
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("ID", ID);
		List<String> deptIds = userService.getDeptIdsByUserId();
		String searchUserId = userService.getRoleUserId();
		String ids = "";
		if (deptIds.size() > 0) {
			ids = deptIds.get(0);
		}

		List<String> list = procedureService.getindexCost(ID, ids, searchUserId);
		return list;
	}
}
