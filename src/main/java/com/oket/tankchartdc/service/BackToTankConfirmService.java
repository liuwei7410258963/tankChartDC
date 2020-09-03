package com.oket.tankchartdc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.tankchartdc.BackToTank;
import com.oket.tankchartdc.entity.BackToTankConfirmEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-04-24
 */
public interface BackToTankConfirmService extends IService<BackToTankConfirmEntity> {
    /**
     * 查询回罐详情
     * @param jsonObject
     * @return
     */
    List<JSONObject> select(JSONObject jsonObject);
    /*
     * 更新回罐确认数据
     */
    void update(List<BackToTankConfirmEntity> lists);

}
