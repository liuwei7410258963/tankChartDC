package com.oket.tank4station;

import com.oket.device.TankInfo;
import com.oket.dispenser.BzNozzleOut;
import com.oket.dispenser.NozzleState;
import com.oket.dispenser.NozzleTransientOut;
import com.oket.tank4station.entity.DbInventoryAlarm;
import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tankchartdc.DbInventoryCycle;

/**
 * TankSession,处理液位、周期、油枪状态的一个session
 */
public interface TankSession {
	/**
	 * 添加液位信息
	 */
	void addLevelInfo(LevelInfo level);

	/**
	 * 液位轨迹赋值
	 *
	 * @param levelTrace
	 */
	void setLevelTrace(LevelTrace levelTrace);

	/**
	 * 上一笔的液位轨迹赋值
	 *
	 * @param lastTrace
	 */
	void setLastTrace(LevelTrace lastTrace);

	/**
	 * 液位信息以字符串形式表示
	 */
	void addLevelInfo(String levelString);

	/**
	 * 添加库存信息
	 */
	void addInventory(TankInventory inventory) throws InventoryException;

	/**
	 * 添加液位信息，液位信息以字符形式作为参数
	 */
	void addInventory(String inventoryString);

	/**
	 * 返回当前液位信息
	 */
	LevelState getLevelState();

	/**
	 * 添加油枪付出数
	 */
	void addNozzleOut(BzNozzleOut bzNozzleOut);

	/**
	 * 更新加油枪状态
	 */
	void updateNozzleState(NozzleState nozzleState);

	/**
	 * 通知油枪实时加油数据
	 */
	boolean updateNozzleTransientOut(NozzleTransientOut nozzleTransientOut);


	/**
	 * 获取液位持久化处理器
	 *
	 * @return
	 */
	InventoryPersistenceProcessor getPersistenceProcessor();

	void setPersistenceProcessor(InventoryPersistenceProcessor persistenceProcessor);

	/**
	 * 获取油罐信息
	 *
	 * @return
	 */
	TankInfo getTankInfo();

	/**
	 * 设置油罐信息信息
	 *
	 * @param tankInfo
	 */
	void setTankInfo(TankInfo tankInfo);

	/**
	 * 设置c周期
	 *
	 * @param cycle
	 */
	void setCycle(DbInventoryCycle cycle);

	/**
	 * 获取周期
	 *
	 * @return
	 */
	DbInventoryCycle getCycle();

	/**
	 * 添加异常信息到持久化中
	 *
	 * @param alarm
	 */
	void addAlarmToNeedPersistence(DbInventoryAlarm alarm);

	/**
	 * 添加液位到持久化中
	 *
	 * @param inventory
	 * @param saveNow
	 */
	void addInventoryToNeedPersistence(TankInventory inventory, boolean saveNow);

	/**
	 * 添加轨迹到持久化中
	 *
	 * @param trace
	 */
	void addTraceToNeedPersistence(AbstractLevelTrace trace);

	/**
	 * 是否正在加油中
	 *
	 * @return
	 */
	boolean isOnFueling();

	/**
	 * 当枪罐关系发生变化的时候，清空对应某个枪的状态及付油信息
	 */
	void nozzleStateDelete(int nozzleNo);
}
