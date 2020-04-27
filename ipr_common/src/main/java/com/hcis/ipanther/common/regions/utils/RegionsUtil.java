package com.hcis.ipanther.common.regions.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import net.sf.json.JSONArray;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.regions.dao.RegionsDao;
import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.common.user.utils.UserRegionsUtils;
import com.hcis.ipanther.core.cache.local.ICacheReloader;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author lianghuahuang
 *
 */
@Component("regionsUtil")
public class RegionsUtil implements ICacheReloader{
	
	@Autowired
	private RegionsDao regionsDao;

	private static Map<String,Regions> regionsMap;//regionsCode:regions
	private static List<Regions> nationList;
	private static Map<String,List<Regions>> provinceMap;//nationCode:province
	private static Map<String,List<Regions>> cityMap;//provinceCode:city
	private static Map<String,List<Regions>> countiesMap;//cityCode:counties
	private static Map<String,List<Regions>> streetsMap;
	private static List<Regions> allList;
	public void setRegionsDao(RegionsDao regionsDao) {
		this.regionsDao = regionsDao;
	}
	
	public RegionsDao getRegionsDao() {
		return regionsDao;
	}

	@PostConstruct
	public void init(){
		regionsMap = new HashMap<String,Regions>();
		nationList=new ArrayList<Regions>();
		provinceMap = new HashMap<String,List<Regions>>();
		cityMap = new HashMap<String,List<Regions>>();
		countiesMap = new HashMap<String,List<Regions>>();
		streetsMap = new HashMap<String,List<Regions>>();
		
		Regions nation = new Regions();
		nation.setRegionsCode(CommonConfig.getProperty("regions.nationCode"));
		nation.setRegionsName("中国");
		nation.setRegionsLevel(BigDecimal.valueOf(0));
		regionsMap.put(CommonConfig.getProperty("regions.nationCode"), nation);
		
		//取出所有数据，一层一层循环后处理
		SearchParam searchParam=new SearchParam();
		/*searchParam.setPageAvailable(false);*/
		Pagination pagination =new Pagination();
		pagination.setAvailable(false);
		searchParam.setPagination(pagination);
		
		allList= regionsDao.selectBySearchParam(searchParam);
		
		//取出所有数据，一个一个处理
		for(Regions regions:allList){
			//国家数据cityMap
			if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_NATION){
				nationList.add(regions);
			}
			//省级数据provinceMap
			else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_PROVINCE){
				List<Regions> provinceList=ObjectUtils.defaultIfNull(provinceMap.get(regions.getParentCode()),new ArrayList<Regions>());
				provinceList.add(regions);
				provinceMap.put(regions.getParentCode(),provinceList);
			}
			//市级数据cityMap
			else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_CITY){
				List<Regions> cityList=ObjectUtils.defaultIfNull(cityMap.get(regions.getParentCode()),new ArrayList<Regions>());
				cityList.add(regions);
				cityMap.put(regions.getParentCode(),cityList);
			}
			//区级数据
			else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_COUNTIES){
				List<Regions> countisList=ObjectUtils.defaultIfNull(countiesMap.get(regions.getParentCode()),new ArrayList<Regions>());
				countisList.add(regions);
				countiesMap.put(regions.getParentCode(),countisList);
			}
			//街镇数据
			else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_STREET){
				List<Regions> streetsList=ObjectUtils.defaultIfNull(streetsMap.get(regions.getParentCode()),new ArrayList<Regions>());
				streetsList.add(regions);
				streetsMap.put(regions.getParentCode(),streetsList);
			}
			regionsMap.put(regions.getRegionsCode(), regions);
		}
	}
	
	//所有地区选择
	public static String getRegionsOptions(){
		StringBuffer sb = new StringBuffer("<option value=''>请选择</option>");
		if(regionsMap!=null){
			for(Regions regions:regionsMap.values()){
				sb.append("<option value='").append(regions.getRegionsCode()).append("'>");
				sb.append(regions.getRegionsName());
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
	
	//所有地区选择
	public static String getRegionsOptionsSelected(String defaultValue){
		StringBuffer sb = new StringBuffer("<option value=''>请选择</option>");
		if(regionsMap!=null){
			for(Regions regions:regionsMap.values()){
				sb.append("<option value='").append(regions.getRegionsCode()).append("' ");
				if(!StringUtils.isEmpty(defaultValue)&&defaultValue.equals(regions.getRegionsCode())){
					sb.append("selected");
				}
				sb.append(" >");
				sb.append(regions.getRegionsName());
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 通用输出选择框的方法，如果不需要默认值，则defaultValue留空
	 * @param regionsList
	 * @param defaultValue
	 * @return
	 */
	private static String createSelectOptions(List<Regions> regionsList,String defaultValue){
//		StringBuffer sb = new StringBuffer("<option value=''>请选择</option>");//改由页面上决定是否有此选项
		StringBuffer sb = new StringBuffer();
		if(regionsList!=null){
			for(Regions regions:regionsList){
				sb.append("<option value='").append(regions.getRegionsCode()).append("' ");
				if(StringUtils.isNotBlank(defaultValue)&&StringUtils.equals(defaultValue,regions.getRegionsCode())){
					sb.append("selected");
				}
				sb.append(" >");
				sb.append(regions.getRegionsName());
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 省级选择框
	 */
	public static List<Regions> getProvinceList(String parentCode){
		return provinceMap.get(parentCode);
	}
	public static String getProvinceOptions(String parentCode){
		List<Regions> regionsList=getProvinceList(parentCode);
		return createSelectOptions(regionsList,null);
	}
	public static String getProvinceOptionsSelected(String parentCode,String defaultValue){
		List<Regions> regionsList=getProvinceList(parentCode);
		return createSelectOptions(regionsList,defaultValue);
	}
	
	/**
	 * 市级选择框
	 */
	public static List<Regions> getCityList(String parentCode){
		return cityMap.get(parentCode);
	}
	public static String getCityOptions(String parentCode){
		List<Regions> regionsList=getCityList(parentCode);
		return createSelectOptions(regionsList,null);
	}
	public static String getCityOptionsSelected(String parentCode,String defaultValue){
		List<Regions> regionsList=getCityList(parentCode);
		return createSelectOptions(regionsList,defaultValue);
	}
	
	/**
	 * 区级选择框
	 */
	public static List<Regions> getCountiesList(String parentCode){
		return countiesMap.get(parentCode);
	}
	/**
	 * 街道
	 */
	public static List<Regions> getStreetsList(String parentCode){
		return streetsMap.get(parentCode);
	}
	public static String getCountiesOptions(String parentCode){
		List<Regions> regionsList=getCountiesList(parentCode);
		return createSelectOptions(regionsList,null);
	}
	public static String getCountiesOptionsSelected(String parentCode,String defaultValue){
		List<Regions> regionsList=getCountiesList(parentCode);
		return createSelectOptions(regionsList,defaultValue);
	}
	
	
	public static Regions getRegions(String regionsCode){
		if(StringUtils.isNotEmpty(regionsCode)&&regionsMap.containsKey(regionsCode)){
			return regionsMap.get(regionsCode);
		}
		return null;
	}
	public static String getRegionsName(String regionsCode){
		if(!StringUtils.isEmpty(regionsCode)&&regionsMap.containsKey(regionsCode)){
			return regionsMap.get(regionsCode).getRegionsName();
		}
		return null;
	}
	
	/**
	 * 通过区域编号得到完整的区域名称
	 * 如传入天河区的编号，返回：广东省 天河区
	 * @param regionsCode
	 * @return
	 */
	public static String getRegionsNameFull(String regionsCode) {
		String regionsName = "";
		Regions regions = getRegions(regionsCode);
		if(null!=regions) {
			regionsName = regions.getRegionsName();
			while(null!=regions&&regions.getRegionsLevel().intValue()!=RegionsConstants.REGIONS_LEVEL_PROVINCE) {
				regions = getRegions(regions.getParentCode());
				if(null!=regions) {
					regionsName = regions.getRegionsName()+" "+regionsName;
				}
			}
		}
		return regionsName;
	}
	
	/**
	 * 返回所有的子节点的List
	 * @param regionsCode
	 * @param isLoadStreet 是否加载到街镇级别
	 * @return
	 */
	public static List<Regions> getChildRegions(String regionsCode,boolean isLoadStreet){
		List<Regions> regionsList=new ArrayList<Regions>();
		Regions regions=getRegions(regionsCode);
		if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_PROVINCE){
			List<Regions> cityList=cityMap.get(regions.getRegionsCode());
			if(CollectionUtils.isNotEmpty(cityList)){
				regionsList.addAll(cityList);
				for(Regions city:cityList){
					List<Regions> countiyList=countiesMap.get(city.getRegionsCode());
					if(CollectionUtils.isNotEmpty(countiyList)){
						regionsList.addAll(countiyList);
						if(isLoadStreet) {//街镇
							for (Regions contry : countiyList) {
								List<Regions> streetsList=streetsMap.get(contry.getRegionsCode());
								if(CollectionUtils.isNotEmpty(streetsList)){
									regionsList.addAll(streetsList);
								}
							}							
						}
					}
				}
			}
		}
		else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_CITY){
			List<Regions> countiyList=countiesMap.get(regions.getRegionsCode());
			if(CollectionUtils.isNotEmpty(countiyList)){
				regionsList.addAll(countiyList);
				if(isLoadStreet) {//街镇
					for (Regions contry : countiyList) {
						List<Regions> streetsList=streetsMap.get(contry.getRegionsCode());
						if(CollectionUtils.isNotEmpty(streetsList)){
							regionsList.addAll(streetsList);
						}
					}
				}
			}
		}
		else if(isLoadStreet&&regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_COUNTIES){
			List<Regions> streetsList=streetsMap.get(regions.getRegionsCode());
			if(CollectionUtils.isNotEmpty(streetsList)){
				regionsList.addAll(streetsList);
			}
		}
		else {
			
		}
		return regionsList;
	}
	
	/**
	 * 返回所有的子节点的List
	 * 到区镇级别
	 * @param regionsCode
	 * @return
	 */
	public static List<Regions> getChildRegions(String regionsCode){
		return getChildRegions(regionsCode,true);
	}
	
	/**
	 * 返回子节点的List
	 * @param regionsCode
	 * @return
	 */
	public static List<Regions> getOnlyChildRegions(String regionsCode){
		List<Regions> regionsList=new ArrayList<Regions>();
		Regions regions=getRegions(regionsCode);
		if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_PROVINCE){
			List<Regions> cityList=cityMap.get(regions.getRegionsCode());
			if(CollectionUtils.isNotEmpty(cityList)){
				regionsList=cityList;
			/*	for(Regions city:cityList){
					regionsList.addAll(countiesMap.containsKey(city.getRegionsCode())?countiesMap.get(city.getRegionsCode()):new ArrayList<Regions>());
				}*/
			}
		}
		else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_CITY){
			regionsList=countiesMap.get(regions.getRegionsCode());
		}
		else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_COUNTIES){
			regionsList=streetsMap.get(regions.getRegionsCode());
		}
		else {
			
		}
		return regionsList;
	}
	
	/**
	 * dwz tree组件，地区树生成方法
	 * 调用例子：RegionsTreeHtml("440000","<ul class=\"tree treeFolder\">","<a href=\"listUserTeacher.do?{code}&regionsCode={regionsCode}\">{regionsName}</a>");
	 * @param provinceCode 计算节点,省级：provinceCode!=null,cityCode=null,countiesCode=null，
	 * @param cityCode  市级：provinceCode!=null,cityCode!=null,countiesCode=null，
	 * @param countiesCode 区县级：provinceCode!=null,cityCode!=null,countiesCode!=null，
	 * @param ulDefine 自定义ul内容，null时默认为<ul class="tree treeFolder">
	 * @param aDefine 自定义a的内容，null时默认为<a href="#">{regionsName}</a>,如果
	 * 	可以设置参数，
	 *  {nationCode}固定为000000,{provinceCode}省代码,{cityCode}市代码,{countiesCode}区代码
	 *  {regionsName}此节点名,{regionsCode}此节点代码,{parentCode}此节点父节点代码
	 * @return
	 */
	public static StringBuffer getRegionsTreeHtml(String provinceCode,String cityCode,String countiesCode,String ulDefine,String aDefine){
		StringBuffer sb=new StringBuffer();
		if(StringUtils.isEmpty(ulDefine)){
			ulDefine="<ul class=\"tree treeFolder\">";
		}
		if(StringUtils.isEmpty(aDefine)){
			aDefine="<a href=\"#\">{regionsName}</a>";
		}
		String nationCodeStr="\\{nationCode\\}";
		String provinceCodeStr="\\{provinceCode\\}";
		String cityCodeStr="\\{cityCode\\}";
		String countiesCodeStr="\\{countiesCode\\}";
		String regionsNameStr="\\{regionsName\\}";
		String regionsCodeStr="\\{regionsCode\\}";
		String parentCodeStr="\\{parentCode\\}";
		String aContent=null;
		String code=null;
		
		sb.append(ulDefine);//start
		if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//省
			Regions region=regionsMap.get(provinceCode);
			aContent=aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			sb.append("<li>").append(aContent);
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				sb.append("<ul>");
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					aContent=aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
									.replaceAll(provinceCodeStr, provinceCode)
									.replaceAll(cityCodeStr, city.getRegionsCode())
									.replaceAll(countiesCodeStr, "")
									.replaceAll(regionsNameStr, city.getRegionsName())
									.replaceAll(regionsCodeStr, city.getRegionsCode())
									.replaceAll(parentCodeStr, city.getParentCode());
					sb.append("<li>").append(aContent);
					List<Regions> countiesList=countiesMap.get(city.getRegionsCode());
					if(countiesList!=null&&countiesList.size()>0){
						sb.append("<ul>");
						for(int k=0;k<countiesList.size();k++){
							Regions counties=countiesList.get(k);
							aContent=aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
											.replaceAll(provinceCodeStr, provinceCode)
											.replaceAll(cityCodeStr, city.getRegionsCode())
											.replaceAll(countiesCodeStr, counties.getRegionsCode())
											.replaceAll(regionsNameStr, counties.getRegionsName())
											.replaceAll(regionsCodeStr, counties.getRegionsCode())
											.replaceAll(parentCodeStr, counties.getParentCode());
							sb.append("<li>").append(aContent).append("</li>");
						}
						sb.append("</ul>");
					}
					sb.append("</li>");
				}
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
		else if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isNotEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//市级
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){
						aContent=aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
														.replaceAll(provinceCodeStr, provinceCode)
										.replaceAll(cityCodeStr, city.getRegionsCode())
										.replaceAll(countiesCodeStr, "")
										.replaceAll(regionsNameStr, city.getRegionsName())
										.replaceAll(regionsCodeStr, city.getRegionsCode())
										.replaceAll(parentCodeStr, city.getParentCode());
						sb.append("<li>").append(aContent);
						List<Regions> countiesList=countiesMap.get(city.getRegionsCode());
						if(countiesList!=null&&countiesList.size()>0){
							sb.append("<ul>");
							for(int k=0;k<countiesList.size();k++){
								Regions counties=countiesList.get(k);
								aContent=aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
												.replaceAll(provinceCodeStr, provinceCode)
												.replaceAll(cityCodeStr, city.getRegionsCode())
												.replaceAll(countiesCodeStr, counties.getRegionsCode())
												.replaceAll(regionsNameStr, counties.getRegionsName())
												.replaceAll(regionsCodeStr, counties.getRegionsCode())
												.replaceAll(parentCodeStr, counties.getParentCode());
								sb.append("<li>").append(aContent).append("</li>");
							}
							sb.append("</ul>");
						}
						sb.append("</li>");
						break;
					}
				}
			}
		}
		else{//区县
			List<Regions> countiesList=countiesMap.get(cityCode);
			if(countiesList!=null&&countiesList.size()>0){
				for(int k=0;k<countiesList.size();k++){
					Regions counties=countiesList.get(k);
					if(counties.getRegionsCode().equals(countiesCode)){
						aContent=aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
								.replaceAll(provinceCodeStr, provinceCode)
								.replaceAll(cityCodeStr, cityCode)
								.replaceAll(countiesCodeStr, counties.getRegionsCode())
								.replaceAll(regionsNameStr, counties.getRegionsName())
								.replaceAll(regionsCodeStr, counties.getRegionsCode())
								.replaceAll(parentCodeStr, counties.getParentCode());
						sb.append("<li>").append(aContent).append("</li>");
						break;
					}
				}
			}
		}
		sb.append("</ul>");//end
		return sb;
	}

	/**
	 * dwz tree组件，地区树生成方法,只有省级的树，用于选择省级
	 * @param provinceCode 计算节点,省级：provinceCode!=null,cityCode=null,countiesCode=null，
	 * @param cityCode  市级：provinceCode!=null,cityCode!=null,countiesCode=null，
	 * @param countiesCode 区县级：provinceCode!=null,cityCode!=null,countiesCode!=null，
	 * @param provinceCheckString 已选择的province的json字符串
	 * @return
	 */
	public static StringBuffer getRelationProvinceTreeHtml(String provinceCode,String cityCode,String countiesCode,String provinceCheckString){
		StringBuffer sb=new StringBuffer();
		String ulDefine="<ul class=\"tree treeFolder treeCheck expand\">";
		String aDefine="{regionsName}";
		String nationCodeStr="\\{nationCode\\}";
		String provinceCodeStr="\\{provinceCode\\}";
		String cityCodeStr="\\{cityCode\\}";
		String countiesCodeStr="\\{countiesCode\\}";
		String regionsNameStr="\\{regionsName\\}";
		String regionsCodeStr="\\{regionsCode\\}";
		String parentCodeStr="\\{parentCode\\}";
		String aContent=null;
		String code=null;
		
		sb.append(ulDefine);//start
		if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//省
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck\" tvalue=\""+region.getRegionsCode()+"\" "+(StringUtils.contains(provinceCheckString,region.getRegionsCode())||region.getRegionsCode().equals(provinceCode)?"checked=\"true\"":"")+" >";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			sb.append("</li>");
		}
		else if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isNotEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//市级
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck\" tvalue=\""+region.getRegionsCode()+"\" "+(StringUtils.contains(provinceCheckString,region.getRegionsCode())?"checked=\"true\"":"")+" >";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			sb.append("</li>");
		}
		else{//区县
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck\" tvalue=\""+region.getRegionsCode()+"\" "+(StringUtils.contains(provinceCheckString,region.getRegionsCode())?"checked=\"true\"":"")+" >";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			sb.append("</li>");
		}
		sb.append("</ul>");//end
		return sb;
	}
	
	/**
	 * dwz tree组件，省+市 树生成方法，用于选择市级
	 * @param provinceCode 计算节点,省级：provinceCode!=null,cityCode=null,countiesCode=null，
	 * @param cityCode  市级：provinceCode!=null,cityCode!=null,countiesCode=null，
	 * @param countiesCode 区县级：provinceCode!=null,cityCode!=null,countiesCode!=null，
	 * @param cityCheckString 已选择的市级列表
	 * @return
	 */
	public static StringBuffer getRelationCityTreeHtml(String provinceCode,String cityCode,String countiesCode,String cityCheckString ){
		StringBuffer sb=new StringBuffer();
		String ulDefine="<ul class=\"tree treeFolder treeCheck expand\">";
		String aDefine="{regionsName}";
		String nationCodeStr="\\{nationCode\\}";
		String provinceCodeStr="\\{provinceCode\\}";
		String cityCodeStr="\\{cityCode\\}";
		String countiesCodeStr="\\{countiesCode\\}";
		String regionsNameStr="\\{regionsName\\}";
		String regionsCodeStr="\\{regionsCode\\}";
		String parentCodeStr="\\{parentCode\\}";
		String aContent=null;
		String code=null;
		
		sb.append(ulDefine);//start
		if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//省
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck_city\" tvalue=\""+region.getRegionsCode()+"\">";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				sb.append("<ul>");
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					aContent="<a tname=\"cityCheck\" tvalue=\""+city.getRegionsCode()+"\" "+(StringUtils.contains(cityCheckString,region.getRegionsCode())?"checked=\"true\"":"")+" >";
					aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
									.replaceAll(provinceCodeStr, provinceCode)
									.replaceAll(cityCodeStr, city.getRegionsCode())
									.replaceAll(countiesCodeStr, "")
									.replaceAll(regionsNameStr, city.getRegionsName())
									.replaceAll(regionsCodeStr, city.getRegionsCode())
									.replaceAll(parentCodeStr, city.getParentCode());
					aContent=aContent+"</a>";
					sb.append("<li>").append(aContent);
					sb.append("</li>");
				}
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
		else if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isNotEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//市级
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck_city\" tvalue=\""+region.getRegionsCode()+"\">";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				sb.append("<ul>");
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){//只显示自己
						aContent="<a tname=\"cityCheck\" tvalue=\""+city.getRegionsCode()+"\" "+(StringUtils.contains(cityCheckString,region.getRegionsCode())||city.getRegionsCode().equals(cityCode)?"checked=\"true\"":"")+" >";
						aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
										.replaceAll(provinceCodeStr, provinceCode)
										.replaceAll(cityCodeStr, city.getRegionsCode())
										.replaceAll(countiesCodeStr, "")
										.replaceAll(regionsNameStr, city.getRegionsName())
										.replaceAll(regionsCodeStr, city.getRegionsCode())
										.replaceAll(parentCodeStr, city.getParentCode());
						aContent=aContent+"</a>";
						sb.append("<li>").append(aContent);
						sb.append("</li>");
					}
				}
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
		else{//区县
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck_city\" tvalue=\""+region.getRegionsCode()+"\">";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				sb.append("<ul>");
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){//只显示自己
						aContent="<a tname=\"cityCheck\" tvalue=\""+city.getRegionsCode()+"\" "+(StringUtils.contains(cityCheckString,region.getRegionsCode())||city.getRegionsCode().equals(cityCode)?"checked=\"true\"":"")+" >";
						aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
										.replaceAll(provinceCodeStr, provinceCode)
										.replaceAll(cityCodeStr, city.getRegionsCode())
										.replaceAll(countiesCodeStr, "")
										.replaceAll(regionsNameStr, city.getRegionsName())
										.replaceAll(regionsCodeStr, city.getRegionsCode())
										.replaceAll(parentCodeStr, city.getParentCode());
						aContent=aContent+"</a>";
						sb.append("<li>").append(aContent);
						sb.append("</li>");
					}
				}
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
		sb.append("</ul>");//end
		return sb;
	}
	
	/**
	 * dwz tree组件，树生成方法，显示区级，用于选择区
	 * @param provinceCode 计算节点,省级：provinceCode!=null,cityCode=null,countiesCode=null，
	 * @param cityCode  市级：provinceCode!=null,cityCode!=null,countiesCode=null，
	 * @param countiesCode 区县级：provinceCode!=null,cityCode!=null,countiesCode!=null，
	 * @param countiesCheckString 已选择的区列表
	 * @return
	 */
	public static StringBuffer getRelationCountiesTreeHtml(String provinceCode,String cityCode,String countiesCode,String countiesCheckString){
		StringBuffer sb=new StringBuffer();
		String ulDefine="<ul class=\"tree treeFolder treeCheck expand\">";
		String aDefine="{regionsName}";
		String nationCodeStr="\\{nationCode\\}";
		String provinceCodeStr="\\{provinceCode\\}";
		String cityCodeStr="\\{cityCode\\}";
		String countiesCodeStr="\\{countiesCode\\}";
		String regionsNameStr="\\{regionsName\\}";
		String regionsCodeStr="\\{regionsCode\\}";
		String parentCodeStr="\\{parentCode\\}";
		String aContent=null;
		String code=null;
		
		sb.append(ulDefine);//start
		if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//省
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck_counties\" tvalue=\""+region.getRegionsCode()+"\">";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				sb.append("<ul>");
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					aContent="<a tname=\"cityCheck_counties\" tvalue=\""+city.getRegionsCode()+"\">";
					aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
									.replaceAll(provinceCodeStr, provinceCode)
									.replaceAll(cityCodeStr, city.getRegionsCode())
									.replaceAll(countiesCodeStr, "")
									.replaceAll(regionsNameStr, city.getRegionsName())
									.replaceAll(regionsCodeStr, city.getRegionsCode())
									.replaceAll(parentCodeStr, city.getParentCode());
					aContent=aContent+"</a>";
					sb.append("<li>").append(aContent);
					List<Regions> countiesList=countiesMap.get(city.getRegionsCode());
					if(countiesList!=null&&countiesList.size()>0){
						sb.append("<ul>");
						for(int k=0;k<countiesList.size();k++){
							Regions counties=countiesList.get(k);
							aContent="<a tname=\"countiesCheck\" tvalue=\""+counties.getRegionsCode()+"\" "+(StringUtils.contains(countiesCheckString,region.getRegionsCode())?"checked=\"true\"":"")+" >";
							aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
											.replaceAll(provinceCodeStr, provinceCode)
											.replaceAll(cityCodeStr, city.getRegionsCode())
											.replaceAll(countiesCodeStr, counties.getRegionsCode())
											.replaceAll(regionsNameStr, counties.getRegionsName())
											.replaceAll(regionsCodeStr, counties.getRegionsCode())
											.replaceAll(parentCodeStr, counties.getParentCode());
							aContent=aContent+"</a>";
							sb.append("<li>").append(aContent).append("</li>");
						}
						sb.append("</ul>");
					}
					sb.append("</li>");
				}
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
		else if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isNotEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//市级
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck_counties\" tvalue=\""+region.getRegionsCode()+"\">";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				sb.append("<ul>");
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){//只显示自己
						aContent="<a tname=\"cityCheck_counties\" tvalue=\""+city.getRegionsCode()+"\">";
						aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
										.replaceAll(provinceCodeStr, provinceCode)
										.replaceAll(cityCodeStr, city.getRegionsCode())
										.replaceAll(countiesCodeStr, "")
										.replaceAll(regionsNameStr, city.getRegionsName())
										.replaceAll(regionsCodeStr, city.getRegionsCode())
										.replaceAll(parentCodeStr, city.getParentCode());
						aContent=aContent+"</a>";
						sb.append("<li>").append(aContent);
						List<Regions> countiesList=countiesMap.get(city.getRegionsCode());
						if(countiesList!=null&&countiesList.size()>0){
							sb.append("<ul>");
							for(int k=0;k<countiesList.size();k++){
								Regions counties=countiesList.get(k);
								aContent="<a tname=\"countiesCheck\" tvalue=\""+counties.getRegionsCode()+"\" "+(StringUtils.contains(countiesCheckString,region.getRegionsCode())?"checked=\"true\"":"")+" >";
								aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
												.replaceAll(provinceCodeStr, provinceCode)
												.replaceAll(cityCodeStr, city.getRegionsCode())
												.replaceAll(countiesCodeStr, counties.getRegionsCode())
												.replaceAll(regionsNameStr, counties.getRegionsName())
												.replaceAll(regionsCodeStr, counties.getRegionsCode())
												.replaceAll(parentCodeStr, counties.getParentCode());
								aContent=aContent+"</a>";
								sb.append("<li>").append(aContent).append("</li>");
							}
							sb.append("</ul>");
						}
						sb.append("</li>");
					}
				}
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
		else{//区县
			Regions region=regionsMap.get(provinceCode);
			aContent="<a tname=\"provinceCheck_counties\" tvalue=\""+region.getRegionsCode()+"\">";
			aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
							.replaceAll(provinceCodeStr, provinceCode)
							.replaceAll(cityCodeStr, "")
							.replaceAll(countiesCodeStr, "")
							.replaceAll(regionsNameStr, region.getRegionsName())
							.replaceAll(regionsCodeStr, region.getRegionsCode())
							.replaceAll(parentCodeStr, region.getParentCode());
			aContent=aContent+"</a>";
			sb.append("<li>").append(aContent);
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				sb.append("<ul>");
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){//只显示自己
						aContent="<a tname=\"cityCheck_counties\" tvalue=\""+city.getRegionsCode()+"\">";
						aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
										.replaceAll(provinceCodeStr, provinceCode)
										.replaceAll(cityCodeStr, city.getRegionsCode())
										.replaceAll(countiesCodeStr, "")
										.replaceAll(regionsNameStr, city.getRegionsName())
										.replaceAll(regionsCodeStr, city.getRegionsCode())
										.replaceAll(parentCodeStr, city.getParentCode());
						aContent=aContent+"</a>";
						sb.append("<li>").append(aContent);
						List<Regions> countiesList=countiesMap.get(city.getRegionsCode());
						if(countiesList!=null&&countiesList.size()>0){
							sb.append("<ul>");
							for(int k=0;k<countiesList.size();k++){
								Regions counties=countiesList.get(k);
								if(counties.getRegionsCode().equals(countiesCode)){//只显示自己
									aContent="<a tname=\"countiesCheck\" tvalue=\""+counties.getRegionsCode()+"\" "+(StringUtils.contains(countiesCheckString,region.getRegionsCode())||counties.getRegionsCode().equals(countiesCode)?"checked=\"true\"":"")+" >";
									aContent=aContent+aDefine.replaceAll(nationCodeStr, CommonConfig.getProperty("regions.nationCode"))
													.replaceAll(provinceCodeStr, provinceCode)
													.replaceAll(cityCodeStr, city.getRegionsCode())
													.replaceAll(countiesCodeStr, counties.getRegionsCode())
													.replaceAll(regionsNameStr, counties.getRegionsName())
													.replaceAll(regionsCodeStr, counties.getRegionsCode())
													.replaceAll(parentCodeStr, counties.getParentCode());
									aContent=aContent+"</a>";
									sb.append("<li>").append(aContent).append("</li>");
								}
							}
							sb.append("</ul>");
						}
						sb.append("</li>");
					}
				}
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
		sb.append("</ul>");//end
		return sb;
	}
	
	/**
	 * 获取当前节点在树中的前后节点
	 * @param provinceCode
	 * @param cityCode
	 * @param countiesCode
	 * @return
	 */
	public static Map<String,Map<String,List<Regions>>> getRelationRegions(String provinceCode,String cityCode,String countiesCode){
		Map<String,Map<String,List<Regions>>> relationRegionsMap=new HashMap<String,Map<String,List<Regions>>>();
		Map<String,List<Regions>> relationProvinceMap=new HashMap<String, List<Regions>>();
		Map<String,List<Regions>> relationCityMap=new HashMap<String, List<Regions>>();
		Map<String,List<Regions>> relationCountiesMap=new HashMap<String, List<Regions>>();
		if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//省级
			//国家
			Regions countryRegion=regionsMap.get(CommonConfig.getProperty("regions.nationCode"));
			//省
			Regions provinceRegion=regionsMap.get(provinceCode);
			List<Regions> provinceMapList=new ArrayList<Regions>();
			provinceMapList.add(provinceRegion);
			relationProvinceMap.put(countryRegion.getRegionsName()+","+countryRegion.getRegionsCode(), provinceMapList);
			relationRegionsMap.put("province",relationProvinceMap);
			//市
			List<Regions> cityMapList=new ArrayList<Regions>();
			List<Regions> cityList=getCityList(provinceCode);
			cityMapList.addAll(cityList==null?ListUtils.EMPTY_LIST:cityList);
			relationCityMap.put(provinceRegion.getRegionsName()+","+provinceRegion.getRegionsCode(),cityMapList);
			relationRegionsMap.put("city",relationCityMap);
			//区
			if(cityList!=null&&cityList.size()>0){
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					List<Regions> countiesMapList=new ArrayList<Regions>();
					countiesMapList=(countiesMap.get(city.getRegionsCode()));
					relationCountiesMap.put(city.getRegionsName()+","+city.getRegionsCode(),countiesMapList);
				}
			}
			relationRegionsMap.put("counties",relationCountiesMap);
		}
		else if(StringUtils.isNotEmpty(provinceCode)&&StringUtils.isNotEmpty(cityCode)&&StringUtils.isEmpty(countiesCode)){//市级
			//国家
			Regions countryRegion=regionsMap.get(CommonConfig.getProperty("regions.nationCode"));
			//省
			Regions provinceRegion=regionsMap.get(provinceCode);
			List<Regions> provinceMapList=new ArrayList<Regions>();
			provinceMapList.add(provinceRegion);
			relationProvinceMap.put(countryRegion.getRegionsName()+","+countryRegion.getRegionsCode(), provinceMapList);
			relationRegionsMap.put("province",relationProvinceMap);
			//市
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){//只显示自己
						List<Regions> cityMapList=new ArrayList<Regions>();
						cityMapList.add(city);
						relationCityMap.put(provinceRegion.getRegionsName()+","+provinceRegion.getRegionsCode(),cityMapList);
					}
				}
			}
			relationRegionsMap.put("city",relationCityMap);
			//区
			if(cityList!=null&&cityList.size()>0){
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){//只显示自己
						List<Regions> countiesMapList=new ArrayList<Regions>();
						countiesMapList=(countiesMap.get(city.getRegionsCode()));
						relationCountiesMap.put(city.getRegionsName()+","+city.getRegionsCode(),countiesMapList);
					}
				}
			}
			relationRegionsMap.put("counties",relationCountiesMap);
		}
		else{//区县
			//国家
			Regions countryRegion=regionsMap.get(CommonConfig.getProperty("regions.nationCode"));
			//省
			Regions provinceRegion=regionsMap.get(provinceCode);
			List<Regions> provinceMapList=new ArrayList<Regions>();
			provinceMapList.add(provinceRegion);
			relationProvinceMap.put(countryRegion.getRegionsName()+","+countryRegion.getRegionsCode(), provinceMapList);
			relationRegionsMap.put("province",relationProvinceMap);
			//市
			List<Regions> cityList=getCityList(provinceCode);
			if(cityList!=null&&cityList.size()>0){
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){//只显示自己
						List<Regions> cityMapList=new ArrayList<Regions>();
						cityMapList.add(city);
						relationCityMap.put(provinceRegion.getRegionsName()+","+provinceRegion.getRegionsCode(),cityMapList);
					}
				}
			}
			relationRegionsMap.put("city",relationCityMap);
			//区
			if(cityList!=null&&cityList.size()>0){
				for(int i=0;i<cityList.size();i++){
					Regions city=cityList.get(i);
					if(city.getRegionsCode().equals(cityCode)){//只显示自己
						List<Regions> countiesList=countiesMap.get(city.getRegionsCode());
						if(countiesList!=null&&countiesList.size()>0){
							for(int k=0;k<countiesList.size();k++){
								Regions counties=countiesList.get(k);
								if(counties.getRegionsCode().equals(countiesCode)){//只显示自己
									List<Regions> countiesMapList=new ArrayList<Regions>();
									countiesMapList.add(counties);
									relationCountiesMap.put(city.getRegionsName()+","+city.getRegionsCode(),countiesMapList);
								}
							}
						}
					}
				}
			}
			relationRegionsMap.put("counties",relationCountiesMap);
		}
		return relationRegionsMap;
	}
	
	/**
	 * 判断当前项目可以选择的区域
	 * 使用时根据机构等级传递响应的code，比如省级，只给provinceCode，cityCode,countiesCode留空
	 * 返回的可以提交的区域为code和checkCode的合集
	 * @param provinceCode 省代码
	 * @param cityCode 区代码
	 * @param countiesCode 市代码
	 * @param provinceCheckCode 配置权限的省代码 json
	 * @param cityCheckCode 配置权限的市代码 json
	 * @param countiesCheckCode 配置权限的区代码 json
	 * @return
	 */
	public static Map<String,Map<String,List<Regions>>> getAuditRegions(String provinceCode,String cityCode,String countiesCode
			,String provinceCheckCode,String cityCheckCode,String countiesCheckCode){
		
		Map<String,Map<String,List<Regions>>> relationRegionsMap=new HashMap<String,Map<String,List<Regions>>>();
		Map<String,List<Regions>> relationProvinceMap=new HashMap<String, List<Regions>>();
		Map<String,List<Regions>> relationCityMap=new HashMap<String, List<Regions>>();
		Map<String,List<Regions>> relationCountiesMap=new HashMap<String, List<Regions>>();
		//省级数据
		if(StringUtils.isNotEmpty(provinceCode)||StringUtils.isNotEmpty(provinceCheckCode)){
			//国家
			Regions countryRegion=regionsMap.get(CommonConfig.getProperty("regions.nationCode"));
			//合并数据
			List<String> codeList=new ArrayList<String>();
			if(StringUtils.isNotEmpty(provinceCode)){
				codeList.add(provinceCode);
			}
			if(StringUtils.isNotEmpty(provinceCheckCode)){
				JSONArray jsonArray=JSONArray.fromObject(provinceCheckCode);
				Object[] codes= jsonArray.toArray();
				for(Object codeStr:codes){
					if(!codeList.contains(ObjectUtils.toString(codeStr))){
						codeList.add(ObjectUtils.toString(codeStr));
					}
				}
			}
			//省
			List<Regions> mapList=new ArrayList<Regions>();
			for(String code:codeList){
				Regions theRegion=regionsMap.get(code);
				mapList.add(theRegion);
			}
			relationProvinceMap.put(countryRegion.getRegionsName()+","+countryRegion.getRegionsCode(), mapList);
			relationRegionsMap.put("province",relationProvinceMap);
		}
		//市级数据
		if(StringUtils.isNotEmpty(cityCode)||StringUtils.isNotEmpty(cityCheckCode)){
			//省
			Regions provinceRegion=regionsMap.get(CommonConfig.getProperty("regions.provinceCode"));
			
			//合并数据
			List<String> codeList=new ArrayList<String>();
			if(StringUtils.isNotEmpty(cityCode)){
				codeList.add(cityCode);
			}
			if(StringUtils.isNotEmpty(cityCheckCode)){
				JSONArray jsonArray=JSONArray.fromObject(cityCheckCode);
				Object[] codes= jsonArray.toArray();
				for(Object codeStr:codes){
					if(!codeList.contains(ObjectUtils.toString(codeStr))){
						codeList.add(ObjectUtils.toString(codeStr));
					}
				}
			}
			//市
			List<Regions> mapList=new ArrayList<Regions>();
			for(String code:codeList){
				Regions theRegion=regionsMap.get(code);
				mapList.add(theRegion);
			}
			relationCityMap.put(provinceRegion.getRegionsName()+","+provinceRegion.getRegionsCode(),mapList);
			relationRegionsMap.put("city",relationCityMap);
		}
		//区级数据
		if(StringUtils.isNotEmpty(countiesCode)||StringUtils.isNotEmpty(countiesCheckCode)){
			
			//合并数据
			List<String> codeList=new ArrayList<String>();
			if(StringUtils.isNotEmpty(countiesCode)){
				codeList.add(countiesCode);
			}
			if(StringUtils.isNotEmpty(countiesCheckCode)){
				JSONArray jsonArray=JSONArray.fromObject(countiesCheckCode);
				Object[] codes= jsonArray.toArray();
				for(Object codeStr:codes){
					if(!codeList.contains(ObjectUtils.toString(codeStr))){
						codeList.add(ObjectUtils.toString(codeStr));
					}
				}
			}
			//区
			for(String code:codeList){
				Regions theRegion=regionsMap.get(code);
				if(theRegion!=null){
					Regions theParentRegion=regionsMap.get(theRegion.getParentCode());
					if(theParentRegion!=null){
						List<Regions> mapList=relationCountiesMap.get(theParentRegion.getRegionsName()+","+theParentRegion.getRegionsCode());
						if(mapList==null){
							mapList=new ArrayList<Regions>();
						}
						mapList.add(theRegion);
						relationCountiesMap.put(theParentRegion.getRegionsName()+","+theParentRegion.getRegionsCode(),mapList);
					}
				}
			}
			relationRegionsMap.put("counties",relationCountiesMap);
		}
		return relationRegionsMap;
	}

	public static Regions getCurrentRegions(String province,String city,String counties){
		if(StringUtils.isNotEmpty(province)&&StringUtils.isNotEmpty(city)&&StringUtils.isNotEmpty(counties)){
			return regionsMap.get(counties);
		}
		else if(StringUtils.isNotEmpty(province)&&StringUtils.isNotEmpty(city)&&StringUtils.isEmpty(counties)){
			return regionsMap.get(city);
		}
		else if(StringUtils.isNotEmpty(province)&&StringUtils.isEmpty(city)&&StringUtils.isEmpty(counties)){
			return regionsMap.get(province);
		}
		else {
			return null;
		}
	}

	/**
	 * 获取此节点所在的省市区regionsName和级别
	 * @param regions
	 * @return
	 */
	public static Map<String,Object> getRegionNameTreeMap(String regionsCode){
		Regions regions=RegionsUtil.getRegions(regionsCode);
		if(regions!=null){
			Map<String,Object> resultMap=new HashMap<String,Object>();
			if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_PROVINCE){
				resultMap.put("deptLevel",regions.getRegionsLevel());
				resultMap.put("province",regions.getRegionsName());
				resultMap.put("city",null);
				resultMap.put("counties",null);
			}
			else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_CITY){
				resultMap.put("deptLevel",regions.getRegionsLevel());
				Regions provinceRegions=RegionsUtil.getRegions(regions.getParentCode());
				resultMap.put("province",provinceRegions.getRegionsName());
				resultMap.put("city",regions.getRegionsName());
				resultMap.put("counties",null);
			}
			else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_COUNTIES){
				resultMap.put("deptLevel",regions.getRegionsLevel());
				resultMap.put("counties",regions.getRegionsName());
				Regions cityRegions=RegionsUtil.getRegions(regions.getParentCode());
				resultMap.put("city",cityRegions.getRegionsName());
				Regions provinceRegions=RegionsUtil.getRegions(cityRegions.getParentCode());
				resultMap.put("province",provinceRegions.getRegionsName());
			}else{
				return null;
			}
			return resultMap;
		}
		else{
			return null;
		}
	}

	/**
	 * 获取此节点所在的省市区regionsCode和级别
	 * @param regions
	 * @return
	 */	
	public static Map<String,Object> getRegionTreeMap(String regionsCode){
		Regions regions=RegionsUtil.getRegions(regionsCode);
		return getRegionTreeMap(regions);
	}

	/**
	 * 获取此节点所在的省市区regionsCode和级别
	 * @param regions
	 * @return
	 */
	public static  Map<String,Object> getRegionTreeMap(Regions regions){
		if(regions!=null){
			Map<String,Object> resultMap=new HashMap<String,Object>();
			if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_PROVINCE){
				resultMap.put("deptLevel",regions.getRegionsLevel());
				resultMap.put("province",regions.getRegionsCode());
				resultMap.put("city",null);
				resultMap.put("counties",null);
			}
			else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_CITY){
				resultMap.put("deptLevel",regions.getRegionsLevel());
				Regions provinceRegions=RegionsUtil.getRegions(regions.getParentCode());
				resultMap.put("province",provinceRegions.getRegionsCode());
				resultMap.put("city",regions.getRegionsCode());
				resultMap.put("counties",null);
			}
			else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_COUNTIES){
				resultMap.put("deptLevel",regions.getRegionsLevel());
				resultMap.put("counties",regions.getRegionsCode());
				Regions cityRegions=RegionsUtil.getRegions(regions.getParentCode());
				resultMap.put("city",cityRegions.getRegionsCode());
				Regions provinceRegions=RegionsUtil.getRegions(cityRegions.getParentCode());
				resultMap.put("province",provinceRegions.getRegionsCode());
			}else{
				return null;
			}
			return resultMap;
		}
		else{
			return null;
		}
	}
	
	/**
	 * 获取所有的省市区街镇
	 * @return
	 */
	public static List<Regions> getAllList() {
		return allList;
	}
	
	/**
	 * 得到当前人所在区域和管辖区域及其下属的区域
	 * @param isLoadStreet 是否到街镇级别
	 * @return
	 */
	public static String getRegionsJson(boolean isLoadStreet) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		BigDecimal level = new BigDecimal(RegionsConstants.REGIONS_LEVEL_COUNTIES);
		if(isLoadStreet) {
			level = new BigDecimal(RegionsConstants.REGIONS_LEVEL_STREET);
		}
		return UserRegionsUtils.getRegionsJson(UserRegionsUtils.getRegionsByUser(loginUser.getRegionsCode(),loginUser.getUserRegionsList(),isLoadStreet),null,level);
	}
	/**
	 * 得到当前人所在区域和管辖区域及其下属的区域
	 * 不到街镇级别
	 * @return
	 */
	public static String getRegionsJson() {
		return getRegionsJson(false);
	}
	
	/**
	 * 得到指定地区的下一级的地区
	 * @param regionsCode
	 * @return
	 */
	public static String getNextRegionsByCode(String regionsCode) {
		List<Regions> regionses = new ArrayList<Regions>();
		Regions regions=getRegions(regionsCode);
		BigDecimal closeLevel = null;
		if(null!=regions) {
			if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_NATION) {
				regionses = provinceMap.get(regionsCode);
				closeLevel = new BigDecimal(RegionsConstants.REGIONS_LEVEL_PROVINCE);
			} else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_PROVINCE) {
				regionses = cityMap.get(regionsCode);
				closeLevel = new BigDecimal(RegionsConstants.REGIONS_LEVEL_CITY);
			} else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_CITY) {
				regionses = countiesMap.get(regionsCode);
				closeLevel = new BigDecimal(RegionsConstants.REGIONS_LEVEL_COUNTIES);
			} else if(regions.getRegionsLevel().intValue()==RegionsConstants.REGIONS_LEVEL_COUNTIES) {
				regionses = streetsMap.get(regionsCode);
				closeLevel = new BigDecimal(RegionsConstants.REGIONS_LEVEL_STREET);
			}
		}
		if(null==regionses||regionses.size()==0) {
			return "[]";
		}
		return UserRegionsUtils.getRegionsJson(regionses,null,closeLevel);
	}

	/**
	 *
	 * @param regionsCode
	 * @return
	 */
	public static List<Regions> getNextRegionsByCodeV2(String regionsCode) {
		List<Regions> regionses = new ArrayList<Regions>();
		Regions regions = getRegions(regionsCode);
		BigDecimal closeLevel = null;
		if (null != regions) {
			if (regions.getRegionsLevel().intValue() == RegionsConstants.REGIONS_LEVEL_NATION) {
				regionses = provinceMap.get(regionsCode);
				closeLevel = new BigDecimal(RegionsConstants.REGIONS_LEVEL_PROVINCE);
			} else if (regions.getRegionsLevel().intValue() == RegionsConstants.REGIONS_LEVEL_PROVINCE) {
				regionses = cityMap.get(regionsCode);
				closeLevel = new BigDecimal(RegionsConstants.REGIONS_LEVEL_CITY);
			} else if (regions.getRegionsLevel().intValue() == RegionsConstants.REGIONS_LEVEL_CITY) {
				regionses = countiesMap.get(regionsCode);
				closeLevel = new BigDecimal(RegionsConstants.REGIONS_LEVEL_COUNTIES);
			} else if (regions.getRegionsLevel().intValue() == RegionsConstants.REGIONS_LEVEL_COUNTIES) {
				regionses = streetsMap.get(regionsCode);
				closeLevel = new BigDecimal(RegionsConstants.REGIONS_LEVEL_STREET);
			}
		}
		if (null == regionses || regionses.size() == 0) {
			return null;
		}
		return regionses;
	}
	
	/**
	 * 返回中国的区域编码
	 * @return
	 */
	public static String getChinaRegionsCode() {
		return RegionsConstants.REGIONS_CODE_CHINA;
	}
	
	/**
	 * 返回广东的区域编码
	 * @return
	 */
	public static String getGDRegionsCode() {
		return RegionsConstants.REGIONS_CODE_GD;
	}
	
	
	/**
	 * 返回区域下 全部的 code list
	 * @param regionsCode
	 * @return
	 */
	public static List<String> getListALLChildRegionsCode(String regionsCode){
		List<String> list=new ArrayList<String>();
		Regions regions= getRegions(regionsCode);
		list.add(regions.getRegionsCode());
		if(regions!=null){
			List<Regions> nextList=getChildRegions(regionsCode);
			for (Regions childRegions : nextList) {
				list.add(childRegions.getRegionsCode());
			}
		}
		return list; 	
	}
	
	
	/**
	 * 返回区域下 全部的 code list 只包含区域下一级
	 * @param regionsCode
	 * @return
	 */
	public static List<String> getListNextChildRegionsCode(String regionsCode){
		List<String> list=new ArrayList<String>();
		Regions regions= getRegions(regionsCode);
		list.add(regions.getRegionsCode());
		if(regions!=null){
			List<Regions> nextList=getOnlyChildRegions(regionsCode);
			for (Regions childRegions : nextList) {
				list.add(childRegions.getRegionsCode());
			}
		}
		return list; 	
	}
	
	/**
	 * 获取站点的名字
	 * @param site 如：/guanghzou /meizhou
	 * @return
	 */
	public static String getSiteName(String site) {
		String regionsName = "";
		String regionsCode = getRegionsCodeFromSite(site);
		if(StringUtils.isNotEmpty(regionsCode)) {
			Regions regions = regionsMap.get(regionsCode);
			regionsName = regions.getRegionsName();
		}
		return regionsName;
	}
	
	/**
	 * 根据站点获取 区域代码
	 * @param site
	 * @return
	 */
	public static String getRegionsCodeFromSite(String site) {
		String regionsCode = "";
		if(StringUtils.isNotEmpty(site)) {
			if(-1!=site.indexOf("/")) {
				site = site.substring(1);//截去第一个字符串
			}
			regionsCode = DictionaryUtils.getEntryValue("SUB_SITE",site);
		} else {
			regionsCode = CommonConfig.getProperty("regions.provinceCode");
		}
		return regionsCode;
	}
	
	/**
	 * 获得所有的站点
	 * @param dictTypeCode
	 * @return
	 */
	public static List<DictEntry> getSiteList() {
		List<DictEntry> entries = DictionaryUtils.getEntryList("SUB_SITE");
		for (DictEntry dictEntry : entries) {//重新设置站点的名字
			dictEntry.setDictValue(regionsMap.get(dictEntry.getDictValue()).getRegionsName());
		}
		return entries;
	}
	
	@Override
	public void reloadCache() {
		init();		
	}
	
	/**
	 * 获取当前区域下的所有区域
	 * @param list
	 * @param regionsCode
	 * @return
	 */
	public static  String getChildNodes(String regionsCode) {
        if(regionsCode == null) return null;
       
        List<String> returnList=new ArrayList<String>();
        Regions regions=RegionsUtil.getRegions(regionsCode);
        return recursionFn(returnList,regions).toString();
    }

	public static List<String> recursionFn(List<String> returnList, Regions regions) {
		// 国家
		if ("RegionsConstants.REGIONS_LEVEL_NATION".equals(regions.getRegionsLevel().toString())) {
			returnList.add(regions.getRegionsCode());
			List<Regions> provinces = RegionsUtil.getProvinceList(regions.getRegionsCode());
			getChildList(returnList, provinces);
		}
		// 省
		else if ("RegionsConstants.REGIONS_LEVEL_PROVINCE".equals(regions.getRegionsLevel().toString())) {
			returnList.add(regions.getRegionsCode());
			List<Regions> cityList = RegionsUtil.getCityList(regions.getRegionsCode());
			getChildList(returnList, cityList);
		}
		// 市或者市区
		else if ("RegionsConstants.REGIONS_LEVEL_CITY".equals(regions.getRegionsLevel().toString())) {
			// 获取城区
			returnList.add(regions.getRegionsCode());
			List<Regions> counties = RegionsUtil.getCountiesList(regions.getRegionsCode());
			List<Regions> streets = RegionsUtil.getStreetsList(regions.getRegionsCode());
			getChildList(returnList, counties);
			getChildList(returnList, streets);
		}
		// 县 或 城区
		else if ("RegionsConstants.REGIONS_LEVEL_COUNTIES".equals(regions.getRegionsLevel().toString())) {
			returnList.add(regions.getRegionsCode());
			List<Regions> streets = RegionsUtil.getStreetsList(regions.getRegionsCode());
			getChildList(returnList, streets);
		}
		// 街道 或乡镇
		else if ("RegionsConstants.REGIONS_LEVEL_STREET".equals(regions.getRegionsLevel().toString())) {
			returnList.add(regions.getRegionsCode());
		}
		return returnList;
	}

	public static void getChildList(List<String> returnList, List<Regions> regionss) {
		if (regionss != null) {
			Iterator<Regions> it = regionss.iterator();
			while (it.hasNext()) {
				Regions n = (Regions) it.next();
				recursionFn(returnList, n);
			}
		}
	}
	
}
