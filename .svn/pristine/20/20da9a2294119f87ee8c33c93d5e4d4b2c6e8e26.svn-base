package com.oket.delivery;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @description: 损耗告警
 * @author: SunBiaoLong
 * @create: 2019-12-10 11:51
 **/
@Getter
@JSONType(serializeEnumAsJavaBean = true)
public enum LossAlarm {
	/**
	 * 正常
	 */
	NORMAL(0, "正常"),
	/**
	 * 告警
	 */
	ALARM(1, "正常");

	LossAlarm(int value, String desc) {
		this.value = value;
		this.desc = desc;
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

	/**
	 * 获取损益告警
	 *
	 * @param value
	 * @return
	 */
	public static LossAlarm getLossAlarm(int value) {
		for (LossAlarm lossAlarm : LossAlarm.values()) {
			if (lossAlarm.getValue() == value) {
				return lossAlarm;
			}
		}
//		默认正常
		return NORMAL;
	}
}
