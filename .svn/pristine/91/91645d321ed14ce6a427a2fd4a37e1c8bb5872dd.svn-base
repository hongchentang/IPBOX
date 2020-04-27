package com.hcis.ipr.space.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.utils.LoginUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.userquestion.entity.UserQuestion;
import com.hcis.ipr.train.userquestion.service.IUserQuestionService;
import com.hcis.survey.entity.Survey;

@Controller
@RequestMapping("/space/frontUserQuestion")
public class FrontUserQuestionController extends BaseController{

	
	@Autowired
	private IUserQuestionService userQuestionService;
	
	//门户个人中心的在线留言列表
	@ResponseBody
	@RequestMapping("frontlistUserQuestion")
	public ModelAndView frontlistUserQuestion(@ModelAttribute("searchParam")SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/train/userQuestion/frontlistUserQuestion");
		LoginUser loginUser = LoginUser.loginUser(request);
		if(loginUser!=null){
			
			searchParam.getParamMap().put("userId", loginUser.getId());
			List<UserQuestion> userQuestionList = this.userQuestionService.list(searchParam);
			mv.addObject("userQuestionList", userQuestionList);
		}
			
		return mv;
	}
	
	
	
	
	
}
