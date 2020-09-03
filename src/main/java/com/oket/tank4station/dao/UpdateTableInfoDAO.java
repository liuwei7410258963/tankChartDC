package com.oket.tank4station.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.tank4station.entity.UpdateTableInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 容积表操作接口
 * @Author: Dxj
 * @Date: 2019/9/27 14:03
 */
public interface UpdateTableInfoDAO extends BaseMapper<UpdateTableInfo> {
    /**
     * 添加或修改容积表信息
     *
     * @param table
     */
    void addTable(@Param("table") UpdateTableInfo table, @Param("tableName") String tableName);

    /**
     * 根据id获取容积表信息
     *
     * @return
     */
    JSONObject getMaxTableInfoByTankId(@Param("tankCode") long tankCode, @Param("tableName") String tableName);

}
