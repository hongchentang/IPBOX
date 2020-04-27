package com.hcis.ipanther.common.notice.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.utils.LoginUtils;
import com.hcis.ipanther.common.notice.entity.Notice;
import com.hcis.ipanther.common.notice.service.INoticeService;
import com.hcis.ipanther.common.notice.utils.NoticeConstants;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * 
 * @author Chaos
 * @date 2013-3-9
 * @time 下午11:49:53
 *
 */
@Controller
@RequestMapping("/common/notice")
public class NoticeController extends BaseController {
	
	@Autowired
	private INoticeService noticeService;
	
	@RequestMapping("listNotice")
	public ModelAndView listNotice(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView mav = new ModelAndView();
		searchParam.getParamMap().put("notice_manage", NoticeConstants.NOTICE_NOMANAGE);
		searchParam.getParamMap().put("noticeStatus",NoticeConstants.STATUS_PUBLISHED);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		searchParam.getParamMap().put("currentTime", format.format(Calendar.getInstance().getTime()));
		List<Notice>  list= noticeService.listNotice(searchParam);
		mav.addObject("resultList",list);
		mav.setViewName("/common/notice/listNotice");
		return mav;
	}
	
	@RequestMapping("listNoticeManage")
	public ModelAndView listNoticeManage(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView mav = new ModelAndView();
		searchParam.getParamMap().put("notice_manage", NoticeConstants.NOTICE_MANAGE);
		List<Notice>  list= noticeService.listNotice(searchParam);
		mav.addObject("resultList",list);
		mav.setViewName("/common/notice/listNotice");
		return mav;
	}
	
	/**
	 * 新增/修改
	 * @return
	 */
	@RequestMapping("goEditNotice")
    public ModelAndView goEditNotice(@FormModel("notice")Notice notice) {
			ModelAndView mav = getNotice(notice);
			mav.setViewName("/common/notice/editNotice");
			return mav;
	}
	
	/**
	 * 添加/修改
	 * 默认操作，关闭窗口-转到并刷新列表窗口
	 * 直接输出JSON
	 */
	@RequestMapping("saveNotice")
	@ResponseBody
	public AjaxReturnObject addNotice(@FormModel("notice")Notice notice){
		int count=0;
		if(notice!=null&&StringUtils.isNotBlank(notice.getId())){
			count=noticeService.updateNotice(notice,LoginUtils.getLoginUser(request));
		}
		else{
			count=noticeService.addNotice(notice,LoginUtils.getLoginUser(request));
		}
	    return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}

	
	@RequestMapping("readNotice")
	@ResponseBody
	public ModelAndView readNotice(@FormModel("notice")Notice notice) {
		ModelAndView mav = getNotice(notice);
		return mav;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("deleteNotice")
	@ResponseBody
	public AjaxReturnObject deleteNotice(@FormModel("notice")Notice notice){
		int count=noticeService.deleteNotice(notice,LoginUtils.getLoginUser(request));
	    return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
	/**
	 * Gets the notice.
	 *
	 * @param notice the notice
	 * @return the notice
	 */
	private ModelAndView getNotice(Notice notice){
		ModelAndView mav = new ModelAndView();
		Notice type=new Notice();
		if(notice!=null&&StringUtils.isNotBlank(notice.getId())){
			type= noticeService.getNotice(notice);
		}
		mav.addObject("notice", type);
		return mav;
	}
}