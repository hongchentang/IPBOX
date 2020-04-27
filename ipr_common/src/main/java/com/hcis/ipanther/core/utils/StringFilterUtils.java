package com.hcis.ipanther.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串过滤工具类
 * @author wuwentao
 * @date 2015年11月12日
 */
public class StringFilterUtils {

	/**
	 * 对字符串进行过滤
	 * @param str
	 * @return
	 */
	public static String filter(String str) {
		//清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher((String)str);
		if(m.find()) {
			str = "";
		}
		return  str;
	}
	
}
