package com.hcis.ipanther.common.attachment.utils;


import java.io.File;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.core.security.utils.ThreeDes;
import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.HttpPostClient;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.utils.JSONUtils;
import com.hcis.ipanther.core.utils.JsonUtil;
public class AttachmentUtils {
	
	private final static Log log = LogFactory.getLog(AttachmentUtils.class);
	
	public static int postUpdateAttacmentValid(String host,int port,String attachmentId,String userId){
		String json = "{\"attachmentId\":\""+attachmentId+"\",\"updatedby\":\""+ObjectUtils.toString(userId,"")+"\"}";
		NameValuePair nameValuePair = new NameValuePair("attachParam",ThreeDes.encryptMode(json));
		NameValuePair[] newValuePariArray = {nameValuePair};
		String response =HttpPostClient.post(host, port, "/common/nosession/attachment/updateAttachmentValid.do", newValuePariArray);
		return StringUtils.isNumeric(response)?Integer.parseInt(response):0;
	}
	
	public static String getAttachmentParam(String attachmentId,String billId,String callbackUrl,String fileDir){
		String fileTypes=CommonConfig.getProperty("attachment.default.fileTypes");
		String fileMaxSize=CommonConfig.getProperty("attachment.default.fileMaxSize");
		return getAttachmentParamBase(attachmentId,billId,callbackUrl,fileDir,fileTypes,fileMaxSize);
	}
	
	public static String getAttachmentParamImage(String attachmentId,String billId,String callbackUrl,String fileDir){
		String fileTypes=CommonConfig.getProperty("attachment.default.fileTypes.image");
		String fileMaxSize=CommonConfig.getProperty("attachment.default.fileMaxSize");
		return getAttachmentParamBase(attachmentId,billId,callbackUrl,fileDir,fileTypes,fileMaxSize);
	}
	
	public static String getAttachmentParamBase(String attachmentId,String billId,String callbackUrl,String fileDir,String fileTypes,String fileMaxSize){
		StringBuffer uploadStr = new StringBuffer();
		uploadStr.append("{\"fileTypes\":\"").append(fileTypes).append("\",");
		uploadStr.append("\"fileMaxSize\":\"").append(fileMaxSize).append("\",");
		if(attachmentId!=null&&!StringUtils.isEmpty(attachmentId)){
			uploadStr.append("\"attachmentId\":\"").append(attachmentId).append("\",");
		}
		uploadStr.append("\"billId\":\"").append(billId).append("\",");
		uploadStr.append("\"callbackUrl\":\"").append(callbackUrl).append("\",");
		uploadStr.append("\"fileDir\":\"").append(fileDir).append("\"}");		
		return ThreeDes.encryptMode(uploadStr.toString());
	}

	/**
	 * 获取Attachment的url
	 * 解析保存在其它页面中的json
	 * 必须给一个默认图片路径
	 */
	public static String getAttachmentPath(String attachmentJson,String defaultPath){
		String path=defaultPath;
		if(StringUtils.isNotEmpty(attachmentJson)){
			try{
				Map<String,Object> attachmentMap=JSONUtils.getJSONMap(attachmentJson);
				String attachmentName=(String)attachmentMap.get("attachmentName");
				String attachmentId=(String)attachmentMap.get("attachmentId");
				IAttachmentService attachmentService=(IAttachmentService)BeanLocator.getBean("attachmentService");
				Attachment attachment=attachmentService.getAttachment(attachmentId);
				if(StringUtils.isNotEmpty(attachment.getFilePath())){
					path=attachment.getFilePath();
				}
				else{
					path=attachment.getTempFilePath();
				}
			}
			catch(Exception e){
				e.printStackTrace();
				path=defaultPath;
			}
		}
		return path;
	}
	
	public static Attachment getAttachment(String id){
		IAttachmentService attachmentService=(IAttachmentService)BeanLocator.getBean("attachmentService");
		return attachmentService.getAttachment(id);
	}
	
