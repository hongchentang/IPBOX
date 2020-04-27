package com.hcis.ipr.cms.channel.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipr.cms.channel.entity.CmsChannel;

@Repository("cmsChannelDao")
public class CmsChannelDao extends MyBatisDao{

	public int selectHasPagemark(Map<String, Object> map) {
		return this.selectForInt(namespace+".selectHasPagemark",map);
	}

	public CmsChannel findBySitePageMark(Map<String, Object> map) {
		return (CmsChannel) this.selectForVO(namespace+".findBySitePageMark",map);
	}

	public List<CmsChannel> findHasChildren(String channelId) {
		return  this.selectForList(namespace+".findHasChildren",channelId);
	}

	public List<CmsChannel> findByParent(Map<String, Object> map) {
		return  this.selectForList(namespace+".findByParent",map);
	}
 
}