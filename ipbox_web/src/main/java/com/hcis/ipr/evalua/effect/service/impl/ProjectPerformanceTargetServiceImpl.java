package com.hcis.ipr.evalua.effect.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipr.evalua.effect.dao.ProjectPerformanceTargetDao;
import com.hcis.ipr.evalua.effect.entity.ProjectPerformanceTarget;
import com.hcis.ipr.evalua.effect.service.IProjectPerformanceTargetService;

@Service
public class ProjectPerformanceTargetServiceImpl extends BaseServiceImpl<ProjectPerformanceTarget> implements IProjectPerformanceTargetService {

	@Autowired
	private ProjectPerformanceTargetDao baseDao;
	@Autowired
	private IAttachmentService attachmentService;
	
	@Override
	public ProjectPerformanceTargetDao getBaseDao() {
		return baseDao;
	}

	@Override
	public Map<String, Object> selectPerformanceTargetById(String projectId) {
		return baseDao.selectPerformanceTargetById(projectId);
	}
	
	@Override
	public List<Map<String, Object>> selectRegionsCodeList(String projectId) {
		return baseDao.selectRegionsCodeList(projectId);
	}

	@Override
	public String uploadFile(ProjectPerformanceTarget projectPerformanceTarget, MultipartFile file) {
		Map<String, String> map=new HashMap<String, String>();
		if(file!=null){
	        if(!file.isEmpty()){ 
	        	if(StringUtils.isNoneEmpty(projectPerformanceTarget.getProjectId())){
	        		int count=baseDao.deleteFileByPid(projectPerformanceTarget.getProjectId());
	        		logger.info("deleteFileByPid count is:"+count);
	        	}
				String tempFileName=FileUtils.getTempDirectory().getPath()+"/"+file.getName();
				File tempFile=new File(tempFileName);
				try {
					file.transferTo(tempFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ReceiveParam receiveParam= setReceiveParam("attachment.default.fileTypes","attachment.default.fileMaxSize","/upload/evaluate/project");
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
		if(!map.isEmpty()){
			try {
				projectPerformanceTarget.setAttachment(JsonUtil.toJson(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static ReceiveParam setReceiveParam(String fileTypesPropertyName,String fileMaxSize,String fileDir) {
		String uuid = org.apache.commons.lang.ObjectUtils.toString(UUID.randomUUID());
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
