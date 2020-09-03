package com.oket.dispenser.dao;

import com.alibaba.fastjson.JSONObject;
import com.oket.dispenser.BzNozzleOutGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.dispenser.VoNozzleOutRelGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2020-04-14
 */
@Repository
public interface VoNozzleOutGroupDao extends BaseMapper<BzNozzleOutGroup> {

    /*
     * 根据付油组查询付油详情
     */
    List<VoNozzleOutRelGroup> selectNozzleOutGroup(JSONObject jsonObject);

    /*
     * 查询最近的上下两条数据
     */
    List<Integer> selectPreAndNext(BzNozzleOutGroup bzNozzleOutGroup);

    /*
     * 新建付油组后查询附近的轨迹数据
     */
    List<JSONObject> querytrace(JSONObject jsonObject);

    /*
     * 查询付油组数据以及关联的id条数
     */
    int count(JSONObject jsonObject);
    /*
     * 查询付油组数据以及关联的id
     */
    List<JSONObject> listNozGroupAndRelId(JSONObject jsonObject);
}
