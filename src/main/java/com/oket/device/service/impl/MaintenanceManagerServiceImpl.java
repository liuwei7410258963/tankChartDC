package com.oket.device.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.common.base.Status;
import com.oket.device.MaintenanceManager;
import com.oket.device.dao.MaintenanceManagerDAO;
import com.oket.device.service.MaintenanceManagerService;
import com.oket.util.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-15
 */
@Service
public class MaintenanceManagerServiceImpl extends ServiceImpl<MaintenanceManagerDAO, MaintenanceManager> implements MaintenanceManagerService {
	//不需要分页查询
	@Value("${noPage}")
	private int noPage;

	@Override
	public Page<MaintenanceManager> query(JSONObject req, boolean isPage) {
		List<MaintenanceManager> maintinManagerEntities = new ArrayList<>();
		//查询条件 根据需要写
		QueryWrapper<MaintenanceManager> wrapper = new QueryWrapper<>();
		// 参数根据需要判断来写
		Page<MaintenanceManager> page = new Page<>(req.getIntValue("pageNum"), req.getIntValue("pageRow"));
		if (req.getString("name") != null) {
			wrapper.lambda().like(MaintenanceManager::getName, req.getString("name"));
		}
		//不分页查询
		if (req.getIntValue("noPage") == noPage) {
			//只查启用的
			wrapper.lambda().eq(MaintenanceManager::getStatus, Status.ENABLE);
			maintinManagerEntities = baseMapper.selectList(wrapper);
		} // 分页的数据
		else if (isPage) {
			maintinManagerEntities = baseMapper.selectPageExt(page, wrapper);
		} else {
			//不分页的数据
			maintinManagerEntities = baseMapper.selectPageExt(null, wrapper);
		}
		page.setRecords(maintinManagerEntities);
		return page;
	}


	@Override
	public boolean queryExistMaintenanceManager(MaintenanceManager maintenanceManager) {
		QueryWrapper<MaintenanceManager> queryWrapper = new QueryWrapper<>();
		//运维单位名称
		if (maintenanceManager.getName() != null) {
			queryWrapper.lambda().eq(MaintenanceManager::getName, maintenanceManager.getName());
		}
		maintenanceManager = baseMapper.selectOne(queryWrapper);
		if (maintenanceManager == null) {
			return true;
		}
		return false;
	}
}
