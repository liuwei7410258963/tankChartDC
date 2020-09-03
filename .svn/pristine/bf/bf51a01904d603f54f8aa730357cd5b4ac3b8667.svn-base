package com.oket.tank4station;

import com.oket.tank4station.*;
import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tank4station.trace.AbstractTraceProcessor;
import com.oket.tank4station.trace.StableTraceProcessor;
import lombok.ToString;

import java.util.List;

/**
 * @description: 稳定液位状态
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:09
 **/
@ToString(callSuper = true)
public class StableInventoryTrace
		extends AbstractLevelTrace {


	public StableInventoryTrace(int deliveryId, int cycleId, TankSession tankSession) {
		super(LevelState.LEVEL_STABLE, deliveryId, cycleId, tankSession);
	}

	public StableInventoryTrace(TankSession tankSession) {
		super(LevelState.LEVEL_STABLE, tankSession);
	}


	@Override
	public void createTraceProcessor(TankSession tankSession) {
		this.traceProcessor = new StableTraceProcessor(this, tankSession);

	}
}
