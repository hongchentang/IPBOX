package com.hcis.ipanther.common.file.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.dict.service.IDictTypeService;
import com.hcis.ipanther.common.dict.utils.DictTypeUtils;
import com.hcis.ipanther.common.dict.vo.DictType;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;

 
@Controller
@RequestMapping("/common/file")
public class FilesContorller extends BaseController {
	

//	private DictType dictType;
	
	@Autowired
	private IDictTypeService dictTypeService;
	
	
	@RequestMapping("filePathList")
	public ModelAndView filePathList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/common/file/list");
//		List<Map<String,Object>> resultList = dictTypeService.list(searchParam);
//		modelAndView.addObject("resultList", resultList);
		return modelAndView;
	}
	
	
	@RequestMapping("saveFilesName")
	public ModelAndView saveFilesName(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/common/file/list");
//		List<Map<String,Object>> resultList = dictTypeService.list(searchParam);
//		modelAndView.addObject("resultList", resultList);
		return modelAndView;
	}
	
	
	@RequestMapping("doCopy")
	public ModelAndView doCopy(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/common/file/list");
//		List<Map<String,Object>> resultList = dictTypeService.list(searchParam);
//		modelAndView.addObject("resultList", resultList);
		return modelAndView;
	}

	
}
