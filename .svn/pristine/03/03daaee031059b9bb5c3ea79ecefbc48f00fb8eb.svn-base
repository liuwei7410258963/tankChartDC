package com.oket.device.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.common.base.Status;
import com.oket.device.NozTankRelationEntity;
import com.oket.device.Nozzle4Device;
import com.oket.device.TankInfo;
import com.oket.device.dao.NozTankRelationDao;
import com.oket.device.dao.Nozzle4DeviceDAO;
import com.oket.device.dao.TankInfoDAO;
import com.oket.device.service.NozTankRelationService;
import com.oket.device.service.Nozzle4DeviceService;
import com.oket.device.service.TankInfoService;
import com.oket.station.bizservice.BalanceService;
import com.oket.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 油罐服务实现类
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-14
 */
@Service
@Lazy(false)
public class TankInfoServiceImpl extends ServiceImpl<TankInfoDAO, TankInfo> implements TankInfoService {

	@Autowired
	NozTankRelationService nozTankRelationService;
	@Autowired
	Nozzle4DeviceService nozzle4DeviceService;
	@Autowired
	BalanceService balanceService;

	@Override
	public List<TankInfo> query(JSONObject jsonObject) {
		QueryWrapper<TankInfo> tankWrapper = new QueryWrapper<>();
		if (jsonObject.getString("status") != null) {
			tankWrapper.lambda().eq(TankInfo::getStatus, jsonObject.getString("status"));
		}
		tankWrapper.lambda().orderByAsc(TankInfo::getTankNo);
		List<TankInfo> tankInfoList = baseMapper.selectList(tankWrapper);
		tankInfoList.stream().forEach(x -> {
			QueryWrapper queryWrapper = new QueryWrapper();
			queryWrapper.eq("tank_no", x.getTankNo());
			queryWrapper.isNull("end_time");
			queryWrapper.select("nozzle_code");
			List<NozTankRelationEntity> list = nozTankRelationService.list(queryWrapper);
			x.setRelNozLists(list.stream().map(NozTankRelationEntity::getNozzleCode).collect(toList()));
		});
		return tankInfoList;
	}

	@Override
	public List<TankInfo> queryTank(JSONObject jsonObject){
		QueryWrapper<TankInfo> tankWrapper = new QueryWrapper<>();
		tankWrapper.select("tank_no","detail_info");
		if (jsonObject.getString("status") != null) {
			tankWrapper.lambda().eq(TankInfo::getStatus, jsonObject.getString("status"));
		}
		tankWrapper.lambda().orderByAsc(TankInfo::getTankNo);
		List<TankInfo> tankInfoList = baseMapper.selectList(tankWrapper);
		return tankInfoList;
	}

	@Override
	public JSONObject queryExistTank(TankInfo tankInfo) {
		QueryWrapper<TankInfo> queryString = new QueryWrapper<>();
		if (tankInfo.getStationId() != null) {
			queryString.lambda().eq(TankInfo::getStationId, tankInfo.getStationId());
		}
		if (tankInfo.getTankNo() != null) {
			queryString.lambda().eq(TankInfo::getTankNo, tankInfo.getTankNo());
		}
		if (StringUtils.isNotEmpty(tankInfo.getDetailInfo())) {
			queryString.lambda().eq(TankInfo::getDetailInfo, tankInfo.getDetailInfo());
		}
		tankInfo = baseMapper.selectOne(queryString);

		if (tankInfo == null) {
			return CommonUtil.successJson();
		}
		return CommonUtil.failJson();
	}

	@Override
	public void delete(QueryWrapper<TankInfo> query) {
		baseMapper.delete(query);
	}


	@Override
	public void updateStatus(List<TankInfo> tankList) {
		//改为禁用状态
		for (TankInfo tank : tankList) {
			QueryWrapper<TankInfo> queryString = new QueryWrapper<>();
			queryString.eq("STATION_ID", tank.getStationId()).eq("CODE", tank.getTankNo());
			tank.setStatus(Status.DISABLE);
			update(tank, queryString);
		}
	}


	@Override
	public TankInfo save(JSONObject jsonObject) {
		String jsonStr = jsonObject.getString("tankInfo");
		JSONObject tankJson = JSONObject.parseObject(jsonStr);
		TankInfo tankInfo = JSON.toJavaObject(tankJson, TankInfo.class);
		baseMapper.insert(tankInfo);
		balanceService.tankPropertyChanged(tankInfo);
		return tankInfo;
	}

	@Override
	public List<TankInfo> getAllTank() {
		return list();
	}

	@Override
	public TankInfo getTankInfo(int tankNo) {
		QueryWrapper<TankInfo> tqw = new QueryWrapper<>();
		tqw.lambda().eq(TankInfo::getTankNo, tankNo).last("LIMIT 1");
		return this.getOne(tqw);
	}

	@Override
	public TankInfo update(JSONObject jsonObject) {
		String jsonStr = jsonObject.getString("tankInfo");
		JSONObject tankJson = JSONObject.parseObject(jsonStr);
		TankInfo tankInfo = JSON.toJavaObject(tankJson, TankInfo.class);
		baseMapper.updateById(tankInfo);
		updateNozzleOil(tankInfo);
		//油罐信息更改通知处理模块
		balanceService.tankPropertyChanged(tankInfo);
		return tankInfo;
	}

	/**
	 * 处理枪罐关系时更新油枪的油品
	 *
	 * @param tankInfo
	 */
	@Override
	public void updateNozzleOil(TankInfo tankInfo) {
		QueryWrapper<NozTankRelationEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(NozTankRelationEntity::getTankNo, tankInfo.getTankNo());
		queryWrapper.lambda().isNull(NozTankRelationEntity::getEndTime);
		List<NozTankRelationEntity> dbLists = nozTankRelationService.list(queryWrapper);
		//修改关联该油罐的油枪的油品
		if (dbLists.size() != 0) {
			List<Integer> nozNoLists = dbLists.stream().map(NozTankRelationEntity::getNozzleCode).collect(toList());
			QueryWrapper<Nozzle4Device> queryWrapper1 = new QueryWrapper<>();
			queryWrapper1.lambda().in(Nozzle4Device::getNozzleNo, nozNoLists);
			List<Nozzle4Device> nozzle4DeviceLists = nozzle4DeviceService.list(queryWrapper1);
			nozzle4DeviceLists.forEach(x -> {
				x.setOilCode(tankInfo.getOilCode());
				x.setOilName(tankInfo.getOilName());
			});
			nozzle4DeviceService.updateBatchById(nozzle4DeviceLists);
		}
	}

	@Override
	public boolean updateById(TankInfo entity) {
		final boolean b = super.updateById(entity);
		if (b){
			balanceService.tankPropertyChanged(entity);
		}
		return b;
	}

	@Override
	public boolean save(TankInfo entity) {
		final boolean save = super.save(entity);
		if (save){
			balanceService.tankPropertyChanged(entity);
		}
		return save;
	}

	@Override
	public boolean saveBatch(Collection<TankInfo> entityList) {
		boolean save=super.saveBatch(entityList);
		if (save){
			for (TankInfo tankInfo : entityList) {
				balanceService.tankPropertyChanged(tankInfo);
			}
		}
		return save;
	}


}
