package com.hcis.ipr.train.effect.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipr.train.effect.dao.CheckDao;
import com.hcis.ipr.train.effect.entity.Check;
import com.hcis.ipr.train.effect.entity.Check.CheckStatus;
import com.hcis.ipr.train.effect.entity.CheckResult;
import com.hcis.ipr.train.effect.entity.CheckResult.CheckResultStatus;
import com.hcis.ipr.train.effect.service.ICheckResultService;
import com.hcis.ipr.train.effect.service.ICheckService;

@Service
public class CheckServiceImpl extends BaseServiceImpl<Check> implements ICheckService {
	
	@Autowired
	private CheckDao baseDao;
	
	@Autowired
	private ICheckResultService checkResultService;
	
	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}

	@Override
	public int save(Check check) throws IOException {
		int count = 0;
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String loginUserId = loginUser.getId();
		String id = check.getId();
		//处理项目ID、教师ID，转换为json集合格式保存
		String projectIds = check.getProjectIds();
		if(StringUtils.isNotEmpty(projectIds)) {
			check.setProjectIds(JsonUtil.toJson(projectIds.split(",")));
		}
		String teacherIds = check.getTeacherIds();
		if(StringUtils.isNotEmpty(teacherIds)) {
			check.setTeacherIds(JsonUtil.toJson(teacherIds.split(",")));
		}
		if(StringUtils.isEmpty(id)) {//新增
			check.setStatus(CheckStatus.UNPUBLISHED.toString());
			count = this.create(check, loginUserId);
		} else {//更新
			count = this.update(check, loginUserId);
		}
		return count;
	}
	
	@Override
	public String publish(Check check) throws IOException {
		String errorMsg = "";
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String loginUserId = loginUser.getId();
		String id= check.getId();
		check = this.read(id);
		String projectIds = check.getProjectIds();
		String teacherIds = check.getTeacherIds();
		if(StringUtils.isEmpty(projectIds)) {
			return "抽查项目为空，发布失败";
		} else if(StringUtils.isEmpty(teacherIds)) {
			return "评估专家为空，发布失败";
		}
		/*
		 * 更改抽查的状态为发布
		 */
		check.setPublishTime(new Date());
		check.setStatus(CheckStatus.PUBLISHED.toString());
		this.update(check, loginUserId);
		/*
		 * 生成评估任务
		 */
		List<String> projectIdList = JsonUtil.fromJson(projectIds, List.class);
		List<String> teacherIdList = JsonUtil.fromJson(teacherIds, List.class);
		for (String projectId : projectIdList) {
			for (String teacherId : teacherIdList) {
				CheckResult checkResult = new CheckResult();
				checkResult.setCheckId(id);
				checkResult.setProjectId(projectId);
				checkResult.setTeacherId(teacherId);
				checkResult.setStatus(CheckResultStatus.UNDONE.toString());//未评估
				checkResultService.create(checkResult, loginUserId);
			}
		}
		return errorMsg;
	}
}
