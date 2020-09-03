package com.oket.delivery.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.delivery.DeliveryConfirm;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.delivery.VoConfirm;
import com.oket.tank4station.entity.UpdateTableInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-04-08
 */
public interface DeliveryConfirmService extends IService<DeliveryConfirm> {
    /**
     * 查询
     * @param jsonObject
     * @return
     */
    JSONObject query(JSONObject jsonObject);
    /**
     * 更新卸油确认
     * @param jsonObject
     * @return
     */
    void updateConfirm(JSONObject jsonObject);

    /**
     * 查询付油确认数据
     * @return
     */
    List<DeliveryConfirm> getConfirm(Integer deliveryId);
}