	/**
	 * 格式如下
	 * {"attachmentId":"43cb34d75cfb4e0984a96c8d041676c8",
	 * "attachmentName":"lighthouse.jpg",
	 * "status":"FASTDFS",
	 * "groupId":"group2",
	 * "fileId":"M00/00/0C/wKgABFREtFqAGvqTAAiQfKHDaaQ573.jpg"}
	 * 
	 * @param attachmentJson
	 * @return
	 */
	public static String getAttachmentDownloadUrl(String attachmentJson){
		String url="";
		if(StringUtils.isNotEmpty(attachmentJson)){
			try{
				Map<String,Object> attachmentMap=JSONUtils.getJSONMap(attachmentJson);
				String attachmentId=(String)attachmentMap.get("attachmentId");
				String attachmentName=(String)attachmentMap.get("attachmentName");
				String status=StringUtils.defaultString((String)attachmentMap.get("status"),"");
				String groupId=StringUtils.defaultString((String)attachmentMap.get("groupId"),"");
				String fileId=StringUtils.defaultString((String)attachmentMap.get("fileId"),"");
				Attachment attachment=new Attachment();
				attachment.setId(attachmentId);
				attachment.setStatus(status);
				attachment.setTempFileName(groupId);
				attachment.setTempFilePath(fileId);
				IAttachmentService attachmentService=(IAttachmentService)BeanLocator.getBean("attachmentService");
				url= attachmentService.getAttachmentDownloadUrl(attachment);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return url;
	}
	
	public static String formatFileSize(Long fileSize) {
		long size=fileSize.longValue();
		long SIZE_KB = 1024;
		long SIZE_MB = SIZE_KB * 1024;
		long SIZE_GB = SIZE_MB * 1024;
		long SIZE_TB = SIZE_GB * 1024;
		if (size < SIZE_KB) {
			return String.format("%d B", (int) size);
		}
		else if (size < SIZE_MB) {
			return String.format("%.2f KB", (float) size / SIZE_KB);
		}
		else if (size < SIZE_GB) {
			return String.format("%.2f MB", (float) size / SIZE_MB);
		}
		else if (size < SIZE_TB){
			return String.format("%.2f GB", (float) size / SIZE_GB);
		}
		else{
			return String.format("%.2f TB", (float) size / SIZE_TB);
		}
	}
	
	
	/**
	 * Sets the receive param.
	 *
	 * 适用与input type="file" 后台组装upload 上传参数
	 *
	 * @param fileDir the file dir 自定义 参数 参数开头不能有  "/"
	 * @return the receive param
	 */
	public static ReceiveParam setReceiveParam(String fileDir) {
		String id=Identities.uuid2();
		ReceiveParam param=new ReceiveParam();
		param.setAttachmentId(id);
		param.setFileMaxSize(Integer.parseInt(CommonConfig.getProperty("attachment.default.fileMaxSize")));
		param.setCallbackUrl("callbackUrl");
		param.setBillId(id);
		param.setFileTypes(CommonConfig.getProperty("attachment.default.fileTypes.ipr"));
		param.setFileDir(fileDir);
		return param;
	}
	
//	public static ReceiveParam setReceiveParam(String fileDir,String fileTypes) {
//		String id=Identities.uuid2();
//		ReceiveParam param=new ReceiveParam();
//		param.setAttachmentId(id);
//		param.setFileMaxSize(Integer.parseInt(CommonConfig.getProperty("attachment.default.fileMaxSize")));
//		param.setCallbackUrl("callbackUrl");
//		param.setBillId(id);
//		if(StringUtils.isNotEmpty(fileTypes)){
//			param.setFileTypes(fileTypes);
//		}else{
//			param.setFileTypes(CommonConfig.getProperty("attachment.default.fileTypes.tms"));
//		}
//		param.setFileDir(fileDir);
//		return param;
//	}
	
	/**
	 * 
	 * @param fileDir
	 * @param fileType 文件类型 参考配置文件common.properties
	 * @return
	 */
	public static ReceiveParam setReceiveParam(String fileDir,String fileType) {
		String id=Identities.uuid2();
		ReceiveParam param=new ReceiveParam();
		param.setAttachmentId(id);
		param.setFileMaxSize(Integer.parseInt(CommonConfig.getProperty("attachment.default.fileMaxSize")));
		param.setCallbackUrl("callbackUrl");
		param.setBillId(id);
		if(StringUtils.isEmpty(fileType)){
			param.setFileTypes(CommonConfig.getProperty("attachment.default.fileTypes.ipr"));
		}else{
			param.setFileTypes(fileType);
		}
		param.setFileDir(fileDir);
		return param;
	}
	
	/**
	 * 上传单个图片通用方法
	 * 用来上传用户头像、单位logo
	 * @param obj 设置上传后img的属性的实体
	 * @param imgs 
	 * @param properName obj的属性名称
	 * @return 错误信息
	 * @author wuwentao
	 */
	public static String updateAvatar(Object obj,MultipartFile img,String properName) {
		SendParam sendParam =null;
		try {
			if(img!=null){
				IAttachmentService attachmentService= (IAttachmentService) BeanLocator.getBean("attachmentService");
		        if(!img.isEmpty()){  
					String tempFileName=FileUtils.getTempDirectory().getPath()+"/"+img.getName();
					File tempFile=new File(tempFileName);
					img.transferTo(tempFile);
					ReceiveParam receiveParam= setReceiveParam("avatar/",CommonConfig.getProperty("attachment.default.fileTypes.image"));
					String info = attachmentService.validateAttachment(tempFile,img.getOriginalFilename(), receiveParam);
		        	if(StringUtils.isNotEmpty(info)){
		        		return info;
		        	}
					sendParam = attachmentService.uploadAttachment(tempFile,img.getOriginalFilename(),receiveParam,"avatar/");
					if(sendParam==null){
						return CommonConfig.getProperty("attachment.upload.error");
					}
		        }  
			}
			if(null!=sendParam){
				PropertyUtils.setProperty(obj, properName, JsonUtil.toJson(sendParam));
			}
		} catch (Exception e) {
			return "上传文件发生异常";
		}
		return null;
	}
	
//	/**
//	 * Sets the receive param.
//	 *
//	 * 适用与input type="file" 后台组装upload 上传参数
//	 *
//	 * @return the receive param
//	 */
//	public static ReceiveParam setReceiveParam() {
//		String id=Identities.uuid2();
//		ReceiveParam param=new ReceiveParam();
//		param.setAttachmentId(id);
//		param.setFileMaxSize(Integer.parseInt(CommonConfig.getProperty("attachment.default.fileMaxSize")));
//		param.setCallbackUrl("callbackUrl");
//		param.setBillId(id);
//		param.setFileTypes(CommonConfig.getProperty("attachment.default.fileTypes.tms"));
//		return param;
//	}
}
