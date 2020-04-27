package com.hcis.ipr.intellectual.patent.service.impl;

import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.patent.dao.PatentAttachmentDao;
import com.hcis.ipr.intellectual.patent.entity.PatentAttachment;
import com.hcis.ipr.intellectual.patent.enumeration.AttachmentEnum;
import com.hcis.ipr.intellectual.patent.service.PatentAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("patentAttachmentService")
public class PatentAttachmentServiceImpl extends BaseServiceImpl<PatentAttachment> implements PatentAttachmentService {

	@Autowired
	private PatentAttachmentDao patentAttachmentDao;

	@Override
	public MyBatisDao getBaseDao() {
		return patentAttachmentDao;
	}

	@Override
	public Map<String, Map<String, List<PatentAttachment>>> selectByPatentAppNumbers(List<String> appNumbers) {

		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("appNumbers", appNumbers);
		Pagination pagination = new Pagination();
		pagination.setPageSize(Integer.MAX_VALUE);
		searchParam.setPagination(pagination);
		List<PatentAttachment> list = patentAttachmentDao.selectBySearchParam(searchParam);
		Map<String, Map<String, List<PatentAttachment>>> mapList = new HashMap<>();

		if(list != null){
			for(PatentAttachment attachment : list){
				String appNumber = attachment.getPatentAppNumber();
				Integer attachmentType = attachment.getType();
				Map<String, List<PatentAttachment>> patentAttachmentMap = mapList.get(appNumber);
				if(patentAttachmentMap == null){
					patentAttachmentMap = new HashMap<>();
					mapList.put(appNumber, patentAttachmentMap);
				}
				AttachmentEnum attachmentEnum = AttachmentEnum.valueOf(attachmentType);
				if (attachmentEnum != null) {
					List<PatentAttachment> fileList = patentAttachmentMap.get(attachmentEnum.getTypeName());
					if(fileList == null){
						fileList = new ArrayList<>();
					}
					fileList.add(attachment);
					patentAttachmentMap.put(attachmentEnum.getTypeName(), fileList);
				}
			}


		}

		return mapList;
	}

    @Override
    public Map<String, List<PatentAttachment>> selectPatentAttachmentsMap(String appNumber) {
		Map<String, List<PatentAttachment>> map = new HashMap<>();
		List<PatentAttachment> attachments = patentAttachmentDao.getPatentAttachments(appNumber);
		if(attachments != null){
			for(PatentAttachment attachment : attachments){
				int type = attachment.getType();
				AttachmentEnum attachmentEnum = AttachmentEnum.valueOf(type);
				if(attachmentEnum  != null){
					List<PatentAttachment> fileList = map.get(attachmentEnum.getTypeName());
					if(fileList == null){
						fileList = new ArrayList<>();
					}
					fileList.add(attachment);
					map.put(attachmentEnum.getTypeName(), fileList);
				}
			}
		}

		return map;
    }
}
