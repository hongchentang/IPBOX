package com.hcis.ipanther.common.user.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcis.ipanther.common.user.service.*;
import com.hcis.ipanther.common.utils.BaseApi;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.utils.Collections3;

import com.haoyu.ipanther.common.login.service.IUserLoginAttemptService;
import com.hcis.ipanther.common.attachment.utils.AttachmentUtils;
import com.hcis.ipanther.common.customers.entity.CustomersEmail;
import com.hcis.ipanther.common.customers.service.CustomersService;
import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.log.utils.CommonLogUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.user.dto.RegisterDto;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserDept;
import com.hcis.ipanther.common.user.entity.UserHis;
import com.hcis.ipanther.common.user.entity.UserRegister;
import com.hcis.ipanther.common.user.entity.UserRegisterFlow;
import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.user.entity.UserStaffHis;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.common.useremail.entity.UserEmail;
import com.hcis.ipanther.common.useremail.service.UserEmailService;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.DateUtils;
import com.hcis.ipanther.core.utils.ExportUtils;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.core.web.controller.BaseController;

/**
 * 用户相关请求包括： /common/user 用户 /teacher 教师 /student 学员 人才 /*Admin* 管理员
 *
 * @author wuwentao
 * @date 2015年3月27日
 */
@Controller("common_userManagerController")
@RequestMapping({ "/common/user", "/" })
public class UserManagerController extends BaseController {

	private final static Log log = LogFactory.getLog(UserManagerController.class);

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
	private CustomersService customersService;
	@Autowired
	private UserEmailService useremailService;
	@Autowired
	private WechatUserService wechatUserService;

	/**
	 * 根据当前企业deptId 查询当前企业deptId下所有的员工
	 *
	 * @param searchParam
	 * @param response
	 * @return
	 * @throwsException
	 */
	@RequestMapping("/employees/list")
	public ModelAndView listEmployees(SearchParam searchParam, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.newModelAndView();

		/**
		 * 获取所有的企业ID
		 */
		if (searchParam.getParamMap().get("deptId") != "" && searchParam.getParamMap().get("deptId") != null) {
			String deptId = searchParam.getParamMap().get("deptId").toString();
			String[] deptIds = departmentService.getDeptIds(deptId);
			searchParam.getParamMap().put("deptIds", deptIds);
		}
		Map<String, Object> paramMap = searchParam.getParamMap();
		String action = (String) paramMap.get("action");
		if ("export".equals(action)) {
			// 导出
			searchParam.getPagination().setAvailable(false);
			String idsStr = (String) searchParam.getParamMap().get("ids");
			if(StringUtils.isNotEmpty(idsStr)){
                String[] strings = idsStr.split(",");
                List<String> list = Arrays.asList(strings);
                searchParam.getParamMap().put("ids", list);
            }
			List<User> users = userService.listEmployees(searchParam);
			userService.setUserInfosForExport(users);
			try {
				Map<String, Object> beans = new HashMap<String, Object>();
				beans.put("yonghu", users);
				beans.put("dateUtils", new DateUtils());
				ExportUtils.exportExcel(response, "/excel/employeesExportTemplate.xls", beans, "员工信息导出");
			} catch (Exception e) {
				log.error("导出出错", e);
			}
			return null;
		} else {
			// 正常查询
			mv.addObject("users", userService.listEmployees(searchParam));
		}
		return mv;
	}

	/**
	 * 展示用户的修改历史
	 *
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/employees/listHis")
	public ModelAndView listHis(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		String userId = (String) searchParam.getParamMap().get("userId");
		if (StringUtils.isNotEmpty(userId)) {
			List<UserHis> users = userHisService.list(searchParam);
			mv.addObject("users", users);
		}
		return mv;
	}

	/**
	 * 跳转到教师管理tab页面
	 *
	 * @return
	 */
	@RequestMapping("/teacher/listTabs")
	public ModelAndView listTeacherTabs(SearchParam searchParam) {
		userService.auditTeacherList(searchParam, "Todo");
		return new ModelAndView();
	}

