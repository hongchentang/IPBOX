package com.hcis.ipr.intellectual.trademark.web;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.common.utils.ApiCode;
import com.hcis.ipr.common.utils.BaseApi;
import com.hcis.ipr.intellectual.patent.entity.Patent;
import com.hcis.ipr.intellectual.patent.result.FileUploadResult;
import com.hcis.ipr.intellectual.patent.service.PatentService;
import com.hcis.ipr.intellectual.trademark.entity.Trademark;
import com.hcis.ipr.intellectual.trademark.service.ITrademarkService;
import com.hcis.items.service.ItemsLibraryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@RequestMapping("/intellectual/tradeMark")
@Controller
public class TrademarkController extends BaseController{

	@Autowired
	private ITrademarkService TrademarkService;

	/**
	 * 商标列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView listPatent(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/intellectual/trademark/list");
		modelAndView.addObject("listTrademarks", TrademarkService.getList(searchParam));
		return modelAndView;
	}

	/**
	 * 根据Id,数据进行逻辑删除
	 */
	@RequestMapping("delete")
	@ResponseBody
	public AjaxReturnObject  delete(@ModelAttribute("trademark") Trademark trademark){
		LoginUser loginUser=LoginUser.loginUser(request);
		int statusCode=200;
		String msg="操作成功！";
		TrademarkService.delete(trademark, loginUser.getId());
		return new AjaxReturnObject(statusCode, msg);
	}

	//编辑、新增专利信息
	@RequestMapping("goAddTrademark")
	public ModelAndView goAddTrademark(Trademark trademark) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/intellectual/trademark/addTrademark");
		if (null != trademark && StringUtils.isNotBlank(trademark.getId())) {
			trademark = TrademarkService.read(trademark.getId());
			modelAndView.addObject("trademark", trademark);
		}
		return modelAndView;
	}

	@RequestMapping("/saveTrademark")	
	public @ResponseBody AjaxReturnObject saveTrademark(@ModelAttribute("trademark") Trademark trademark, DefaultMultipartHttpServletRequest request){

		int statusCode = 200 ;
		String msg ="新增商标数据成功";
		try {
			String userId = LoginUser.loginUser(request).getId();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("upload");
			MultipartFile trademarkFile = multipartRequest.getFile("uploadFile");

			//保存附件信息
			TrademarkService.saveImage(trademark, file);
			TrademarkService.saveFile(trademark, trademarkFile);

			if(StringUtils.isBlank(trademark.getId())){
				trademark.setCreator(userId);
				trademark.setId(UUIDUtils.getUUId());
				TrademarkService.create(trademark, userId);
			}else {
				msg = "修改商标数据成功";
				TrademarkService.update(trademark, userId);
				//patentService.updatePatent(patent, fileMap);
			}

		}catch (Exception e){
			statusCode=300;
			msg="新增数据失败";
			e.printStackTrace();
		}
		return new AjaxReturnObject(statusCode, msg);
	}

	@RequestMapping("/view")
	public ModelAndView view(String id){
		ModelAndView view = new ModelAndView("/intellectual/trademark/view");
		try {
			view.addObject("trademark", TrademarkService.read(id));
		}catch (Exception e){
			e.printStackTrace();
		}
		return view;
	}
}
