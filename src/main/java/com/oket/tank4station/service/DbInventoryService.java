package com.oket.tank4station.service;

import com.oket.common.base.BaseService;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.InventoryException;
import com.oket.tank4station.TankInventory;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryLast;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface DbInventoryService extends BaseService<DbInventory> {
	/**
	 * 是否启用批量保存
	 *
	 * @return
	 */
	String isEnableBatchInsert();

	/**
	 * 设置是否启用批量保存
	 *
	 * @param enableBatchInsert
	 */
	void setEnableBatchInsert(boolean enableBatchInsert);

	/**
	 * 更新罐存last
	 * @param inventory
	 */
	void updateInventoryLast(TankInventory inventory);

	/**
	 * 保存罐存数据
	 *
	 * @param inventories
	 * @param saveNow
	 * @throws
	 */
	void saveInventory(List<Inventory> inventories, int tankNo, boolean saveNow) throws InventoryException;

	/**
	 * 获取在一定范围内的液位数据
	 *
	 * @param tankNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<DbInventory> getInventoryInGap(int tankNo, Date startTime, Date endTime);

	/**
	 * 计算一个油罐的两个id之间的数量
	 *
	 * @param tankNo
	 * @param startId
	 * @param endId
	 * @return
	 */
	int count(int tankNo, Long startId, Long endId);

	/**
	 * 获取罐存信息根据油罐编号
	 *
	 * @param tankNo
	 * @return
	 */
	DbInventory getLastByTankNo(int tankNo);


	/**
	 * 获取在开始和结束id之间的罐存信息
	 *
	 * @param tankNo
	 * @param startId
	 * @param endId
	 * @return
	 */
	List<DbInventory> getInGaps(int tankNo, Long startId, Long endId);

	/**
	 * 获取最后的罐存信息
	 *
	 * @return
	 */
	Collection<DbInventoryLast> getAllDbInventoryLast();

	/**
	 * 获取之前的罐存
	 * @param tankNo
	 * @param time
	 * @return
	 */
	DbInventory getBeforeInventory(int tankNo,Date time);

	/**
	 * 获取之后的罐存
	 * @param tankNo
	 * @param time
	 * @return
	 */
	DbInventory getAfterInventory(int tankNo,Date time);

}
