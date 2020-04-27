package com.hcis.ipanther.common.user.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.haoyu.ipanther.common.login.service.IUserLoginAttemptService;
import com.hcis.ipanther.common.customers.entity.CustomersEmail;
import com.hcis.ipanther.common.customers.service.CustomersService;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.user.dto.RegisterDto;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.user.service.IUserDeptService;
import com.hcis.ipanther.common.user.service.IUserHisService;
import com.hcis.ipanther.common.user.service.IUserParttimeService;
import com.hcis.ipanther.common.user.service.IUserRegisterFlowService;
import com.hcis.ipanther.common.user.service.IUserRegisterService;
import com.hcis.ipanther.common.user.service.IUserResearchService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.IUserStaffHisService;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.core.web.controller.BaseController;

/**
 * 用户相关请求包括： /common/user 用户 /teacher 教师 /student 学员 人才 /*Admin* 管理员
 *
 * @author wuwentao
 * @date 2015年3月27日
 */
@Controller("common_UserSiteController")
@RequestMapping("/site")
public class UserSiteController extends BaseController {

	private final static Log log = LogFactory.getLog(UserSiteController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserStaffService userStaffService;
	@Autowired
	private IUserDeptService userDeptService;
	@Resource
	private IRoleService roleService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IUserLoginAttemptService userLoginAttemptService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IUserRegisterFlowService userRegisterFlowService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IUserRegisterService userRegisterService;
	@Autowired
	private IUserHisService userHisService;
	@Autowired
	private IUserStaffHisService userStaffHisService;
	@Autowired
	private IUserParttimeService userParttimeService;
	@Autowired
	private IUserResearchService userResearchService;
	@Autowired
	private CustomersService customersService;

	/**
	 * 跳转到注册页面
	 *
	 * @return
	 */
	@RequestMapping("/register/goRegister")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("/site/registerV2");
		return mv;
	}

	@RequestMapping("/register/getCheckCode")
	@ResponseBody
	public Map getCheckCode(@ModelAttribute("email") String email, @ModelAttribute("userName") String userName) {
		Map<String, Object> map = new HashMap<>(2);
		try {
			String checkCode = getCode();
			//检查用户名
			userService.checkUserName(userName);
			// 发送邮件
			userService.sendCheckCodeMail(checkCode, email);
			map.put("code", 200);
			map.put("checkCode", checkCode);
		} catch (Exception e) {
			map.put("code", 500);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	@RequestMapping("/register")
	public ModelAndView old() {
		ModelAndView mv = new ModelAndView("/site/register");
		return mv;
	}

	@RequestMapping("/test")
	public ModelAndView test() {
		String s = "aa";
		return new ModelAndView("/site/registerV2");
	}

	/**
	 * 执行用户注册
	 *
	 * @throws IOException
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public Response regist(@FormModel("user") User user, @FormModel("userStaff") UserStaff userStaff, String checkCode,
			String checkType, String code) throws IOException {

		// 由于email字段暂时不能自动封装进User实体，先单独来取
		String email = request.getParameter("user.email");
		user.setEmail(email);
		Response response = userService.regist(request, user, userStaff, checkCode, checkType, code);
		return response;
	}

	@RequestMapping("/unitRegist")
	@ResponseBody
	public Response test(@FormModel("user") User user, @FormModel("userStaff") UserStaff userStaff,
			@FormModel("department") Department department, String orgCheckCode, String checkType, String code) {
		try {
			return userService.unitRegist(request, user, userStaff, department, orgCheckCode, checkType, code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行机构用户注册
	 *
	 * @throws IOException
	 */
	@RequestMapping("/unitRegister")
	@ResponseBody
	public String unitRegister(RegisterDto register, DefaultMultipartHttpServletRequest request) {

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile logo = multipartRequest.getFile("logo");
			userService.doRegister(register, logo);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
		CustomersEmail customers = new CustomersEmail();
		customersService.addCustomers(register, customers);
		return "OK";
	}

	/**
	 * 找回密码
	 *
	 * @param searchParam
	 * @param regions
	 * @return
	 */
	@RequestMapping("/findPassword")
	public ModelAndView findPassword() {
		ModelAndView mv = new ModelAndView();
		// mv.addObject("regions", regions);
		return mv;
	}

	@RequestMapping("/register/goFindPassword")
	public ModelAndView goFindPassword() {
		return new ModelAndView("/site/findPasswordV2");
	}

	/**
	 * 找回密码，发送邮件
	 *
	 * @throws IOException
	 */
	@RequestMapping("/getPassword")
	@ResponseBody
	public Response getPassword(String email, String checkCode) throws IOException {
		User user = new User();
		user.setEmail(email);
		Response response = userService.getPassword(request, user, checkCode);
		return response;
	}

	/**
	 * CMS首页展示培训师资系统信息
	 *
	 * @param searchParam
	 * @param
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/cms/teacher/showIndex")
	// public ModelAndView listTeacherForCMSIndex(SearchParam searchParam)
	// throws Exception {
	// ModelAndView mv = new ModelAndView("/common/user/teacher/cms/showIndex");
	// List<User> teachers = userService.selectTeacherForCMS(searchParam);
	// mv.addObject("teachers", teachers);
	// return mv;
	// }

	private String getCode() {
		String str = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random r = new Random();
		String arr[] = new String[4];
		String checkCode = "";
		for (int i = 0; i < 4; i++) {
			int n = r.nextInt(62);

			arr[i] = str.substring(n, n + 1);
			checkCode += arr[i];

		}
		return checkCode;
	}
}
