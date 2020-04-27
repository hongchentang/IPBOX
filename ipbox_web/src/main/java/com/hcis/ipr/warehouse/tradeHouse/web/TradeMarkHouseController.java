package com.hcis.ipr.warehouse.tradeHouse.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.warehouse.tradeHouse.entity.TradeMarkHouse;
import com.hcis.ipr.warehouse.tradeHouse.service.ITradeMarkRegisterInfoService;
import com.hcis.ipr.warehouse.tradeHouse.service.ITradeMarkHouseService;
import com.hcis.ipr.warehouse.tradeHouse.service.ITradeMarkServiceInfoService;


@RequestMapping("/warehouse/tms")
@Controller
public class TradeMarkHouseController extends BaseController{

	@Autowired
	private ITradeMarkHouseService tradeMarkHouseService;
	@Autowired
	private ITradeMarkRegisterInfoService tradeMarkHouseRegInfoService;	
	@Autowired
	private ITradeMarkServiceInfoService tradeMarkHouseServiceInfoService;	

	@RequestMapping("/listTMS")
	public ModelAndView listTradeMark(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/warehouse/tradeMark/listTradeMarks");
		List<TradeMarkHouse> list= tradeMarkHouseService.list(searchParam);
		modelAndView.addObject("resultList", list);
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	/**
	 * function 查看商标数据
	 * @author zonggaojin
	 * @param SearchParam searchParam
	 * @param TradeMark tradeMark
	 */
	@RequestMapping("/addTradeMarkHouse")
	public ModelAndView addTradeMark(SearchParam searchParam,TradeMarkHouse tradeMarkHouse){
		ModelAndView modelAndView=new ModelAndView("/warehouse/tradeMark/addTradeMark");
		modelAndView.addObject("paramMap", searchParam.getParamMap());
		if(tradeMarkHouse!=null&&StringUtils.isNotEmpty(tradeMarkHouse.getId())){
			tradeMarkHouse=tradeMarkHouseService.read(tradeMarkHouse.getId());		
		}		
		modelAndView.addObject("tradeMark", tradeMarkHouse);
		return modelAndView;
	}
	
	@RequestMapping("/addTms")
	public ModelAndView addTmsFile(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/warehouse/tradeMark/addTmsFile");
//		modelAndView.addObject("paramMap", searchParam.getParamMap());
		return modelAndView;
	}
	
	/**
	 * 删除数据
	 * @param file
	 * @param userType teacher student
	 * @param request
	 * @return
	 */
	@RequestMapping("/delTrademark")
	public @ResponseBody AjaxReturnObject delTrademark(TradeMarkHouse tradeMarkHouse){		
		tradeMarkHouseService.delete(tradeMarkHouse, LoginUser.loginUser(request).getId());
		return new AjaxReturnObject(200,"1");
	}

}
