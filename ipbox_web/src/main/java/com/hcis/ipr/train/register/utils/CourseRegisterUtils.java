package com.hcis.ipr.train.register.utils;

public class CourseRegisterUtils {

	//用于导出成绩
	public static String setResut(String result){
		if(null!=result){
			return "1".equals(result)?"通过":"不通过";
		}else{
			return "";
		}
	}
}
