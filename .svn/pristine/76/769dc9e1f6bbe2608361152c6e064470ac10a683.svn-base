package com.oket.device.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.device.NozRelDisEntity;
import com.oket.device.NozTankRelationEntity;
import com.oket.device.dao.NozTankRelationDao;
import com.oket.device.dao.Nozzle4DeviceDAO;
import com.oket.device.service.NozTankRelationService;
import com.oket.device.service.Nozzle4DeviceService;
import com.oket.station.bizservice.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-03-31
 */
@Service
public class NozTankRelationServiceImpl extends ServiceImpl<NozTankRelationDao, NozTankRelationEntity> implements NozTankRelationService {


    @Autowired
    Nozzle4DeviceService nozzle4DeviceService;
    @Autowired
    BalanceService balanceService;


    @Override
    public List<NozTankRelationEntity> query(JSONObject req) {
        QueryWrapper<NozTankRelationEntity> wrapper = new QueryWrapper<>();
        if(req.get("tankNo")!=null){
            wrapper.eq("tank_no",req.get("tankNo"));
        }
        wrapper.orderByDesc("end_time");
        //结束时间为空放在最前面，其他按时间倒叙排列
        List<NozTankRelationEntity> dbLists = baseMapper.selectList(wrapper);
        //带查询条件为查询改罐的数据
        if(req.get("tankNo")!=null){
            List<NozTankRelationEntity> result;
            result = dbLists.stream().filter(x->x.getEndTime()==null)
                    .filter(x->x.getStartTime()!=null)
                    .sorted(Comparator.comparing(NozTankRelationEntity::getStartTime).reversed())
                    .collect(toList());
            result.addAll(dbLists.stream().filter(x->x.getStartTime()==null).collect(toList()));
            List<NozTankRelationEntity> temp = dbLists.stream().filter(x->x.getEndTime()!=null).sorted(Comparator.comparing(NozTankRelationEntity::getEndTime).reversed()).collect(toList());
            result.addAll(temp);
            return result;
        }
        //不带查询条件为查询所有数据
        else{
            return dbLists;
        }
    }

    @Override
    public void changeRelation(List<NozTankRelationEntity> nozTankRelationEntityLists){
        Date now = new Date();
        List<Integer> integerList = nozTankRelationEntityLists.stream().map(NozTankRelationEntity::getId).collect(Collectors.toList());
        List<NozTankRelationEntity> needChangeLists = baseMapper.selectBatchIds(integerList);
        //传过来的参数可能包含未关联过的（需关联），还有已经关联过的（需改变关联，并生成新的关联数据）
        List<NozTankRelationEntity> tanknoNotEmptys = new ArrayList<>();
        List<NozTankRelationEntity> tanknoEmptys = new ArrayList<>();
        for(NozTankRelationEntity list:needChangeLists){
            if(list.getTankNo()==null){
                tanknoEmptys.add(list);
            }
            else{
                tanknoNotEmptys.add(list);
            }
        }
        //已关联的油罐
        if(tanknoNotEmptys.size()!=0){
            List<NozTankRelationEntity> tempList = nozTankRelationEntityLists;
            //给已关联的油罐设置关联关系，结束时间赋值
            tanknoNotEmptys.stream().forEach(x-> x.setEndTime(now));
            this.updateBatchById(tanknoNotEmptys);
            //生成新的数据，开始时间为当前时间，结束时间为空
            List<NozTankRelationEntity> result = new ArrayList<>();
            for(NozTankRelationEntity list:tanknoNotEmptys) {
                tempList.stream().filter(x->x.getNozzleCode()==list.getNozzleCode())
                        .filter(x->list.getTankNo()!=null).forEach(x->{
                    NozTankRelationEntity nozTankRelationEntity = new NozTankRelationEntity();
                    nozTankRelationEntity.setNozzleCode(x.getNozzleCode());
                    nozTankRelationEntity.setTankNo(x.getTankNo());
                    nozTankRelationEntity.setEndTime(null);
                    nozTankRelationEntity.setStartTime(now);
                    result.add(nozTankRelationEntity);
                });
            }
            this.saveBatch(result);
        }
        //未关联的油罐
        if(tanknoEmptys.size()!=0){
            for(NozTankRelationEntity list:nozTankRelationEntityLists) {
                tanknoEmptys.stream().filter(x->list.getNozzleCode()==x.getNozzleCode()).forEach(x -> {x.setStartTime(now);x.setTankNo(list.getTankNo());});
            }
            this.updateBatchById(tanknoEmptys);
        }
        //修改noz表
        nozTankRelationEntityLists.stream().forEach(x->{
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("nozzle_no",x.getNozzleCode());
            wrapper.set("tank_no",x.getTankNo());
            wrapper.set("oil_code",x.getOilCode());
            wrapper.set("oil_name",x.getOilName());
            nozzle4DeviceService.update(wrapper);
        });
        //枪罐改变通知处理模块
        List<Integer> codeLists = nozTankRelationEntityLists.stream().map(NozTankRelationEntity::getNozzleCode).collect(Collectors.toList());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("nozzle_no",codeLists);
        balanceService.nozzleRelTankChanged(nozzle4DeviceService.list(queryWrapper));
    }

    @Override
    public void cancelRelation(List<NozTankRelationEntity> nozTankRelationEntityLists){
        Date now = new Date();
        List<Integer> integerList = nozTankRelationEntityLists.stream().map(NozTankRelationEntity::getId).collect(Collectors.toList());
        List<NozTankRelationEntity> needChangeLists = baseMapper.selectBatchIds(integerList);
        //将原有数据的结束时间赋值
        needChangeLists.stream().forEach(x-> x.setEndTime(now));
        this.updateBatchById(needChangeLists);
        //生成新的数据未关联的数据，除油枪编号外其他都为空
        nozTankRelationEntityLists.stream().forEach(x-> {x.setId(null);x.setTankNo(null);x.setEndTime(null);x.setStartTime(null);});
        this.saveBatch(nozTankRelationEntityLists);

        //修改noz表
        nozTankRelationEntityLists.stream().forEach(x->{
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("nozzle_no",x.getNozzleCode());
            wrapper.set("tank_no",null);
            wrapper.set("oil_code",null);
            wrapper.set("oil_name",null);
            nozzle4DeviceService.update(wrapper);
        });
    }
}
