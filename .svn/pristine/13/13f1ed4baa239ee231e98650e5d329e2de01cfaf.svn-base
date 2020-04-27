package com.hcis.ipr.train.register.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.register.dao.TrainRegisterDao;
import com.hcis.ipr.train.register.entity.CourseRegister;
import com.hcis.ipr.train.register.entity.TrainRegister;
import com.hcis.ipr.train.register.service.ICourseRegisterService;
import com.hcis.ipr.train.register.service.ITrainRegisterService;
import com.hcis.ipr.train.register.utils.RegisterConstants;
import com.hcis.ipr.train.train.entity.TrainCourse;
import com.hcis.ipr.train.train.service.ITrainCourseService;

import freemarker.template.Template;

@Service("trainRegisterService")
public class TrainRegisterServiceImpl extends BaseServiceImpl<TrainRegister> implements ITrainRegisterService{

	@Autowired
	private TrainRegisterDao trainRegisterDao;
	@Autowired
	private ICourseRegisterService courseRegisterService;
	@Autowired
	private ITrainCourseService trainCourseService;
	@Autowired
	private IUserService userService;
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Resource(name="mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer;
	//保存关联
	public int saveTrainRegister(SearchParam searchParam,HttpServletRequest request){
		Map<String,Object> param=searchParam.getParamMap();
		LoginUser loginUser=LoginUser.loginUser(request);
		String trainId=param.get("trainId").toString();
		String studentsStr=param.get("studentIds").toString();
		String[] StudentIds=studentsStr.split(",");
		TrainRegister tr=null;
		CourseRegister cr=null;
		//获取培训班关联的课程
		List<TrainCourse> trainCourseList=trainCourseService.list(searchParam);
		for(String id:StudentIds){
			//保存学员与培训班关联
			tr=new TrainRegister();
			tr.setTrainId(trainId);
			tr.setUserId(id);
			tr.setStatus("01");
			tr.setAuditUser(loginUser.getId());
			tr.setAuditDept(loginUser.getFirstDeptId());
			tr.setAuditTime(new Date());
			this.create(tr, loginUser.getId());
			//保存学员与培训班所选课程的关联，用于后面的成绩录入。
			if(trainCourseList.size()>0){
				for(TrainCourse tc:trainCourseList){
					cr=new CourseRegister();
					cr.setCourseId(tc.getCourseId());
					cr.setUserId(id);
					cr.setTrainId(trainId);
					courseRegisterService.create(cr, loginUser.getId());
				}
			}
		}
		return StudentIds.length;
	}
	
	//报名审核
	public int auditUser(SearchParam searchParam,HttpServletRequest request){
		int count=0;
		CourseRegister cr=null;
		Map<String,Object> param=searchParam.getParamMap();
		LoginUser loginUser=LoginUser.loginUser(request);
		String auditId=param.get("auditId").toString();//格式：auditId1,auditId2多个以逗号隔开
		String userTrainId=param.get("userTrainId").toString();//格式：userId1:trainId1,userId2:trainId2多个以逗号隔开
		String auditStatus=param.get("auditStatus").toString();
		param.put("auditId", "'"+auditId.replaceAll(",", "','")+"'");
		param.put("auditUser", loginUser.getId());
		param.put("auditDept", loginUser.getFirstDeptId());
		param.put("auditTime", new Date());
		//批量更新审核状态
		count=trainRegisterDao.updateByIds(searchParam);
		//若通过，则新增课程与学员的关联关系
		if(auditStatus.equals(RegisterConstants.STUDENT_AUDIT_STATUS_01)&&count>0){
			SearchParam sp=new SearchParam();
			String[] userTrainArray=userTrainId.split(",");
			for(String userTrain:userTrainArray){
				String[] ut=userTrain.split(":");
				if(StringUtils.isNotBlank(ut[0])&&StringUtils.isNotBlank(ut[1])){
					//获取培训班关联的课程
					sp.getParamMap().put("trainId", ut[1]);
					List<TrainCourse> trainCourseList=trainCourseService.list(sp);
					//保存学员与培训班所选课程的关联，用于后面的成绩录入。
					if(trainCourseList.size()>0){
						for(TrainCourse tc:trainCourseList){
							cr=new CourseRegister();
							cr.setCourseId(tc.getCourseId());
							cr.setUserId(ut[0]);
							cr.setTrainId(ut[1]);
							courseRegisterService.create(cr, loginUser.getId());
						}
					}
				}
			}
		}
		
		return count;
	}
	
	
	//根据关系表ID批量更新删除
	public int batchDelete(SearchParam searchParam,HttpServletRequest request){
		LoginUser loginUser=LoginUser.loginUser(request);
		Map<String,Object> param=searchParam.getParamMap();
		int count=0;
		String registerIds=param.get("registerIds").toString();
		registerIds="'"+registerIds.replaceAll(",", "','")+"'";
		param.put("ids", registerIds);
		param.put("updatedby", loginUser.getId());
		param.put("updateTime", new Date());
		//批量更新删除报名
		count=trainRegisterDao.batchUpdate(searchParam);
		//批量更新删除课程学员关联
		count+=courseRegisterService.batchUpdate(searchParam);
		return count;
	}
	
	//前端个人报名
	public int signUp(SearchParam searchParam,HttpServletRequest request) throws ParseException{
		Map<String,Object> param=searchParam.getParamMap();
		LoginUser loginUser=LoginUser.loginUser(request);
		String roleCode=loginUser.getRoleCode().toString();
		String trainId=param.get("trainId").toString();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String isRoom = param.get("isRoom").toString();
		//判断是否为学员或学员兼教师 roleCode为2和6
		if(roleCode.equals(UserConstants.USER_ROLECODE_STUDENT)||roleCode.equals(UserConstants.USER_ROLECODE_STUDENT_TEACHER)){
			param.put("userId", loginUser.getId());
			//判断是否已经报名
			List list=this.list(searchParam);
			if(list.size()>0){
				return 300;
			}
			//报名，状态为待审核00 ,保存学员与培训班关联
			TrainRegister tr=new TrainRegister();
			tr.setTrainId(trainId);
			tr.setUserId(loginUser.getId());
			tr.setStatus("00");
			tr.setIsRoom(isRoom);
			if (isRoom.equals("01")) {
				tr.setRoomStartTime(sf.parse(param.get("startTime").toString()));
				tr.setRoomEndTime(sf.parse(param.get("endTime").toString()));
			}	
			this.create(tr, loginUser.getId());
			return 200;
		}else{
			return 400;
		}
	}
	
	@Override
	public TrainRegisterDao getBaseDao() {
		return trainRegisterDao;
	}

	@Override
	public List<Map<String, Object>> selectCountRegister(Map<String, Object> map) {
		return trainRegisterDao.selectCountRegister(map);
	}
	
	public AjaxReturnObject  sendEmailTrain(String auditStatus,String toUserIds){
		String msg="操作成功！";
		int  statusCode=200;
		String emailMsg="已通过";
		//获取状态是否通过或未通过
		String status=auditStatus;
		if(StringUtils.equals("02",status)){
			emailMsg="未通过";
		}
		String toUserId = toUserIds;
		String[] userIds = null;
		if (toUserId.indexOf(",") >= 0) {
			userIds = toUserId.split(",");
		} else {
			userIds = new String[1];
			userIds[0] = toUserId;
		}

		// 多选就循环
		for (int i = 0; i < userIds.length; i++) {
			User newUser = userService.read(userIds[i]);
			try {
				MimeMessage mailMsg = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg, true, "UTF-8");
				messageHelper.setTo(newUser.getEmail());// 接收邮箱
				// 设置自定义发件人昵称
				String nickname = javax.mail.internet.MimeUtility.encodeText(AppConfig.getProperty("common", "mail.nickname"));
				String username = mailSender.getUsername();
				messageHelper.setFrom(new InternetAddress(nickname + " <" + username + ">"));// 发送邮箱
				messageHelper.setSubject("培训活动报名消息通知");// 邮件标题
				Template tpl = this.mailFreeMarkerConfigurer.getConfiguration()
						.getTemplate("sendEmailTrainInfo.ftl");// 内容模板
				Map<String, Object> args = new HashMap<String, Object>();
				Calendar cal = Calendar.getInstance();
				args.put("now", cal.getTime());// 当前时间
				args.put("userName", newUser.getUserName());
				args.put("result", emailMsg);
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
				messageHelper.setText(html, true);// 邮件内容,true 表示启动HTML格式的邮件
				mailSender.send(mailMsg);// 发送
			} catch (Exception e) {
				msg = "邮箱发送失败！";
				statusCode=300;
				logger.error(e.getMessage(), e);
			}

		}
		return new AjaxReturnObject(statusCode,msg);
	}
}
