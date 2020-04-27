/**
 * 
 */
package com.hcis.ipanther.core.entity;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class RemainTime implements Serializable{

	private static final long serialVersionUID = 557258102612249047L;
	
	private long day;
	
	private long hour;
	
	private long min;
	
	private String pattern;
	
	public RemainTime(String pattern){
		this.pattern = pattern;
	}

	public RemainTime(long day,long hour,long min,String pattern) {
		super();
		this.day = day;
		this.hour = hour;
		this.min = min;
		this.pattern = pattern;
	}
	public long getDay() {
		return day;
	}



	public void setDay(long day) {
		this.day = day;
	}



	public long getHour() {
		return hour;
	}



	public void setHour(long hour) {
		this.hour = hour;
	}



	public long getMin() {
		return min;
	}



	public void setMin(long min) {
		this.min = min;
	}



	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
