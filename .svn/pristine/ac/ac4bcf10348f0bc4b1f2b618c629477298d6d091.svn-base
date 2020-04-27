package com.hcis.ipanther.core.web.controller;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.BaseListForm;
import com.hcis.ipanther.core.web.vo.SearchParam;




public interface  IBaseFormController<T>{
	
	/**
	 * 列表查询
	 * @param searchParam
	 * @return
	 */
	ModelAndView list(SearchParam searchParam);	
	/**
	 * 查看
	 * @param t
	 * @return
	 */
	 ModelAndView view(T t);
    /**
     * 进入新增页面
     * @return
     */
     String add();
    /**
     * 进入编辑页面
     * @param t
     * @return
     */
     ModelAndView edit(T t);	
	/**
	 * 保存,包含新增和修改
	 * 按照version决定是新增还是修改
	 * @param t
	 * @return
	 */
     AjaxReturnObject save(T t,Errors errors);	
	/**
	 * 删除操作
	 * @param t
	 * @return
	 */
	 AjaxReturnObject delete(T t);	
	/**
	 * 批量删除操作
	 * @param baseListForm
	 * @return
	 */
	AjaxReturnObject batchDelete(BaseListForm<T> baseListForm);
	
}
