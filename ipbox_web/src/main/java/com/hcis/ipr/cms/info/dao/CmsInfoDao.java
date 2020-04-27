package com.hcis.ipr.cms.info.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.info.entity.CmsInfo;

@Repository("cmsInfoDao")
public class CmsInfoDao extends MyBatisDao{
    
	public List<CmsInfo> selectListCmsInfo(Map<String, Object> map){
		
		return this.selectForList(namespace+".selectListCmsInfo", map);
	}
	
	/**
	 * 站点搜索
	 * @param searchParam
	 * @return
	 */
	public List<CmsInfo> searchForCms(SearchParam searchParam){
		return this.getSqlSession().selectList(namespace+".searchForCms",searchParam);
	}
	
}