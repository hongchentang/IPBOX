package com.hcis.ipr.train.train.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.call.dao.ProcedureDao;
import com.hcis.ipr.intellectual.call.entity.PatentApplyer;
import com.hcis.ipr.train.train.dao.TrainDao;
import com.hcis.ipr.train.train.entity.Train;
import com.hcis.ipr.train.train.service.ITrainService;

@Service("trainService")
public class TrainServiceImpl extends BaseServiceImpl<Train> implements ITrainService {

	@Autowired
	private TrainDao trainDao;
	@Autowired
	private ProcedureDao procedureDao;

	// 报名培训班列表
	public List<Map<String, Object>> listTrain(SearchParam searchParam) {
		return trainDao.listTrain(searchParam);
	}

	@Override
	public MyBatisDao getBaseDao() {
		return trainDao;
	}

	@Override
	public Map<String, Object> selectTrainStatistics(Map<String, Object> args) {
		return trainDao.selectTrainStatistics(args);
	}

	@Override
	public Map<String, Object> selectTrainHoursStatistics(Map<String, Object> args) {
		return trainDao.selectTrainHoursStatistics(args);
	}

	@Override
	public List<Object> selectTrainStatisticsGroup(Map<String, Object> args) {
		return trainDao.selectTrainStatisticsGroup(args);
	}

	/**
	 * 按年度统计参加面授、远程课程学习学员人数
	 */
	@Override
	public List selectRegisterStatistics(Map<String, Object> args) {
		List<String> years = (List<String>) args.get("years");
		return this.getYearValue(trainDao.selectRegisterStatistics(args), years);
	}

