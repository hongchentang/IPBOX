package com.hcis.ipr.train.course.listener;

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
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.entity.CourseFlow;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.course.service.impl.CourseFlowServiceImpl;
import com.hcis.ipr.train.course.utils.CourseConstants;

/**
 * 监听课程审核操作，并进行相应的处理
 * @author 邓学辉
 *
 */

@Service("courseTaskListener")
public class CourseTaskListener implements TaskListener{
	private static final long serialVersionUID = 1785629297873173170L;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private TaskService taskService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private CourseFlowServiceImpl courseFlowService;

	@Override
	public void notify(DelegateTask delegateTask) {
		
		String taskDefKey = delegateTask.getTaskDefinitionKey();//任务的标识
		
		//request中的参数
    	Map<String, Object> paramMap = (Map<String, Object>) runtimeService.getVariable(delegateTask.getExecutionId(), CourseConstants.COURSE_VARIABLE_PARAM_MAP);
    	LoginUser loginUser=(LoginUser) runtimeService.getVariable(delegateTask.getExecutionId(), CourseConstants.COURSE_VARIABLE_LOGIN_USER);
    	Course course=(Course) runtimeService.getVariable(delegateTask.getExecutionId(), "course");
    	//课程申报
    	if(StringUtils.equals("writeInfo",taskDefKey)){
    		course.setDeclareUser(loginUser.getId());
    		course.setDeclareDept(loginUser.getFirstDeptId());
    		course.setDeclareTime(new Date());
    		course.setApplyCount(course.getApplyCount().add(new BigDecimal(1)));;
    		this.updateCourseStatus(course, "01", loginUser);//设成审核状态
    	}
    	//培训机构领导审核
    	if(StringUtils.equals("trainLeaderConfirm", taskDefKey)){
    		//保存审核日记
    		this.SaveCourseFlow(course, paramMap, loginUser,delegateTask);
    		//若不通过，则记录退回状态
    		if(StringUtils.equals(paramMap.get("status").toString(), CourseConstants.COURSE_ISPASS)){
    			this.updateCourseStatus(course, "03", loginUser);//设成退回状态
    		}else if(StringUtils.equals(paramMap.get("status").toString(), "03")){
    			this.updateCourseStatus(course, "04", loginUser);//设成不通过状态
    		}else if(course.getApplyCount().compareTo(course.getMaxApplyCount())==1){
    			this.updateCourseStatus(course, "04", loginUser);//超过申请次数
    		}
    	}
    	//广东省知识产权局领导审核
		if(StringUtils.equals("iprLeaderConfirm", taskDefKey)){
			//保存审核日记
    		this.SaveCourseFlow(course, paramMap, loginUser,delegateTask);
			//若不通过，则记录退回状态
    		if(StringUtils.equals(paramMap.get("status").toString(), CourseConstants.COURSE_ISPASS)){
    			this.updateCourseStatus(course, "03", loginUser);//设成退回状态
    		}else if(StringUtils.equals(paramMap.get("status").toString(), "03")){
    			this.updateCourseStatus(course, "04", loginUser);//设成不通过状态
    		}else if(course.getApplyCount().compareTo(course.getMaxApplyCount())==1){
    			this.updateCourseStatus(course, "04", loginUser);//超过申请次数
    		}else{
    			this.updateCourseStatus(course, "02", loginUser);//设成已审核状态
    		}
		}
	}
	
	//设置状态
	public void updateCourseStatus(Course course,String status,LoginUser loginUser){
		course.setStatus(status);
		courseService.update(course, loginUser.getId());
	}
	//保存审核日记
	public void SaveCourseFlow(Course course,Map<String, Object> paramMap,LoginUser loginUser,DelegateTask delegateTask){
		CourseFlow courseFlow=new CourseFlow();
		courseFlow.setCourseId(course.getId());
		courseFlow.setTaskName(delegateTask.getName());
		courseFlow.setTaskId(delegateTask.getId());
		courseFlow.setStatus(delegateTask.getTaskDefinitionKey());
		
		courseFlow.setAuditUser(loginUser.getId());
		courseFlow.setAuditContent(paramMap.get("auditContent").toString());
		courseFlow.setAuditResult(paramMap.get("status").toString());
		courseFlow.setAuditDept(loginUser.getFirstDeptId());
		courseFlow.setAuditTime(new Date());
		courseFlowService.create(courseFlow, loginUser.getId());
	}
	
}