	/**
	 * 教师列表-全部
	 *
	 * @param searchParam
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teacher/list")
	public ModelAndView listTeacher(SearchParam searchParam, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> paramMap = searchParam.getParamMap();
		String action = (String) paramMap.get("action");
		if ("export".equals(action)) {// 导出
			searchParam.getPagination().setAvailable(false);
			List<User> users = userService.listTeacher(searchParam);
			userService.setUserInfosForExport(users);
			try {
				Map<String, Object> beans = new HashMap<String, Object>();
				beans.put("yonghu", users);
				beans.put("dateUtils", new DateUtils());
				ExportUtils.exportExcel(response, "/excel/teacherExport.xls", beans, "教师信息导出");
			} catch (Exception e) {
				log.error("导出出错", e);
			}
			return null;
		} else {// 正常查询
			mv.addObject("users", userService.listTeacher(searchParam));
		}
		mv.addObject("request", "list");
		return mv;
	}

	/**
	 * 教师选择用户成为教师选择列表
	 *
	 * @param searchParam
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teacher/chooseUser")
	public ModelAndView chooseUser(SearchParam searchParam, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.newModelAndView();
		mv.addObject("users", userService.chooseUserList(searchParam));
		return mv;
	}

	/**
	 * 选择用户成为教师确认操作
	 *
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping("/teacher/chooseUserDo")
	@ResponseBody
	public String chooseUserDo(User user, HttpServletResponse response) {
		int count = userService.chooseUserDo(user);
		return this.render(Response.defaultInstance(count).toString(), "text/plain;charset=UTF-8", response);
	}

	/**
	 * 选择教师，必须拥有教师的身份
	 *
	 * @param searchParam
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teacher/chooseTeacher")
	public ModelAndView chooseTeacher(SearchParam searchParam, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.newModelAndView();
		mv.addObject("users", userService.chooseTeacherList(searchParam));
		return mv;
	}

	/**
	 * 教师列表-流程相关的列表 auditListTodo auditListDone
	 *
	 * @param searchParam
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teacher/auditList{type}")
	public ModelAndView auditTeacherList(SearchParam searchParam, @PathVariable String type) throws Exception {
		ModelAndView mv = new ModelAndView("/common/user/teacher/auditList");
		mv.addObject("type", type);
		if ("Edit".equals(type)) {
			mv.setViewName("/common/user/teacher/list");
		}
		mv.addObject("users", userService.auditTeacherList(searchParam, type));
		mv.addObject("request", "auditList" + type);
		return mv;
	}

	/**
	 * 注册成为教师流程启动
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping("/teacher/registTeacher")
	@ResponseBody
	public Response registTeacher(User user) {
		userService.registTeacherForDept(user.getId());
		return Response.newDefaultResponse();
	}

	/**
	 * 查出机构下的管理员
	 *
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAdmin")
	public ModelAndView listAdmin(SearchParam searchParam) throws Exception {
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("/common/user/listAdmin");
		mv.addObject("users", userService.listAdmin(searchParam));
		return mv;
	}

	/**
	 * 跳转到学员 编辑、查看的tab页
	 *
	 * @param searchParam
	 * @param user
	 * @param action
	 *            查看还是编辑 view/edit
	 * @param userType
	 *            教师还是学生 teacher/student
	 * @param procInstId
	 *            流程实例的ID
	 * @param registerId
	 *            流程业务ID requestURL:/view/teacherTabs /edit/teacherTabs
	 *            /view/studentTabs /edit/studentTabs
	 * @return
	 */
	@RequestMapping("/{userType}/{action}Tabs")
	public ModelAndView tabs(SearchParam searchParam, User user, String procInstId, String registerId,
							 @PathVariable String action, @PathVariable String userType) {
		ModelAndView mv = new ModelAndView("/common/user/" + userType + "/tabs");
		mv.addObject("user", user);
		mv.addObject("action", action);
		/*
		 * 如果是教师查看，且没有传过来相关的流程信息 则取最新的注册流程
		 */
		if ("teacher".equals(userType) && StringUtils.isEmpty(procInstId)) {
			UserRegister userRegister = userRegisterService.getLatestByUserId(user.getId());
			if (null != userRegister) {
				procInstId = userRegister.getProcInstId();
				registerId = userRegister.getId();
			}
		}
		mv.addObject("procInstId", procInstId);
		mv.addObject("registerId", registerId);
		return mv;
	}

