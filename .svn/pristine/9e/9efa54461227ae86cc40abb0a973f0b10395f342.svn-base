package com.oket.tankchartdc.mina.dit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description: dit模拟器处理器
 * @author: SunBiaoLong
 * @create: 2019-12-21 11:25
 **/
@Slf4j
@Component
public class DitEmulatorProcess {
    /**
     * 是否启用dit文件模拟
     */
    public static boolean enableDitFileEmulator = false;
    /**
     * 是否可以模拟器
     */
    public static boolean canEnable = false;

    @Value("${dit.emulator.enable}")
    private boolean ditEmulatorEnable;

    @PostConstruct
    public void init() {
        try {
            canEnable =ditEmulatorEnable;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            canEnable = false;
        }

    }

    /**
     * 判断是否启用了dit模拟器
     *
     * @return
     */
    public static boolean isEnableDitFileEmulator() {
        if (!canEnable) {
            return false;
        }
        return enableDitFileEmulator;
    }

    /**
     * 设置dit模拟器是否
     *
     * @param enableDitFileEmulator
     */
    public static void setEnableDitFileEmulator(boolean enableDitFileEmulator) {
        if (canEnable) {
            DitEmulatorProcess.enableDitFileEmulator = enableDitFileEmulator;
        }
    }
}
