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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.attachment.dao.AttachmentDao;
import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.core.security.utils.ThreeDes;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.utils.Zipper;

//@Service("attachmentLocalService")
public class AttachmentLocalServiceImpl extends AttachmentServiceImpl implements IAttachmentService {

	private static final Logger logger = LoggerFactory.getLogger(AttachmentLocalServiceImpl.class);

	private static final String rootDirectory = CommonConfig.getProperty("attachment.root.dir");
	private static final String tempDirectory = CommonConfig.getProperty("attachment.root.dir") + "temp/";// rootDirectory要用"/"结尾
	private static final String defaultDirectory = CommonConfig.getProperty("attachment.default.dir");
	private static final String downloadUrl = CommonConfig.getProperty("attachment.download.url");
	
	@Autowired
	private AttachmentDao attachmentDao;

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
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
		if (StringUtils.isEmpty(receiveParam.getFileDir())) {
			receiveParam.setFileDir(defaultDirectory);
		}
		StringBuffer sb = new StringBuffer();

		String attachmentId = Identities.uuid2();
		if (!StringUtils.isEmpty(receiveParam.getAttachmentId())) {
			attachmentId = receiveParam.getAttachmentId();
		}
		sb.append(receiveParam.getFileDir()).append(attachmentId).append(".").append(StringUtils.substringAfterLast(fileName, ".").toLowerCase());
		byte[] data;
		try {
			data = FileUtils.readFileToByteArray(file);
			File disFile = null;

			  // 如果参数以"/"开头，则根目录为web目录
			 if (receiveParam.getFileDir().startsWith("/")) {
				  if(basePath.endsWith("/")||basePath.endsWith("\\")){
					  basePath=StringUtils.substring(basePath,0,basePath.length()-1); 
				  }
			  	disFile = new File(basePath + sb.toString()); 
			  } else { 
				 disFile = new File(tempDirectory + sb.toString());
			  }

			//disFile = new File(tempDirectory + sb.toString());

			FileUtils.writeByteArrayToFile(disFile, data);
			Attachment attachment = new Attachment();
			attachment.setId(attachmentId);
			attachment.setTempFileName(fileName.toLowerCase());
			attachment.setTempFilePath(sb.toString());
			attachment.setBillId(receiveParam.getBillId());
			// attachment.setFileSize(BigDecimal.valueOf(file.length() * 1024));
			attachment.setFileSize(BigDecimal.valueOf(file.length()));
			if (!StringUtils.isEmpty(receiveParam.getRealName())) {
				attachment.setCreator(receiveParam.getRealName());
			}
			if (!StringUtils.isEmpty(receiveParam.getAttachmentId())) {
				Attachment attachmentResult = (Attachment) attachmentDao.selectByPrimaryKey(attachmentId);
				if (attachmentResult == null) {
					attachment.setDefaultValue();
					attachmentDao.insert(attachment);
				}
				else {
					if (!StringUtils.isEmpty(attachmentResult.getTempFilePath()) && !attachmentResult.getTempFilePath().equals(attachment.getTempFilePath())) {
						new File(tempDirectory + attachmentResult.getTempFilePath()).delete();
					}
					attachment.setUpdateTime(new Date());
					attachmentDao.updateByPrimaryKeySelective(attachment);
				}
			}
			else {
				attachment.setDefaultValue();
				attachmentDao.insert(attachment);
			}
			SendParam sendParam = new SendParam();
			sendParam.setAttachmentId(attachmentId);
			sendParam.setAttachmentName(fileName.toLowerCase());
			sendParam.setBillId(receiveParam.getBillId());
			sendParam.setStatus(attachment.getStatus());
			sendParam.setGroupId("");
			sendParam.setFileId(attachment.getTempFilePath());
			sendParam.setDownloadUrl(this.getAttachmentDownloadUrl(attachment));
			return sendParam;
		}
		catch (IOException e) {
			e.printStackTrace();
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
//		Attachment attachmentResult = (Attachment) attachmentDao.selectByPrimaryKey(attachment.getId());
		Attachment attachmentResult = null;
		if(StringUtils.isNotBlank(attachment.getFileName())){//上一个方法传入
			attachmentResult = attachment;
		}
		else{
			attachmentResult = (Attachment) attachmentDao.selectByPrimaryKey(attachment.getId());
		}
		if (!StringUtils.isEmpty(attachmentResult.getFilePath())) {
			attachment.setFilePath(attachmentResult.getFilePath());
			attachment.setFileName(attachmentResult.getFileName());
		}
		else {
			String filePath = attachmentResult.getFilePath();
			String tempFilePath = attachmentResult.getTempFilePath();
			try {
				if (tempFilePath.startsWith("/")) {
					// 上传到web目录的数据，无需转移
				}else {
					// 注意数据库中数据不是用"/"开头的路径，rootDirectory应该用"/"结尾，
					FileUtils.moveFile(new File(tempDirectory + tempFilePath), new File(rootDirectory + tempFilePath));
					if (!StringUtils.isEmpty(filePath) && !filePath.equals(tempFilePath)) {
						new File(rootDirectory + filePath).delete();
					}
				}
			
				attachment.setUpdateTime(new Date());
				attachmentDao.updateValid(attachment);
				attachment.setFilePath(attachmentResult.getTempFilePath());
				attachment.setFileName(attachmentResult.getTempFileName());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		File file = null;
		if (attachment.getFilePath().startsWith("/")) {
			if (realPath.endsWith("/") || basePath.endsWith("\\")) {
				realPath = StringUtils.substring(realPath, 0, realPath.length() - 1);
			}
			file = new File(realPath + attachment.getFilePath());
		}
		else {
			file = new File(rootDirectory + attachment.getFilePath());
		}
		InputStream in = null;
		try {
			if (file.exists()) {
				in = new FileInputStream(file);
			}
			else {
				if (attachment.getFilePath().startsWith("/")) {
					logger.error("download file not found:"+ realPath + attachment.getFilePath());
				}else{
					logger.error("download file not found:"+basePath + attachment.getFilePath());
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return in;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcis.ipanther.common.attachment.service.IAttachmentService#
	 * downloadCompressAttachment(java.lang.String)
	 */
	@Override
	public void downloadCompressAttachment(String dir, HttpServletResponse response) {
		if (StringUtils.isEmpty(dir)) {
			PrintWriter writer;
			try {
				writer = response.getWriter();
				writer.print("<script>alert('非法访问！');window.close();</script>");
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		String decryptStr = ThreeDes.decryptMode(dir);
		if (StringUtils.isEmpty(decryptStr)) {
			PrintWriter writer;
			try {
				writer = response.getWriter();
				writer.print("<script>alert('非法访问！');window.close();</script>");
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		String dirPath = rootDirectory + decryptStr;
		if (!new File(dirPath).exists()) {
			try {
				PrintWriter writer = response.getWriter();
				writer.print("<script>alert('暂无附件上传！');window.close();</script>");
				writer.close();
				return;
			}
			catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		String tmpDir = dirPath + "tmp";
		try {
			FileUtils.copyDirectory(new File(dirPath), new File(tmpDir));
		}
		catch (IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		List<Attachment> attachmentList = attachmentDao.selectByFileDirectory(decryptStr + "%");
		for (Attachment attachment : attachmentList) {
			String id = attachment.getId();
			String fileName = attachment.getFileName();
			String creator = attachment.getCreator();
			String filePath = attachment.getFilePath();
			String tmpFilePath = null;
			if (!StringUtils.isEmpty(creator)) {
				tmpFilePath = StringUtils.replace(StringUtils.replace(filePath, decryptStr, decryptStr + "tmp"), id, creator + id);
			}
			else {
				tmpFilePath = StringUtils.replace(StringUtils.replace(filePath, decryptStr, decryptStr + "tmp"), id,
						StringUtils.substringBeforeLast(fileName, ".") + id);
			}
			File file = new File(rootDirectory + StringUtils.replace(filePath, decryptStr, decryptStr + "tmp"));
			File tmpfile = new File(rootDirectory + tmpFilePath);
			if (file.exists()) {
				file.renameTo(tmpfile);
			}
		}

		Zipper.zip(tmpDir, tmpDir + ".zip");
		String filePath = tmpDir + ".zip";
		// 以流的形式下载文件。
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();

			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + StringUtils.substringAfterLast(filePath, "/"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				File f = new File(filePath);
				f.delete();
				FileUtils.deleteDirectory(new File(tmpDir));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String validateAttachment(File file, String fileName, ReceiveParam receiveParam) {
		String[] fileTypes = StringUtils.split(receiveParam.getFileTypes(), ",");// 限制文件类型
		if (fileTypes != null && !StringUtils.isEmpty(fileTypes[0])) {
			String currentFileType = StringUtils.substringAfterLast(fileName.toLowerCase(), ".");
			if (!ArrayUtils.contains(fileTypes,currentFileType)) {
				return CommonConfig.getProperty("attachment.fileTypes.error");
			}
		}

		long fileMaxSize = receiveParam.getFileMaxSize();
		long currentFileSize = file.length();
		if (fileMaxSize > 0) {
			if (currentFileSize > fileMaxSize * 1024) {
				return CommonConfig.getProperty("attachment.fileMaxSize.error");
			}
		}
		if (StringUtils.isEmpty(receiveParam.getCallbackUrl()) || StringUtils.isEmpty(receiveParam.getBillId())) {
			return CommonConfig.getProperty("attachment.view.illega");
		}

		return null;
	}

	@Override
	public int updateAttachmentValid(String encryptStr) {
		if (StringUtils.isEmpty(encryptStr)) {
			return 0;
		}
		String decryptStr = ThreeDes.decryptMode(encryptStr);
		Map jsonMap = (Map) JSONObject.toBean(JSONObject.fromObject(decryptStr), Map.class);
		Attachment attachment = new Attachment();
		if (!jsonMap.containsKey("attachmentId")) {
			return 0;
		}
		attachment.setId(ObjectUtils.toString(jsonMap.get("attachmentId")));
		if (jsonMap.containsKey("userId") && !StringUtils.isEmpty(ObjectUtils.toString(jsonMap.get("userId")))) {
			attachment.setUpdatedby(ObjectUtils.toString(jsonMap.get("userId")));
		}
		Attachment attachmentResult = (Attachment) attachmentDao.selectByPrimaryKey(attachment.getId());
		String filePath = attachmentResult.getFilePath();
		String tempFilePath = attachmentResult.getTempFilePath();
		try {
			new File(rootDirectory + tempFilePath).delete();
			FileUtils.moveFile(new File(tempDirectory + tempFilePath), new File(rootDirectory + tempFilePath));
			if (!StringUtils.isEmpty(filePath) && !filePath.equals(tempFilePath)) {
				new File(rootDirectory + filePath).delete();
			}
			attachment.setUpdateTime(new Date());
			return attachmentDao.updateValid(attachment);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getAttachmentDownloadUrl(Attachment attachment) {
		return downloadUrl+"?attachment.id="+attachment.getId();
	}

}
