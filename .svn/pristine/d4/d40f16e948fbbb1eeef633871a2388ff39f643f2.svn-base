/**
 *
 */
package com.oket.tank4station;

import java.io.Serializable;

/**
 * 油罐安装尺寸，液位仪安装位置到主圆桶最高边位置Lg，油罐轴向倾斜情况E,油罐水平倾斜情况E1
 * @author qdwan_000
 *
 */
public class TankFixedInfo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 2087781458257758099L;
	private double Lg = 0;//参照点至液位高端封头直边与（过渡）曲线相切点处的长(液位仪安装位置与主圆桶最高液面的边的距离)
	private boolean trueLg = false;//Lg是否已知；默认未知
	private double E = 0;//油罐主圆筒两端高度差
	private boolean trueE = false;//E是否已知；默认未知,如果设置外部初始化了E则默认为已知，不需要对此参数做调整
	private double Axis = 0;//油罐水平倾斜
	private boolean trueAxis = false;//E1是否已知；默认未知,如果设置外部初始化了E1则默认为已知，不需要对此参数做调整

	public TankFixedInfo(double lg, double e, double axis) {
		if (lg != 0) {
			Lg = lg;
			setTrueLg(true);
		}
		if (e > 0) {
			E = e;
			setTrueE(true);
		}
		if (axis > 0) {
			Axis = axis;
			setTrueAxis(true);
		}
	}

	public TankFixedInfo(double lg, double e) {
		if (lg != 0) {
			Lg = lg;
			setTrueLg(true);
		}
		if (e > 0) {
			E = e;
			setTrueE(true);
		}
	}

	/**
	 * 默认情况下，Lg为600mm，没有倾斜
	 */
	public TankFixedInfo() {
		Lg = 600;
		E = 0;
	}

	public double getLg() {
		return Lg;
	}

	public void setLg(double lg) {

		Lg = lg;
		setTrueLg(true);


	}

	public double getE() {
		return E;
	}

	public void setE(double e) {

		E = e;
		setTrueE(true);

	}
	@Override
	public String toString() {
		String ret = "";
		ret = String.format("E=%1$.1f,Lg=%2$.1f,axis=%3$.1f", E, Lg, this.Axis);
		return ret;
	}

	public boolean isTrueLg() {
		return trueLg;
	}

	public void setTrueLg(boolean trueLg) {
		this.trueLg = trueLg;
	}

	public boolean isTrueE() {
		return trueE;
	}

	public void setTrueE(boolean trueE) {
		this.trueE = trueE;
	}

	public double getAxis() {
		return Axis;
	}

	public void setAxis(double axis) {

		Axis = axis;
		setTrueAxis(true);

	}

	public boolean isTrueAxis() {
		return trueAxis;
	}

	public void setTrueAxis(boolean trueAxis) {
		this.trueAxis = trueAxis;
	}
}
