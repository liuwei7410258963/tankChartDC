package com.oket.station;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;
/**
 * 地址类型num
 */
@Getter
@JSONType(serializeEnumAsJavaBean = true)
public enum AddressType {
	/**
	 * 围栏
	 */
	LOCATION("location"),
	/**
	 * GPS坐标
	 */
	GPS("gps"),
	/**
	 * 通信地址
     * +邮编
	 */
	ADDRESS("address"),
	/**
     * 网址
	 */
	URL("url");

	AddressType(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	//	@EnumValue
	private final String deviceTypeName;



	public String getDeviceTypeName() {
		return deviceTypeName;
	}
}
