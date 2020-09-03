package com.oket.tank4station;

import com.oket.tank4station.entity.DbInventoryTrace;

/**
 * @description: 轨迹工厂
 * @author: SunBiaoLong
 * @create: 2020-04-09 19:05
 **/
public class InventoryTraceFactory {
    /**
     * 获取trace并初始化
     *
     * @param dbTrace 数据库中的trace
     * @return 业务trace
     */
    public static AbstractLevelTrace getTrace(DbInventoryTrace dbTrace) throws InventoryException {
        //避免传入的
        if (dbTrace == null || dbTrace.getLevelState() == null) {
            return null;
        }
        final AbstractLevelTrace trace = getTrace(dbTrace.getLevelState(),null);
        if (trace != null) {
            trace.fromDbTrace(dbTrace);
            return trace;
        } else {
            return null;
        }
    }

    /**
     * 获取trace并初始化
     *
     * @param dbTrace 数据库中的trace
     * @return 业务trace
     */
    public static AbstractLevelTrace getTrace(DbInventoryTrace dbTrace,TankSession tankSession) throws InventoryException {
        //避免传入的
        if (dbTrace == null || dbTrace.getLevelState() == null) {
            return null;
        }
        final AbstractLevelTrace trace = getTrace(dbTrace.getLevelState(),tankSession);
        if (trace != null) {
            trace.fromDbTrace(dbTrace);
            return trace;
        } else {
            return null;
        }
    }




    public static AbstractLevelTrace getTrace(LevelState levelState,TankSession tankSession) throws InventoryException {
        AbstractLevelTrace abstractLevelTrace = null;
        switch (levelState) {
            case LEVEL_WAVING:
                abstractLevelTrace = new WavingInventoryTrace(tankSession);
                break;
            case NEW:
                abstractLevelTrace = new NewInventoryTrace(tankSession);
                break;
            case LEVEL_DESCENDING:
                abstractLevelTrace = new DescendingInventoryTrace(tankSession);
                break;
            case LEVEL_ASCENDING:
                abstractLevelTrace = new AscendingInventoryTrace(tankSession);
                break;
            case LEVEL_STABLE:
                abstractLevelTrace = new StableInventoryTrace(tankSession);
                break;
            default:
                throw new InventoryException("找不到转化的类型，levelState：" + levelState);
        }
        return abstractLevelTrace;
    }
}
