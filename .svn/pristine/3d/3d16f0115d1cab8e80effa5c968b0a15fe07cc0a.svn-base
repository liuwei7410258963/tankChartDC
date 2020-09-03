package com.oket.dispenser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.config.exception.CommonJsonException;
import com.oket.config.spring.SpringUtil;
import com.oket.device.Nozzle4Device;
import com.oket.device.cache.DeviceCacheManager;
import com.oket.device.service.DeviceService;
import com.oket.device.service.IFileProcess;
import com.oket.device.service.Nozzle4DeviceService;
import com.oket.device.service.impl.FileProcess;
import com.oket.dispenser.DispenserErrorItem;
import com.oket.dispenser.dao.DispenserErrorItemDAO;
import com.oket.dispenser.service.DispenserErrorItemService;
import com.oket.util.TimeUtils;
import com.oket.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 油枪精度service
 * @author: SunBiaoLong
 * @create: 2020-02-17 15:23
 **/
@Service
public class DispenserErrorItemServiceImpl extends ServiceImpl<DispenserErrorItemDAO, DispenserErrorItem> implements DispenserErrorItemService {
	@Autowired
	private Nozzle4DeviceService nozzle4DeviceService;
	@Autowired
	private DeviceService deviceService;

	@Override
	public List<DispenserErrorItem> getByNozzleNo(int nozzleNo) {
		QueryWrapper<DispenserErrorItem> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(DispenserErrorItem::getNozzleNo, nozzleNo);
		wrapper.lambda().orderByAsc(DispenserErrorItem::getSequence);
		return baseMapper.selectList(wrapper);
	}


	@Override
	public DispenserErrorItem getUsedByNozzle(int nozzleNo) {
		QueryWrapper<DispenserErrorItem> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(DispenserErrorItem::getNozzleNo, nozzleNo);
		wrapper.lambda().isNull(DispenserErrorItem::getEndDate);
		return baseMapper.selectOne(wrapper);
	}

	public List<DispenserErrorItem> getUsedByNozzleNos(List<Integer> nozzleNos) {
		QueryWrapper<DispenserErrorItem> wrapper = new QueryWrapper<>();
		wrapper.lambda().in(DispenserErrorItem::getNozzleNo, nozzleNos);
		wrapper.lambda().isNull(DispenserErrorItem::getEndDate);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public void saveDispenserErrorItem(DispenserErrorItem dispenserErrorItem) {
		if (dispenserErrorItem == null
				|| dispenserErrorItem.getNozzleNo() == null
				|| dispenserErrorItem.getFromDate() == null) {
			throw new CommonJsonException(ErrorEnum.E_1001);
		} else {
			final List<DispenserErrorItem> byNozzleNo = getByNozzleNo(dispenserErrorItem.getNozzleNo());
			if (byNozzleNo == null || byNozzleNo.isEmpty()) {
//				序号从0开始
				dispenserErrorItem.setSequence(0);
				baseMapper.insert(dispenserErrorItem);

			} else {
				final DispenserErrorItem last = byNozzleNo.get(byNozzleNo.size() - 1);
				if (last.equals(dispenserErrorItem)) {
					//如果属性没有变那么不更新
					return;
				}
				if (dispenserErrorItem.getFromDate().equals(last.getFromDate())) {
					dispenserErrorItem.setId(last.getId());
					baseMapper.updateById(dispenserErrorItem);
				} else if (!dispenserErrorItem.getFromDate().after(last.getFromDate())) {
					throw new CommonJsonException(ErrorEnum.E_70016);
				} else {
					last.setEndDate(dispenserErrorItem.getFromDate());
//					序号加1
					dispenserErrorItem.setSequence(last.getSequence() + 1);
					baseMapper.updateById(last);
					baseMapper.insert(dispenserErrorItem);
				}
			}
		}
	}





	@Override
	public IPage<DispenserErrorItem> query(JSONObject req, boolean isPage) {
		// 参数根据需要判断来写
		IPage<DispenserErrorItem> page = new Page<>(req.getIntValue("pageNum"), req.getIntValue("pageRow"));
		//查询条件 根据需要写
		QueryWrapper<DispenserErrorItem> wrapper = new QueryWrapper<>();
		//查询条件
		if (req.getString("fromDate") != null && req.getString("endDate") != null) {
			try {
				wrapper.lambda().ge(DispenserErrorItem::getFromDate, TimeUtils.parseToSecond(req.getString("fromDate")));
				wrapper.lambda().le(DispenserErrorItem::getEndDate, TimeUtils.parseToSecond(req.getString("endDate")));
			} catch (ParseException e) {
				log.error("解析时间失败", e);
			}
		}
		if (req.getString("nozzleNo") != null) {
			wrapper.lambda().eq(DispenserErrorItem::getNozzleNo, req.getString("nozzleNo"));
		}
		wrapper.lambda().orderByDesc(DispenserErrorItem::getFromDate);
		// 分页的数据
		if (isPage) {
			page = baseMapper.selectPage(page, wrapper);
		} else {
			//不分页的数据
			page.setRecords(baseMapper.selectList(wrapper));
		}
		return page;
	}
}
