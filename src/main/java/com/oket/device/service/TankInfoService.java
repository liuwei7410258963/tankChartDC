package com.oket.device.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.common.base.BaseService;
import com.oket.device.TankInfo;
import com.oket.tankchartdc.mina.json.bean.TankRelNozzle;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 油罐服务类
 * @author lw
 * @since 2019-12-12
 */
public interface TankInfoService extends IService<TankInfo> {

	/*
	 * 查询油罐信息(包含枪罐关系)
	 */
	List<TankInfo> query(JSONObject jsonObject);

	/*
	 * 查询油罐信息(不包含枪罐关系)
	 */
	List<TankInfo> queryTank(JSONObject jsonObject);


	/**
	 * 油罐是否存在
	 *
	 * @param tankInfo
	 * @return
	 */
	JSONObject queryExistTank(TankInfo tankInfo);

	/**
	 * 删除油罐
	 *
	 * @param query
	 */
	void delete(QueryWrapper<TankInfo> query);

	/**
	 * 修改油罐列表状态
	 *
	 * @param tankList
	 */
	void updateStatus(List<TankInfo> tankList);


	/**
	 * 保存油罐信息
	 * @param jsonObject
	 * @return
	 */
	TankInfo save(JSONObject jsonObject);


	/**
	 * 更新油罐信息
	 * @param jsonObject
	 * @return
	 */
	TankInfo update(JSONObject jsonObject);

	/**
	 * 获取油罐信息
	 * @param tankNo
	 * @return
	 */
	TankInfo getTankInfo(int tankNo);

	/**
	 * 获取所有的油罐信息
	 * @return
	 */
	List<TankInfo> getAllTank();

	/**
	 * 处理枪罐关系时更新油枪的油品
	 * @param tankInfo
	 */
	void updateNozzleOil(TankInfo tankInfo);
}
