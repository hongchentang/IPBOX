package com.hcis.ipanther.common.flow.service.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hcis.ipanther.common.flow.service.IFlowService;
import com.hcis.ipanther.common.flow.utils.DesUtil;
import com.hcis.ipanther.common.flow.utils.FlowConstants;
import com.hcis.ipanther.common.user.dao.UserDao;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service
public class FlowServiceImpl extends BaseServiceImpl implements IFlowService {
	
	private final static Log log=LogFactory.getLog(FlowServiceImpl.class);
	
	@Autowired
	private UserDao baseDao;
	
	@Autowired
    protected TaskService taskService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return baseDao;
	}

	@Override
	public List<Object[]> getFlows(SearchParam searchParam) {
		List<Object[]> flows = new ArrayList<Object[]>();
		int count=0;
		count= (int) repositoryService.createProcessDefinitionQuery().count();
		Pagination pagination= searchParam.getPagination();
		List<ProcessDefinition>  processDefinitionList = new ArrayList<ProcessDefinition>();
		if (pagination==null) {
			pagination=new Pagination("1", (int) count);
		}else{
			pagination=new Pagination(String.valueOf(pagination.getCurrentPage()), (int) count);
		}
		int currentMinRow =pagination.getCurrentMinRow();
		processDefinitionList=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc().listPage(currentMinRow, pagination.getPageSize());
		searchParam.setPagination(pagination);
		/**
		 * 循环流程找到流程部署的信息
		 */
        for (ProcessDefinition processDefinition : processDefinitionList) {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            flows.add(new Object[]{processDefinition, deployment});
        }
		return flows;
	}

	@Override
	public List<Model> getModels(SearchParam searchParam) {
		int count=0;
		count= (int) repositoryService.createModelQuery().count();
		Pagination pagination= searchParam.getPagination();
		List<Model> models = new ArrayList<Model>();
		if (pagination==null) {
			pagination=new Pagination("1", (int) count);
		}else{
			pagination=new Pagination(String.valueOf(pagination.getCurrentPage()), (int) count);
		}
		int currentMinRow =pagination.getCurrentMinRow();
		models = repositoryService.createModelQuery().listPage(currentMinRow, pagination.getPageSize());
		searchParam.setPagination(pagination);
		return models;
	}

	@Override
	public void transToModel(String processDefinitionId) throws UnsupportedEncodingException, XMLStreamException {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),processDefinition.getResourceName());
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(in);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

        BpmnJsonConverter converter = new BpmnJsonConverter();
        com.fasterxml.jackson.databind.node.ObjectNode modelNode = converter.convertToJson(bpmnModel);
        Model modelData = repositoryService.newModel();
        modelData.setKey(processDefinition.getKey());
        modelData.setName(processDefinition.getResourceName());
        modelData.setCategory(processDefinition.getDeploymentId());

        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());

        repositoryService.saveModel(modelData);

        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
	}

	@Override
	public InputStream getFlowResource(String processDefinitionId,String resourceType) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		return resourceAsStream;
	}

	@Override
	public Deployment deploy(String modelId,MultipartFile file) {
		Deployment deployment = null;
		try {
			if(StringUtils.isNotEmpty(modelId)) {//通过模型部署
				Model modelData = repositoryService.getModel(modelId);
				JsonNode modelNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
	            byte[] bpmnBytes = null;
	            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
	            bpmnBytes = new BpmnXMLConverter().convertToXML(model);
	            String processName = modelData.getName() + ".bpmn20.xml";
	            deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
			} else {//资源文件方式部署
				String fileName = file.getOriginalFilename();
				InputStream fileInputStream = file.getInputStream();
				String extension = FilenameUtils.getExtension(fileName);
				if (extension.equals("zip") || extension.equals("bar")) {
					ZipInputStream zip = new ZipInputStream(fileInputStream);
					deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
				} else {
					deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
				}
			}
		} catch (Exception e) {
			logger.error("上传文件有误，部署失败!", e);
		}
		return deployment;
	}

	@Override
	public void deleteDeployment(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}

	@Override
	public String updateState(String processDefinitionId, String action) {
		String actionName = "";
		if (action.equals("active")) {//激活
        	actionName = "激活";
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
        } else if (action.equals("suspend")) {//挂起
        	actionName = "挂起";
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
        }
		return "已"+actionName+"ID为【"+processDefinitionId+"】的流程定义";
	}

	@Override
	public Map<String, Object> getActivityXY(HistoricProcessInstance processInstance) throws Exception {
		Map<String, Object> activityXY = new HashMap<String, Object>();
		boolean isEnd = false;//标记流程是否已经结束
		String processInstanceId = processInstance.getId();
		String processDefinitionId = processInstance.getProcessDefinitionId();
		Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();//执行实例
		
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> activitiList = processDefinition.getActivities();//获得当前任务的所有节点
		/**
		 * 获取流程正在执行的节点
		 * 如果execution为空表示该流程已经结束了，则获取流程的最后一个环节，即“结束”环节
		 */
		ActivityImpl activity = null;
		if(null!=execution) {
			Object property = PropertyUtils.getProperty(execution, "activityId");
			String activityId = "";
			if (property != null) {
				activityId = property.toString();
			}
			activity = processDefinition.findActivity(activityId);
		} else {
			isEnd = true;
			activity = activitiList.get(activitiList.size()-1);
		}
		
		activityXY.put("x", activity.getX());
		activityXY.put("y", activity.getY());
		activityXY.put("width", activity.getWidth());
		activityXY.put("height", activity.getHeight());
		activityXY.put("isEnd", isEnd);
		
		return activityXY;
	}

	@Override
	public void deleteModel(String modelId) {
		repositoryService.deleteModel(modelId);
	}

	@Override
	public String createModel(String key, String name, String description) {
		String modelId = "";
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();
            
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            modelData.setKey(key);

            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            modelId = modelData.getId();
        } catch (Exception e) {
            logger.error("创建模型失败：", e);
        }
		return modelId;
	}

	@Override
	public Map<String,String> getActivitiExplorerURL(String modelId) throws JsonProcessingException {
		String url = CommonConfig.getProperty("activiti.explorer.url");//请求的地址
		String encryptionkey = CommonConfig.getProperty("activiti.explorer.encryptionkey");//密钥
		
		String time = String.valueOf(new Date().getTime());
		
		//将需要传递先转换为json格式再加密
		//Map<String, String> hashMap = new HashMap<String, String>();
		String app = CommonConfig.getProperty("app.key");
		/*hashMap.put(FlowConstants.APP, app);
		hashMap.put(FlowConstants.DRIVER_CLASS_NAME, AppConfig.getProperty("config", FlowConstants.DRIVER_CLASS_NAME));
		hashMap.put(FlowConstants.JDBC_URL, AppConfig.getProperty("config", FlowConstants.JDBC_URL));
		hashMap.put(FlowConstants.JDBC_USERNAME, AppConfig.getProperty("config", FlowConstants.JDBC_USERNAME));
		hashMap.put(FlowConstants.JDBC_PASSWORD, AppConfig.getProperty("config", FlowConstants.JDBC_PASSWORD));
		hashMap.put(FlowConstants.TIME, time);
		hashMap.put(FlowConstants.MODEL_ID, modelId);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsons = objectMapper.writeValueAsString(hashMap);
		
		String encry = DesUtil.encrypt(jsons,encryptionkey);*/
		
		url+="/service/editor?id="+modelId;
		
		logger.info("activiti explorer请求地址为："+url);
		Map<String,String> results = new HashMap<String,String>();
		results.put("url", url);
		results.put("app", app);
		return results;
	}
	
	
}
