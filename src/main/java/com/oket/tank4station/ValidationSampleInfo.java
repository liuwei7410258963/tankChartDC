/**
 *
 */
package com.oket.tank4station;

import java.io.Serializable;

/**
 * 验证样本属性
 * @author 王恒
 *@since 2016-03-22
 */
public class ValidationSampleInfo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 7456928244789515516L;
	public final static String GASOlINE = "GASOLINE";
	public final static String DIESEL = "DIESEL";
	/**
	 * 样本类型，即是汽油还是柴油
	 */
	private String sampleType = "";
	/**
	 * 验证样本误差
	 */
	private double sampleErr = 0;
	/**
	 *
	 * 液位读数偏移量或者液位仪修正值
	 */
	private double levelOffset = 0;

	public double getSampleErr() {
		return sampleErr;
	}

	public void setSampleErr(double sampleErr) {
		this.sampleErr = sampleErr;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleTaype) {
		this.sampleType = sampleTaype;
	}

	public double getExpansionCoefficient() {
		double ret = 0;
		if (sampleType.equalsIgnoreCase(GASOlINE)) {
			ret = 0.0012;
		} else if (sampleType.equalsIgnoreCase(DIESEL)) {
			ret = 0.0008;
		}
		return ret;
	}

	public static double getExpansionCoefficient(String type) {
		double ret = 0;
		if (type.equalsIgnoreCase(GASOlINE)) {
			ret = 0.0012;
		} else if (type.equalsIgnoreCase(DIESEL)) {
			ret = 0.0008;
		}
		return ret;
	}

	public double getLevelOffset() {
		return levelOffset;
	}

	public void setLevelOffset(double levelOffset) {
		this.levelOffset = levelOffset;
	}

	public void setSampleTypeByName(String oilName) {
		if ("汽油".equals(oilName)) {
			sampleType = GASOlINE;
		} else if ("柴油".equals(oilName)) {
			sampleType = DIESEL;
		}
	}

	public String getOilName() {
		String name = "";
		if (sampleType.equalsIgnoreCase(GASOlINE)) {
			name = "汽油";
		} else if (sampleType.equalsIgnoreCase(DIESEL)) {
			name = "柴油";
		}
		return name;
	}
	@Override
	public String toString() {
		String ret = "";
		double err = sampleErr;
		ret = String.format("oilType=%1$s,flowMeterError=%2$.1f ‰n", getOilName(), err * 1000);
		return ret;
	}
}
