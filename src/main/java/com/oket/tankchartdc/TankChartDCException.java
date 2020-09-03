package com.oket.tankchartdc;

/**
 * @description: 业务处理异常
 * @author: SunBiaoLong
 * @create: 2020-04-14 13:20
 **/
public class TankChartDCException extends Exception {
	public TankChartDCException() {
	}

	public TankChartDCException(String message) {
		super(message);
	}

	public TankChartDCException(String message, Throwable cause) {
		super(message, cause);
	}

	public TankChartDCException(Throwable cause) {
		super(cause);
	}
}
