package com.hcis.ipr.train.userquestion.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.utils.LoginUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.web.UserManagerController;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.DateUtils;
import com.hcis.ipanther.core.utils.ExportUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.project.entity.Project;
import com.hcis.ipr.train.userquestion.entity.UserQuestion;
import com.hcis.ipr.train.userquestion.service.IUserQuestionService;
import com.hcis.survey.entity.Survey;
import com.hcis.survey.service.ISurveyService;

@Controller
@RequestMapping("/cms/userQuestion")
public class UserQuestionController extends BaseController{

	private final static Log log = LogFactory.getLog(UserQuestionController.class);
	
	@Autowired
	private IUserQuestionService userQuestionService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ISurveyService surveyService;
	
	
	//跳转到填写在线留言页面
	@ResponseBody
	@RequestMapping("goAddMessage")
	public ModelAndView goAddMessage(){
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/train/userQuestion/addUserQuestion");
		
		String userId = "";
		//取出当前登录的用户id
		LoginUser loginUser = LoginUser.loginUser(request);
		
		if(loginUser!=null){
			User user = new User();
			user = this.userService.read(loginUser.getId());
			mv.addObject("user", user);
		}
			
		return mv;
	}
	
	//增加用户在线留言记录
	@RequestMapping("saveUserQuestion")
	@ResponseBody
	public Map<String,Object> saveUserQuestion(UserQuestion userQuestion){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int count = 0;
		
		//取出当前登录的用户
		LoginUser loginUser = LoginUser.loginUser(request);
		if(loginUser!=null){
			userQuestion.setUserId(loginUser.getId());
		}
		
		//获取当前时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curDate = format.format(new Date());
		//存入当前时间
		userQuestion.setStartTime(curDate);
		//存入状态值,0为刚创建，1为已回复，2为已发送
		userQuestion.setStatus("0");;
		
		count = this.userQuestionService.addUserQuestion(userQuestion, LoginUtils.getLoginUser(request));
		
		resultMap.put("count", count);
		
		return resultMap;
	}
	
	//
	@RequestMapping("listTabs")
	public ModelAndView listTeacherTabs(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/train/userQuestion/listTabs");
		mv.addObject("listUserQuestion", this.userQuestionService.list(searchParam));
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status", "0");
		mv.addObject("toDo", this.userQuestionService.selectCount(paramMap));
		paramMap.put("status", "1");
		mv.addObject("toSend", this.userQuestionService.selectCount(paramMap));
		return mv;
	}
	
	//在线留言列表
	@ResponseBody
	@RequestMapping("listAllUserQuestion")
	public ModelAndView listAllUserQuestion(@ModelAttribute("searchParam")SearchParam searchParam,HttpServletResponse response,String status){
		
		ModelAndView mv = new ModelAndView();
		
		Map<String,Object> paramMap = searchParam.getParamMap();
		String action = (String) paramMap.get("action");
		if("export".equals(action)){//导出
			searchParam.getPagination().setAvailable(false);
			List<UserQuestion> userQuestions = this.userQuestionService.list(searchParam);
			try {
				Map<String,Object> beans = new HashMap<String,Object>();
				beans.put("liuyan", userQuestions);
				beans.put("dateUtils", new DateUtils());
				ExportUtils.exportExcel(response, "/excel/userQuestionExport.xls", beans, "在线留言导出");
			} catch (Exception e) {
				log.error("导出出错",e);
			}
			return null;
		}else{
			if(!"all".equals(status))
				searchParam.getParamMap().put("status", status);
			mv.setViewName("/train/userQuestion/listUserQuestion");
			List<UserQuestion> list = this.userQuestionService.list(searchParam);
			mv.addObject("listUserQuestion",list);
		}
		return mv;
		
	}
	
	//跳转到修改在线留言页面
	@ResponseBody
	@RequestMapping("goEditUserQuestion")
	public ModelAndView goEditUserQuestion(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/train/userQuestion/editUserQuestion");
		String id = searchParam.getParamMap().get("id").toString();
		UserQuestion userQuestion = this.userQuestionService.read(id);
		mv.addObject("userQuestion", userQuestion);
		return mv;
	}
	
	//修改在线留言并发送邮件
	@ResponseBody
	@RequestMapping("editUserQuestion")
	public Map<String,Object> editUserQuestion(@ModelAttribute("userQuestion")UserQuestion userQuestion,String type,DefaultMultipartHttpServletRequest request){
		int count = 0;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		count = this.userQuestionService.updateAndSend(request, userQuestion, type);
		if(count>0){
			resultMap.put("flag", true);
			resultMap.put("msg", "更新成功");
		}else{
			resultMap.put("flag", false);
			resultMap.put("msg", "更新失败");
		}
		return resultMap;
	}
	
	//逻辑删除在线留言记录
	@ResponseBody
	@RequestMapping("deleteUserQuestion")
	public Map<String,Object> deleteUserQuestion(UserQuestion userQuestion){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int count = this.userQuestionService.deleteByLogic(userQuestion);
		if(count>0)
			resultMap.put("flag", true);
		else
			resultMap.put("flag", false);
		return resultMap;
	}
	
	//单独选择记录发送邮件回复
	@ResponseBody
	@RequestMapping("sendUserQuestion")
	public Map<String,Object> sendUserQuestion(String id){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int count = 0;
		paramMap.put("id", id);
		List<UserQuestion> userQuestionList = this.userQuestionService.selectByMap(paramMap);
		count = this.userQuestionService.updateAndSend(request, userQuestionList.get(0), "editAndSend");
		if(count>0)
			resultMap.put("flag", true);
		else
			resultMap.put("flag", false);
		return resultMap;
	}
	
	
	//门户个人中心-跳转到在线留言的详情页面
	@RequestMapping("goViewUserQuestion")
	public ModelAndView goViewUserQuestion(String id){
		ModelAndView mv = new ModelAndView("train/userQuestion/frontviewUserQuestion");
		UserQuestion userQuestion = this.userQuestionService.read(id);
		mv.addObject("userQuestion", userQuestion);
		return mv;
	}
	
	
	
	
	
	
	
	
	
}
