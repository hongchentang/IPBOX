package com.hcis.ipr.train.train.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository("trainDao")
public class TrainDao extends MyBatisDao {

	public List<Map<String, Object>> listTrain(SearchParam searchParam) {
		return this.selectForList(namespace + ".listTrain", searchParam);
	}

	/**
	 * 统计培训信息
	 * 
	 * @param args
	 * @return
	 */
	public Map<String, Object> selectTrainStatistics(Map<String, Object> args) {
		return (Map<String, Object>) this.selectForOneVO(namespace + ".selectTrainStatistics", args);
	}

	/**
	 * 统计培训学时信息
	 * 
	 * @param args
	 * @return
	 */
	public Map<String, Object> selectTrainHoursStatistics(Map<String, Object> args) {
		return (Map<String, Object>) this.selectForOneVO(namespace + ".selectTrainHoursStatistics", args);
	}

	/**
	 * 以年度为单位统计培训信息
	 * 
	 * @param args
	 * @return
	 */
	public List<Object> selectTrainStatisticsGroup(Map<String, Object> args) {
		return (List<Object>) this.selectForList(namespace + ".selectTrainStatisticsGroup", args);
	}

	/**
	 * 按年度统计参加面授、远程课程学习学员人数
	 */
	public List<Map<String, Object>> selectRegisterStatistics(Map<String, Object> args) {

		return this.selectForList(namespace + ".registerStatistics", args);
	}

	/**
	 * 年度统计情况年度面授培训、远程培训、总培训人数
	 * 
	 * @param args
	 * @return
	 */
	public List<Map<String, Object>> selectTrainByYear(Map<String, Object> args) {
		return this.selectForList(namespace + ".selectTrainByYear", args);
	}

	public List<Map<String, Object>> selectTrainByMonth(Map<String, Object> args) {
		return this.selectForList(namespace + ".selectTrainByMonth", args);
	}

	public List<Map<String, Object>> selectCountByRegions(Map<String, Object> args) {
		return this.selectForList(namespace + ".selectCountByRegions", args);
	}

	// 行业类型统计
	public List<Object> selectTrainStatisticsIndustryType(SearchParam searchParam) {
		return (List<Object>) this.selectForList(namespace + ".selectTrainStatisticsIndustryType", searchParam);
	}

	// 人才类型统计
	public List<Object> selectTrainStatisticsPersonType(SearchParam searchParam) {
		return (List<Object>) this.selectForList(namespace + ".selectTrainStatisticsPersonType", searchParam);
	}

}