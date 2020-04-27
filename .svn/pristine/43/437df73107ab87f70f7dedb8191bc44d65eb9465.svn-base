/*************************************************
*ICommonDao.java
*
*2013-11-28
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.ipanther.core.persistence.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.hcis.ipanther.core.page.Pagination;



/**
 *
 *
 *@author 梁华璜
 */
public interface ICommonDao<T> {
	T selectById(Object object);
	
	List<T> select(T t); 
	
	/**
	 * 按照参数分页查询
	 * @param T
	 * @param pagination
	 * @return
	 */
	List<T> select(T t,Pagination pagination); 
	
	/**
	 * 插入
	 * @param T
	 * @return
	 */
	String insert(T t);
	/**
	 * 更新
	 * @param T
	 * @return
	 */
	int update(T t);
	/**
	 * 逻辑删除
	 * @param T
	 * @return
	 */
	int deleteByLogic(T t);
	
	int deleteByLogic(String updatedby,Object[] ids);
	/**
	 * 物理删除
	 * @param T
	 * @return
	 */
	int deleteByPhysics(T t);
	
	int deleteByPhysics(Object[] ids);
	/**
	 * 撤销逻辑删除
	 * @param T
	 * @return
	 */
	int retrieveDelete(T t);
	
	int retrieveDelete(String updatedby,Object[] ids);
}
