/**
 * 
 */
package com.hcis.ipanther.common.seq.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.config.AppConfigConstants;
import com.hcis.ipanther.common.seq.dao.SeqDao;
import com.hcis.ipanther.common.seq.entity.Seq;
import com.hcis.ipanther.common.seq.service.ISeqService;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chaos
 * @date 2013-3-21
 * @time 下午5:53:26
 *
 */
@Service
public class SeqServiceImpl implements ISeqService {

	@Autowired
	private SeqDao seqDao;
	
	/**
	 * 取一次就更新一次
	 * 更新逻辑为必须id一致，旧数据的next_num=新数据的current_num，version一致
	 * 如果不一致，则表明此数据已被更新过，要重新递归调用取
	 * @param seqCode 代码
	 * @param userId 更新人
	 */
	@Override
	public BigDecimal generatSeq(String seqCode,String userId){
		int seqRetryTime_Max=Integer.parseInt(AppConfig.getProperty("app",AppConfigConstants.SEQ_RETRYTIME));
		int seqRetryTime=0;
		BigDecimal seqNum=this.generatSeq(seqCode, userId, seqRetryTime_Max, seqRetryTime);
		return seqNum;
	}
	
	public BigDecimal generatSeq(String seqCode,String userId,int seqRetryTime_Max,int seqRetryTime){
		Seq seq=(Seq)seqDao.selectBySeqCode(seqCode);
		BigDecimal seqNum=seq.getNextNum();
		seq.setCurrentNum(seq.getCurrentNum().add(seq.getStepNum()));
		seq.setNextNum(seq.getNextNum().add(seq.getStepNum()));
		seq.setUpdatedby(userId);
		seq.setUpdateTime(Calendar.getInstance().getTime());
		int updateCount=seqDao.updateByPrimaryKeyAndNumSelective(seq);
		if(seqRetryTime<seqRetryTime_Max){
			if(updateCount==0){
				seqRetryTime=seqRetryTime+1;//计算重试次数
				seqNum=this.generatSeq(seqCode, userId,seqRetryTime_Max,seqRetryTime);
			}
		}
		else{
			seqNum=null;
		}
		return seqNum;
	}
	
	@Override
	public Seq getSeq(String id){
		return (Seq) seqDao.selectByPrimaryKey(id);
	}

	@Override
	public int addSeq(Seq seq){
		if(StringUtils.isEmpty(seq.getId())){
			seq.setId(Identities.uuid2());
		}
		seq.setDefaultValue();
		return this.seqDao.insertSelective(seq);
	}
	
	@Override
	public int updateSeq(Seq seq){
		seq.setUpdateTime(Calendar.getInstance().getTime());
		return this.seqDao.updateByPrimaryKeySelective(seq);
	}
	
	@Override
	public int deleteSeq(Seq seq){
		seq.setUpdateTime(Calendar.getInstance().getTime());
		return this.seqDao.deleteByLogic(seq);
	}
	
	@Override
	public List<Seq> listSeq(SearchParam searchParam){
		return this.seqDao.selectBySearchParam(searchParam);
	}

	public SeqDao getSeqDao() {
		return seqDao;
	}

	public void setSeqDao(SeqDao seqDao) {
		this.seqDao = seqDao;
	}

}