	/**
	 * 跳转到新增/查看 学员/教师 页面 hisId不为空时表示查看历史数据，从历史库中读取
	 *
	 * @param user
	 * @return
	 */
	/*
	 * @RequestMapping( {"/student/edit","/student/view","/student/print",
	 * "/teacher/edit","/teacher/view","/teacher/print",} )
	 */
	@RequestMapping({ "/employees/edit", "/employees/view", "/teacher/edit", "/teacher/view", "/expert/edit" })
	public ModelAndView load(User user, String hisId) {
		ModelAndView mv = this.newModelAndView();
		String userId = user.getId();
		if (StringUtils.isNotEmpty(userId)) {// 加载用户当前最新的信息
			user = userService.read(userId);
			convertUserRoleCode(user);
			if (RoleConstant.DEPT_ADMIN.equals(user.getRoleCode())) {
				user.setDeptIds(userService.getDeptAdminDeptIdsStr(userId));
			}
			mv.addObject("user", user);
			UserStaff userStaff = userStaffService.read(userId);
			mv.addObject("userStaff", userStaff);
		} else if (StringUtils.isNotEmpty(hisId)) {
			UserHis userHis = userHisService.read(hisId);
			mv.addObject("user", userHis);
			UserStaffHis userStaffHis = userStaffHisService.read(hisId);
			mv.addObject("userStaff", userStaffHis);
		}
		return mv;
	}

	/**
	 * 跳转到打印 学员/教师 页面
	 *
	 * @param searchParam
	 * @return
	 */
	@RequestMapping({ "/employees/print", "/teacher/print" })
	public ModelAndView userPrint(SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
		List list = userStaffService.userStaffInfoList(searchParam);
		mv.addObject("list", list);
		return mv;
	}

	/**
	 * 保存人才、教师基础信息
	 *
	 * @param user
	 * @param upload
	 * @param userType
	 *            student teacher
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{userType}/save")
	@ResponseBody
	public Response save(User user, @RequestParam(value = "upload", required = false) MultipartFile upload,
						 @PathVariable String userType) throws Exception {
		/*
		 * 上传个人头像
		 */
		String info = AttachmentUtils.updateAvatar(user, upload, "avatar");
		if (StringUtils.isNotEmpty(info)) {
			return new Response(300, info);
		}

		String userId = userService.saveV2(userType, user);

