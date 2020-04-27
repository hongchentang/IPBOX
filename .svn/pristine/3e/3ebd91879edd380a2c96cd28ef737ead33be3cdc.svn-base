/**
 * 
 */
package com.hcis.ipanther.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.hcis.ipanther.core.entity.RemainTime;

/**
 * @author Administrator
 *
 */
public class DateUtil {
	public static String getRemainTime(Date endTime){
		   java.util.Date now = new Date();
		   long l=now.getTime()-endTime.getTime();
		   long day=l/(24*60*60*1000);
		   long hour=(l/(60*60*1000)-day*24);
		   long min=((l/(60*1000))-day*24*60-hour*60);
		   return ""+day+"天"+hour+"小时";
	}
	
	public static RemainTime getRemainTime(Date startTime,Date endTime){
		   java.util.Date now = new Date();
		   //还未开始
		   if(startTime!=null&&startTime.getTime()-now.getTime()>0){
			   return new RemainTime("remainTime.notStarted");
		   }else{
			   long l=endTime.getTime()-now.getTime();
			   long day=l/(24*60*60*1000);
			   long hour=(l/(60*60*1000)-day*24);			   
			   if(day>0){
				   return new RemainTime(day,hour,-1,"remainTime.DDHH");
			   }
			   long min=((l/(60*1000))-day*24*60-hour*60);
			   if(hour>0){
				   return new RemainTime(-1,hour,min,"remainTime.HHMM");
			   }
			   if(min>0){
				   return new RemainTime(-1,-1,min,"remainTime.MM");
			   }
			   return new RemainTime(-1,-1,-1,"remainTime.hasEnded");
		   }
	}
	
}
