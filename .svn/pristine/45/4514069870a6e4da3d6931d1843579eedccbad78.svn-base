package com.oket.dispenser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.common.base.Status;
import com.oket.dispenser.*;
import com.oket.dispenser.dao.NozzleOutRelGroupDAO;
import com.oket.dispenser.service.NozzleOutRelGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 付油数据关联付油组Service
 * @author: SunBiaoLong
 * @create: 2020-04-03 11:27
 **/
@Service
public class NozzleOutRelGroupServiceImpl extends ServiceImpl<NozzleOutRelGroupDAO, NozzleOutRelGroup>
		implements NozzleOutRelGroupService {
	@Override
	public IPage<NozzleOutRelGroup> query(JSONObject req, boolean isPage) {
		return null;
	}

	@Override
	public List<NozzleOutRelGroup> getRel(Long groupId) {
		QueryWrapper<NozzleOutRelGroup> qw = new QueryWrapper<>();
		qw.lambda().eq(NozzleOutRelGroup::getGroupId, groupId);
		qw.lambda().eq(NozzleOutRelGroup::getStatus, Status.ENABLE);
		return list(qw);
	}

	@Override
	public void newRel(BzNozzleOut bzNozzleOut, BzNozzleOutGroup bzNozzleOutGroup) {
		NozzleOutRelGroup bzOutRelGroup = new NozzleOutRelGroup(bzNozzleOut.getId(), bzNozzleOutGroup.getId());
		this.save(bzOutRelGroup);
	}

	@Override
	public void updateRel(Long beforeGroupId, Long currentGroupId) {
		final List<NozzleOutRelGroup> rel = getRel(beforeGroupId);
		if (rel!=null&& !rel.isEmpty()){
			rel.parallelStream().forEach(v -> v.setGroupId(currentGroupId));
			updateBatchById(rel);
		}
	}
}