	public List getYearValue(List<Map<String, Object>> resultList, List<String> years) {
		List list = new ArrayList();
		for (String y : years) {
			Integer value = 0;
			for (Map<String, Object> result : resultList) {
				if (result.get("YEAR") != null && y.equals(result.get("YEAR").toString())) {
					value = Integer.valueOf(result.get("STUDENT_COUNT").toString());
					break;
				}
			}
			list.add(value);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectTrainByYear(Map<String, Object> args) {
		return trainDao.selectTrainByYear(args);
	}

	@Override
	public List<Map<String, Object>> selectTrainByMonth(Map<String, Object> args) {
		return trainDao.selectTrainByMonth(args);
	}

	@Override
	public List<Map<String, Object>> selectCountByRegions(Map<String, Object> args) {
		return trainDao.selectCountByRegions(args);
	}

	@Override
	public List<Object> selectTrainStatisticsIndustryType(SearchParam searchParam) {
		return trainDao.selectTrainStatisticsIndustryType(searchParam);
	}

	@Override
	public List<Object> selectTrainStatisticsPersonType(SearchParam searchParam) {
		return trainDao.selectTrainStatisticsPersonType(searchParam);
	}

	@Override
	public GsonOption echartsPersonType(String ID) {
		List<DictEntry> dictNotParent = DictionaryUtils.getEntryListNotParent("USER_TYPE");
		String[] dictValue = new String[dictNotParent.size()];
		String[] dictName = new String[dictNotParent.size()];
		int sumData = 0;// 声明一个存储数据库查询出的数值的和
		for (Integer i = 0; i < dictNotParent.size(); i++) {
			dictValue[i] = dictNotParent.get(i).getDictValue();// 获取值
			dictName[i] = dictNotParent.get(i).getDictName();
		}
		SearchParam searchParam2 = new SearchParam();
		// 查询结果
		// charDatasByPersonType=trainService.selectTrainStatisticsIndustryType(searchParam2);
		List<Object> charDatasByPersonType = this.selectTrainStatisticsPersonType(searchParam2);
		// 声明list存储
		List<Object> list = new ArrayList<Object>();
		Map result = new HashMap();
		for (Object obj : charDatasByPersonType) {
			Map<String, Object> personcharData = (Map<String, Object>) obj;
			result.put(personcharData.get("newkey"), personcharData.get("value"));
		}
		for (Integer i = 0; i < dictNotParent.size() - 1; i++) {
			// list.add(new Data(dictNotParent.get(i).getDictName().toString(),

			// 注释
			// ((Long)
			// result.get(dictNotParent.get(i).getDictValue())).intValue()));
			// sumData+=((Long)
			// result.get(dictNotParent.get(i).getDictValue())).intValue();
		}

		List<PatentApplyer> applyer = procedureDao.getPatentApplyer(ID);

		for (int i = 0; i < applyer.size(); i++) {
			list.add(new Data(applyer.get(i).getApplyer(), applyer.get(i).getCount()));
		}

		GsonOption optionPerson = new GsonOption();
		// 设置标题及样式
		optionPerson.title().text("专利申请人/专利权人统计").x(X.center).textStyle().color("#188DD2");
		// 背景颜色及网格样式
		// optionPerson.backgroundColor("#188DD2");
		optionPerson.toolbox().show(true).feature(
				/*
				 * Tool.mark, Tool.dataView,
				 */
				new MagicType(Magic.pie, Magic.funnel).show(true), Tool.restore, Tool.saveAsImage);
		// 提示信息
		optionPerson.tooltip().trigger(Trigger.item).formatter("{b}: {c}个 ,({d}%)");
		optionPerson.itemStyle().normal().label().show(true).textStyle().fontSize(7);
		/*
		 * if (list.size() <= 0||sumData==0) { list.add(new
		 * Data("目前占无数据,其他数据为0", "100"));
		 * optionPerson.tooltip().trigger(Trigger.item).formatter("{b}"); }
		 */
		optionPerson.itemStyle().normal().label().textStyle().fontSize(10);
		Pie pie = new Pie();

		pie.name("专利申请人/专利权人统计");
		pie.setType(SeriesType.pie);
		pie.hoverable(true);
		pie.radius("60%").data(list.toArray()).hoverable(true);
		pie.hoverable(true);
		optionPerson.series(pie);
		return optionPerson;
	}

	@Override
	public GsonOption echartsIndustryType() {
		// 行业类型
		/*
		 * 处理数据，没有人数的行业类型设置为0
		 */
		List<DictEntry> dictEntries = DictionaryUtils.getEntryList("IPR_INDUSTRY_TYPE");
		// 存放字典编码的数组
		Random rd = new Random();
		LinkedHashMap<String, Integer> dataIndustry = new LinkedHashMap<String, Integer>();
		String[] code = new String[dictEntries.size()];
		for (Integer i = 0; i < dictEntries.size(); i++) {
			code[i] = dictEntries.get(i).getDictValue();// 获取值
		}
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("typeCodes", code);
		List<Object> charDatasByIndustryType = this.selectTrainStatisticsIndustryType(searchParam);
		for (Object obj : charDatasByIndustryType) {
			Map<String, Object> newcharData = (Map<String, Object>) obj;
			for (Integer i = 0; i < dictEntries.size(); i++) {
				// dataIndustry.put(dictEntries.get(i).getDictName().toString(),
				// ((BigDecimal)newcharData.get(dictEntries.get(i).getDictValue())).intValue());
				// dataIndustry.put(dictEntries.get(i).getDictName().toString(),
				// (rd.nextInt(10)*i)+6);
			}
		}
		// 模拟数据
		dataIndustry.put("水利、环境和公共设施管理业", 54);
		dataIndustry.put("居民服务、修理和其他服务业", 14);
		dataIndustry.put("卫生和社会工作", 56);
		dataIndustry.put("建筑业", 78);
		dataIndustry.put("教育", 45);
		dataIndustry.put("信息传输、软件和信息技术服务业", 24);
		dataIndustry.put("制造业", 22);
		dataIndustry.put("电力、热力、燃气及水生产和供应业", 89);
		dataIndustry.put("采矿业", 78);
		dataIndustry.put("交通运输、仓储和邮政业", 59);
		dataIndustry.put("公共管理、社会保障和社会组织", 98);
		dataIndustry.put("文化、体育和娱乐业", 56);
		dataIndustry.put("房地产业", 89);
		dataIndustry.put("农、林、牧、渔业", 52);
		dataIndustry.put("住宿和餐饮业", 98);
		dataIndustry.put("国际组织", 11);
		dataIndustry.put("金融业", 32);
		dataIndustry.put("租赁和商务服务业", 44);
		dataIndustry.put("科学研究和技术服务业", 73);
		dataIndustry.put("批发和零售业", 45);
		/*
		 * 用图表的统计数据构建图表所需的json数据
		 */
		GsonOption optionIndustry = new GsonOption();
		// 设置标题及样式
		optionIndustry.title().text("行业培训人次趋势图").x(X.center).textStyle().color("#188DD2");
		// 背景颜色及网格样式
		// optionIndustry.backgroundColor("#188DD2").grid().backgroundColor("#46A6DE").setHeight("210");
		optionIndustry.grid().setHeight("210");
		// 设置工具栏
		List<Object> colorsPerson = new ArrayList<Object>();
		optionIndustry.toolbox().setColor(colorsPerson);
		optionIndustry.toolbox().show(true).feature(
				/*
				 * Tool.mark, Tool.dataView,
				 */
				new MagicType(Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
		// 提示信息
		optionIndustry.tooltip().trigger(Trigger.axis).formatter("{b}行业 : {c}人");
		/*
		 * 横坐标和纵坐标的数据及样式
		 */
		ValueAxis valueAxisId = new ValueAxis();
		valueAxisId.axisLabel().formatter("{value}人");
		optionIndustry.yAxis(valueAxisId);
		CategoryAxis industryAxis = new CategoryAxis();
		industryAxis.axisLabel().formatter("{value}");
		industryAxis.axisLabel().textStyle().align(X.left);
		industryAxis.axisLabel().rotate(-30);
		industryAxis.axisLabel().textStyle().setFontSize(11);
		;
		industryAxis.type(AxisType.category);
		industryAxis.data(dataIndustry.keySet().toArray());
		optionIndustry.xAxis(industryAxis);

		// 柱形图
		Bar bar = new Bar();
		bar.name("行业培训人数的趋势图").data(dataIndustry.values().toArray());
		optionIndustry.series(bar);
		return optionIndustry;
	}
}
