package com.oket.dispenser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.common.base.BaseService;
import com.oket.dispenser.DispenserErrorItem;

import java.util.List;

/**
 * @description: 油枪精度service
 * @author: SunBiaoLong
 * @create: 2020-02-17 15:21
 **/
public interface DispenserErrorItemService extends BaseService<DispenserErrorItem> {
	/**
	 * 根据油枪编号获取油枪精度
	 * @param nozzleNo
	 * @return
	 */
	List<DispenserErrorItem> getByNozzleNo(int nozzleNo);

	/**
	 * 获取正在使用油枪精度
	 * @param nozzleNo
	 * @return
	 */
	DispenserErrorItem getUsedByNozzle(int nozzleNo);

	/**
	 * 保存油枪精度
	 * @param dispenserErrorItem
	 */
	void saveDispenserErrorItem(DispenserErrorItem dispenserErrorItem);
}
