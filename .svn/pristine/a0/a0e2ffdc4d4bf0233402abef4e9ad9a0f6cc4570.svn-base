package com.oket.dispenser;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @description: 销售类型
 * @author: SunBiaoLong
 * @create: 2019-12-09 10:29
 **/
@Getter
@JSONType(serializeEnumAsJavaBean = true)
public enum NozzleOutType {
	/**
	 * 正常付油信息
	 */
	NORMAL("正常", 0),
	/**
	 * 脱机数据
	 */
	OFFLINE("脱机", 1),
	/**
	 * 系统追加,当出现了付油信息没有上传到的时候，系统自己追加的付油信息
	 */
	SYSTEM_ADDITION("系统追加",2)
	;

	NozzleOutType(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}

	/**
	 * 类型
	 */
	private final String desc;
	/**
	 * 描述
	 */
	@EnumValue
	private final int value;

}
