package com.hcis.ipr.train.problem.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.dict.vo.DictType;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.train.problem.entity.Problem;
import com.hcis.ipr.train.problem.service.IProblemService;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;

 
@Controller
@RequestMapping("/train/problem")
public class ProblemController extends BaseController {
	
	@Autowired
	private IProblemService problemService;
	
	public IProblemService getProblemService() {
		return problemService;
	}

	public void setProblemService(IProblemService problemService) {
		this.problemService = problemService;
	}

	@RequestMapping("listProblem")
	public ModelAndView listProblem(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		List<Map<String,Object>> resultList = problemService.list(searchParam);
		modelAndView.addObject("resultList", resultList);
		return modelAndView;
	}
	
	/**
	 * 添加常见问题
	 */
	@RequestMapping("addProblem")
	@ResponseBody
	public void addProblem(@FormModel("problem")Problem problem, HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
		int count=0;
		try{
			if(problem!=null&&StringUtils.isNotEmpty(problem.getProblemCode())){
				problem.setUpdatedby(LoginUser.loginUser(request).getId());
				problem.setIsDeleted("N");
				count=problemService.addOrUpdate(problem, LoginUser.loginUser(request));
				if(count<=0){
					problem.setCreator(LoginUser.loginUser(request).getId());
				count =problemService.addOrUpdate(problem, LoginUser.loginUser(request));
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
	}
	
	/**
	 * 跳转到添加常见问题页面
	 */
	@RequestMapping("goAddProblem")
	public ModelAndView goAddProblem(){
		ModelAndView modelAndView=new ModelAndView("/train/problem/addProblem");
		return modelAndView;
	}
	
	/**
	 * 获取需修改的常见问题
	 */
	@RequestMapping("goEditProblem")
	public ModelAndView goEditProblem(@FormModel("problem")Problem problem){
		ModelAndView modelAndView=new ModelAndView("/train/problem/addProblem");
		problem=problemService.getByPk(problem);
		modelAndView.addObject("problem",problem);
		return modelAndView;
	}
	
	/**
	 * 修改常见问题
	 */
	@RequestMapping("editProblem")
	@ResponseBody
	public void editProblem(@FormModel("problem")Problem problem,HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
		int count=0;
		try{
			if(problem!=null&&StringUtils.isNotEmpty(problem.getProblemCode())){
				problem.setUpdatedby(LoginUser.loginUser(request).getId());
				count=problemService.addOrUpdate(problem, LoginUser.loginUser(request));
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
	}
	
	/**
	 * 删除常见问题
	 */
	@RequestMapping("deleteProblem")
	@ResponseBody
	public void deleteProblem(@FormModel("problem")Problem problem, HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
		int count=0;
		try{
			if(problem!=null&&StringUtils.isNotEmpty(problem.getId())){
				problem.setUpdatedby(LoginUser.loginUser(request).getId());
				count=problemService.delete(problem);
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
	}
}
