package com.oket.tankchartdc.bean;

import lombok.Data;

/**
 * @description:
 * @author: Longer
 * @create: 2019-11-22 11:22
 **/
@Data
public class TankRelHoseKey {
	private String hoseId;
	private String tankId;

	public TankRelHoseKey(String hoseId, String tankId) {
		this.hoseId = hoseId;
		this.tankId = tankId;
	}
}
