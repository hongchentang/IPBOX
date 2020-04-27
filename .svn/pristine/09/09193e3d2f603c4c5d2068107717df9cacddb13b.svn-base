package com.hcis.ipanther.common.regions.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hcis.ipanther.common.utils.ApiCode;
import com.hcis.ipanther.common.utils.BaseApi;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipr.core.web.controller.BaseController;

@Controller
@RequestMapping("/common/regions")
public class RegionsController  extends BaseController {
	
	private final static Log log = LogFactory.getLog(RegionsController.class);
	
	/**
	 * 根据当前机构编号得到下级的机构
	 * @param regionsCode
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getNextRegionsByCode")
	@ResponseBody
	public void getNextRegionsByCode(String regionsCode,HttpServletResponse response) throws IOException {
		this.renderJson(RegionsUtil.getNextRegionsByCode(regionsCode), response);			
	}

	/**
	 * 根据当前机构编号得到下级的机构
	 * @param regionsCode
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getNextRegionsByCodeV2")
	@ResponseBody
	public BaseApi getNextRegionsByCodeV2(String regionsCode, HttpServletResponse response) throws IOException {
		BaseApi api = new BaseApi();
		try {
			api.setData(RegionsUtil.getNextRegionsByCodeV2(regionsCode));
		}catch (Exception e){
			api.setCode(ApiCode.ERROR);
			api.setMsg(e.getMessage());
		}
		return api;
	}
	
	/**
	 * 根据当前机构编号得到下级的机构
	 * @param regionsCode
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getOnlyChildRegions")
	@ResponseBody
	public void getOnlyChildRegions(String regionsCode,String selecRegionsCode,HttpServletResponse response) throws IOException {
		String option="<option value=''>-请选择-</option>";
		List<Regions> list=RegionsUtil.getOnlyChildRegions(regionsCode);
		if(null!=list &&list.size()>0){
			for(Regions r:list){
				if(null!=selecRegionsCode&&r.getRegionsCode().equals(selecRegionsCode)){
					option+="<option value='"+r.getRegionsCode()+"' selected='selected'>"+r.getRegionsName()+"</option>";
				}else{
					option+="<option value='"+r.getRegionsCode()+"'>"+r.getRegionsName()+"</option>";
				}
			}
		}
		this.renderText(option, response);			
	}
	
}
