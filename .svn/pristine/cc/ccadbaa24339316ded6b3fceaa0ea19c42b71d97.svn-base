package com.hcis.ipr.train.course.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.call.dao.ProcedureDao;
import com.hcis.ipr.intellectual.call.entity.PatentType;
import com.hcis.ipr.train.course.dao.CourseDao;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.course.utils.CourseConstants;

import freemarker.template.Template;
import net.sf.json.JSONArray;

@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<Course> implements ICourseService {
	private final static Log log = LogFactory.getLog(CourseServiceImpl.class);

	@Autowired
	private ProcedureDao procedureDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Resource(name = "mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer;
	@Autowired
	private IUserService userService;

	// 启动流程
	public ProcessInstance startWorkFlow(Course course, HttpServletRequest request) {
		LoginUser loginUser = LoginUser.loginUser(request);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(CourseConstants.COURSE_TRAIN_USER_ID, loginUser.getId());
		// identityService.setAuthenticatedUserId(loginUser.getId());
		return runtimeService.startProcessInstanceByKey(CourseConstants.COURSE_INSTANCE_KEY, course.getId(), variables);
	}

	// 删除流程
	@Override
	public void deleteWorkFlow(String procInstId) {
		runtimeService.deleteProcessInstance(procInstId, "");
		historyService.deleteHistoricProcessInstance(procInstId);
	}

	// 领导审核
	public void audit(Course course, HttpServletRequest request, SearchParam searchParam) {
		LoginUser loginUser = LoginUser.loginUser(request);
		Map<String, Object> variables = new HashMap<String, Object>();
		Map<String, Object> paramMap = searchParam.getParamMap();
		// 用于监听中判断操作类型
		Task task = taskService.createTaskQuery().processInstanceId(course.getProcInstId()).singleResult();

		// 设置监听中可能会用到的参数
		runtimeService.setVariable(task.getExecutionId(), CourseConstants.COURSE_VARIABLE_PARAM_MAP, paramMap);
		runtimeService.setVariable(task.getExecutionId(), CourseConstants.COURSE_VARIABLE_LOGIN_USER, loginUser);
		runtimeService.setVariable(task.getExecutionId(), "course", course);
		// 签收
		try {
			taskService.claim(task.getId(), loginUser.getId());
		} catch (ActivitiTaskAlreadyClaimedException e) {
			taskService.unclaim(task.getId());
			taskService.claim(task.getId(), loginUser.getId());
		}
		// 若退回，设置接收人
		if (StringUtils.equals(paramMap.get("status").toString(), CourseConstants.COURSE_ISPASS)) {
			variables.put(CourseConstants.COURSE_TRAIN_USER_ID, course.getDeclareUser());
		}
		// 若为培训机构审核的节点，则加上广东省知识产权机构Id
		if (task.getTaskDefinitionKey().equals("trainLeaderConfirm")) {
			variables.put(CourseConstants.COURSE_IPR_DEPT_ID, CourseConstants.COURSE_IPR_DEPT_ID_VALUE);
			variables.put("status", paramMap.get("status").toString());
		}
		// 若为知识产权机构的节点
		if (task.getTaskDefinitionKey().equals("iprLeaderConfirm")) {
			if (StringUtils.equals(paramMap.get("status").toString(), CourseConstants.COURSE_ISPASS)) {
				variables.put("status", paramMap.get("status").toString());
			} else {
				variables.put("status", "01");
			}
		}
		// 判断是否超过申请次数，若超过申请次数，status设为不通过标示：01通过，03不通过，02退回
		this.setStatus(course, variables, task.getTaskDefinitionKey());
		if (task.getTaskDefinitionKey().equals("iprLeaderConfirm")
				&& course.getApplyCount().compareTo(course.getMaxApplyCount()) == -1) {
			if (StringUtils.equals(paramMap.get("status").toString(), CourseConstants.COURSE_STATUS_01)) {
				this.sendEmeilByCourse(course, request);
			}

		}
		taskService.complete(task.getId(), variables);
	}

	// 申报课程
	public void declareCourse(Course course, HttpServletRequest request) {
		course = this.read(course.getId());
		// 若是新增的申报，则启动一个流程
		if (course.getStatus().equals("00")) {
			ProcessInstance processInstance = this.startWorkFlow(course, request);
			course.setProcInstId(processInstance.getId());
			this.update(course, LoginUser.loginUser(request).getId());
		}
		LoginUser loginUser = LoginUser.loginUser(request);
		Map<String, Object> variables = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Task task = taskService.createTaskQuery().processInstanceId(course.getProcInstId()).singleResult();
		// 设置监听中可能会用到的参数
		runtimeService.setVariable(task.getExecutionId(), CourseConstants.COURSE_VARIABLE_PARAM_MAP, paramMap);
		runtimeService.setVariable(task.getExecutionId(), CourseConstants.COURSE_VARIABLE_LOGIN_USER, loginUser);
		runtimeService.setVariable(task.getExecutionId(), "course", course);
		// 签收
		taskService.claim(task.getId(), loginUser.getId());
		// 候选为申报人所在部门的领导
		variables.put(CourseConstants.COURSE_TRAIN_DEPT_ID, loginUser.getFirstDeptId());
		taskService.complete(task.getId(), variables);
	}

	// 所有课程
	public List<Map<String, Object>> listAll(SearchParam searchParam, HttpServletRequest request) {
		searchParam.getParamMap().put("userId", LoginUser.loginUser(request).getId());
		return courseDao.listAll(searchParam);
	}

	// 待办任务
	public List<Map<String, Object>> listTodo(SearchParam searchParam, HttpServletRequest request) {
		LoginUser loginUser = LoginUser.loginUser(request);
		String roleStr = this.getRolesStr(loginUser);
		// 根据角色获取任务
		/*
		 * if(StringUtils.contains(roleStr, RoleConstant.GD_IPR_LEADER)){
		 * searchParam.getParamMap().put("candidateId",
		 * CourseConstants.COURSE_IPR_DEPT_ID_VALUE); }else
		 * if(StringUtils.contains(roleStr, RoleConstant.TRAIN_LEADER)){
		 * searchParam.getParamMap().put("candidateId", loginUser.getDeptId());
		 * }else{ searchParam.getParamMap().put("candidateId",
		 * loginUser.getId()); }
		 */
		// 根据角色获取任务
		List candidateIdList = new ArrayList();
		if (StringUtils.contains(roleStr, RoleConstant.GD_IPR_LEADER)) {
			candidateIdList.add(CourseConstants.COURSE_IPR_DEPT_ID_VALUE);
		}
		if (StringUtils.contains(roleStr, RoleConstant.TRAIN_LEADER)) {
			candidateIdList.add(loginUser.getFirstDeptId());
		}
		if (StringUtils.contains(roleStr, RoleConstant.TRAIN_BUSINESS)) {
			candidateIdList.add(loginUser.getId());
		}
		if (candidateIdList.size() == 0) {
			candidateIdList.add("noRole");
		}
		searchParam.getParamMap().put("candidateIdList", candidateIdList);
		return courseDao.listTodo(searchParam);
	};

	// 已办任务
	public List<Map<String, Object>> listDone(SearchParam searchParam, HttpServletRequest request) {
		searchParam.getParamMap().put("userId", LoginUser.loginUser(request).getId());
		return courseDao.listDone(searchParam);
	}

	// 上传图片
	@Override
	public String uploadImg(Course course, MultipartFile file) {
		return upload(course, file, "img", "attachment.default.fileTypes.image", "course/img/");
	}

	// 上传文件
	@Override
	public String uploadFile(Course course, MultipartFile file) {
		return upload(course, file, "file", "attachment.default.fileTypes", "course/file/");
	}

	// 上传主讲教师头像
	@Override
	public String uploadAvatar(Course course, MultipartFile file) {
		return upload(course, file, "avatar", "attachment.default.fileTypes.image", "course/avatar/");
	}

	// 个人空间教师专题课程
	public List<Map<String, Object>> listCourse(SearchParam searchParam) {
		return courseDao.listCourse(searchParam);
	}

	// 已经分配了培训班，且可以选分配教师的课程列表
	@Override
	public List<Map<String, Object>> listTrainCoure(SearchParam searchParam) {
		return courseDao.listTrainCoure(searchParam);
	}

	// 前端课程报名列表
	public List<Map<String, Object>> selectCourseTrain(SearchParam searchParam) {
		return courseDao.selectCourseTrain(searchParam);
	}

	// 前端课程列表
	public List<Map<String, Object>> selectTalentCourse(SearchParam searchParam) {
		return courseDao.selectTalentCourse(searchParam);
	}

	// 前端人才培训系统栏目首页培训基地选项
	public List<Map<String, Object>> selectTrain(SearchParam searchParam) {
		return courseDao.selectTrain(searchParam);
	}

	// 首页4条课程数据
	public List<Map<String, Object>> selectCourseHome(SearchParam searchParam) {
		return courseDao.selectCourseHome(searchParam);
	}

	// 单文件上传
	public String upload(Course course, MultipartFile file, String type, String fileTypes, String fileDir) {
		Map<String, String> map = new HashMap<String, String>();
		String info = null;
		info = this.saveFile(map, file, fileTypes, fileDir);
		if ((!map.isEmpty())) {
			try {
				if (type.equals("img")) {
					course.setCourseImg(JsonUtil.toJson(map));
				} else if (type.equals("file")) {
					course.setAttachment(JsonUtil.toJson(map));
				} else if (type.equals("avatar")) {
					course.setTeacherAvatar(JsonUtil.toJson(map));
				}
			} catch (Exception e) {
				info = "上传附件失败";
				log.error(this.getClass(), e);
			}
		}
		return info;
	}

	// 专题多文件上传
	public String uploadExpertFile(SearchParam searchParam, MultipartFile[] files, HttpServletRequest request) {
		LoginUser loginUser = LoginUser.loginUser(request);
		Course course = new Course();
		List list = new ArrayList();
		Map<String, String> map = null;
		String info = "";
		JSONArray jsonArray = null;
		// 检测上传文件和已有的文件数量是否大于等于50
		Course courseOld = this.read(searchParam.getParamMap().get("courseId").toString());
		if (null != courseOld.getExpertAttachment() && courseOld.getExpertAttachment().startsWith("[")) {
			jsonArray = JSONArray.fromObject(courseOld.getExpertAttachment());
		}
		if (null != jsonArray && (jsonArray.size() + files.length) > 50) {
			return "上传附件总数已经超过50个！";
		}
		// 循环上传文件
		if (null != files && files.length > 0) {
			String fileStr = "";
			for (int i = 0; i < files.length; i++) {
				map = new HashMap<String, String>();
				info = this.saveFile(map, files[i], "attachment.default.fileTypes", "course/expertFile/");
				if (StringUtils.isNotEmpty(info)) {
					return info;
				} else {
					if (map.get("attachmentId") != null) {
						list.add(map.get("attachmentId"));
					}
				}
			}
			try {
				if (null != jsonArray) {
					for (Object id : list) {
						jsonArray.add(id.toString());
					}
					fileStr = jsonArray.toString();
				} else {
					fileStr = JsonUtil.toJson(list);
				}
			} catch (IOException e) {
				info = "上传附件失败";
				log.error(this.getClass(), e);
			}
			// 把上传的附件添加到已经上传的json附件数组里
			course.setId(searchParam.getParamMap().get("courseId").toString());
			course.setExpertAttachment(fileStr);
			this.update(course, loginUser.getId());
		}
		return info;
	}

	// 删除专题附件
	public int deleteExpertFile(SearchParam searchParam, HttpServletRequest request) {
		Map<String, Object> param = searchParam.getParamMap();
		String attachmentId = param.get("attachmentId").toString();
		String courseId = param.get("courseId").toString();
		int count = 0;
		// 删除附件
		Attachment attachment = new Attachment();
		attachment.setId(attachmentId);
		count = attachmentService.deleteAttachment(attachment, LoginUser.loginUser(request).getId());
		// 移除课程里专家字段的附件ID
		Course courseOld = this.read(courseId);
		if (null != courseOld.getExpertAttachment() && courseOld.getExpertAttachment().startsWith("[")) {
			JSONArray jsonArray = JSONArray.fromObject(courseOld.getExpertAttachment());
			jsonArray.remove(attachmentId);
			Course c = new Course();
			c.setId(courseId);
			c.setExpertAttachment(jsonArray.toString());
			this.update(c, LoginUser.loginUser(request).getId());
		}
		return count;
	}

	@Override
	public MyBatisDao getBaseDao() {
		return courseDao;
	}

	public static ReceiveParam setReceiveParam(String fileTypesPropertyName, String fileMaxSize, String fileDir) {
		String uuid = ObjectUtils.toString(UUID.randomUUID());
		String id = StringUtils.remove(uuid, "-");
		ReceiveParam param = new ReceiveParam();
		param.setAttachmentId(id);
		param.setFileDir(fileDir);
		param.setFileMaxSize(Integer.parseInt(CommonConfig.getProperty(fileMaxSize)));
		param.setCallbackUrl("callbackUrl");
		param.setBillId(id);
		param.setFileTypes(CommonConfig.getProperty(fileTypesPropertyName));
		return param;
	}

	public String getRolesStr(LoginUser loginUser) {
		StringBuffer sb = new StringBuffer();
		sb.append("'");
		List<Role> list = loginUser.getRoleList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i).getRoleCode()).append("','");
				} else {
					sb.append(list.get(i).getRoleCode()).append("'");
				}
			}
		} else {
			sb.append("'");
		}
		return sb.toString();
	}

	public void setStatus(Course course, Map<String, Object> variables, String taskDefinitionKey) {
		if (course.getApplyCount() != null) {
			if (course.getApplyCount().compareTo(course.getMaxApplyCount()) == 1) {
				if (taskDefinitionKey.equals("iprLeaderConfirm")) {
					variables.put("status", "01");
				} else {
					variables.put("status", "03");
				}
			}
		}
	}

	// 保存上传的文件
	public String saveFile(Map<String, String> map, MultipartFile file, String fileTypes, String fileDir) {
		if (file != null) {
			if (!file.isEmpty()) {
				String tempFileName = FileUtils.getTempDirectory().getPath() + "/" + file.getName();
				File tempFile = new File(tempFileName);
				try {
					file.transferTo(tempFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ReceiveParam receiveParam = setReceiveParam(fileTypes, "attachment.default.fileMaxSize", fileDir);
				String info = attachmentService.validateAttachment(tempFile, file.getOriginalFilename(), receiveParam);
				if (StringUtils.isNotEmpty(info)) {
					return info;
				}
				SendParam sendParam = null;
				sendParam = attachmentService.uploadAttachment(tempFile, file.getOriginalFilename(), receiveParam, "");
				if (sendParam == null) {
					return CommonConfig.getProperty("attachment.upload.error");
				} else {

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

	// 课程审核通过后向报名人员的邮箱定向推送消息
	public void sendEmeilByCourse(Course course, HttpServletRequest request) {
		SearchParam searchParam = new SearchParam();
		Course courses = this.read(course.getId());

		searchParam.getParamMap().put("userTypes", courses.getPersonType());
		searchParam.getParamMap().put("industryType", courses.getIndustryType());
		List<User> users = userService.selectUserSendEmail(searchParam);
		for (User user : users) {
			// 邮箱模板的信息
			String realName = user.getUserName();
			String email = user.getEmail();
			String courseName = courses.getCourseName();
			String courseIntro = courses.getCourseIntro();
			String teacherName = courses.getTeacherName();
			Date startTime = courses.getStartTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String newStartTime = dateFormat.format(startTime);
			try {
				MimeMessage mailMsg = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg, true, "UTF-8");
				messageHelper.setTo(email);// 接收邮箱
				// 设置自定义发件人昵称
				String nickname = javax.mail.internet.MimeUtility
						.encodeText(AppConfig.getProperty("common", "mail.nickname"));
				String username = mailSender.getUsername();
				messageHelper.setFrom(new InternetAddress(nickname + " <" + username + ">"));// 发送邮箱
				messageHelper.setSubject("培训课程推送消息");// 邮件标题
				Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate("sendEmailCourseInfo.ftl");// 内容模板
				Map<String, Object> args = new HashMap<String, Object>();
				StringBuffer url = request.getRequestURL();
				String domain = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/")
						.toString();// 获取域名
				String courseUrl = domain + "site/train/detail.do?id=" + courses.getId();
				Calendar cal = Calendar.getInstance();
				// args.put("now", cal.getTime());// 当前时间
				args.put("realName", realName);
				args.put("courseName", courseName);
				args.put("courseIntro", courseIntro);
				args.put("teacherName", teacherName);
				args.put("startTime", newStartTime);
				args.put("courseUrl", courseUrl);
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
				messageHelper.setText(html, true);// 邮件内容,true 表示启动HTML格式的邮件
				mailSender.send(mailMsg);// 发送
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	@Override
	@Transactional(readOnly = false)
	public List<PatentType> getPatentType(String ID) {
		/* List<PatentCost> patentcost = procedureDao.getPatentCost(); */
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("ID", ID);
		return procedureDao.getPatentTypeList(ID);

	}
}
