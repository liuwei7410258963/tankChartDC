package com.oket.tank4station;

import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tank4station.trace.AbstractTraceProcessor;
import com.oket.tank4station.trace.DescendingTraceProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @description: 下降液位状态
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:10
 **/
@Slf4j
@ToString(callSuper = true)
public class DescendingInventoryTrace extends AbstractLevelTrace {
	@Getter
	@Setter
	private long stableStartTime;
	/**
	 * 持续平稳30秒
	 */
	public static final long STABLE_THRESHOLD = 35;
	public DescendingInventoryTrace(int deliveryId, int cycleId,TankSession tankSession)  {
		super(LevelState.LEVEL_DESCENDING,deliveryId,cycleId,tankSession);
	}


	public DescendingInventoryTrace(TankSession tankSession)  {
		super(LevelState.LEVEL_DESCENDING,tankSession);
	}

	@Override
	public void createTraceProcessor(TankSession tankSession) {
		this.traceProcessor = new DescendingTraceProcessor(this, tankSession);
	}
}
