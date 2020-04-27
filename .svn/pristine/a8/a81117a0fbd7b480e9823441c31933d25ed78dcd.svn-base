package com.hcis.ipanther.common.flow.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hcis.ipanther.common.flow.service.IFlowLogService;
import com.hcis.ipanther.common.flow.service.IFlowService;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * 流程管理类
 * @author wuwentao
 *
 */
@Controller("common_flowController")
@RequestMapping("/common/flow")
public class FlowController  extends BaseController {
	
	@Autowired
	private HistoryService historyService;
	@Autowired
	private IFlowService flowService;
	@Autowired
	private IFlowLogService flowLogService;
	
	@RequestMapping("/list")
	public ModelAndView list(@ModelAttribute("searchParam")SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
		return mv;
	}
	
	//取到所有的已经部署的流程
	@RequestMapping("/listFlow")
	public ModelAndView listFlow(@ModelAttribute("searchParam")SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
        mv.addObject("flows", flowService.getFlows(searchParam));
		return mv;
	}	
	
	//取到所有的模型
	@RequestMapping("/listModel")
	public ModelAndView listModel(@ModelAttribute("searchParam")SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
        mv.addObject("models", flowService.getModels(searchParam));
		return mv;
	}
	
	/**
	 * 将流程转换为模型
	 * @param processDefinitionId 流程定义的ID
	 * @return
	 */
	@RequestMapping(value = "/transToModel")
	public @ResponseBody AjaxReturnObject transToModel(@RequestParam(value = "processDefinitionId") String processDefinitionId) {
        AjaxReturnObject result = null;
        try {
            flowService.transToModel(processDefinitionId);
        	result = new AjaxReturnObject(200, "转换成功，请到【模型列表】中编辑模型！");
        } catch (Exception e) {
        	logger.error("流程转换模型失败",e);
        	result = new AjaxReturnObject(300, "流程转换模型失败！");
        }
        return result;
	}
	/**
	 * 读取流程的XML文件或者图片
	 * @param processDefinitionId
	 * @param resourceType
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/resource/read")
	public void loadByDeployment(@RequestParam("processDefinitionId") String processDefinitionId, @RequestParam("resourceType") String resourceType,
            HttpServletResponse response) throws Exception {
		InputStream resourceAsStream = flowService.getFlowResource(processDefinitionId, resourceType);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	/**
	 * 跳转到发布流程页面
	 * @return
	 */
	@RequestMapping("/deploy")
	public ModelAndView add() {
		ModelAndView mv = this.newModelAndView();
		return mv;
	}
	
	/**
	 * 发布新的流程
	 * @param exportDir
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/deployDo")
	public @ResponseBody AjaxReturnObject deploy(@RequestParam(value = "file", required = false) MultipartFile file) {
        AjaxReturnObject result = null;
        Deployment deployment = flowService.deploy(null,file);
        //判断是否部署成功
        if(null!=deployment) {
        	result = new AjaxReturnObject(200, "部署成功!");
        } else {
        	result = new AjaxReturnObject(300, "部署失败!");
        }
        return result;
    }
	
	/**
	 * 发布模型
	 * @param modelId
	 * @return
	 */
	@RequestMapping(value = "/deployModel")
	public @ResponseBody AjaxReturnObject deploy(@RequestParam(value="modelId") String modelId) {
        AjaxReturnObject result = null;
        Deployment deployment = flowService.deploy(modelId,null);
        //判断是否部署成功
        if(null!=deployment) {
        	result = new AjaxReturnObject(200, "发布成功!");
        } else {
        	result = new AjaxReturnObject(300, "发布失败!");
        }
        return result;
    }
	
