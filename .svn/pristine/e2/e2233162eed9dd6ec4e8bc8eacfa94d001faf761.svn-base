package com.oket.station.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.station.StationEntity;

import java.util.List;

public interface StationService extends IService<StationEntity> {

	/**
	 * 油站信息查询
	 *
	 * @param jsonObject
	 * @return
	 */
	JSONObject selectStationAddress(JSONObject jsonObject);

	/**
	 * 油站信息更改
	 *
	 * @param list
	 * @return
	 */
	JSONObject updateStationAddress(List<JSONObject> list);


	/**
	 * 获取油站信息
	 *
	 * @return
	 */
	StationEntity getStation();

	/**
	 * 处理油站信息
	 *
	 * @param stationEntity
	 */
	void processStation(StationEntity stationEntity);
}
