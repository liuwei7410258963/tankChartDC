package com.oket.tankchartdc.dao;

import com.alibaba.fastjson.JSONObject;
import com.oket.tankchartdc.entity.BackToTankConfirmEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2020-04-24
 */
@Repository
public interface BackToTankConfirmDao extends BaseMapper<BackToTankConfirmEntity> {
    /*
     * 查询回罐详情
     */
    List<JSONObject> select(JSONObject jsonObject);

}
