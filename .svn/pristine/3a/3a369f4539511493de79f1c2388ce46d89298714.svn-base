package com.hcis.ipr.train.train.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.hcis.ipanther.common.dept.utils.DepartmentConstants;
import com.hcis.ipanther.common.dept.utils.DepartmentUtils;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.security.utils.SecurityRoleUtils;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.entity.Response;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.evalua.effect.entity.CourseEffectEvalua;
import com.hcis.ipr.evalua.effect.entity.TeachingEvalua;
import com.hcis.ipr.evalua.effect.service.ICourseEffectEvaluaService;
import com.hcis.ipr.evalua.effect.service.ITeachingEvaluaService;
import com.hcis.ipr.intellectual.call.entity.PatentCost;
import com.hcis.ipr.intellectual.call.service.ProcedureService;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.register.service.ITrainRegisterService;
import com.hcis.ipr.train.train.entity.Train;
import com.hcis.ipr.train.train.service.ITrainCourseService;
import com.hcis.ipr.train.train.service.ITrainService;

@RequestMapping({ "/train/train", "/cms/train" })
@Controller
public class TrainController extends BaseController {

	@Autowired
	private ITrainService trainService;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private ITrainCourseService trainCourseService;
	@Autowired
	private ITrainRegisterService trainRegisterService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICourseEffectEvaluaService courseEffectEvaluaService;
	@Autowired
	private ITeachingEvaluaService teachingEvaluaService;
	@Autowired
	private ProcedureService procedureService;

	// 培训班列表
	@RequestMapping("listTrain")
	public ModelAndView listTrain(@ModelAttribute("searchParam") SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listTrain", trainService.list(searchParam));
		return modelAndView;
	}

	// 申报校验
	@RequestMapping("declaration")
	@ResponseBody
	public Response test(@ModelAttribute("searchParam") SearchParam searchParam) {
		List<Train> list = trainService.list(searchParam);
		if (list.size() == 0) {
			return new Response("200");
		} else {
			return new Response("300");
		}
	}

	// 培训班列表
	@RequestMapping("listAllTrain")
	public ModelAndView listAllTrain(@ModelAttribute("searchParam") SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		LoginUser loginUser = LoginUser.loginUser(request);
		if (DepartmentUtils.isTrainOrg(loginUser.getDeptType())) {
			searchParam.getParamMap().put("declareDept", loginUser.getFirstDeptId());
		}
		modelAndView.addObject("listTrain", trainService.list(searchParam));
		return modelAndView;
	}

	// 项目Detail培训列表
	@RequestMapping("listTrainDetail")
	public ModelAndView listTrainDetail(@ModelAttribute("searchParam") SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listTrain", trainService.list(searchParam));
		return modelAndView;
	}

