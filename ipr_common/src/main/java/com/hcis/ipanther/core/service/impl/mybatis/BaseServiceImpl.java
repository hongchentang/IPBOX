/**
 * 
 */
package com.hcis.ipanther.core.service.impl.mybatis;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcis.ipanther.core.entity.BaseEntity;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.utils.StringUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chao 2013年12月6日 下午4:35:04
 * 
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements IBaseService<T> {
	
	protected Logger logger=LoggerFactory.getLogger(getClass());

	@Override
	public List<T> list(SearchParam searchParam) {
		return this.getBaseDao().selectBySearchParam(searchParam);
	}

	@Override
	public T read(String id) {
		return (T) this.getBaseDao().selectByPrimaryKey(id);
	}

	@Override
	public int create(T obj, String creator) {
		if (obj != null) {
			if (StringUtils.isEmpty(obj.getId())) {
				obj.setId(Identities.uuid2());
			}
			obj.setDefaultValue();
			obj.setCreator(creator);
			return this.getBaseDao().insertSelective(obj);
		}
		return 0;
	}

	@Override
	public int update(T obj, String updateBy) {
		if (obj != null) {
			obj.setUpdatedby(updateBy);
			obj.setUpdateTime(new Date());
			return this.getBaseDao().updateByPrimaryKeySelective(obj);
		}
		return 0;
	}

	@Override
	public int delete(T obj, String updateBy) {
		if (obj != null && obj.getId() != null) {
			obj.setUpdatedby(updateBy);
			obj.setUpdateTime(new Date());
			return this.getBaseDao().deleteByLogic(obj);
		}
		return 0;
	}

	public abstract MyBatisDao getBaseDao();

}
