package com.hcis.ipr.wechat.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.core.utils.JSONUtils;

/**
 * 用于微信页面的多选框的数据加载和处理
 * @author Administrator
 *
 */
public class WeUiUtil {

	/**
	 * 加载单选/多选框的数据
	 * @param dictTypeCode
	 * @return
	 */
	public static String getWeUIEntryList(String dictTypeCode){
		if(StringUtils.isNotEmpty(dictTypeCode)){
			List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
			Map<String,Object> map;
			List<DictEntry> list = DictionaryUtils.getEntryList(dictTypeCode);
			
			if("USER_TYPE".equals(dictTypeCode)){
				for(DictEntry d : list){
					if(d.getParentValue()!=null){
						map = new HashMap<String,Object>();
						map.put("title", d.getDictName());
						map.put("value", d.getDictValue());
						data.add(map);
					}
				}
			}else{
				for(DictEntry d : list){
						map = new HashMap<String,Object>();
						map.put("title", d.getDictName());
						map.put("value", d.getDictValue());
						data.add(map);
				}
			}
			
			return  JSONUtils.getJSONString(data);
		}
		return null;
	}
	
	/**
	 * 加载单选/多选框的数据
	 * @param dictTypeCode
	 * @param value
	 * @param type 选择框类型  radio为单选框
	 * @return
	 */
	public static Map<String,Object> getDataValue(String dictTypeCode,String value,String type){
		
		if(StringUtils.isNotEmpty(dictTypeCode)){
			Map<String,Object> map = new HashMap<String,Object>();
			Object[] s = new Object[250];
			StringBuffer name = new StringBuffer();
			List<DictEntry> list = DictionaryUtils.getEntryList(dictTypeCode);
			if(!"radio".equals(type)&&value!=null&&!"".equals(value)){
				s = JSONUtils.getJSONArray(value);
				if("".equals(s[0])){
					return null;
				}
				//用于存放data-values
				map.put("value", StringUtils.join(s, ","));
				
				for(int i=0;i<s.length;i++){
					String dictValue = (String) s[i];
					for(int j=0;j<list.size();j++){
						if(dictValue.equals(list.get(j).getDictValue()))
							name.append(",").append(list.get(j).getDictName());
					}
					
				}
				
			}
			else{
				for(int j=0;j<list.size();j++){
					if(value.equals(list.get(j).getDictValue())){
						name.append(",").append(list.get(j).getDictName());
						break;
					}
				}
			}
			//用于存放页面的value的值
			if(!"".equals(name.toString()))
				map.put("data", name.toString().substring(1, name.length()));
			else
				map.put("data", "");
			return map;
		}
		return null;
	}
	
	
	
	
	
	
}
