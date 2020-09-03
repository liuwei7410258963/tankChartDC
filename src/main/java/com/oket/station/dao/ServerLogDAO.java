package com.oket.station.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.station.ServerLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 系统运行日志
 * @author: SunBiaoLong
 * @create: 2020-05-23 13:57
 **/
@Repository
public interface ServerLogDAO  extends BaseMapper<ServerLog> {
    /*
     * 查询运行记录信息
     */
    List<JSONObject> selectServer(JSONObject jsonObject);

    /*
     * 查询异常信息
     */
    List<JSONObject> selectAbnormal(JSONObject jsonObject);


    /*
     * 查询第一次开机时间和持续天数
     */
    JSONObject selectTime(JSONObject jsonObject);
}
