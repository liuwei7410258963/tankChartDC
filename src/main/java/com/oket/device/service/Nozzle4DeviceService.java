package com.oket.device.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseService;
import com.oket.device.Nozzle4Device;

import java.util.List;

/**
 * @description: 加油枪设备service
 * @author: SunBiaoLong
 * @create: 2019-12-09 17:18
 **/
public interface Nozzle4DeviceService extends BaseService<Nozzle4Device> {
	/**
	 * 查询加油机是否存在
	 *
	 * @param nozzle
	 * @return
	 */
	JSONObject queryExistNozzle(Nozzle4Device nozzle);

	/**
	 * 保存枪罐关系
	 *
	 * @return
	 */
	void saveNoz( Nozzle4Device nozzle4Device);

	/**
	 * 更新枪罐关系
	 *
	 * @return
	 */
	void updateNozRel(Nozzle4Device nozzle4Device);

	/**
	 * 获取所有的油枪信息
	 * @return
	 */
	 List<Nozzle4Device> getAllNozzle();
}
