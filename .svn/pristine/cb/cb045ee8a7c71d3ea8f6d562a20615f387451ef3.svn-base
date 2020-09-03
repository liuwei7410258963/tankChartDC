package com.oket.station.bizservice;


import com.oket.delivery.BzDelivery;
import com.oket.device.Dispenser4Device;
import com.oket.device.Nozzle4Device;
import com.oket.device.TankInfo;
import com.oket.dispenser.BzNozzleOut;
import com.oket.dispenser.NozzleState;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.TankInventory;
import com.oket.tank4station.TankSession;

import java.util.*;

/**
 * 业务处理接口--液位和付油、卸油  进出存平衡
 */
public interface BalanceService {
	/**
	 * 监听器
	 *
	 * @param listener
	 */
	void addListener(ServiceListener listener);

	/**
	 * 删除监听器
	 *
	 * @param listener
	 */
	void removeListener(ServiceListener listener);

	/**
	 * 管理油罐  进出的数据session
	 *
	 * @return
	 */
	Map<Integer, TankSession> getManagedTankSessions();

	/**
	 * session个数
	 *
	 * @return
	 */
	int getManagedTankCount();

	/**
	 * 是否关闭中--待用
	 *
	 * @return
	 */
	boolean isDisposing();

	/**
	 * 是否已经关闭--待用
	 *
	 * @return
	 */
	boolean isDisposed();

	/**
	 * 保存液位长时间没有上传记录
	 *
	 * @param inventory
	 * @param lastUploadTime
	 */
	void saveInventoryLongTimeNoUpload(Inventory inventory, Date lastUploadTime);

	/**
	 * 结束长时间没有数据上传
	 * 当有新数据上传的时候
	 *
	 * @param inventory
	 */
	void endInventoryLongTimeNoUpload(Inventory inventory);

	/**
	 * @param inventory
	 */
	void firstProcessLongTimeNoUpload(Inventory inventory);

	/**
	 * 是否销毁
	 *
	 * @return
	 */
	boolean isActive();

	/**
	 * 激活时间戳--启动时间
	 *
	 * @return
	 */
	long getActivationTime();

	/**
	 * 相关的service的统计数据
	 *
	 * @return
	 */
	ServiceStatistics getStatistics();

	/**
	 * 收到的罐存信息
	 *
	 * @param message
	 */
	void receiveTankInventoryMessage(TankInventory message);

	/**
	 * 收到的付油数据
	 *
	 * @param nozzleOut
	 */
	void receiveNozzleOut(BzNozzleOut nozzleOut);

	/**
	 * 收到油枪状态
	 *
	 * @param nozzleState
	 */
	void nozzleStateChanged(NozzleState nozzleState);


	/**
	 * 油罐基本信息修变化
	 *
	 * @param tankInfo
	 */
	void tankPropertyChanged(TankInfo tankInfo);


	/**
	 * 油枪信息变化
	 *
	 * @param nozzle4Device
	 */
	void nozzlePropertyChanged(List<Nozzle4Device> nozzle4Device);

	/**
	 * 加油机信息变化
	 *
	 * @param nozzle4Device
	 */
	void dispenserPropertyChanged(Dispenser4Device nozzle4Device);

	/**
	 * 枪罐关系变化
	 *
	 * @param nozzle4Devices
	 */
	void nozzleRelTankChanged(List<Nozzle4Device> nozzle4Devices);

	/**
	 * 卸油报告数据
	 *
	 * @param deliveries
	 */
	void receiveDelivery(List<BzDelivery> deliveries);


	/**
	 * 获取所有的油枪状态
	 *
	 * @return
	 */
	Collection<NozzleState> getAllNozzleState();


	/**
	 * 添加观察者
	 * @param observer
	 */
	void  addObserver(Observer observer);
}
