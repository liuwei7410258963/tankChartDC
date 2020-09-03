package com.oket.dispenser.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.dispenser.BzNozzleOutGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.dispenser.NozzleOutRelGroup;
import com.oket.dispenser.VoNozzleOutRelGroup;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-04-14
 */
public interface VoNozzleOutGroupService extends IService<BzNozzleOutGroup> {

    /**
     * 分页整合
     *
     * @param req isPage :是不是分页的
     * @return
     */
    IPage<BzNozzleOutGroup> query(JSONObject req, boolean isPage);

    /*
     * 拆分付油组展示数据
     */
    List<VoNozzleOutRelGroup> selectNozzleOutGroup(JSONObject jsonObject);

    /*
     * 付油组关系合并拆分
     */
    void update(List<NozzleOutRelGroup> lists);

    /*
     * 根据付油组查询付油详情
     */
    List<VoNozzleOutRelGroup> selectNozs(JSONObject jsonObject);


    /*
     * 新建付油组后查询附近轨迹数据
     */
    List<JSONObject> querytrace(JSONObject jsonObject);

    /*
     * 查询付油组数据以及关联的id
     */
    JSONObject listNozGroupAndRelId(JSONObject jsonObject);
}
