package com.hcis.ipanther.common.log.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haoyu.ipanther.common.login.util.UserLoginLogUtil;
import com.hcis.ipanther.core.utils.BeanLocator;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.Version;

import com.hcis.ipanther.common.log.entity.CommonLog;
import com.hcis.ipanther.common.log.service.ICommonLogService;

public class CommonLogUtils {

	protected static final Logger logger = LoggerFactory.getLogger(CommonLogUtils.class);
	
	public static ICommonLogService commonLogService=(ICommonLogService) BeanLocator.getBean("commonLogService");
	
	/**
	 * Log. 站点操作记录
	 *
	 * @param siteId the site id
	 * @param content the content
	 * @param userId the user id
	 * @param ip the ip
	 */
	public static void log(String siteId,String content,String userId,String ip,HttpServletRequest request){
		CommonLog commonLog=setCommonLog(siteId, null, null,content, UserLoginLogUtil.getIpAddr(request), new Date(),request);
		int count=commonLogService.create(commonLog, userId);
		logger.info("CmsLogUtils log return count is :" + count);
	}
	
	/**
	 * Log. 栏目操作记录
	 *
	 * @param siteId the site id
	 * @param channelId the channel id
	 * @param content the content
	 * @param userId the user id
	 * @param ip the ip
	 */
	public static void log(String siteId,String channelId,String content,String userId,String ip,HttpServletRequest request){
		CommonLog commonLog=setCommonLog(siteId, channelId, null,content, UserLoginLogUtil.getIpAddr(request), new Date(),request);
		int count=commonLogService.create(commonLog, userId);
		logger.info("CmsLogUtils log return count is :" + count);
	}
	
	/**
	 * Log.	信息操作记录 
	 *
	 * @param siteId the site id
	 * @param channelId the channel id
	 * @param infoId the info id
	 * @param content the content
	 * @param userId the user id
	 * @param ip the ip
	 */
	public static void log(String siteId,String channelId,String infoId,String content,String userId,String ip){
		CommonLog commonLog=setCommonLog(siteId, channelId, infoId,content, ip, new Date());
		int count=commonLogService.create(commonLog, userId);
		logger.info("CmsLogUtils log return count is :" + count);
	}
	
	public static void log(String siteId,String channelId,String infoId,String url,String content,String userId,String ip,String role,String action,String endMachine,HttpServletRequest request ){
		CommonLog commonLog=setCommonLog(siteId, channelId, infoId,url,content, UserLoginLogUtil.getIpAddr(request), new Date(),role,action,endMachine,request);
		int count=commonLogService.create(commonLog, userId);
		logger.info("CmsLogUtils log return count is :" + count);
	}
	
	public static void log(String siteId,String channelId,String content,String userId,String ip){
		CommonLog commonLog=setCommonLog(siteId, channelId, content, userId,ip);
		int count=commonLogService.create(commonLog, userId);
		logger.info("CmsLogUtils log return count is :" + count);
	}
	
	public static void log(String siteId,String content,String userId,String ip){
		CommonLog commonLog=setCmsLog(siteId, content, userId,ip);
		int count=commonLogService.create(commonLog, userId);
		logger.info("CmsLogUtils log return count is :" + count);
	}
//	CmsLogUtils.log(cmsSite.getId(),cmsChannel.getId(),"栏目静态化:"+cmsChannel.getName()+" 第"+pageNum+"页",  userId,ip);
	/**
	 * Sets the cms log.
	 *
	 * @param siteId the site id
	 * @param channelId the channel id
	 * @param infoId the info id
	 * @param content the content
	 * @param ip the ip
	 * @param addTime the add time
	 * @return the cms log
	 */
	private static CommonLog setCommonLog(String siteId,String channelId,String infoId,String url,String content,String ip,Date addTime,String role,String action,String endMachine, HttpServletRequest request){
		CommonLog commonLog=new CommonLog();
		commonLog.setSiteId(siteId);
		commonLog.setChannelId(channelId);
		commonLog.setInfoId(infoId);		
		commonLog.setAddTime(new Date());		
		commonLog.setContent(content);
		commonLog.setRole(role);
		commonLog.setAction(action);
		/**
		 * 获取url 的全部信息
		 */
		commonLog.setUrl(UserLoginLogUtil.getDomain(request));
		
		/**
		 * 获取客户端浏览器信息
		 */
		commonLog.setIp(UserLoginLogUtil.getIpAddr(request));
		/**
		 * 获取浏览器的版本信息
		 */		
		@SuppressWarnings("static-access")
		Browser browser = UserLoginLogUtil.getUserAgent(request).parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
	    Version version = browser.getVersion(request.getHeader("User-Agent"));
	    String info = browser.getName() + "/" + version.getVersion();
	    commonLog.setEndMachine(info);	
		return commonLog;
	}
	
	private static CommonLog setCommonLog(String siteId,String channelId,String infoId,String content,String ip,Date addTime,HttpServletRequest request){
		CommonLog commonLog=new CommonLog();
		commonLog.setSiteId(siteId);
		commonLog.setChannelId(channelId);
		commonLog.setInfoId(infoId);
		commonLog.setAddTime(new Date());
		commonLog.setIp(UserLoginLogUtil.getIpAddr(request));
		commonLog.setContent(content);
		return commonLog;
	}
	
	private static  CommonLog setCommonLog(String siteId,String channelId,String infoId,String content,String ip,Date addTime){
		CommonLog commonLog=new CommonLog();
		commonLog.setSiteId(siteId);
		commonLog.setChannelId(channelId);
		commonLog.setInfoId(infoId);
		commonLog.setAddTime(new Date());
		commonLog.setIp(ip);
		commonLog.setContent(content);
		return commonLog;
	}
	
	private static CommonLog setCommonLog(String siteId,String channelId,String content,String userId,String ip){
		CommonLog commonLog=new CommonLog();
		commonLog.setSiteId(siteId);
		commonLog.setChannelId(channelId);
		commonLog.setAddTime(new Date());
		commonLog.setIp(ip);
		commonLog.setContent(content);
		return commonLog;
	}
	
	private static CommonLog setCmsLog(String siteId,String content,String userId,String ip){
		CommonLog commonLog=new CommonLog();
		commonLog.setSiteId(siteId);
		commonLog.setAddTime(new Date());
		commonLog.setIp(ip);
		commonLog.setContent(content);
		return commonLog;
	}
	

	
}
