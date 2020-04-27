package com.hcis.ipr.train.userquestion.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.userquestion.dao.UserQuestionDao;
import com.hcis.ipr.train.userquestion.entity.UserQuestion;
import com.hcis.ipr.train.userquestion.service.IUserQuestionService;

import freemarker.template.Template;

@Service
public class UserQuestionServiceImpl extends BaseServiceImpl<UserQuestion> implements IUserQuestionService{

	@Autowired
	private UserQuestionDao userQuestionDao;
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Resource(name="mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer;
	
	@Override
	public MyBatisDao getBaseDao() {
		return userQuestionDao;
	}
	
	/**
	 * 增加
	 */
	@Override
	public int addUserQuestion(UserQuestion userQuestion, LoginUser loginUser) {
		int count=0;
		if(userQuestion!=null&&(!userQuestion.equals(null))){
			if(StringUtils.isEmpty(userQuestion.getId())){
				userQuestion.setId(Identities.uuid2());
			}
			userQuestion.setDefaultValue();
			if(loginUser!=null){
				userQuestion.setCreator(loginUser.getId());
			}
			count= this.userQuestionDao.insertSelective(userQuestion);
		}
		return count;
	}
	
	/**
	 * 数据列表
	 */
	/*@Override
	public List<UserQuestion> selectBySearchParam(SearchParam searchParam) {
		return this.userQuestionDao.selectBySearchParam(searchParam);
	}*/
	
	/**
	 * 保存操作或保存并发送邮件操作
	 */
	@Override
	public int updateAndSend(HttpServletRequest request,UserQuestion userQuestion, String type) {
		
		int count = 0;
		
		LoginUser loginUser = LoginUser.loginUser(request);
		if(loginUser!=null)
			userQuestion.setUpdatedby(loginUser.getId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime = format.format(new Date());
		userQuestion.setEndTime(endTime);
		userQuestion.setUpdateTime(new Date());
		if("edit".equals(type))//status为1说明只是回复状态
			userQuestion.setStatus("1");
		else                   //status为2说明该记录已经发送邮件
			userQuestion.setStatus("2");
		count = this.userQuestionDao.updateByPrimaryKeySelective(userQuestion);
		if(count>0&&!"edit".equals(type))
			this.sendMail(userQuestion);
		return count;
	}
	
	/**
	 * 邮件发送
	 */
	@Override
	public void sendMail(UserQuestion userQuestion) {
		
		try{
			
			MimeMessage mailMsg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg,true, "UTF-8");  
			messageHelper.setTo(userQuestion.getEmail());//接收邮箱
			
			//设置发件人
			String username = AppConfig.getProperty("common", "mail.username");
			//发件人邮箱
			messageHelper.setFrom(new InternetAddress(username));
			messageHelper.setSubject(AppConfig.getProperty("common", "mail.userQuestion.subject"));//邮件标题 
			
			/*String html = "问题解答:" + userQuestion.getAnswer();
			messageHelper.setText(html, true);//邮件内容,true 表示启动HTML格式的邮件
*/			
			//获取发送邮件模板
			Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate(AppConfig.getProperty("common", "mail.userQuestion.template"));
			
			Map<String,Object> args = new HashMap<String,Object>();
			args.put("question", userQuestion.getQuestion());
			args.put("answer", userQuestion.getAnswer());
			
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
			messageHelper.setText(html, true);//邮件内容,true 表示启动HTML格式的邮件
			mailSender.send(mailMsg);//发送
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 逻辑删除在线留言记录
	 */
	@Override
	public int deleteByLogic(UserQuestion userQuestion) {
		return this.userQuestionDao.deleteByLogic(userQuestion);
	}

	/**
	 *  根据某个字段查找出符合的所有在线留言记录
	 */
	@Override
	public List<UserQuestion> selectByMap(Map<String, Object> map) {
		return this.userQuestionDao.selectByMap(map);
	}

	/**
	 * 计算出不同类型的数据数量
	 * @param map
	 * @return
	 */
	@Override
	public int selectCount(Map<String, Object> map) {
		return this.userQuestionDao.selectCount(map);
	}

	

	

	

}
