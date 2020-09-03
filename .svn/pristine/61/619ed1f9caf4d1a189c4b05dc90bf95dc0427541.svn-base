package com.oket.oil;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @description: 油品类型
 * @author: SunBiaoLong
 * @create: 2019-12-10 09:15
 **/
@JSONType(serializeEnumAsJavaBean = true)
@Getter
public enum OilType {
	GASOLINE(1, "汽油","gas"),
	DIESEL(2, "柴油","diesel"),
	OTHER(3, "其他","other");

	OilType(int value, String desc,String englishName) {
		this.value = value;
		this.desc = desc;
		this.englishName = englishName;
	}

	/**
	 * 类型的值
	 */
	@EnumValue
	private final int value;
	/**
	 * 描述
	 */
	private final String desc;
	private final String englishName;
}
