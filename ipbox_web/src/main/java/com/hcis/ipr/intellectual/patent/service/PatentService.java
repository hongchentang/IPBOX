package com.hcis.ipr.intellectual.patent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hcis.datas.DataSource;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.patent.entity.Patent;

public interface PatentService extends IBaseService<Patent> {

	/**
	 * 批量删除专利
	 * 
	 * @param patentIds
	 */
	void deleteByIds(List<String> patentIds);

	/**
	 * 新增专利
	 * 
	 * @param patent
	 */
	void addPatent(Patent patent, MultiValueMap<String, MultipartFile> fileMap);

	/**
	 * 修改专利
	 * 
	 * @param patent
	 */
	void updatePatent(Patent patent, MultiValueMap<String, MultipartFile> fileMap);

	void updatePatentisCost(Patent patent);

	/**
	 * 查询专利的信息，包括专利基本信息，代理机构信息，附件信息
	 * 
	 * @param id
	 *            专利id
	 * @return
	 */
	Patent getPatentInfo(String id);

	/**
	 * 查询列表
	 * 
	 * @param searchParam
	 * @return
	 */
	List<Patent> patentList(SearchParam searchParam);

	/**
	 * 批量修改专利信息
	 * 
	 * @param updateList
	 */
	void updateBatch(List<Patent> updateList);

	List<Patent> listConcatMongoPatent();

	String readExcelFile(MultipartFile file);

	void refreshLegalStatus(List<String> ids);

	/**
	 *
	 * @param patent
	 */
	void costedPatent(Patent patent);

	/**
	 * 查询专利所有的文件路径
	 * @param id
	 * @return
	 */
    List<String> getPatentFiles(String id);


    Map test();

    List<Patent> selectByAppNuber(List<String> appNumber);
	List<Patent> selectByImportAppNuber(List<String> appNumber);
}
