package com.oket.tank4station.trace;

import com.oket.tank4station.*;
import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tank4station.tankchart.TankChartManager;
import com.oket.tankchartdc.DbInventoryCycle;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 稳定轨迹处理器
 * @author: SunBiaoLong
 * @create: 2020-04-10 10:16
 **/
@Slf4j
public class StableTraceProcessor extends AbstractTraceProcessor {
	public StableTraceProcessor(StableInventoryTrace stableInventoryTrace, TankSession tankSession) {
		super(stableInventoryTrace, tankSession);
	}


	@Override
	public void start(Inventory inventory) throws InventoryException {
		trace.addInventory(inventory);
		trace.start();
		started = true;
		if (trace.getLastLevel() != inventory) {
			tankSession.addInventoryToNeedPersistence(inventory, true);
		}
		tankSession.addTraceToNeedPersistence(trace);

	}


	@Override
	public void processing(Inventory inventory) throws InventoryException {
		trace.addInventory(inventory);
	}

	@Override
	public void closed() throws InventoryException {
		super.closed();
		tankSession.addInventoryToNeedPersistence(trace.getLastLevel(), true);
	}


	@Override
	public LevelState checkLevelState(Inventory inventory) {
		final double level = inventory.getLevel();
		if (trace.getLastLevel() == null) {
			return LevelState.LEVEL_STABLE;
		}
		double levelGap = level - trace.getLastLevel().getLevel();
		TankInventory lastTankInventory = trace.getLastLevel();
		long seconds = inventory.getTime().getTime() - trace.getLastLevel().getTime().getTime();
		seconds = seconds / 1000;
		//30分钟内不用做异常检查
		if (seconds < INVENTORY_UPLOAD_SECONDS) {
			return getLevelState(levelGap);
		}
		//30-45分钟内
		else {
			long min = seconds / 60;
			if (min <= INVENTORY_UPLOAD_MINUTE) {
				return getLevelState(levelGap);
			}
			//超过45分钟
			else {
				double mmChangeRate =
						TankChartManager.mmChangeRate(
								lastTankInventory
								, inventory);
				if (trace.getFV() > 1 * mmChangeRate) {
					saveInventoryAlarm(lastTankInventory, inventory);
					return LevelState.NEW;
				} else {
					return getLevelState(levelGap);
				}
			}
		}
	}

	/**
	 * 获取液位状态
	 *
	 * @param levelGap
	 * @return
	 */
	private LevelState getLevelState(double levelGap) {
		log.info("levelGap:{},fueling:{}",levelGap,tankSession.isOnFueling());
		if (!tankSession.isOnFueling()) {
			if (levelGap < LEVEL_DOWN_THRESHOLD) {
				return LevelState.LEVEL_DESCENDING;
			} else if (levelGap > LEVEL_UP_THRESHOLD) {
				return LevelState.LEVEL_ASCENDING;
			} else {
				return LevelState.LEVEL_STABLE;
			}
		} else {
			if (levelGap <= 0.11) {
				return LevelState.LEVEL_DESCENDING;
			} else {
				return LevelState.LEVEL_ASCENDING;
			}
		}
	}
}
