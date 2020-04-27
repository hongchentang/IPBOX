package com.hcis.ipanther.common.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.dept.dao.DepartmentDao;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.user.dao.UserDeptDao;
import com.hcis.ipanther.common.user.dao.UserStaffDao;
import com.hcis.ipanther.common.user.dto.RegisterDto;
import com.hcis.ipanther.common.user.entity.*;
import com.hcis.ipanther.common.user.excelmodel.EmployeeModel;
import com.hcis.ipanther.core.utils.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springside.modules.utils.Collections3;

import com.hcis.ipanther.common.config.AppConfigConstants;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.dept.utils.DepartmentUtils;
import com.hcis.ipanther.common.dict.service.IDictEntryService;
import com.hcis.ipanther.common.dict.utils.DictUtils;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.excel.config.ExcelConfig;
import com.hcis.ipanther.common.excel.convert.ExcelTOModelService;
import com.hcis.ipanther.common.excel.model.ImportFailModel;
import com.hcis.ipanther.common.flow.entity.FlowLog;
import com.hcis.ipanther.common.flow.service.IFlowLogService;
import com.hcis.ipanther.common.flow.service.IFlowService;
import com.hcis.ipanther.common.flow.utils.FlowConstants;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.common.regions.service.IRegionsService;
import com.hcis.ipanther.common.regions.utils.RegionsConstants;
import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipanther.common.security.dao.UserRoleDao;
import com.hcis.ipanther.common.security.entity.UserRole;
import com.hcis.ipanther.common.security.service.IModuleService;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.common.security.service.IUserRoleService;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.security.utils.SecurityRoleUtils;
import com.hcis.ipanther.common.seq.service.ISeqService;
import com.hcis.ipanther.common.user.dao.UserDao;
import com.hcis.ipanther.common.user.entity.User.StudentStatus;
import com.hcis.ipanther.common.user.entity.User.TeacherStatus;
import com.hcis.ipanther.common.user.excelmodel.UserModel;
import com.hcis.ipanther.common.user.service.IUserDeptService;
import com.hcis.ipanther.common.user.service.IUserHisService;
import com.hcis.ipanther.common.user.service.IUserRegisterFlowService;
import com.hcis.ipanther.common.user.service.IUserRegisterService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.IUserStaffHisService;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.common.user.utils.UserUtils;
import com.hcis.ipanther.common.validate.entity.Validate;
import com.hcis.ipanther.common.validate.service.IValidateService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.entity.Response;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import freemarker.template.Template;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	private final static Log log=LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserDao baseDao;
	@Autowired
	private ExcelTOModelService excelTOModelService;
	@Autowired
	private IRegionsService regionsService;
	@Autowired
	private IDictEntryService dictEntryService;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IModuleService moduleService;
	@Autowired
	private IUserDeptService userDeptService;
	@Autowired
	private ISeqService seqService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserStaffService userStaffService;
	@Autowired
	private IDepartmentService departmentService;
	@Resource(name = "imageCaptchaService")
	private ImageCaptchaService imageCaptchaService;
	@Resource
	private IUserRegisterFlowService userRegisterFlowService;
	@Autowired
	private IUserRegisterService userRegisterService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private IFlowService flowService;
	@Autowired
	private IFlowLogService flowLogService;
	@Autowired
	private IUserHisService userHisService;
	@Autowired
	private IUserStaffHisService userStaffHisService;
	@Autowired
	private IValidateService validateService;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private UserDeptDao userDeptDao;
	@Autowired
	private UserStaffDao userStaffDao;
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private UserDao userDao;

	@Resource(name="mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer;




	//查询
	@Override
	@Cacheable(value="iprcache:usercache:userinfo",condition="#userId!=null",key="'iprcache:usercache:userinfo:'+#userId")
	public User read(String userId) {
		return (User) baseDao.selectByPrimaryKey(userId);
	}

	/**
	 * 新增用户
	 */
	@Override
	public int create(User user, String creator) {
		if(user!=null){
			user.setCreator(creator);
			user.setCreateTime(new Date());
			if(StringUtils.isEmpty(user.getId())){
				user.setId(Identities.uuid2());
			}
			//密码加密
			String passwordPlain = user.getPasswordPlain();
			if(StringUtils.isNotEmpty(passwordPlain)) {
				String password=DigestUtils.md5DigestAsHex(passwordPlain.getBytes());
				user.setPassword(password);
			}
			//证件号转为大写
			user.setPaperworkNo(StringUtils.upperCase(user.getPaperworkNo()));
			user.setDefaultValue();
			return baseDao.insertSelective(user);
		}
		return 0;
	}
	//密码重置
	@Override
	public int reSetPassword(String userId){
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		int count=0;
		User uv=this.read(userId);
		String password = AppConfig.getProperty(AppConfigConstants.NAME,AppConfigConstants.USER_DEFAULT_PASSWORD);
		uv.setPasswordPlain(password);
		count=this.update(uv, loginUser.getId());
		return count;
	}

	/**
	 * 更新用户
	 */
	@Override
	@CacheEvict(value="iprcache:usercache:userinfo",condition="#obj!=null",key="'iprcache:usercache:userinfo:'+#obj.id")
	public int update(User obj,String updateBy){
		//密码加密
		String passwordPlain = obj.getPasswordPlain();
		if(StringUtils.isNotEmpty(passwordPlain)) {
			String password=DigestUtils.md5DigestAsHex(passwordPlain.getBytes());
			obj.setPassword(password);
		}
		obj.setPaperworkNo(StringUtils.upperCase(obj.getPaperworkNo()));//证件号转为大写
		return super.update(obj, updateBy);
	}


	@Override
	@CacheEvict(value="iprcache:usercache:userinfo",condition="#obj!=null",key="'iprcache:usercache:userinfo:'+#obj.id")
	public int delete(User obj,String updateBy){
		return super.delete(obj, updateBy);
	}

	//检测新增或修改用户是否重复
	@Override
	public boolean checkExistUser(SearchParam searchParam){
		boolean flg=true;
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("paperworkNo", StringUtils.upperCase((String)paramMap.get("paperworkNo")));//将证件号转成大写
		List<User> list=baseDao.checkExistUser(searchParam);
		if(list.size()>0){
			flg = false;
		}
		return flg;
	}

	//保存单个角色配置
	@Override
	public int saveRoleConfig(User user,UserRole userRole) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		int count=1;
		UserRole temp=new UserRole();
		temp.setUserId(user.getId());
		temp.setRoleCodes(UserUtils.getCanGrantRoleCodes());//只删除自己有权限授权的角色，避免多删
		//根据模块删除对应的角色
		int delNum=	userRoleService.deleteUserRole(temp);

		if(StringUtils.isNotBlank(userRole.getRoleId())){
			String[] tempRoleIds=StringUtils.split(userRole.getRoleId(),";");
			for (String roleId : tempRoleIds) {
				if(StringUtils.isNotEmpty(roleId)){
					userRole=new UserRole();
					userRole.setUserId(user.getId());
					userRole.setRoleId(roleId);
					count+=userRoleService.insertSelective(userRole,loginUser);
				}
			}
		}
		return count+delNum;
	}

	//根据模块删除对应的角色
	public int deleteByModule(UserRole userRole){
		int count =0;
		count=userRoleService.deleteUserRole(userRole);
		return count;
	}

	//去除勾选重复的roleId
	public String[] toSet(String[] tempRoleIds){
		String[] a={};
		Set<String> set=new HashSet<String>(Arrays.asList(tempRoleIds));
		return set.toArray(a);
	}

	//保存批量角色配置
	@Override
	public int saveBatchRoleConfig(SearchParam searchParam,String roleId){
		int count=0;
		Map map=new HashMap();
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userIds=searchParam.getParamMap().get("userIds").toString();
		userIds=StringUtils.substring(userIds, 1, userIds.length()-1);
		List<String> pageUserId=Arrays.asList(userIds.split("##"));
		String [] pageRoleId=roleId.split(",");
		//去除勾选重复的RoleId
		pageRoleId=toSet(pageRoleId);
		for(String rId:pageRoleId){
			map.clear();
			map.put("roleId", rId);
			map.put("userIds", pageUserId);
			List<Map> databaseUserId=userRoleDao.selectByRoleAndUser(map);
			count+=insertUserRole(rId,databaseUserId,pageUserId,loginUser);

		}
		return count;
	}
	//批量插入角色与用户对应关系
	public int insertUserRole(String roleId,List<Map> userIds,List<String> pageUserId,LoginUser loginUser){
		List<String> userIdList=new ArrayList<String>();
		List<String> pageUserIdList=new ArrayList<String>();
		UserRole userRole=null;
		for(Map m:userIds){
			userIdList.add(userIds.get(0).get("userId").toString());
		}
		pageUserIdList.addAll(pageUserId);
		pageUserIdList.removeAll(userIdList);
		for(String userId:pageUserIdList){
			userRole=new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoleService.insertSelective(userRole,loginUser);
		}
		return pageUserIdList.size();
	}

	@Override
	public Map<String, Object> readUserview(User user) {
		return baseDao.selectByPrimaryKey(user);
	}

	@Override
	public List<User> listUser(SearchParam searchParam) {
		Map<String,Object> paramMap = searchParam.getParamMap();
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		/*
		 * 其它只能管理自己单位的
		 * 系统管理员、超级管理员可以管理所有
		 */
		if(!SecurityRoleUtils.isSystemAdmin(loginUser.getId())) {
			paramMap.put("deptId", loginUser.getFirstDeptId());
			paramMap.put("isSystemAdmin", false);//标识不是管理，页面上判断用
		} else {
			paramMap.put("isSystemAdmin", true);//标识不是管理，页面上判断用
		}
		if(!UserConstants.USER_ROLECODE_SUPER_ADMIN.equals(loginUser.getRoleCode())) {//非超级管理
			searchParam.getParamMap().put("notRoleCode", "0");//列出不包括rolecode=0的超级管理员
		}

		return baseDao.selectUserList(searchParam);
	}

	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}

	/**
	 * 新增默认管理员
	 * 同时授予默认管理员对应的角色
	 */
	@Override
	public int addUserAdminDefault(String deptId,LoginUser loginUser){
		int count=0;
		try {
			String currentUserId = loginUser.getId();
			Map<String,Object> userInfos = UserUtils.generatDefaultUserInfos(seqService,currentUserId);
			String defaultUserName = (String) userInfos.get("defaultUserName");
			String roleCodes = (String) userInfos.get("roleCodes");
			if(StringUtils.isNotEmpty(defaultUserName)){
				String password = AppConfig.getProperty(AppConfigConstants.NAME,AppConfigConstants.USER_DEFAULT_PASSWORD);
				User user=new User();
				user.setUserName(defaultUserName);
				user.setRealName(defaultUserName);
				user.setRoleCode(UserConstants.USER_ROLECODE_ADMIN.toString());
				user.setStatus(UserConstants.USER_STATUS_ENABLE);
				user.setDefaultValue();
				user.setPasswordPlain(password);
				user.setPassword(password);
				count = this.create(user,currentUserId);

				/**
				 * 保存用户与部门的关联关系
				 */
				UserDept userDept=new UserDept();
				userDept.setUserId(user.getId());
				userDept.setDeptId(deptId);
				userDept.setDefaultValue();
				userDeptService.create(userDept, currentUserId);

				/**
				 * 插入默认的角色
				 */
				String userId = user.getId();
				if(StringUtils.isNotEmpty(roleCodes)) {
					for (String roleCode : roleCodes.split(",")) {
						if(StringUtils.isNotEmpty(roleCode)) {
							userRoleService.saveByCode(userId, roleCode);
						}
					}
				}

			}
		}
		catch (Exception e) {
			log.error("添加默认管理出错",e);
		}
		return count;
	}

	@Override
	public List<User> listStudent(SearchParam searchParam) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		Map<String,Object> paramMap = searchParam.getParamMap();
		//只查有学生的标志的
		paramMap.put("types", new String[]{UserConstants.USER_ROLECODE_STUDENT,UserConstants.USER_ROLECODE_STUDENT_TEACHER});
		//处理人才分类筛选条件
		String userType = (String) paramMap.get("userType");
		if(StringUtils.isNotEmpty(userType)) {
			userType = "\""+userType.replace(",", "\",\"")+"\"";
			paramMap.put("userTypes",Arrays.asList(userType.split(",")));
		}
		//处理区域多选
		String regionsCode=(String)paramMap.get("regionsCodes");
		if(StringUtils.isNotEmpty(regionsCode)){
			paramMap.put("regionsCodes",Arrays.asList(regionsCode.split(",")));
		}
		String deptType = loginUser.getDeptType();
		/*
		 * 主管机构，可以查看当前区域及向下的
		 * 其它机构只能看到自己机构的
		 */
		if(DepartmentUtils.isAdminCompetent(deptType)) {
			String userRegionsCode = loginUser.getRegionsCode();// 用户区域编码
			String regionsCodeData=RegionsUtil.getChildNodes(userRegionsCode);
			String newData=regionsCodeData.replace("[", "").replace("]", "");
			paramMap.put("regionsCodeChildSearch", newData.replaceAll(" ", ""));
			//	paramMap.put("regionsCodeChildSearch", new String[]{loginUser.getRegionsCode()});
		} else {
			paramMap.put("deptId",loginUser.getFirstDeptId());
		}
		List<User> users = baseDao.selectBySearchParam(searchParam);
		paramMap.put("regionsCodes",regionsCode);
		return users;
	}

	@Override
	public List<User> listTeacher(SearchParam searchParam) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String deptType = loginUser.getDeptType();
		Map<String,Object> paramMap = searchParam.getParamMap();
		//有教师的身份的
		paramMap.put("types", new String[]{UserConstants.USER_ROLECODE_TEACHER,UserConstants.USER_ROLECODE_STUDENT_TEACHER});
		//处理人才分类筛选条件
		String userType = (String) paramMap.get("userType");
		if(StringUtils.isNotEmpty(userType)) {
			userType = "\""+userType.replace(",", "\",\"")+"\"";
			paramMap.put("userTypes",Arrays.asList(userType.split(",")));
		}
		/*
		 * 主管机构，可以查看当前区域及向下的
		 * 其它机构只能看到自己机构的
		 */
		if(DepartmentUtils.isAdminCompetent(deptType)) {
			Map<String,Object> map=new HashMap<String,Object>();
			String userRegionsCode = loginUser.getRegionsCode();// 用户区域编码
			String regionsCodeData=RegionsUtil.getChildNodes(userRegionsCode);
			String newData=regionsCodeData.replace("[", "").replace("]", "");
			paramMap.put("regionsCodeChildSearch", newData.replaceAll(" ", ""));
			//paramMap.put("regionsCodeChildSearch", new String[]{loginUser.getRegionsCode()});
		} else {
			paramMap.put("deptId",loginUser.getFirstDeptId());
		}
		//处理区域多选
		String regionsCode=(String)paramMap.get("regionsCodes");
		if(StringUtils.isNotEmpty(regionsCode)){
			paramMap.put("regionsCodes",Arrays.asList(regionsCode.split(",")));
		}
		List<User> users = baseDao.selectBySearchParam(searchParam);
		paramMap.put("regionsCodes",regionsCode);
		return users;
	}

	@Override
	public List<User> chooseUserList(SearchParam searchParam) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String deptType = loginUser.getDeptType();
		Map<String,Object> paramMap = searchParam.getParamMap();
		//不是师资、管理员角色
		paramMap.put("notRoleCodes", new String[]{UserConstants.USER_ROLECODE_SUPER_ADMIN,UserConstants.USER_ROLECODE_ADMIN,
				UserConstants.USER_ROLECODE_TEACHER,UserConstants.USER_ROLECODE_STUDENT_TEACHER});
		//不是师资
		paramMap.put("notTypes", new String[]{UserConstants.USER_ROLECODE_TEACHER,UserConstants.USER_ROLECODE_STUDENT_TEACHER});
		/*
		 * 主管机构，可以查看当前区域及向下的
		 * 其它机构只能看到自己机构的
		 */
		if(DepartmentUtils.isAdminCompetent(deptType)) {
			Map<String,Object> map=new HashMap<String,Object>();
			String userRegionsCode = loginUser.getRegionsCode();// 用户区域编码
			String regionsCodeData=RegionsUtil.getChildNodes(userRegionsCode);
			String newData=regionsCodeData.replace("[", "").replace("]", "");
			paramMap.put("regionsCodeChildSearch", newData.replaceAll(" ", ""));
			//paramMap.put("regionsCodeChildSearch", new String[]{loginUser.getRegionsCode()});
		} else {
			paramMap.put("deptId",loginUser.getFirstDeptId());
		}
		return baseDao.selectBySearchParam(searchParam);
	}

	@Override
	public List<User> chooseTeacherList(SearchParam searchParam) {
		Map<String,Object> paramMap = searchParam.getParamMap();
		String notIds = (String) paramMap.get("notIds");
		if(StringUtils.isNotEmpty(notIds)) {
			paramMap.put("notIds", notIds.split(","));
		} else {
			paramMap.put("notIds", null);
		}
		//是教师
		paramMap.put("roleCodes", new String[]{UserConstants.USER_ROLECODE_TEACHER,UserConstants.USER_ROLECODE_STUDENT_TEACHER});
		List<User> users = baseDao.selectBySearchParam(searchParam);
		paramMap.put("notIds", notIds);
		return users;
	}

	@Override
	public int chooseUserDo(User user) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getId();
		user = this.read(userId);
		String type = user.getType();
		/*
		 * 如果用户之前是人才，则角色设置为人才+教师，否则设置为教师
		 */
		if(UserConstants.USER_ROLECODE_STUDENT.equals(type)) {
			user.setType(UserConstants.USER_ROLECODE_STUDENT_TEACHER);
		} else {
			user.setType(UserConstants.USER_ROLECODE_TEACHER);
		}
		user.setTeacherStatus(User.TeacherStatus.EDIT.toString());//编辑中的状态
		return ((IUserService)AopContext.currentProxy()).update(user, loginUser.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> auditTeacherList(SearchParam searchParam,String type) {
		Map<String,Object> paramMap = searchParam.getParamMap();
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		List<User> users = new ArrayList<User>();
		if("Edit".equals(type)) {//编辑中
			//有教师的身份的
			paramMap.put("types", new String[]{UserConstants.USER_ROLECODE_TEACHER,UserConstants.USER_ROLECODE_STUDENT_TEACHER});
			//编辑中或者退回的都列在编辑中
			paramMap.put("teacherStatuses", new String[]{User.TeacherStatus.EDIT.toString(),User.TeacherStatus.REJECT.toString()});
			users = baseDao.selectBySearchParam(searchParam);
		} else if("Todo".equals(type)) {//待办
			List<String> roles = Collections3.extractToList(loginUser.getRoleList(), "roleCode");
			/*
			 * 机构领导只能处理本机构的
			 * 省局、系统管理员（运维）可以处理全省的
			 */
			if(roles.contains(RoleConstant.DEPT_LEADER)) {
				paramMap.put("roles", new String[]{RoleConstant.DEPT_LEADER});
				paramMap.put("deptId", loginUser.getFirstDeptId());
			} else if(roles.contains(RoleConstant.GD_IPR)){
				paramMap.put("roles", new String[]{RoleConstant.GD_IPR});
			} else if(roles.contains(RoleConstant.ADMIN)){
				paramMap.put("roles", new String[]{RoleConstant.ADMIN});
			}
			users = baseDao.selectTeacherTodo(searchParam);
		} else if("Done".equals(type)) {//已办
			paramMap.put("userId", loginUser.getId());
			users = baseDao.selectTeacherDone(searchParam);
		}
		return users;
	}

	@Override
	public List<User> listAdmin(SearchParam searchParam) {
		searchParam.getParamMap().put("roleCodes", new String[]{UserConstants.USER_ROLECODE_ADMIN});//只查管理员
		return baseDao.selectBySearchParam(searchParam);
	}

	@Override
	@CacheEvict(value="iprcache:usercache:userinfo",condition="#user!=null",key="'iprcache:usercache:userinfo:'+#user.id")
	public String save(String userType,User user) throws Exception {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();

		/*
		 * 将数据拷到用户扩展实体里
		 */
		UserStaff userStaff = new UserStaff();
		PropertyUtils.copyProperties(userStaff, user);

		String LoginUserId = loginUser.getId();
		String userId = user.getId();
		/*
		 * 处理可多选的字段，存入数据库为json格式
		 */
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
		if(StringUtils.isEmpty(userId)) {//新增
			userId = Identities.uuid2();
			/*
			 * 设置用户的角色
			 * 给用户添加角色
			 */
			user.setRoleCode(UserConstants.USER_ROLECODE_STUDENT);//人才角色
			user.setType(UserConstants.USER_ROLECODE_STUDENT);//人才标志
			user.setStudentStatus(User.StudentStatus.SUCCESS.toString());//标记审核成功
			String roleCode = RoleConstant.EMPLOYEE;
			if("teacher".equals(userType)) {//教师-教师需要经过审核才能正式成为教师
				user.setRoleCode("");
				roleCode = RoleConstant.COMMON;//普通角色
				user.setType(UserConstants.USER_ROLECODE_TEACHER);//教师身份标识
				user.setStudentStatus("");//学生审核标记置空
				user.setTeacherStatus(User.TeacherStatus.EDIT.toString());//教师编辑中
			}
			if("expert".equals(userType)){
				user.setRoleCode(UserConstants.USER_ROLECODE_EXPERT);//专家角色
				roleCode = RoleConstant.EXPERT;//专家角色
				user.setType(UserConstants.USER_ROLECODE_EXPERT);//专家份标识
				//user.setStudentStatus("");//学生审核标记置空
			}
			//设定角色
			userRoleService.saveByCode(userId, roleCode);

			//保存用户与机构的关联关系
			UserDept userDept=new UserDept();
			userDept.setUserId(userId);
			userDept.setDeptId(user.getDeptId());
			userDept.setDefaultValue();
			userDeptService.create(userDept, LoginUserId);

			//保存用户信息
			user.setId(userId);
			user.setDefaultValue();
			user.setCreator(LoginUserId);
			user.setStatus(UserConstants.USER_STATUS_ENABLE);//正常状态
			this.create(user,LoginUserId);
			String hisId = userHisService.bakUser(userId);//备份用户

			//保存用户的staff信息
			userStaff.setId(Identities.uuid2());
			userStaff.setDefaultValue();
			userStaff.setCreator(LoginUserId);
			userStaff.setUserId(userId);
			userStaffService.create(userStaff,LoginUserId);
			userStaffHisService.bakUserStaff(userId, hisId);//备份用户扩展信息
		} else {//更新
			User oldUser = this.read(userId);
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
			this.update(user,LoginUserId);
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
		}
		return userId;
	}

	@Override
	@CacheEvict(value="iprcache:usercache:userinfo",condition="#user!=null",key="'iprcache:usercache:userinfo:'+#user.id")
	public String saveV2(String userType,User user) throws Exception {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();

		/*
		 * 将数据拷到用户扩展实体里
		 */
		UserStaff userStaff = new UserStaff();
		PropertyUtils.copyProperties(userStaff, user);

		String LoginUserId = loginUser.getId();
		String userId = user.getId();
		/*
		 * 处理可多选的字段，存入数据库为json格式
		 */
		//用户类型
		String _userType = userStaff.getUserType();
		if(StringUtils.isNotEmpty(_userType)) {
			userStaff.setUserType(JsonUtil.toJson(_userType.split(",")));
		}

		String roleCode = user.getRoleCode();
		convertUserRoleCode(user);
		if(StringUtils.isEmpty(userId)) {
			//新增
			userId = Identities.uuid2();

			//用户类型
			user.setType(UserConstants.USER_ROLE_CODE_EMPLOYEE);
			//标记审核成功
			user.setStudentStatus(User.StudentStatus.SUCCESS.toString());
			if(StringUtils.isBlank(roleCode)){
				roleCode = RoleConstant.EMPLOYEE;
			}
			//设定角色
			userRoleService.saveByCode(userId, roleCode);

			//保存用户信息
			user.setId(userId);
			user.setDefaultValue();
			user.setCreator(LoginUserId);
			//正常状态
			user.setStatus(UserConstants.USER_STATUS_ENABLE);
			this.create(user,LoginUserId);
			//备份用户
			String hisId = userHisService.bakUser(userId);

			//保存用户的staff信息
			userStaff.setId(Identities.uuid2());
			userStaff.setDefaultValue();
			userStaff.setCreator(LoginUserId);
			userStaff.setUserId(userId);
			userStaffService.create(userStaff,LoginUserId);
			//备份用户扩展信息
			userStaffHisService.bakUserStaff(userId, hisId);

			//
			saveAdminDept(user, LoginUserId);

		} else {
			//更新
			//更新用户信息
			this.update(user,LoginUserId);
			//备份用户
			String hisId = userHisService.bakUser(user.getId());

			//更新用户的staff信息
			userStaff.setUpdatedby(LoginUserId);
			userStaff.setUpdateTime(new Date());
			int updateCount = userStaffService.update(userStaff,LoginUserId);
			if(updateCount==0) {
				//初始化的数据可能没有userstaff的信息
				userStaff.setId(Identities.uuid2());
				userStaff.setDefaultValue();
				userStaff.setCreator(LoginUserId);
				userStaff.setUserId(userId);
				userStaffService.create(userStaff,LoginUserId);
			}
			//备份用户扩展信息
			userStaffHisService.bakUserStaff(userId, hisId);

			//修改角色权限
			userRoleService.deleteByUserId(userId);
			userRoleService.saveByCode(userId, roleCode);

			//
			userDeptDao.deleteByUserId(userId);
			saveAdminDept(user, LoginUserId);

		}
		return userId;
	}

	private void convertUserRoleCode(User user) {
		String roleCode = user.getRoleCode();
		if(RoleConstant.CMS_INFO_ADMIN.equals(roleCode)){
			user.setRoleCode(UserConstants.USER_ROLE_CODE_CMS_INFO_ADMIN);
		}else if(RoleConstant.UNIT_ADMIN.equals(roleCode)){
			user.setRoleCode(UserConstants.USER_ROLECODE_ADMIN);
		}else if(RoleConstant.DEPT_ADMIN.equals(roleCode)){
			user.setRoleCode(UserConstants.USER_ROLE_CODE_DEPT_ADMIN);
		}else if(RoleConstant.TEST.equals(roleCode)){
			user.setRoleCode(UserConstants.USER_ROLE_CODE_TEST);
		}else {
			user.setRoleCode(UserConstants.USER_ROLE_CODE_EMPLOYEE);
		}
	}

	private void saveAdminDept(User user, String LoginUserId){
		String deptIdStr = user.getDeptIds();
		String userId = user.getId();
		String dId = user.getDeptId();
		if(StringUtils.isNotBlank(deptIdStr) || StringUtils.isNotBlank(dId)){
			String[] deptIds = deptIdStr.split(",");
			Set<String> deptIdSet = new HashSet<>();
			deptIdSet.add(dId);
			for(String deptId : deptIds){
				if(StringUtils.isNotBlank(deptId)){
					deptIdSet.add(deptId);
				}
			}
			for(String deptId : deptIdSet){
				UserDept dept = new UserDept();
				dept.setUserId(userId);
				dept.setDeptId(deptId);
				dept.setCreator(LoginUserId);
				dept.setDefaultValue();
				userDeptDao.insert(dept);
			}
		}
	}

	@Override
	public String saveUserForSpace(User user,String registerType,String checkType) throws Exception {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getId();
		String loginUserId = loginUser.getId();
		/*
		 * 如果用户是注册请求，则要更改用户的类型
		 * 并且需要发起对应的流程
		 * 学员流程不走流程机
		 * 教师流程走流程机
		 */
		if(StringUtils.isNotEmpty(registerType)) {
			String type = user.getType();
			if("student".equals(registerType)) {//注册为学员
				if(UserConstants.USER_ROLECODE_TEACHER.equals(type)) {
					user.setType(UserConstants.USER_ROLECODE_STUDENT_TEACHER);
				} else {
					user.setType(UserConstants.USER_ROLECODE_STUDENT);
				}
				user.setStudentStatus(StudentStatus.AUDIT.toString());//审核中
				/*
				 * 创建学员的申请流程记录
				 */
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

				/*
				 * 创建流程主业务数据
				 */
				String procInstId = processInstance.getId();
				UserRegister userRegister = new UserRegister();
				userRegister.setId(businessId);
				userRegister.setUserId(userId);
				userRegister.setDefaultValue();
				userRegister.setType(UserRegister.Type.TEACHER.toString());
				userRegister.setStatus(UserRegister.Status.UNDONE.toString());//正在进行
				userRegister.setProcInstId(procInstId);
				userRegisterService.create(userRegister, loginUserId);
				/*
				 * 跳过第一个步骤
				 */
				Task task=taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
				String taskId = task.getId();
				taskService.claim(taskId, loginUserId);
				taskService.complete(taskId, variables);//系统自动完成
			}
		}
		((IUserService)AopContext.currentProxy()).save(null,user);
		//更新session中用户的头像
		String avatar = user.getAvatar();
		if(StringUtils.isNotEmpty(avatar)) {
			loginUser.setAvatar(avatar);
		}


		//逻辑删除此次操作产生的验证码
		deleteValidate(checkType,user);



		return userId;
	}

	public int deleteValidate(String checkType,User user){
		//删除此次操作产生的验证码
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
		return count;
	}

	@Override
	public void setUserInfosForExport(List<User> users) throws Exception {
		LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		SearchParam searchParam = new SearchParam();
		Map<String, Role> roleMap = roleService.select2Map(searchParam);
		Map<String, Department> departmentNameMap = departmentService.getDepartmentNameMap(loginUser.getCompanyId());

		List<String> userIds = new ArrayList<>();
		for(User user : users){
			userIds.add(user.getId());
		}
		Map<String, List<String>> userDeptMap = departmentService.getUserDeptMap(userIds);

		//取出userstaff并转码
		for (User user : users) {
			UserStaff staff = userStaffService.read(user.getId());
			if(null==staff) {
				staff = new UserStaff();
			}
			PropertyUtils.copyProperties(user,staff);
			//转码
			user.setSex(DictionaryUtils.getEntryName(UserConstants.DICT_SEX_TYPE, user.getSex()));
			user.setUserType(DictUtils.getEntryNameByJson(UserConstants.DICT_USER_TYPE, user.getUserType()));
			user.setPoliticsRole(DictionaryUtils.getEntryName(UserConstants.DICT_ZZMM, user.getPoliticsRole()));
			user.setCountry(DictionaryUtils.getEntryName(UserConstants.DICT_GJDQ, user.getCountry()));
			user.setNation(DictionaryUtils.getEntryName(UserConstants.DICT_MZ, user.getNation()));
			user.setPaperworkType(DictionaryUtils.getEntryName(UserConstants.DICT_PAPERWORK_TYPE, user.getPaperworkType()));
			user.setExpertType(DictUtils.getEntryNameByJson(UserConstants.DICT_EXPERT_TYPE, user.getExpertType()));
			user.setExpertLevel(DictUtils.getEntryNameByJson(UserConstants.DICT_EXPERT_LEVEL, user.getExpertLevel()));
			user.setHighDiploma(DictionaryUtils.getEntryName(UserConstants.DICT_DIPLOMA_TYPE, user.getHighDiploma()));
			user.setHighDegree(DictionaryUtils.getEntryName(UserConstants.DICT_DEGREE_TYPE, user.getHighDegree()));
			user.setForeignLanguages((DictionaryUtils.getEntryName(UserConstants.DICT_YZMC, user.getForeignLanguages())));
			user.setForeignDegree(DictionaryUtils.getEntryName(UserConstants.DICT_WGYZSLCD, user.getForeignDegree()));
			user.setComputerLevel(DictionaryUtils.getEntryName(UserConstants.DICT_COMPUTER_LEVEL, user.getComputerLevel()));
			user.setHaveExp((DictionaryUtils.getEntryName(UserConstants.DICT_HAVE_EXP, user.getHaveExp())));

			//籍贯
			user.setHometownProvince(RegionsUtil.getRegionsName(user.getHometownProvince()));
			user.setHometownCity(RegionsUtil.getRegionsName(user.getHometownCity()));

			//目前所在地区
			user.setCurrentProvince(RegionsUtil.getRegionsName(user.getCurrentProvince()));
			user.setCurrentCity(RegionsUtil.getRegionsName(user.getCurrentCity()));
			user.setCurrentCounties(RegionsUtil.getRegionsName(user.getCurrentCounties()));

			//角色
			Role role = roleMap.get(user.getRoleCode());
			if(role != null){
				user.setRoleName(role.getName());
			}
			//所在部门
			List<String> deptIds = userDeptMap.get(user.getId());
			if(CollectionUtils.isNotEmpty(deptIds)){
				StringBuilder deptIdStr = new StringBuilder();
				for(String deptId : deptIds){
					if(StringUtils.isBlank(deptIdStr.toString())){
						deptIdStr.append(deptId);
					}else {
						deptIdStr.append(",").append(deptId);
					}
				}
				user.setDeptIds(deptIdStr.toString());
			}
		}
	}

	@Override
	public String importUser(String userType,MultipartFile file,HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = "";
		try {
			//生成批次ID
			String batchId = Identities.uuid2();

			LoginUser loginUser = LoginUser.loginUser(request);
			String loginUserId = loginUser.getId();

			/**
			 * 用户归属机构：
			 * 如果导入人是培训机构，则用户直接归属到当前人所在机构
			 * 如果导入人不是培训机构，则用户归属到当前人所在区域的“个人机构”
			 */
			String deptId = "";
			if(DepartmentUtils.isTrainOrg(loginUser.getDeptType())) {
				deptId = loginUser.getFirstDeptId();
			} else {
				Department virtualDept = departmentService.getVirtualDeptByRegionsCode(loginUser.getRegionsCode());
				deptId = virtualDept.getId();
			}
			InputStream inputStream = file.getInputStream();

			FileInputStream in = (FileInputStream)inputStream;
			//得到工作表
			HSSFWorkbook book = new HSSFWorkbook(in);
			List<ImportFailModel> failList = new ArrayList<ImportFailModel>();//校验失败的
			List<UserModel> successList = new ArrayList<UserModel>();

			String modelName = userType+"UserModel";
			List<Object> list = excelTOModelService.getModelList(book,modelName, failList);
			HSSFSheet sheet = book.getSheetAt(0);

			Date now = new Date();

			//用于校验时传输数据
			SearchParam searchParam = new SearchParam();
			Map<String, Object> paramMap = searchParam.getParamMap();

			/*
			 * 设置状态角色
			 */
			String userRoleCode = UserConstants.USER_ROLECODE_STUDENT;
			String roleCode = RoleConstant.STUDENT;
			String type = UserConstants.USER_ROLECODE_STUDENT;
			String studentStatus = User.StudentStatus.SUCCESS.toString();//已通过
			String teacherStatus = "";
			if("teacher".equals(userType)) {//教师要走流程通过后才能成为教师
				userRoleCode = "";//角色代码为空
				roleCode = RoleConstant.COMMON;//只赋予普通的角色
				type = UserConstants.USER_ROLECODE_TEACHER;//类型为教师
				studentStatus = "";//置空学生状态
				teacherStatus = User.TeacherStatus.EDIT.toString();//编辑中
			}
			for(Object obj:list){

				UserModel model = (UserModel)obj;

				String userId = Identities.uuid2();

				UserStaff userStaff = new UserStaff();
				User user = new User();

				HSSFRow row = sheet.getRow(model.getRownum());
				HSSFCell resultCell = row.createCell((short)(row.getPhysicalNumberOfCells()));//用于保存导入结果的单元格
				HSSFRichTextString rts=null;//结果描述

				try {
					BeanUtils.copyProperties(user, model);//从模型中拷贝数据

					String paperworkNo = model.getPaperworkNo();

					//校验证件类型是否合法
					String paperwordType = user.getPaperworkType();
					if("01".equals(paperwordType)) {
						boolean isLegalCard = Validator.validateIdCards(paperworkNo);
						if(!isLegalCard) {
							rts = new HSSFRichTextString("身份证格式不合法");
							resultCell.setCellValue(rts);
							ImportFailModel failModel=new ImportFailModel(model.getRownum()+1,"身份证格式不合法");
							failList.add(failModel);
							continue;
						}
					}

					//校验证件或者用户名是否已经存在
					paramMap.put("userName", model.getUserName());
					paramMap.put("paperworkNo", paperworkNo);
					boolean notExists = this.checkExistUser(searchParam);
					if(!notExists) {//已经存在不保存
						rts = new HSSFRichTextString("用户名或者身份证已经存在");
						resultCell.setCellValue(rts);
						ImportFailModel failModel=new ImportFailModel(model.getRownum()+1,"用户名或者身份证已经存在");
						failList.add(failModel);
						continue;
					}
					//转码
					user.setSex(DictUtils.getEntryValueByName(UserConstants.DICT_SEX_TYPE, user.getSex()));
					user.setUserType(DictUtils.getEntryValueByName(UserConstants.DICT_USER_TYPE, user.getUserType(),true));
					user.setPoliticsRole(DictUtils.getEntryValueByName(UserConstants.DICT_ZZMM, user.getPoliticsRole()));
					user.setCountry(DictUtils.getEntryValueByName(UserConstants.DICT_GJDQ, user.getCountry()));
					user.setNation(DictUtils.getEntryValueByName(UserConstants.DICT_MZ, user.getNation()));
					user.setPaperworkType(DictUtils.getEntryValueByName(UserConstants.DICT_PAPERWORK_TYPE, user.getPaperworkType()));
					user.setExpertType(DictUtils.getEntryValueByName(UserConstants.DICT_EXPERT_TYPE, user.getExpertType(),true));
					user.setExpertLevel(DictUtils.getEntryValueByName(UserConstants.DICT_EXPERT_LEVEL, user.getExpertLevel(),true));
					user.setHighDiploma(DictUtils.getEntryValueByName(UserConstants.DICT_DIPLOMA_TYPE, user.getHighDiploma()));
					user.setHighDegree(DictUtils.getEntryValueByName(UserConstants.DICT_DEGREE_TYPE, user.getHighDegree()));
					user.setForeignLanguages((DictUtils.getEntryValueByName(UserConstants.DICT_YZMC, user.getForeignLanguages())));
					user.setForeignDegree(DictUtils.getEntryValueByName(UserConstants.DICT_WGYZSLCD, user.getForeignDegree()));
					user.setComputerLevel(DictUtils.getEntryValueByName(UserConstants.DICT_COMPUTER_LEVEL, user.getComputerLevel()));
					user.setHaveExp(DictUtils.getEntryValueByName(UserConstants.DICT_HAVE_EXP, user.getHaveExp()));

					//转日期
					user.setWorkDate(DateUtils.parseDate(model.getWorkDate_()));
					user.setAdminStartDate(DateUtils.parseDate(model.getAdminStartDate_()));
					user.setAdminEndDate(DateUtils.parseDate(model.getAdminEndDate_()));
					user.setStartTime(DateUtils.parseDate(model.getStartTime_()));
					user.setEndTime(DateUtils.parseDate(model.getEndTime_()));
					user.setHighDt(DateUtils.parseDate(model.getHighDt_()));
					user.setHighDegreeDt(DateUtils.parseDate(model.getHighDegreeDt_()));
					user.setProDt(DateUtils.parseDate(model.getProDt_()));
					user.setTitleDt(DateUtils.parseDate(model.getTitleDt_()));

					//保存staff
					user.setUserId(userId);
					user.setCreateTime(now);//导入时间
					user.setCreator(userId);//导入人
					user.setDefaultValue();

					//出生日期校验，避免计算年龄时出错
					Date _bornDate = DateUtils.parseDate(user.getBornDate(), "yyyy-MM");
					if(null==_bornDate) {
						user.setBornDate("");
					}

					/**
					 * 设置状态角色
					 */
					user.setRoleCode(userRoleCode);
					user.setType(type);
					user.setStudentStatus(studentStatus);
					user.setTeacherStatus(teacherStatus);

					String password = AppConfig.getProperty(AppConfigConstants.NAME,AppConfigConstants.USER_DEFAULT_PASSWORD);
					user.setPasswordPlain(password);
					user.setId(userId);
					user.setDefaultValue();
					user.setCreator(loginUserId);
					user.setStatus(UserConstants.USER_STATUS_ENABLE);//正常状态

					//保存user和机构的关联关系
					UserDept userDept=new UserDept();
					userDept.setUserId(userId);
					userDept.setDeptId(deptId);
					userDept.setDefaultValue();
					userDeptService.create(userDept, loginUserId);

					this.create(user,loginUserId);
					String hisId = userHisService.bakUser(userId);//备份用户信息

					//保存userStaff
					BeanUtils.copyProperties(userStaff, user);
					userStaff.setUserId(userId);
					userStaffService.create(userStaff,loginUserId);
					userStaffHisService.bakUserStaff(userId, hisId);//备份用户扩展信息

					/*
					 * 给用户添加角色
					 */
					userRoleService.saveByCode(userId, roleCode);

					rts = new HSSFRichTextString("导入成功！");
					resultCell.setCellValue(rts);
					successList.add(model);
				} catch (Exception e) {
					log.error(this.getClass(),e);
					rts = new HSSFRichTextString("导入成功失败，请检查数据是否正确");
					resultCell.setCellValue(rts);
					ImportFailModel failModel=new ImportFailModel(model.getRownum()+1,"插入数据失败，请检查数据是否正确");
					failList.add(failModel);
				}
			}

			/**
			 * 生成文件，供用户下载查看导入结果
			 */
			String id = Identities.uuid2();
			String path = request.getSession().getServletContext().getRealPath("/");

			String fileName = path+ExcelConfig.getProperty("excel.file.path")+id+".xls";
			File outputFolder=new File(path+ExcelConfig.getProperty("excel.file.path"));
			if(!outputFolder.exists()){
				outputFolder.mkdirs();
			}
			File outputFile=new File(fileName);
			if(!outputFile.exists()){
				outputFile.createNewFile();
			}
			OutputStream output = new FileOutputStream(outputFile);
			book.write(output);
			output.close();
			resultMap.put("successList", successList);
			resultMap.put("failList", failList);
			resultMap.put("filePath", ExcelConfig.getProperty("excel.file.path")+id+".xls");//导出结果的下载路径
			resultMap.put("message", "成功导入"+successList.size()+"条数据！");//导出结果的整体信息

			request.getSession().setAttribute(batchId, resultMap);
			result = batchId;
		} catch (IOException e) {
			log.error(this.getClass(),e);
		}
		return result;
	}

	@Override
	@Transactional
	public String importUserV2(String userType, MultipartFile file, HttpServletRequest request) throws IOException {

		String result = "";
		String batchId = UUIDUtils.getUUId();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		InputStream inputStream = file.getInputStream();
		LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String loginUserId = loginUser.getId();

		FileInputStream in = (FileInputStream) inputStream;
		//得到工作表
		HSSFWorkbook book = new HSSFWorkbook(in);
		List<ImportFailModel> failList = new ArrayList<ImportFailModel>();//校验失败的
		List<EmployeeModel> successList = new ArrayList<EmployeeModel>();

		String modelName = userType + "UserModel";
		List<Object> list = excelTOModelService.getModelList(book, modelName, failList);
		HSSFSheet sheet = book.getSheetAt(0);

		Date now = new Date();

		SearchParam searchParam = new SearchParam();
		Map<String, Object> paramMap = searchParam.getParamMap();

		Map<String, Role> roleMap = roleService.select2Map(searchParam);

		Map<String, Department> departmentNameMap = departmentService.getDepartmentNameMap(loginUser.getCompanyId());

		for (Object obj : list) {

			EmployeeModel model = (EmployeeModel) obj;

			String userId = Identities.uuid2();

			UserStaff userStaff = new UserStaff();
			User user = new User();

			HSSFRow row = sheet.getRow(model.getRownum());
			//用于保存导入结果的单元格
			HSSFCell resultCell = row.createCell((short) (row.getPhysicalNumberOfCells()));
			//结果描述
			HSSFRichTextString rts = null;

			try {
				//从模型中拷贝数据
				BeanUtils.copyProperties(model, user);



				//校验证件或者用户名是否已经存在
				paramMap.put("userName", model.getUserName());
				boolean notExists = this.checkExistUser(searchParam);
				if (!notExists) {
					//已经存在不保存
					rts = new HSSFRichTextString("用户已存在");
					resultCell.setCellValue(rts);
					ImportFailModel failModel = new ImportFailModel(model.getRownum() + 1, "用户已存在");
					failList.add(failModel);
					continue;
				}

				//转码
				user.setSex(DictUtils.getEntryValueByName(UserConstants.DICT_SEX_TYPE, user.getSex()));

				//保存staff
				user.setUserId(userId);
				//导入时间
				user.setCreateTime(now);
				//导入人
				user.setCreator(userId);
				user.setDefaultValue();

				/**
				 * 设置状态角色
				 */
				Role role = roleMap.get(model.getRoleName());
				if(role == null){
					//找不到角色
					rts = new HSSFRichTextString("填写的角色：" + model.getRoleName() + "不存在！");
					resultCell.setCellValue(rts);
					ImportFailModel failModel = new ImportFailModel(model.getRownum() + 1, "填写的角色：" + model.getRoleName() + " 不存在！");
					failList.add(failModel);
					continue;
				}
				user.setRoleCode(role.getRoleCode());
				user.setType(UserConstants.USER_ROLE_CODE_EMPLOYEE);

				String password = AppConfig.getProperty(AppConfigConstants.NAME, AppConfigConstants.USER_DEFAULT_PASSWORD);
				user.setPasswordPlain(password);
				user.setId(userId);
				user.setDefaultValue();
				user.setCreator(loginUser.getId());
				user.setStatus(UserConstants.USER_STATUS_ENABLE);//正常状态

				//保存user和部门的关联关系
				String deptStr = model.getDeptNames();
				List<String> deptNameList = Arrays.asList(deptStr.split(","));
				List<String> deptIds = convertDeptId(deptNameList, departmentNameMap);
				if(CollectionUtils.isNotEmpty(deptIds)){
					for(String deptId : deptIds){
						UserDept dept = new UserDept();
						dept.setUserId(userId);
						dept.setDeptId(deptId);
						dept.setCreator(loginUserId);
						dept.setDefaultValue();
						userDeptDao.insert(dept);
					}
				}else {
					//找不到部门
					rts = new HSSFRichTextString("填写的部门没有存在");
					resultCell.setCellValue(rts);
					ImportFailModel failModel = new ImportFailModel(model.getRownum() + 1, "填写的部门没有存在");
					failList.add(failModel);
					continue;
				}

				this.create(user, loginUserId);
				String hisId = userHisService.bakUser(userId);//备份用户信息

				//保存userStaff
				BeanUtils.copyProperties(userStaff, user);
				userStaff.setUserId(userId);
				userStaffService.create(userStaff, loginUserId);
				userStaffHisService.bakUserStaff(userId, hisId);//备份用户扩展信息

				/*
				 * 给用户添加角色
				 */
				userRoleService.saveByCode(userId, role.getRoleCode());

				rts = new HSSFRichTextString("导入成功！");
				resultCell.setCellValue(rts);
				successList.add(model);
			} catch (Exception e) {
				log.error(this.getClass(), e);
				rts = new HSSFRichTextString("导入成功失败，请检查数据是否正确");
				resultCell.setCellValue(rts);
				ImportFailModel failModel = new ImportFailModel(model.getRownum() + 1, "插入数据失败，请检查数据是否正确");
				failList.add(failModel);
			}

		}

		/**
		 * 生成文件，供用户下载查看导入结果
		 */
		String id = Identities.uuid2();
		String path = request.getSession().getServletContext().getRealPath("/");

		String fileName = path+ExcelConfig.getProperty("excel.file.path")+id+".xls";
		File outputFolder=new File(path+ExcelConfig.getProperty("excel.file.path"));
		if(!outputFolder.exists()){
			outputFolder.mkdirs();
		}
		File outputFile=new File(fileName);
		if(!outputFile.exists()){
			outputFile.createNewFile();
		}
		OutputStream output = new FileOutputStream(outputFile);
		book.write(output);
		output.close();
		resultMap.put("successList", successList);
		resultMap.put("failList", failList);
		//导出结果的下载路径
		resultMap.put("filePath", ExcelConfig.getProperty("excel.file.path")+id+".xls");
		//导出结果的整体信息
		resultMap.put("message", "成功导入"+successList.size()+"条数据！");

		request.getSession().setAttribute(batchId, resultMap);
		result = batchId;

		return result;
	}

	private List<String> convertDeptId(List<String> deptNameList, Map<String, Department> deptMap) {
		List<String> deptIds = new ArrayList<>();
		for(String name : deptNameList){
			Department dept = deptMap.get(name);
			if(dept != null){
				deptIds.add(dept.getId());
			}
		}
		return deptIds;
	}

	@Override
	public Response regist(HttpServletRequest request,User user,UserStaff userStaff,String checkCode,String checkType,String code) throws IOException {
		/*
		 * 验证验证码
		 */
		boolean correct = false;
		try {
			correct = imageCaptchaService.validateResponseForID(request.getSession().getId(), StringUtils.lowerCase(checkCode));
		} catch (CaptchaServiceException e) {
			log.error("图片验证码验证错误，请重新填写",e);
		}
		if(!correct) {
			return new Response(300, "图片验证码错误");
		}

		/*
		 * 验证用户名或者证件号是否已经存在
		 */
		SearchParam searchParam = new SearchParam();
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("userName", user.getUserName());
//		paramMap.put("paperworkNo", user.getPaperworkNo());
//		paramMap.put("email", user.getEmail());
		boolean notExists = this.checkExistUser(searchParam);
		if(!notExists) {
//			return new Response(300, "用户名/证件号/邮箱重复");
			return new Response(300, "用户名重复");
		}

		/*
		 * 验证填写的验证码和邮箱/手机是否对应
		 */
/*		String checkTypeTitle="邮箱";
		Map<String,String> paramMap2 = new HashMap<String,String>();
		if("email".equals(checkType)){//如果传入的checkType为email，说明选择的是邮箱验证
			paramMap2.put("email", user.getEmail());
			checkTypeTitle="邮箱";
		}
		else{
			paramMap2.put("phone", user.getMobilePhone());
			checkTypeTitle="手机";
		}

		List<Validate> validateList = this.validateService.selectByMap(paramMap2);
		if(validateList.size()==0){//传入的邮箱或手机未有验证码
			return new Response(300, "未向此"+checkTypeTitle+"发送验证码！请重新获取验证码。");
		}else{//如果有就比较两者的验证码是否正确
			String curCode = validateList.get(0).getCode();
			if(!code.equals(curCode)){
				return new Response(300, "填写的"+checkTypeTitle+"验证码错误");
			}
		}*/

		String id = Identities.uuid2();
		Department virtualDept = departmentService.getVirtualDeptByRegionsCode(user.getRegionsCode());//所选择区域的个人机构
		String virtualDeptId = virtualDept.getId();

		user.setId(id);
		//user.setStatus(UserConstants.USER_STATUS_NO_CONFIRM);//待确认
		user.setStatus(UserConstants.USER_STATUS_ENABLE);//有效
		user.setDefaultValue();
		user.setCreator(id);
		this.create(user,id);

		/*
		 * 给用户插入默认的普通用户角色
		 */
		//userRoleService.saveByCode(id, RoleConstant.COMMON);

		//保存用户与机构的关联关系
		UserDept userDept=new UserDept();
		userDept.setUserId(id);
		userDept.setDeptId(virtualDeptId);
		userDept.setDefaultValue();
		userDeptService.create(userDept, id);

		/*
		 * 处理可多选的字段，存入数据库为json格式
		 */
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
		//保存用户的staff信息
		userStaff.setId(Identities.uuid2());
		userStaff.setDefaultValue();
		userStaff.setCreator(id);
		userStaff.setUserId(id);
		userStaffService.create(userStaff,id);


		//逻辑删除此次操作产生的验证码
		deleteValidate(checkType, user);

		//发送邮件到用户的邮箱
		/*try {
			MimeMessage mailMsg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg,true, "UTF-8");
			messageHelper.setTo(user.getEmail());//接收邮箱
			//设置自定义发件人昵称
			String nickname = javax.mail.internet.MimeUtility.encodeText(AppConfig.getProperty("common", "mail.nickname"));
			String username = AppConfig.getProperty("common", "mail.username");
			messageHelper.setFrom(new InternetAddress(nickname+" <"+username+">"));//发送邮箱
			messageHelper.setSubject(AppConfig.getProperty("common", "mail.activate.subject"));//邮件标题

			Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate(AppConfig.getProperty("common", "mail.activate.template"));
			Map<String,Object> args = new HashMap<String,Object>();
			args.put("realName", user.getRealName());
			args.put("userName", user.getUserName());
			String code = DigestUtils.md5DigestAsHex((AppConfig.getProperty("common", "mail.activate.encrypt")+user.getId()).getBytes()).toUpperCase();
			StringBuffer url = request.getRequestURL();
			String domain = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();//获取域名
			String activateUrl = domain+"activate.do?id="+user.getId()+"&code="+code;
			args.put("activateUrl", activateUrl);
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
			messageHelper.setText(html, true);//邮件内容,true 表示启动HTML格式的邮件
			mailSender.send(mailMsg);//发送
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}*/

		return Response.newDefaultResponse();
	}

	//获取忘记密码，并发送邮件
	public Response getPassword(HttpServletRequest request,User user,String checkCode) throws IOException {
		/*
		 * 验证验证码
		 */
		boolean correct = false;
		try {
			correct = imageCaptchaService.validateResponseForID(request.getSession().getId(), StringUtils.lowerCase(checkCode));
		} catch (CaptchaServiceException e) {
			log.error("验证码验证错误",e);
		}
		if(!correct) {
			return new Response(300, "验证码错误");
		}
		/*
		 * 验证邮箱是否存在
		 */
		SearchParam searchParam = new SearchParam();
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("email", user.getEmail());
		List<User> ul=this.list(searchParam);
		if(ul.size()>1) {
			return new Response(300, "该邮箱已注册多次！");
		}else if(ul.size()<1){
			return new Response(300, "该邮箱还没有注册！");
		}
		user=ul.get(0);
		//发送邮件到用户的邮箱
		try {
			MimeMessage mailMsg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg,true, "UTF-8");
			messageHelper.setTo(user.getEmail());//接收邮箱
			//设置自定义发件人昵称
			String nickname = javax.mail.internet.MimeUtility.encodeText(AppConfig.getProperty("common", "mail.nickname"));
			String username = AppConfig.getProperty("common", "mail.username");
			messageHelper.setFrom(new InternetAddress(nickname+" <"+username+">"));//发送邮箱
			messageHelper.setSubject(AppConfig.getProperty("common", "mail.findPassword.subject"));//邮件标题
			Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate(AppConfig.getProperty("common", "mail.findPassword.template"));

			Map<String,Object> args = new HashMap<String,Object>();
			args.put("userName", user.getUserName());
			args.put("password", user.getPasswordPlain());
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
			messageHelper.setText(html, true);//邮件内容,true 表示启动HTML格式的邮件
			mailSender.send(mailMsg);//发送
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return Response.newDefaultResponse();
	}

	@Override
	public String activate(String id,String code) {
		/*
		 * 更新用户到可用状态
		 */
		User user = this.read(id);
		if(null==user) {
			return "用户不存在，操作无效";
		}
		String status = user.getStatus();
		if(!UserConstants.USER_STATUS_NO_CONFIRM.equals(status)) {
			return "操作无效";
		}
		/*
		 * 验证链接
		 */
		String _code = DigestUtils.md5DigestAsHex((AppConfig.getProperty("common", "mail.activate.encrypt")+id).getBytes()).toUpperCase();
		if(!_code.equals(code)) {
			return "无效链接";
		}
		user.setStatus(UserConstants.USER_STATUS_ENABLE);
		((IUserService)AopContext.currentProxy()).update(user,id);

		/*
		 * 给用户插入默认的普通用户角色
		 */
		userRoleService.saveByCode(id, RoleConstant.COMMON);
		return "用户信息确定成功";
	}

	@Override
	public List<User> auditStudentList(SearchParam searchParam,String type) {
		List<User> users= new ArrayList<User>();
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		Map<String,Object> paramMap = searchParam.getParamMap();
		String regionsCode = loginUser.getRegionsCode();
		Regions regions = RegionsUtil.getRegions(regionsCode);
		if(RegionsConstants.REGIONS_LEVEL_COUNTIES==regions.getRegionsLevel().intValue()) {//区县级可以向下审核
			Map<String,Object> map=new HashMap<String,Object>();
			String regionsCodeData=RegionsUtil.getChildNodes(regionsCode);
			String newData=regionsCodeData.replace("[", "").replace("]", "");
			paramMap.put("regionsCodeChildSearch", newData.replaceAll(" ", ""));
			//paramMap.put("regionsCodeChildSearch", new String[]{regionsCode});//包含子区域的标识
		} else {
			paramMap.put("regionsCode",regionsCode);//只列出当前区域向下的
		}
		paramMap.put("type", type);
		users = baseDao.selectAuditStudent(searchParam);
		return users;
	}

	@Override
	public int auditStudent(UserRegisterFlow flow) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = flow.getUserId();
		String loginUserId = loginUser.getId();
		User user = this.read(userId);
		flow.setStatus(UserRegisterFlow.Status.DONE.toString());//标记结束
		flow.setAuditUser(loginUserId);
		flow.setAuditDept(loginUser.getFirstDeptId());
		flow.setAuditTime(new Date());
		/*
		 * 审核通过
		 * 1，根据申请类型，给用户添加对应的角色
		 * 2，根据申请类型，更新ipanther_user表里的rolecode值，如之前已经是教师或者学生，那么角色代码叠加
		 */
		if(FlowConstants.AUDIT_RESULT_SUCCESS.equals(flow.getAuditResult())) {//审核成功，添加对应的权限，更新rolecode
			String roleCode = user.getRoleCode();//用户本身的角色代码
			String studentRoleCode = UserConstants.USER_ROLECODE_STUDENT;
			String teacherRoleCode = UserConstants.USER_ROLECODE_TEACHER;
			String studentTeacherRoleCode = UserConstants.USER_ROLECODE_STUDENT_TEACHER;

			userRoleService.saveByCode(userId, RoleConstant.STUDENT);
			if(teacherRoleCode.equals(roleCode)) {
				user.setRoleCode(studentTeacherRoleCode);
				user.setType(studentTeacherRoleCode);
			} else {
				user.setRoleCode(studentRoleCode);
				user.setType(studentRoleCode);
			}
			user.setStudentStatus(User.StudentStatus.SUCCESS.toString());//已通过
		} else {
			user.setStudentStatus(User.StudentStatus.REJECT.toString());//未通过
		}
		((IUserService)AopContext.currentProxy()).update(user, loginUserId);
		String hisId = userHisService.bakUser(userId);//备份用户信息
		userStaffHisService.bakUserStaff(userId, hisId);//备份用户扩展信息
		return userRegisterFlowService.update(flow, loginUserId);
	}

	@Override
	@CacheEvict(value="iprcache:usercache:userinfo",condition="#user!=null",key="'iprcache:usercache:userinfo:'+#user.id")
	public int save(boolean isAdmin, User user,String roleIds) {
		int count = 0;
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String userId = user.getId();
		if(StringUtils.isEmpty(userId)) {//新增
			userId = Identities.uuid2();
			//如果用户的单位ID为空，则设置成当前人的单位
			if(StringUtils.isEmpty(user.getDeptId())) {
				user.setDeptId(loginUser.getFirstDeptId());
			}
			user.setId(userId);
			//无论是系统管理还是机构管理增加管理员，用户的roleCode都是管理员
			user.setRoleCode(UserConstants.USER_ROLECODE_ADMIN);
			userRoleService.saveByCode(userId, RoleConstant.COMMON);
			user.setStatus(UserConstants.USER_STATUS_ENABLE);
			count = this.create(user, loginUser.getId());
			UserDept userDept=new UserDept();
			userDept.setUserId(user.getId());
			userDept.setDeptId(user.getDeptId());
			userDept.setDefaultValue();
			userDeptService.create(userDept, loginUser.getId());
		} else {//修改，可能修改的是人才的信息，则需要备份
			count = ((IUserService)AopContext.currentProxy()).update(user,loginUser.getId());
			String hisId = userHisService.bakUser(user.getId());//备份用户
			userStaffHisService.bakUserStaff(userId, hisId);//备份用户扩展信息
		}
		/*
		 * 新增管理员
		 */
		if(isAdmin) {
			userRoleService.deleteByUserId(userId);//删掉所拥有的所有角色
			/*
			 * 增加所拥有的角色
			 */
			for (String roleId : roleIds.split(",")) {
				UserRole userRole=new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(roleId);
				userRoleService.insertSelective(userRole, loginUser);
			}
		}
		return count;
	}

	@Override
	public void registTeacherForDept(String userId) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String loginUserId = loginUser.getId();
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(loginUserId);

		/*
		 * 更新用户状态为“审核中”
		 */
		User user = new User();
		user.setId(userId);
		user.setTeacherStatus(User.TeacherStatus.AUDIT.toString());//审核中
		((IUserService)AopContext.currentProxy()).update(user, loginUserId);

		String businessId = Identities.uuid2();
		Map<String, Object> variables = new HashMap<String, Object>();
		//启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(UserConstants.TEACHER_FLOW_DEPT_DEF_KEY, businessId, variables);

		/*
		 * 创建流程主业务数据
		 */
		String procInstId = processInstance.getId();
		UserRegister userRegister = new UserRegister();
		userRegister.setId(businessId);
		userRegister.setUserId(userId);
		userRegister.setDefaultValue();
		userRegister.setType(UserRegister.Type.TEACHER.toString());
		userRegister.setStatus(UserRegister.Status.UNDONE.toString());//正在进行
		userRegister.setProcInstId(procInstId);
		userRegisterService.create(userRegister, loginUserId);
		/*
		 * 跳过第一个步骤
		 */
		Task task=taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
		String taskId = task.getId();
		taskService.claim(taskId, loginUserId);
		taskService.complete(taskId, variables);//系统自动完成
	}

	@Override
	public void auditTeacher(UserRegisterFlow flow,String registerId,String taskId) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String executionId = task.getExecutionId();
		taskService.claim(taskId, loginUser.getId());
		Map<String,Object> variables = new HashMap<String,Object>();
		/**
		 * 日志监听中需要用到的参数
		 */
		runtimeService.setVariable(executionId, FlowConstants.FLOW_AUDIT_RESULT, flow.getAuditResult());
		runtimeService.setVariable(executionId, FlowConstants.FLOW_AUDIT_CONTENT, flow.getAuditContent());
		taskService.complete(taskId, variables);
	}

	@Override
	public void becomeTeacher(DelegateExecution execution) {
		String businessId = execution.getProcessBusinessKey();
		UserRegister register = userRegisterService.read(businessId);
		String userId = register.getUserId();
		User user = this.read(userId);

		String roleCode = user.getRoleCode();
		/*
		 * 如果用户之前是人才，则角色设置为人才+教师，否则设置为教师
		 */
		if(UserConstants.USER_ROLECODE_STUDENT.equals(roleCode)) {
			user.setRoleCode(UserConstants.USER_ROLECODE_STUDENT_TEACHER);
			user.setType(UserConstants.USER_ROLECODE_STUDENT_TEACHER);
		} else {
			user.setRoleCode(UserConstants.USER_ROLECODE_TEACHER);
			user.setType(UserConstants.USER_ROLECODE_TEACHER);
		}

		user.setTeacherStatus(User.TeacherStatus.SUCCESS.toString());
		((IUserService)AopContext.currentProxy()).update(user,userId);
		String hisId = userHisService.bakUser(userId);
		userStaffHisService.bakUserStaff(userId, hisId);
		/*
		 * 插入教师的权限
		 */
		userRoleService.saveByCode(userId, RoleConstant.TEACHER);
	}

	@Override
	public void teacherFlowEndListener(DelegateExecution execution) {
		String executionId = execution.getId();
		String businessId = execution.getProcessBusinessKey();
		UserRegister register = userRegisterService.read(businessId);
		User user = this.read(register.getUserId());
		register.setStatus(UserRegister.Status.DONE.toString());//将注册业务数据更新为已经结束
		/*
		 * 关联最后一次处理日志
		 */
		String tableName = (String) runtimeService.getVariable(executionId, FlowConstants.FLOW_TABLE_NAME);//表名
		String businessColumnName = (String) runtimeService.getVariable(executionId, FlowConstants.FLOW_BUSINESS_COLUMN_NAME);//字段名
		FlowLog flowLog = flowLogService.getLastFlowLog(tableName, businessColumnName, businessId);
		if(null!=flowLog) {
			register.setLastFlowId(flowLog.getId());
		}
		/*
		 * 设置教师的审批状态
		 * 如果不是通过即已经退回
		 */
		if(!User.TeacherStatus.SUCCESS.toString().equals(user.getTeacherStatus())) {
			user.setTeacherStatus(User.TeacherStatus.REJECT.toString());
			((IUserService)AopContext.currentProxy()).update(user,user.getId());
		}

		userRegisterService.update(register);
	}

	@Override
	public List<User> selectTeacherForCMS(SearchParam searchParam) {
		//只看教师信息
		searchParam.getParamMap().put("roleCodes", new String[]{UserConstants.USER_ROLECODE_TEACHER,UserConstants.USER_ROLECODE_STUDENT_TEACHER});
		//设置区域参数
		/*String regionsCodeData=RegionsUtil.getChildNodes(this.getParam(searchParam));
		String newData=regionsCodeData.replace("[", "").replace("]", "");
		searchParam.getParamMap().put("regions", newData.replaceAll(" ", ""));*/
		searchParam.getParamMap().put("regions", this.getParam(searchParam));
		if(null !=searchParam.getParamMap()){
			if(null != searchParam.getParamMap().get("province")){
				searchParam.getParamMap().put("province", searchParam.getParamMap().get("province").toString().matches("\\d{6}") ? searchParam.getParamMap().get("province") : (long)440000);
			}
		}
		return baseDao.selectTeacherForCMS(searchParam);
	}

	public String getParam(SearchParam searchParam){
		Map<String,Object> map=searchParam.getParamMap();
		String province=map.get("province")==null?"":map.get("province").toString();
		String city=map.get("city")==null?"":map.get("city").toString();
		String counties=map.get("counties")==null?"":map.get("counties").toString();
		if(StringUtils.isNotBlank(counties)) return counties;
		if(StringUtils.isNotBlank(city)) return city;
		if(StringUtils.isNotBlank(province)) return province;
		return "";
	}

	@Override
	public List<Map<String, Object>> selectCountRegister(Map<String, Object> map) {
		return baseDao.selectCountRegister(map);
	}
	//设置人才
	@Override
	public void isSetPerson(String isPerson,String userIds){
		String newUserIds=userIds.replaceFirst(",", "");
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String[] userId=newUserIds.split(",");//多选
		for(int i=0;i<userId.length;i++){
			User user=new User();
			user.setId(userId[i]);
			user.setIsPerson(isPerson);
			this.update(user, loginUser.getId());
		}
	}
	//课程审核通过时，对人才或者行业的人员发送课程推送信息
	@Override
	public List<User> selectUserSendEmail(SearchParam searchParam) {
		String userTypes=searchParam.getParamMap().get("userTypes").toString();
		String industryType=searchParam.getParamMap().get("industryType").toString();
		if(StringUtils.isNotEmpty(userTypes)){
			//userTypes = "\""+userTypes.replace(",", "\",\"")+"\"";
			userTypes =userTypes.substring(1, userTypes.length()-1);
			industryType =industryType.substring(1, industryType.length()-1).replace("\"", "");
			searchParam.getParamMap().put("userTypes",Arrays.asList(userTypes.split(",")));
			searchParam.getParamMap().put("industryType",Arrays.asList(industryType.split(",")));
		}
		return baseDao.selectUserSendEmail(searchParam);
	}

	/**
	 * 根据传入的邮箱或手机判断该邮箱或手机是否已经注册过（用于注册验证码发送）
	 */
	@Override
	public List<String> selectByEmailOrPhone(Map<String, String> map) {
		return this.baseDao.selectByEmailOrPhone(map);
	}

	@Override
	public List<User> selectExperts(SearchParam searchParam){
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		Map<String,Object> paramMap = searchParam.getParamMap();
		//只查有学生的标志的
		paramMap.put("types", new String[]{UserConstants.USER_ROLECODE_EXPERT});
		//处理人才分类筛选条件
		String userType = (String) paramMap.get("userType");
		if(StringUtils.isNotEmpty(userType)) {
			userType = "\""+userType.replace(",", "\",\"")+"\"";
			paramMap.put("userTypes",Arrays.asList(userType.split(",")));
		}
		//处理区域多选
		String regionsCode=(String)paramMap.get("regionsCodes");
		if(StringUtils.isNotEmpty(regionsCode)){
			paramMap.put("regionsCodes",Arrays.asList(regionsCode.split(",")));
		}
		String deptType = loginUser.getDeptType();
		/*
		 * 主管机构，可以查看当前区域及向下的
		 * 其它机构只能看到自己机构的
		 */
		if(DepartmentUtils.isAdminCompetent(deptType)) {
			String userRegionsCode = loginUser.getRegionsCode();// 用户区域编码
			String regionsCodeData=RegionsUtil.getChildNodes(userRegionsCode);
			String newData=regionsCodeData.replace("[", "").replace("]", "");
			paramMap.put("regionsCodeChildSearch", newData.replaceAll(" ", ""));
			//	paramMap.put("regionsCodeChildSearch", new String[]{loginUser.getRegionsCode()});
		} else {
			paramMap.put("deptId",loginUser.getFirstDeptId());
		}
		List<User> users = baseDao.selectExpertBySearchParam(searchParam);
		paramMap.put("regionsCodes",regionsCode);
		return users;
	}

	@Override
	public void sendCheckCodeMail(String checkCode, String email) {
		/*
		 * 验证邮箱是否存在
		 */
		SearchParam searchParam = new SearchParam();
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("email", email);
		List<User> ul = this.list(searchParam);
		if(ul.size()>1) {
			throw  new IllegalArgumentException("该邮箱已注册！");
		}

		//发送邮件到用户的邮箱
		try {
			MimeMessage mailMsg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg,true, "UTF-8");
			//接收邮箱
			messageHelper.setTo(email);
			//设置自定义发件人昵称
			String nickname = javax.mail.internet.MimeUtility.encodeText(AppConfig.getProperty("common", "mail.nickname"));
			String username = AppConfig.getProperty("common", "mail.username");
			//发送邮箱
			messageHelper.setFrom(new InternetAddress(nickname+" <"+username+">"));
			//邮件标题
			messageHelper.setSubject(AppConfig.getProperty("common", "mail.checkCodeMail.subject"));
			Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate(AppConfig.getProperty("common", "mail.checkCodeMail.template"));

			Map<String,Object> args = new HashMap<String,Object>();
			args.put("checkCode", checkCode);
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
			//邮件内容,true 表示启动HTML格式的邮件
			messageHelper.setText(html, true);
			//发送
			mailSender.send(mailMsg);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doRegister(RegisterDto register, MultipartFile logo) throws Exception {

		//验证用户名或者证件号是否已经存在
		SearchParam searchParam = new SearchParam();
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("userName", register.getUserName());

		//检查用户名是否重复
		this.checkUserName(register.getUserName());

		String userId = Identities.uuid2();
		String departmentId = Identities.uuid2();
		String staffId = Identities.uuid2();
		Role unitAdminRole = roleService.getRoleByCode("unitAdmin");
		String password = DigestUtils.md5DigestAsHex(register.getPassword().getBytes());

		// 保存附件
		Map<String, String> map = new HashMap<>();
		String reStr = this.saveFile(map, logo, "attachment.default.fileTypes.image",
				"unit/logo/file");

		//用户信息
		User user = new User();
		user.setId(userId);
		user.setUserName(register.getUserName());
		user.setRealName(register.getRealName());
		user.setPasswordPlain(register.getPassword());
		user.setPassword(password);
		user.setEmail(register.getUnitEmail());
		user.setRoleCode(UserConstants.USER_ROLECODE_ADMIN);
		user.setMobilePhone(register.getPhone());
		//有效
		user.setStatus(UserConstants.USER_STATUS_ENABLE);
		user.setDefaultValue();
		user.setCreator(userId);
		baseDao.insertSelective(user);

		//组织信息
		Department department = new Department();
		department.setId(departmentId);
		department.setDeptName(register.getUnitName());
		department.setAdminEmail(register.getUnitEmail());
		department.setLinkEmail(register.getUnitEmail());
		department.setDeptLevel(BigDecimal.ONE);
		department.setDeptCode(register.getCode());
		department.setUnitId(departmentId);
		department.setLinkFax(register.getUnitPhone());
		if (StringUtils.isBlank(reStr) && map.size() > 0) {
			department.setLogo(JsonUtil.toJson(map));
		}
		//临时值
		department.setRegionsCode("350000");
		departmentDao.insertSelective(department);

		//保存用户与机构的关联关系
		UserDept userDept = new UserDept();
		userDept.setUserId(userId);
		userDept.setDeptId(departmentId);
		userDept.setDefaultValue();
		userDept.setCreator(userId);
		userDeptDao.insertSelective(userDept);

		//
		UserStaff userStaff = new UserStaff();
		userStaff.setId(staffId);
		userStaff.setUserId(userId);
		userStaff.setDefaultValue();
		userStaff.setCreator(userId);
		userStaffDao.insertSelective(userStaff);

		//用户的权限
		UserRole role = new UserRole();
		role.setUserId(userId);
		role.setRoleId(unitAdminRole.getId());
		role.setDefaultValue();
		role.setCreator(userId);
		userRoleDao.insertSelective(role);

	}



	@Override
	public List<String> getDeptAdminDeptIds(String userId) {

		return userDeptDao.getDeptIdsByUserId(userId);
	}

	@Override
	public String getDeptAdminDeptIdsStr(String userId) {

		List<String> deptIds = getDeptAdminDeptIds(userId);
		if(CollectionUtils.isNotEmpty(deptIds)){
			String deptIdStr = "";
			for(String deptId: deptIds){
				if(org.apache.commons.lang3.StringUtils.isBlank(deptIdStr)){
					deptIdStr = deptId;
				}else {
					deptIdStr = deptIdStr +  "," + deptId;
				}
			}
			return deptIdStr;
		}
		return "";
	}

	@Override
	public List<String> getDeptIdsByUserId() {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		User user = this.read(loginUser.getId());
		List<String> list = new ArrayList<>();
		if(user != null){

			if(UserConstants.USER_ROLECODE_ADMIN.equals(user.getRoleCode())){
				//当前用户为企业管理员
				//取所有部门
				Department dept = departmentService.read(loginUser.getFirstDeptId());
				List<Department> departments = departmentService.getDepartmentList(dept.getUnitId());
				for(Department department : departments){
					list.add(department.getId());
				}
			}else if(UserConstants.USER_ROLE_CODE_DEPT_ADMIN.equals(user.getRoleCode())) {
				//当前用户为部门管理员
				//取管理的部门
				list = getDeptAdminDeptIds(user.getId());

			}

		}
		return list;
	}

	@Override
	public String getRoleUserId() {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		User user = this.read(loginUser.getId());
		if(UserConstants.USER_ROLE_CODE_EMPLOYEE.equals(user.getRoleCode()) || UserConstants.USER_ROLE_CODE_TEST.equals(user.getRoleCode())){
			return user.getId();
		}
		return null;
	}

	@Override
	public List<String> getUserDeptIds(String id) {
		return userDeptDao.getUserDeptIds(id);
	}

	@Override
	public void checkUserName(String userName) {
		User user = userDao.getByUserName(userName);
		if(user != null){
			throw new IllegalArgumentException("该用户名已被使用!");
		}
	}

	@Override
	public User getUserByOpenId(String openId) {
		return baseDao.getByOpenId(openId);
	}

	@Override
	public User getUserByPhone(String phone) {
		return baseDao.getByPhone(phone);
	}

	@Override
	public List<Map> selectWaitBind(String id) {
		return baseDao.selectWaitBind(id);
	}

	@Override
	public void updateWxUserStatus(String wxUserId, int status) {
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("wxUserId", wxUserId);
		searchParam.getParamMap().put("status", status);
		baseDao.updateWxUserStatus(searchParam);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void bindWx(String openId, String wxUserId) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		User user = new User();
		user.setId(loginUser.getId());
		user.setWechatId(openId);
		this.update(user, user.getId());
		this.updateWxUserStatus(wxUserId, 2);
	}

    @Override
    public boolean deleteAble(LoginUser loginUser, User user) {
        if(UserConstants.USER_ROLECODE_SUPER_ADMIN.equals(loginUser.getRoleCode())){
        	return true;
		}else if(UserConstants.USER_ROLECODE_ADMIN.equals(loginUser.getRoleCode())){
        	return true;
		}else if(UserConstants.USER_ROLE_CODE_DEPT_ADMIN.equals(loginUser.getRoleCode())){

        	List<String> userDeptIds =  userDeptDao.getDeptIdsByUserId(user.getId());
        	List<String> loginUserDeptIds = loginUser.getDeptIds();
			Set<String> temp = new HashSet<>();
			temp.addAll(userDeptIds);
			temp.addAll(loginUserDeptIds);
			if(temp.size() < (userDeptIds.size() + loginUserDeptIds.size())){
				return true;
			}
			return false;
		}

        return false;
    }

    /**
	 * 机构注册，自动关联到机构的用户
	 */
	@Override
	public Response unitRegist(HttpServletRequest request,User user,UserStaff userStaff,Department department, String checkCode,String checkType,String code) throws IOException {
		/*
		 * 验证验证码
		 */
		boolean correct = false;
		try {
			correct = imageCaptchaService.validateResponseForID(request.getSession().getId(), StringUtils.lowerCase(checkCode));
		} catch (CaptchaServiceException e) {
			log.error("图片验证码验证错误，请重新填写",e);
		}
		if(!correct) {
			return new Response(300, "图片验证码错误");
		}

		/*
		 * 验证用户名或者证件号是否已经存在
		 */
		SearchParam searchParam = new SearchParam();
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("userName", user.getUserName());
//		paramMap.put("paperworkNo", user.getPaperworkNo());
//		paramMap.put("email", user.getEmail());
		boolean notExists = this.checkExistUser(searchParam);
		if(!notExists) {
//			return new Response(300, "用户名/证件号/邮箱重复");
			return new Response(300, "用户名重复");
		}

		/*
		 * 验证填写的验证码和邮箱/手机是否对应
		 */
/*		String checkTypeTitle="邮箱";
		Map<String,String> paramMap2 = new HashMap<String,String>();
		if("email".equals(checkType)){//如果传入的checkType为email，说明选择的是邮箱验证
			paramMap2.put("email", user.getEmail());
			checkTypeTitle="邮箱";
		}
		else{
			paramMap2.put("phone", user.getMobilePhone());
			checkTypeTitle="手机";
		}

		List<Validate> validateList = this.validateService.selectByMap(paramMap2);
		if(validateList.size()==0){//传入的邮箱或手机未有验证码
			return new Response(300, "未向此"+checkTypeTitle+"发送验证码！请重新获取验证码。");
		}else{//如果有就比较两者的验证码是否正确
			String curCode = validateList.get(0).getCode();
			if(!code.equals(curCode)){
				return new Response(300, "填写的"+checkTypeTitle+"验证码错误");
			}
		}*/
		String registerCode = department.getProvince();
		String realName = user.getUserName();
		String id = Identities.uuid2();
		user.setId(id);
		user.setRegionsCode(registerCode);
		user.setRegionsCode(realName);
		user.setHometownProvince(department.getProvince());
		user.setHometownCity(department.getCity());
		user.setCurrentProvince(department.getProvince());
		user.setCurrentCity(department.getCity());
		user.setCurrentCounties(department.getCounties());
		//user.setStatus(UserConstants.USER_STATUS_NO_CONFIRM);//待确认
		user.setStatus(UserConstants.USER_STATUS_ENABLE);//有效
		user.setDefaultValue();
		user.setCreator(id);
		this.create(user,id);


//		Department virtualDept = departmentService.getVirtualDeptByRegionsCode(department.getRegionsCode());//所选择区域的个人机构
//		String virtualDeptId = virtualDept.getId();
//
		department.setId(id);
		department.setRegionsCode(registerCode);
		user.setCreator(id);
		departmentService.create(department, id);


		/*
		 * 给用户插入默认的普通用户角色
		 */
		//userRoleService.saveByCode(id, RoleConstant.COMMON);

		//保存用户与机构的关联关系
		UserDept userDept=new UserDept();
		userDept.setUserId(id);
		userDept.setDeptId(id);
		userDept.setDefaultValue();
		userDeptService.create(userDept, id);

		/*
		 * 处理可多选的字段，存入数据库为json格式
		 */
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
		//保存用户的staff信息
		userStaff.setId(Identities.uuid2());
		userStaff.setDefaultValue();
		userStaff.setCreator(id);
		userStaff.setUserId(id);
		userStaffService.create(userStaff,id);

		//逻辑删除此次操作产生的验证码
		deleteValidate(checkType, user);

		//发送邮件到用户的邮箱
		/*try {
			MimeMessage mailMsg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg,true, "UTF-8");
			messageHelper.setTo(user.getEmail());//接收邮箱
			//设置自定义发件人昵称
			String nickname = javax.mail.internet.MimeUtility.encodeText(AppConfig.getProperty("common", "mail.nickname"));
			String username = AppConfig.getProperty("common", "mail.username");
			messageHelper.setFrom(new InternetAddress(nickname+" <"+username+">"));//发送邮箱
			messageHelper.setSubject(AppConfig.getProperty("common", "mail.activate.subject"));//邮件标题

			Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate(AppConfig.getProperty("common", "mail.activate.template"));
			Map<String,Object> args = new HashMap<String,Object>();
			args.put("realName", user.getRealName());
			args.put("userName", user.getUserName());
			String code = DigestUtils.md5DigestAsHex((AppConfig.getProperty("common", "mail.activate.encrypt")+user.getId()).getBytes()).toUpperCase();
			StringBuffer url = request.getRequestURL();
			String domain = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();//获取域名
			String activateUrl = domain+"activate.do?id="+user.getId()+"&code="+code;
			args.put("activateUrl", activateUrl);
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
			messageHelper.setText(html, true);//邮件内容,true 表示启动HTML格式的邮件
			mailSender.send(mailMsg);//发送
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}*/

		return Response.newDefaultResponse();
	}

	@Override
	public List<User> listEmployees(SearchParam searchParam) {
		Map<String,Object> paramMap = searchParam.getParamMap();
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		/*
		 * 其它只能管理自己单位的
		 * 系统管理员、超级管理员可以管理所有
		 */
		if(!SecurityRoleUtils.isSystemAdmin(loginUser.getId())) {
			//paramMap.put("deptId", loginUser.getDeptId());
			//标识不是管理，页面上判断用
			paramMap.put("isSystemAdmin", false);
		} else {
			//标识不是管理，页面上判断用
			paramMap.put("isSystemAdmin", true);
		}
		if(!UserConstants.USER_ROLECODE_SUPER_ADMIN.equals(loginUser.getRoleCode())) {
			//非超级管理
			//列出不包括rolecode=0的超级管理员
			searchParam.getParamMap().put("notRoleCode", "0");
		}

		List<User> list = baseDao.selectUserList(searchParam);
		List<String> userIds = new ArrayList<>();

		for (User user : list) {
			userIds.add(user.getId());
		}

		Map<String, List<UserDept>> map = userDeptService.getUsersDept(userIds);
		for (User user : list) {
			user.setDeptName(
					userDeptService.getUserDeptNameStr(
							map.get(user.getId())
					)
			);
		}

		return list;
	}

	public String saveFile(Map<String, String> map, MultipartFile file,String fileTypes,String fileDir){
		if(file!=null){
			if(!file.isEmpty()){
				String tempFileName= FileUtils.getTempDirectory().getPath()+"/"+file.getName();
				File tempFile=new File(tempFileName);
				try {
					file.transferTo(tempFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ReceiveParam receiveParam= setReceiveParam(fileTypes,"attachment.default.fileMaxSize",fileDir);
				String info = attachmentService.validateAttachment(tempFile,file.getOriginalFilename(), receiveParam);
				if(org.apache.commons.lang3.StringUtils.isNotEmpty(info)){
					return info;
				}
				SendParam sendParam =null;
				sendParam = attachmentService.uploadAttachment(tempFile,file.getOriginalFilename(),receiveParam,"");
				if(sendParam==null){
					return CommonConfig.getProperty("attachment.upload.error");
				}else{

					map.put("attachmentId", sendParam.getAttachmentId());
					map.put("attachmentName", sendParam.getAttachmentName());
					map.put("downloadUrl", sendParam.getDownloadUrl());
					map.put("fileId", sendParam.getFileId());
					map.put("billId", sendParam.getBillId());
					map.put("groupId", sendParam.getGroupId());
					map.put("status", sendParam.getStatus());
				}
			}
		}
		return null;
	}

	private static ReceiveParam setReceiveParam(String fileTypesPropertyName,String fileMaxSize,String fileDir) {
		String uuid = ObjectUtils.toString(UUID.randomUUID());
		String id= org.apache.commons.lang3.StringUtils.remove(uuid,"-");
		ReceiveParam param=new ReceiveParam();
		param.setAttachmentId(id);
		param.setFileDir(fileDir);
		param.setFileMaxSize(Integer.parseInt(CommonConfig.getProperty(fileMaxSize)));
		param.setCallbackUrl("callbackUrl");
		param.setBillId(id);
		param.setFileTypes(CommonConfig.getProperty(fileTypesPropertyName));
		return param;
	}

}
