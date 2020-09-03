package com.oket.delivery;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @description: 收油类型
 * @author: SunBiaoLong
 * @create: 2019-12-10 10:41
 **/
@Getter
@JSONType(serializeEnumAsJavaBean = true)
public enum DeliveryType {
	RECEIVE(1, "收货"),
	CLEAR_TANK(6, "清罐/报废Transfer to warehouse"),
	TRANSFER_TO_WAREHOUSE_IN_STATION(7, "站内调拨入库（倒罐）"),
	TRANSFER_OUT_IN_STATION(8, "站内调拨出库（倒罐）"),
	TRANSFER_TO_WAREHOUSE_BETWEEN_STATION(10, "站间调拨入库（移库）"),
	TRANSFER_OUT_BETWEEN_STATION(11, "站间调拨出库（移库）");

	DeliveryType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	public static DeliveryType getEnum(int value) {
		switch (value) {
			case 1:
				return DeliveryType.RECEIVE;
			case 6:
				return DeliveryType.CLEAR_TANK;
			case 7:
				return DeliveryType.TRANSFER_TO_WAREHOUSE_IN_STATION;
			case 8:
				return DeliveryType.TRANSFER_OUT_IN_STATION;
			case 10:
				return DeliveryType.TRANSFER_TO_WAREHOUSE_BETWEEN_STATION;
			case 11:
				return DeliveryType.TRANSFER_OUT_BETWEEN_STATION;
			default:
				return null;
		}
	}

	/**
	 * 类型值
	 */
	@EnumValue
	private final int value;
	/**
	 * 类型描述
	 */
	private final String desc;
}
