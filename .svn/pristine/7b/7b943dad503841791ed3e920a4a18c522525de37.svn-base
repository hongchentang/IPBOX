package com.hcis.ipanther.common.regions.service;

import java.util.List;
import java.util.Map;

import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.core.web.vo.SearchParam;

public interface IRegionsService {
	
	public int addRegions(Regions regions);
	
	public int editRegions(Regions  regions);
	
	public Regions getRegions(Regions regions);
	
	public int deleteByLogic(Regions regions);
	
	public List<Regions> selectBySearchParam(SearchParam searchParam);
	
	public List<Regions> selectByMap(Map map);
	
	public String getAreaTree(SearchParam searchParam);
	
	//递归查询
	public Object selectUserByProvinceAndCity(Map map);
}
