package com.hcis.ipr.train.project.listener;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.train.course.utils.CourseConstants;
import com.hcis.ipr.train.project.entity.Project;
import com.hcis.ipr.train.project.entity.ProjectFlow;
import com.hcis.ipr.train.project.service.IProjectFlowService;
import com.hcis.ipr.train.project.service.IProjectService;
import com.hcis.ipr.train.project.utils.ProjectConstants;

/**
 * 监听项目审核操作，并进行相应的处理
 * @author 邓学辉
 *
 */

@Service("projectTaskListener")
public class ProjectTaskListener implements TaskListener{
	
	private static final long serialVersionUID = 1785629287873173170L;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TaskService taskService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IProjectFlowService projectFlowService;

	@Override
	public void notify(DelegateTask delegateTask) {
		String taskDefKey = delegateTask.getTaskDefinitionKey();//任务的标识
		
		//request中的参数
    	Map<String, Object> paramMap = (Map<String, Object>) runtimeService.getVariable(delegateTask.getExecutionId(), ProjectConstants.PROJECT_VARIABLE_PARAM_MAP);
    	LoginUser loginUser=(LoginUser) runtimeService.getVariable(delegateTask.getExecutionId(), ProjectConstants.PROJECT_VARIABLE_LOGIN_USER);
    	Project project=(Project) runtimeService.getVariable(delegateTask.getExecutionId(), "project");
    	
    	//申报项目
    	if(StringUtils.equals("writeInfo",taskDefKey)){
    		project.setDeclareUser(loginUser.getId());
    		project.setDeclareDept(loginUser.getFirstDeptId());
    		project.setDeclareTime(new Date());
    		project.setApplyCount(project.getApplyCount().add(new BigDecimal(1)));;
    		this.updateProjectStatus(project, "01", loginUser);//设成审核状态
    	}
    	//培训机构领导审核
    	if(StringUtils.equals("trainLeaderConfirm", taskDefKey)){
    		//保存审核日记
    		this.SaveProjectFlow(project, paramMap, loginUser,delegateTask);
    		//若不通过，则记录退回状态
    		if(StringUtils.equals(paramMap.get("status").toString(), CourseConstants.COURSE_ISPASS)){
    			this.updateProjectStatus(project, "03", loginUser);//设成退回状态
    		}else if(StringUtils.equals(paramMap.get("status").toString(), "03")){
    			this.updateProjectStatus(project, "04", loginUser);//设成不通过状态
    		}else if(project.getApplyCount().compareTo(project.getMaxApplyCount())==1){
    			this.updateProjectStatus(project, "04", loginUser);//超过申请次数
    		}
    	}
    	//广东省知识产权局领导审核
		if(StringUtils.equals("iprLeaderConfirm", taskDefKey)){
			//保存审核日记
    		this.SaveProjectFlow(project, paramMap, loginUser,delegateTask);
			//若不通过，则记录退回状态
    		if(StringUtils.equals(paramMap.get("status").toString(), CourseConstants.COURSE_ISPASS)){
    			this.updateProjectStatus(project, "03", loginUser);//设成退回状态
    		}else if(StringUtils.equals(paramMap.get("status").toString(), "03")){
    			this.updateProjectStatus(project, "04", loginUser);//设成不通过状态
    		}else if(project.getApplyCount().compareTo(project.getMaxApplyCount())==1){
    			this.updateProjectStatus(project, "04", loginUser);//超过申请次数
    		}else{
    			this.updateProjectStatus(project, "02", loginUser);//设成已审核状态
    		}
		}
		
	}
	//设置状态
	public void updateProjectStatus(Project project,String status,LoginUser loginUser){
		project.setStatus(status);
		projectService.update(project, loginUser.getId());
	}
	//保存审核日记
	public void SaveProjectFlow(Project project,Map<String, Object> paramMap,LoginUser loginUser,DelegateTask delegateTask){
		ProjectFlow projectFlow=new ProjectFlow();
		projectFlow.setProjectId(project.getId());
		projectFlow.setTaskName(delegateTask.getName());
		projectFlow.setTaskId(delegateTask.getId());
		projectFlow.setStatus(delegateTask.getTaskDefinitionKey());
		
		projectFlow.setAuditUser(loginUser.getId());
		projectFlow.setAuditContent(paramMap.get("auditContent").toString());
		projectFlow.setAuditResult(paramMap.get("status").toString());
		projectFlow.setAuditDept(loginUser.getFirstDeptId());
		projectFlow.setAuditTime(new Date());
		projectFlowService.create(projectFlow, loginUser.getId());
	}

}
