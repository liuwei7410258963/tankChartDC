package com.oket.device;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 设备类型enum
 */
@Getter
@JSONType(serializeEnumAsJavaBean = true)
public enum DeviceType {
	NOZZLE(1, "油枪"),
	DISPENSER(2, "加油机"),
	TANK(3, "油罐");


	DeviceType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 设备类型的值
	 */
	@EnumValue
	private final int value;
	/**
	 * 设备类型名称
	 */
	private final String desc;
}
