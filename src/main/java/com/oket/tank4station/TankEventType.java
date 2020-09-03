package com.oket.tank4station;

/**
 * 油罐内油品变化个各种状态事件类型
 */
public enum TankEventType {
    /** 油罐监控开始状态。在这一状态下油罐不进行任何业务监控*/
    NEW,
    /** 液位开始上升 */
    LEVELSTARTASCEND,
    /** 液位上升 */
    LEVELASCENDING,
    /** 液位上升结束*/
    LEVELASCENDED,
    /** 液位开始下降 */
    LEVELSTARTDESCEND,
    /** 液位下降*/
    LEVELDESCENDING,
    /** 液位下降结束 */
    LEVELDESCENDED,
    /** 液位开始稳定 */
    LEVELSTARTSTABLE,
    /** 液位稳定*/
    LEVELSTABLED,
    /** 液位更行中断 */
    LEVELSTOPUPDATE
}
