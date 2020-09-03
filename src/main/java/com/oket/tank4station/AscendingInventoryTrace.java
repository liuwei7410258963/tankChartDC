package com.oket.tank4station;

import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tank4station.trace.AbstractTraceProcessor;
import com.oket.tank4station.trace.AscendingTraceProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 上升液位状态
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:10
 **/
@Slf4j
@ToString(callSuper = true)
public class AscendingInventoryTrace extends AbstractLevelTrace {
    /**
     * 持续平稳35秒
     */
    public static final long STABLE_THRESHOLD = 35;
    /**
     * 持续下降35秒
     */
    public static final long DESCEND_THRESHOLD = 35;
    /**
     * 持续平稳35秒
     */
    public static final long WAVING_THRESHOLD = 35;
    /**
     * 稳定开始时间
     */
    @Getter
    @Setter
    private long stableStartTime;

    /**
     * 波动开始时间
     */
    @Getter
    @Setter
    private long wavingStartTime;

    /**
     * 波动开始时间
     */
    @Getter
    @Setter
    private long descendStartTime;

    public AscendingInventoryTrace(int deliveryId, int cycleId, TankSession tankSession) {
        super(LevelState.LEVEL_ASCENDING, deliveryId, cycleId, tankSession);
    }

    public AscendingInventoryTrace(TankSession tankSession) {
        super(LevelState.LEVEL_ASCENDING, tankSession);
    }

    @Override
    public void createTraceProcessor(TankSession tankSession) {
        this.traceProcessor = new AscendingTraceProcessor(this, tankSession);
    }
}
