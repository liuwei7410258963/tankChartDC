package com.oket.util;


import java.math.BigDecimal;

/**
 * lw
 * Double精度计算
 */
public class DoubleUtils {

	/**
	 * 提供精确的加法运算。
	 *
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static Double add(Double v1, Double v2, Integer scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.add(b2).doubleValue(), scale);
	}

	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	public static BigDecimal add(BigDecimal v1, BigDecimal v2, Integer scale) {
		return round(v1.add(v2), scale);
	}


	/**
	 * 提供精确的减法运算。
	 *
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static Double sub(Double v1, Double v2, Integer scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.subtract(b2).doubleValue(), scale);
	}

	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	public static BigDecimal sub(BigDecimal v1, BigDecimal v2, Integer scale) {
		return round(v1.subtract(v2), scale);
	}

	/**
	 * 提供精确的乘法运算。
	 *
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static Double mul(Double v1, Double v2, Integer scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.multiply(b2).doubleValue(), scale);
	}

	public static BigDecimal mul(BigDecimal v1, BigDecimal v2, Integer scale) {
		return round(v1.multiply(v2), scale);
	}

	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}


	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 *
	 * @param v1    被除数
	 * @param v2    除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static Double div(Double v1, Double v2, Integer scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b2.doubleValue() == 0 ? 0.00 : b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static BigDecimal div(BigDecimal v1, BigDecimal v2, Integer scale) {
		return v2.doubleValue() == 0 ? v2 : v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static Double div(Double v1, Double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b2.doubleValue() == 0 ? 0.00 : b1.divide(b2, 6, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v     需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static Double round(Double v, Integer scale) {
		BigDecimal rs = BigDecimal.valueOf(v);
		return rs.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static BigDecimal round(BigDecimal v, Integer scale) {
		return v.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
}
