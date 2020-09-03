package com.oket.tank4station;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 液位状态
 *
 * @author SunBiaoLong
 * @date 2020-04-03
 */
@Getter
public enum LevelState {
	/**
	 * 未被初始化
	 */
	NEW(0, "未初始化"),
	/**
	 * 油罐内油品液面稳定
	 */
	LEVEL_STABLE(2, "液位稳定"),
	/**
	 * 液面下降
	 */
	LEVEL_DESCENDING(3, "液位下降"),
	/**
	 * 液面上升
	 */
	LEVEL_ASCENDING(4, "液位上升"),
	/**
	 * 液面震荡
	 */
	LEVEL_WAVING(5, "液面震荡");
	/**
	 * 存储在数据库中的值
	 */
	@EnumValue
	private int index;
	private String name;

	LevelState(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public static LevelState getLevel(int index) {
		switch(index) {
			case 0:
				return LevelState.NEW;
			case 2:
				return LevelState.LEVEL_STABLE;
			case 3:
				return LevelState.LEVEL_DESCENDING;
			case 4:
				return LevelState.LEVEL_ASCENDING;
			case 5:
				return LevelState.LEVEL_WAVING;
		}
		return null;
	}

}
