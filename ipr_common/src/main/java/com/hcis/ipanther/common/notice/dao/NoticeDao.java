package com.hcis.ipanther.common.notice.dao;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.notice.entity.Notice;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

/**
 * @author Chaos
 * @date 2013-3-8
 * @time 下午5:38:37
 *
 */

@Repository
public class NoticeDao extends MyBatisDao {
	
	public int deleteByLogic(Notice notice){
		return this.update(namespace+".deleteByLogic", notice);
	}
}
