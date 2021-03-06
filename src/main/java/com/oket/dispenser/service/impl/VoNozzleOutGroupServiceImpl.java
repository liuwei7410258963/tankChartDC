package com.oket.dispenser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.common.base.Status;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.dispenser.NozzleOutRelGroup;
import com.oket.dispenser.VoNozzleOutRelGroup;
import com.oket.dispenser.dao.BzNozzleOutGroupDAO;
import com.oket.dispenser.dao.VoNozzleOutGroupDao;
import com.oket.dispenser.service.BzNozzleOutGroupService;
import com.oket.dispenser.service.NozzleOutRelGroupService;
import com.oket.dispenser.service.VoNozzleOutGroupService;
import com.oket.tank4station.dao.DbInventoryTraceDAO;
import com.oket.tankchartdc.BzTraceRelOutGroup;
import com.oket.tankchartdc.NozzleOutGroupProcessor;
import com.oket.tankchartdc.dao.BzTraceRelOutGroupDAO;
import com.oket.util.AirUtils;
import com.oket.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-04-14
 */
@Service
public class VoNozzleOutGroupServiceImpl extends ServiceImpl<VoNozzleOutGroupDao, BzNozzleOutGroup> implements VoNozzleOutGroupService {

    @Autowired
    VoNozzleOutGroupDao voNozzleOutGroupDao;
    @Autowired
    NozzleOutRelGroupService nozzleOutRelGroupService;
    @Autowired
    BzNozzleOutGroupService bzNozzleOutGroupService;
    @Autowired
    BzNozzleOutGroupDAO bzNozzleOutGroupDAO;
    @Autowired
    BzTraceRelOutGroupDAO bzTraceRelOutGroupDAO;
    @Override
    public IPage<BzNozzleOutGroup> query(JSONObject req, boolean isPage) {
        IPage<BzNozzleOutGroup> page = new Page<>(req.getIntValue("pageNum"), req.getIntValue("pageRow"));
        QueryWrapper<BzNozzleOutGroup> wrapper = new QueryWrapper<>();
        if (req.getInteger("tankNo") != null) {
            wrapper.lambda().eq(BzNozzleOutGroup::getTankNo, req.getInteger("tankNo"));
        }
        wrapper.lambda().eq(BzNozzleOutGroup::getStatus, "1");
        if (req.get("startTime") != null && req.get("endTime") != null) {
            wrapper.lambda().ge(BzNozzleOutGroup::getStartTime, req.getString("startTime"))
                    .le(BzNozzleOutGroup::getEndTime, req.getString("endTime"));
        }
        wrapper.eq("status", 1);
        wrapper.orderByDesc("start_time");
        if (isPage) {
            return voNozzleOutGroupDao.selectPage(page, wrapper);
        } else {
            final List<BzNozzleOutGroup> bzDeliveries = voNozzleOutGroupDao.selectList(wrapper);
            page.setRecords(bzDeliveries);
            return page;
        }
    }

