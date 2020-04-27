package com.hcis.ipanther.common.dict.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.core.utils.JSONUtils;

public class DictUtils {

	private final static Log log=LogFactory.getLog(DictUtils.class);
	
	public static String getEntryListJson(String dictTypeCode){
		if(StringUtils.isNotEmpty(dictTypeCode)){
			List<DictEntry> list= DictionaryUtils.getEntryList(dictTypeCode);
			List<Map<String, Object>> map=setList(list,"false");
			return  JSONUtils.getJSONString(map);
		}
		return null;
	}
	
	
	public static String getEntryListJsonOpen(String dictTypeCode,String isOpen){
		if(StringUtils.isNotEmpty(dictTypeCode)){
			List<DictEntry> list= DictionaryUtils.getEntryList(dictTypeCode);
			List<Map<String, Object>> map=setList(list,isOpen);
			return  JSONUtils.getJSONString(map);
		}
		return null;
	}
	
	
	private static List<Map<String,Object>> setList(List<com.hcis.ipanther.common.dict.vo.DictEntry> list,String isOpen){
		List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
		for (DictEntry object : list) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("createTime",object.getCreateTime());
			map.put("creator",object.getCreator());
			map.put("dictName",object.getDictName());
			map.put("dictTypeCode",object.getDictTypeCode());
			map.put("dictValue",object.getDictValue());
			map.put("dictId",object.getId());
			map.put("isDeleted",object.getIsDeleted());
			map.put("isHidden",object.getIsHidden());
			map.put("parentCode",object.getParentValue());
			map.put("parentName",object.getParentName());
			map.put("parentValue",object.getParentValue());
			map.put("rank",object.getRank());
			map.put("sortNo",object.getSortNo());
			map.put("updatedby",object.getUpdatedby());
			map.put("updateTime",object.getUpdateTime());
			map.put("version",object.getVersion());
			map.put("text",object.getDictName());
			map.put("pid",object.getParentValue());
			map.put("id",object.getDictValue());
			if(StringUtils.isEmpty(object.getParentValue())&&isOpen.equals("false")){
				map.put("state","closed");
			}else{
				map.put("state","open"); 
			}
			map.put("iconCls","");
			maps.add(map);
		}
		return maps;
	}
