package com.oket.tank4station;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * 实体类设计成，不可修改，可以在多线程中不需要担心数据同步问题，即不允许设计成有set方法
 * 计量单位为：level 、waterLevel—— mm
 * volume单位 升
 * 温度单位——摄氏度
 *
 * @author 王恒
 * @since 2019年11月26日
 */

@Data
public class LevelInfo implements TankLevel {
	protected int tankNo;
	/**
	 * 记录流水号
	 */
	protected Long id;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date time;
	/**
	 * 油高
	 */
	protected double level;
	/**
	 * 温度
	 */
	protected double temperature;
	/**
	 * 水高
	 */
	protected double waterLevel;

	public LevelInfo( int tankNo, Date time, double level, double waterLevel, double temperature) {
		this.tankNo = tankNo;
		this.time = time;
		this.level = level;
		this.waterLevel = waterLevel;
		this.temperature = temperature;
	}


	public LevelInfo() {

	}


	public boolean compareTo(Inventory inv) {
		boolean ret = false;
		//两者温度差在±0.3℃度范围内认为温度相等,液位高度在±0.3mm
		if (this.temperature < inv.getTemperature() + 0.3
				&& this.temperature > inv.getTemperature() - 0.3
				&& this.level >= inv.getLevel() - 0.3
				&& this.level <= inv.getLevel() + 0.3) {
			ret = true;
		}

		return ret;
	}


	/**
	 * 油罐内液位与加油枪之间的状态组合
	 */
	@Getter
	public enum TankState {
		/**
		 * 空闲
		 */
		IDLE(0, "空闲"),
		/**
		 * 正在卸油
		 */
		FILLING(1, "正在卸油"),
		/**
		 * 液位下降油枪加油中
		 */
		LEVEL_DECEBNDING_FUELLING(2, "液位下降油枪加油中"),
		/**
		 * 液位下降油枪空闲
		 */
		LEVEL_DECEBNDING_NOZLLE_IDLE(3, "液位下降油枪空闲"),
		/**
		 * 液位稳定，油枪加油中
		 */
		LEVEL_STALE_FUELLING(4, "液位稳定，油枪加油中");


		private final int id;
		private final String name;

		TankState(int id, String name) {
			this.id = id;
			this.name = name;
		}

	}
}
