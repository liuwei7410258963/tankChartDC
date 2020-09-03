package com.oket.device.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.common.base.BaseService;
import com.oket.device.Product;

import java.util.List;

/**
 * 产品服务类
 * @author lw
 * @since 2019-12-12
 */
public interface ProductService extends BaseService<Product> {

	/**
	 * 查询是否存在
	 *
	 * @param product
	 * @return
	 */
	boolean queryExistDevice(Product product);

	/**
	 * 通过Ids获取所有产品
	 *
	 * @param deviceIds
	 * @return
	 */
	List<Product> getDeviceListByIds(List<Integer> deviceIds);
}
