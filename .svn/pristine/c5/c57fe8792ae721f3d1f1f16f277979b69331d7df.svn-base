package com.hcis.ipanther.common.seq.web;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.seq.entity.Seq;
import com.hcis.ipanther.common.seq.service.ISeqService;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * 序列管理控制类
 * @author wuwentao
 * @date 2015年1月9日
 */
@Controller
@RequestMapping("/common/seq")
public class SeqController  extends BaseController {
	
	@Autowired
	private ISeqService seqService;
	
	/**
	 * 跳转到tab页面
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(@ModelAttribute("searchParam")SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
		List<Seq> seqs = seqService.listSeq(searchParam);
		mv.addObject("seqs",seqs);
		return mv;
	}
	
	/**
	 * 跳转到新增 /修改页面
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(@ModelAttribute("seq")Seq seq) {
		ModelAndView mv = this.newModelAndView();
		if(seq!=null&&StringUtils.isNotEmpty(seq.getId())){
			seq=seqService.getSeq(seq.getId());
		}
		mv.addObject("seq", seq);
		return mv;
	}
    
	/**
	 * 保存，发起一个流程实例
	 * 上传文件
	 * @param sqddxx
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public @ResponseBody AjaxReturnObject save(@ModelAttribute("seq")Seq seq) {
		int count = 0;
		//无ID：新增,有ID：修改
		if(StringUtils.isEmpty(seq.getId())){
			SearchParam searchParam = new SearchParam();
			searchParam.getParamMap().put("seqCode", seq.getSeqCode());
			List<Seq> seqs = seqService.listSeq(searchParam);
			if(seqs.size()>0) {
				return new AjaxReturnObject(300, "序列代码已经存在，请勿重复增加！");
			} else {
				seq.setCreator(LoginUser.loginUser(request).getId());
				count = seqService.addSeq(seq);				
			}
		}
		else{
			seq.setUpdatedby(LoginUser.loginUser(request).getId());
			seq.setUpdateTime(Calendar.getInstance().getTime());
			count = seqService.updateSeq(seq);
		}
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
}
