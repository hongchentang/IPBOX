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
package com.hcis.ipanther.common.attachment.service;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;

public interface IAttachmentService {
	
	public SendParam uploadAttachment(File file,String fileName,ReceiveParam receiveParam, String basePath);
	
	public ReceiveParam decrypt(String encryptStr);
	
	public InputStream downloadAttachment(Attachment attachment, String basePath,String realPath);
	
	public void downloadCompressAttachment(String dir,HttpServletResponse response);
	
	public String validateAttachment(File file,String fileName,ReceiveParam receiveParam);
	
	public int updateAttachmentValid(String encryptStr);
	
	public Attachment getAttachment(String id);
	
	public String getAttachmentDownloadUrl(Attachment attachment);
	
	public int deleteAttachment(Attachment attachement,String operator);
	
}
