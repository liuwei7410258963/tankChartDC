package com.oket.common.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.lang.annotation.Annotation;

/**
 * @description: 状态
 * @author: SunBiaoLong
 * @create: 2019-12-09 15:16
 **/
@JSONType(serializeEnumAsJavaBean = true)
public enum Status {
	DISABLE(0, "禁用"),
	ENABLE(1, "启用");

	Status(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 状态值
	 */
	@EnumValue
	private final int value;
	/**
	 * 描述
	 */
	private final String desc;

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
