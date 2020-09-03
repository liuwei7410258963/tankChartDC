package com.oket.device.service;

import com.oket.device.Dispenser4Device;
import com.oket.device.Nozzle4Device;
import com.oket.device.TankInfo;

import java.util.Collection;
import java.util.List;

/**
 * @description: 设备接口
 * @author: SunBiaoLong
 * @create: 2019-12-10 16:54
 **/
public interface DeviceService {

	/**
	 * 处理油罐基本信息
	 *
	 * @param tankInfoList
	 */
	void processTanks(List<TankInfo> tankInfoList);

	/**
	 * 处理加油机信息
	 *
	 * @param dispenser4DeviceList
	 */
	void processDispenser(List<Dispenser4Device> dispenser4DeviceList);

	/**
	 * 处理油枪基本信息信息
	 *
	 * @param nozzle4DeviceList
	 */
	void processNozzleBaseData(List<Nozzle4Device> nozzle4DeviceList);

	/**
	 * 处理枪罐关系
	 *
	 * @param nozzle4DeviceList
	 */
	void processNozzleRelTank(List<Nozzle4Device> nozzle4DeviceList);

	/**
	 * 处理油枪的ifsf相关属性
	 * 节点地址，所属加油点
	 *
	 * @param nozzle4Device
	 */
	void processNozzleIfsfData(Nozzle4Device nozzle4Device);

	/**
	 * 获取油罐编号
	 *
	 * @param nozzleNo
	 * @return
	 */
	Integer getTankNo(Integer nozzleNo);

	/**
	 * 获取油枪编号
	 *
	 * @param node         节点
	 * @param fuelingPoint 加油点
	 * @return
	 */
	Integer getNozzle(byte node, int fuelingPoint);

}
