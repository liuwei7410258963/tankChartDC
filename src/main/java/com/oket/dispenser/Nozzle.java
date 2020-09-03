package com.oket.dispenser;

import com.oket.device.DeviceItem;
import lombok.Data;

@Data
public class Nozzle {
	/**
	 * @see DeviceItem#
	 */
	protected long nozzleID;

	protected String nozzleName;
	/**
	 *
	 */
	protected long dispenserID;
	/**
	 * 加油站内部内部业务物理枪号
	 *
	 * @see #nozzleID4Station
	 */
	protected String nozzleID4Station;
}
