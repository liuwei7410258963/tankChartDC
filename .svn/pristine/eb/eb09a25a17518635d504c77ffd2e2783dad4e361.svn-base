package com.oket.dispenser;

public interface FuelNozzle {
    /** 激活加油机，从不可操作状态转化为允许操作状态；
     * 不可操作状态包括以下状况：1）当发生故障；2）故障维修；3、参数设置或者初始化状态；
     */
    void operativeNozzle();
    /** 禁止油枪，从可操作状态转化成不可业务操作状态
     */
    void unableNozzle();
    /** 打开油枪，使油枪从关闭状态到空闲
     */
    void openNozzle();
    /** 关闭油枪，重空闲状态转成关闭状态
     */
    void closeNozzle();

}
