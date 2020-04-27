package com.hcis.ipr.train.project.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

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
import com.hcis.ipr.train.course.dao.CourseDao;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.utils.CourseConstants;
import com.hcis.ipr.train.project.dao.ProjectDao;
import com.hcis.ipr.train.project.entity.Project;
import com.hcis.ipr.train.project.service.IProjectService;
import com.hcis.ipr.train.project.utils.ProjectConstants;
import com.hcis.ipr.train.train.dao.TrainCourseDao;
import com.hcis.ipr.train.train.dao.TrainDao;
import com.hcis.ipr.train.train.entity.Train;

import freemarker.template.Template;

@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements IProjectService{

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Resource(name="mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer;
	@Autowired
	private IUserService userService;
	@Autowired
	private TrainDao trainDao;
	@Autowired
	private TrainCourseDao trainCourseDao;
	@Autowired
	private CourseDao courseDao;
	
	//启动流程
	public ProcessInstance  startWorkFlow(Project project,HttpServletRequest request){
		LoginUser loginUser=LoginUser.loginUser(request);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProjectConstants.PROJECT_TRAIN_USER_ID,loginUser.getId());
		return runtimeService.startProcessInstanceByKey(ProjectConstants.PROJECT_INSTANCE_KEY,project.getId(),variables);
	}
	
	//删除流程
	public void deleteWorkFlow(String procInstId) {
		runtimeService.deleteProcessInstance(procInstId, "");
		historyService.deleteHistoricProcessInstance(procInstId);
	}
	
	//申报项目
	public void declareCourse(Project project,HttpServletRequest request){
		project=this.read(project.getId());
		//若是新增的申报，则启动一个流程
		if(project.getStatus().equals("00")){
			ProcessInstance processInstance=this.startWorkFlow(project, request);
			project.setProcInstId(processInstance.getId());
			this.update(project, LoginUser.loginUser(request).getId());
		}
		
		LoginUser loginUser=LoginUser.loginUser(request);
		Map<String, Object> variables = new HashMap<String, Object>();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		Task task=taskService.createTaskQuery().processInstanceId(project.getProcInstId()).singleResult();
		//设置监听中可能会用到的参数
		runtimeService.setVariable(task.getExecutionId(),ProjectConstants.PROJECT_VARIABLE_PARAM_MAP, paramMap);
		runtimeService.setVariable(task.getExecutionId(),ProjectConstants.PROJECT_VARIABLE_LOGIN_USER, loginUser);
		runtimeService.setVariable(task.getExecutionId(),"project", project);
		//签收
		taskService.claim(task.getId(), loginUser.getId());
//		//候选为申报人所在部门的领导
//		variables.put(ProjectConstants.PROJECT_TRAIN_DEPT_ID,loginUser.getDeptId());
		variables.put( ProjectConstants.PROJECT_IPR_DEPT_ID, ProjectConstants.PROJECT_IPR_DEPT_ID_VALUE);
		taskService.complete(task.getId(), variables);
	}
	
	//领导审核
	public void audit(Project project,HttpServletRequest request,SearchParam searchParam){
		LoginUser loginUser=LoginUser.loginUser(request);
		Map<String, Object> variables = new HashMap<String, Object>();
		Map<String,Object> paramMap=searchParam.getParamMap();
		//用于监听中判断操作类型
		Task task=taskService.createTaskQuery().processInstanceId(project.getProcInstId()).singleResult();
		
		//设置监听中可能会用到的参数
		runtimeService.setVariable(task.getExecutionId(),ProjectConstants.PROJECT_VARIABLE_PARAM_MAP, paramMap);
		runtimeService.setVariable(task.getExecutionId(),ProjectConstants.PROJECT_VARIABLE_LOGIN_USER, loginUser);
		runtimeService.setVariable(task.getExecutionId(),"project", project);
		//签收
		try{
			taskService.claim(task.getId(), loginUser.getId());
		}catch(ActivitiTaskAlreadyClaimedException e){
			taskService.unclaim(task.getId());
			taskService.claim(task.getId(), loginUser.getId());
		} 
        //若退回，设置接收人
       if(StringUtils.equals(paramMap.get("status").toString(), ProjectConstants.PROJECT_ISPASS)){
        	variables.put(ProjectConstants.PROJECT_TRAIN_USER_ID, project.getDeclareUser());
        }
        //若为培训机构审核的节点，则加上广东省知识产权机构Id
        if(task.getTaskDefinitionKey().equals("trainLeaderConfirm")){
        	variables.put( ProjectConstants.PROJECT_IPR_DEPT_ID, ProjectConstants.PROJECT_IPR_DEPT_ID_VALUE);
        	variables.put("status", paramMap.get("status").toString());
        }
        //若为知识产权机构的节点
        if(task.getTaskDefinitionKey().equals("iprLeaderConfirm")){
        	if(StringUtils.equals(paramMap.get("status").toString(), ProjectConstants.PROJECT_ISPASS)){
        		 variables.put("status", paramMap.get("status").toString());
        	}else{
        		variables.put("status", "01");
        	}
        }
        //判断是否超过申请次数，若超过申请次数，status设为不通过标示：01通过，03不通过，02退回
        this.setStatus(project, variables, task.getTaskDefinitionKey());
        if(task.getTaskDefinitionKey().equals("iprLeaderConfirm")&&project.getApplyCount().compareTo(project.getMaxApplyCount())==-1){
        	if(StringUtils.equals(paramMap.get("status").toString(), CourseConstants.COURSE_STATUS_01)){
        		this.sendEmeilByProject(project,request);
        	}
        	
        }
        taskService.complete(task.getId(), variables);
	}
	
	//待办任务
	public List<Map<String,Object>> listTodo(SearchParam searchParam,HttpServletRequest request){
		LoginUser loginUser=LoginUser.loginUser(request);
		String roleStr=this.getRolesStr(loginUser);
		//根据角色获取任务
		/*if(StringUtils.contains(roleStr, RoleConstant.GD_IPR_LEADER)){
			searchParam.getParamMap().put("candidateId", CourseConstants.COURSE_IPR_DEPT_ID_VALUE);
		}else if(StringUtils.contains(roleStr, RoleConstant.TRAIN_LEADER)){
			searchParam.getParamMap().put("candidateId", loginUser.getDeptId());
		}else{
			searchParam.getParamMap().put("candidateId", loginUser.getId());
		}*/ 
		//根据角色获取任务
		List candidateIdList=new ArrayList();
		if(StringUtils.contains(roleStr, RoleConstant.GD_IPR_LEADER)){
			candidateIdList.add(CourseConstants.COURSE_IPR_DEPT_ID_VALUE);
		}
		if(StringUtils.contains(roleStr, RoleConstant.TRAIN_LEADER)){
			candidateIdList.add(loginUser.getFirstDeptId());
		}
		if(StringUtils.contains(roleStr, RoleConstant.TRAIN_BUSINESS)){
			candidateIdList.add(loginUser.getId());
		}
		if(candidateIdList.size()==0){
			candidateIdList.add("noRole");
		}
		searchParam.getParamMap().put("candidateIdList", candidateIdList);
		return projectDao.listTodo(searchParam);
	};
	
	//所有项目
	public List<Map<String,Object>> listAll(SearchParam searchParam,HttpServletRequest request){
		searchParam.getParamMap().put("userId", LoginUser.loginUser(request).getId());
		return projectDao.listAll(searchParam);
	}
	
	//已办任务
	public List<Map<String,Object>> listDone(SearchParam searchParam,HttpServletRequest request){
		searchParam.getParamMap().put("userId", LoginUser.loginUser(request).getId());
		return projectDao.listDone(searchParam);
	}
	
	//上传附件
	@Override
	public String uploadFile(Project project, MultipartFile file) {
		 return upload( project,  file, "file","attachment.default.fileTypes","project/file/");
	}
	
	public String upload(Project project, MultipartFile file,String type,String fileTypes,String fileDir) {
		Map<String, String> map=new HashMap<String, String>();
		if(file!=null){
	        if(!file.isEmpty()){  
				String tempFileName=FileUtils.getTempDirectory().getPath()+"/"+file.getName();
				File tempFile=new File(tempFileName);
				try {
					file.transferTo(tempFile);//传送到指定文件路径
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ReceiveParam receiveParam= setReceiveParam(fileTypes,"attachment.default.fileMaxSize",fileDir);
				String info = attachmentService.validateAttachment(tempFile,file.getOriginalFilename(), receiveParam);
	        	if(StringUtils.isNotEmpty(info)){
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
		if((!map.isEmpty())){
			try {
				if(type.equals("file")){
					project.setAttachment(JsonUtil.toJson(map));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public MyBatisDao getBaseDao() {
		return projectDao;
	}

	public static ReceiveParam setReceiveParam(String fileTypesPropertyName,String fileMaxSize,String fileDir) {
		String uuid = ObjectUtils.toString(UUID.randomUUID());
		String id=StringUtils.remove(uuid,"-");
		ReceiveParam param=new ReceiveParam();
		param.setAttachmentId(id);
		param.setFileDir(fileDir);
		param.setFileMaxSize(Integer.parseInt(CommonConfig.getProperty(fileMaxSize)));
		param.setCallbackUrl("callbackUrl");
		param.setBillId(id);
		param.setFileTypes(CommonConfig.getProperty(fileTypesPropertyName));
		return param;
	}

	public void setStatus(Project project,Map<String, Object> variables,String taskDefinitionKey){
		if(project.getApplyCount()!=null){
			if(project.getApplyCount().compareTo(project.getMaxApplyCount())==1){
				if(taskDefinitionKey.equals("iprLeaderConfirm")){
					variables.put("status", "01");
				}else{
					variables.put("status", "03");
				}
			}
		}
	}
	public String getRolesStr(LoginUser loginUser){
		StringBuffer sb=new StringBuffer();
		sb.append("'");
		List<Role> list=loginUser.getRoleList();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(i<list.size()-1){
					sb.append(list.get(i).getRoleCode()).append("','");
				}else{
					sb.append(list.get(i).getRoleCode()).append("'");
				}
			}
		}else{
			sb.append("'");
		}
		return sb.toString();
	}

	public int deleteAll(Project project,String id) {
		if (project != null && project.getId() != null) {
			project.setUpdatedby(id);
			project.setUpdateTime(new Date());
			return projectDao.deleteAll(project);
		}
		return 0;
		
	}
	
	//项目审核通过后向对应课程的相关人员的邮箱定向推送消息
	public void sendEmeilByProject(Project project,HttpServletRequest request) {
		SearchParam searchParam = new SearchParam();
		//获取培训班 begin
		SearchParam sp = new SearchParam();
		sp.getParamMap().put("projectId", project.getId());
		List<Map<String,Object>> trainList = trainDao.listTrain(sp);
		//获取培训班 end
		sp.getParamMap().remove("projectId");
		for (int i = 0; i < trainList.size(); i++) {
			String content = "培训班【"+trainList.get(i).get("trainName")+"】的选课起止时间为"+trainList.get(i).get("startTime")+"至"+trainList.get(i).get("endTime");
			sp.getParamMap().put("trainId", trainList.get(i).get("id"));
			List<Map<String,Object>> trainCourseList = trainCourseDao.selectCourseByTrain(sp);
			//获取课程
			for (int j = 0; j < trainCourseList.size(); j++) {
				Course courses = new Course();
				courses = courseDao.selectByPrimaryKey(trainCourseList.get(j).get("courseId"));
				System.out.println("CourseName"+j+"----------"+courses.getCourseName());
				searchParam.getParamMap().put("userTypes", courses.getPersonType());
				searchParam.getParamMap().put("industryType", courses.getIndustryType());
				List<User> users = userService.selectUserSendEmail(searchParam);
				for (User user : users) {
					// 邮箱模板的信息
					String realName = user.getUserName();
					String email = user.getEmail();
					try {
						MimeMessage mailMsg = mailSender.createMimeMessage();
						MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg, true, "UTF-8");
						messageHelper.setTo(email);// 接收邮箱
						// 设置自定义发件人昵称
						String nickname = javax.mail.internet.MimeUtility
								.encodeText(AppConfig.getProperty("common", "mail.nickname"));
						String username = mailSender.getUsername();
						messageHelper.setFrom(new InternetAddress(nickname + " <" + username + ">"));// 发送邮箱
						messageHelper.setSubject("培训班推送消息");// 邮件标题
						Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate("sendEmailProjectInfo.ftl");// 内容模板
						Map<String, Object> args = new HashMap<String, Object>();
//						StringBuffer url = request.getRequestURL();
//						String domain = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();// 获取域名
//						String courseUrl = domain + "site/train/detail.do?id=" + courses.getId();
						Calendar cal = Calendar.getInstance();
						// args.put("now", cal.getTime());// 当前时间
						args.put("realName", realName);
						args.put("content", content);
						String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
						messageHelper.setText(html, true);// 邮件内容,true 表示启动HTML格式的邮件
						mailSender.send(mailMsg);// 发送
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		}		
	}
}
