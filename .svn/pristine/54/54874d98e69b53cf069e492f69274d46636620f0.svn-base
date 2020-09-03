package com.oket.device;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 加油机
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "dev_dispenser")
public class Dispenser4Device extends AbstractDeviceItem<Version> {
	public Dispenser4Device() {
		this.deviceType = DeviceType.DISPENSER;
	}

	/**
	 * 版本列表
	 */
	@TableField(exist = false)
	private List<Version> deviceVersions;

	/**
	 * 油罐编号--可以关联多个油罐
	 */
	private String tankNos;

	/**
	 * 加油机编号
	 */
	private Integer dispenserNo;
	/**
	 * 油枪列表
	 */
	@TableField(exist = false)
	private List<Nozzle4Device> nozzle4Devices;

	@TableField(exist = false)
	private List<Integer> relNozLists;//关联的油枪list


}
