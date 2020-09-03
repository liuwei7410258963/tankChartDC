package com.oket.dispenser.service;

import com.oket.common.base.BaseService;
import com.oket.dispenser.BzNozzleOut;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.dispenser.NozzleOutRelGroup;

import java.util.List;

/**
 * @description: 付油数据关联付油组Service
 * @author: SunBiaoLong
 * @create: 2020-04-03 11:27
 **/
public interface NozzleOutRelGroupService extends BaseService<NozzleOutRelGroup> {
	/**
	 * @param bzNozzleOut
	 * @param bzNozzleOutGroup
	 */
	void newRel(BzNozzleOut bzNozzleOut, BzNozzleOutGroup bzNozzleOutGroup);

	/**
	 * 获取关联关系
	 *
	 * @param groupId
	 * @return
	 */
	List<NozzleOutRelGroup> getRel(Long groupId);


	/**
	 * 更新关联关系
	 *
	 * @param beforeGroupId
	 * @param currentGroupId
	 */
	void updateRel(Long beforeGroupId, Long currentGroupId);

}
