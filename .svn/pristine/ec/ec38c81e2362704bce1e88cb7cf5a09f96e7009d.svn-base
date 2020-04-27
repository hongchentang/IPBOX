package com.hcis.ipr.intellectual.cost.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.cost.dto.PatentCostDetailDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostListDto;
import com.hcis.ipr.intellectual.cost.entity.PatentCost;
import com.hcis.ipr.intellectual.cost.entity.PatentCostPayment;

public interface PatentCostService extends IBaseService<PatentCost> {

	/**
	 * 查询
	 * 
	 * @param searchParam
	 * @return
	 */
	List<PatentCostListDto> selectList(SearchParam searchParam);

	/**
	 * 新增费用
	 * 
	 * @param patentCost
	 * @param file
	 */
	void addCost(PatentCost patentCost, MultipartFile file) throws IOException;

	void addCostFee(PatentCost patentCost) throws IOException;

	/**
	 *
	 * @param id
	 */
	void deleteByIds(String id);

	/**
	 *
	 * @param patentCost
	 * @param file
	 */
	void updateCost(PatentCost patentCost, MultipartFile file) throws IOException;

	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	PatentCostDetailDto getPatentDetail(String id);

	/**
	 * 缴纳费用
	 * 
	 * @param patentCostPayment
	 */
	void payable(PatentCostPayment patentCostPayment);

	List<PatentCost> getPatentId(String id);

	/**
	 * 发送邮件
	 * 
	 * @param id
	 */
	void sendEmail(String id, String email, String theme, String text) throws Exception;

	/*根据patentID查询费用数据*/
	 List<PatentCost> selectListByPatentId(SearchParam searchParam);
   /*根据patentID删除数据逻辑删除*/
  int  deletByPatentId(PatentCost patentCost);
}
