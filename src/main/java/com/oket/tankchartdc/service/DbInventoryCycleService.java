package com.oket.tankchartdc.service;

import com.oket.common.base.BaseService;
import com.oket.tank4station.AbstractLevelTrace;
import com.oket.tankchartdc.DbInventoryCycle;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2020-03-17 14:16
 **/

public interface DbInventoryCycleService extends BaseService<DbInventoryCycle> {

	/**
	 * 获取上个周期
	 *
	 * @param tankNo
	 * @return
	 */
	DbInventoryCycle getCycleFromDb(int tankNo);

	/**
	 * 处理周期
	 *
	 * @param trace
	 */
	void processCycle(AbstractLevelTrace trace);

	/**
	 * 根据罐号获取周期
	 *
	 * @param tankNo
	 * @return
	 */
	DbInventoryCycle getCycle(int tankNo);

	/**
	 * 获取周期
	 * @param tankNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<DbInventoryCycle> getCycle(int tankNo, Date startTime, Date endTime);
}
