package com.oket.station;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.device.DeviceItem;
import com.oket.device.Devices;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 加油站
 * </p>
 *
 * @author lw
 * @since 2019-11-26
 */
@Data
@Accessors(chain = true)
@TableName("bd_station")
public class StationEntity {

	private static final long serialVersionUID = 1L;
	@TableField(exist = false)
	private final Set<Address> addresses = Collections
			.synchronizedSet(new HashSet<Address>());
	@TableId(type = IdType.INPUT)
	private Long stationId = 1L;
	private String stationName;
	/**
	 * 简称
	 */
	private String stationShortName;
	/**
	 * 移动手机号
	 */
	private String phone;
	/**
	 * 座机号码
	 */
	private String landline;
	/**
	 * 简述
	 */
	private String stationAbbreviation;
	private String stationCode;
	/**
	 * 1启用2禁用
	 */
	private Integer status = 1;
	@TableField(exist = false)
	private Devices devices;

	public boolean addAddress(AbstractAddress address) {
		return true;
	}

	public boolean removeAddress(AbstractAddress address) {
		return false;
	}

	public boolean addDevice(DeviceItem item) {

		return false;
	}

	public boolean removeDevice(DeviceItem item) {
		return false;
	}
}