		return new Response(200, userId);
	}

	/**
	 * 跳转到导入页面
	 *
	 * @param searchParam
	 * @param userType
	 * @return
	 */
	@RequestMapping("/{userType}/goImport")
	public ModelAndView goImport(SearchParam searchParam, @PathVariable String userType) {
		ModelAndView mv = this.newModelAndView();
		mv.setViewName("/common/user/import");
		mv.addObject("userType", userType);
		return mv;
	}

	/**
	 * 执行上传
	 *
	 * @param file
	 * @param userType
	 *            teacher student
	 * @return
	 */
	@RequestMapping("/{userType}/import")
	public @ResponseBody AjaxReturnObject importUser(MultipartFile file, @PathVariable String userType) {
		String batchId = null;
		try {
			batchId = userService.importUserV2(userType, file, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxReturnObject(StringUtils.isNotEmpty(batchId) ? 200 : 300, batchId);
	}

	/**
	 * 查看导入结果
	 *
	 * @param batchId
	 * @param userType
	 * @return
	 */
	@RequestMapping("/{userType}/importResult")
	public ModelAndView importResult(String batchId, @PathVariable String userType) {
		ModelAndView mav = new ModelAndView("/common/user/importResult");
		Map<String, Object> results = (Map<String, Object>) request.getSession().getAttribute(batchId);
		mav.addObject("results", results);
		return mav;
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
		Response response = userService.regist(request, user, userStaff, checkCode, checkType, code);
		return response;
	}

	/**
	 * 找回密码，发送邮件
	 *
	 * @throws IOException
	 */
	@RequestMapping("/getPassword")
	@ResponseBody
	public Response getPassword(@FormModel("user") User user, String checkCode) throws IOException {
		Response response = userService.getPassword(request, user, checkCode);
		return response;
	}

	/**
	 * 执行确定用户信息操作 验证完毕重定向到用户注册页面
	 *
	 * @param id
	 * @param code
	 * @return
	 */
	@RequestMapping("/activate")
	public ModelAndView activate(String id, String code) {
		String message = userService.activate(id, code);
		String globalRegions = (String) request.getSession().getAttribute("globalRegions");
		ModelAndView mv = new ModelAndView("redirect:/site" + globalRegions + "/register.do");
		mv.addObject("confirmRegisterInfo", message);
		return mv;
	}

	/**
	 * 跳转到注册用户审核tab页面
	 *
	 * @return
	 */
	@RequestMapping("/employees/auditListTabs")
	public ModelAndView auditStudentListTabs() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	/**
	 * 注册列表 auditListTodo 待办 auditListDone 已办
	 *
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/employees/auditList{type}")
	public ModelAndView auditStudentList(SearchParam searchParam, @PathVariable String type) {
		ModelAndView mv = new ModelAndView("/common/user/employees/auditList");
		mv.addObject("users", userService.auditStudentList(searchParam, type));
		mv.addObject("request", "auditList" + type);
		return mv;
	}

	/**
	 * 跳转到审核人才界面
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping("/employees/goAudit")
	public ModelAndView goAuditStudent(User user) {
		ModelAndView mv = new ModelAndView("/common/user/student/audit");
		mv.addObject("action", "view");// 对于人才的信息只是查看
		String userId = user.getId();
		if (StringUtils.isNotEmpty(userId)) {
			user = userService.read(userId);
			mv.addObject("user", user);
			UserRegisterFlow flow = userRegisterFlowService.getLatestByUserId(userId);
			mv.addObject("flow", flow);
		}
		return mv;
	}

	/**
	 * 保存审核人才信息
	 *
	 * @param flow
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/employees/audit")
	@ResponseBody
	public Response auditStudent(UserRegisterFlow flow) throws IOException {
		int count = userService.auditStudent(flow);
		return Response.newDefaultResponse(count);
	}

	/**
	 * 跳转到审核教师界面
	 *
	 * @param user
	 * @param registerId
	 *            对应IPR_USER_REGISTER的ID
	 * @param taskId
	 *            流程任务的ID
	 * @return
	 */
	@RequestMapping("/teacher/goAudit")
	public ModelAndView goAuditTeacher(User user, @RequestParam String registerId, @RequestParam String taskId) {
		ModelAndView mv = new ModelAndView("/common/user/teacher/audit");
		mv.addObject("action", "view");// 对于用户的信息只是查看
		String userId = user.getId();
		if (StringUtils.isNotEmpty(userId)) {
			user = userService.read(userId);
			mv.addObject("user", user);
		}

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		mv.addObject("task", task);
		// 查看教师时，会根据该值是否为空判断是否取查看流程图
		mv.addObject("procDefId", task.getProcessDefinitionId());
		mv.addObject("procInstId", task.getProcessInstanceId());
		UserRegister register = userRegisterService.read(registerId);
		mv.addObject("registerId", registerId);
		mv.addObject("register", register);
		return mv;
	}

	/**
	 * 审核教师
	 *
	 * @param flow
	 * @param registerId
	 * @param taskId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/teacher/audit")
	@ResponseBody
	public Response auditTeacher(UserRegisterFlow flow, String registerId, String taskId) throws IOException {
		userService.auditTeacher(flow, registerId, taskId);
		return Response.newDefaultResponse();
	}

	/**
	 * 系统管理-用户列表 1，超级管理员或者系统管理员可以管理所有的用户 2，其它角色只能查看自己单位的；
	 * 3，如果当前人不是超级管理员，不让查看到超级管理员
	 *
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(@ModelAttribute("searchParam") SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
		mv.addObject("listUser", userService.listUser(searchParam));
		mv.addObject("paramMap", searchParam.getParamMap());

		return mv;
	}

	@RequestMapping("/listEmail")
	public ModelAndView listEmail(@ModelAttribute("searchParam") SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();

		mv.addObject("listUser", customersService.list(searchParam));
		mv.addObject("paramMap", searchParam.getParamMap());
		mv.addObject("contacts", customersService.selectcontacts(searchParam));
		List<CustomersEmail> list = customersService.selectcontacts(searchParam);

		return mv;
	}

	/**
	 * 取出所有的申请流程日志 包括教师申请和人才申请
	 *
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/listLog")
	public ModelAndView listLog(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView("/common/user/listLog");
		mv.addObject("logs", userRegisterFlowService.getFlowsByUserId(searchParam));
		return mv;
	}

	/**
	 * 给机构增加默认的管理员
	 *
	 * @param deptId
	 *            需要生成默认管理员的单位
	 * @return
	 */
	@RequestMapping("/addDefaultAdmin")
	public @ResponseBody AjaxReturnObject addDefaultAdmin(@RequestParam("deptId") String deptId) {
		LoginUser loginUser = LoginUser.loginUser(request);
		int count = userService.addUserAdminDefault(deptId, loginUser);
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}

	/**
	 * 系统管理-用户管理-跳转到编辑用户、管理员界面
	 *
	 * @param user
	 * @param isAdmin
	 *            是否是管理员 1为是其它为否
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(User user, boolean isAdmin) throws IOException {
		ModelAndView mv = this.newModelAndView();
		String userId = user.getId();
		if (StringUtils.isNotEmpty(userId)) {
			user = userService.read(user.getId());
		}
		if (isAdmin) {

			String roleIds = "";// 用户已经持有的角色ID，多个用逗号隔开
			if (StringUtils.isNotEmpty(userId)) {
				user = userService.read(user.getId());
				if (isAdmin) {
					List<Role> haveRoles = userRoleService.selectRoleByUser(user);
					for (int i = 0; i < haveRoles.size(); i++) {
						Role role = haveRoles.get(i);
						String roleCode = role.getRoleCode();
						if (!roleCode.equals(RoleConstant.AREA_ADMIN)
								&& !roleCode.equals(RoleConstant.AREA_COMPETENT_ADMIN)
								&& !roleCode.equals(RoleConstant.DEPT_ADMIN)
								&& !roleCode.equals(RoleConstant.DEPT_LEADER)
								&& !roleCode.equals(RoleConstant.TRAIN_BUSINESS)
								&& !roleCode.equals(RoleConstant.TRAIN_LEADER)) {
							haveRoles.remove(i);
						}
					}
					roleIds = Collections3.extractToString(haveRoles, "id", ",");
				}
			}

			/*
			 * 加载可授权的角色：区域主管单位管理员、单位管理员、单位领导、培训机构业务员、培训机构领导 将角色转化为json格式的字符串
			 */
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("roleCodes",
					new String[] { RoleConstant.AREA_ADMIN, RoleConstant.AREA_COMPETENT_ADMIN, RoleConstant.DEPT_ADMIN,
							RoleConstant.DEPT_LEADER, RoleConstant.TRAIN_BUSINESS, RoleConstant.TRAIN_LEADER });
			List<Role> roles = roleService.selectByMap(args);
			List<Map<String, String>> _roles = new ArrayList<Map<String, String>>();
			for (Role role : roles) {
				Map<String, String> _role = new HashMap<String, String>();
				_role.put("text", role.getName());
				_role.put("pid", "");
				_role.put("id", role.getId());
				_roles.add(_role);
			}
			mv.addObject("rolesJson", JsonUtil.toJson(_roles));
			mv.addObject("roleIds", roleIds);
		}
		mv.addObject("user", user);
		mv.addObject("isAdmin", isAdmin);
		return mv;
	}

	@RequestMapping("/custedit")
	public ModelAndView custedit(CustomersEmail customersEmail) throws IOException {
		ModelAndView mv = this.newModelAndView();
		String userId = customersEmail.getId();

		if (StringUtils.isNotEmpty(userId)) {
			customersEmail = customersService.selectById(customersEmail.getId()).get(0);

		}

		mv.addObject("user", customersEmail);
		return mv;
	}

	@RequestMapping("/updateAll")
	public String updateAll(@FormModel("user") CustomersEmail customersEmail, HttpServletResponse response,
							HttpServletRequest request) throws IOException {

		int count = customersService.updateByAll(customersEmail);

		return this.renderText(Response.newDefaultResponse(count).toString(), response);
	}

	@RequestMapping("/customeredit")
	public ModelAndView customeredit() throws IOException {
		ModelAndView mv = this.newModelAndView();

		return mv;
	}

	/**
	 * 系统管理-新增修改保存用户、新增、修改管理员
	 *
	 * @param user
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("user") User user, boolean isAdmin, String roleIds, HttpServletResponse response) {
		int count = 0;
		SearchParam searchParam = new SearchParam();
		Map<String, Object> paramMap = searchParam.getParamMap();
		paramMap.put("userId", user.getId());
		paramMap.put("userName", user.getUserName());
		paramMap.put("paperworkNo", user.getPaperworkNo());
		if (userService.checkExistUser(searchParam)) {
			count = userService.save(isAdmin, user, roleIds);
		} else {
			return this.renderText(new Response(300, "用户名或者证件号已经存在").toString(), response);
		}
		return this.renderText(Response.newDefaultResponse(count).toString(), response);
	}

	/**
	 * 删除用户
	 *
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@FormModel("user") User user, HttpServletResponse response, HttpServletRequest request) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		//是否能够删除本用户（超级管理员，企业管理员，关联了本员工的部门的部门管理员）
		boolean flag = userService.deleteAble(loginUser, user);
		if(!flag){
			return this.render(new Response("03", "无权限").toString(), "text/plain;charset=UTF-8", response);
		}

		int count = userService.delete(user, loginUser.getId());
		// 逻辑删除旧部门关联
		UserDept userDept = new UserDept();
		userDept.setUserId(user.getId());
		userDeptService.deleteByUserIdLogic(userDept);
		// 删除角色
		userRoleService.deleteByUserId(user.getId());

		if (count > 0) {
			CommonLogUtils.log(user.getId(), null, null, null, "删除用户" + user.getRealName() + "成功", loginUser.getId(),
					"", "", "DELETE", "", request);
		} else {
			CommonLogUtils.log(user.getId(), null, null, null, "删除用户" + user.getRealName() + "失败", loginUser.getId(),
					"", "", "DELETE", "", request);
		}

		return this.render(count > 0 ? new Response("00", "删除成功").toString() : new Response("01", "删除失败").toString(),
				"text/plain;charset=UTF-8", response);
	}

	@RequestMapping("/deletes")
	@ResponseBody
	public String deletes(@FormModel("user") CustomersEmail user, HttpServletResponse response,
						  HttpServletRequest request) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		int count = customersService.updateById(user.getId());

		if (count > 0) {
			CommonLogUtils.log(user.getId(), null, null, null, "删除用户" + user.getUserName() + "成功", loginUser.getId(),
					"", "", "DELETE", "", request);
		} else {
			CommonLogUtils.log(user.getId(), null, null, null, "删除用户" + user.getUserName() + "失败", loginUser.getId(),
					"", "", "DELETE", "", request);
		}

		return this.render(count > 0 ? new Response("00", "删除成功").toString() : new Response("01", "删除失败").toString(),
				"text/plain;charset=UTF-8", response);
	}

	@RequestMapping("/add")
	@ResponseBody
	public String add(@FormModel("user") CustomersEmail user, HttpServletResponse response,
					  HttpServletRequest request) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		RegisterDto register = new RegisterDto();
		user.setCreator(loginUser.getUserName());
		user.setUserId(loginUser.getId());
		int count = 1;
		SearchParam searchParam = new SearchParam();
		Map<String, Object> paramMap = searchParam.getParamMap();
		paramMap.put("userEmail", user.getUserEmail());
		List<CustomersEmail> ul = customersService.list(searchParam);
		if (ul.size() > 1) {

			count = 0;
			return this.renderText(Response.newDefaultResponse(count).toString(), response);
		}

		register.setUserName(user.getUserName());
		register.setUnitEmail(user.getUserEmail());
		register.setUnitName(user.getCompanyName());
		register.setUnitPhone(user.getMobilePhoe());
		try {
			customersService.addCustomers(register, user);
		} catch (Exception e) {
			count = -1;
		}

		return this.renderText(Response.newDefaultResponse(count).toString(), response);
	}

	/**
	 * 重置密码
	 *
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping("resetPassword")
	@ResponseBody
	public String resetPassword(@FormModel("user") User user, HttpServletResponse response) {
		int count = 0;
		Object roleCode = user.getRoleCode();
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String password = sdf.format(date);
		if (null != roleCode && "2".equals(user.getRoleCode())) {
			String paperworkNo = user.getPaperworkNo();
			if (null != paperworkNo && paperworkNo.length() == 18) {
				password = paperworkNo.substring(paperworkNo.length() - 6);
			}
		}
		user.setPasswordPlain(password);
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		count = userService.update(user, loginUser.getId());
		String message = "操作成功！重置后密码为：'" + password + "'";
		Response responseMessage = Response.failInstance();
		if (count > 0) {
			responseMessage = new Response("00", message);
		}
		return this.render(responseMessage.toString(), "text/plain;charset=UTF-8", response);
	}

	/**
	 * 帐号 解锁/锁定
	 *
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping("lock")
	@ResponseBody
	public String unlock(@FormModel("user") User user, HttpServletResponse response) {
		Response jsonResponse = null;
		int count = 0;
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		// user.setRoleCode("-1");// 不更新当前字段
		count = userService.update(user, loginUser.getId());
		if (count > 0 && user.getStatus().equals("0")) {
			count += userLoginAttemptService.clearAttempts(user.getId());// 清空记录
		}
		if (count > 0) {
			jsonResponse = new Response("00", "操作成功");
		} else {
			jsonResponse = new Response("01", "操作失败");
		}
		return this.render(jsonResponse.toString(), "text/plain;charset=UTF-8", response);
	}

	/**
	 * CMS首页展示培训师资系统信息
	 *
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/teacher/showIndex")
	public ModelAndView listTeacherForCMSIndex(SearchParam searchParam) throws Exception {
		ModelAndView mv = new ModelAndView("/common/user/teacher/cms/showIndex");
		List<User> teachers = userService.selectTeacherForCMS(searchParam);
		mv.addObject("teachers", teachers);
		return mv;
	}

	/**
	 * 设置是否是人才
	 *
	 * @param searchParam
	 * @param response
	 * @return
	 */
	@RequestMapping("/{userType}/setPerson")
	public @ResponseBody AjaxReturnObject setPerson(SearchParam searchParam, HttpServletResponse response) {
		int statusCode = 200;
		String msg = "操作成功！";
		if (StringUtils.isNotEmpty((String) searchParam.getParamMap().get("userIds"))) {
			String userIds = (String) searchParam.getParamMap().get("userIds");
			String isPerson = (String) searchParam.getParamMap().get("isPerson");
			userService.isSetPerson(isPerson, userIds);
		}
		return new AjaxReturnObject(statusCode, msg);
	}

	/**
	 * 选择人才类型的选择
	 *
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/student/choosePersonType")
	public ModelAndView choosePersonType(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/user/student/choosePersonType");
		return mv;

	}

	/**
	 * 根据当前人所在区域 查询当前人所在区域及辖下区域的专家信息
	 *
	 * @param searchParam
	 * @param response
	 * @return
	 * @throwsException
	 */
	@RequestMapping("/expert/list")
	public ModelAndView listExpert(SearchParam searchParam, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.newModelAndView();
		Map<String, Object> paramMap = searchParam.getParamMap();
		String action = (String) paramMap.get("action");
		if ("export".equals(action)) {// 导出
			searchParam.getPagination().setAvailable(false);
			List<User> users = userService.listStudent(searchParam);
			userService.setUserInfosForExport(users);
			try {
				Map<String, Object> beans = new HashMap<String, Object>();
				beans.put("yonghu", users);
				beans.put("dateUtils", new DateUtils());
				ExportUtils.exportExcel(response, "/excel/studentExport.xls", beans, "人才信息导出");
			} catch (Exception e) {
				log.error("导出出错", e);
			}
			return null;
		} else {// 正常查询
			mv.addObject("users", userService.selectExperts(searchParam));
		}
		return mv;
	}

	@RequestMapping("/listUserEmail")
	public ModelAndView listUserEmail(@ModelAttribute("searchParam") SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
		LoginUser loginUser = LoginUser.loginUser(request);
		searchParam.getParamMap().put("userId", loginUser.getId());
		mv.addObject("listUser", useremailService.list(searchParam));
		mv.addObject("paramMap", searchParam.getParamMap());

		return mv;
	}

	@RequestMapping("/addUserEmail")
	public ModelAndView addUserEmail() throws IOException {
		ModelAndView mv = this.newModelAndView();

		return mv;
	}

	@RequestMapping("/newUserEmail")
	@ResponseBody
	public String newUserEmail(@FormModel("user") UserEmail user, HttpServletResponse response,
							   HttpServletRequest request) {
		LoginUser loginUser = LoginUser.loginUser(request);
		RegisterDto register = new RegisterDto();
		user.setCreator(loginUser.getUserName());
		int count = 1;
		SearchParam searchParam = new SearchParam();
		Map<String, Object> paramMap = searchParam.getParamMap();
		user.setUserId(loginUser.getId());
		paramMap.put("userId", loginUser.getId());
		List<UserEmail> ul = useremailService.list(searchParam);
		if (ul.size() >= 3) {
			count = 100;
		} else {
			try {
				useremailService.addUserEmail(user);
			} catch (Exception e) {
				count = -1;
			}
		}

		return this.renderText(Response.newDefaultResponse(count).toString(), response);
	}

	@RequestMapping("/deleteUserEmail")
	@ResponseBody
	public String deleteUserEmail(@FormModel("user") UserEmail user, HttpServletResponse response,
								  HttpServletRequest request) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		int count = useremailService.updateById(user.getId());

		if (count > 0) {
			CommonLogUtils.log(user.getId(), null, null, null, "删除用户" + user.getName() + "成功", loginUser.getId(), "",
					"", "DELETE", "", request);
		} else {
			CommonLogUtils.log(user.getId(), null, null, null, "删除用户" + user.getName() + "失败", loginUser.getId(), "",
					"", "DELETE", "", request);
		}

		return this.render(count > 0 ? new Response("00", "删除成功").toString() : new Response("01", "删除失败").toString(),
				"text/plain;charset=UTF-8", response);
	}

	@RequestMapping("/updateUserEmail")
	public String updateUserEmail(@FormModel("user") UserEmail userEmail, HttpServletResponse response,
								  HttpServletRequest request) throws IOException {

		int count = useremailService.updateByAll(userEmail);

		return this.renderText(Response.newDefaultResponse(count).toString(), response);
	}

	@RequestMapping("/editUseremail")
	public ModelAndView editUseremail(UserEmail userEmail) throws IOException {
		ModelAndView mv = this.newModelAndView();
		String userId = userEmail.getId();

		if (StringUtils.isNotEmpty(userId)) {
			userEmail = useremailService.selectById(userEmail.getId()).get(0);

		}

		mv.addObject("user", userEmail);
		return mv;
	}

	private void convertUserRoleCode(User user) {
		String roleCode = user.getRoleCode();
		if (UserConstants.USER_ROLE_CODE_CMS_INFO_ADMIN.equals(roleCode)) {
			user.setRoleCode(RoleConstant.CMS_INFO_ADMIN);
		} else if (UserConstants.USER_ROLECODE_ADMIN.equals(roleCode)) {
			user.setRoleCode(RoleConstant.UNIT_ADMIN);
		} else if (UserConstants.USER_ROLE_CODE_DEPT_ADMIN.equals(roleCode)) {
			user.setRoleCode(RoleConstant.DEPT_ADMIN);
		} else if (UserConstants.USER_ROLE_CODE_TEST.equals(roleCode)) {
			user.setRoleCode(RoleConstant.TEST);
		} else {
			user.setRoleCode(RoleConstant.EMPLOYEE);
		}
	}

}
