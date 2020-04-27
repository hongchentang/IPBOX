package com.hcis.ipr.space.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.notice.entity.Notice;
import com.hcis.ipanther.common.notice.service.INoticeService;
import com.hcis.ipanther.common.notice.utils.NoticeConstants;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.DateUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.info.entity.CmsInfo;
import com.hcis.ipr.cms.info.service.ICmsInfoService;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.evalua.effect.entity.CourseEffectEvalua;
import com.hcis.ipr.evalua.effect.entity.TeachingEvalua;
import com.hcis.ipr.evalua.effect.service.ICourseEffectEvaluaService;
import com.hcis.ipr.evalua.effect.service.ITeachingEvaluaService;
import com.hcis.ipr.space.service.ISpaceService;
import com.hcis.ipr.train.effect.entity.CheckResult;
import com.hcis.ipr.train.effect.entity.CheckResult.CheckResultStatus;
import com.hcis.ipr.train.effect.service.ICheckResultService;
import com.hcis.ipr.train.effect.service.ICheckService;
import com.hcis.ipr.train.project.entity.Project;
import com.hcis.ipr.train.project.service.IProjectService;
import com.hcis.ipr.train.train.entity.Train;
import com.hcis.ipr.train.train.service.ITrainCourseService;
import com.hcis.ipr.train.train.service.ITrainService;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.service.ISurveyService;
/**
 * 个人端相关请求
 * noskin开头的不需要封套
 * @author wuwentao
 * @date 2015年4月17日
 */
@Controller
@RequestMapping("/space")
public class SpaceController extends BaseController{
	
	@Autowired
	private ISpaceService spaceService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private ICheckService checkService;
	@Autowired
	private ICheckResultService checkResultService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private INoticeService noticeService;
	@Autowired
	private ICmsInfoService cmsInfoService;
	@Autowired
	private ITrainService trainService;
	@Autowired
	private ITrainCourseService trainCourseService;
	@Autowired
	private ICourseEffectEvaluaService courseEffectEvaluaService;
	@Autowired
	private ITeachingEvaluaService teachingEvaluaService;
	
