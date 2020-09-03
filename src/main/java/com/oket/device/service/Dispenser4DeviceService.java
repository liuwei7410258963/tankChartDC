package com.oket.device.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.common.base.BaseService;
import com.oket.device.Dispenser4Device;
import com.oket.device.Nozzle4Device;

import java.util.List;

/**
 * @description: 加油机service
 * @author: SunBiaoLong
 * @create: 2019-11-25 21:06
 **/
public interface Dispenser4DeviceService extends IService<Dispenser4Device> {

	/**
	 * 保存或更新加油机
	 *
	 * @param dispenser4Devices
	 */
	void saveOrUpdateDispenser(List<Dispenser4Device> dispenser4Devices);

	/**
	 * @return
	 */
	List<Dispenser4Device> getByStationBizId();

	void processDispenser(Dispenser4Device dispenser4Device);


	void processNozzle(Nozzle4Device nozzle4Device);

	/**
	 * 查询是否存在加油机
	 * @param dispenser4Device
	 * @return
	 */
	JSONObject queryExistDispenser(Dispenser4Device dispenser4Device);

	/**
	 * 查询是否存在加油机
	 * @return
	 */
	List<Dispenser4Device> query(JSONObject jsonObject);
}
