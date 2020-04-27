package com.hcis.ipr.wechat.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.common.privilege.shiro.IShiroLoginService;
import com.hcis.ipanther.common.user.dao.WechatUserDao;
import com.hcis.ipanther.common.user.entity.WechatUser;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.patent.entity.Patent;
import com.hcis.ipr.oauth.service.OauthPatentService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.hcis.ipanther.common.login.service.ILoginService;
import com.hcis.ipanther.common.login.utils.LoginConstants;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserDeptService;
import com.hcis.ipanther.common.user.service.IUserHisService;
import com.hcis.ipanther.common.user.service.IUserRegisterFlowService;
import com.hcis.ipanther.common.user.service.IUserRegisterService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.IUserStaffHisService;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.common.validate.service.IValidateService;
import com.hcis.ipr.wechat.service.IWechatService;
import com.hcis.ipr.wechat.utils.WechatConstants;

/**
 * 
 * @author wuwentao
 * @date 2016年10月20日
 */
@Service
public class WechatServiceImpl implements IWechatService {

	@Autowired
	private ApiConfig apiConfig;
	@Autowired
	private ILoginService loginService;
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IValidateService validateService;
	
	@Resource
	private IUserRegisterFlowService userRegisterFlowService;
	@Autowired
	private IUserRegisterService userRegisterService;
	@Autowired
    protected TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IUserHisService userHisService;
	@Autowired
	private IUserDeptService userDeptService;
	@Autowired
	private IUserStaffService userStaffService;
	@Autowired
	private IUserStaffHisService userStaffHisService;
	@Autowired
	private IShiroLoginService shiroLoginService;
	@Autowired
	private OauthPatentService oauthPatentService;
	@Autowired
	private WechatUserDao wechatUserDao;

	@Override
	public boolean login(String wechatId, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(wechatId)) {
			WechatUser wechatUser = wechatUserDao.getByOpenId(wechatId);
			if(wechatUser.getStatus().equals(WechatUser.WeChatUserStatus.REGISTER_AND_BIND.getStatus())){
				LoginUser loginUser = loginService.getLoginUser(wechatUser.getUserId());
				if (null != loginUser) {

					// 登录成功，将信息设置到session中
					request.getSession().setAttribute(WechatConstants.LOGIN_USER, loginUser);
					// 登录成功，将信息设置到session中
					request.getSession().setAttribute(LoginConstants.LOGIN_USER, loginUser);


					//登陆shiro
					UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUserName(), loginUser.getPassword());

					Subject subject = SecurityUtils.getSubject();
					subject.login(token);

					LoginUser loginuser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

					shiroLoginService.onLoginSuccess(token, request, null);

					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String bind(User user, String account, String password) {
		String error = null;
		String wechatId = user.getWechatId();
		if (StringUtils.isEmpty(wechatId)) {
			error = "参数错误";
		}
		else {
			LoginUser loginUser = loginService.getLoginUserByAccount(account);
			if (null == loginUser) {
				error = "账户不存在";
			}
			else if (!StringUtils.equals(loginUser.getPassword(), DigestUtils.md5DigestAsHex(password.getBytes()))) {
				error = "用户名密码不匹配";
			}
			else if (StringUtils.isNotEmpty(loginUser.getWechatId())) {
				error = "该账户已绑定微信";
			}
			else {
				user.setId(loginUser.getId());
				if (0 == userService.update(user, loginUser.getId())) {
					error = "绑定出错";
				}
				else {// 设置备注
					UserAPI userApi = new UserAPI(apiConfig);
					userApi.setUserRemark(wechatId, loginUser.getDeptName() + "-" + loginUser.getRealName());
				}
			}
		}
		return error;
	}

	@Override
	public String unbind(String wechatId) {
		String error = null;
		if (StringUtils.isEmpty(wechatId)) {
			error = "参数错误";
		}
		else {
			LoginUser loginUser = loginService.getLoginUserByWechatId(wechatId);
			if (null == loginUser) {
				error = "账户不存在";
			}
			else {
				User user = new User();
				user.setId(loginUser.getId());
				user.setWechatId("");
				user.setWechatNickname("");
				if (0 == userService.update(user, loginUser.getId())) {
					error = "解绑出错";
				}
			}
		}
		return error;
	}

	@Override
	public void loginV2(WechatUser user, HttpServletRequest request) {
		if (StringUtils.isNotBlank(user.getPhone()) && StringUtils.isNotBlank(user.getOpenId())) {
			//
			LoginUser loginUser = loginService.getUserByPhoneAndWechatId(user.getPhone(), user.getOpenId());
			if (loginUser != null) {
				// 登录成功，将信息设置到session中
				request.getSession().setAttribute(WechatConstants.LOGIN_USER, loginUser);
				// 登录成功，将信息设置到session中
				request.getSession().setAttribute(LoginConstants.LOGIN_USER, loginUser);


				//登陆shiro
				UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getPaperworkNo(), loginUser.getPassword());

				Subject subject = SecurityUtils.getSubject();
				subject.login(token);
			}
		}
	}

    @Override
    public List<Patent> searchPatents(SearchParam searchParam) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		List<Patent> reList = new ArrayList<>();
		Map<String, Object> queryMap = oauthPatentService.list(searchParam, "");

		if(!queryMap.get("status").equals("0")){
			return reList;
		}

		List<Map<String, Object>> results = (List<Map<String, Object>>) queryMap.get("results");
		Integer total = (Integer) queryMap.get("total");
		searchParam.getPagination().setRowCount(total);
		for(Map<String, Object> map : results){
			Patent patent = new Patent();
			patent.setPatentName((String) map.get("title"));
			patent.setPatentType((String) map.get("patType"));

			List<String> appNumbers = (List<String>) map.get("appNumber");
			if(appNumbers.size() > 0){
				patent.setAppNumber(appNumbers.get(0));
			}

			patent.setAppDate(dateFormat.parse((String) map.get("appDate")));
			List<String> inventors = (List<String>) map.get("inventroName");
			if(inventors != null){
				String inventorNames = "";
				for(String name : inventors){
					if(StringUtils.isBlank(inventorNames)){
						inventorNames += name;
					}else {
						inventorNames += "," + name;
					}
				}
				patent.setInventor(inventorNames);
			}
			List<String> applyers = (List<String>) map.get("patentee");
			if(inventors != null){
				String applyerNames = "";
				for(String name : applyers){
					if(StringUtils.isBlank(applyerNames)){
						applyerNames += name;
					}else {
						applyerNames += "," + name;
					}
				}
				patent.setApplyer(applyerNames);
			}
			//ResolveUtils.resolve((String) map.get("legalStatus"), "#", 1)
			patent.setLegalStatus((String) map.get("lprs"));

			reList.add(patent);
		}

		return reList;
    }
	
