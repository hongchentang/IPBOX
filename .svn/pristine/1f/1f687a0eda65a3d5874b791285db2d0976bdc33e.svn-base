package com.hcis.ipanther.common.validate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.impl.UserServiceImpl;
import com.hcis.ipanther.common.validate.dao.ValidateDao;
import com.hcis.ipanther.common.validate.entity.Validate;
import com.hcis.ipanther.common.validate.service.IValidateService;
import com.hcis.ipanther.common.validate.utils.SmsUtil;
import com.hcis.ipanther.common.validate.utils.ValicateCodeUtils;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.hcis.ipanther.core.utils.AppConfig;

import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class ValidateServiceImpl extends BaseServiceImpl<Validate> implements IValidateService{

	private final static Log log=LogFactory.getLog(ValidateServiceImpl.class);
	
	@Autowired
	private ValidateDao validateDao;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Resource(name="mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer; 
	
	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return validateDao;
	}
	
	/**
	 * 增加
	 */
	@Override
	public int addValidate(String checkType, String email,String phone, String userId, LoginUser loginUser) {
		
		int count = 0;
		Validate v = new Validate();
		//存入主键id
		v.setId(Identities.uuid2());
		//存入当前时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curDate = format.format(new Date());
		v.setStartTime(curDate);
		//存入一个6位的随机数
		String code = ValicateCodeUtils.getRandomNumber();
		v.setCode(code);
		//存入用户id
		v.setUserId(userId);
		//
		v.setDefaultValue();
		if(loginUser!=null){
			v.setCreator(loginUser.getId());
		}
		
		if("email".equals(checkType))
			v.setEmail(email);
		else
			v.setPhone(phone);
		
		
		count = this.validateDao.insertSelective(v);
		
		if(count>0){//当增加验证码成功后，发送邮箱或短信
			if("email".equals(checkType))
				this.sendMail(email, code);//发送邮箱
			else//发送短信
				SmsUtil.sendNotify(phone, SmsUtil.SMS_TEMPLATE_CODE, code,null,null);
		}
		
		return count;
	}

	/**
	 * 根据传入的email或phone查出 时间最新的验证码数据
	 */
	@Override
	public List<Validate> selectByMap(Map map) {
		return this.validateDao.selectByMap(map);
	}

	/**
	 * 根据用户填写的邮箱发送验证码
	 */
	@Override
	public void sendMail(String email, String Vcode) {
		
			try{
				
				MimeMessage mailMsg = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg,true, "UTF-8");  
				messageHelper.setTo(email);//接收邮箱
				
				//设置发件人 
				//String nickname = javax.mail.internet.MimeUtility.encodeText(AppConfig.getProperty("common", "mail.nickname"));
				String username = AppConfig.getProperty("common", "mail.username");
				//发件人邮箱
				messageHelper.setFrom(new InternetAddress(username));
				messageHelper.setSubject(AppConfig.getProperty("common", "mail.validateCode.subject"));//邮件标题  
				
				//获取发送邮件模板
				Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate(AppConfig.getProperty("common", "mail.validateCode.template"));
				
				Map<String,Object> args = new HashMap<String,Object>();
				args.put("code", Vcode);
				
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
				//String html = "您的验证码为:"+Vcode;
				messageHelper.setText(html, true);//邮件内容,true 表示启动HTML格式的邮件
				//JavaMailSenderImpl mailSender;
				//这里就包含发送方的信息
				mailSender.send(mailMsg);//发送
			}
			catch(Exception e){
				logger.error(e.getMessage(),e);
			}
			
			/*Properties props = new Properties();
	      	props.setProperty("mail.transport.protocol","smtp");
	      	Session session = null;
	      	Message message = null;
	      	Transport transport = null;
			
	      	 try{
	           	session = Session.getDefaultInstance(props);
	           	session.setDebug(true);
	               //2.新建邮件
	   	        message = new MimeMessage(session);
	   	    	//3.写邮件
	   	    	message.setSubject("ceshiceshi！！");
	   	    	message.setContent("测试","text/html;charset=utf-8");
	   	    	message.setFrom(new InternetAddress("qq1987417037@sina.com"));//发件人邮箱地址
	   	    	//4.填写发送信息，发送邮件
	   	    	transport = session.getTransport();
	   	    	transport.connect("smtp.sina.com", "qq1987417037", "hyy86894365");
	   	    	transport.sendMessage(message, 
	   	    			new InternetAddress[]{new InternetAddress("1987417037@qq.com")});
	           }catch(MessagingException e){
	           	throw new RuntimeException(e);
	           }finally{
	           	try{
	           		//5.关闭邮件客户端(释放资源)
	               	transport.close();
	           	}catch(MessagingException e){
	           		throw new RuntimeException(e);
	           	}
	           }*/
		
		
	}

	/**
	 * 根据传入的email或者phone来逻辑删除记录
	 */
	@Override
	public int deleteByLogic(Validate validate) {
		return this.validateDao.deleteByLogic(validate);
	}

	

	

}
