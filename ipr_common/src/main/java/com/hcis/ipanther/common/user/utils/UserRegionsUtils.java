/**
 * 
 */
package com.hcis.ipanther.common.user.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springside.modules.utils.Collections3;

import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipanther.common.user.entity.UserRegions;
import com.hcis.ipanther.core.utils.JSONUtils;

/**
 * @author Chao
 *
 * 主要用于用户区域授权后，用户区域树的读取
 */
public class UserRegionsUtils {
	
	/**
	 * 获取当前用户部门所属区域及子区域
	 * 不加载街镇
	 * @param currentRegionsCode
	 * @param userRegionsList
	 * @return
	 */
	public static final List<Regions> getRegionsByUser(String currentRegionsCode,List<UserRegions> userRegionsList){
		return getRegionsByUser(currentRegionsCode, userRegionsList, false);
	}
	
	/**
	 * 获取当前用户部门所属区域及子区域
	 * @param currentRegionsCode 当前用户所属区域
	 * @param userRegionsList 授权区域列表
	 * @param isLoadStreet 是否加载到街镇级别
	 * @return
	 */
	public static final List<Regions> getRegionsByUser(String currentRegionsCode,List<UserRegions> userRegionsList,boolean isLoadStreet){
		List<Regions> regionsList=new ArrayList<Regions>();
		//使用Set，避免重复加入，判断方法见Regions中的equals
		Set<Regions> regionsSet=new HashSet<Regions>();
		//1.加入默认区域
		Regions currentRegions=RegionsUtil.getRegions(currentRegionsCode);
		regionsSet.add(currentRegions);
		//2.加入默认区域的下属区域（实现，可能去掉）
		regionsSet.addAll(RegionsUtil.getChildRegions(currentRegions.getRegionsCode(),isLoadStreet));
		//3.加入授权的区域
		if(CollectionUtils.isNotEmpty(userRegionsList)){
			for(UserRegions ur:userRegionsList){
				Regions uRegions=RegionsUtil.getRegions(ur.getRegionsCode());
				regionsSet.add(uRegions);
				//如果包括子集
				if(StringUtils.equals(ur.getHasChild(),"1")){
					regionsSet.addAll(RegionsUtil.getChildRegions(uRegions.getRegionsCode()));
				}
			}
		}
		regionsList.addAll(regionsSet);
		Collections.sort(regionsList, new Comparator<Regions>(){
			@Override
			public int compare(Regions o1, Regions o2) {
				try{
					int n1=NumberUtils.toInt(o1.getRegionsCode(),0);
					int n2=NumberUtils.toInt(o2.getRegionsCode(),0);
					return n1-n2;//正序
				}
				catch(Exception e){
					
				}
				return 0;
			}});
		return regionsList;
	}

	/**
	 * 返回EasyUI的tree使用的data
	 * @param regionsList
	 * @return
	 */
	public static final String getRegionsJson(List<Regions> regionsList){
		return getRegionsJson(regionsList,null,new BigDecimal(3));
	}
	
	/**
	 * 返回EasyUI的tree使用的data
	 * @param regionsList
	 * @param checkedMap
	 * @return
	 */
	public static final String getRegionsJson(List<Regions> regionsList,Map<String,String> checkedMap,BigDecimal closedLevel){
		if(CollectionUtils.isNotEmpty(regionsList)){
			List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
			for(Regions r:regionsList){
				Map<String,Object> nodeMap=new HashMap<String,Object>();
				nodeMap.put("id",r.getRegionsCode()); 
				nodeMap.put("pid",r.getParentCode());
				nodeMap.put("text",r.getRegionsName());
				nodeMap.put("iconCls","");
				if(!r.getRegionsLevel().equals(closedLevel)){
					nodeMap.put("state","closed"); 
				}
				if(checkedMap!=null){
					nodeMap.put("checked", (checkedMap!=null&&checkedMap.containsKey(r.getRegionsCode())));
					Map<String,Object> attributesMap=new HashMap<String,Object>();
					attributesMap.put("hasChild", checkedMap.get("hasChild_"+r.getRegionsCode()));
					nodeMap.put("attributes",attributesMap);
				}
				mapList.add(nodeMap);
			}
			return JSONUtils.getJSONString(mapList); 
		}
		return null;
	}
	
	/**
	 * 重载 UserRegionsUtils.getRegionsJson 
	 * @param regionsList
	 * @param checkedMap
	 * @return
	 */
	public static final String getRegionsJson(List<Regions> regionsList,Map<String,String> checkedMap) {
		return getRegionsJson(regionsList, checkedMap, new BigDecimal(3));
	}
	
	
	public static Map<String,String> getUserRegionsCheckedMap(List<UserRegions> userRegionsList){
		Map<String,String> checkedMap=new HashMap<String,String>();
		if(CollectionUtils.isNotEmpty(userRegionsList)){
			for(UserRegions ur:userRegionsList){
				checkedMap.put(ur.getRegionsCode(),ur.getRegionsCode());
				checkedMap.put("hasChild_"+ur.getRegionsCode(),ur.getHasChild());
			}
		}
		return checkedMap;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getRegionCodes(String currentRegionsCode,List<UserRegions> userRegionsList){
		List<Regions> regions = getRegionsByUser(currentRegionsCode,userRegionsList);
		return Collections3.extractToList(regions, "regionsCode");

	}
	
	public static List<String> getRegionCodes(String currentRegionsCode){
		List<Regions> regions = getRegionsByUser(currentRegionsCode);
		return Collections3.extractToList(regions, "regionsCode");

	}
	
	/**
	 * 获取当前用户部门所属区域及子区域
	 * @param currentRegionsCode 当前用户所属区域
	 * @param userRegionsList 授权区域列表
	 * @return
	 */
	public static final List<Regions> getRegionsByUser(String currentRegionsCode){
		List<Regions> regionsList=new ArrayList<Regions>();
		//使用Set，避免重复加入，判断方法见Regions中的equals
		Set<Regions> regionsSet=new HashSet<Regions>();
		//1.加入默认区域
		Regions currentRegions=RegionsUtil.getRegions(currentRegionsCode);
		regionsSet.add(currentRegions);
		//2.加入默认区域的下属区域  只有下级
		regionsSet.addAll(RegionsUtil.getOnlyChildRegions(currentRegions.getRegionsCode()));
/*		//3.加入授权的区域
		if(CollectionUtils.isNotEmpty(userRegionsList)){
			for(UserRegions ur:userRegionsList){
				Regions uRegions=RegionsUtil.getRegions(ur.getRegionsCode());
				regionsSet.add(uRegions);
				//如果包括子集
				if(StringUtils.equals(ur.getHasChild(),"1")){
					regionsSet.addAll(RegionsUtil.getChildRegions(uRegions.getRegionsCode()));
				}
			}
		}*/
		regionsList.addAll(regionsSet);
		Collections.sort(regionsList, new Comparator<Regions>(){
			@Override
			public int compare(Regions o1, Regions o2) {
				try{
					int n1=NumberUtils.toInt(o1.getRegionsCode(),0);
					int n2=NumberUtils.toInt(o2.getRegionsCode(),0);
					return n1-n2;//正序
				}
				catch(Exception e){
					
				}
				return 0;
			}});
		return regionsList;
	}
}
