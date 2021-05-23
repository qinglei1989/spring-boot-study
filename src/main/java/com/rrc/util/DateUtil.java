/**
 * <h3>Class description</h3>
 * <h4>日期处理类</h4>
 *
 * <h4>Special Notes</h4>
 * 
 *
 * @ver 0.1
 * @author terry.wei
* 2011-5-4
 */
package com.rrc.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	

	/**
	 * 一天中的天数
	 */
	public static long millionSecondsOfDay = 86400000;
	
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static final String YYYYMMDD = "yyyy-MM-dd";
	
	private DateUtil() {
	}

    /**
     * 获取当前时间的标准字符串形式
     *
     * @return
     */
    public static String getStandardTime(){

        return getFormatDate(null);
    }

    /**
     * 获取当前日期的字符串形式
     *
     * @return
     */
    public static String getStandardDate(){

        return getFormatDate(YYYYMMDD);
    }

    /**
     * 获取当前时间的格式化字符串形式
     * @param formater
     * @return
     */
    public static String getFormatDate(String formater){
        if (StringUtils.isBlank(formater)) {
            formater = YYYYMMDDHHMMSS;
        }

        return getFormatDate(new Date(), null);
    }

	/**
	 * 根据指定日期的格式化字符串形式
	 *
	 * @param date
	 * @param formater
	 * @return
	 */
	public static String getFormatDate(Date date, String formater){
		if (StringUtils.isBlank(formater)) {
            formater = YYYYMMDDHHMMSS;
        }

		return new SimpleDateFormat(formater).format(date);
	}

	public static void main(String[] args) {
		String ss = "1+=-_111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111122222222222222";
		System.out.println(ss.length());
	}
}

