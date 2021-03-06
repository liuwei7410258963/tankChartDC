package com.oket.tank4station;

import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tank4station.trace.AbstractTraceProcessor;
import com.oket.tank4station.trace.WavingTraceProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description: 震荡状态
 * @author: SunBiaoLong
 * @create: 2020-04-08 11:40
 **/
@ToString(callSuper = true)
public class WavingInventoryTrace extends AbstractLevelTrace {
	/**
	 * 稳定的阈值时长（分钟）
	 */
	public static final int STABLE_THRESHOLD = 5;
	/**
	 * lastInventories保存的是最近的五分钟之内的液位。如果超过则剔除
	 */
	public static final int MAX_LAST_INVENTORY_TIME_GAP = 8;
	/**
	 * 最后五分钟的数据
	 */
	@Getter
	@Setter
	private List<Inventory> lastInventories = new CopyOnWriteArrayList<>();

	@Getter
	@Setter
	private Inventory uploadFinshed ;
	/**
	 * 开始稳定的时间
	 */
	@Getter
	@Setter
	private long stableStartTime;

	/**
	 * 开始上升的液位高度
	 */
	@Getter
	@Setter
	private double ascendStartLevel;

	public WavingInventoryTrace(int deliveryId, int cycleId, TankSession tankSession) {
		super(LevelState.LEVEL_WAVING, deliveryId, cycleId, tankSession);
	}

	public WavingInventoryTrace(TankSession tankSession) {
		super(LevelState.LEVEL_WAVING, tankSession);
	}

	@Override
	public boolean addInventory(Inventory inventory) throws InventoryException {
		final boolean add = super.addInventory(inventory);
		if (add) {
			if (!lastInventories.isEmpty()) {
				long currentInventoryTime = inventory.getTime().getTime();
				for (Inventory lastInventory : lastInventories) {

					long minGap = currentInventoryTime - lastInventory.getTime().getTime();
					minGap = minGap / 1000 / 60;
					//时间过长的数据剔除掉
					if (minGap > MAX_LAST_INVENTORY_TIME_GAP) {
						lastInventories.remove(lastInventory);
					}
				}
			}
			lastInventories.add(inventory);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public AbstractLevelTrace newTrace(LevelState levelState) throws InventoryException {
		final AbstractLevelTrace trace = InventoryTraceFactory.getTrace(levelState, this.getTankSession());
		if (this.isConvertToOtherTrace()) {
			convertToOtherTrace(trace);
			if (trace.getLevelState().equals(LevelState.LEVEL_ASCENDING)) {
				//标志着继续卸油
				trace.setOnUnloading(true);
			}
		} else {
			return super.newTrace(levelState);
		}
		return trace;

	}

	@Override
	public void createTraceProcessor(TankSession tankSession) {
		this.traceProcessor = new WavingTraceProcessor(this, tankSession);
	}


}
