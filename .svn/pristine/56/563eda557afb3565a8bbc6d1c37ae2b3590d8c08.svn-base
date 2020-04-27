package com.hcis.ipr.intellectual.application.service.impl;

import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipr.intellectual.application.dao.UsePatentDao;
import com.hcis.ipr.intellectual.application.entity.UsePatent;
import com.hcis.ipr.intellectual.application.service.IUsePatentService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("usePatentService")
public class UsePatentServiceImpl extends BaseServiceImpl<UsePatent> implements IUsePatentService {
	
	private final static Log log=LogFactory.getLog(UsePatentServiceImpl.class);
	@Autowired
	private UsePatentDao usePatentDao;
	@Autowired
	private IAttachmentService attachmentService;
	@Override
	public MyBatisDao getBaseDao() {
		return usePatentDao;
	}
	@Override
	public String uploadFile(UsePatent usePatent,MultipartFile file){
		return upload( usePatent,  file, "file","attachment.default.fileTypes","course/file/");
		
	}
	
	public String upload(UsePatent usePatent,MultipartFile file,String type,String fileTypes,String fileDir){
		Map<String, String> map=new HashMap<String, String>();
		String info=null;
		info=this.saveFile(map, file, fileTypes, fileDir);
		if((!map.isEmpty())){
			try {
				if(type.equals("file")){
					usePatent.setAttachName(JsonUtil.toJson(map));
				}
			} catch (Exception e) {
				info = "上传附件失败";
				log.error(this.getClass(), e);
			}
		}
		return info;
	}
	
	public String saveFile(Map<String, String> map, MultipartFile file,String fileTypes,String fileDir){
		if(file!=null){
	        if(!file.isEmpty()){  
				String tempFileName=FileUtils.getTempDirectory().getPath()+"/"+file.getName();
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
		return null;
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
}
