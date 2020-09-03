/**
 *
 */
package com.oket.tank4station;

import java.io.Serializable;


/**
 * 描述油罐的基本信息，包括罐高、封头形状、封头大小
 * @author 王恒
 *
 */
public class TankBasicInfo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <p>{@link #tankId} 油罐ID</p>
	 * <p>{@link #ssr} 残差平方和</p>
	 * <p>{@link #D1} 油罐主圆筒平均直径，单位：mm</p>
	 * <p>{@link #D2} 油罐封头直边内直径，单位：mm</p>
	 * <p>{@link #L1} 油罐主圆筒内长，单位：mm</p>
	 * <p>{@link #L2} 油罐两端封头直边内长的平均值，单位：mm</p>
	 * <p>{@link #L3} 油罐两端封头内长的平均值，单位：mm</p>
	 * <p>{@link #Lg} 参照点至液位高端封头直边与（过渡）曲线相切点处的长，单位：mm</p>
	 * <p>{@link #E} 油罐主圆筒两端高度差，单位：mm</p>
	 * <p>{@link #headType} 油罐两端封头类型</p>
	 * */
	private String tankName;
	private long tankId;
	/**
	 * 主圆筒平均直径，单位mm
	 */
	private double D1;
	/**
	 * D1主圆桶是否已知；默认主圆桶直径即为罐高，故默认已知；
	 */
	private boolean trueD1 = true;
	/**
	 * 封头直边内直径，默认与D1相同
	 */
	private double D2;
	/**
	 * 封头直边直径D2是否已知；默认与D1相同且已知
	 */
	private boolean trueD2 = true;
	/**
	 * 主圆筒内长
	 */
	private double L1;
	/**
	 * 主圆桶长度是否已知；默认未知
	 */
	private boolean trueL1 = false;
	/**
	 * 两端封头直边内长的平均值
	 */
	private double L2;
	/**
	 * 封头直边圆桶长度已知；默认已知
	 */
	private boolean trueL2 = true;
	/**
	 * 两端封头内长的平均值
	 */
	private double L3;
	/**
	 * 封头长度是否已知；默认未知
	 */
	private boolean trueL3 = false;
	/**
	 * 封头类型
	 */
	private int headType;
	/**
	 * 封头类型是否已知；默认未知
	 */
	private boolean trueType = false;
	/**
	 * 指定油罐高度后，如果未指定油罐的其他尺寸，则默认生成油罐的最小基本尺寸
	 * @param d1 油罐高度
	 */
	public TankBasicInfo(double d1) {
		D1 = d1;
		D2 = d1;
		//默认
		L1 = TankConstant.TANK_L1_MIN_LENGTH;
		L2 = 0;
		//默认L3=4000mm
		L3 = TankConstant.TANK_L3_MIN_LENGTH;
	}

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public double getD1() {
		return D1;
	}

	public void setD1(double d1) {
		D1 = d1;
		D2 = D1;
	}

	public double getD2() {
		//D2未赋值，在采用D1
		if (D2 == 0) {
			return D1;
		}
		return D2;
	}

	public void setD2(double d2) {
		D2 = d2;
	}

	public double getL1() {
		return L1;
	}

	public void setL1(double l1) {
		L1 = l1;
	}

	public double getL2() {
		return L2;
	}

	public void setL2(double l2) {
		L2 = l2;
	}

	public double getL3() {
		return L3;
	}

	public void setL3(double l3) {
		L3 = l3;
	}

	public int getHeadType() {
		return headType;
	}

	public void setHeadType(int type) {
		this.headType = type;
	}

	public boolean isTrueD1() {
		return trueD1;
	}

	public void setTrueD1(boolean trueD1) {
		this.trueD1 = trueD1;
	}

	public boolean isTrueD2() {
		return trueD2;
	}

	public void setTrueD2(boolean trueD2) {
		this.trueD2 = trueD2;
	}

	public boolean isTrueL1() {
		return trueL1;
	}

	public void setTrueL1(boolean trueL1) {
		this.trueL1 = trueL1;
	}

	public boolean isTrueL2() {
		return trueL2;
	}

	public void setTrueL2(boolean trueL2) {
		this.trueL2 = trueL2;
	}

	public boolean isTrueL3() {
		return trueL3;
	}

	public void setTrueL3(boolean trueL3) {
		this.trueL3 = trueL3;
	}

	public boolean isTrueType() {
		return trueType;
	}

	public void setTrueType(boolean trueType) {
		this.trueType = trueType;
	}

	public long getTankId() {
		return tankId;
	}

	public void setTankId(long tankId) {
		this.tankId = tankId;
	}

	@Override
	public String toString() {
		String ret = "";
		ret = String.format("D1=%1$.1f,D2=%2$.1f,L1=%3$.1f,L2=%4$.1f,L3=%5$.1f,headType=%6$s", D1, D2, L1, L2, L3, getTypeName());
		return ret;
	}

	public String getTypeName() {
		if (headType == TankConstant.TANK_HEAD_TYPE_BALLMISSING) {
			return TankConstant.TANK_HEAD_TYPE_BALLMISSING_NAME;
		} else if (headType == TankConstant.TANK_HEAD_TYPE_OVAL) {
			return TankConstant.TANK_HEAD_TYPE_OVAL_NAME;
		} else if (headType == TankConstant.TANK_HEAD_TYPE_PLANE) {
			return TankConstant.TANK_HEAD_TYPE_PLANE_NAME;
		}
		if (headType == TankConstant.TANK_HEAD_TYPE_UNKNOWN) {
			return TankConstant.TANK_HEAD_TYPE_UNKNOWN_NAME;
		}

		return "";
	}

	public void setTypeName(String name) {
		if (name.equals(TankConstant.TANK_HEAD_TYPE_BALLMISSING_NAME)) {
			headType = TankConstant.TANK_HEAD_TYPE_BALLMISSING;
		} else if (name.equals(TankConstant.TANK_HEAD_TYPE_PLANE_NAME)) {
			headType = TankConstant.TANK_HEAD_TYPE_PLANE;
		} else if (name.equals(TankConstant.TANK_HEAD_TYPE_OVAL_NAME)) {
			headType = TankConstant.TANK_HEAD_TYPE_OVAL;
		} else if (name.equals(TankConstant.TANK_HEAD_TYPE_UNKNOWN_NAME)) {
			headType = TankConstant.TANK_HEAD_TYPE_UNKNOWN;
		} else {
			headType = -1;
		}
	}
}
