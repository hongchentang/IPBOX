package com.hcis.ipr.train.channel.service.impl;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.dao.UserDao;
import com.hcis.ipanther.common.user.dao.UserRegisterDao;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserRegister;
import com.hcis.ipanther.core.entity.BaseEntity;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.security.entity.Loginer;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.channel.dao.ChannelDao;
import com.hcis.ipr.train.channel.service.IChannelService;
import com.hcis.ipr.train.train.dao.TrainDao;

import freemarker.template.Template;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Service("channelService")
public class ChannelServiceImpl extends BaseServiceImpl<BaseEntity> implements IChannelService
{	
	@Autowired
	private ChannelDao channelDao;
	
	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRegisterDao userRegisterDao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Resource(name="mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer;
	
	@Override
	public void askMail(SearchParam searchParam, HttpServletRequest request, LoginUser loginUser) {	
		String projectId = searchParam.getParamMap().get("projectId").toString();
		String studentIds = searchParam.getParamMap().get("studentIds").toString();
		
		//获取培训班 begin
		SearchParam sp = new SearchParam();
		sp.getParamMap().put("projectId", projectId);
		List<Map<String,Object>> trainList = trainDao.listTrain(sp);
		//获取培训班 end
		
		//获取学员信息 begin
		List<User> users = this.getUserList(studentIds);
		//获取学员信息 end
		
		this.sendMail(trainList, users, request);
	}

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return channelDao;
	}
	
	private List<User> getUserList(String param) {
		List list = new ArrayList<>();
		
		String[] studensIds = param.split(",");
		
		for (int i = 0; i < studensIds.length; i++) {
			User user = new User();
			user = userDao.selectByPrimaryKey(studensIds[i]);
			
			list.add(user);
		}
		
		return list;
	}
	
	private void sendMail(List<Map<String,Object>> list, List<User> users, HttpServletRequest request) {
		System.out.println(users.get(0).getEmail());
		System.out.println(users.get(0).getUserName());
		System.out.println(list.get(0).get("trainName"));
		System.out.println(list.get(0).get("startTime"));
		System.out.println(list.get(0).get("endTime"));
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < list.size(); i++) {
			sb.append("培训班【"+list.get(i).get("trainName")+"】的选课起止时间为"+list.get(i).get("startTime")+"至"+list.get(i).get("endTime")+",");
		}
		
		String content = sb.toString().substring(0, sb.toString().length()-1)+"。";
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
				messageHelper.setSubject("培训班学员邀请推送消息");// 邮件标题
				Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate("sendEmailAskInfo.ftl");// 内容模板
				Map<String, Object> args = new HashMap<String, Object>();
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