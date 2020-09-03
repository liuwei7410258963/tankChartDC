package com.oket.device.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.oket.device.NozRelDisEntity;
import com.oket.device.NozTankRelationEntity;
import com.oket.device.dao.NozRelDisDao;
import com.oket.device.service.NozRelDisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.device.service.Nozzle4DeviceService;
import com.oket.station.bizservice.BalanceService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-04-01
 */
@Service
public class NozRelDisServiceImpl extends ServiceImpl<NozRelDisDao, NozRelDisEntity> implements NozRelDisService {

    @Autowired
    Nozzle4DeviceService nozzle4DeviceService;
    @Override
    public List<NozRelDisEntity> query(JSONObject req) {

        QueryWrapper<NozRelDisEntity> wrapper = new QueryWrapper<>();
        if(req.get("dispenserNo")!=null){
            wrapper.eq("dispenser_no",req.get("dispenserNo"));
        }
        wrapper.orderByDesc("end_time");
        //结束时间为空放在最前面，其他按时间倒叙排列
        List<NozRelDisEntity> dbLists = baseMapper.selectList(wrapper);
        //带查询条件为查询改罐的数据
        if(req.get("dispenserNo")!=null){
            List<NozRelDisEntity> result;
            result = dbLists.stream().filter(x->x.getEndTime()==null)
                    .filter(x->x.getStartTime()!=null)
                    .sorted(Comparator.comparing(NozRelDisEntity::getStartTime).reversed())
                    .collect(toList());
            result.addAll(dbLists.stream().filter(x->x.getStartTime()==null).collect(toList()));
            List<NozRelDisEntity> temp = dbLists.stream().filter(x->x.getEndTime()!=null).sorted(Comparator.comparing(NozRelDisEntity::getEndTime).reversed()).collect(toList());
            result.addAll(temp);
            return result;
        }
        //不带查询条件为查询所有数据
        else{
            return dbLists;
        }
    }

    @Override
    public void changeRelation(List<NozRelDisEntity> nozRelDisEntityList){
        Date now = new Date();
        List<Integer> integerList = nozRelDisEntityList.stream().map(NozRelDisEntity::getId).collect(toList());
        List<NozRelDisEntity> needChangeLists = baseMapper.selectBatchIds(integerList);
        //传过来的参数可能包含未关联过的（需关联），还有已经关联过的（需改变关联，并生成新的关联数据）
        List<NozRelDisEntity> disnoNotEmptys = new ArrayList<>();
        List<NozRelDisEntity> disnoEmptys = new ArrayList<>();
        for(NozRelDisEntity list:needChangeLists){
            if(list.getDispenserNo()==null){
                disnoEmptys.add(list);
            }
            else{
                disnoNotEmptys.add(list);
            }
        }
        //已关联的加油机
        if(disnoNotEmptys.size()!=0){
            List<NozRelDisEntity> tempList = nozRelDisEntityList;
            //给已关联的油罐设置关联关系，结束时间赋值
            disnoNotEmptys.stream().forEach(x-> x.setEndTime(now));
            this.updateBatchById(disnoNotEmptys);
            //生成新的数据，开始时间为当前时间，结束时间为空
            List<NozRelDisEntity> result = new ArrayList<>();
            for(NozRelDisEntity list:disnoNotEmptys) {
                tempList.stream().filter(x->x.getNozzleCode()==list.getNozzleCode())
                        .filter(x->list.getDispenserNo()!=null).forEach(x->{
                    NozRelDisEntity nozRelDisEntity = new NozRelDisEntity();
                    nozRelDisEntity.setNozzleCode(x.getNozzleCode());
                    nozRelDisEntity.setDispenserNo(x.getDispenserNo());
                    nozRelDisEntity.setEndTime(null);
                    nozRelDisEntity.setStartTime(now);
                    result.add(nozRelDisEntity);
                });
            }
            this.saveBatch(result);
        }
        //未关联的加油机
        if(disnoEmptys.size()!=0){
            for(NozRelDisEntity list:nozRelDisEntityList) {
                disnoEmptys.stream().filter(x->list.getNozzleCode()==x.getNozzleCode()).forEach(x -> {x.setStartTime(now);x.setDispenserNo(list.getDispenserNo());});
            }
            this.updateBatchById(disnoEmptys);
        }
        //修改noz表
        nozRelDisEntityList.stream().forEach(x->{
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("nozzle_no",x.getNozzleCode());
            wrapper.set("dispenser_no",x.getDispenserNo());
            nozzle4DeviceService.update(wrapper);
        });
        //枪机关系不用推送到处理模块
    }

    @Override
    public void cancelRelation(List<NozRelDisEntity> nozRelDisEntityList){
        Date now = new Date();
        List<Integer> integerList = nozRelDisEntityList.stream().map(NozRelDisEntity::getId).collect(toList());
        List<NozRelDisEntity> needChangeLists = baseMapper.selectBatchIds(integerList);
        //将原有数据的结束时间赋值
        needChangeLists.stream().forEach(x-> x.setEndTime(now));
        this.updateBatchById(needChangeLists);
        //生成新的数据未关联的数据，除油枪编号外其他都为空
        nozRelDisEntityList.stream().forEach(x-> {x.setId(null);x.setDispenserNo(null);x.setEndTime(null);x.setStartTime(null);});
        this.saveBatch(nozRelDisEntityList);

        //修改noz表
        needChangeLists.stream().forEach(x->{
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("nozzle_no",x.getNozzleCode());
            wrapper.set("dispenser_no",null);
            nozzle4DeviceService.update(wrapper);
        });
    }
}
