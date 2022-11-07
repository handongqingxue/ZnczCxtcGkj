package com.znczCxtcGkj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期格式组件
 * @author lhb
 *
 */
public class DateUtil {
	static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public DateUtil() {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public DateUtil(String pattern) {
		sdf = new SimpleDateFormat(pattern);
	}
	
	public String format(Date date) {
		String format = sdf.format(date);
		return format;
	}
	public Date parse(String source) throws ParseException {
		Date date = sdf.parse(source);
		return date;
	}
	
	/**
	 * 返回1小时的time
	 * @param hour  几小时 
	 * @return  
	 */
	public static Long getTime(int hour) {
		return (long) (hour * 60 * 60 * 1000);
	}
	
	public static void main(String[] args) {
		getDateStr(new Date());
	}
	
	public static String getDateStr(Date date) {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		String time = sf.format(date);
		
		
		String dateToWeek = dateToWeek(date);
		
		SimpleDateFormat sfd = new SimpleDateFormat("HH\\:mm\\:ss");
		String time2 = sfd.format(date);
		
		
		String timeStr = time + " " + dateToWeek + "  " +time2;
		return timeStr;
	}
	
	
	/**
	 * 获取星期几
	 * @param date
	 * @return
	 */
	public static String dateToWeek(Date date) {

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
}