	// 编辑培训班信息
	@RequestMapping("goAddTrain")
	public ModelAndView goAddTrain(Train train) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/train/train/addTrain");
		if (null != train && StringUtils.isNotBlank(train.getId())) {
			train = trainService.read(train.getId());
			SearchParam sp = new SearchParam();
			sp.getParamMap().put("trainId", train.getId());
			modelAndView.addObject("courseList", trainCourseService.selectCourseByTrain(sp));
		}
		modelAndView.addObject("train", train);
		return modelAndView;
	}

	@RequestMapping("view")
	public ModelAndView view(Train train) {
		ModelAndView modelAndView = new ModelAndView();
		if (null != train && StringUtils.isNotBlank(train.getId())) {
			train = trainService.read(train.getId());
			SearchParam sp = new SearchParam();
			sp.getParamMap().put("trainId", train.getId());
			modelAndView.addObject("courseList", trainCourseService.selectCourseByTrain(sp));
		}
		modelAndView.addObject("train", train);
		return modelAndView;
	}

	// 保存培训班信息
	@RequestMapping("saveTrain")
	@ResponseBody
	public AjaxReturnObject saveTrain(@ModelAttribute("train") Train train, SearchParam searchParam) {
		LoginUser loginUser = LoginUser.loginUser(request);
		int statusCode = 200;
		String msg = "操作成功！";
		if (StringUtils.isNotBlank(train.getId())) {
			trainService.update(train, loginUser.getId());
		} else {
			trainService.create(train, loginUser.getId());
		}
		// 保存培训班与课程的关联
		Object o = searchParam.getParamMap().get("courseIds");
		trainCourseService.saveTrainCourse(train.getId(), o.toString(), loginUser);
		return new AjaxReturnObject(statusCode, msg);
	}

	// 终止培训班报名
	@RequestMapping("stopApply")
	@ResponseBody
	public AjaxReturnObject stopApply(@ModelAttribute("train") Train train) {
		LoginUser loginUser = LoginUser.loginUser(request);
		int statusCode = 200;
		String msg = "操作成功！";
		train.setIsStopApply("1");// 终止培训班报名
		trainService.update(train, loginUser.getId());
		return new AjaxReturnObject(statusCode, msg);
	}

	// 选择课程
	@RequestMapping("selectCourse")
	public ModelAndView selectCourse(@ModelAttribute("searchParam") SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/train/train/selectCourse");
		searchParam.getParamMap().put("status", "02");
		Object courseIdObj = searchParam.getParamMap().get("paramCourseIds");
		if (null != courseIdObj && StringUtils.isNotBlank(courseIdObj.toString())) {
			String courseIds = "'" + courseIdObj.toString().replaceAll(",", "','") + "'";
			System.out.println(courseIds);
			searchParam.getParamMap().put("courseIds", courseIds);
		}
		// 若为知识产权局则显示所有可以分配教师的课程
		if (!SecurityRoleUtils.hasRole(RoleConstant.GD_IPR_LEADER, LoginUser.loginUser(request).getId())) {
			searchParam.getParamMap().put("declareDept", LoginUser.loginUser(request).getFirstDeptId());
		}
		modelAndView.addObject("listCourse", courseService.list(searchParam));
		return modelAndView;
	}

	// 删除培训班
	@RequestMapping("deleteTrain")
	@ResponseBody
	public AjaxReturnObject deleteTrain(@ModelAttribute("train") Train train) {
		LoginUser loginUser = LoginUser.loginUser(request);
		int statusCode = 200;
		String msg = "操作成功！";
		// 删除培训班与课程关联
		trainCourseService.deleteByTrainId(train.getId());
		// 删除培训班
		trainService.delete(train, loginUser.getId());
		return new AjaxReturnObject(statusCode, msg);
	}

	/**
	 * CMS首页人才评估系统统计信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("statisticsIndex")
	public ModelAndView statisticsIndex() throws IOException {
		ModelAndView mv = new ModelAndView("/train/train/cms/statisticsIndex");
		Map<String, Object> args = new HashMap<String, Object>();
		int yearNum = 4;// 年度范围，当前年度前X年
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);// 当前年度
		int minYear = currentYear - yearNum;// 最小年度
		/*
		 * 培训总体情况
		 */
		Map<String, Object> sumStatistics = trainService.selectTrainStatistics(args);
		Map<String, Object> studyHoursStatistics = trainService.selectTrainHoursStatistics(args);
		sumStatistics.put("studyHoursCount",
				null == studyHoursStatistics ? "0" : studyHoursStatistics.get("studyHoursCount"));
		/*
		 * 上年培训情况
		 */
		args.put("year", currentYear - 1);
		Map<String, Object> lastYearStatistics = trainService.selectTrainStatistics(args);
		Map<String, Object> lastYearStudyHoursStatistics = trainService.selectTrainHoursStatistics(args);
		lastYearStatistics.put("studyHoursCount",
				null == lastYearStudyHoursStatistics ? "0" : lastYearStudyHoursStatistics.get("studyHoursCount"));

		mv.addObject("sumStatistics", sumStatistics);
		mv.addObject("lastYearStatistics", lastYearStatistics);

		/*
		 * 加载图表的统计数据
		 */
		args.clear();
		args.put("minYear", minYear);
		List<Object> charDatas = trainService.selectTrainStatisticsGroup(args);

		/*
		 * 处理数据，没有人数的年度设置为0
		 */
		LinkedHashMap<String, Integer> data = new LinkedHashMap<String, Integer>();
		for (Integer i = minYear; i <= currentYear; i++) {
			data.put(i.toString(), 0);
		}
		for (Object obj : charDatas) {
			Map<String, Object> charData = (Map<String, Object>) obj;
			data.put(charData.get("year").toString(), ((Long) charData.get("studentCount")).intValue());
		}

		/*
		 * 用图表的统计数据构建图表所需的json数据
		 */
		GsonOption option = new GsonOption();
		// 设置标题及样式
		option.title().text("年度培训人次趋势图").x(X.center).textStyle().color("white");
		// 背景颜色及网格样式
		option.backgroundColor("#188DD2").grid().backgroundColor("#46A6DE");
		// 设置工具栏
		List<Object> colors = new ArrayList<Object>();
		colors.add("white");
		colors.add("white");
		colors.add("white");
		colors.add("white");
		option.toolbox().setColor(colors);
		option.toolbox().show(true).feature(
				/*
				 * Tool.mark, Tool.dataView,
				 */
				new MagicType(Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
		// 提示信息
		option.tooltip().trigger(Trigger.axis).formatter("{b}年度 : {c}人");

		/*
		 * 横坐标和纵坐标的数据及样式
		 */
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.axisLabel().formatter("{value}人");
		valueAxis.axisLine().lineStyle().color("#FF7F50");
		valueAxis.splitLine().lineStyle().color("white");
		valueAxis.axisLabel().textStyle().color("white");
		option.yAxis(valueAxis);

		CategoryAxis yearAxis = new CategoryAxis();
		yearAxis.axisLine().lineStyle().color("#FF7F50");
		yearAxis.splitLine().lineStyle().color("white");
		yearAxis.axisLabel().formatter("{value}");
		yearAxis.axisLabel().textStyle().color("white");
		yearAxis.boundaryGap(false);
		yearAxis.data(data.keySet().toArray());
		option.xAxis(yearAxis);

		// 曲线图
		Line line = new Line();
		line.smooth(true).name("年度面授培训、远程培训、总培训人数统计").data(data.values().toArray()).itemStyle().normal().lineStyle()
				.color("#ACF444");
		option.series(line);
		mv.addObject("option", option);
		return mv;
	}

	@RequestMapping("statisticsTrainYear")
	public ModelAndView statisticsTrainYear() throws IOException {
		ModelAndView mv = new ModelAndView("/train/train/cms/statisticsTrainYear");
		Map<String, Object> args = new HashMap<String, Object>();

		LoginUser loginUser = LoginUser.loginUser(request);
		// 当前登录人是培训机构的，则只显示本单位的
		/*
		 * if (null != loginUser &&
		 * loginUser.getDeptType().equals(DepartmentConstants.
		 * DEPT_TYPE_TRAIN_ORG) &&
		 * SecurityRoleUtils.isTrainAdmin(loginUser.getId())) {
		 * args.put("deptId", loginUser.getId()); }
		 */

		List<PatentCost> patentCost = procedureService.getPatentCost(loginUser.getFirstDeptId());
		int length = patentCost.size();
		int size = length > 5 ? 5 : length;
		String[] year = new String[size];

		for (int i = 0; i < size; i++) {
			year[i] = patentCost.get(i).getYears();
		}
		/* 代理费、 官费、年费，其他费 */

		/*
		 * List<Map<String, Object>> yearTrain =
		 * trainService.selectTrainByYear(args);
		 */
		mv.addObject("years", JsonUtil.toJson(year));
		// 代理费、
		List<String> agency = new ArrayList<String>();
		for (int i = 0; i < patentCost.size(); i++) {
			agency.add(patentCost.get(i).getOfficialfees());
		}
		// 官费、
		List<String> officialfees = new ArrayList<String>();
		for (int i = 0; i < patentCost.size(); i++) {
			officialfees.add(patentCost.get(i).getAnnualfee());
		}
		// 年费

		List<String> annualfee = new ArrayList<String>();
		for (int i = 0; i < patentCost.size(); i++) {
			annualfee.add(patentCost.get(i).getAgency());
		}
		// 其他费
		List<String> otherfee = new ArrayList<String>();
		for (int i = 0; i < patentCost.size(); i++) {
			otherfee.add(patentCost.get(i).getOtherfee());
		}
		mv.addObject("agency", JsonUtil.toJson(agency));
		mv.addObject("officialfees", JsonUtil.toJson(officialfees));
		mv.addObject("annualfee", JsonUtil.toJson(annualfee));
		mv.addObject("otherfee", JsonUtil.toJson(otherfee));
		return mv;
	}

	@RequestMapping("statisticsTrainMonth")
	public ModelAndView statisticsTrainmMonth(@ModelAttribute("searchParam") SearchParam searchParam)
			throws IOException {
		ModelAndView mv = new ModelAndView("/train/train/cms/statisticsTrainMonth");
		Map<String, Object> args = searchParam.getParamMap();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);// 当前年度

		int yearNum = 4;// 年度范围，当前年度前X年
		int minYear = currentYear - yearNum;// 最小年度

		int[] years = new int[yearNum + 1];
		int index = 0;
		for (int i = minYear; i <= currentYear; i++) {
			years[index] = new Integer(i);
			index++;
		}
		mv.addObject("years", years);

		LoginUser loginUser = LoginUser.loginUser(request);
		// 当前登录人是培训机构的，则只显示本单位的
		if (null != loginUser && loginUser.getDeptType().equals(DepartmentConstants.DEPT_TYPE_TRAIN_ORG)
				&& SecurityRoleUtils.isTrainAdmin(loginUser.getId())) {
			args.put("deptId", loginUser.getId());
		}

		if (!args.containsKey("year")) {
			args.put("year", currentYear);
		}
		mv.addObject("currentYear", args.get("year").toString().matches("\\d{4}") ? args.get("year") : currentYear);
		int[] month = new int[12];
		index = 0;
		for (int i = 1; i <= 12; i++) {
			month[index] = i;
			index++;
		}
		args.put("months", month);
		List<Map<String, Object>> monthTrain = trainService.selectTrainByMonth(args);

		mv.addObject("month", JsonUtil.toJson(month));
		// 面授培训、
		List<Integer> ftftList = new ArrayList<Integer>();
		// 远程培训、
		List<Integer> rlist = new ArrayList<Integer>();
		// 总培训人数
		List<Integer> sumlist = new ArrayList<Integer>();
		if (CollectionUtils.isNotEmpty(monthTrain)) {
			for (Map<String, Object> map : monthTrain) {
				Integer ftftNum = new Integer(ObjectUtils.toString(map.get("typeF")));
				Integer rNum = new Integer(ObjectUtils.toString(map.get("typeR")));
				Integer sumNum = new Integer(ftftNum + rNum);
				ftftList.add(ftftNum);
				rlist.add(rNum);
				sumlist.add(sumNum);
			}
		}
		mv.addObject("ftftList", JsonUtil.toJson(ftftList));
		mv.addObject("rlist", JsonUtil.toJson(rlist));
		mv.addObject("sumlist", JsonUtil.toJson(sumlist));
		return mv;
	}

	@RequestMapping("statisticsRegisterCount")
	public ModelAndView statisticsRegisterCount(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView("/train/train/cms/selectCount");
		Map<String, Object> map = searchParam.getParamMap();

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);// 当前年度

		int yearNum = 4;// 年度范围，当前年度前X年
		int minYear = currentYear - yearNum;// 最小年度

		int[] years = new int[yearNum + 1];
		int index = 0;
		for (int i = minYear; i <= currentYear; i++) {
			years[index] = new Integer(i);
			index++;
		}
		modelAndView.addObject("years", years);

		if (!map.containsKey("year")) {
			map.put("year", currentYear);
		}
		LoginUser loginUser = LoginUser.loginUser(request);
		// 当前登录人是培训机构的，则只显示本单位的
		if (null != loginUser && loginUser.getDeptType().equals(DepartmentConstants.DEPT_TYPE_TRAIN_ORG)
				&& SecurityRoleUtils.isTrainAdmin(loginUser.getId())) {
			map.put("deptId", loginUser.getId());
		}
		modelAndView.addObject("currentYear", map.get("year"));
		int[] month = new int[12];
		index = 0;
		for (int i = 1; i <= 12; i++) {
			month[index] = i;
			index++;
		}
		map.put("months", month);

		List<String> userList = new ArrayList<String>();
		List<String> trainList = new ArrayList<String>();
		List<Map<String, Object>> listTrainRegister = trainRegisterService.selectCountRegister(map);
		List<Map<String, Object>> listUserRegister = userService.selectCountRegister(map);
		if (CollectionUtils.isNotEmpty(listTrainRegister)) {
			for (Map<String, Object> trainRegister : listTrainRegister) {
				trainList.add(ObjectUtils.toString(trainRegister.get("SUM_COUNT")));
			}
		}
		if (CollectionUtils.isNotEmpty(listUserRegister)) {
			for (Map<String, Object> userRegister : listUserRegister) {
				userList.add(ObjectUtils.toString(userRegister.get("SUM_USER")));
			}
		}
		try {
			modelAndView.addObject("userList", JsonUtil.toJson(userList));
			modelAndView.addObject("countlist", JsonUtil.toJson(trainList));
			modelAndView.addObject("month", JsonUtil.toJson(month));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping("statisticsRegionCount")
	public ModelAndView statisticsRegionCount(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView("/train/train/cms/selectRegions");
		Map<String, Object> map = searchParam.getParamMap();

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);// 当前年度
		List<String> yearList = new ArrayList<String>();// 包含日期格式的
														// YYYY-MM-dd(年份-01-01)

		int yearNum = 4;// 年度范围，当前年度前X年
		int minYear = currentYear - yearNum;// 最小年度

		String[] years = new String[yearNum + 1];
		int index = 0;
		for (int i = minYear; i <= currentYear; i++) {
			years[index] = new Integer(i).toString();
			index++;
		}
		for (String i : years) {
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = time.parse(i + "-01-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			yearList.add(time.format(date));
		}
		// modelAndView.addObject("years", years);
		map.put("years", years);
		if (!map.containsKey("year")) {
			map.put("year", currentYear);
		}
		Random rd = new Random();
		modelAndView.addObject("currentYear", map.get("year"));
		// 数据字典
		/*
		 * 社会公众 public 6 领导干部 cadre 5 知识产权师资学员 teacher 4 知识产权服务业人员 serve 3
		 * 企事业单位知识产权从业人员 career 2 知识产权行政管理和执法人员 manage 1
		 * 
		 */
		List<String> regionList = new ArrayList<String>();// 含广东省 以及地市的 list
															// 区域名称
		List<String> dictEntryList = new ArrayList<String>();// 完整的 USER_TYPE 一级
																// list name
		List<String> dictEntryValueList = new ArrayList<String>();// 完整的
																	// USER_TYPE
																	// 一级 list
																	// value
		for (int n = 6; n >= 1; n--) {
			DictEntry dictEntry = DictionaryUtils.getEntry("USER_TYPE", Integer.toString(n));
			dictEntryList.add(dictEntry.getDictName());
			dictEntryValueList.add(dictEntry.getDictValue());
		}

		LoginUser loginUser = LoginUser.loginUser(request);
		// 当前登录人是培训机构的，则只显示本单位的
		if (null != loginUser && loginUser.getDeptType().equals(DepartmentConstants.DEPT_TYPE_TRAIN_ORG)
				&& SecurityRoleUtils.isTrainAdmin(loginUser.getId())) {
			map.put("deptId", loginUser.getId());
		}
		List<Map<String, Object>> listTrainRegister = trainService.selectCountByRegions(map);
		List<String> regionAllList = RegionsUtil.getListNextChildRegionsCode(RegionsUtil.getGDRegionsCode());// 只包含省
																												// 市
																												// 的code
		Map<String, Integer> dataListMap = new HashMap<String, Integer>();// 原始数据(包含全部有值的)
		Map<String, Integer> dataListMapOfRegion = new HashMap<String, Integer>();// 二次数据(包含全部有值的
																					// 并合并区级数据的)
		// ===================================================================================================================================================
		/**
		 * 只处理有值的数据 处理完成后 类型为 Map key : yearsKey+regionKey+ 数据字典(6大类型)
		 */
		for (Map<String, Object> tempData : listTrainRegister) {
			String yearsKey = ObjectUtils.toString(tempData.get("YEARS"));
			String regionKey = ObjectUtils.toString(tempData.get("REGIONS_CODE"));
			String userType = ObjectUtils.toString(tempData.get("USER_TYPE"));
			// String
			// userCount=ObjectUtils.toString(tempData.get("USER_COUNT"));
			String userCount = String.valueOf(rd.nextInt(10) * 2);
			int intPublic = 0, intCadre = 0, intTeacher = 0, intServe = 0, intCareer = 0, intManage = 0;

			if (StringUtils.isNotEmpty(userType)) {
				List<String> codeList = new ArrayList<String>();
				;
				try {
					codeList = JsonUtil.fromJson(userType, List.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 处理数据
				for (String codeTemp : codeList) {
					DictEntry dictEntry = DictionaryUtils.getEntry("USER_TYPE", codeTemp);
					if (dictEntry != null) {
						int value = 0;

						if (StringUtils.equals(dictEntry.getParentValue(), "6")) {
							intPublic += Integer.parseInt(userCount);
							if (dataListMap.containsKey(yearsKey + regionKey + dictEntry.getParentValue())) {
								value = dataListMap.get(yearsKey + regionKey + dictEntry.getParentValue());
							}
							value = value + intPublic;
							dataListMap.put(yearsKey + regionKey + dictEntry.getParentValue(), value);
						} else if (StringUtils.equals(dictEntry.getParentValue(), "5")) {
							intCadre += Integer.parseInt(userCount);

							if (dataListMap.containsKey(yearsKey + regionKey + dictEntry.getParentValue())) {
								value = dataListMap.get(yearsKey + regionKey + dictEntry.getParentValue());
							}
							value = value + intCadre;
							dataListMap.put(yearsKey + regionKey + dictEntry.getParentValue(), value);
						} else if (StringUtils.equals(dictEntry.getParentValue(), "4")) {
							intTeacher += Integer.parseInt(userCount);

							if (dataListMap.containsKey(yearsKey + regionKey + dictEntry.getParentValue())) {
								value = dataListMap.get(yearsKey + regionKey + dictEntry.getParentValue());
							}
							value = value + intTeacher;
							dataListMap.put(yearsKey + regionKey + dictEntry.getParentValue(), value);
						} else if (StringUtils.equals(dictEntry.getParentValue(), "3")) {
							intServe += Integer.parseInt(userCount);

							if (dataListMap.containsKey(yearsKey + regionKey + dictEntry.getParentValue())) {
								value = dataListMap.get(yearsKey + regionKey + dictEntry.getParentValue());
							}
							value = value + intServe;
							dataListMap.put(yearsKey + regionKey + dictEntry.getParentValue(), value);
						} else if (StringUtils.equals(dictEntry.getParentValue(), "2")) {
							intCareer += Integer.parseInt(userCount);

							if (dataListMap.containsKey(yearsKey + regionKey + dictEntry.getParentValue())) {
								value = dataListMap.get(yearsKey + regionKey + dictEntry.getParentValue());
							}
							value = value + intCareer;
							dataListMap.put(yearsKey + regionKey + dictEntry.getParentValue(), value);
						} else if (StringUtils.equals(dictEntry.getParentValue(), "1")) {
							intCareer += Integer.parseInt(userCount);

							if (dataListMap.containsKey(yearsKey + regionKey + dictEntry.getParentValue())) {
								value = dataListMap.get(yearsKey + regionKey + dictEntry.getParentValue());
							}
							value = value + intCareer;
							dataListMap.put(yearsKey + regionKey + dictEntry.getParentValue(), value);
						}
					}

				}

			}
		}
		// ===================================================================================================================================================
		// ===================================================================================================================================================
		/**
		 * 过滤 省市区 保留省级 市级 合并区级 数据
		 *//*
			 * for (String yearkey : years) { for (String code : regionAllList)
			 * { Regions regions = RegionsUtil.getRegions(code); for (String
			 * dictEntryValue : dictEntryValueList) { int value
			 * =0,tempValue=dataListMap.get(yearkey+code+dictEntryValue)!=null?
			 * dataListMap.get(yearkey+code+dictEntryValue):0;
			 * if(regions.getRegionsLevel().intValue()==RegionsConstants.
			 * REGIONS_LEVEL_COUNTIES){
			 * if(dataListMapOfRegion.containsKey(yearkey+regions.getParentCode(
			 * )+dictEntryValue)){
			 * value=dataListMapOfRegion.get(yearkey+regions.getParentCode()+
			 * dictEntryValue); } tempValue+=value;
			 * dataListMapOfRegion.put(yearkey+regions.getParentCode()+
			 * dictEntryValue,tempValue); }else{
			 * if(dataListMapOfRegion.containsKey(yearkey+code+dictEntryValue)){
			 * value=dataListMapOfRegion.get(yearkey+code+dictEntryValue); }
			 * tempValue+=value;
			 * dataListMapOfRegion.put(yearkey+code+dictEntryValue,tempValue); }
			 * } } }
			 */
		// ===================================================================================================================================================
		// ===================================================================================================================================================
		/**
		 * 正式处理数据 最后需求数据结构 为 Map key is yearsKey value is List<int> list 排序依据为
		 * regionKey
		 */
		Map<String, List<Integer>> mapPublic = new HashMap<String, List<Integer>>();
		Map<String, List<Integer>> mapCadre = new HashMap<String, List<Integer>>();
		Map<String, List<Integer>> mapTeacher = new HashMap<String, List<Integer>>();
		Map<String, List<Integer>> mapServe = new HashMap<String, List<Integer>>();
		Map<String, List<Integer>> mapCareer = new HashMap<String, List<Integer>>();
		Map<String, List<Integer>> mapManage = new HashMap<String, List<Integer>>();

		for (String yearkey : years) {
			List<Integer> listPublic = new ArrayList<Integer>();
			List<Integer> listCadre = new ArrayList<Integer>();
			List<Integer> listTeacher = new ArrayList<Integer>();
			List<Integer> listServe = new ArrayList<Integer>();
			List<Integer> listCareer = new ArrayList<Integer>();
			List<Integer> listManage = new ArrayList<Integer>();
			for (String code : regionAllList) {
				for (String dictEntryValue : dictEntryValueList) {
					// int value=
					// dataListMap.get(yearkey+code+dictEntryValue)!=null?dataListMap.get(yearkey+code+dictEntryValue):0;
					int value = rd.nextInt(10) * 2 + 2;
					if (StringUtils.equals(dictEntryValue, "6")) {
						listPublic.add(value);
					} else if (StringUtils.equals(dictEntryValue, "5")) {
						listCadre.add(value);
					} else if (StringUtils.equals(dictEntryValue, "4")) {
						listTeacher.add(value);
					} else if (StringUtils.equals(dictEntryValue, "3")) {
						listServe.add(value);
					} else if (StringUtils.equals(dictEntryValue, "2")) {
						listCareer.add(value);
					} else if (StringUtils.equals(dictEntryValue, "1")) {
						listManage.add(value);
					}
				}
			}

			mapPublic.put(yearkey, listPublic);
			mapCadre.put(yearkey, listCadre);
			mapTeacher.put(yearkey, listTeacher);
			mapServe.put(yearkey, listServe);
			mapCareer.put(yearkey, listCareer);
			mapManage.put(yearkey, listManage);
		}

		for (String code : regionAllList) {
			Regions regions = RegionsUtil.getRegions(code);
			regionList.add(regions.getRegionsName());
		}

		// ===================================================================================================================================================
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("dataPublic", mapPublic);
		data.put("dataCadre", mapCadre);
		data.put("dataTeacher", mapTeacher);
		data.put("dataServe", mapServe);
		data.put("dataCareer", mapCareer);
		data.put("dataManage", mapManage);

		try {
			modelAndView.addObject("dataMap", JsonUtil.toJson(data));
			modelAndView.addObject("regionList", JsonUtil.toJson(regionList));
			modelAndView.addObject("years", JsonUtil.toJson(yearList));
			modelAndView.addObject("yearList", JsonUtil.toJson(years));
			modelAndView.addObject("dictEntryList", JsonUtil.toJson(dictEntryList));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping("readEvaluate")
	public ModelAndView readEvaluate(Train train) {
		ModelAndView mv = new ModelAndView();
		train = trainService.read(train.getId());
		SearchParam searchParam = new SearchParam();
		searchParam.setPageAvailable(false);
		searchParam.getParamMap().put("registerTrainId", train.getId());
		List<Map<String, Object>> list = trainCourseService.listTeacher(searchParam);
		if (CollectionUtils.isNotEmpty(list)) {
			mv.addObject("listUser", list);
		}
		CourseEffectEvalua courseEffectEvalua = courseEffectEvaluaService.list(searchParam) != null
				? courseEffectEvaluaService.list(searchParam).get(0) : null;
		Map<String, TeachingEvalua> listMap = teachingEvaluaService.selectList(searchParam);
		mv.addObject("courseEffectEvalua", courseEffectEvalua);
		mv.addObject("teachingEvalua", listMap);
		mv.addObject("train", train);
		return mv;
	}

	@RequestMapping("/statisticsTrainType")
	public ModelAndView statisticsTrainType() {
		ModelAndView modelAndView = new ModelAndView("/train/train/cms/statisticsTrainType");

		/*
		 * 加载图表的统计数据
		 */
		Map<String, Object> args = new HashMap<String, Object>();
		int yearNum = 4;// 年度范围，当前年度前X年
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);// 当前年度
		int minYear = currentYear - yearNum;// 最小年度
		args.clear();
		args.put("minYear", minYear);
		List<Object> charDatas = trainService.selectTrainStatisticsGroup(args);

		/*
		 * 处理数据，没有人数的年度设置为0
		 */
		LinkedHashMap<String, Integer> data = new LinkedHashMap<String, Integer>();
		for (Integer i = minYear; i <= currentYear; i++) {
			data.put(i.toString(), 0);
		}
		for (Object obj : charDatas) {
			Map<String, Object> charData = (Map<String, Object>) obj;
			// data.put(charData.get("year").toString(),
			// ((BigDecimal)charData.get("studentCount")).intValue());
			data.put(charData.get("year").toString(), ((Long) charData.get("studentCount")).intValue());
		}

		/*
		 * 用图表的统计数据构建图表所需的json数据
		 */
		GsonOption option = new GsonOption();
		// 设置标题及样式
		option.title().text("年度培训人次趋势图").x(X.center).textStyle().color("#188DD2");
		// 背景颜色及网格样式
		// option.backgroundColor("#188DD2").grid().backgroundColor("#46A6DE");
		// 设置工具栏
		List<Object> colors = new ArrayList<Object>();
		option.toolbox().show(true).feature(
				/*
				 * Tool.mark, Tool.dataView,
				 */
				new MagicType(Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
		// 提示信息
		option.tooltip().trigger(Trigger.axis).formatter("{b}年度 : {c}人");

		/*
		 * 横坐标和纵坐标的数据及样式
		 */
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.axisLabel().formatter("{value}人");
		// valueAxis.axisLine().lineStyle().color("#188DD2");
		// valueAxis.splitLine().lineStyle().color("gray");
		// valueAxis.axisLabel().textStyle().color("white");
		option.yAxis(valueAxis);

		CategoryAxis yearAxis = new CategoryAxis();
		yearAxis.axisLine().lineStyle().color("#188DD2");
		// yearAxis.splitLine().lineStyle().color("gray");
		yearAxis.axisLabel().formatter("{value}");
		yearAxis.axisLabel().textStyle().color("black");
		yearAxis.boundaryGap(false);
		yearAxis.data(data.keySet().toArray());
		option.xAxis(yearAxis);
		option.legend().data("年度").orient(Orient.vertical).x(X.left).y(5).selectedMode(true);
		// 曲线图
		Line line = new Line();
		line.smooth(true).name("年度").data(data.values().toArray()).itemStyle().normal().lineStyle().color("#ACF444");
		option.series(line);
		// 行业
		GsonOption optionIndustry = trainService.echartsIndustryType();

		/**
		 * 人才扇形
		 */
		List<DictEntry> dictNotParent = DictionaryUtils.getEntryListNotParent("USER_TYPE");
		String[] dictName = new String[dictNotParent.size()];
		for (Integer i = 0; i < dictNotParent.size(); i++) {
			// dictValue[i] = dictNotParent.get(i).getDictValue();// 获取值
			dictName[i] = dictNotParent.get(i).getDictName();
		}
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String ID = loginUser.getFirstDeptId();
		GsonOption optionPerson = (GsonOption) trainService.echartsPersonType(ID);

		optionPerson.legend().data(dictName).orient(Orient.vertical).x(X.left).y(40);
		optionPerson.legend().textStyle().fontSize(12);
		modelAndView.addObject("optionPerson", optionPerson);
		modelAndView.addObject("optionIndustry", optionIndustry);
		modelAndView.addObject("optionYear", option);

		return modelAndView;
	}
}
