package com.oket.device.dao;

import com.oket.common.base.BaseDao;
import com.oket.device.Dispenser4Device;
import com.oket.device.Nozzle4Device;
import com.oket.dispenser.Nozzle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Dispenser4DeviceDAO extends BaseDao<Dispenser4Device> {

	@Insert({"insert into dev_nozzle(hose_id,hose_status,host_status_time,dispenser_id,dispenser_brand)"
			+ "values(#{nozzleId},#{hoseStatus},#{hoseStatusTime},#{dispenserID},#{dispenserBrand})"})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void saveNozzle(Nozzle nozzle);

	/**
	 * 批量增加加油机
	 * @param dispenser4Devices
	 */
	boolean batchInsert(List<Dispenser4Device> dispenser4Devices);

	/**
	 * 修改加油机
	 * @param dispenser4Devices
	 */
	boolean update(List<Dispenser4Device> dispenser4Devices);
	/**
	 * 获取加油机数据
	 *
	 * @return
	 */
	List<Dispenser4Device> getByStationBizId();


	/**
	 * 增加加油机
	 *
	 * @param dispenser4Device
	 */
	void addDispenser(Dispenser4Device dispenser4Device);


	/**
	 * 增加油枪
	 *
	 * @param nozzle4Device
	 */
	void addNozzle(Nozzle4Device nozzle4Device);
}
