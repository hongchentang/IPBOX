package com.hcis.ipanther.common.regions.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.regions.dao.RegionsDao;
import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.common.regions.service.IRegionsService;
import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipanther.common.user.utils.UserRegionsUtils;
import com.hcis.ipanther.core.cache.local.CacheReloadInvoker;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.utils.JSONUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service(value="regionsService")
public class RegionsServiceImpl extends CacheReloadInvoker implements IRegionsService {

	@Autowired
	private RegionsDao regionsDao;

	@Override
	public int addRegions(Regions regions) {
		int re = 0;
		if (regions != null) {
			regions.setDefaultValue();
			regions.setCreateTime(new Date());
			regions.setId(Identities.uuid2());  
			re = regionsDao.insertSelective(regions);
			this.invokeCacheReload();
		}
		return re;
	}
	
	@Override
	public int editRegions(Regions regions) {
		int re = 0;
		if (regions != null) {
			regions.setUpdateTime(new Date());

			re = regionsDao.updateByPrimaryKeySelective(regions);
			this.invokeCacheReload();
		}
		return re;
	}

	@Override
	public Regions getRegions(Regions regions) {
		Regions regionsTemp = null;
		if (regions != null) {
			regionsTemp = (Regions) regionsDao.selectByPrimaryKey(regions.getId());
		}
		return regionsTemp;
	}

	@Override
	public int deleteByLogic(Regions regions) {
		int re = 0;
		if (regions != null) {
			regions.setUpdateTime(new Date());

			re = regionsDao.deleteByLogic(regions);
			this.invokeCacheReload();
		}
		return re;
	}
	
	//根据名称模糊匹配,用于用户导入
	public List<Regions> selectByMap(Map map){
		return regionsDao.selectByMap(map);
	}
	
	public RegionsDao getRegionsDao() {
		return regionsDao;
	}

	public void setRegionsDao(RegionsDao regionsDao) {
		this.regionsDao = regionsDao;
	}

	@Override
	public List<Regions> selectBySearchParam(SearchParam searchParam) {
		// TODO Auto-generated method stub
		return regionsDao.selectBySearchParam(searchParam);
	}
	
	//生成区域树
	@Override
	public String getAreaTree(SearchParam searchParam) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		List<Regions> currentRegions=UserRegionsUtils.getRegionsByUser(loginUser.getRegionsCode(), loginUser.getUserRegionsList(),true);
		return UserRegionsUtils.getRegionsJson(currentRegions,null,new BigDecimal(4));
	}
	
	//所有广东省的区域（用户操作）
	public String getAllAreaTree(SearchParam searchParam){
		List<Map<String,Object>> nodeMapList=new ArrayList<Map<String,Object>>();
		List<Regions> nodeList =new ArrayList<Regions>();
		//广东省
		Regions gds=RegionsUtil.getRegions("440000");
		nodeList.add(gds);
		//市
		List<Regions> cityList= RegionsUtil.getCityList(gds.getRegionsCode());
		nodeList.addAll(cityList);
		//区
		for(Regions r:cityList){
			List<Regions> countiesList=RegionsUtil.getCountiesList(r.getRegionsCode());
			if(null!=countiesList&&countiesList.size()>0){
				nodeList.addAll(countiesList);
			}
		}
		//遍历并组装成Map
		for(Regions r:nodeList){
			this.addNode(nodeMapList, r);
		}
		return JSONUtils.getJSONString(nodeMapList);
	}
	
	public void addNode(List<Map<String,Object>> nodeMapList,Regions r) {
		Map<String,Object> nodeMap=new HashMap<String,Object>();
		nodeMap.put("id",r.getRegionsCode()); 
		nodeMap.put("pid",r.getParentCode());
		nodeMap.put("text",r.getRegionsName());
		nodeMap.put("iconCls","");
		if(!r.getRegionsLevel().equals(new BigDecimal(3))){
			nodeMap.put("state","closed"); 
		}
		nodeMap.put("checked", false);
		nodeMapList.add(nodeMap);
	}

	@Override
	public Object selectUserByProvinceAndCity(Map map) {
		return regionsDao.selectUserByProvinceAndCity(map);
	}
}
