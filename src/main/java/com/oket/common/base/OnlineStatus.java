package com.oket.common.base;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @description: 在线状态
 * @author: SunBiaoLong
 * @create: 2019-12-11 09:54
 **/
@JSONType(serializeEnumAsJavaBean = true)
@Getter
public enum OnlineStatus {
	ONLINE(true, "在线"),
	OFFLINE(false, "离线");

	OnlineStatus(boolean value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 状态值
	 */
	@EnumValue
	private final boolean value;

	/**
	 * 状态描述
	 */
	private final String desc;

}
