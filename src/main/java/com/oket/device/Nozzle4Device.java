package com.oket.device;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.OnlineStatus;
import com.oket.dispenser.DispenserErrorItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 油枪
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "dev_nozzle")
public class Nozzle4Device extends AbstractDeviceItem<Version> {
	/**
	 * 版本 Set<DeviceVersion>
	 */
	@TableField(exist = false)
	private List<Version> deviceVersions;
	/**
	 * 加油机id
	 */
	private Integer dispenserNo;
	/**
	 * 油罐编号
	 */
	private Integer tankNo;
	/**
	 * 油枪编号
	 */
	private Integer nozzleNo;
	/**
	 * ifsf的节点地址
	 */
	private Integer ifsfNode;
	/**
	 * ifsf加油点
	 */
	private Integer ifsfFuelingPoint;
	/**
	 * 在线状态
	 */
	private OnlineStatus onlineStatus;
	/**
	 * 在线状态发生变化时间
	 */
	private Date onlineStatusChangeTime;
	/**
	 * 油品code
	 */
	private String oilCode;
	/**
	 * 油品名称
	 */
	private String oilName;
	@TableField(exist = false)
	private DispenserErrorItem dispenserErrorItem;

	public Nozzle4Device() {
		this.deviceType = DeviceType.NOZZLE;
	}
}