/*	
	public static String getEntryListJson(String dictTypeCode,String dictValue){
		if(StringUtils.isNotEmpty(dictTypeCode)){
			List<DictEntry> list= DictionaryUtils.getEntryList(dictTypeCode);
			List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
			for (DictEntry object : list) {
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("createTime",object.getCreateTime());
				map.put("creator",object.getCreator());
				map.put("dictName",object.getDictName());
				map.put("dictTypeCode",object.getDictTypeCode());
				map.put("dictValue",object.getDictValue());
				map.put("id",object.getId());
				map.put("isDeleted",object.getIsDeleted());
				map.put("isHidden",object.getIsHidden());
				map.put("isHidden",object.getIsHidden());
				map.put("isHidden",object.getIsHidden());
				
			}
			try {
				return JsonUtil.toJson(list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}*/
	
	
	public static String getAllParentValueJson(String parentCodeOne,String parentCodeTow){
		if(parentCodeOne!=null && parentCodeTow!=null){
			List<DictEntry> listOne= DictionaryUtils.getEntryList(parentCodeOne);
			List<DictEntry> listTow= DictionaryUtils.getEntryList(parentCodeTow);
			List<DictEntry> list=new ArrayList<DictEntry>();
			list.addAll(listOne);
			list.addAll(listTow);
			List<Map<String, Object>> map=setList(list,"false");
			return  JSONUtils.getJSONString(map);
		}
		return null;
	}
	
	
	public static String getEntryListName(String dictTypeCode,String dictValue){
		if(StringUtils.isNotEmpty(dictValue)){
			if(dictValue.indexOf(",")>=0){
				//List<String> list=new ArrayList<String>();
				StringBuffer buffer=new StringBuffer();
				String[] listDictValue=dictValue.split(",");
				for (int i = 0; i < listDictValue.length; i++) {
					String entryName=DictionaryUtils.getEntryName(dictTypeCode, listDictValue[i]);
					//list.add(entryName);
					buffer.append(entryName);
					if(i+1!=listDictValue.length){
						buffer.append(",&nbsp;&nbsp;");
					}
				}
				return  buffer.toString();
			}else{
				return DictionaryUtils.getEntryName(dictTypeCode, dictValue);
			}
		}
		return null;
	}
	
	public static String getNOParentCodeValueJson(String dictTypeCode){
		if(StringUtils.isNotEmpty(dictTypeCode)){
			List<com.hcis.ipanther.common.dict.vo.DictEntry> list= DictionaryUtils.getEntryListNotParent(dictTypeCode);
			List<Map<String, Object>> map=setList(list,"true");
			return  JSONUtils.getJSONString(map);
		}
		return null;
	}
	
	public static String getListSpanEntry(String dictTypeCode,String parentValue){
		if(StringUtils.isNotEmpty(dictTypeCode)&&StringUtils.isNotEmpty(parentValue)){
			List<DictEntry> list= DictionaryUtils.getEntryList(dictTypeCode, parentValue);
			StringBuffer buffer=new StringBuffer();
			for (DictEntry dictEntry : list) {
				buffer.append("<span>");
				buffer.append(dictEntry.getDictName());
				buffer.append("</span><br/>");
			}
			return  buffer.toString();
		}
		return null;
	}
	
	/**
	 * 
	 * @author wuwentao
	 * @param dictTypeCode
	 * @param dictValue 多个用逗号隔开
	 * @return 返回多个字典之，多个用逗号隔开
	 */
	public static String getEntryNameMulti(String dictTypeCode,String dictValue){
		String result = "";
		if(StringUtils.isNotEmpty(dictTypeCode)&&StringUtils.isNotEmpty(dictValue)) {
			try {
				String[] dictVals = dictValue.split(",");
				for (String dictVal : dictVals) {
					if(StringUtils.isNotEmpty(dictVal)) {
						String entryName = DictionaryUtils.getEntryName(dictTypeCode, dictVal);
						if(StringUtils.isNotEmpty(entryName)) {
							result+=","+entryName;							
						}
					}
				}
				if(StringUtils.isNotEmpty(result)) {
					result = result.substring(1);
				}
			} catch (Exception e) {
				log.error(DictUtils.class,e);
			}
		}
		return result;
	}
	
	/**
	 * 根据字典值取到字典名称，多个用逗号隔开
	 * @author wuwentao
	 * @param dictTypeCode
	 * @param dictValue json字符串，如：["11","12","22","23"]
	 * @return
	 */
	public static String getEntryNameByJson(String dictTypeCode,String dictValue) {
		String result = "";
		if(StringUtils.isNotEmpty(dictTypeCode)&&StringUtils.isNotEmpty(dictValue)) {
			dictValue = dictValue.replace("[", "").replace("]", "").replace("\"", "");
			result = DictUtils.getEntryNameMulti(dictTypeCode, dictValue);
		}
		return result;
	}
	
	/**
	 * 根据字典名称得到字典值
	 * @param dictTypeCode
	 * @param dictName 多个用英文逗号隔开
	 * @param isJson 是否返回json格式的字符串：通常dictName可能有多个值时时为true
	 * @return 字典值：多个用逗号隔开，如果isJson为true，则返回json格式的字符串
	 */
	public static String getEntryValueByName(String dictTypeCode,String dictName,boolean isJson) {
		String result = "";
		if(StringUtils.isNotEmpty(dictName)) {
			String[] dictNames = dictName.split(",");
			for (String _dictName : dictNames) {
				if(StringUtils.isNotEmpty(_dictName)) {
					String entryValue = DictionaryUtils.getEntryValue(dictTypeCode, _dictName);
					if(StringUtils.isNotEmpty(entryValue)) {
						result+=","+entryValue;
					}
				}
			}
			if(StringUtils.isNotEmpty(result)) {
				result = result.substring(1);
			}
		}
		return "".equals(result)?"":(isJson?JSONUtils.getJSONString(Arrays.asList(result.split(","))):result);
	}
	
	/**
	 * 重载 DictUtils.getEntryValueByName(String dictTypeCode, String dictName, boolean isJson)
	 * isJson 为 false
	 * @param dictTypeCode
	 * @param dictName
	 * @return
	 */
	public static String getEntryValueByName(String dictTypeCode,String dictName) {
		return getEntryValueByName(dictTypeCode, dictName, false);
	}
}