	/*@Override
	public String saveUserForSpace(User user,String registerType,String checkType,HttpServletRequest request) throws Exception {
		//LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		LoginUser loginUser = WechatUtils.getLoginUser(request);
		String userId = user.getId();
		String loginUserId = loginUser.getId();
		
		 * 如果用户是注册请求，则要更改用户的类型
		 * 并且需要发起对应的流程
		 * 学员流程不走流程机
		 * 教师流程走流程机
		 
		if(StringUtils.isNotEmpty(registerType)) {
			String type = user.getType();
			if("student".equals(registerType)) {//注册为学员
				if(UserConstants.USER_ROLECODE_TEACHER.equals(type)) {
					user.setType(UserConstants.USER_ROLECODE_STUDENT_TEACHER);
				} else {
					user.setType(UserConstants.USER_ROLECODE_STUDENT);
				}
				user.setStudentStatus(StudentStatus.AUDIT.toString());//审核中
				
				 * 创建学员的申请流程记录
				 
				UserRegisterFlow flow = new UserRegisterFlow();
				flow.setStatus(UserRegisterFlow.Status.UNDONE.toString());//待办理
				flow.setUserId(userId);
				flow.setTaskId(UserRegisterFlow.TaskId.STUDENT_DEFAULT.toString());//标记是学员注册流程日志
				flow.setTaskName(UserRegisterFlow.TaskName.STUDENT_DEFAULT.toString());
				userRegisterFlowService.create(flow, loginUserId);
				
			} else if("teacher".equals(registerType)) {//注册成为教师
				if(UserConstants.USER_ROLECODE_STUDENT.equals(type)) {
					user.setType(UserConstants.USER_ROLECODE_STUDENT_TEACHER);
				} else {
					user.setType(UserConstants.USER_ROLECODE_TEACHER);
				}
				user.setTeacherStatus(TeacherStatus.AUDIT.toString());//审核中
				
				String businessId = Identities.uuid2();
		        Map<String, Object> variables = new HashMap<String, Object>();
		        //启动流程
		        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(UserConstants.TEACHER_FLOW_USER_DEF_KEY, businessId, variables);
		        
		        
		         * 创建流程主业务数据
		         
		        String procInstId = processInstance.getId();
		        UserRegister userRegister = new UserRegister();
		        userRegister.setId(businessId);
		        userRegister.setUserId(userId);
		        userRegister.setDefaultValue();
		        userRegister.setType(UserRegister.Type.TEACHER.toString());
		        userRegister.setStatus(UserRegister.Status.UNDONE.toString());//正在进行
		        userRegister.setProcInstId(procInstId);
		        userRegisterService.create(userRegister, loginUserId);
		        
		         * 跳过第一个步骤
		         
		        Task task=taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
		        String taskId = task.getId();
		        taskService.claim(taskId, loginUserId);
		        taskService.complete(taskId, variables);//系统自动完成
			}
		}
		((IWechatService)AopContext.currentProxy()).save(null,user,request);
		
		//更新session中用户的头像
		String avatar = user.getAvatar();
		if(StringUtils.isNotEmpty(avatar)) {
			loginUser.setAvatar(avatar);
		}
		
		
		//逻辑删除此次操作产生的验证码
		Validate validate = new Validate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curDate = format.format(new Date());
		validate.setUpdateTime(new Date());
		validate.setEndTime(curDate);
		if("email".equals(checkType))
			validate.setEmail(user.getEmail());
		else
			validate.setPhone(user.getMobilePhone());
		int count = this.validateService.deleteByLogic(validate);
		
		
		
		return userId;
	}
	
	@Override
	public String save(String userType,User user,HttpServletRequest request) throws Exception {
		//LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		LoginUser loginUser = WechatUtils.getLoginUser(request);
		
		 * 将数据拷到用户扩展实体里
		 
		UserStaff userStaff = new UserStaff();
		PropertyUtils.copyProperties(userStaff, user);
		
		String LoginUserId = loginUser.getId();
		String userId = user.getId();
		
		 * 处理可多选的字段，存入数据库为json格式
		 
		String _userType = userStaff.getUserType();//用户类型
		if(StringUtils.isNotEmpty(_userType)) {
			userStaff.setUserType(JsonUtil.toJson(_userType.split(",")));
		}
		String expertType = userStaff.getExpertType();//专家类型
		if(StringUtils.isNotEmpty(expertType)) {
			userStaff.setExpertType(JsonUtil.toJson(expertType.split(",")));
		}
		String expertLevel = userStaff.getExpertLevel();//专家等级
		if(StringUtils.isNotEmpty(expertLevel)) {
			userStaff.setExpertLevel(JsonUtil.toJson(expertLevel.split(",")));
		}
		//更新
			User oldUser = this.userService.read(userId);
			if(oldUser.getDeptId()!=null&&user.getDeptId()!=null){
				if(!oldUser.getDeptId().equals(user.getDeptId())) {
					//更新用户与机构的关联关系
					UserDept userDept=new UserDept();
					userDept.setUserId(userId);
					userDept.setDeptId(oldUser.getDeptId());
					userDept.setNewDeptId(user.getDeptId());
					userDeptService.updateByUserDeptId(userDept, loginUser);
				}
			}
			//更新用户信息
			this.userService.update(user,LoginUserId);
			String hisId = userHisService.bakUser(user.getId());//备份用户
			
			//更新用户的staff信息
			userStaff.setUpdatedby(LoginUserId);
			userStaff.setUpdateTime(new Date());
			int updateCount = userStaffService.update(userStaff,LoginUserId);
			if(updateCount==0) {//初始化的数据可能没有userstaff的信息
				userStaff.setId(Identities.uuid2());
				userStaff.setDefaultValue();
				userStaff.setCreator(LoginUserId);
				userStaff.setUserId(userId);
				userStaffService.create(userStaff,LoginUserId);
			}
			userStaffHisService.bakUserStaff(userId, hisId);//备份用户扩展信息
		
		return userId;
	}
	*/
	
	
	
	
	
	
	

}
