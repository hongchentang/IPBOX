/**
 * 
 */
package com.hcis.ipanther.common.seq.dao;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.seq.entity.Seq;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

/**
 * @author Chaos
 * @date 2013-3-8
 * @time 下午5:38:37
 *
 */
@Repository
public class SeqDao extends MyBatisDao {
	
	public Seq selectBySeqCode(String seqCode){
		return (Seq) this.getSqlSession().selectOne(namespace+".selectBySeqCode",seqCode);
	}
	
	/**
	 * 更新逻辑为必须id一致，旧数据的next_num=新数据的current_num，version一致
	 * @param seq
	 * @return
	 */
	public int updateByPrimaryKeyAndNumSelective(Seq seq){
		return this.getSqlSession().update(namespace+".updateByPrimaryKeyAndNumSelective",seq);
	}

}
