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

import com.hcis.ipanther.common.dept.dao.UnitFundDao;
import com.hcis.ipanther.common.dept.entity.UnitFund;
import com.hcis.ipanther.common.dept.service.IUnitFundService;
import com.hcis.ipanther.core.cache.local.CacheReloadInvoker;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service("unitFundService")
public class UnitFundServiceImpl extends CacheReloadInvoker implements IUnitFundService {
	
	private final static Log logger = LogFactory.getLog(DepartmentServiceImpl.class);
	
	@Autowired
	private UnitFundDao unitFundDao;
	
	@Override
	public List<UnitFund> list(SearchParam searchParam) {
		return unitFundDao.selectBySearchParam(searchParam);
	}

	@Override
	//@Cacheable(value="iprcache:deptcache:deptinfo",condition="#id!=null",key="'iprcache:deptcache:deptinfo:'+#id")
	public UnitFund read(String id) {
		if(StringUtils.isNotEmpty(id)){
			return unitFundDao.selectByPrimaryKey(id);
		}
		return null;
	}

	@Override
	public int create(UnitFund obj, String creator) {
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
	public int update(UnitFund obj, String updateBy) {
		if (obj != null) {
			obj.setUpdatedby(updateBy);
			obj.setUpdateTime(new Date());
			return this.getBaseDao().updateByPrimaryKeySelective(obj);
		}
		return 0;
	}

	@Override
	//@CacheEvict(value="iprcache:deptcache:deptinfo",condition="#obj!=null",key="'iprcache:deptcache:deptinfo:'+#obj.id")
	public int delete(UnitFund obj, String updateBy) {
		if (obj != null && obj.getId() != null) {
			obj.setUpdatedby(updateBy);
			obj.setUpdateTime(new Date());
			return this.getBaseDao().deleteByLogic(obj);
		}
		return 0;
	}

	public UnitFundDao getUnitFundDao() {
		return unitFundDao;
	}

	public UnitFundDao getBaseDao() {
		return unitFundDao;
	}


	
	
}
