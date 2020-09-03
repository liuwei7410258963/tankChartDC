package com.oket.dispenser;

public interface NozzleEvents {
    /** 激活加油机，从不可操作状态转化为允许操作状态；
     * 不可操作状态包括以下状况：1）当发生故障；2）故障维修；3、参数设置或者初始化状态；
     */
    void operative();
    /** 禁止油枪，从可操作状态转化成不可业务操作状态
     */
    void unable();
    /** 打开油枪，使油枪从关闭状态到空闲
     */
    void open();
    /** 关闭油枪，重空闲状态转成关闭状态
     */
    void close();

    /**
     * 有效提枪
     */
    void validNozzleUp();

    /**
     * 无效提枪
     */
    void invalidNozzleUp();

    /**
     * 挂枪
     */
    void nozzleDown();
    void releaseNozzle();

    /**
     * 授权超时
     */
    void authTimeout();
    /**
     * 加油超时
     */
    void fuellingTimeout();
    /**
     * 暂停加油
     */
    void suspendNozzzle();

    /**
     * 继续加油
     */
    void resumeNozzle();
    /**
     * 终止加油
     */
    void terminateNozzle();
    /**
     * 无动作
     */
    void noProgress();
    /**
     * 限时完成
     */
    void limitReached();

    /**
     * 最大加油量
     */
    void maxVolume();

    /**
     * 已经开始加油
     */
    void firstVolumePluses();
}
