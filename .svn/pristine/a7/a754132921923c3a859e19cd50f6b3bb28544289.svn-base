package com.hcis.ipr.cms.link.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.cms.link.dao.CmsLinkDao;
import com.hcis.ipr.cms.link.entity.CmsLink;
import com.hcis.ipr.cms.link.service.ICmsLinkService;

@Service("cmsLinkService")
public class CmsLinkServiceImpl extends BaseServiceImpl<CmsLink> implements ICmsLinkService {

	@Autowired
	private CmsLinkDao baseDao;
	
	@Override
	public List<CmsLink> listLink(Map<String, Object> map) {
		return baseDao.selectListLink(map);
	}
	
	@Override
	public List<CmsLink> listLink(Map<String, Object> map, int siezNum) {
		List<CmsLink> list=listLink(map);
		if(list!=null){
			if(siezNum>0&&list.size()>siezNum){
				list=list.subList(0, siezNum-1);
				return list;
			}else{
				return list;
			}
		}
		return null;
	}
	
	@Override
	public CmsLinkDao getBaseDao() {
		return baseDao;
	}



}
