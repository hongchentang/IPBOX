package com.hcis.ipanther.common.dict.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;

import com.hcis.ipanther.common.excel.utils.ExcelExport;

public class DictTypeUtils {
	
	private static   String[]  TITLE={"数据类型名称","数据类型编码","数据项名称","数据项值","排序号","父数据项名称"	,"父数据项值","是否隐藏"};
	
	
	public static void dictTypeExport(List<Map<String, Object>> resultList,HttpServletResponse response){
		if(resultList!=null){
		String name="dictTypeExport";
		List<String[]> list=new ArrayList<String[]>();
		for (int i = 0; i < resultList.size(); i++) {
			 String[] temp={ObjectUtils.toString(resultList.get(i).get("dictTypeName")),ObjectUtils.toString(resultList.get(i).get("dictTypeCode")),
					 ObjectUtils.toString(resultList.get(i).get("dictName")),ObjectUtils.toString(resultList.get(i).get("dictValue")), 
					 ObjectUtils.toString(resultList.get(i).get("sortNo")),ObjectUtils.toString(resultList.get(i).get("parentName")), 
					 ObjectUtils.toString(resultList.get(i).get("parentValue")),ObjectUtils.toString(resultList.get(i).get("isHidden"))};
			        list.add(temp);
		}
		
		try {
			ExcelExport.outSuccessExcel(list, name,TITLE, response,"导出数据字典");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	
	
}
