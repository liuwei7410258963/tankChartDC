package com.oket.tank4station.service;

import com.oket.common.base.BaseService;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.entity.DbInventoryAlarm;
import com.oket.tank4station.entity.DbInventoryLast;

import java.util.Date;
import java.util.List;


/**
 * @description: 液位报警service
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:09
 **/
public interface DbInventoryAlarmService extends BaseService<DbInventoryAlarm> {

	/**
	 * 保存过长时间开始时的数据
	 *
	 * @param inventory
	 */
	void saveLongTimeNoUpload(Inventory inventory, Date lastUploadTime);

	/**
	 * 结束长时间没有数据上传
	 * 当有新数据上传的时候
	 *
	 * @param inventory
	 */
	void endLongTimeNoUpload(Inventory inventory);

	/**
	 * 初始化处理第一笔罐存
	 *
	 * @param inventory
	 */
	void firstProcessLongTimeNoUpload(Inventory inventory);


	/**
	 *
	 * @param lastList
	 */
	void initAlarm(List<DbInventoryLast> lastList);
}
