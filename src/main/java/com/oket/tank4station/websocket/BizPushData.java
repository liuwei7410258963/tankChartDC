package com.oket.tank4station.websocket;

import com.oket.dispenser.BzNozzleOutLast;
import com.oket.dispenser.NozzleState;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryAlarm;
import com.oket.tank4station.entity.DbInventoryLast;
import lombok.Data;

import java.util.Collection;

/**
 * @description: 业务推送数据
 * @author: SunBiaoLong
 * @create: 2019-12-13 14:01
 **/
@Data
public class BizPushData {
	/**
	 * 最后的罐存数据
	 */
	private Collection<DbInventoryLast> dbInventoryLasts;
	/**
	 * 最后的销售数据
	 */
	private Collection<BzNozzleOutLast> nozzleOutLasts;

	/**
	 * 罐存报警信息
	 */
	private Collection<DbInventoryAlarm> inventoryAlarms;

	/**
	 * 油枪状态信息
	 */
	private Collection<NozzleState> nozzleStates;
}
