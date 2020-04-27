package com.hcis.ipanther.common.flow.service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface IFlowService extends IBaseService {
	
	/**
	 * 取到所有的已经部署的流程
	 * @param searchParam
	 * @return List<Object[]> 流程列表：[0]为流程定义的信息[1]为部署的信息
	 */
	public List<Object[]> getFlows(SearchParam searchParam)	;
	
	/**
	 * 取到所有的模型
	 * @param searchParam
	 * @return
	 */
	public List<Model> getModels(SearchParam searchParam);
	
	/**
	 * 将流程转换为可编辑的模型
	 * @param processDefinitionId
	 */
	public void transToModel(String processDefinitionId) throws UnsupportedEncodingException, XMLStreamException;
	
	/**
	 * 得到流程的资源流
	 * @param processDefinitionId
	 * @param resourceType image 或者 xml
	 * @return
	 */
	public InputStream getFlowResource(String processDefinitionId,String resourceType);
	
	/**
	 * 通过资源文件或者模型的ID部署流程
	 * 模型ID为空时，即为资源文件方式部署
	 * @param modelId 模型的ID
	 * @param file 部署的资源文件
	 * @return
	 */
	public Deployment deploy(String modelId,MultipartFile file);
	
	/**
	 * 删除流程部署，级联删除相关的流程实例
	 * @param deploymentId
	 * @return
	 */
	public void deleteDeployment(String deploymentId);
	
	/**
	 * 删除模型
	 * @param modelId
	 */
	public void deleteModel(String modelId);
	
	/**
	 * 更新流程定义的状态
	 * @param processDefinitionId
	 * @param action 挂起 或者 激活
	 */
	public String updateState(String processDefinitionId,String action);
	
	/**
	 * 通过流程实例找到流程实例正在执行的节点信息
	 * @param processInstance
	 * @return
	 */
	public Map<String, Object> getActivityXY(HistoricProcessInstance processInstance) throws Exception;
	
	/**
	 * 创建模型，并设置模型的基本信息
	 * @param key
	 * @param name
	 * @param description
	 * @return 成功则返回模型的ID
	 */
	public String createModel(String key,String name,String description);
	
	/**
	 * 用当前用户的ID、模型ID、当前时间、系统名称及相关的数据库连接信息 加密生成密文
	 * 加密前将加密信息转换成json格式
	 * 将密文和请求地址拼接
	 * @param modelId
	 * @param userId
	 * @return Map 包含url和加密信息
	 */
	public Map<String,String> getActivitiExplorerURL(String modelId) throws JsonProcessingException ;
	
}
