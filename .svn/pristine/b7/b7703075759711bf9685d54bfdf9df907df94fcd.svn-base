package com.oket.tankchartdc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.common.base.BaseService;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tankchartdc.BzTraceRelOutGroup;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

/**
 * @description: 液位轨迹关联付油组Service
 * @author: SunBiaoLong
 * @create: 2020-04-14 14:34
 **/
public interface BzTraceRelOutGroupService extends IService<BzTraceRelOutGroup> {

	/**
	 * 确认与重组
	 *
	 * @param bzTraceRelOutGroupList
	 */
	void edit(List<BzTraceRelOutGroup> bzTraceRelOutGroupList);

	/**
	 * 根据液位轨迹的id获取正在使用的关联关系
	 *
	 * @param traceIds
	 * @return
	 */
	List<BzTraceRelOutGroup> getByTraceIds(List<Long> traceIds);

	/**
	 * 获取轨迹对应的付油组信息
	 *
	 * @param traceId
	 * @return
	 */
	BzTraceRelOutGroup getByTraceId(Long traceId);

	/**
	 * 查询轨迹对应的付油组信息
	 */
	JSONObject select(JSONObject jsonObject);

	/**
	 * 导出轨迹对应的付油组信息
	 */
	List<BzTraceRelOutGroup> export(JSONObject jsonObject);

	/**
	 * 合并关联关系
	 * @param bzTraceRelOutGroupList
	 * @param dbInventoryTrace
	 */
	void  mergeRel(List<BzTraceRelOutGroup> bzTraceRelOutGroupList, DbInventoryTrace dbInventoryTrace);
}