	/**
	 * 个人端主页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> paramMap = searchParam.getParamMap();
		mv.addObject("user", userService.read(this.getLoginUser().getId()));
		/*
		 * 加载问卷调查
		 */
		List<Survey> surveies = surveyService.listSurveyForSpace(searchParam);
		mv.addObject("surveies", surveies);
		/*
		 * 加载通知公告
		 */
		paramMap.put("noticeStatus",NoticeConstants.STATUS_PUBLISHED);
		paramMap.put("currentTime", DateUtils.formatDate(new Date()));//时间有效
		List<Notice> notices = noticeService.listNotice(searchParam);
		mv.addObject("notices", notices);
		/*
		 * 加载培训动态
		 * 只加载前五条
		 */
		searchParam = new SearchParam();
		paramMap = searchParam.getParamMap();
		Pagination pagination = new Pagination();
		pagination.setPageSize(5);
		searchParam.setPagination(pagination);
		searchParam.setPageAvailable(false);
		paramMap.put("state", "01");
		paramMap.put("pageMark", "dynamic");
		List<CmsInfo> cmsInfos = cmsInfoService.list(searchParam);
		mv.addObject("cmsInfos", cmsInfos);
		return mv;
	}
	
	/**
	 * 列出个人可参加的所有问卷
	 * @param searchParam
	 * @param noskin
	 * @return
	 */
	@RequestMapping({"/survey/listSurvey","/survey/noskin/listSurvey"})
	public ModelAndView listSurvey(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView("/space/survey/listSurvey");
		List<Survey> surveies = surveyService.listSurveyForSpace(searchParam);
		mv.addObject("surveies", surveies);
		mv.addObject("noskin", super.isNoskin());
		return mv;
	}
	
	/**
	 * 通知公告列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping({"/notice/listNotice","/notice/noskin/listNotice"})
	public ModelAndView listNotice(SearchParam searchParam){
		ModelAndView mv = new ModelAndView("/space/notice/listNotice");
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("noticeStatus",NoticeConstants.STATUS_PUBLISHED);
		paramMap.put("currentTime", DateUtils.formatDate(new Date()));//时间有效
		List<Notice>  notices= noticeService.listNotice(searchParam);
		mv.addObject("notices",notices);
		mv.addObject("noskin", super.isNoskin());
		return mv;
	}
	
	/**
	 * 通知公告查看
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/notice/viewNotice")
	public ModelAndView viewNotice(Notice notice){
		ModelAndView mv = new ModelAndView();
		notice= noticeService.getNotice(notice);
		mv.addObject("notice",notice);
		return mv;
	}
	
	/**
	 * 列出个人可参加的省级管理机构抽查
	 * @param searchParam
	 * @return
	 */
	@RequestMapping({"/check/listCheck","/check/noskin/listCheck"})
	public ModelAndView listCheck(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView("/space/train/effect/check/listCheck");
		
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("teacherId", this.getLoginUser().getId());//只查自己的
		List<CheckResult> checkResults = checkResultService.list(searchParam);
		mv.addObject("checkResults", checkResults);
		mv.addObject("noskin", super.isNoskin());
		return mv;
	}
	
	/**
	 * 跳转到省级管理机构抽查 评估/查看结果页面
	 * @param checkResult
	 * @return
	 */
	@RequestMapping({"/check/check","/check/checkResult"})
	public ModelAndView check(CheckResult checkResult) {
		ModelAndView mv = new ModelAndView("/space/train/effect/check/check");
		checkResult = checkResultService.read(checkResult.getId());
		
		Project project = projectService.read(checkResult.getProjectId());
		
		mv.addObject("checkResult", checkResult);
		mv.addObject("project", project);
		return mv;
	}
	
	/**
	 * 保存省级管理机构抽查 评估结果
	 * @param checkResult
	 * @return
	 */
	@RequestMapping("/check/saveCheck")
	@ResponseBody
	public void saveCheck(CheckResult checkResult,HttpServletResponse response) {
		checkResult.setStatus(CheckResultStatus.DONE.toString());
		int count = checkResultService.update(checkResult, this.getLoginUser().getId());
		this.render(Response.newDefaultResponse(count).toString(), "text/plain;charset=UTF-8",response);
	}
	
	/**
	 * 跳转到学员评估培训班页面
	 * @param train
	 * @return
	 */
	@RequestMapping("/evaluate/evaluate")
	public ModelAndView evaluate(Train train) {
		ModelAndView mv = new ModelAndView("/space/evaluate/evaluate01");
		train=trainService.read(train.getId());
		SearchParam searchParam=new SearchParam();
		searchParam.setPageAvailable(false);
		searchParam.getParamMap().put("registerTrainId",train.getId());
		List<Map<String, Object>> list= trainCourseService.listTeacher(searchParam);
//		List<String> expertList=Collections3.extractToList(list, "realName");
		if(CollectionUtils.isNotEmpty(list)){
			mv.addObject("listUser",list); 
		}
		mv.addObject("train",train);
		return mv;
	}
	
	
	@RequestMapping("/evaluate/saveEvaluate")
	@ResponseBody
	public void saveEvaluate(@FormModel("courseEffectEvalua")CourseEffectEvalua courseEffectEvalua,@FormModel("teachingEvalua")List<TeachingEvalua> teachingEvaluas,HttpServletResponse response) {
		int count=teachingEvaluaService.create(teachingEvaluas, this.getLoginUser().getId());
		count += courseEffectEvaluaService.create(courseEffectEvalua,this.getLoginUser().getId());//checkResultService.update(checkResult, this.getLoginUser().getId());
		this.render(Response.newDefaultResponse(count).toString(), "text/plain;charset=UTF-8",response);
	}
	
	
	/**
	 * 跳转到学员评估培训班页面
	 * @param train
	 * @return
	 */
	@RequestMapping("/evaluate/readEvaluate")
	public ModelAndView readEvaluate(Train train) {
		ModelAndView mv = new ModelAndView("/space/evaluate/readEvaluate");
		train=trainService.read(train.getId());
		SearchParam searchParam=new SearchParam();
		searchParam.setPageAvailable(false);
		searchParam.getParamMap().put("registerTrainId",train.getId());
		List<Map<String, Object>> list= trainCourseService.listTeacher(searchParam);
//		List<String> expertList=Collections3.extractToList(list, "realName");
		if(CollectionUtils.isNotEmpty(list)){
			mv.addObject("listUser",list); 
		}
		CourseEffectEvalua courseEffectEvalua=courseEffectEvaluaService.list(searchParam)!=null?courseEffectEvaluaService.list(searchParam).get(0):null;
		Map<String,TeachingEvalua> listMap=teachingEvaluaService.selectList(searchParam);
		mv.addObject("courseEffectEvalua",courseEffectEvalua); 
		mv.addObject("teachingEvalua",listMap); 
		mv.addObject("train",train);
		return mv;
	}
}
