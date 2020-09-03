package com.oket.tankchartdc.mina.process;

import com.oket.dispenser.NozzleOutConsumer;
import com.oket.dispenser.NozzleState;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.InventoryConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description: ifsf消息处理
 * @author: SunBiaoLong
 * @create: 2019-12-07 09:07
 **/
@Service
@Slf4j
public class IFSFProcess extends BaseProcess {
	/**
	 * 存入时间超时阈值
	 */
	private final static int OVERTIME_SECONDS = 3;

	/**
	 * 处理罐存
	 *
	 * @param inventory
	 */
	public void processInventory(Inventory inventory) {
		//设定的等待时间为2s，如果超过2s还没加进去返回true

		offerData(inventory);

	}

	private void offerData(Object inventory) {
		try {
			int offer = 1;
			while (!isOffer(inventory) && offer <= 3) {
				log.error("放入数据失败：" + inventory);
				offer ++;
			}
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	private boolean isOffer(Object obj) throws InterruptedException {
		return InventoryConsumer.getRealtimeDataBlockingQueue().offer(obj, OVERTIME_SECONDS, TimeUnit.SECONDS);
	}


	/**
	 * 处理油枪状态数据
	 *
	 * @param nozzleState
	 */
	public void processNozzleState(NozzleState nozzleState) {
		offerData(nozzleState);
	}

	/**
	 * 加油中信息
	 *
	 * @param fueling
	 */
	public void processFueling(NozzleState fueling) {
		offerData(fueling);
	}


	/**
	 * 获取油枪编号
	 *
	 * @param node         节点
	 * @param fuelingPoint 加油点
	 * @return
	 */
	public Integer getNozzle(byte node, int fuelingPoint) {
		return deviceService.getNozzle(node, fuelingPoint);
	}
}
