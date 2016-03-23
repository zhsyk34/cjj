package com.baiyi.order.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class FormatUtil {

	public static Boolean intToBol(Integer key) {
		if (key == null) {
			return null;
		}
		return key > 0;
	}

	// 根据 Integer 获取枚举值
	public static <T> T getEnum(Class<T> clazz, Integer id) {
		T[] enums = clazz.getEnumConstants();

		if (id >= 0 && id < enums.length) {
			return enums[id];
		}
		return null;
	}

	// 根据 String 获取枚举值
	public static <T> T getEnum(Class<T> clazz, String name) {
		if (name == null) {
			return null;
		}
		T[] enums = clazz.getEnumConstants();
		for (T t : enums) {
			if (name.equalsIgnoreCase(t.toString())) {
				return t;
			}
		}
		return null;
	}

	public static double format(double d, int decimal) {
		return new BigDecimal(d).setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	// 将字母转换成数字 TODO
	public static void charToDigit(String input) {
		for (byte b : input.getBytes()) {
			System.out.print(b - 96);
		}
	}

	// 将数字转换成字母 TODO
	public static void digitToChar(String input) {
		for (byte b : input.getBytes()) {
			System.out.print((char) (b + 48));
		}
	}

	/* 日期转换 */
	public static String dateToString(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (StringUtils.isBlank(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static Date stringToDate(String str, String pattern) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (StringUtils.isBlank(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (ValidateUtil.isSimpleDate(str)) {
			str += " 00:00:00";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
