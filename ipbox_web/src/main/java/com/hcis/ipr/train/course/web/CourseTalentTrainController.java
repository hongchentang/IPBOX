package com.hcis.ipr.train.course.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.info.service.ICmsInfoService;
import com.hcis.ipr.cms.site.service.ICmsSiteService;
import com.hcis.ipr.intellectual.call.dao.ProcedureDao;
import com.hcis.ipr.intellectual.call.entity.PatentApplyer;
import com.hcis.ipr.intellectual.call.entity.PatentInvent;
import com.hcis.ipr.intellectual.call.entity.PatentIpic;
import com.hcis.ipr.intellectual.call.entity.PatentType;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.register.service.ITrainRegisterService;
import com.hcis.ipr.train.train.service.ITrainService;

@RequestMapping("/train/talentTrain")
@Controller
public class CourseTalentTrainController extends BaseController {

	@Autowired
	private ICourseService courseService;
	@Autowired
	private ITrainRegisterService trainRegisterService;
	@Autowired
	private ICmsInfoService cmsInfoService;
	@Autowired
	private ICmsSiteService cmsSiteService;
	@Autowired
	private ITrainService trainService;
	@Autowired
	private ProcedureDao procedureDao;

	// 首页显示的4条课程数据
	@RequestMapping("indexListCourse")
	public ModelAndView indexListCourse(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		Pagination pagination = searchParam.getPagination();
		if (null == pagination) {
			pagination = new Pagination();
		}
		pagination.setPageSize(4);
		searchParam.setPagination(pagination);
		/*
		 * searchParam.getParamMap().put("openStatus", "all");
		 * modelAndView.addObject("CourseList",
		 * courseService.selectTalentCourse(searchParam));
		 */
		modelAndView.addObject("CourseList", courseService.selectCourseHome(searchParam));
		return modelAndView;
	}

	// 前端课程列表
	@RequestMapping("listCourse")
	public ModelAndView listCourse(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		Pagination pagination = searchParam.getPagination();
		if (null == pagination) {
			pagination = new Pagination();
		}
		pagination.setPageSize(8);
		searchParam.setPagination(pagination);
		// modelAndView.addObject("CourseList",
		// courseService.selectTalentCourse(searchParam));
		modelAndView.addObject("CourseList", courseService.selectCourseHome(searchParam));
		return modelAndView;
	}

	// 前端课程详细
	@RequestMapping("courseDetail")
	public ModelAndView courseDetail(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		Object courseId = searchParam.getParamMap().get("courseId");
		Course c = new Course();
		if (null != courseId && StringUtils.isNotBlank(courseId.toString())) {
			c = courseService.read(courseId.toString());
		}
		modelAndView.addObject("CourseList", courseService.selectTalentCourse(searchParam));
		modelAndView.addObject("course", c);
		return modelAndView;
	}

