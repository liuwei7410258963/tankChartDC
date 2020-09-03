package com.oket.tank4station.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import com.oket.common.base.Status;
import com.oket.tank4station.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:51
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "db_inventory_alarm")
public class DbInventoryAlarm extends BaseEntity {
	/**
	 * 超时时间--秒
	 */
	public static final int TIMEOUT_THRESHOLD = 60;
	/**
	 * 异常类型--超时上传
	 */
	public static final int TIMEOUT = 1;
	/**
	 * 不连续
	 */
	public static final int NOT_CONTINUITY = 2;

	/**
	 * 异常下降
	 */
	public static final int ABNORMAL_DESCEND = 3;

	/**
	 * 液位异常波动
	 */
	public static final int ERROR_WAVING = 4;

	/**
	 * 卡浮子
	 */
	public static final int BLOCK_FLOAT = 5;

	private Integer type;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private Integer tankNo;
	private Double startLevel;
	private Double startWaterLevel;
	private Double startWaterVolume;
	private Double startVolume;
	private Double startTemperature;

	private Double endWaterLevel;
	private Double endVolume;
	private Double endTemperature;
	private Double endLevel;
	private Double endWaterVolume;
	private String detailInfo;
	private Status status;
	/**
	 * 是否完成
	 */
	private boolean closed = false;

	/**
	 * 期间的付油量
	 */
	private double sumOut;

	/**
	 * 持续时间
	 */
	private double seconds;
	/**
	 * 液位轨迹id
	 */
	private Long traceId;

	/**
	 * 初始化
	 *
	 * @param inventory
	 */
	public void init(Inventory inventory) {
		this.setTankNo(inventory.getTankNo());
		this.setStartLevel(inventory.getLevel());
		this.setStartTime(inventory.getTime());
		this.setStartWaterLevel(inventory.getWaterLevel());
		this.setStartWaterVolume(inventory.getWaterVolume());
		this.setStartVolume(inventory.getVolume());
		this.setStartTemperature(inventory.getTemperature());
		this.setStatus(Status.ENABLE);

	}

	/**
	 * 结束赋值
	 *
	 * @param inventory
	 */
	public void setEndAlarm(Inventory inventory) {
		this.setEndLevel(inventory.getLevel());
		this.setEndTime(inventory.getTime());
		this.setEndWaterLevel(inventory.getWaterLevel());
		this.setEndWaterVolume(inventory.getWaterVolume());
		this.setEndVolume(inventory.getVolume());
		this.setEndTemperature(inventory.getTemperature());
		this.closed = true;
	}

	@Override
	public String toString() {
		return "DbInventoryAlarm{" +
				"type=" + type +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", tankNo=" + tankNo +
				", startLevel=" + startLevel +
				", startWaterLevel=" + startWaterLevel +
				", startWaterVolume=" + startWaterVolume +
				", startVolume=" + startVolume +
				", startTemperature=" + startTemperature +
				", endWaterLevel=" + endWaterLevel +
				", endVolume=" + endVolume +
				", endTemperature=" + endTemperature +
				", endLevel=" + endLevel +
				", endWaterVolume=" + endWaterVolume +
				", detailInfo='" + detailInfo + '\'' +
				", status=" + status +
				", closed=" + closed +
				", sumOut=" + sumOut +
				", traceId=" + traceId +
				'}';
	}
}
