/**
 * 
 */
package com.haoyu.ipanther.common.excel.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.hcis.ipanther.core.utils.JsonUtil;

/**
 * @author Administrator
 *
 */
public class Dictionary {
	private Map<String,Object> dictMap;
	
	public Dictionary(String dictJsonString){
		dictMap = Maps.newHashMap();
		try {
			if(StringUtils.isNotEmpty(dictJsonString)){
				dictMap = JsonUtil.fromJson(dictJsonString, Map.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean containsKey(String key){
		return dictMap.containsKey(key);
	}
	
	public Object getValue(String key){
		return dictMap.get(key);
	}
	
}
