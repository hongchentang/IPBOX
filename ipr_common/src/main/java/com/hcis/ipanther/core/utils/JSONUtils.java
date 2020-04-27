package com.hcis.ipanther.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author lianghuahuang
 *
 */
public class JSONUtils {
	
	public static Map<String,Object> getJSONMap(String jsonStr){
		if(StringUtils.isNotEmpty(jsonStr)){
			try {
				return  JsonUtil.fromJson(jsonStr, Map.class);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	public static Object getJSONMapValue(String jsonStr,String key){
		if(StringUtils.isNotEmpty(jsonStr)){
			Map<String,Object> map=null;
				try {
					map = JsonUtil.fromJson(jsonStr, Map.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(map!=null&&map.containsKey(key)){
					return map.get(key);
				}
		}
		return "";
	}
	
	public static Object[] getJSONArray(String jsonStr){
		if(StringUtils.isEmpty(jsonStr)){
			return null;
		}
		try {
			return JsonUtil.fromJson(jsonStr, Object[].class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Map[] getJSONArrayMap(String jsonStr){
		if(StringUtils.isNotEmpty(jsonStr)){
		try {
			return JsonUtil.fromJson(jsonStr, Map[].class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		}
		return null;
	}
	public static Object[] getJSONArrayMapValue(String jsonStr,String key){
		if(StringUtils.isNotEmpty(jsonStr)){
			Map<String,Object>[] map=null;
			try {
				 map= JsonUtil.fromJson(jsonStr, Map[].class);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			if(map!=null){
				 List<Object> list=new ArrayList<Object>();
				for (Map<String,Object> temp : map) {
					list.add(temp.get(key));
				}	
				if(!list.isEmpty()){
					return list.toArray();
				}
			}
		}
		return null;
	}
	
	public static String getJSONString(Object obj){
		try {
			return JsonUtil.toJson(obj);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
//	public static void main(String[] args){
//		//JSONObject jsonObj = JSONObject.fromObject("[{\"attachmentId\":\"42bd4d4fad284a92bdb0f466d1bc9008\",\"attachmentName\":\"jivejdon��ϸ����ĵ�.doc\"}]");
//		Object[] array = getJSONArrayMapValue("[{\"attachmentId\":\"42bd4d4fad284a92bdb0f466d1bc9008\",\"attachmentName\":\"jivejdon��ϸ����ĵ�.doc\"}]","attachmentName");
//		for(Object obj:array){
//			System.out.println(obj);
//		}
//	}
}
