package com.oket.device.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.device.NozRelDisEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-04-01
 */
public interface NozRelDisService extends IService<NozRelDisEntity> {
    /**
     * 解除油枪加油机关系
     * @param lists
     * @return
     */
    void cancelRelation(List<NozRelDisEntity> lists);

    /**
     * 查询油枪加油机关系
     * @param jsonObject
     * @return
     */
    List<NozRelDisEntity> query(JSONObject jsonObject);
    /*
     * 更改油枪加油机关系
     */
    void changeRelation(List<NozRelDisEntity> nozTankRelationEntityLists);
}
