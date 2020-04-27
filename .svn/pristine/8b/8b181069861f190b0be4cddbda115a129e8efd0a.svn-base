package com.hcis.ipr.cms.site.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipr.cms.site.entity.CmsSite;

@Repository("cmsSiteDao")
public class CmsSiteDao extends MyBatisDao{

	public CmsSite selectFirstSite() {
		return (CmsSite) this.selectForVO(namespace+".selectFirstSite");
	}

	public CmsSite selectFirstByRoles(Map<String, Object> map) {
		return (CmsSite) this.selectForVO(namespace+".selectFirstSite",map);
	}

	public int deleteMainSite(CmsSite cmsSite) {
		return this.update(namespace+".deleteMainSite",cmsSite);
	}

	public CmsSite selectMainSite() {
		return (CmsSite) this.selectForVO(namespace+".selectMainSite");
	}

	public int selectHaveSourcePath(String sourcePath) {
		return this.selectForInt(namespace+".selectHaveSourcePath",sourcePath);
	}

	public CmsSite selectFirstSite(Map<String, Object> map) {
		return (CmsSite) this.selectForVO(namespace+".selectFirstSite",map);
	}
 
}