package com.muyou.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取格式化时间 Date->String
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate(Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取格式化时间 String->Date
	 * 
	 * @param 字符串格式
	 *            yyyy-MM-dd HH:mm:ss
	 * @return返回Date
	 */
	public static Date getDateByString(String currentTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTime = null;
		try {
			dateTime = simpleDateFormat.parse(currentTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * 
	 * @param currentTime
	 * @param format
	 * @return
	 */
	public static Date getDateByString(String currentTime,String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date dateTime = null;
		try {
			dateTime = simpleDateFormat.parse(currentTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * 自定义格式化时间 String->Date
	 * @param currentTime
	 * @param format
	 * @return
	 */
	public static String getStringDate(Date currentTime,String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(currentTime);
		return dateString;
	}
}
