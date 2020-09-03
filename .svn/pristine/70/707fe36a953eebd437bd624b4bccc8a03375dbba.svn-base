package com.oket.tankchartdc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.oil.OilEntity;
import com.oket.tank4station.dao.DbInventoryTraceDAO;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tank4station.service.impl.DbInventoryTraceServiceImpl;
import com.oket.tankchartdc.BackToTank;
import com.oket.tankchartdc.dao.BackToTankDAO;
import com.oket.tankchartdc.entity.BackToTankConfirmEntity;
import com.oket.tankchartdc.dao.BackToTankConfirmDao;
import com.oket.tankchartdc.service.BackToTankConfirmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-04-24
 */
@Service
public class BackToTankConfirmServiceImpl extends ServiceImpl<BackToTankConfirmDao, BackToTankConfirmEntity> implements BackToTankConfirmService {
    @Autowired
    private BackToTankServiceImpl backToTankService;

    @Override
    public List<JSONObject> select(JSONObject jsonObject){
        return baseMapper.select(jsonObject);
    }

    @Override
    public void update(List<BackToTankConfirmEntity> lists){
        //用户手动更改回罐的值，并且确认
        this.saveOrUpdateBatch(lists);
        //更改总表的真实回罐量和总付油量
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("true_volume",lists.get(0).getTrueVolumeAll());
        updateWrapper.set("volume",lists.get(0).getVolumeAll());
        updateWrapper.eq("id",lists.get(0).getBackId());
        backToTankService.update(updateWrapper);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("back_id",lists.get(0).getBackId());
        List<BackToTankConfirmEntity> dbLists= baseMapper.selectList(queryWrapper);
        List<Integer> dbIds = dbLists.stream().map(BackToTankConfirmEntity::getId).collect(Collectors.toList());
        List<Integer> ids = lists.stream().map(BackToTankConfirmEntity::getId).collect(Collectors.toList());
        dbIds.removeAll(ids);
        if(dbIds.size()!=0) {
            baseMapper.deleteBatchIds(dbIds);
        }
        //不对已有付油罐存组产生影响，仅影响校正图的变化率
    }
}
