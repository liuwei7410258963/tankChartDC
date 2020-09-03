package com.oket.tankchartdc.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseService;

import com.oket.tankchartdc.entity.AbnormalEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-04-01
 */
public interface AbnormalService extends BaseService<AbnormalEntity> {
    List<JSONObject> exportPumpLists(List<AbnormalEntity> lists);
    /*
     * 泵码数异常确认
     */
    void update(JSONObject jsonObject);
}
