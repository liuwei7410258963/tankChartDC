package com.oket.oil.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oket.oil.OilEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-14
 */

public interface OilDAO extends BaseMapper<OilEntity> {
    /**
     * 分页查询油品
     *
     * @param page
     * @param wrapper
     * @return
     */
    List<OilEntity> selectPageExt(Page<OilEntity> page, @Param("ew") QueryWrapper wrapper);

    /**
     * 通过油品编号得到油品名
     * @param oilCode
     * @return
     */
    String getOilNameByOilCode(String oilCode);
    /**
     * 通过油站ID和油罐code 查询对应地油品
     * @param stationId
     * @param tankCode
     * @return
     */
    String getOilNameByStation(@Param("stationId") Long stationId, @Param("tankCode") Integer tankCode);


    /**
     * 通过油罐号获取油品
     * @param station
     * @return
     */
    JSONObject getOilByTank(JSONObject station);
    /**
     * 通过油品ID 获取油品名
     * @return 油品名
     */
    JSONObject getOilNameByOilID(int oilID);

    /**
     * 获取油品的Id
     * @param shortName
     * @return
     */
    Integer getOilIdByShortName(@Param("shortName") String shortName);

    /**
     * 获取所有油品
     * @param jsonObject
     * @return
     */
    List<JSONObject> getAllOil(JSONObject jsonObject);
}
