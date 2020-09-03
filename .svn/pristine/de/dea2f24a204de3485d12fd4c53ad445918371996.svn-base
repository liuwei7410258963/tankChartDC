package com.oket.dispenser.service;

import com.oket.common.base.BaseService;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.dispenser.BzNozzleOut;

import java.util.Collection;
import java.util.List;

/**
 * @description: 付油记录组service
 * @author: SunBiaoLong
 * @create: 2020-04-03 09:31
 **/
public interface BzNozzleOutGroupService extends BaseService<BzNozzleOutGroup> {


	/**
	 * 处理付油信息,整理成付油组
	 *
	 * @param nozzleOuts
	 */
	BzNozzleOutGroup buildOutGroup(List<BzNozzleOut> nozzleOuts);

	/**
	 * 单条数据整合
	 *
	 * @param bzNozzleOut
	 * @param bzNozzleOutGroup
	 */
	void oneGroupMerge(BzNozzleOut bzNozzleOut, BzNozzleOutGroup bzNozzleOutGroup);

	/**
	 * 多组合并
	 *
	 * @param bzNozzleOut
	 * @param groups
	 */
	void manyGroupMerge(BzNozzleOut bzNozzleOut, List<BzNozzleOutGroup> groups);

	/**
	 * 多组合并

	 * @param groups
	 */
	void manyGroupMerge( List<BzNozzleOutGroup> groups);

}
