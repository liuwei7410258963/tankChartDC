package com.oket.tank4station.trace;

import com.oket.tank4station.*;
import com.oket.tank4station.service.InventoryPersistenceProcessor;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 新建轨迹处理器
 * @author: SunBiaoLong
 * @create: 2020-04-10 10:18
 **/
@Slf4j
public class NewTraceProcessor extends AbstractTraceProcessor {

	public NewTraceProcessor(NewInventoryTrace trace,  TankSession tankSession) {
		super(trace,  tankSession);
	}



	@Override
	public void processing(Inventory inventory) throws InventoryException {
	}

	@Override
	public void closed() throws InventoryException {
		if (!trace.isConvertToOtherTrace()) {
			super.closed();
		}
	}

	@Override
	public LevelState checkLevelState(Inventory inventory) {
		if (trace.getFirstLevel() == null) {
			return LevelState.NEW;
		}
		Inventory beforeInventory = trace.getFirstLevel();
		long min = inventory.getTime().getTime() - beforeInventory.getTime().getTime();
		min = min / 1000 / 60;
		if (min < INVENTORY_UPLOAD_MINUTE) {
			trace.setConvertToOtherTrace(true);
			final double level = inventory.getLevel();
			double levelGap = level - beforeInventory.getLevel();
			log.debug("变化量：" + levelGap);
			if (levelGap < LEVEL_DOWN_THRESHOLD) {
				return LevelState.LEVEL_DESCENDING;
			} else if (levelGap > LEVEL_UP_THRESHOLD) {
				return LevelState.LEVEL_ASCENDING;
			} else {
				return LevelState.LEVEL_STABLE;
			}
		} else {
			//超过45分钟，应该创建一条的新的LevelState.NEW的液位轨迹
			try {
				trace.addInventory(inventory);
			} catch (InventoryException e) {
				log.error(e.getMessage(), e);
			}
			trace.setClosed(true);
			return LevelState.NEW;
		}
	}
}
