package com.hcis.ipr.train.register.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.register.entity.TrainRegister;

public interface ITrainRegisterService extends IBaseService<TrainRegister>{

	public int saveTrainRegister(SearchParam searchParam,HttpServletRequest request);
	public int batchDelete(SearchParam searchParam,HttpServletRequest request);
	public int signUp(SearchParam searchParam,HttpServletRequest request) throws ParseException;
	public int auditUser(SearchParam searchParam,HttpServletRequest request);
	/**
	 * 培训报名审核的邮箱发送
	 * @param auditStatus 审核状态
	 * @param toUserIds		报名人员
	 * @return
	 */
	public AjaxReturnObject  sendEmailTrain(String auditStatus,String toUserIds);
	
	public List<Map<String,Object>> selectCountRegister(Map<String,Object> map);
}
