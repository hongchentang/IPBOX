/**
 * 
 */
package com.hcis.ipanther.core.service.impl.mybatis;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.hcis.ipanther.core.entity.BaseEntity;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.IBaseFormService;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.utils.StringUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chao 2013年12月6日 下午4:35:04
 * 
 */
public abstract class BaseFormServiceImpl<T extends BaseEntity> implements IBaseFormService<T> {
	
	protected Logger logger=LoggerFactory.getLogger(getClass());

	@Override
	public List<T> list(SearchParam searchParam) {
		return this.getBaseDao().selectBySearchParam(searchParam);
	}

	@Override
	public T get(String id) {
		return (T) this.getBaseDao().selectByPrimaryKey(id);
	}

	@Override
	public Response create(T obj, String creator) {
		if (obj != null) {
			if (StringUtils.isEmpty(obj.getId())) {
				obj.setId(Identities.uuid2());
			}
			obj.setDefaultValue();
			if (StringUtils.isNotEmpty(creator)) {
				obj.setCreator(creator);
			}
			int count = this.getBaseDao().insertSelective(obj);
			return count>0?Response.successInstance():Response.failInstance();
		}
		return Response.failInstance();
	}

	@Override
	public Response update(T obj, String updatedby) {
		if (obj != null) {
			if (StringUtils.isNotEmpty(updatedby)) {
				obj.setUpdatedby(updatedby);
			}
			obj.setUpdateTime(new Date());
			int count = this.getBaseDao().updateByPrimaryKeySelective(obj);
			return count>0?Response.successInstance():Response.failInstance();
		}
		return Response.failInstance();
	}

	@Override
	public Response delete(String id, String updatedby) {
		if (StringUtils.isNotEmpty(id)) {
			T obj = (T) new BaseEntity();
			obj.setId(id);
			if (StringUtils.isNotEmpty(updatedby)) {
				obj.setUpdatedby(updatedby);
			}
			obj.setUpdateTime(new Date());
			int count = this.getBaseDao().deleteByLogic(obj);
			return count>0?Response.successInstance():Response.failInstance();
		}
		return Response.failInstance();
	}
	
	@Override
	public Response batchDelete(List<String> ids,String updatedby){
		if (ids != null && !ids.isEmpty()) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("ids", ids);
			if (StringUtils.isNotEmpty(updatedby)) {
				param.put("updatedby", updatedby);
			}
			param.put("updateTime", new Date());
			int count = this.getBaseDao().batchDeleteByLogic(param);
			return count>0?Response.successInstance():Response.failInstance();
		}
		return Response.failInstance();
	}

	public abstract MyBatisDao getBaseDao();

}
