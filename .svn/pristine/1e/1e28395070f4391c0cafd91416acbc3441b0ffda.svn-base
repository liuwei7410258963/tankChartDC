package com.oket.device;

import com.oket.common.base.Status;

import java.util.Collection;
import java.util.List;

public interface DeviceItem<T extends DeviceVersion> {
	/**
	 * 设备ID 系统内ID
	 */
	Integer getId();

	/**
	 * 制造商出厂系列号
	 */
	String getManufacturerProductId();

	/**
	 * 设备类型，例如液位仪、测漏报警、加油机等
	 */
	DeviceType getDeviceType();

	/**
	 * 获取油站id
	 *
	 * @return
	 */
	Integer getStationId();

	/**
	 * 产品信息
	 *
	 * @return
	 */
	Product getProduct();

	/**
	 * 获取产品型号
	 *
	 * @return
	 */
	String getProductType();

	/**
	 * 设备版本
	 *
	 * @return
	 */
	List<T> getDeviceVersions();

	/**
	 * 增加设备版本
	 *
	 * @param version
	 * @return
	 */
	boolean addDeviceVersion(DeviceVersion version);

	/**
	 * 增加多个设备版本
	 *
	 * @param versions
	 * @return
	 */
	boolean addDeviceVersions(Collection<T> versions);

	/**
	 * 获取详情信息
	 *
	 * @return
	 */
	String getDetailInfo();

	/**
	 * 获取描述信息
	 *
	 * @return
	 */
	String getAbbreviation();

	/**
	 * 获取状态信息
	 *
	 * @return
	 */
	Status getStatus();

	/**
	 * 获取设备ip
	 *
	 * @return
	 */
	String getDeviceIp();

	/**
	 * 获取设备端口
	 *
	 * @return
	 */
	String getDevicePorts();

}
