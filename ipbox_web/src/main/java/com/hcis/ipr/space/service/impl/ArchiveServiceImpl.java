package com.hcis.ipr.space.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.attachment.dao.AttachmentDao;
import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.space.service.IArchiveService;

@Service("archiveService")
public class ArchiveServiceImpl implements IArchiveService{

	@Autowired
	private AttachmentDao attachmentDao;
	
	
	//专题课程附件列表
	public List<Attachment> listExpertAttachment(SearchParam searchParam){
		return attachmentDao.selectBySearchParam(searchParam);
	}
	
}
