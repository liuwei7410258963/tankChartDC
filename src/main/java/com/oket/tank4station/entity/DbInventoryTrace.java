package com.oket.tank4station.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.Status;
import com.oket.tank4station.LevelState;
import com.oket.tank4station.TankInventory;
import com.oket.util.DoubleUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 罐存轨迹信息
 *
 * @author SunBiaoLong
 * @date 2019-12-07
 */
@Data
@TableName(value = "db_inventory_trace")
public class DbInventoryTrace {
	public final static int FINISHED = 1;
	public final static int NOT_FINISHED = 0;
	@TableId(type = IdType.AUTO)
	private Long id;
	private Integer tankNo;
	private String oilCode;
	private String oilName;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private Integer cycleId;
	/**
	 * 卸油周期id,从1开始递增
	 */
	private Integer deliveryId;
	/**
	 * 开始的id
	 */
	private Long startId;
	private Double startLevel;
	private Double startWaterLevel;
	private Double startVolume;
	private Double startTemperature;
	private Double startWaterVolume;
	/**
	 * 结束的id
	 */
	private Long endId;
	private Double endLevel;
	private Double endWaterLevel;
	private Double endVolume;
	private Double endTemperature;
	private Double endWaterVolume;
	/**
	 * 详情
	 */
	private String detailInfo;
	/**
	 * 中间的点数量
	 */
	private int points;
	/**
	 * 变化量
	 */
	private Double volume;
	/**
	 * 默认是未确定状态
	 */
	private LevelState levelState = LevelState.NEW;
	private Integer minutes;
	private Integer closed = NOT_FINISHED;
	private Status status = Status.ENABLE;
	/**
	 * 最后一笔罐存数据
	 */
	@TableField(exist = false)
	private DbInventory lastInventory;
	/**
	 * 最后一笔液位
	 */
	@TableField(exist = false)
	private DbInventory firstInventory;
	/**
	 * 分表处理
	 */
	@TableField(exist = false)
	private List<TankInventory> inventories;
	@TableField(exist = false)
	//用油罐温度修正付出数
	private Double deltaVolume;
	@TableField(exist = false)
	//用油枪温度修正付出数
	private Double deltaNozzleVolume;

	/**
	 * @param dbInventoryTrace
	 */
	public void endInventory(DbInventoryTrace dbInventoryTrace) {
		this.setEndTime(dbInventoryTrace.getEndTime());
		long minutes = this.getEndTime().getTime() - this.getStartTime().getTime();
		minutes = minutes / 60 / 1000;
		this.setMinutes((int) minutes);
		this.setEndLevel(dbInventoryTrace.getEndLevel());
		this.setEndTemperature(dbInventoryTrace.getEndTemperature());
		this.setEndVolume(dbInventoryTrace.getEndVolume());
		this.setEndWaterLevel(dbInventoryTrace.getEndWaterLevel());
		this.setEndWaterVolume(dbInventoryTrace.getEndWaterVolume());
		this.setVolume(this.getEndVolume() - this.getStartVolume());
		this.setEndId(dbInventoryTrace.getEndId());
		this.setClosed(1);
	}

	/**
	 * 为结束相关属性赋值
	 *
	 * @param inventory
	 */
	public void endInventory(DbInventory inventory) {
		this.setEndTime(inventory.getTime());
		long minutes = this.getEndTime().getTime() - this.getStartTime().getTime();
		minutes = minutes / 60 / 1000;
		this.setMinutes((int) minutes);
		this.setEndLevel(inventory.getLevel());
		this.setEndTemperature(inventory.getTemperature());
		this.setEndVolume(inventory.getVolume());
		this.setEndWaterLevel(inventory.getWaterLevel());
		this.setEndWaterVolume(inventory.getWaterVolume());
		this.setVolume(this.getEndVolume() - this.getStartVolume());
		this.setEndId(inventory.getId());
		this.setClosed(1);
	}

	/**
	 * 获取开始时的轨迹数据
	 *
	 * @param inventory
	 * @return
	 */
	public void startInventory(DbInventory inventory, LevelState levelState) {
		this.setOilCode(inventory.getOilCode());
		this.setOilName(inventory.getOilName());
		this.setTankNo(inventory.getTankNo());
		this.setStartTime(inventory.getTime());
		this.setStartLevel(inventory.getLevel());
		this.setStartVolume(inventory.getVolume());
		this.setStartTemperature(inventory.getTemperature());
		this.setStartWaterLevel(inventory.getWaterLevel());
		this.setStartWaterVolume(inventory.getWaterVolume());
		this.setStartId(inventory.getId());
		this.setLevelState(levelState);
	}

//	/**
//	 * 获取体积差
//	 * @return
//	 */
//	public Double getVolume(){
//		if (startVolume==null||endVolume==null){
//			return null;
//		}else {
//			return DoubleUtils.round(endVolume - startVolume, 3);
//		}
//	}
	/**
	 * 为开始属性赋值
	 *
	 * @param inventoryTrace
	 */
	public void startInventory(DbInventoryTrace inventoryTrace) {
		this.setOilCode(inventoryTrace.getOilCode());
		this.setOilName(inventoryTrace.getOilName());
		this.setTankNo(inventoryTrace.getTankNo());
		this.setStartTime(inventoryTrace.getStartTime());
		this.setStartLevel(inventoryTrace.getStartLevel());
		this.setStartVolume(inventoryTrace.getStartVolume());
		this.setStartTemperature(inventoryTrace.getStartTemperature());
		this.setStartWaterLevel(inventoryTrace.getStartWaterLevel());
		this.setStartWaterVolume(inventoryTrace.getStartWaterVolume());
		this.setStartId(inventoryTrace.getStartId());
	}

}
