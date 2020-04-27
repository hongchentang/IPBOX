package com.hcis.ipr.train.audit.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.core.utils.DateUtils;
import com.hcis.ipanther.core.utils.ExportUtils;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.register.service.ITrainRegisterService;
import com.hcis.ipr.train.register.utils.RegisterConstants;

@RequestMapping("/train/audit")
@Controller
public class AuditController extends BaseController{

	@Autowired
	private ITrainRegisterService trainRegisterService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserStaffService userStaffService;
	@Resource(name="mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer;
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	//审核tab页
	@RequestMapping("tabList")
	public ModelAndView tabList(SearchParam searchParam){
		ModelAndView modelAndView =new ModelAndView();
		return modelAndView;
	}
	
	/**
	 * 审核列表
	 * action为export时为导出用户信息
	 * @param searchParam
	 * @param action
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("listAuditUser")
	public ModelAndView listAuditUser(SearchParam searchParam,String action,HttpServletResponse response) throws Exception{
		ModelAndView modelAndView =new ModelAndView();
		List list = new ArrayList();
		//页面已传学员培训状态
		if("export".equals(action)) {//导出
			searchParam.getPagination().setAvailable(false);
			list=trainRegisterService.list(searchParam);
			List<User> users = new ArrayList<User>();
			for(int i = 0;i<list.size();i++) {
				Map user = (Map) list.get(i);
				String userId = (String) user.get("userId");
				User _user = userService.read(userId);
				users.add(_user);
			}
			userService.setUserInfosForExport(users);
			try {
				String status=searchParam.getParamMap().get("status").toString();
				 String exportName="报名已通过信息导出";
				 if(RegisterConstants.STUDENT_AUDIT_STATUS_02.equals(status)){
					 exportName="报名未通过信息导出";
				 }
				 if(RegisterConstants.STUDENT_AUDIT_STATUS_00.equals(status)){
					 exportName="报名待审核信息导出";
				 }
				Map<String,Object> beans = new HashMap<String,Object>();
				beans.put("yonghu", users);
				beans.put("dateUtils", new DateUtils());
				ExportUtils.exportExcel(response, "/excel/studentExport.xls", beans, exportName);
			} catch (Exception e) {
				logger.error("导出出错",e);
			}
			return null;
		} else {
			list=trainRegisterService.list(searchParam);
		}
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	
	//审核 
	@RequestMapping("saveAuditUser")
	@ResponseBody
	public AjaxReturnObject saveAuditUser(SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		int count=trainRegisterService.auditUser(searchParam, request);
		if (count > 0) {
			String auditStatus=(String)searchParam.getParamMap().get("auditStatus");
			String toUserIds=(String)searchParam.getParamMap().get("toUserId");
			//邮箱发送消息的
			AjaxReturnObject ajaxReturnObject=trainRegisterService.sendEmailTrain(auditStatus, toUserIds);
			statusCode=ajaxReturnObject.getStatusCode();
			msg=ajaxReturnObject.getMessage();
		}
		if(count==0){
			statusCode=300;
			msg="操作失败！！";
		}
		return new AjaxReturnObject(statusCode,msg);
	}
	
	//审核意见窗口
	@RequestMapping("goAudit")
	public ModelAndView goAudit(SearchParam searchParam){
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("/train/audit/auditView");
		modelAndView.addObject("count", searchParam.getParamMap().get("count").toString());
		return modelAndView;
	}
	
//	//批量审核意见窗口
//	@RequestMapping("goBatchAudit")
//	public ModelAndView goBatchAudit(SearchParam searchParam){
//		ModelAndView modelAndView =new ModelAndView();
//		modelAndView.setViewName("/train/audit/batchAuditView");
//		modelAndView.addObject("count", searchParam.getParamMap().get("count").toString());
//		return modelAndView;
//	}
}
