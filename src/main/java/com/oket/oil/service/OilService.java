package com.oket.oil.service;

import com.oket.common.base.BaseService;
import com.oket.oil.OilEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-14
 */
public interface OilService extends BaseService<OilEntity> {


	/**
	 * 查询油品是否存在
	 *
	 * @param oilEntity
	 * @return
	 */
	boolean queryExistOil(OilEntity oilEntity);

	/**
	 * 通过Code查询油品
	 *
	 * @param oilCode
	 * @return
	 */
	OilEntity getOilByCode(String oilCode);

	/**
	 * 当系统中没有找到油品信息的时候，新建油品信息
	 *
	 * @param oilCode
	 * @return
	 */
	OilEntity saveNewOil(String oilCode);
}
