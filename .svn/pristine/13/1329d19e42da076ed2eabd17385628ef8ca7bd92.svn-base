/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 2013-8-21
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
*************************************************/
package com.hcis.ipanther.core.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.web.vo.SearchParam;

public class
MyBatisDao extends SqlSessionDaoSupport {
	
	//命名空间,默认为dao路径加上dao名称，将Dao替换为Mapper
	protected String namespace  = StringUtils.replace(StringUtils.replace(this.getClass().getName(), "dao", "mapper"),"Dao","Mapper");
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	/**
	 * 查询结果返回一个Map集合
	 * @param statement
	 * @param key
	 * @return
	 */
	protected Map selectForMap(String statement,String key){
		return this.getSqlSession().selectMap(statement, key);
	}
	
	/**
	 * 查询结果返回一个Map
	 * @param statement
	 * @param object
	 * @param key
	 * @return
	 */
	protected Map selectForMap(String statement,Object object,String key){
		return this.getSqlSession().selectMap(statement, object, key);
	}

	/**
	 * 查询结果返回一个List集合
	 * @param statement
	 * @return
	 */
	protected <E> List<E> selectForList(String statement){
		return this.getSqlSession().selectList(statement);
		
	}
	
	/**
	 * 查询结果返回一个List集合
	 * @param statement
	 * @param object
	 * @return
	 */
	protected <E> List<E> selectForList(String statement,Object e){
			return this.getSqlSession().selectList(statement, e);
	}
	

	/**
	 * 返回带分页的集合
	 * @param statement SqlMap文件需要实现 statement为 stament+"BySearchParam"
	 * @param e 参数对象
	 * @param key 用于封装 成SearchParam中paramMap的key值，用于sqlMap文件中调用参数对象
	 * @param pagination
	 * @return
	 */
	protected <E> List<E> selectForList(String statement,Object e,String key,Pagination pagination){
			SearchParam sp = new SearchParam();
			if(pagination!=null){
				sp.setPagination(pagination);
			}
			sp.getParamMap().put(key, e);
			return this.getSqlSession().selectList(statement+"BySearchParam", sp);
	}
	
	
	/**
	 * 根据请求参数查询
	 * statement为namespace+".selectByRequestParam"
	 * @param searchParam
	 * @return
	 */
	public <E> List<E> selectBySearchParam(SearchParam searchParam){
		return this.getSqlSession().selectList(namespace+".selectBySearchParam",searchParam);
	}
	
	/**
	 * 根据主键查询 
	 * statement=spring注入的namespace+.selectByPrimaryKey
	 * @param primaryKey
	 * @return
	 */
	public <E> E selectByPrimaryKey(String primaryKey){
		return this.getSqlSession().selectOne(namespace+".selectByPrimaryKey",primaryKey);
	}
	
	/**
	 * 根据主键查询 
	 * statement=spring注入的namespace+.selectByPrimaryKey
	 * @param primaryKey
	 * @return
	 */
	public <E> E selectByPrimaryKey(Object primaryKey){
		return this.getSqlSession().selectOne(namespace+".selectByPrimaryKey",primaryKey);
	}
	
	/**
	 * 根据双主键查询 
	 * statement=spring注入的namespace+.selectByPrimaryKey
	 * @param primaryKey
	 * @return
	 */
	public <E> E selectByDoublePrimaryKey(Object primaryKey){
		return this.getSqlSession().selectOne(namespace+".selectByDoublePrimaryKey",primaryKey);
	}
	
	/**
	 * 根据主键删除
	 * statement=spring注入的namespace+.selectByPrimaryKey
	 * @param primaryKey
	 * @return
	 */
	public int deleteByPrimaryKey(Object primaryKeyObject){
		return  this.getSqlSession().delete(namespace+".deleteByPrimaryKey", primaryKeyObject);
	}
	
	
	public int deleteByLogic(SearchParam searchParam){
		return this.getSqlSession().update(namespace+".deleteByLogic", searchParam);
	}
	
	public int deleteByLogic(Object object){
		return this.getSqlSession().update(namespace+".deleteByLogic", object);
	}
	
	public int batchDeleteByLogic(Object object){
		return this.getSqlSession().update(namespace+".batchDeleteByLogic", object);
	}
	
	public int deleteByPhysics(Object object){
		return this.getSqlSession().delete(namespace+".deleteByPhysics", object);
	}
	
	public int batchDeleteByPhysics(Object object){
		return this.getSqlSession().delete(namespace+".batchDeleteByPhysics", object);
	}
	
	public int deleteByPhysics(SearchParam searchParam){
		return this.getSqlSession().delete(namespace+".deleteByPhysics", searchParam);
	}
	
	public int retrieve(Object object){
		return this.getSqlSession().update(namespace+".retreive", object);
	}
	
	/**
	 * 选择性插入，根据传入的参数对象属性，属性不为空的则构造对应的插入字段
	 * statement=spring注入的namespace+.selectByPrimaryKey
	 * @param object
	 * @return
	 */
	public int insertSelective(Object object){
		return  this.getSqlSession().insert(namespace+".insertSelective", object);
	}
	
	/**
	 * 根据主键选择性更新，属性不为空的则构造对应的更新字段
	 * statement=spring注入的namespace+.selectByPrimaryKey
	 * @param object
	 * @return
	 */
	public int updateByPrimaryKeySelective(Object object){
		return  this.getSqlSession().insert(namespace+".updateByPrimaryKeySelective", object);
	}
	
	/**
	 * 根据主键更新字段值
	 * @param object
	 * @return
	 */
	public int updateByPrimaryKey(Object object){
		return  this.getSqlSession().insert(namespace+".updateByPrimaryKey", object);
	}
	
	
	/**
	 * 查询必须返回一个对象，多余或者没有返回，将抛出异常
	 * @param statement
	 * @param object
	 * @return
	 */
	protected Object selectForOneVO(String statement,Object object){
		return this.getSqlSession().selectOne(statement, object);
	}
	
	

	
	
	/**
	 * 查询必须返回一个对象，多余或者没有返回，将抛出异常
	 * @param statement
	 * @return
	 */
	protected Object selectForOneVO(String statement){
		return this.getSqlSession().selectOne(statement);
	}
	
	
	/**
	 * 查询结果返回一个对象，如果有多条记录则只返回第一条记录
	 * @param statement
	 * @param object
	 * @return
	 */
	protected Object selectForVO(String statement,Object object){
		List list = (List)this.getSqlSession().selectList(statement,object);
		return list!=null&&!list.isEmpty()?list.get(0):null;
	}
	

	
	/**
	 * 查询结果返回一个对象，如果有多条记录则只返回第一条记录
	 * @param statement
	 * @return
	 */
	protected Object selectForVO(String statement){
		List<Object> list = (List<Object>)this.getSqlSession().selectList(statement);
		return list!=null&&!list.isEmpty()?list.get(0):null;
	}
	

	/**
	 * 查询必须返回一个对象 ,且该对象必须能够转换成int类型，多余、没有返回或者无法转换成int类型，将抛出异常
	 * @param statement
	 * @param object
	 * @return
	 */
	protected int selectForInt(String statement,Object object){
		return Integer.parseInt(ObjectUtils.toString(this.getSqlSession().selectOne(statement, object)));
	}
	
	
	/**
	 * 查询必须返回一个对象 ,且该对象必须能够转换成int类型，多余、没有返回或者无法转换成int类型，将抛出异常
	 * @param statement
	 * @return
	 */
	protected int selectForInt(String statement){
		return Integer.parseInt(ObjectUtils.toString(this.getSqlSession().selectOne(statement)));				
	}
	
	/**
	 * 插入记录,statement由配置文件定义的namespace+".insert"组成
	 * 需要sqlMap文件支持
	 * @param object
	 * @return
	 */
	public int insert(Object object){
		return this.getSqlSession().insert(namespace+".insert", object);
	}
	
	protected int insert(String statement,Object object){
		return this.getSqlSession().insert(statement,object);
	}
	
	protected int insert(String statement){
		return this.getSqlSession().insert(statement);
	}
	
	protected int update(String statement,Object object){
		return this.getSqlSession().update(statement,object);
	}
	
	protected int update(String statement){
		return this.getSqlSession().update(statement);
	}
	
	protected int delete(String statement,Object object){
		return this.getSqlSession().delete(statement,object);
	}
	
	protected int delete(String statement){
		return this.getSqlSession().delete(statement);
	}

	
	/**
	 * 查询记录,statement由配置文件定义的namespace+".selectOne"组成
	 * 需要sqlMap文件支持
	 * @param object
	 * @return
	 */
	public Object selectOne(Object object){
		return (Object)this.getSqlSession().selectOne(namespace+".selectOne",object);
	}
	
	protected Object selectOne(String statement,Object object){
		return this.getSqlSession().selectOne(statement,object);
	}
	
	protected Object selectOne(String statement){
		return this.getSqlSession().selectOne(statement);
	}
}
