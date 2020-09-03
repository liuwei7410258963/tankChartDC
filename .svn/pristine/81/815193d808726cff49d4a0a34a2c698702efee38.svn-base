package com.oket.tank4station;

import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tank4station.trace.AbstractTraceProcessor;
import com.oket.tank4station.trace.NewTraceProcessor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 不确定液位状态
 * @author: SunBiaoLong
 * @create: 2020-03-17 16:53
 **/
@Slf4j
@ToString(callSuper = true)
public class NewInventoryTrace extends AbstractLevelTrace {
	public NewInventoryTrace(int deliveryId, int cycleId,TankSession tankSession)  {
		super(LevelState.NEW,deliveryId,cycleId,tankSession);
	}
	public NewInventoryTrace(TankSession tankSession) {
		super(LevelState.NEW,tankSession);
	}


	@Override
	public AbstractLevelTrace newTrace(LevelState levelState) throws InventoryException {
		final AbstractLevelTrace trace = InventoryTraceFactory.getTrace(levelState,this.getTankSession());
		if (this.isConvertToOtherTrace()) {
			convertToOtherTrace(trace);
		} else {
			trace.addInventory(this.lastLevel);
			addOutToNext(trace);
		}
		return trace;
	}

	@Override
	public void createTraceProcessor(TankSession tankSession) {
		this.traceProcessor = new NewTraceProcessor(this, tankSession);

	}


}
