package com.hcis.ipanther.common.dept.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.dept.dao.UnitIntellectualDao;
import com.hcis.ipanther.common.dept.entity.UnitIntellectual;
import com.hcis.ipanther.common.dept.service.IUnitIntellectualService;
import com.hcis.ipanther.core.cache.local.CacheReloadInvoker;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service("unitIntellectualService")
public class UnitIntellectualServiceImpl extends CacheReloadInvoker implements IUnitIntellectualService {
	
	private final static Log logger = LogFactory.getLog(DepartmentServiceImpl.class);
	
	@Autowired
	private UnitIntellectualDao unitIntellectualDao;
	
	@Override
	public List<UnitIntellectual> list(SearchParam searchParam) {
		return unitIntellectualDao.selectBySearchParam(searchParam);
	}

	@Override
	//@Cacheable(value="iprcache:deptcache:deptinfo",condition="#id!=null",key="'iprcache:deptcache:deptinfo:'+#id")
	public UnitIntellectual read(String id) {
		if(StringUtils.isNotEmpty(id)){
			return unitIntellectualDao.selectByPrimaryKey(id);
		}
		return null;
	}

	@Override
	public int create(UnitIntellectual obj, String creator) {
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
	//@CacheEvict(value="iprcache:deptcache:deptinfo",condition="#obj!=null",key="'iprcache:deptcache:deptinfo:'+#obj.id")
	public int update(UnitIntellectual obj, String updateBy) {
		if (obj != null) {
			obj.setUpdatedby(updateBy);
			obj.setUpdateTime(new Date());
			return this.getBaseDao().updateByPrimaryKeySelective(obj);
		}
		return 0;
	}

	@Override
	//@CacheEvict(value="iprcache:deptcache:deptinfo",condition="#obj!=null",key="'iprcache:deptcache:deptinfo:'+#obj.id")
	public int delete(UnitIntellectual obj, String updateBy) {
		if (obj != null && obj.getId() != null) {
			obj.setUpdatedby(updateBy);
			obj.setUpdateTime(new Date());
			return this.getBaseDao().deleteByLogic(obj);
		}
		return 0;
	}

	public UnitIntellectualDao getUnitIntellectualDao() {
		return unitIntellectualDao;
	}

	public UnitIntellectualDao getBaseDao() {
		return unitIntellectualDao;
	}


	
	
}
