package com.hcis.ipr.site.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserParttime;
import com.hcis.ipanther.common.user.entity.UserResearch;
import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.user.service.IUserParttimeService;
import com.hcis.ipanther.common.user.service.IUserResearchService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.info.entity.CmsInfo;
import com.hcis.ipr.cms.info.service.ICmsInfoService;
import com.hcis.ipr.cms.link.entity.CmsLink;
import com.hcis.ipr.cms.link.service.ICmsLinkService;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.service.ICourseService;

/**
 * 前台站点相关请求 关于匹配地址：
 * /site{regions:[a-z]*}用于省级站点；{regions:[a-z]*匹配永远都是空，这里这么写是为了避免
 * 调用方法@PathVariable 报错 /site/{regions:[a-z]*}用于匹配市级的站点
 * 
 * @author wuwentao
 * @date 2015年8月13日
 */
@Controller
@RequestMapping({ "/site{regions:[a-z]*}", "/site/{regions:[a-z]*}" })
public class SiteController extends BaseController {

	@Autowired
	private ICmsInfoService cmsInfoService;
	@Autowired
	private ICmsLinkService cmsLinkService;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserStaffService userStaffService;
	@Autowired
	private IUserParttimeService userParttimeService;
	@Autowired
	private IUserResearchService userResearchService;

	/**
	 * 首页
	 * 
	 * @param searchParam
	 * @param regions
	 *            子站点
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(SearchParam searchParam, @PathVariable String regions) {
		ModelAndView mv = new ModelAndView("/site/index");
		String regionsCode = RegionsUtil.getRegionsCodeFromSite(regions);

		/*
		 * 轮播图
		 */
		searchParam.getParamMap().put("img", "true");// 图片新闻
		searchParam.getParamMap().put("regionsCode", regionsCode);// 根据区域来取
		searchParam.getParamMap().put("type", "1");// 取通知要闻
		searchParam.getParamMap().put("state", "01");// 已经发布
		List<CmsInfo> scrollNews = cmsInfoService.find(searchParam, null, 1, 4);// 取四条默认排序
		mv.addObject("scrollNews", scrollNews);
		/*
		 * 通知要闻
		 */
		searchParam = new SearchParam();
		searchParam.getParamMap().put("state", "01");// 已经发布
		searchParam.getParamMap().put("regionsCode", regionsCode);// 根据区域来取
		searchParam.getParamMap().put("type", "1");// 取通知要闻
		List<CmsInfo> informs = cmsInfoService.find(searchParam, null, 1, 10);// 取10条默认排序
		mv.addObject("informs", informs);

		/*
		 * 培训动态
		 */
		searchParam = new SearchParam();
		searchParam.getParamMap().put("state", "01");// 已经发布
		searchParam.getParamMap().put("regionsCode", regionsCode);// 根据区域来取
		searchParam.getParamMap().put("type", "2");// 取培训动态
		List<CmsInfo> dynamics = cmsInfoService.find(searchParam, null, 1, 10);// 取10条默认排序
		mv.addObject("dynamics", dynamics);

		/*
		 * 首页最新培训报名的滚动窗口数据
		 */
		searchParam = new SearchParam();
		searchParam.getParamMap().put("openStatus", "opening");// 取正在培训的
		searchParam.getParamMap().put("allTrainCount", "allTrainCount");// 取所有报名了的学员总数
		Pagination pagination = searchParam.getPagination();
		if (null == pagination) {
			pagination = new Pagination();
		}
		pagination.setPageSize(1);
		searchParam.setPagination(pagination);
		List<Map<String, Object>> courseList = courseService.selectTalentCourse(searchParam);
		if (courseList.size() > 0) {
			mv.addObject("course", courseList.get(0));
		} else {
			mv.addObject("course", null);
		}
		/*
		 * 链接
		 */
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("isok", "01");// 有效

