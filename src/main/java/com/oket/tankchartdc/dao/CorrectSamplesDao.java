package com.oket.tankchartdc.dao;

import com.alibaba.fastjson.JSONObject;
import com.oket.tankchartdc.BzTraceRelOutGroup;
import com.oket.tankchartdc.entity.CorrectSamples;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.tankchartdc.entity.CorrectingDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2020-07-22
 */
public interface CorrectSamplesDao extends BaseMapper<CorrectSamples> {
    /*
     * 查询整合图
     */
    List<CorrectingDTO> select(JSONObject jsonObject);

    /**
     * 查询整合图的弹窗
     *
     * @return
     */
    List<BzTraceRelOutGroup> getRels(JSONObject req);

    /*
     * 查询付油数据校正值
     */
    List<JSONObject> selectCorrectOut(@Param("groupIds") List<Long> groupIds);
}
