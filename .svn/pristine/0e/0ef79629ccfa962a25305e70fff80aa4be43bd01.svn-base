package com.oket.device.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.device.*;
import com.oket.device.dao.Dispenser4DeviceDAO;
import com.oket.device.dao.NozRelDisDao;
import com.oket.device.dao.Nozzle4DeviceDAO;
import com.oket.device.dao.VersionDAO;
import com.oket.device.service.Dispenser4DeviceService;
import com.oket.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service(value = "dispenser4DeviceService")
public class Dispenser4DeviceServiceImpl extends ServiceImpl<Dispenser4DeviceDAO, Dispenser4Device> implements Dispenser4DeviceService {
	@Resource
	private Dispenser4DeviceDAO dispenser4DeviceDAO;
	@Resource
	private Nozzle4DeviceDAO nozzle4DeviceDAO;

	@Resource
	private VersionDAO versionDAO;
	@Autowired
	NozRelDisDao nozRelDisDao;

	@Override
	public List<Dispenser4Device> getByStationBizId() {
		return dispenser4DeviceDAO.getByStationBizId();
	}


	public void saveOrUpdateNozzles(List<Nozzle4Device> nozzles) {
		if (nozzles == null || nozzles.isEmpty()) {
			return;
		} else {
			nozzles.forEach(nozzle -> {
				if (nozzle.getId() != null) {
					nozzle4DeviceDAO.updateById(nozzle);
				} else {
					nozzle4DeviceDAO.insert(nozzle);
					if (nozzle.getDeviceVersions() != null && nozzle.getDeviceVersions().size() > 0) {
						nozzle.getDeviceVersions().forEach(version -> {
							version.setDeviceId(nozzle.getId());
							version.setDeviceType(DeviceType.NOZZLE);
						});
					}
				}
				if (nozzle.getDeviceVersions() != null && nozzle.getDeviceVersions().size() > 0) {
					saveOrUpdateVersions(nozzle.getDeviceVersions());
				}
			});
		}
	}

	@Override
	public void saveOrUpdateDispenser(List<Dispenser4Device> dispenser4Devices) {
		if (dispenser4Devices == null || dispenser4Devices.isEmpty()) {
			return;
		} else {
			for (Dispenser4Device dispenser4Device : dispenser4Devices) {
				if (dispenser4Device.getId() != null) {
					dispenser4DeviceDAO.updateById(dispenser4Device);
				} else {
					dispenser4DeviceDAO.insert(dispenser4Device);
					if (dispenser4Device.getDeviceVersions() != null
							&& dispenser4Device.getDeviceVersions().size() > 0) {
						dispenser4Device.getDeviceVersions().forEach(version -> {
							version.setDeviceId(dispenser4Device.getId());
							version.setDeviceType(DeviceType.DISPENSER);
						});
					}
					if (dispenser4Device.getNozzle4Devices() != null
							&& !dispenser4Device.getNozzle4Devices().isEmpty()) {
						dispenser4Device.getNozzle4Devices().forEach(nozzle4Device -> {
							nozzle4Device.setDispenserNo(dispenser4Device.getId());
						});
					}
				}
				saveOrUpdateNozzleAndVersions(dispenser4Device);
			}
		}
	}

	private void saveOrUpdateNozzleAndVersions(Dispenser4Device dispenser4Device) {
		if (dispenser4Device.getNozzle4Devices() != null
				&& !dispenser4Device.getNozzle4Devices().isEmpty()) {
			saveOrUpdateNozzles(dispenser4Device.getNozzle4Devices());
		}
		if (dispenser4Device.getDeviceVersions() != null
				&& !dispenser4Device.getDeviceVersions().isEmpty()) {
			saveOrUpdateVersions(dispenser4Device.getDeviceVersions());
		}
	}


	public void saveOrUpdateVersions(List<Version> deviceVersions) {
		if (deviceVersions == null || deviceVersions.isEmpty()) {
			return;
		} else {
			deviceVersions.forEach(deviceVersion -> {
				Version version = deviceVersion;
				if (version.getId() == null) {
					versionDAO.insert(version);
				} else {
					versionDAO.updateById(version);
				}
			});
		}
	}

	@Override
	public void processDispenser(Dispenser4Device dispenser4Device) {

	}

	@Override
	public void processNozzle(Nozzle4Device nozzle4Device) {

	}

	@Override
	public JSONObject queryExistDispenser(Dispenser4Device dispenser4Device) {
		QueryWrapper<Dispenser4Device> queryString = new QueryWrapper<>();

		if (dispenser4Device.getDispenserNo() != null) {
			queryString.lambda().eq(Dispenser4Device::getDispenserNo, dispenser4Device.getDispenserNo());
		}
		if (StringUtils.isNotEmpty(dispenser4Device.getDetailInfo())) {
			queryString.lambda().eq(Dispenser4Device::getDetailInfo, dispenser4Device.getDetailInfo());
		}
		dispenser4Device = baseMapper.selectOne(queryString);

		if (dispenser4Device == null) {
			return CommonUtil.successJson();
		}
		return CommonUtil.failJson();
	}

	@Override
	public List<Dispenser4Device> query(JSONObject req) {
		QueryWrapper queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.orderByAsc("dispenser_no");
		List<Dispenser4Device> dispenser4DeviceList = dispenser4DeviceDAO.selectList(queryWrapper1);
		dispenser4DeviceList.stream().forEach(x->{
			QueryWrapper queryWrapper = new QueryWrapper();
			queryWrapper.eq("dispenser_no",x.getDispenserNo());
			queryWrapper.isNull("end_time");
			queryWrapper.select("nozzle_code");
			List<NozRelDisEntity> list = nozRelDisDao.selectList(queryWrapper);
			x.setRelNozLists(list.stream().map(NozRelDisEntity::getNozzleCode).collect(toList()));
		});
		return dispenser4DeviceList;
	}
}
