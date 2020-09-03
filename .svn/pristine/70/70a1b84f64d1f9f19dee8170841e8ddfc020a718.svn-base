package com.oket.tank4station;

import java.util.Date;

public interface TankLevel {
	Long getId();

	void setId(Long id);

	/**
	 * 冗余设计
	 * 站内标识符，类似于1号罐、2号罐
	 *
	 * @return
	 */
	int getTankNo();

	void setTankNo(int id);

	/**
	 * 返回液位值
	 *
	 * @return
	 */
	double getLevel();

	void setLevel(double level);


	/**
	 * 返回对应的温度
	 *
	 * @return
	 */
	double getTemperature();

	void setTemperature(double temp);


	/**
	 * 返回水位高度
	 *
	 * @return
	 */
	double getWaterLevel();

	void setWaterLevel(double waterLevel);

	/**
	 * 也为对应时间
	 *
	 * @return
	 */
	Date getTime();

	void setTime(Date time);
}
