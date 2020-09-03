package com.oket.device;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.oket.common.base.Status;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * 抽象的设备类
 * @param <T>
 */
@Data
abstract public class AbstractDeviceItem<T extends DeviceVersion> implements DeviceItem, Serializable {
	/**
	 * 设备类型
	 */
	protected DeviceType deviceType;
	/**
	 * 设备id
	 */
	@TableId(type = IdType.AUTO)
	protected Integer id;
	/**
	 * 生产厂家的产品id
	 */
	protected String manufacturerProductId;

	/**
	 * 系统内的产品id
	 */
	protected Integer productId;
	/**
	 * 简介
	 */
	@TableField(exist = false)
	protected String abbreviation;

	/**
	 * 详情
	 */
	protected String detailInfo;

	/**
	 * 产品信息
	 */
	@TableField(exist = false)
	protected Product product;

	/**
	 * 油站id
	 */
	protected Integer stationId;
	/**
	 * 状态
	 */
	protected Status status;

	/**
	 * 产品型号
	 */
	protected String productType;

	/**
	 * 设备Ip
	 */
	@TableField(exist = false)
	protected String deviceIp;
	/**
	 * 设备端口
	 */
	@TableField(exist = false)
	protected String devicePorts;
	/**
	 * 安装时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date installTime;
	/**
	 * 运维单位id
	 */
	protected Integer maintenanceManagerId;
	/**
	 * 运维单位名称
	 */
	protected String maintenanceManagerName;
	/**
	 * 检修时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date checkTime;
	/**
	 * 备用属性1
	 */
	protected String remark;

	@Override
	public boolean addDeviceVersion(DeviceVersion version) {
		return false;
	}


	@Override
	public boolean addDeviceVersions(Collection versions) {
		return false;
	}

}
