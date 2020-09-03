package com.oket.tank4station.service;

import com.oket.tank4station.*;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tankchartdc.DbInventoryCycle;

import java.util.Date;
import java.util.List;

/**
 * @description: 液位持久化处理器
 * @author: SunBiaoLong
 * @create: 2020-04-08 15:54
 **/
public interface InventoryPersistenceProcessor {
	/**
	 * @param needPersistence
	 */
	void persistence(AbstractTankSession.NeedPersistence needPersistence) throws InventoryException;

	/**
	 * 保存轨迹信息
	 *
	 * @param trace
	 */
	void saveOrUpdateTrace(DbInventoryTrace trace);

	/**
	 * 保存或更新轨迹信息
	 *
	 * @param traces
	 */
	void saveOrUpdateTraces(List<DbInventoryTrace> traces);


	/**
	 * 获取所有罐的最后一笔轨迹信息
	 * 慎用：：如果缺少数据会补充罐存数据
	 *
	 * @return
	 */
	List<DbInventoryTrace> getAllLastTrace();

	/**
	 * 指定油罐的的指定时间的上一条数据
	 *
	 * @param tankNo
	 * @param time
	 * @return
	 */
	DbInventoryTrace getLastTrace(int tankNo, Date time);

	/**
	 * 指定油罐的的指定时间的下一条数据
	 *
	 * @param tankNo
	 * @param time
	 * @return
	 */
	DbInventoryTrace getNextTrace(int tankNo, Date time);

	/**
	 * 获取轨迹信息
	 * //1.轨迹的开始时间小于传入的结束时间
	 * //2.轨迹的结束时间小于传入的开始时间
	 *
	 * @param tankNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<DbInventoryTrace> getTrace(int tankNo, Date startTime, Date endTime, boolean descByStartTime);


	List<DbInventory> getInventories(int tankNo, Date startTime, Date endTime);


	void processCycle(AbstractLevelTrace trace);

	/**
	 * 根据罐号获取周期
	 *
	 * @param tankNo
	 * @return
	 */
	DbInventoryCycle getCycle(int tankNo);

	/**
	 * 合并液位轨迹
	 *
	 * @param traceList
	 */
	void mergeTrace(List<DbInventoryTrace> traceList);

	/**
	 * 更新罐存last
	 * @param inventory
	 */
	void updateInventoryLast(TankInventory inventory);
}
