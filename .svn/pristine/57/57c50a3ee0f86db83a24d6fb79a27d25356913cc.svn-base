package com.oket.tankchartdc.mina.json.bean;

import com.oket.station.StationEntity;
import lombok.Data;

/**
 * @description: 油站信息
 * @author: SunBiaoLong
 * @create: 2019-12-05 13:51
 **/
@Data
public class Station {
	/**
	 * 油站code
	 */
	private String code;
	/**
	 * id
	 */
	private Long id;

	/**
	 * 保存油站信息
	 *
	 * @param station
	 * @return
	 */
	public static StationEntity convertToStationEntity(Station station) {
		if (station != null) {
			StationEntity stationEntity = new StationEntity();
			stationEntity.setStationCode(station.code);
			return stationEntity;
		} else {
			return null;
		}
	}
}
