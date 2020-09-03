package com.oket.device.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.device.NozRelDisEntity;
import com.oket.device.NozTankRelationEntity;
import com.oket.device.Nozzle4Device;
import com.oket.device.dao.NozRelDisDao;
import com.oket.device.dao.NozTankRelationDao;
import com.oket.device.dao.Nozzle4DeviceDAO;
import com.oket.device.service.NozTankRelationService;
import com.oket.device.service.Nozzle4DeviceService;
import com.oket.dispenser.service.DispenserErrorItemService;
import com.oket.tankchartdc.mina.json.bean.TankRelNozzle;
import com.oket.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 油枪设备service
 * @author: SunBiaoLong
 * @create: 2019-12-09 17:19
 **/
@Service
public class Nozzle4DeviceServiceImpl extends ServiceImpl<Nozzle4DeviceDAO, Nozzle4Device> implements Nozzle4DeviceService {

	@Autowired
	private DispenserErrorItemService dispenserErrorItemService;
	@Autowired
	NozRelDisDao nozRelDisDao;
	@Autowired
	NozTankRelationService nozTankRelationService;

	@Override
	public IPage<Nozzle4Device> query(JSONObject req, boolean isPage) {
		IPage<Nozzle4Device> page = new Page<>();
		QueryWrapper<Nozzle4Device> wrapper = new QueryWrapper<>();
		if (req.getInteger("nozzleNo")!=null){
			wrapper.lambda().eq(Nozzle4Device::getNozzleNo, req.getIntValue("nozzleNo"));
		}
		wrapper.lambda().orderByAsc(Nozzle4Device::getNozzleNo);
		List<Nozzle4Device> nozzle4Devices = baseMapper.selectList(wrapper);
		if (!nozzle4Devices.isEmpty()) {
			for (Nozzle4Device nozzle4Device : nozzle4Devices) {
				if (nozzle4Device.getNozzleNo() != null) {
					nozzle4Device.setDispenserErrorItem(dispenserErrorItemService.getUsedByNozzle(nozzle4Device.getNozzleNo()));
				}
			}
		}
		page.setRecords(nozzle4Devices);

		return page;
	}

	@Override
	public JSONObject queryExistNozzle(Nozzle4Device nozzle) {
		QueryWrapper<Nozzle4Device> queryString = new QueryWrapper<>();
		if (nozzle.getNozzleNo() != null) {
			queryString.lambda().eq(Nozzle4Device::getNozzleNo, nozzle.getNozzleNo());
		}
		if (StringUtils.isNotEmpty(nozzle.getDetailInfo())) {
			queryString.lambda().eq(Nozzle4Device::getDetailInfo, nozzle.getDetailInfo());
		}
		nozzle = baseMapper.selectOne(queryString);

		if (nozzle == null) {
			return CommonUtil.successJson();
		}
		return CommonUtil.failJson();
	}

	@Override
	public void saveNoz( Nozzle4Device nozzle4Device){
		baseMapper.insert(nozzle4Device);
		//生成默认枪罐枪机关系数据
		NozTankRelationEntity nozTankRelationEntity = new NozTankRelationEntity();
		nozTankRelationEntity.setNozzleCode(nozzle4Device.getNozzleNo());
		if(nozzle4Device.getTankNo()!=null){
			nozTankRelationEntity.setTankNo(nozzle4Device.getTankNo());
			nozTankRelationEntity.setStartTime(new Date());
		}
		nozTankRelationService.save(nozTankRelationEntity);
		NozRelDisEntity nozRelDisEntity = new NozRelDisEntity();
		nozRelDisEntity.setNozzleCode(nozzle4Device.getNozzleNo());
		nozRelDisDao.insert(nozRelDisEntity);
	}


	@Override
	public List<Nozzle4Device> getAllNozzle() {
		return baseMapper.selectList(new QueryWrapper<>());
	}
	@Override
	public void updateNozRel(Nozzle4Device nozzle4Device){
		QueryWrapper<NozTankRelationEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(NozTankRelationEntity::getNozzleCode,nozzle4Device.getNozzleNo());
		queryWrapper.lambda().isNull(NozTankRelationEntity::getEndTime);
		List<NozTankRelationEntity> dblists = nozTankRelationService.list(queryWrapper);
		if(dblists.size()!=0) {
			NozTankRelationEntity nozTankRelationEntity = nozTankRelationService.list(queryWrapper).get(0);
			nozTankRelationEntity.setTankNo(nozzle4Device.getTankNo());
			nozTankRelationEntity.setOilName(nozzle4Device.getOilName());
			nozTankRelationEntity.setOilCode(nozzle4Device.getOilCode());
			List<NozTankRelationEntity> lists = new ArrayList<>();
			lists.add(nozTankRelationEntity);
			nozTankRelationService.changeRelation(lists);
		}
	}

}
