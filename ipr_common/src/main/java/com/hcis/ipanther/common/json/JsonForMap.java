package com.hcis.ipanther.common.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
public class JsonForMap {

	
	public static JSONObject JsonFormartMap(ArrayList arrayList){
		Map<String, Object> map = new HashMap<String, Object>();
		int size = 0;
		if(null != arrayList && arrayList.size()>0){
			size = arrayList.size();
		}
		map.put("rows", arrayList);
		map.put("total",size);
		JSONObject object = JSONObject.fromObject(map);
		return object;
	}
	
	public static JSONObject JSONArrayForMap(List list){
		JSONObject object = new JSONObject();
		object.put("total", list.size());
		object.put("rows", list);
		return object;
	}
}
