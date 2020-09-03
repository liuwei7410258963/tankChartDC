package com.oket.tank4station.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.IParameter;
import com.oket.oil.OilEntity;
import com.oket.oil.cache.OilCacheManager;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.TankInventory;
import com.oket.util.DoubleUtils;
import com.oket.util.TimeUtils;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 罐存信息
 *
 * @author SunBiaoLong
 * @date 2019-12-07
 */
@Data
@TableName(value = "db_inventory")
@ToString
public class DbInventory implements IParameter {
	@TableId(type = IdType.INPUT)
	private Long id;
	/**
	 * 罐号
	 */
	private int tankNo;
	/**
	 * 油高
	 */
	private double level;
	/**
	 * 水高
	 */
	private double waterLevel;
	/**
	 * 温度
	 */
	private double temperature;
	/**
	 * 室温体积
	 */
	private double volume;
	/**
	 * 标准体积
	 */
	@TableField(exist = false)
	private double v20;
	/**
	 * 水体积
	 */
	private double waterVolume;
	/**
	 * 空容=maxVolume-waterVolume-volume
	 */
	@TableField(exist = false)
	private double emptySpace;
	/**
	 * 时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	/**
	 * 最大体积
	 */
	private double maxVolume;

	/**
	 * 油品code
	 */
	private String oilCode;
	/**
	 * 油品编码
	 */
	private String oilName;


	/**
	 * 获取v20
	 *
	 * @return
	 */
	public double getV20() {
		return OilCacheManager.getV20(oilCode, volume, temperature);
	}

	/**
	 * 获取空容
	 *
	 * @return
	 */
	public double getEmptySpace() {
		if (maxVolume > 0) {
			return DoubleUtils.round(maxVolume - volume - waterVolume, 3);
		} else {
			return 0.0;
		}
	}

	public static DbInventory convertFromInventory(TankInventory inventory) {
		DbInventory dbInventory = new DbInventory();
		dbInventory.setId(inventory.getId());
		dbInventory.setTime(inventory.getTime());
		dbInventory.setLevel(inventory.getLevel());
		dbInventory.setTemperature(inventory.getTemperature());
		dbInventory.setVolume(inventory.getVolume());
		dbInventory.setWaterLevel(inventory.getWaterLevel());
		dbInventory.setWaterVolume(inventory.getWaterVolume());
		dbInventory.setTankNo(inventory.getTankNo());
		dbInventory.setOilCode(inventory.getOilCode());
		dbInventory.setOilName(inventory.getOilName());
		dbInventory.setMaxVolume(inventory.getMaxVolume());
		return dbInventory;
	}

//	public static DbInventory convertFromInventory(TankInventory inventory) {
//		DbInventory dbInventory = new DbInventory();
//		dbInventory.setId(inventory.getId());
//		dbInventory.setTime(inventory.getTime());
//		dbInventory.setLevel(inventory.getLevel());
//		dbInventory.setTemperature(inventory.getTemperature());
//		dbInventory.setVolume(inventory.getVolume());
//		dbInventory.setWaterLevel(inventory.getWaterLevel());
//		dbInventory.setWaterVolume(inventory.getWaterVolume());
//		return dbInventory;
//	}

	@JSONField(serialize = false, deserialize = false)
	@Override
	public String getSubTableTime() {
		return TimeUtils.formatToMonthWithUnderline(time);
	}
}
