package com.oket.device.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.device.MaintenanceManager;

/**
 * 运维单位服务类
 * @author lw
 * @since 2019-12-12
 */
public interface MaintenanceManagerService extends IService<MaintenanceManager> {
    /**
     * 查询运维单位列表
     * @param jsonObject
     * @param b
     * @return
     */
    Page<MaintenanceManager> query(JSONObject jsonObject, boolean b);

    /**
     * 运维单位是否存在
     * @param maintenanceManager
     * @return
     */
    boolean queryExistMaintenanceManager(MaintenanceManager maintenanceManager);
}
