package com.oket.device.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.device.Product;
import com.oket.device.dao.ProductDAO;
import com.oket.device.service.ProductService;
import com.oket.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-14
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDAO, Product> implements ProductService {

	@Override
	public IPage<Product> query(JSONObject jsonObject, boolean isPage) {
		IPage<Product> page = new Page<>(jsonObject.getIntValue("pageNum"), jsonObject.getIntValue("pageRow"));
		CommonUtil.fillPageParam(jsonObject);
		QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(jsonObject.getString("name"))) {
			queryWrapper.lambda().like(Product::getName, jsonObject.getString("name"));
		}
		if (jsonObject.getInteger("deviceType") != null) {
			queryWrapper.lambda().eq(Product::getDeviceType, jsonObject.getInteger("deviceType"));
		}
		if (StringUtils.isNotBlank(jsonObject.getString("productType"))) {
			queryWrapper.lambda().eq(Product::getProductType, jsonObject.getString("productType"));

		}
		if (isPage) {
			return baseMapper.selectPage(page, queryWrapper);
		} else {
			final List<Product> products = baseMapper.selectList(queryWrapper);
			return page.setRecords(products);
		}
	}

	@Override
	public boolean queryExistDevice(Product product) {
		QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
		Product product1 = null;
		//设备名
		if (product.getName() != null) {
			queryWrapper.lambda().eq(Product::getName, product.getName());
		}
		//设备型号
		if (product.getProductType() != null) {
			queryWrapper.lambda().eq(Product::getProductType, product.getProductType());
		}
		product1 = baseMapper.selectOne(queryWrapper);
		if (product1 == null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Product> getDeviceListByIds(List<Integer> deviceIds) {
		return baseMapper.selectBatchIds(deviceIds);
	}


}
