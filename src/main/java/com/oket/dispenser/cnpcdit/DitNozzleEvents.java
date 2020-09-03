package com.oket.dispenser.cnpcdit;

import com.oket.dispenser.NozzleEvents;

public class DitNozzleEvents implements NozzleEvents {
    /**
     * 激活加油机，从不可操作状态转化为允许操作状态；
     * 不可操作状态包括以下状况：1）当发生故障；2）故障维修；3、参数设置或者初始化状态；
     */
    @Override
    public void operative() {

    }

    /**
     * 禁止油枪，从可操作状态转化成不可业务操作状态
     */
    @Override
    public void unable() {

    }

    /**
     * 打开油枪，使油枪从关闭状态到空闲
     */
    @Override
    public void open() {

    }

    /**
     * 关闭油枪，重空闲状态转成关闭状态
     */
    @Override
    public void close() {

    }

    /**
     * 有效提枪
     */
    @Override
    public void validNozzleUp() {

    }

    /**
     * 无效提枪
     */
    @Override
    public void invalidNozzleUp() {

    }

    /**
     * 挂枪
     */
    @Override
    public void nozzleDown() {

    }

    @Override
    public void releaseNozzle() {

    }

    /**
     * 授权超时
     */
    @Override
    public void authTimeout() {

    }

    /**
     * 加油超时
     */
    @Override
    public void fuellingTimeout() {

    }

    /**
     * 暂停加油
     */
    @Override
    public void suspendNozzzle() {

    }

    /**
     * 继续加油
     */
    @Override
    public void resumeNozzle() {

    }

    /**
     * 终止加油
     */
    @Override
    public void terminateNozzle() {

    }

    /**
     * 无动作
     */
    @Override
    public void noProgress() {

    }

    /**
     * 限时完成
     */
    @Override
    public void limitReached() {

    }

    /**
     * 最大加油量
     */
    @Override
    public void maxVolume() {

    }

    /**
     * 已经开始加油
     */
    @Override
    public void firstVolumePluses() {

    }
}
