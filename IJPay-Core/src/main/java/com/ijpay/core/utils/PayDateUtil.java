package com.ijpay.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 时间工具类
 * <p>
 * 依赖 xk-time
 * @author YunGouOS
 */
public class PayDateUtil {

	public static final String yyyyMMdd = "yyyyMMdd";

	/**  日期格式yyyy-MM-dd  */
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	/** 日期时间格式yyyy-MM-dd HH:mm:ss */
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 到分 */
	public static final String DATA_TIME_MIN = "yyyy-MM-dd HH:mm";
	/** 全 **/
	public static final String ALL_PATTERN = "yyyyMMddHHmmssSSS";
	public static final String ALL_NON_PATTERN = "yyyyMMddHHmmss";
	/** MDHS */
	public static final String MDHS = "MM-dd HH:mm:ss";

	public static final String YYYY_MM_DD_T_HH_MM_SS_XXX_FMT = "yyyy-MM-dd'T'HH:mm:ssxxx";

	public static String formatLocalDateTime (String pattern){
		return formatLocalDateTime(null, pattern);
	}

	public static String formatLocalDateTime (LocalDateTime ldt, String pattern){
		if (ldt == null) {
			ldt = LocalDateTime.now();
		}
		if (StringUtils.isBlank(pattern)) {
			pattern = DATE_TIME_PATTERN;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return ldt.format(formatter);
	}

	public static String addDate (long days, String pattern){
		LocalDateTime ldt = LocalDateTime.now();
		ldt = ldt.plusDays(days);
		return formatLocalDateTime(ldt, pattern);
	}

	/**
	 * 时间转 TimeZone
	 * <p>
	 * 2020-08-17T16:46:37+08:00
	 *
	 * @param seconds 时间戳
	 * @return {@link String}  TimeZone 格式时间字符串
	 * @throws Exception 异常信息
	 */
	public static String addSecondsXxx (long seconds){
		LocalDateTime ldt = LocalDateTime.now();
		ldt = ldt.plusSeconds(seconds);
		return formatLocalDateTime(ldt, YYYY_MM_DD_T_HH_MM_SS_XXX_FMT);
	}
}
