package com.hcis.ipanther.common.dict.web;

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
@RequestMapping("/common/datadict")
public class DictTypeController extends BaseController {
	

//	private DictType dictType;
	
	@Autowired
	private IDictTypeService dictTypeService;
	
//	public DictType getDictType() {
//		return dictType;
//	}
//
//	public void setDictType(DictType dictType) {
//		this.dictType = dictType;
//	}

	public IDictTypeService getDictTypeService() {
		return dictTypeService;
	}

	public void setDictTypeService(IDictTypeService dictTypeService) {
		this.dictTypeService = dictTypeService;
	}
	
	@RequestMapping("listDictType")
	public ModelAndView listDictType(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		List<Map<String,Object>> resultList = dictTypeService.list(searchParam);
		modelAndView.addObject("resultList", resultList);
		return modelAndView;
	}

	/**
	 * 导出数据字典 dictType
	 */
	@RequestMapping("exportDictType")
	@ResponseBody
	public void exportDictType(HttpServletResponse response){
	    List<Map<String, Object>> list= dictTypeService.list(null);
	    DictTypeUtils.dictTypeExport(list, response);
	}
	
	
	/**
	 * 添加数据字典 dictType
	 */
	@RequestMapping("addDictType")
	@ResponseBody
	public void addDictType(@FormModel("dictType")DictType dictType, HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
		int count=0;
		try{
			if(dictType!=null&&StringUtils.isNotEmpty(dictType.getDictTypeCode())){
				dictType.setUpdatedby(LoginUser.loginUser(request).getId());
				dictType.setIsDeleted("N");
				count=dictTypeService.addOrUpdate(dictType, getLoginer());
				if(count<=0){
				dictType.setCreator(LoginUser.loginUser(request).getId());
				count =dictTypeService.addOrUpdate(dictType, getLoginer());
				if(count>0){
					message="添加数据项成功";
				}else if(count==-1){
				//statusCode="300";
					message="添加数据项失败";
				}else{
				//	statusCode="300";
					message="添加数据项失败，请重新操作";
				}
				} 
			}else{
				//statusCode="300";
				message="参数错误，请重新操作";
			}
		}catch(Exception e){
			logger.error(this.getClass().getName(),e);
			//statusCode="300";
			message="操作失败!";
		}
		this.renderText(Response.newDefaultResponse(count,message).toString(), response);
		//JSONObject jsonObject=  UserUtils.reslutJsonMap(0,statusCode, message);
	   // render(ObjectUtils.toString(jsonObject), UserConstants.CONTENT_TYPE_UTF);
	}
	
	/**
	 * 跳转到添加数据字典 dictType
	 */
	@RequestMapping("goAddDictType")
	public ModelAndView goAddDictType(){
		ModelAndView modelAndView=new ModelAndView("/common/datadict/addDictType");
		return modelAndView;
	}
	
	/**
	 * 获取需修改数据字典 dictType
	 */
	@RequestMapping("goEditDictType")
	public ModelAndView goEditDictType(@FormModel("dictType")DictType dictType){
		ModelAndView modelAndView=new ModelAndView("/common/datadict/addDictType");
		dictType=dictTypeService.getByPk(dictType);
		modelAndView.addObject("dictType",dictType);
		return modelAndView;
	}
	
	/**
	 * 修改数据字典 dictType
	 */
	@RequestMapping("editDictType")
	@ResponseBody
	public void editDictType(@FormModel("dictType")DictType dictType, HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
		int count=0;
		try{
			if(dictType!=null&&StringUtils.isNotEmpty(dictType.getDictTypeCode())){
				dictType.setUpdatedby(LoginUser.loginUser(request).getId());
				count=dictTypeService.addOrUpdate(dictType, getLoginer());
				if(count>0){
					message="修改数据项成功";
				}
				else if(count==-1){
				//	statusCode="300";
					message="修改数据项失败";
				}
				else{
				//	statusCode="300";
					message="修改数据项失败，请重新操作";
				}
			}
			else{
			//statusCode="300";
				message="参数错误，请重新操作";
			}
		}
		catch(Exception e){
			logger.error(this.getClass().getName(),e);
			//statusCode="300";
			message="操作失败!";
		}
		this.renderText(Response.newDefaultResponse(count,message).toString(), response);
		//JSONObject jsonObject=  UserUtils.reslutJsonMap(0,statusCode, message);
	   // render(ObjectUtils.toString(jsonObject), UserConstants.CONTENT_TYPE_UTF);
	}
	
	/**
	 * 删除数据字典 dictType
	 */
	@RequestMapping("deleteDictType")
	@ResponseBody
	public void deleteDictType(@FormModel("dictType")DictType dictType, HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
		int count=0;
		try{
			if(dictType!=null&&StringUtils.isNotEmpty(dictType.getDictTypeCode())){
				dictType.setUpdatedby(LoginUser.loginUser(request).getId());
				count=dictTypeService.delete(dictType);
				if(count>0){
					message="删除数据项成功";
				}
				else if(count==-1){
				//	statusCode="300";
					message="删除数据项失败";
				}
				else{
				//	statusCode="300";
					message="删除数据项失败，请重新操作";
				}
			}
			else{
			//	statusCode="300";
				message="参数错误，请重新操作";
			}
		}
		catch(Exception e){
			logger.error(this.getClass().getName(),e);
			//statusCode="300";
			message="操作失败!";
		}
		this.renderText(Response.newDefaultResponse(count,message).toString(), response);
		//JSONObject jsonObject=  UserUtils.reslutJsonMap(0,statusCode, message);
	    //render(ObjectUtils.toString(jsonObject), UserConstants.CONTENT_TYPE_UTF);
		
	}
}
