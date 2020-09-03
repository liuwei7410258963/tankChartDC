package com.oket.tank4station;

import com.oket.common.base.Status;
import com.oket.dispenser.NozzleState;
import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tank4station.trace.AbstractTraceProcessor;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 记录每个液位状态的内部液位详细情况
 */
public interface LevelTrace {
	boolean addInventory(Inventory inventory) throws InventoryException;

	boolean addLevelInfos(Collection<Inventory> levels) throws InventoryException;

	/**
	 * 获取当前液位记录条数
	 */
	int getLevelCount();

	/**
	 * 获取levelingfo的时间跨度，即从最开始一条到最后一条的时间价格
	 */
	long getLevelIntervalTime(TimeUnit unit);


	/**
	 * 返回液位轨迹处在的状态
	 *
	 * @return
	 */
	LevelState getLevelState();

	/**
	 * 设置此液位轨迹的状态：LevelState.LEVELSTABLE——液位稳定;
	 * LevelState.LEVELSTABLE.LEVELASCENDING_——液位上升、
	 * LevelState.LEVELDECEBNDING——液位下降
	 * LevelState.NOLEVELUPDATE——液位无更新
	 */
	void setLevelState(LevelState state);


	TankLevel getLastLevel();

	TankLevel getFirstLevel();


	/**
	 * 是否已经结束
	 *
	 * @return
	 */
	boolean isClosed();


	/**
	 * 生成新的液位轨迹
	 *
	 * @param levelState
	 * @return
	 * @throws InventoryException
	 */
	LevelTrace newTrace(LevelState levelState) throws InventoryException;

	void setStatus(Status status);

	/**
	 * 更新油枪状态
	 *
	 * @param nozzleState
	 */
	void updateNozzleState(NozzleState nozzleState);

	/**
	 * 是否要转为其他类型数据
	 *
	 * @return
	 */
	boolean isConvertToOtherTrace();

	/**
	 * 创建构造器
	 *
	 * @param tankSession
	 * @return
	 */
	void createTraceProcessor(TankSession tankSession);

	/**
	 * 获取液位轨迹
	 *
	 * @return
	 */
	AbstractTraceProcessor getTraceProcessor();

	/**
	 * 是否在卸油
	 *
	 * @return
	 */
	boolean isOnUnloading();

	/**
	 * 设置周期id.从单罐从1开始自增
	 *
	 * @param deliveryId
	 */
	void setDeliveryId(int deliveryId);

	/**
	 * 设置周期id.DbInventoryCycle的主键id..所有罐的id数据库自增
	 *
	 * @param cycleId
	 */
	void setCycleId(int cycleId);

	/**
	 * 设置是否关闭
	 *
	 * @param closed
	 */
	void setClosed(boolean closed);

	/**
	 * 获取付油总量
	 *
	 * @return
	 */
	double getSumNozzleOut();

	/**
	 * 获取油罐编号
	 * @return
	 */
	int getTankNo();
}