	// 前端课程报名列表
	@RequestMapping("listCourseTrain")
	public ModelAndView courseTrain(SearchParam searchParam) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/train/talentTrain/listTrainCourse");
		modelAndView.addObject("courseTrain", courseService.selectCourseTrain(searchParam));
		return modelAndView;
	}

	// 前端报名
	@RequestMapping("signUp")
	@ResponseBody
	public AjaxReturnObject signUp(SearchParam searchParam) throws ParseException {
		int statusCode = 200;
		String msg = "操作成功！";
		statusCode = trainRegisterService.signUp(searchParam, request);
		return new AjaxReturnObject(statusCode, msg);
	}

	// 跳转到住宿时间填写
	@RequestMapping("wirteRoomTime")
	@ResponseBody
	public ModelAndView wirteRoomTime(SearchParam searchParam) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/train/talentTrain/roomconfirm");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy年MM月dd日");

		modelAndView.addObject("trainId", searchParam.getParamMap().get("trainId").toString());
		modelAndView.addObject("roomStartTime",
				sFormat.format(sf.parse(searchParam.getParamMap().get("roomStartTime").toString())));
		modelAndView.addObject("roomEndTime",
				sFormat.format(sf.parse(searchParam.getParamMap().get("roomEndTime").toString())));
		return modelAndView;
	}

	// 前端按专利类型。统计图.
	@RequestMapping("registerStatistics")
	public ModelAndView registerStatistics() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/train/talentTrain/registerStatistics");
		List<String> list = new ArrayList<String>();
		List<String> lists = new ArrayList<String>();
		PatentType patentone = null;
		PatentType patenttwe = null;
		PatentType patent3 = null;
		PatentType patent4 = null;
		PatentType patent5 = null;
		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);// 当前年
		for (int i = 0; i < 5; i++) {
			list.add(String.valueOf(currentYear - i));
		}
		Collections.sort(list);

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("years", list);
		LoginUser loginUser = LoginUser.loginUser(request);
		param.put("deptId", loginUser.getFirstDeptId());
		// 当前登录人是培训机构的，则只显示本单位的
		/*
		 * if (null != loginUser &&
		 * loginUser.getDeptType().equals(DepartmentConstants.
		 * DEPT_TYPE_TRAIN_ORG) &&
		 * SecurityRoleUtils.isTrainAdmin(loginUser.getId())) {
		 * param.put("deptId", loginUser.getId()); }
		 */
		param.put("trainType", "01");
		List ftf = trainService.selectRegisterStatistics(param);
		List<PatentType> type = courseService.getPatentType(loginUser.getFirstDeptId());
		System.out.println("type=" + type);
		param.put("trainType", "02");
		List rt = trainService.selectRegisterStatistics(param);

		for (int i = 0; i < type.size(); i++) {
			if (i == 0) {
				patentone = type.get(0);
			}
			if (i == 1) {
				patenttwe = type.get(1);
			}
			if (i == 2) {
				patent3 = type.get(2);
			}
			if (i == 3) {
				patent4 = type.get(3);
			}
			if (i == 4) {
				patent5 = type.get(4);
			}

		}

		for (int i = 0; i < type.size(); i++) {
			lists.add(type.get(i).getYear());
		}
		Collections.sort(lists);
		// 设置统计图
		GsonOption option = new GsonOption();
		option.title().text("专利类型总体统计图");
		option.tooltip().trigger(Trigger.axis);
		List<Object> obj = new ArrayList<Object>();
		obj.add("#ADD6F2");
		obj.add("#FE7B4D");
		obj.add("#FDAD4E");
		option.legend("发明", "实用新型", "外观设计").setColor(obj);
		option.toolbox().show(true).feature(Tool.restore, new MagicType(Magic.line, Magic.bar).show(true),
				Tool.saveAsImage);
		option.calculable(true);
		CategoryAxis ca = new CategoryAxis();
		ca.data().addAll(lists);
		option.xAxis(ca);
		option.yAxis(new ValueAxis());

		Bar bar = new Bar("发明");
		List a = new ArrayList();
		if (patentone != null)
			a.add(patentone.getInvent());

		if (patenttwe != null) {
			a.add(patenttwe.getInvent());
		}
		if (patent3 != null) {
			a.add(patent3.getInvent());
		}
		if (patent4 != null) {
			a.add(patent4.getInvent());
		}
		if (patent5 != null) {
			a.add(patent5.getInvent());
		}

		bar.data().addAll(a);
		bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));

		/*
		 * bar.markLine().data(new
		 * PointData().type(MarkType.average).name("平均值"));
		 */

		Bar bar2 = new Bar("实用新型");
		// bar2.data().addAll(rt);
		List b = new ArrayList();

		if (patentone != null) {
			b.add(patentone.getAppearance());
		}
		if (patenttwe != null) {
			b.add(patenttwe.getAppearance());
		}
		if (patent3 != null) {
			b.add(patent3.getAppearance());
		}
		if (patent4 != null) {
			b.add(patent4.getAppearance());
		}
		if (patent5 != null) {
			b.add(patent5.getAppearance());
		}

		bar2.data().addAll(b);
		bar2.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));
		/*
		 * bar2.markLine().data(new
		 * PointData().type(MarkType.average).name("平均值"));
		 */
		Bar bar3 = new Bar("外观设计");
		List c = new ArrayList();

		if (patentone != null) {
			c.add(patentone.getUtility());
		}
		if (patenttwe != null) {
			c.add(patenttwe.getUtility());
		}
		if (patent3 != null) {
			c.add(patent3.getUtility());
		}

		if (patent4 != null) {
			c.add(patent4.getUtility());
		}
		if (patent5 != null) {
			c.add(patent5.getUtility());
		}

		bar3.data().addAll(c);
		bar3.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));

		/*
		 * bar3.markLine().data(new
		 * PointData().type(MarkType.average).name("平均值"));
		 */

		option.series(bar, bar2, bar3);

		modelAndView.addObject("option", option);
		return modelAndView;
	}

	@RequestMapping("invent")
	public ModelAndView invent() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/train/talentTrain/invent");

		List<String> lists = new ArrayList<String>();

		Map<String, Object> param = new HashMap<String, Object>();

		LoginUser loginUser = LoginUser.loginUser(request);
		param.put("deptId", loginUser.getFirstDeptId());
		param.put("trainType", "01");
		// 当前登录人是培训机构的，则只显示本单位的
		/*
		 * if (null != loginUser &&
		 * loginUser.getDeptType().equals(DepartmentConstants.
		 * DEPT_TYPE_TRAIN_ORG) &&
		 * SecurityRoleUtils.isTrainAdmin(loginUser.getId())) {
		 * param.put("deptId", loginUser.getId()); }
		 */
		List<PatentInvent> invent = procedureDao.getPatentInvent(loginUser.getFirstDeptId());

		for (int i = 0; i < invent.size(); i++) {
			lists.add(invent.get(i).getInvent());
		}
		Collections.sort(lists);

		// 设置统计图
		GsonOption option = new GsonOption();
		option.title().text("专利第一发明人排名统计图");
		option.tooltip().trigger(Trigger.axis);
		option.legend("第一发明人");
		option.toolbox().show(true).feature(Tool.restore, new MagicType(Magic.line, Magic.bar).show(true),
				Tool.saveAsImage);
		option.calculable(true);
		CategoryAxis ca = new CategoryAxis();
		ca.data().addAll(lists);
		option.xAxis(ca);
		option.yAxis(new ValueAxis());

		Bar bar = new Bar("第一发明人");
		List a = new ArrayList();

		for (int i = 0; i < invent.size(); i++) {
			int b = invent.get(i).getCount();

			a.add(b);
		}

		bar.data().addAll(a);
		bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));

		bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));

		option.series(bar);

		modelAndView.addObject("option", option);
		return modelAndView;
	}

	@RequestMapping("applyer")
	public ModelAndView applyer() {
		ModelAndView modelAndView = new ModelAndView("/train/talentTrain/applyer");

		/*
		 * 加载图表的统计数据
		 */LoginUser loginUser = LoginUser.loginUser(request);
		List<PatentApplyer> applyer = procedureDao.getPatentApplyer(loginUser.getFirstDeptId());
		Map<String, Object> args = new HashMap<String, Object>();

		LinkedHashMap<String, Integer> data = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < applyer.size(); i++) {
			data.put(applyer.get(i).getApplyer(), applyer.get(i).getCount());
		}
		/*
		 * for (Object obj : charDatas) { Map<String, Object> charData =
		 * (Map<String, Object>) obj; //
		 * data.put(charData.get("year").toString(), //
		 * ((BigDecimal)charData.get("studentCount")).intValue());
		 * data.put(charData.get("year").toString(), ((Long)
		 * charData.get("studentCount")).intValue()); }
		 */

		/*
		 * 用图表的统计数据构建图表所需的json数据
		 */
		GsonOption option = new GsonOption();
		// 设置标题及样式
		option.title().text("sss").x(X.center).textStyle().color("#188DD2");
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
		option.tooltip().trigger(Trigger.axis).formatter("{b} : {c}个");

		/*
		 * 横坐标和纵坐标的数据及样式
		 */
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.axisLabel().formatter("{value}个");
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
		String[] dictName = new String[applyer.size()];
		for (Integer i = 0; i < applyer.size(); i++) {
			// dictValue[i] = dictNotParent.get(i).getDictValue();// 获取值
			dictName[i] = applyer.get(i).getApplyer();
		}
		GsonOption optionPerson = (GsonOption) trainService.echartsPersonType(loginUser.getFirstDeptId());

		optionPerson.legend().data(dictName).orient(Orient.vertical).x(X.left).y(40);
		optionPerson.legend().textStyle().fontSize(12);
		modelAndView.addObject("optionPerson", optionPerson);
		modelAndView.addObject("optionIndustry", optionIndustry);
		modelAndView.addObject("optionYear", option);

		return modelAndView;
	}

	// 前端按专利运用。统计图.
	@RequestMapping("ipic")
	public ModelAndView ipic() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/train/talentTrain/ipic");

		List<String> lists = new ArrayList<String>();
		PatentIpic patentone = null;
		PatentIpic patenttwe = null;
		PatentIpic patent3 = null;
		PatentIpic patent4 = null;
		PatentIpic patent5 = null;
		LoginUser loginUser = LoginUser.loginUser(request);

		List<PatentIpic> ipic = procedureDao.getPatentIpic(loginUser.getFirstDeptId());
		System.out.println("type=" + ipic);
		for (int i = 0; i < ipic.size(); i++) {
			lists.add(ipic.get(i).getYears());
		}
		Collections.sort(lists);

		for (int i = 0; i < ipic.size(); i++) {
			if (i == 0) {
				patentone = ipic.get(0);
			}
			if (i == 1) {
				patenttwe = ipic.get(1);
			}
			if (i == 2) {
				patent3 = ipic.get(2);
			}
			if (i == 3) {
				patent4 = ipic.get(3);
			}
			if (i == 4) {
				patent5 = ipic.get(4);
			}

		}

		// 设置统计图
		GsonOption option = new GsonOption();
		List<Object> list = new ArrayList<Object>();
		list.add("#2EC7C9");
		list.add("#5AB1EF");
		list.add("#FFB980");
		list.add("#B6A2DE");
		option.title().text("专利运用统计图");
		option.tooltip().trigger(Trigger.axis);

		option.legend("专利转让", "专利质押", "专利许可", "专利入股").setColor(list);

		option.toolbox().show(true).feature(Tool.restore, new MagicType(Magic.line, Magic.bar).show(true),
				Tool.saveAsImage);
		option.calculable(true);
		CategoryAxis ca = new CategoryAxis();
		ca.data().addAll(lists);
		option.xAxis(ca);
		option.yAxis(new ValueAxis());

		Bar bar6 = new Bar("专利转让");
		List a = new ArrayList();
		if (patentone != null)
			a.add(patentone.getTransfer());

		if (patenttwe != null) {
			a.add(patenttwe.getTransfer());
		}
		if (patent3 != null) {
			a.add(patent3.getTransfer());
		}
		if (patent4 != null) {
			a.add(patent4.getTransfer());
		}
		if (patent5 != null) {
			a.add(patent5.getTransfer());
		}

		bar6.data().addAll(a);
		bar6.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));

		/*
		 * bar.markLine().data(new
		 * PointData().type(MarkType.average).name("平均值"));
		 */

		Bar bar7 = new Bar("专利质押");
		// bar2.data().addAll(rt);
		List b = new ArrayList();

		if (patentone != null) {
			b.add(patentone.getPermission());
		}
		if (patenttwe != null) {
			b.add(patenttwe.getPermission());
		}
		if (patent3 != null) {
			b.add(patent3.getPermission());
		}
		if (patent4 != null) {
			b.add(patent4.getPermission());
		}
		if (patent5 != null) {
			b.add(patent5.getPermission());
		}

		bar7.data().addAll(b);
		bar7.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));
		/*
		 * bar2.markLine().data(new
		 * PointData().type(MarkType.average).name("平均值"));
		 */
		Bar bar8 = new Bar("专利许可");
		List c = new ArrayList();

		if (patentone != null) {
			c.add(patentone.getPledge());
		}
		if (patenttwe != null) {
			c.add(patenttwe.getPledge());
		}
		if (patent3 != null) {
			c.add(patent3.getPledge());
		}

		if (patent4 != null) {
			c.add(patent4.getPledge());
		}
		if (patent5 != null) {
			c.add(patent5.getPledge());
		}

		bar8.data().addAll(c);
		bar8.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));
		Bar bar9 = new Bar("专利入股");
		List d = new ArrayList();

		if (patentone != null) {
			d.add(patentone.getShares());
		}
		if (patenttwe != null) {
			d.add(patenttwe.getShares());
		}
		if (patent3 != null) {
			d.add(patent3.getShares());
		}

		if (patent4 != null) {
			d.add(patent4.getShares());
		}
		if (patent5 != null) {
			d.add(patent5.getShares());
		}

		bar9.data().addAll(d);
		bar9.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));

		/*
		 * bar3.markLine().data(new
		 * PointData().type(MarkType.average).name("平均值"));
		 */

		option.series(bar6, bar7, bar8, bar9);

		modelAndView.addObject("option", option);
		return modelAndView;
	}

	/* 部门统计 */
	@RequestMapping("department")
	public ModelAndView department() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/train/talentTrain/department");

		List<String> lists = new ArrayList<String>();

		LoginUser loginUser = LoginUser.loginUser(request);

		List<PatentApplyer> dept = procedureDao.getPatentDept(loginUser.getFirstDeptId());

		for (int i = 0; i < dept.size(); i++) {
			lists.add(dept.get(i).getApplyer());
		}
		Collections.sort(lists);

		// 设置统计图
		GsonOption option = new GsonOption();
		List<Object> list = new ArrayList<Object>();
		list.add("#6ab0b8");
		option.title().text("部门专利统计图");
		option.tooltip().trigger(Trigger.axis);
		option.legend("数量").setColor(list);
		option.toolbox().show(true).feature(Tool.restore, new MagicType(Magic.line, Magic.bar).show(true),
				Tool.saveAsImage);
		option.calculable(true);
		CategoryAxis ca = new CategoryAxis();
		ca.data().addAll(lists);
		option.xAxis(ca);
		option.yAxis(new ValueAxis());

		Bar bar = new Bar("数量");
		List a = new ArrayList();

		for (int i = 0; i < dept.size(); i++) {
			int b = dept.get(i).getCount();

			a.add(b);
		}

		bar.data().addAll(a);
		bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"),
				new PointData().type(MarkType.min).name("最小值"));

		bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));

		option.series(bar);

		modelAndView.addObject("option", option);
		return modelAndView;
	}
}
