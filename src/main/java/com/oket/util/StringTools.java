package com.oket.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * Sting 工具类
 *
 * @author Administrator
 */
public class StringTools {

	public static boolean isNullOrEmpty(String str) {
		return null == str || "".equals(str) || "null".equals(str);
	}

	public static boolean isNullOrEmpty(Object obj) {
		return null == obj || "".equals(obj);
	}

	/**
	 * 获取ip地址
	 *
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}

		return join(array, separator, 0, array.length);
	}

	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	/**
	 * 匹配是否包含数字
	 *
	 * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
	 * @return
	 * @author yutao
	 * @date 2016年11月14日下午7:41:22
	 */
	public static boolean isNumeric(String str) {
		// 该正则表达式可以匹配所有的数字 包括负数
		Pattern pattern = compile("-?[0-9]+\\.?[0-9]*");
		String bigStr;
		try {
			bigStr = new BigDecimal(str).toString();
		} catch (Exception e) {
			return false;//异常 说明包含非数字。
		}
		// matcher是全匹配
		Matcher isNum = pattern.matcher(bigStr);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static String join(Object[] array, String separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = "";
		}

		// endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
		//           (Assuming that all Strings are roughly equally long)
		int bufSize = (endIndex - startIndex);
		if (bufSize <= 0) {
			return "";
		}

		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length())
				+ separator.length());

		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	public static String join(Object[] array, char separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		int bufSize = (endIndex - startIndex);
		if (bufSize <= 0) {
			return "";
		}

		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 利用正则表达式截取出字符串中的整数或小数
	 *
	 * @param str
	 * @return
	 */
	public static String getNumber(String str) {
		// 控制正则表达式的匹配行为的参数(小数)
		Pattern p = compile("(\\d+\\.\\d+)");
		//Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
		Matcher m = p.matcher(str);
		//m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
		if (m.find()) {
			//如果有相匹配的,则判断是否为null操作
			//group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
			str = m.group(1) == null ? "" : m.group(1);
		} else {
			//如果匹配不到小数，就进行整数匹配
			p = compile("(\\d+)");
			m = p.matcher(str);
			if (m.find()) {
				//如果有整数相匹配
				str = m.group(1) == null ? "" : m.group(1);
			} else {
				//如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
				str = "";
			}
		}
		return str;
	}
}
