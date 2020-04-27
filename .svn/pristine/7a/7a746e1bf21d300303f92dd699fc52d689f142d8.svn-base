/**
 * 
 */
package com.hcis.ipanther.common.seq.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.hcis.ipanther.common.seq.entity.Seq;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chaos
 * @date 2013-3-21
 * @time 下午5:52:01
 *
 */
public interface ISeqService {

	/**
	 * @param seq
	 * @return
	 */
	public int addSeq(Seq seq);

	/**
	 * @param seq
	 * @return
	 */
	public int updateSeq(Seq seq);

	/**
	 * @param seq
	 * @return
	 */
	public int deleteSeq(Seq seq);

	/**
	 * @param searchParam
	 * @return
	 */
	public List<Seq> listSeq(SearchParam searchParam);

	/**
	 * @param id
	 * @return
	 */
	public Seq getSeq(String id);

	/**
	 * @param seqCode
	 * @param userId
	 * @return
	 */
	public BigDecimal generatSeq(String seqCode, String userId);

}
