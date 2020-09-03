package com.oket.device.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.device.NozTankRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.tank4station.entity.UpdateTableInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-03-31
 */
public interface NozTankRelationService extends IService<NozTankRelationEntity> {
    /**
     * 改变枪罐关系
     * @param lists
     * @return
     */
    void changeRelation(List<NozTankRelationEntity> lists);
    /**
     * 解除枪罐关系
     * @param lists
     * @return
     */
    void cancelRelation(List<NozTankRelationEntity> lists);

    /**
     * 查询
     * @param jsonObject
     * @return
     */
    List<NozTankRelationEntity> query(JSONObject jsonObject);

}