    @Override
    public List<VoNozzleOutRelGroup> selectNozzleOutGroup(JSONObject jsonObject) {
        List<VoNozzleOutRelGroup> results = new ArrayList<>();
        String ids = jsonObject.getString("ids");

        //拆分合并一条数据，查询出时间最邻近的数据
        if (!ids.contains(",")) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", ids);
            BzNozzleOutGroup bzNozzleOutGroup = bzNozzleOutGroupDAO.selectOne(queryWrapper);
            List<Integer> groupIds = voNozzleOutGroupDao.selectPreAndNext(bzNozzleOutGroup);
            if (groupIds != null) {
                for (Integer id : groupIds) {
                    ids += "," + id;
                }
            }
        }
        jsonObject.put("ids", ids);
        List<VoNozzleOutRelGroup> dblists = voNozzleOutGroupDao.selectNozzleOutGroup(jsonObject);
        Set<Integer> tempSet = new HashSet<>();
        for (VoNozzleOutRelGroup list : dblists) {
            if (tempSet.contains(list.getGroupId())) {
                results.stream().filter(x -> x.getGroupId() == list.getGroupId()).forEach(x -> {
                    x.getBzNozzleOuts().add(list.getBzNozzleOuts().get(0));
                });
            } else {
                VoNozzleOutRelGroup voNozzleOutRelGroup = new VoNozzleOutRelGroup();
                voNozzleOutRelGroup.setGroupRelTraceId(list.getGroupRelTraceId());
                voNozzleOutRelGroup.setTraceId(list.getTraceId());
                voNozzleOutRelGroup.setGroupId(list.getGroupId());
                voNozzleOutRelGroup.setBzNozzleOuts(list.getBzNozzleOuts());
                voNozzleOutRelGroup.setStatus(list.getStatus());
                results.add(voNozzleOutRelGroup);
            }
        }
        return results;
    }

    @Override
    public void update(List<NozzleOutRelGroup> relGroupLists) {
        List<NozzleOutRelGroup> updateRelResults = new ArrayList<>();
        for (NozzleOutRelGroup nozzleOutRelGroup : relGroupLists) {
            NozzleOutGroupProcessor.checkMerge(nozzleOutRelGroup.getTraceId());
        }

        //组里没有付油数据，在list最前面（需禁用）
        //组的关联关系改了，在list最后面（需合并）
        List<NozzleOutRelGroup> lists = new ArrayList<>();
        relGroupLists.stream().filter(x->x.getIds().equals("")).forEach(x-> lists.add(x));
        relGroupLists.stream().filter(x->!x.getIds().equals(""))
                .filter(x->x.getOldTraceId()==0)
                .forEach(x-> lists.add(x));
        relGroupLists.stream().filter(x->!x.getIds().equals(""))
                .filter(x->x.getOldTraceId()!=0)
                .forEach(x-> lists.add(x));
        for (NozzleOutRelGroup list : lists) {
            //更新
            if (AirUtils.hv(list.getGroupId())) {
                //组里没有付油数据了,付油组设置为禁用
                if (list.getIds().equals("")) {
                    forbiddenGroup(list);
                }
                //组里还有付油数据
                else {
                    //付油组和轨迹关系处理
                    setNewGroupAndTraceRel(updateRelResults, list);
                }
            }
            //新增付油组
            else{
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("id", list.getRelGroupId());
                BzTraceRelOutGroup bzTraceRelOutGroup = bzTraceRelOutGroupDAO.selectOne(queryWrapper);
                //该轨迹已经关联过付油组，需要将新建的这一条付油组和已经有关联的付油组合并
                if (AirUtils.hv(list.getRelGroupId())&&bzTraceRelOutGroup.getStatus()==Status.ENABLE) {
                    mergeGroup(updateRelResults, list,bzTraceRelOutGroup);
                }
                //该轨迹没有关联过付油组，直接关联
                else {
                    setRelation(updateRelResults, list);
                }
            }
        }
        if (updateRelResults.size() != 0) {
            nozzleOutRelGroupService.updateBatchById(updateRelResults);
        }
    }

    private void setNewGroupAndTraceRel(List<NozzleOutRelGroup> updateRelResults, NozzleOutRelGroup list) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("out_group_id",list.getGroupId());
        queryWrapper.eq("status",1);
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 1");
        BzTraceRelOutGroup bzTraceRelOutGroup = bzTraceRelOutGroupDAO.selectOne(queryWrapper);
        //该液位组没有与付油组匹配
        if(bzTraceRelOutGroup==null){
            //新建关联关系
            BzTraceRelOutGroup bzTraceRelOutGroup2 = new BzTraceRelOutGroup();
            bzTraceRelOutGroup2.setOutGroupId(list.getGroupId());
            bzTraceRelOutGroup2.setTraceId(list.getTraceId());
            bzTraceRelOutGroup2.setStatus(Status.ENABLE);
            bzTraceRelOutGroupDAO.insert(bzTraceRelOutGroup2);
        }
        //改了关联关系
        else if(!bzTraceRelOutGroup.getTraceId().equals(list.getTraceId())){
            List<String> arrs1 = Arrays.asList(list.getIds().substring(0, list.getIds().length() - 1).split(","));
            //查传过来的新轨迹关联的付油组
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("trace_id",list.getTraceId());
            queryWrapper2.eq("status",1);
            queryWrapper2.orderByDesc("id");
            queryWrapper2.last("limit 1");
            BzTraceRelOutGroup bzTraceRelOutGroup2 = bzTraceRelOutGroupDAO.selectOne(queryWrapper2);
            //该轨迹没有关联过付油组,直接关联
            if(bzTraceRelOutGroup2 == null){
                setGroupAndOutRel(updateRelResults, list);
                //设置该付油组关联的液位组为禁用
                BzTraceRelOutGroup bzTraceRelOutGroup1 = new BzTraceRelOutGroup();
                bzTraceRelOutGroup1.setStatus(Status.DISABLE);
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("trace_id", bzTraceRelOutGroup.getTraceId());
                bzTraceRelOutGroupDAO.update(bzTraceRelOutGroup1, updateWrapper);
                //新建关联关系
                BzTraceRelOutGroup bzTraceRelOutGroup3 = new BzTraceRelOutGroup();
                bzTraceRelOutGroup3.setOutGroupId(list.getGroupId());
                bzTraceRelOutGroup3.setTraceId(list.getTraceId());
                bzTraceRelOutGroup3.setStatus(Status.ENABLE);
                bzTraceRelOutGroup3.setTankNo(bzTraceRelOutGroup.getTankNo());
                bzTraceRelOutGroupDAO.insert(bzTraceRelOutGroup3);
            }
            //关联过了，需要合并付油组
            else{
                //该付油组禁用
                BzNozzleOutGroup needStopGroup = new BzNozzleOutGroup();
                needStopGroup.setId(list.getGroupId());
                needStopGroup.setStatus(Status.DISABLE);
                bzNozzleOutGroupService.updateById(needStopGroup);
                //设置该付油组关联的液位组为禁用
                BzTraceRelOutGroup bzTraceRelOutGroup1 = new BzTraceRelOutGroup();
                bzTraceRelOutGroup1.setStatus(Status.DISABLE);
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("trace_id", bzTraceRelOutGroup.getTraceId());
                bzTraceRelOutGroupDAO.update(bzTraceRelOutGroup1, updateWrapper);

                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("id", bzTraceRelOutGroup2.getOutGroupId());
                BzNozzleOutGroup needUpdateBzNozzleOutGroup = bzNozzleOutGroupDAO.selectOne(queryWrapper1);
                needUpdateBzNozzleOutGroup.setCount(needUpdateBzNozzleOutGroup.getCount() + arrs1.size());
                needUpdateBzNozzleOutGroup.setVolume(needUpdateBzNozzleOutGroup.getVolume() + list.getVolume());
                //取最小时间和最大时间
                if (list.getStartTime().getTime() < needUpdateBzNozzleOutGroup.getStartTime().getTime()) {
                    needUpdateBzNozzleOutGroup.setStartTime(list.getStartTime());
                }
                if (list.getEndTime().getTime() > needUpdateBzNozzleOutGroup.getEndTime().getTime()) {
                    needUpdateBzNozzleOutGroup.setEndTime(list.getEndTime());
                }
                this.updateById(needUpdateBzNozzleOutGroup);
                saveOutRelGroup(updateRelResults, arrs1, needUpdateBzNozzleOutGroup.getId());
            }
        }
        //没改关联关系
        else if(bzTraceRelOutGroup.getTraceId().equals(list.getTraceId())){
            //付油组和付油数据处理
            setGroupAndOutRel(updateRelResults, list);
        }
    }

    private void setGroupAndOutRel(List<NozzleOutRelGroup> updateRelResults, NozzleOutRelGroup list) {
        //付油组和付油数据处理
        BzNozzleOutGroup bzNozzleOutGroup = new BzNozzleOutGroup();
        bzNozzleOutGroup.setId(list.getGroupId().longValue());
        bzNozzleOutGroup.setVolume(list.getVolume());
        bzNozzleOutGroup.setStartTime(list.getStartTime());
        bzNozzleOutGroup.setEndTime(list.getEndTime());
        //付油组和付油关系处理
        List<String> arrs = Arrays.asList(list.getIds().substring(0, list.getIds().length() - 1).split(","));
        saveOutRelGroup(updateRelResults, arrs, list.getGroupId());
        bzNozzleOutGroup.setCount(arrs.size());
        this.updateById(bzNozzleOutGroup);
    }


    private void setRelation(List<NozzleOutRelGroup> updateRelResults, NozzleOutRelGroup list) {
        List<String> arrs = Arrays.asList(list.getIds().substring(0, list.getIds().length() - 1).split(","));
        BzNozzleOutGroup bzNozzleOutGroup = new BzNozzleOutGroup();
        bzNozzleOutGroup.setStatus(Status.ENABLE);
        bzNozzleOutGroup.setVolume(list.getVolume());
        bzNozzleOutGroup.setStartTime(list.getStartTime());
        bzNozzleOutGroup.setEndTime(list.getEndTime());
        bzNozzleOutGroup.setTankNo(list.getTankNo());
        bzNozzleOutGroup.setCount(arrs.size());
        this.save(bzNozzleOutGroup);
        //付油组和付油关系处理
        saveOutRelGroup(updateRelResults, arrs, bzNozzleOutGroup.getId());
        //保存关联关系
        BzTraceRelOutGroup bzTraceRelOutGroup = new BzTraceRelOutGroup();
        bzTraceRelOutGroup.setOutGroupId(bzNozzleOutGroup.getId());
        bzTraceRelOutGroup.setTraceId(list.getTraceId());
        bzTraceRelOutGroup.setTankNo(list.getTankNo());
        bzTraceRelOutGroupDAO.insert(bzTraceRelOutGroup);
    }

    private void mergeGroup(List<NozzleOutRelGroup> updateRelResults, NozzleOutRelGroup list,BzTraceRelOutGroup bzTraceRelOutGroup) {
        List<String> arrs = Arrays.asList(list.getIds().substring(0, list.getIds().length() - 1).split(","));
        //已关联的付油组
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("id", bzTraceRelOutGroup.getOutGroupId());
        BzNozzleOutGroup needUpdateBzNozzleOutGroup = bzNozzleOutGroupDAO.selectOne(queryWrapper1);
        needUpdateBzNozzleOutGroup.setCount(needUpdateBzNozzleOutGroup.getCount() + arrs.size());
        needUpdateBzNozzleOutGroup.setVolume(needUpdateBzNozzleOutGroup.getVolume() + list.getVolume());
        //取最小时间和最大时间
        if (list.getStartTime().getTime() < needUpdateBzNozzleOutGroup.getStartTime().getTime()) {
            needUpdateBzNozzleOutGroup.setStartTime(list.getStartTime());
        }
        if (list.getEndTime().getTime() > needUpdateBzNozzleOutGroup.getEndTime().getTime()) {
            needUpdateBzNozzleOutGroup.setEndTime(list.getEndTime());
        }
        this.updateById(needUpdateBzNozzleOutGroup);
        //付油组和付油关系处理
        saveOutRelGroup(updateRelResults, arrs, needUpdateBzNozzleOutGroup.getId());
    }

    private void forbiddenGroup(NozzleOutRelGroup list) {
        BzNozzleOutGroup bzNozzleOutGroup = new BzNozzleOutGroup();
        bzNozzleOutGroup.setId(list.getGroupId().longValue());
        bzNozzleOutGroup.setStatus(Status.DISABLE);
        bzNozzleOutGroup.setVolume(0.0);
        bzNozzleOutGroup.setCount(0);
        this.updateById(bzNozzleOutGroup);
        //设置该付油组关联的液位组为禁用
        BzTraceRelOutGroup bzTraceRelOutGroup = new BzTraceRelOutGroup();
        bzTraceRelOutGroup.setOutGroupId(list.getGroupId());
        bzTraceRelOutGroup.setStatus(Status.DISABLE);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("out_group_id", list.getGroupId());
        bzTraceRelOutGroupDAO.update(bzTraceRelOutGroup, updateWrapper);
    }

    private void saveOutRelGroup(List<NozzleOutRelGroup> updateRelResults, List<String> arrs, Long id) {
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.in("out_id", arrs);
        List<NozzleOutRelGroup> dbLists = nozzleOutRelGroupService.list(queryWrapper1);
        for (String outid : arrs) {
            dbLists.stream()
                    .filter(x -> (x.getOutId() + "").equals(outid))
                    .forEach(x -> x.setGroupId(id));
        }
        updateRelResults.addAll(dbLists);
    }

    @Override
    public List<VoNozzleOutRelGroup> selectNozs(JSONObject jsonObject) {
        return voNozzleOutGroupDao.selectNozzleOutGroup(jsonObject);
    }

    @Override
    public List<JSONObject> querytrace(JSONObject jsonObject) {
        List<JSONObject> resultLists = new ArrayList<>();
        //去重，同一个液位组可能关联过多个付油组
        HashSet<Integer> set = new HashSet<>();
        voNozzleOutGroupDao.querytrace(jsonObject).stream().forEach(x->{
            if(!set.contains(x.getIntValue("id"))){
                set.add(x.getIntValue("id"));
                resultLists.add(x);
            }
        });
        return resultLists;
    }

    @Override
    public JSONObject listNozGroupAndRelId(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject, 20);
        int count = voNozzleOutGroupDao.count(jsonObject);
        return CommonUtil.successPage(jsonObject, voNozzleOutGroupDao.listNozGroupAndRelId(jsonObject), count);
    }

}