	 /**
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId 流程部署ID
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody AjaxReturnObject delete(@RequestParam("deploymentId") String deploymentId) {
    	flowService.deleteDeployment(deploymentId);
    	return AjaxReturnObject.newDefaultAjaxReturnObject(1);
    }
	
    
    /**
     * 挂起、激活流程实例
     */
    @RequestMapping(value = "/update/{action}")
    public @ResponseBody AjaxReturnObject  updateState(@PathVariable("action") String action, @RequestParam("processID") String processID,
                              RedirectAttributes redirectAttributes) {
    	String message = flowService.updateState(processID, action);
        return new AjaxReturnObject(200, message);
    }
    
    /**
     * 跳转到追踪流程的iframe
     * @param procInstId 流程实例的ID
     * @param height 展示的高度，可以不传值
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/process/goTrace")
    public ModelAndView goTrace(String procInstId,String height) throws Exception {
       	ModelAndView mv=new ModelAndView("/common/flow/goTrace");
       	mv.addObject("procInstId",procInstId);
       	mv.addObject("height", height);
       	mv.addObject("activiti_explorer_url",CommonConfig.getProperty("activiti.explorer.url"));
       	mv.addObject("app",CommonConfig.getProperty("app.key"));
       	return mv;
    }
    
    /**
     * 输出跟踪流程信息
     * 	1，图片
     * 	2，正在执行环节的节点坐标
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/process/trace")
    public ModelAndView trace(@RequestParam("processInstanceId")String processInstanceId) throws Exception {  
       	ModelAndView mv=new ModelAndView("/common/flow/trace");
       	mv.addObject("processInstanceId", processInstanceId);
   		if(StringUtils.isNotEmpty(processInstanceId)){
   			HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
   			Map<String,Object> activityXY = flowService.getActivityXY(processInstance);
			mv.addObject("activityXY", activityXY);
			mv.addObject("isEnd",activityXY.get("isEnd"));//是否结束的标识
	    	mv.addObject("processDefinitionId",processInstance.getProcessDefinitionId());
   		}
   		return mv;  
   	}  
    
    /**
     * 删除流程模型 
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/deleteModel")
    public @ResponseBody AjaxReturnObject deleteModel(@RequestParam("modelId") String modelId) {
    	flowService.deleteModel(modelId);
    	return AjaxReturnObject.newDefaultAjaxReturnObject(1);
    }
    
    /**
     * 跳转到新增模型界面
     * @return
     */
    @RequestMapping("/createModel")
	public ModelAndView createModel() {
		ModelAndView mv = this.newModelAndView();
		return mv;
	}
    
    /**
     * 保存新增的模型的基本信息：name key desc
     * @return
     */
    @RequestMapping("/createModelDo")
	public @ResponseBody AjaxReturnObject createModelDo(@RequestParam("name") String name,
			@RequestParam("key") String key, @RequestParam("description") String description) {
    	AjaxReturnObject result = null;
		String moduleId = flowService.createModel(key, name, description);
		//判断是否部署成功
        if(StringUtils.isNotEmpty(moduleId)) {
        	result = new AjaxReturnObject(200, moduleId);
        } else {
        	result = new AjaxReturnObject(300, "模型创建失败！");
        }
		return result;
	}
    
    /**
     * 得到跳转到ActivitiExplorer的请求链接
     * 链接包含加密信息
     * 在ActivitiExplorer中解密进行校验
     * @param modelId
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping("/getActivitiExplorerURL")
    public @ResponseBody String getActivitiExplorerURL(@RequestParam("modelId") String modelId,HttpServletRequest request) 
    		throws IOException {
    	Map<String,String> results= flowService.getActivitiExplorerURL(modelId);
    	return JsonUtil.toJson(results);
    }
    
   /**
    * 列出流程的日志信息
    * @param procInstId 流程实例的ID
    * @param businessId 业务ID
    * @return
    */
    @RequestMapping("/listLog")
	public ModelAndView listLog(SearchParam searchParam,String procInstId,String businessId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("logs",flowLogService.listByBusinessId(searchParam,procInstId, businessId));
		mv.addObject("procInstId", procInstId);
		mv.addObject("businessId", businessId);
		return mv;
	}
}
