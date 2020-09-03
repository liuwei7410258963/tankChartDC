package com.oket.tankchartdc.mina;

import com.oket.util.TimeUtils;

import java.util.Date;

/**
 * @description:
 **/
public interface IConnector {


	/**
	 * 获取ip
	 * @return
	 */
	String getIp();


	/**
	 * 获取端口
	 * @return
	 */
	int getPort();

	/**
	 * 获取id
	 *
	 * @return
	 */
	String getId();

	/**
	 * 设置id
	 *
	 * @param id
	 */
	void setId(String id);

	/**
	 * 构建id
	 *
	 * @return
	 */
	default void buildId() {
		setId(TimeUtils.formatToSecondID(new Date()) + "_" + this.getClass().getSimpleName() + "_"
				+ Thread.currentThread().getName());
	}
}
