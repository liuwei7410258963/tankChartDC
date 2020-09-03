package com.oket.device.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oket.device.TankInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 油罐接口
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-14
 */
@Repository
public interface TankInfoDAO extends BaseMapper<TankInfo> {
    /**
     * 油罐列表
     * @param page
     * @param wrapper
     * @return
     */
    List<TankInfo> selectPageExt(Page<TankInfo> page, @Param("ew") QueryWrapper wrapper);

    /**
     * 通过油罐Code和stationId得到油罐
     * @param tankCodeAndstationCode
     * @return
     */
    JSONObject getTankInfo(JSONObject tankCodeAndstationCode);
}
