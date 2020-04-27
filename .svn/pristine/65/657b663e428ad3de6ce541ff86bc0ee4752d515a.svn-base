package com.hcis.ipanther.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author lianghuahuang
 *
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	
	public static boolean inCurrentDate(Date startDate,Date endDate){
		if(startDate!=null&&endDate!=null){
			int currentDate = Integer.parseInt(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			int intStartDate = Integer.parseInt(DateFormatUtils.format(startDate, "yyyyMMdd"));
			if(intStartDate>currentDate){
				return false;
			}else{
				int intEndDate = Integer.parseInt(DateFormatUtils.format(endDate, "yyyyMMdd"));
				if(currentDate<=intEndDate){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean afterCurrentDate(Date endDate){
		if(endDate!=null){
			int currentDate = Integer.parseInt(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			int intEndDate = Integer.parseInt(DateFormatUtils.format(endDate, "yyyyMMdd"));
			if(currentDate<intEndDate){
				return true;
			}
		}
		return false;
	}
	
	public static boolean beforeCurrentDate(Date startDate){
		if(startDate!=null){
			int currentDate = Integer.parseInt(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			int intStartDate = Integer.parseInt(DateFormatUtils.format(startDate, "yyyyMMdd"));
			if(currentDate>intStartDate){
				return true;
			}
		}
		return false;
	}
	
	public static boolean inCurrentDate(String startDate,String endDate){
		if(StringUtils.isEmpty(startDate)){
			int currentDate = Integer.parseInt(DateFormatUtils.format(new Date(), "yyyyMMdd"));
			int intStartDate = Integer.parseInt(startDate);
			if(intStartDate>currentDate){
				return false;
			}else{
				if(endDate==null){
					return true;
				}else{
					int intEndDate = Integer.parseInt(endDate);
					if(currentDate<=intEndDate){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static  String getYearOptions(int pre,int next){
		StringBuffer sb = new StringBuffer();
		int nowYear = Integer.parseInt(DateFormatUtils.format(new java.util.Date(), "yyyy")); 
		for(int i=nowYear-pre;i<=nowYear+next;i++){
			sb.append("<option value='").append(i).append("' ");
			/*if(i==nowYear){
				sb.append(" selected ");
			}*/
			sb.append(">").append(i).append("</option>");
		}
		return sb.toString();
	}
	
	public static String getYearOptionsSelected(int pre,int next,short defaultYear){
		StringBuffer sb = new StringBuffer();
		int nowYear = Integer.parseInt(DateFormatUtils.format(new java.util.Date(), "yyyy")); 
		for(int i=nowYear-pre;i<=nowYear+next;i++){
			sb.append("<option value='").append(i).append("' ");
			if((defaultYear==i)||(defaultYear==0&&i==nowYear)){
				sb.append(" selected ");
			}
			sb.append(">").append(i).append("</option>");
		}
		return sb.toString();
	}
	
	public static boolean inCurrentDate(Date startDate,Date endDate,String pattern){
		if (StringUtils.isEmpty(pattern)||pattern.equals("yyyyMMdd")) {
			return inCurrentDate(startDate, endDate);
		}else {
			if(startDate!=null&&endDate!=null){
				long currentDate = Long.parseLong(DateFormatUtils.format(new Date(), pattern));
				long longStartDate = Long.parseLong(DateFormatUtils.format(startDate, pattern));
				if(longStartDate>currentDate){
					return false;
				}else{
					long longEndDate = Long.parseLong(DateFormatUtils.format(endDate, pattern));
					if(currentDate<=longEndDate){
							return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @return 返回格式化后的日期，如果date为空，返回空字符串
	 */
	public static String formatDate(Date date,String pattern) {
		String result = "";
		if(null!=date) {
			SimpleDateFormat formater = new SimpleDateFormat(pattern);
			result = formater.format(date);
		}
		return result;
	}
	
	/**
	 * 重载 DateUtils.formatDate
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 将日期字符串转换为日期
	 * @param dateStr
	 * @param pattern
	 * @return dateStr格式不正确返回空日期
	 */
	public static Date parseDate(String dateStr,String pattern) {
		Date date = null;
		if(StringUtils.isNotEmpty(dateStr)) {
			SimpleDateFormat formater = new SimpleDateFormat(pattern);
			try {
				date = formater.parse(dateStr);
			} catch (ParseException e) {
				
			}
		}
		return date;
	}
	
	/**
	 * 重载 DateUtils.parseDate
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr,"yyyy-MM-dd");
	}
	
}	
