package com.hcis.ipanther.core.utils;

import java.math.BigDecimal;

/**
 * @author lianghuahuang
 *
 */
public class NumberUtils {
	public static double divide(BigDecimal a,BigDecimal b,int scale,int roundingMode){
		if(a.doubleValue()==0||b.doubleValue()==0){
			return 0;
		}
		return a.divide(b, scale, roundingMode).doubleValue();
	}
}
