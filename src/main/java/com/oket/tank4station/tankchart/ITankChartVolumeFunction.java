package com.oket.tank4station.tankchart;

/**
 * 容积表操作接口
 *
 * @author sunBiaolong
 * @date 2020-04-27
 */
public interface ITankChartVolumeFunction {
	/**
	 * 根据指定高度获取体积
	 *
	 * @param height height(单位mm) double
	 * @return 体积值(升) double
	 */
	double getVolume(double height);

	/**
	 * 获取最小高度
	 *
	 * @return
	 */
	int getMinHeight();

	/**
	 * 设置最小高度
	 *
	 * @param h
	 */
	void setMinHeight(int h);

	/**
	 * 获取最大高度
	 *
	 * @return
	 */
	int getMaxHeight();

	/**
	 * 设置最大高度
	 *
	 * @param h
	 */
	void setMaxHeight(int h);

	/**
	 * 获取毫米变化率
	 * 如果endH-startH=0，返回0
	 *
	 * @param startH 开始高度
	 * @param endH   结束高度
	 * @return
	 */
	double getMMChangeRate(double startH, double endH);

}
