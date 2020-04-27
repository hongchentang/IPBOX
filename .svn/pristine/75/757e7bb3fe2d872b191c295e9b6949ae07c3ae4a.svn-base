package com.hcis.ipr.quartz.utils;
import java.text.DecimalFormat;

/**
 * @author zhw
 * @date 2019/9/23
 **/
public class QuartzCronDateUtils {
    /***
     *根据自己的需要编写转化方法即可  * * * * * ?：秒 分 时 日 月 星期（日和星期冲突，需把一个设为?）
     * @param  time  HH:mm:ss
     * @param  weekdays 星期（bit0:周日；bit1:周一；...；bit6：周六）
     * @return
     */
    public static String getCron(String time,int weekdays){
        String[] str = time.split(":");
        String second = str[2];
        String minute = str[1];
        String hour = str[0];
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(second).append(" ").append(minute).append(" ").append(hour).append(" ? * ");
        String week = Integer.toBinaryString(weekdays);
        DecimalFormat df = new DecimalFormat("0000000");
        week = df.format(Integer.parseInt(week));
        for (int i = 6; i >= 0; i--) {
            if (Integer.valueOf(week.substring(i, i+1)) == 1) {
                stringBuffer.append((7-i) + ",");
            }
        }
        String cron = stringBuffer.toString();
        return cron.substring(0, cron.length()-1);
    }
}