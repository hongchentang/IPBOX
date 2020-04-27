package com.hcis.ipanther.common.validate.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcis.ipanther.core.utils.AppConfig;

import io.rong.RongCloud;
import io.rong.models.CodeSuccessReslut;
import io.rong.models.SMSSendCodeReslut;

public class SmsUtil {
	
	private static final Logger logger=LoggerFactory.getLogger(SmsUtil.class);

	private static String APP_KEY=AppConfig.getProperty("sms","APP_KEY");//App_Key
	private static String APP_SECRET=AppConfig.getProperty("sms","APP_SECRET");//App_Secret
	public static String SMS_CODE_REGISTER=AppConfig.getProperty("sms","SMS_CODE_REGISTER");//#用户注册
	public static String SMS_CODE_PWD_RESET=AppConfig.getProperty("sms","SMS_CODE_PWD_RESET");//#密码找回
	public static String SMS_CODE_PWD_EDIT=AppConfig.getProperty("sms","SMS_CODE_PWD_EDIT");//#修改密码
	public static String SMS_CODE_IDENTITY=AppConfig.getProperty("sms","SMS_CODE_IDENTITY");//#身份验证
	public static String SMS_CODE_NORMAL=AppConfig.getProperty("sms","SMS_CODE_NORMAL");//#通用验证码
	public static String SMS_TEMPLATE_CODE=AppConfig.getProperty("sms","SMS_TEMPLATE_CODE");//#生成短信验证码

//	public static int SMS_CODE_USEFUL_TIME=Integer.valueOf(AppConfig.getProperty("sms","SMS_CODE_USEFUL_TIME"));//默认3分钟内有效
	
	/**
	 * 发送短信验证码，使用
	 * @param phoneNo
	 * @param smsTemplateId 
	 * @param contents 对应短信模板中的参数
	 * @return
	 */
	public static String sendCode(String phoneNo,String smsTemplateId){
		RongCloud rongCloud=RongCloud.getInstance(APP_KEY, APP_SECRET);
		SMSSendCodeReslut result=new SMSSendCodeReslut(null, null, null);
		try {
			result = rongCloud.sms.sendCode(phoneNo, smsTemplateId, "86", null, null);
		}
		catch (Exception e) {
			logger.error(SmsUtil.class.getName(),e);
		}
		if("200".equals(result.getCode().toString())){
			return result.getSessionId();
		}
		else{
			//异常返回输出错误码和错误信息
			logger.error(SmsUtil.class.getName(),"错误码=" + result.getCode() +" 错误信息= "+result.getErrorMessage());
			return null;
		}
	}
	
	/**
	 * 验证短信验证
	 * @param phoneNo
	 * @param smsTemplateId 
	 * @param contents 对应短信模板中的参数
	 * @return
	 */
	public static boolean verifyCode(String sessionId,String code){
		RongCloud rongCloud=RongCloud.getInstance(APP_KEY, APP_SECRET);
		CodeSuccessReslut result=new CodeSuccessReslut(0,null);
		try {
			result = rongCloud.sms.verifyCode(sessionId, code);
		}
		catch (Exception e) {
			logger.error(SmsUtil.class.getName(),e);
		}
		if("200".equals(result.getCode().toString())){
			return true;
		}
		else{
			//异常返回输出错误码和错误信息
			logger.error(SmsUtil.class.getName()+"错误码=" + result.getCode() +" 错误信息= "+result.getErrorMessage());
			return false;
		}
	}
	
	/**
	 * 发送短信模板
	 * @param phoneNo
	 * @param smsTemplateId 
	 * @param contents 对应短信模板中的参数
	 * @return
	 */
	public static String sendNotify(String phoneNo,String smsTemplateId,String p1,String p2,String p3){
		RongCloud rongCloud=RongCloud.getInstance(APP_KEY, APP_SECRET);
		SMSSendCodeReslut result=new SMSSendCodeReslut(null, null, null);
		try {
			result = rongCloud.sms.sendNotify(phoneNo, smsTemplateId, "86", p1, p2, p3);
		}
		catch (Exception e) {
			logger.error(SmsUtil.class.getName(),e);
		}
		if("200".equals(result.getCode().toString())){
			return result.getSessionId();
		}
		else{
			//异常返回输出错误码和错误信息
			logger.error(SmsUtil.class.getName()+"错误码=" + result.getCode() +" 错误信息= "+result.getErrorMessage());
			return null;
		}
	}
	
	public static void main(String args[]) throws Exception{
		// 发送短信验证码方法。 
		RongCloud rongCloud=RongCloud.getInstance(APP_KEY, APP_SECRET);
//		SMSSendCodeReslut sMSSendCodeResult = rongCloud.sms.sendCode("18688851688", SMS_CODE_REGISTER, "86", null, null);
//		System.out.println("sendCode:  " + sMSSendCodeResult.toString());
		
		// 验证码验证方法 
//		CodeSuccessReslut sMSVerifyCodeResult = rongCloud.sms.verifyCode("2wavh_9AQ0NbIvRx7ytV5Z", "940951");
//		System.out.println("verifyCode:  " + sMSVerifyCodeResult.toString());
		
		// 发送短信模板
//		SMSSendCodeReslut sMSNotifyCodeResult = rongCloud.sms.sendNotify("18688851688", SMS_TEMPLATE_CODE, "86", "123456", null, null);
//		System.out.println("sendNotify:  " + sMSNotifyCodeResult.toString());
		
		System.out.println("SmsUtil.sendNotify:  " + SmsUtil.sendNotify("18688851688", SMS_TEMPLATE_CODE, "123456", null, null));
	}
	
}
