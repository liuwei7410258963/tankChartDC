package com.oket.util;

/**
 * @description: 方差计算工具类
 * @author: SunBiaoLong
 * @create: 2020-03-18 16:46
 **/


import com.google.common.base.Preconditions;

import java.math.BigDecimal;

/**
 * 方差计算工具类。
 *
 * @author frank
 */
public final class VarianceUtils {
	/**
	 * 默认精度
	 */
	private static final int DEFAULT_SCALE = 64;

	private VarianceUtils() {
	}

	public static BigDecimal variance(byte[] arr) {
		Preconditions.checkNotNull(arr);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr);
	}

	public static BigDecimal variance(byte[] arr, int scale) {
		Preconditions.checkNotNull(arr);
		Preconditions.checkArgument(scale > 0, "scale must be positive: " + scale);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr, scale);
	}

	public static BigDecimal variance(char[] arr) {
		Preconditions.checkNotNull(arr);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr);
	}

	public static BigDecimal variance(char[] arr, int scale) {
		Preconditions.checkNotNull(arr);
		Preconditions.checkArgument(scale > 0, "scale must be positive: " + scale);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr, scale);
	}

	public static BigDecimal variance(int[] arr) {
		Preconditions.checkNotNull(arr);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr);
	}

	public static BigDecimal variance(int[] arr, int scale) {
		Preconditions.checkNotNull(arr);
		Preconditions.checkArgument(scale > 0, "scale must be positive: " + scale);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr, scale);
	}

	public static BigDecimal variance(long[] arr) {
		Preconditions.checkNotNull(arr);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr);
	}

	public static BigDecimal variance(long[] arr, int scale) {
		Preconditions.checkNotNull(arr);
		Preconditions.checkArgument(scale > 0, "scale must be positive: " + scale);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr, scale);
	}

	public static BigDecimal variance(float[] arr) {
		Preconditions.checkNotNull(arr);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr);
	}

	public static BigDecimal variance(float[] arr, int scale) {
		Preconditions.checkNotNull(arr);
		Preconditions.checkArgument(scale > 0, "scale must be positive: " + scale);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr, scale);
	}

	public static BigDecimal variance(double[] arr) {
		Preconditions.checkNotNull(arr);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr);
	}

	public static BigDecimal variance(double[] arr, int scale) {
		Preconditions.checkNotNull(arr);
		Preconditions.checkArgument(scale > 0, "scale must be positive: " + scale);

		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		return variance(strArr, scale);
	}

	private static BigDecimal variance(String[] arr) {
		return variance(arr, DEFAULT_SCALE);
	}

	private static BigDecimal variance(String[] arr, int scale) {
		if (arr.length < 2) {
			return BigDecimal.ZERO;
		}

		BigDecimal sum = BigDecimal.ZERO;
		for (int i = 0; i < arr.length; i++) {
			sum = sum.add(new BigDecimal(arr[i]));
		}
		BigDecimal meanNum = sum.divide(new BigDecimal(arr.length), scale, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal tmp = null;
		BigDecimal tmpSum = BigDecimal.ZERO;
		for (int i = 0; i < arr.length; i++) {
			tmp = meanNum.subtract(new BigDecimal(arr[i]));
			tmpSum = tmpSum.add(tmp.multiply(tmp));
		}
		BigDecimal vari = tmpSum.divide(new BigDecimal(arr.length - 1), scale, BigDecimal.ROUND_HALF_DOWN);
		return new BigDecimal(trimZero(vari.toString()));
	}

	/**
	 * 去除小数中后面多余的0
	 *
	 * @param str
	 * @return
	 */
	private static String trimZero(String str) {
		if (!str.contains(".")) {
			return str;
		}

		StringBuilder ret = new StringBuilder();
		char[] chars = str.toCharArray();
		// stop trimming 0
		boolean stopTrim = false;
		for (int i = chars.length - 1; i >= 0; i--) {
			char ch = chars[i];
			if (stopTrim) {
				ret.append(ch);
				continue;
			}

			// not stop trimming 0
			if (ch != '0') {
				ret.append(ch);
				stopTrim = true;
			}
		}
		if (ret.charAt(0) == '.') {
			ret.deleteCharAt(0);
		}
		return ret.reverse().toString();
	}
}

