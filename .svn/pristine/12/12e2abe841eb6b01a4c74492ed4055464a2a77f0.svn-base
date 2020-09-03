package com.oket.tankchartdc.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.tankchartdc.BackToTank;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 回罐
 * @author: SunBiaoLong
 * @create: 2020-04-17 08:30
 **/
@Repository
public interface BackToTankDAO extends BaseMapper<BackToTank> {
    /*
     * 查询回罐主页接口
     */
    int count(JSONObject jsonObject);
    /*
     * 查询回罐主页接口
     */
    List<JSONObject> select(JSONObject jsonObject);
    /*
     * 查询回罐主页接口
     */
    List<JSONObject> selectNoPage(JSONObject jsonObject);
    /*
     * 查询监控中心回罐
     */
    List<JSONObject> selectHomePage(JSONObject jsonObject);
}
