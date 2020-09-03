package com.oket.station.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.station.StationEntity;

import java.util.List;
public interface StationDao extends BaseMapper<StationEntity> {
    /**
     * 油站信息查询
     * @param jsonObject
     * @return
     */
    List<JSONObject> selectStaionAddress(JSONObject jsonObject);
}
