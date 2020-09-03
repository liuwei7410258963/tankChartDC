package com.oket.tank4station.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseService;
import com.oket.tank4station.InventoryException;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tank4station.service.vo.InventoryTraceMergeVO;
import com.oket.tank4station.service.vo.InventoryTraceVO;

import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * @description: 液位轨迹service
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:09
 **/
public interface DbInventoryTraceService extends BaseService<DbInventoryTrace> {
	/**
	 * 获取日期交集内的所有轨迹数据
	 *
	 * @return
	 */
	JSONObject getTrace(JSONObject jsonObject);
	/**
	 * 获取之前一笔轨迹
	 *
	 * @param time
	 * @param tankNo
	 * @return
	 */
	DbInventoryTrace getBeforeTrace(Date time, int tankNo);

	/**
	 * 获取之前一笔轨迹
	 *
	 * @param time
	 * @param tankNo
	 * @return
	 */
	DbInventoryTrace getNextTrace(Date time, int tankNo);

	/**
	 * 获取最后一笔轨迹信息
	 *
	 * @param tankNo
	 * @return
	 */
	DbInventoryTrace getLastTrace(int tankNo);

	/**
	 * 合并拆分液位组
	 *
	 * @param traceMergeVO
	 */
	void merge(InventoryTraceMergeVO traceMergeVO) throws InventoryException;


	/**
	 * 获取轨迹
	 *
	 * @param startTime
	 * @param endTime
	 * @param tankNo
	 * @return
	 */
	List<DbInventoryTrace> getTrace(Date startTime, Date endTime, int tankNo);

	/**
	 * 获取轨迹
	 *
	 * @param startTime
	 * @param endTime
	 * @param tankNo
	 * @param descByStartTime true->倒序，false->顺序
	 * @return
	 */
	List<DbInventoryTrace> getTrace(Date startTime, Date endTime, int tankNo, boolean descByStartTime);


	/**
	 * 获取轨迹信息以及其中包含的液位
	 *
	 * @param traceIds
	 * @return
	 */
	List<InventoryTraceVO> queryInventories(List<Long> traceIds);

	/**
	 * 获取所有罐的最后一笔轨迹信息
	 *
	 * @return
	 */
	List<DbInventoryTrace> getAllLastTrace();

	/**
	 * 合并液位轨迹
	 *
	 * @param traceList
	 */
	void merge(List<DbInventoryTrace> traceList);

	/**
	 * 获取开始和结束罐存
	 * @param lastTrace
	 */
	void getStartAndEndInv(DbInventoryTrace lastTrace);
}
