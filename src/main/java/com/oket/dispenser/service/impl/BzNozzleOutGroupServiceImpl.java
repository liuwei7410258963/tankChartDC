package com.oket.dispenser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.common.base.Status;
import com.oket.dispenser.BzNozzleOut;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.dispenser.dao.BzNozzleOutGroupDAO;
import com.oket.dispenser.service.BzNozzleOutGroupService;
import com.oket.dispenser.service.NozzleOutRelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 付油记录组service
 * @author: SunBiaoLong
 * @create: 2020-04-03 09:34
 **/
@Service
public class BzNozzleOutGroupServiceImpl extends ServiceImpl<BzNozzleOutGroupDAO, BzNozzleOutGroup> implements BzNozzleOutGroupService {
	@Autowired
	private NozzleOutRelGroupService nozzleOutRelGroupService;

	@Override

	public IPage<BzNozzleOutGroup> query(JSONObject req, boolean isPage) {
		QueryWrapper<BzNozzleOutGroup> qw = new QueryWrapper<>();
		if (req.getInteger("tankNo") != null) {
			qw.lambda().eq(BzNozzleOutGroup::getTankNo, req.getInteger("tankNo"));
		}
		if (req.get("startTime") != null) {
			qw.lambda().ge(BzNozzleOutGroup::getStartTime, req.get("startTime"));
		}
		if (req.get("endTime") != null) {
			qw.lambda().le(BzNozzleOutGroup::getEndTime, req.get("endTime"));
		}
		qw.lambda().orderByAsc(BzNozzleOutGroup::getStartTime);
		IPage<BzNozzleOutGroup> page = new Page<>();
		if (isPage) {
			return baseMapper.selectPage(page, qw);
		} else {
			page.setRecords(baseMapper.selectList(qw));
			return page;
		}
	}


	/**
	 * 处理付油信息,整理成付油组
	 *
	 * @param nozzleOuts
	 */
	@Override
	public BzNozzleOutGroup buildOutGroup(List<BzNozzleOut> nozzleOuts) {
		BzNozzleOutGroup bzNozzleOutGroup = null;
		for (BzNozzleOut nozzleOut : nozzleOuts) {
			if (bzNozzleOutGroup == null) {
				bzNozzleOutGroup = createNewGroup(nozzleOut);
			} else {
				oneGroupMerge(nozzleOut, bzNozzleOutGroup);
			}
		}
		return bzNozzleOutGroup;
	}


	/**
	 * 多条数据整合为数据
	 *
	 * @param bzNozzleOut
	 * @param groups
	 */
	@Override
	public void manyGroupMerge(BzNozzleOut bzNozzleOut, List<BzNozzleOutGroup> groups) {
		if (bzNozzleOut == null || groups.size() == 0) {
			return;
		}
		BzNozzleOutGroup group = merge(groups);
		assert group != null;
		group.addNozzleOut(bzNozzleOut);
		//更新数据
		this.updateBatchById(groups);
	}

	/**
	 * 多组合并
	 * @param groups
	 * @return
	 */
	private BzNozzleOutGroup merge(List<BzNozzleOutGroup> groups) {
		log.debug("多组合并");
		BzNozzleOutGroup group = null;
		for (int i = 0; i < groups.size(); i++) {
			final BzNozzleOutGroup bzNozzleOutGroup = groups.get(i);
			if (i == 0) {
				group = bzNozzleOutGroup;
			} else {
				bzNozzleOutGroup.setStatus(Status.DISABLE);
				group.mergeGroup(bzNozzleOutGroup);
				nozzleOutRelGroupService.updateRel(bzNozzleOutGroup.getId(), group.getId());
			}
		}
		return group;
	}

	@Override
	public void manyGroupMerge(List<BzNozzleOutGroup> groups) {
		merge(groups);
		//更新数据
		this.updateBatchById(groups);
	}

	/**
	 * 单条数据整合
	 *
	 * @param bzNozzleOut
	 * @param bzNozzleOutGroup
	 */
	@Override
	public void oneGroupMerge(BzNozzleOut bzNozzleOut, BzNozzleOutGroup bzNozzleOutGroup) {
		log.debug("单组合并");
		oneGroupMerge(bzNozzleOut, bzNozzleOutGroup, false);
	}

	/**
	 * 单条数据整合
	 *
	 * @param bzNozzleOut
	 * @param bzNozzleOutGroup
	 * @param needConfirm      是否需要确认
	 */
	private void oneGroupMerge(BzNozzleOut bzNozzleOut, BzNozzleOutGroup bzNozzleOutGroup, boolean needConfirm) {
		bzNozzleOutGroup.addNozzleOut(bzNozzleOut);
		bzNozzleOutGroup.setNeedConfirm(needConfirm);
		this.saveOrUpdate(bzNozzleOutGroup);
		//新建关联关系
		nozzleOutRelGroupService.newRel(bzNozzleOut, bzNozzleOutGroup);
	}

	/**
	 * 1.创建新的付油记录组
	 * 2.关联液位数据
	 *
	 * @param bzNozzleOut
	 * @return
	 */
	public BzNozzleOutGroup createNewGroup(BzNozzleOut bzNozzleOut) {
		log.debug("创建新的付油记录组");
		//加入缓存
		return createNewGroup(bzNozzleOut, false);
	}

	/**
	 * 1.创建新的付油记录组
	 * 2.关联液位数据
	 *
	 * @param bzNozzleOut
	 * @return
	 */
	public BzNozzleOutGroup createNewGroup(BzNozzleOut bzNozzleOut, boolean needConfirm) {
		BzNozzleOutGroup bzNozzleOutGroup
				= new BzNozzleOutGroup(bzNozzleOut.getTankNo()
				, bzNozzleOut.getStartTime()
				, bzNozzleOut.getEndTime()
				, bzNozzleOut.getVolume()
				, 1);
		bzNozzleOutGroup.setNeedConfirm(needConfirm);
		this.save(bzNozzleOutGroup);
		nozzleOutRelGroupService.newRel(bzNozzleOut, bzNozzleOutGroup);
		return bzNozzleOutGroup;
	}


}
