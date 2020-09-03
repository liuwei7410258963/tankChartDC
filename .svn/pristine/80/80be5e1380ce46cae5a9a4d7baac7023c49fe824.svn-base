package com.oket.dispenser.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseService;
import com.oket.dispenser.BzNozzleOut;
import com.oket.dispenser.BzNozzleOutLast;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Observer;


/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-21
 */
public interface NozzleOutService extends BaseService<BzNozzleOut> {

	/**
	 * 各个油品的汇总
	 *
	 * @param jsonObject
	 * @return
	 */
	JSONObject getOilSum(JSONObject jsonObject);

	/**
	 * 获取最后的销售数据
	 *
	 * @return
	 */
	Collection<BzNozzleOutLast> getNozzleOutLast();

	/**
	 * 处理来自dit的付油数据
	 *
	 * @param bzNozzleOut
	 */
	void processNozzleOutFromDit(BzNozzleOut bzNozzleOut);

	/**
	 *查询一段时间内的付油数据
	 * @param nozzleNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<BzNozzleOut> getByTimeAndNozzleNo(String nozzleNo, Date startTime, Date endTime);
	/**
	 * 获取没有匹配的付油信息
	 * @return
	 */
	List<BzNozzleOut> getNotMatched();
	/**
	 * 添加观察者
	 * @param observer
	 */
	void addObserver(Observer observer);
}
