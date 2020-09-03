package com.oket.tank4station;

import com.oket.oil.OilEntity;
import com.oket.oil.cache.OilCacheManager;
import com.oket.tank4station.entity.DbInventory;
import com.oket.util.DoubleUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Inventory extends LevelInfo implements TankInventory, Serializable {

	private static final long serialVersionUID = -2206570198700412824L;
	/**
	 * 油净体积
	 */
	private double volume;
	/**
	 * 水体积
	 */
	private double waterVolume;
	/**
	 * 标准体积
	 */
	private double standardVolume;
	/**
	 * 标准密度
	 */
	private double standardDensity;
	/**
	 * 油品编码
	 */
	private String oilCode;
	/**
	 * 油品名称
	 */
	private String oilName;
	/**
	 * 数据类型，供前端筛选
	 */
	private String type;

	/**
	 * 最大罐存
	 */
	private double maxVolume;

	/**
	 * V20
	 */
	private double v20;
	/**
	 * 获取v20
	 *
	 * @return
	 */
	public double getV20() {
		return OilCacheManager.getV20(oilCode, volume, temperature);
	}

	public Inventory(int tankNo, Date time, double level
			, double waterLevel, double inventoryTemperature
			, double volume, double standardVolume) {
		super(tankNo, time, level, waterLevel, inventoryTemperature);
		this.volume = volume;
		this.standardVolume = standardVolume;
	}

	public Inventory() {
		super();
	}

	/**
	 * 数据库实体的inventory转为业务实体
	 *
	 * @param dbInventory
	 * @return
	 */
	public static Inventory convertFromInventory(DbInventory dbInventory) {
		Inventory inventory = new Inventory();
		inventory.setId(dbInventory.getId());
		inventory.setTime(dbInventory.getTime());
		inventory.setLevel(dbInventory.getLevel());
		inventory.setTemperature(dbInventory.getTemperature());
		inventory.setVolume(dbInventory.getVolume());
		inventory.setWaterLevel(dbInventory.getWaterLevel());
		inventory.setWaterVolume(dbInventory.getWaterVolume());
		inventory.setOilCode(dbInventory.getOilCode());
		inventory.setOilName(dbInventory.getOilName());
		inventory.setTankNo(dbInventory.getTankNo());
		return inventory;
	}
}
