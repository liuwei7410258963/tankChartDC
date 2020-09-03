package com.oket.device;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.Status;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设备实体
 *
 * @author SunBiaoLong
 * @since 2019-10-14
 */
@Data
@Accessors(chain = true)
@TableName("dev_product")
public class Product {
	private static final long serialVersionUID = 1L;

	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * 设备名称
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 设备型号
	 */
	@TableField("PRODUCT_TYPE")
	private String productType;
	/**
	 * 设备类型
	 */
	private DeviceType deviceType;

	/**
	 * 厂商
	 */
	@TableField("MANUFACTURER")
	private String manufacturer;
	/**
	 * 状态
	 */
	@TableField("STATUS")
	private Status status;

	/**
	 * 检修周期,单位月
	 */
	@TableField("CHECK_DATE")
	private Integer checkDate;

	/**
	 * 经销商
	 */
	private String dealer;
}
