package com.hcis.ipr.cms.link.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipr.cms.link.entity.CmsLink;

@Repository("cmsLinkDao")
public class CmsLinkDao extends MyBatisDao{

	public List<CmsLink> selectListLink(Map<String, Object> map) {
		return this.selectForList(namespace+".selectListLink", map);
	}
 
}