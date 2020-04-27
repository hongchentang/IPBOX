/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 2012-8-16
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
 *************************************************/
package com.hcis.ipanther.common.attachment.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.common.attachment.dao.AttachmentDao;
import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.attachment.utils.AttachmentConstants;
import com.hcis.ipanther.core.security.utils.ThreeDes;
import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.CommonConfig;

//@Service("attachmentService")
public class AttachmentServiceImpl implements IAttachmentService {
//	@Autowired
	private IAttachmentService attachmentLocalService;
//	@Autowired
	private IAttachmentService attachmentFdfsService;
//	@Autowired
	private AttachmentDao attachmentDao;
	
	public IAttachmentService getAttachmentService() {
		String uploadType=CommonConfig.getProperty(AttachmentConstants.FILE_STORE_TYPE_KEY);
		if(StringUtils.equals(AttachmentConstants.FILE_STORE_FASTDFS,uploadType)){
			return this.getAttachmentFdfsService();
		}
		else {
			return this.getAttachmentLocalService();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcis.ipanther.common.attachment.service.IAttachmentService#
	 * uploadAttachment(java.io.File,
	 * com.hcis.ipanther.common.attachment.vo.Attachment)
	 */
	@Override
	public SendParam uploadAttachment(File file, String fileName, ReceiveParam receiveParam, String basePath) {
		return this.getAttachmentService().uploadAttachment(file, fileName, receiveParam, basePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcis.ipanther.common.attachment.service.IAttachmentService#decrypt
	 * (java.lang.String)
	 */
	@Override
	public ReceiveParam decrypt(String encryptStr) {
		if (!StringUtils.isEmpty(encryptStr)) {
			String jsonStr = ThreeDes.decryptMode(encryptStr);
			JSONObject jsonObj = JSONObject.fromObject(jsonStr);
			Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(jsonObj, Map.class);
			ReceiveParam receiveParam = new ReceiveParam();
			String billId = ObjectUtils.toString(map.get("billId"));
			if (StringUtils.isEmpty(billId)) {
				return null;
			}
			receiveParam.setBillId(billId);
			String callbackUrl = ObjectUtils.toString(map.get("callbackUrl"));
			if (StringUtils.isEmpty(callbackUrl)) {
				return null;
			}
			receiveParam.setCallbackUrl(callbackUrl);
			if (!StringUtils.isEmpty(ObjectUtils.toString(map.get("attachmentId")))) {
				receiveParam.setAttachmentId(ObjectUtils.toString(map.get("attachmentId")));
			}
			if (!StringUtils.isEmpty(ObjectUtils.toString(map.get("fileDir")))) {
				receiveParam.setFileDir(ObjectUtils.toString(map.get("fileDir")));
			}
			if (!StringUtils.isEmpty(ObjectUtils.toString(map.get("fileMaxSize")))) {
				receiveParam.setFileMaxSize(Integer.parseInt(ObjectUtils.toString(map.get("fileMaxSize"))));
			}
			if (!StringUtils.isEmpty(ObjectUtils.toString(map.get("fileTypes")))) {
					receiveParam.setFileTypes(ObjectUtils.toString(map.get("fileTypes")));
			}
			if (!StringUtils.isEmpty(ObjectUtils.toString(map.get("realName")))) {
				receiveParam.setRealName(ObjectUtils.toString(map.get("realName")));
			}
			return receiveParam;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcis.ipanther.common.attachment.service.IAttachmentService#
	 * downloadAttachment(com.hcis.ipanther.common.attachment.vo.Attachment)
	 */
	@Override
	public InputStream downloadAttachment(Attachment attachment, String basePath,String realPath) {
		Attachment attachmentResult= (Attachment) attachmentDao.selectByPrimaryKey(attachment.getId());
		if (!StringUtils.isEmpty(attachmentResult.getFilePath())) {
			attachment.setFilePath(attachmentResult.getFilePath());
			attachment.setFileName(attachmentResult.getFileName());
		} else if(!StringUtils.isEmpty(attachmentResult.getTempFilePath())) {
			attachment.setFilePath(attachmentResult.getTempFilePath());
			attachment.setFileName(attachmentResult.getTempFileName());
		}
		if(StringUtils.equals(AttachmentConstants.FILE_STORE_FASTDFS,attachmentResult.getStatus())){
			return this.getAttachmentFdfsService().downloadAttachment(attachmentResult, basePath,realPath);
		}
		else{
			return this.getAttachmentLocalService().downloadAttachment(attachmentResult, basePath,realPath);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcis.ipanther.common.attachment.service.IAttachmentService#
	 * downloadCompressAttachment(java.lang.String)
	 */
	@Override
	public void downloadCompressAttachment(String dir, HttpServletResponse response) {
		this.getAttachmentService().downloadCompressAttachment(dir, response);
	}

	@Override
	public String validateAttachment(File file, String fileName, ReceiveParam receiveParam) {
		return this.getAttachmentService().validateAttachment(file, fileName, receiveParam);
	}

	@Override
	public int updateAttachmentValid(String encryptStr) {
		return this.getAttachmentService().updateAttachmentValid(encryptStr);
	}

	/**
	 * 获取Attachment的信息
	 */
	@Override
	public Attachment getAttachment(String id) {
		Attachment attachment = (Attachment) attachmentDao.selectByPrimaryKey(id);
		return attachment;
	}

	@Override
	public String getAttachmentDownloadUrl(Attachment attachment) {
		String url="";
		switch(attachment.getStatus()){
			case AttachmentConstants.FILE_STORE_FASTDFS:{
				IAttachmentService attachmentService=(IAttachmentService)BeanLocator.getBean("attachmentFdfsService");
				url= attachmentService.getAttachmentDownloadUrl(attachment);
				
			};
			break;
			case AttachmentConstants.FILE_STORE_LOCAL:{
				IAttachmentService attachmentService=(IAttachmentService)BeanLocator.getBean("attachmentLocalService");
				url= attachmentService.getAttachmentDownloadUrl(attachment);
			};
			break;
			default:{
				IAttachmentService attachmentService=(IAttachmentService)BeanLocator.getBean("attachmentLocalService");
				url= attachmentService.getAttachmentDownloadUrl(attachment);
			};
		}
		return url;
	}
	
	@Override
	public int deleteAttachment(Attachment attachment,String operator){
		int count=0;
		if(attachment!=null&&(!attachment.equals(null))){
			attachment.setUpdateTime(new Date());
			if(operator!=null&&operator.length()>0){
				attachment.setUpdatedby(operator);
			}
			count=attachmentDao.deleteByLogic(attachment);
		}
		return count;
		
	}

	public IAttachmentService getAttachmentLocalService() {
		return attachmentLocalService;
	}

	public void setAttachmentLocalService(IAttachmentService attachmentLocalService) {
		this.attachmentLocalService = attachmentLocalService;
	}

	public IAttachmentService getAttachmentFdfsService() {
		return attachmentFdfsService;
	}

	public void setAttachmentFdfsService(IAttachmentService attachmentFdfsService) {
		this.attachmentFdfsService = attachmentFdfsService;
	}

	public AttachmentDao getAttachmentDao() {
		return attachmentDao;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

}
