package com.hcis.ipr.train.train.service;

import java.util.List;
import java.util.Map;

import com.github.abel533.echarts.json.GsonOption;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.train.entity.Train;

public interface ITrainService extends IBaseService<Train> {

	public List<Map<String, Object>> listTrain(SearchParam searchParam);

	/**
	 * 统计培训信息
	 * 
	 * @param args
	 * @return
	 */
	public Map<String, Object> selectTrainStatistics(Map<String, Object> args);

	/**
	 * 统计培训学时信息
	 * 
	 * @param args
	 * @return
	 */
	public Map<String, Object> selectTrainHoursStatistics(Map<String, Object> args);

	/**
	 * 以年度为单位统计培训信息
	 * 
	 * @param args
	 * @return
	 */
	public List<Object> selectTrainStatisticsGroup(Map<String, Object> args);

	/**
	 * 年度统计情况年度面授培训、远程培训、总培训人数
	 */
	public List selectRegisterStatistics(Map<String, Object> args);

	/**
	 * 按年度统计参加面授、远程课程学习学员人数 Select train by year.
	 *
	 * @param args
	 *            the args
	 * @return the map
	 */
	public List<Map<String, Object>> selectTrainByYear(Map<String, Object> args);

	public List<Map<String, Object>> selectTrainByMonth(Map<String, Object> args);

	public List<Map<String, Object>> selectCountByRegions(Map<String, Object> args);

	/**
	 * 行业类型查询的培训信息
	 * 
	 * @param searchParam
	 * @return
	 */
	public List<Object> selectTrainStatisticsIndustryType(SearchParam searchParam);

	/**
	 * 人才类别查询培训信息
	 * 
	 * @param searchParam
	 * @return
	 */
	public List<Object> selectTrainStatisticsPersonType(SearchParam searchParam);

	public GsonOption echartsPersonType(String ID);

	/**
	 * 行业类型
	 * 
	 * @return
	 */
	public GsonOption echartsIndustryType();
}
