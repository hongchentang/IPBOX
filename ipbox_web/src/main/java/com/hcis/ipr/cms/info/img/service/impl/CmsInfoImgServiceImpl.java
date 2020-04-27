package com.hcis.ipr.cms.info.img.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.cms.info.img.dao.CmsInfoImgDao;
import com.hcis.ipr.cms.info.img.entity.CmsInfoImg;
import com.hcis.ipr.cms.info.img.service.ICmsInfoImgService;

@Service("cmsInfoImgService")
public class CmsInfoImgServiceImpl extends BaseServiceImpl<CmsInfoImg> implements ICmsInfoImgService {

	@Autowired
	private CmsInfoImgDao baseDao;
	
	
	
	
	
	@Override
	public CmsInfoImgDao getBaseDao() {
		return baseDao;
	}
}