		args.put("type", "01");// 国内机构
		List<CmsLink> inLinks = cmsLinkService.listLink(args, 9);
		mv.addObject("inLinks", inLinks);
		args.put("type", "02");// 国际机构
		List<CmsLink> interLinks = cmsLinkService.listLink(args, 9);
		mv.addObject("interLinks", interLinks);
		args.put("type", "03");// 服务网站
		List<CmsLink> serviceLinks = cmsLinkService.listLink(args, 9);
		mv.addObject("serviceLinks", serviceLinks);

		regions = StringUtils.isNotEmpty(regions) ? ("/" + regions) : "";
		request.getSession().setAttribute("globalRegions", regions);// 访问首页的时候保存区域子站点的信息
		return mv;
	}

	/**
	 * 改变站点
	 * 
	 * @param site
	 *            子站点，如：guangzhou
	 * @param url
	 *            原来请求的地址，即在哪个页面做的请求
	 * @param regions
	 * @return
	 */
	@RequestMapping("/changeSite")
	public ModelAndView changeSite(String site, @PathVariable String regions) {
		ModelAndView mv = new ModelAndView("/site/index");
		if ("guangdong".equals(site)) {// 广东是请求主站点
			site = "";
		}
		site = StringUtils.isNotEmpty(site) ? ("/" + site) : "";
		String url = null;
		if (StringUtils.isEmpty(regions)) {// 在主站点请求
			url = ("/site" + site + "/index.do").toString();
		} else {// 在子站点请求
			url = ("/site" + site + "/index.do").toString();
		}
		request.getSession().setAttribute("globalRegions", site);
		mv.setViewName("redirect:" + url);// 重定向回原来的页面
		return mv;
	}

	/**
	 * 通知要闻列表 新闻动态列表
	 * 
	 * @param searchParam
	 * @param regions
	 *            子站点
	 * @return
	 */
	@RequestMapping("/{type:inform||dynamic}/list")
	public ModelAndView listInfo(SearchParam searchParam, @PathVariable String regions, @PathVariable String type) {
		ModelAndView mv = new ModelAndView("/site/info/list");

		searchParam.getParamMap().put("state", "01");// 已经发布
		searchParam.getParamMap().put("type", "inform".equals(type) ? "1" : "2");// 取通知要闻或者新闻动态
		searchParam.getParamMap().put("regionsCode", RegionsUtil.getRegionsCodeFromSite(regions));// 根据区域来取
		List<CmsInfo> infos = cmsInfoService.list(searchParam);
		mv.addObject("infos", infos);
		mv.addObject("type", type);// 区别类型
		mv.addObject("regions", regions);
		return mv;
	}

	/**
	 * 通知要闻详情 新闻动态详情
	 * 
	 * @param searchParam
	 * @param regions
	 *            子站点
	 * @return
	 */
	@RequestMapping("/{type:inform||dynamic}/detail")
	public ModelAndView infoDetail(String id, @PathVariable String regions, @PathVariable String type) {
		ModelAndView mv = new ModelAndView("/site/info/detail");
		CmsInfo info = new CmsInfo();
		info.setId(id);
		cmsInfoService.updateClick(info, null);// 更新浏览次数

		info = cmsInfoService.read(id);
		mv.addObject("info", info);
		mv.addObject("type", type);
		mv.addObject("regions", regions);
		return mv;
	}

	/**
	 * 人才培训系统
	 * 
	 * @param searchParam
	 * @param regions
	 * @return
	 */
	@RequestMapping("/train/list")
	public ModelAndView trainList(SearchParam searchParam, @PathVariable String regions) {
		ModelAndView mv = new ModelAndView("/site/train/list");

		List<Integer> yearList = new ArrayList<Integer>();
		// 年度选项
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		int year = Integer.valueOf(sf.format(new Date()));
		for (int i = 6; i >= 0; i--) {
			yearList.add(year - i);
		}
		mv.addObject("yearList", yearList);
		// 培训基地选项
		mv.addObject("trainList", courseService.selectTrain(searchParam));

		/*
		 * 获取培训动态
		 */
		searchParam.getParamMap().put("state", "01");// 已经发布
		searchParam.getParamMap().put("type", "2");
		searchParam.getParamMap().put("regionsCode", RegionsUtil.getRegionsCodeFromSite(regions));// 根据区域来取
		List<CmsInfo> dynamics = cmsInfoService.find(searchParam, null, 1, 10);// 取10条默认排序
		mv.addObject("dynamics", dynamics);
		mv.addObject("regions", regions);
		return mv;
	}

	/**
	 * 人才培训系统-课程详细信息
	 * 
	 * @param searchParam
	 * @param regions
	 * @return
	 */
	@RequestMapping("/train/detail")
	public ModelAndView trainDetail(String id, @PathVariable String regions) {
		ModelAndView mv = new ModelAndView("/site/train/detail");
		Course course = new Course();
		course = courseService.read(id);
		SearchParam searchParam = new SearchParam();
		searchParam.getParamMap().put("courseId", id);
		mv.addObject("CourseList", courseService.selectTalentCourse(searchParam));
		mv.addObject("course", course);
		mv.addObject("regions", regions);
		return mv;
	}

	/**
	 * 培训师资系统
	 * 
	 * @param searchParam
	 * @param regions
	 * @return
	 */
	@RequestMapping("/teacher/list")
	public ModelAndView teacherList(SearchParam searchParam, @PathVariable String regions) {
		ModelAndView mv = new ModelAndView("/site/teacher/list");
		List<User> teachers = userService.selectTeacherForCMS(searchParam);
		mv.addObject("teachers", teachers);
		mv.addObject("regions", regions);
		return mv;
	}

	/**
	 * 培训师资系统-教师明细
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teacher/detail")
	public ModelAndView teacherDetail(User user, @PathVariable String regions) throws Exception {
		ModelAndView mv = new ModelAndView("/site/teacher/detail");
		String userId = user.getId();
		if (StringUtils.isNotEmpty(userId)) {
			User teachers = userService.read(userId);
			UserStaff userStaff = userStaffService.read(userId);
			if (null != userStaff) {
				PropertyUtils.copyProperties(teachers, userStaff);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			SearchParam param = new SearchParam();
			param.setPageAvailable(false);
			param.getParamMap().put("userId", user.getId());
			List<UserParttime> parttimes = userParttimeService.list(param);
			List<UserResearch> researchs = userResearchService.list(param);
			map.put(user.getId() + "parttimes", parttimes);
			map.put(user.getId() + "researchs", researchs);
			mv.addObject("teacher", teachers);
			mv.addObject("dataMap", map);
			mv.addObject("regions", regions);
		}
		return mv;
	}

	/**
	 * 人才评估系统
	 * 
	 * @param searchParam
	 * @param regions
	 *            子站点
	 * @return
	 */
	@RequestMapping("/stat")
	public ModelAndView stat(SearchParam searchParam, @PathVariable String regions) {
		ModelAndView mv = new ModelAndView("/site/stat");
		mv.addObject("regions", regions);
		return mv;
	}

	/**
	 * 联系我们模块
	 * 
	 * @param searchParam
	 * @param regions
	 *            子站点
	 * @return
	 */
	@RequestMapping("/contactUs")
	public ModelAndView contactUs(SearchParam searchParam, @PathVariable String regions) {
		ModelAndView mv = new ModelAndView("/site/contactUs");
		mv.addObject("regions", regions);
		return mv;
	}

	/**
	 * 首页搜索 关键字为keyword
	 * 
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView search(SearchParam searchParam, @PathVariable String regions) {
		ModelAndView mv = new ModelAndView("/site/search");
		searchParam.getParamMap().put("regionsCode", RegionsUtil.getRegionsCodeFromSite(regions));// 根据区域来取
		List<CmsInfo> infos = cmsInfoService.searchForCms(searchParam);
		mv.addObject("infos", infos);
		mv.addObject("regions", regions);
		return mv;
	}

}
