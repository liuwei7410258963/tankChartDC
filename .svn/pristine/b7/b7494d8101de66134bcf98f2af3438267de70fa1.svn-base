package com.oket.device.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oket.config.spring.SpringUtil;
import com.oket.device.*;
import com.oket.device.dao.*;
import com.oket.device.service.DeviceService;
import com.oket.device.service.NozRelDisService;
import com.oket.device.service.NozTankRelationService;
import com.oket.device.service.Nozzle4DeviceService;
import com.oket.station.bizservice.BalanceService;
import com.oket.tank4station.dao.DbInventoryAlarmDAO;
import com.oket.tank4station.entity.DbInventoryAlarm;
import com.oket.util.AirUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.oket.tank4station.trace.AbstractTraceProcessor.ERROR_NUM;
import static com.oket.tank4station.trace.AbstractTraceProcessor.errorMap;
import static com.oket.util.DeviationCalculator.loadDll;

/**
 * @description: 设备管理器
 * @author: SunBiaoLong
 * @create: 2019-12-10 16:22
 **/
@Service
@Slf4j
public class DeviceCacheManager {

	@Autowired
	NozRelDisService nozRelDisService;
	@Autowired
	NozRelDisDao nozRelDisDao;
	@Value("${ditIniFilePath}")
	private String ditIniFilePath;
	@Value("${dllFilePath}")
	private String dllFilePath;
	@Autowired
	private TankInfoDAO tankInfoDAO;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private NozTankRelationService nozTankRelationService;
	@Autowired
	private NozTankRelationDao nozTankRelationDao;
	@Autowired
	private DitNozzleIfsfIni ditNozzleIfsfIni;
	@Autowired
	BalanceService balanceService;
	@Autowired
	Nozzle4DeviceService nozzle4DeviceService;
	@Autowired
	DbInventoryAlarmDAO alarmDAO;

	/**
	 * 初始化加载缓存数据
	 */
	@PostConstruct
	public void init() {
		try {
			//加载油枪物理节点基本信息
			loadDitIni(ditIniFilePath);
			//加载枪罐关系
			insertRel();
			//加载dll文件
			loadDll(dllFilePath);
			//加载dll文件
			putErrorMap();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 读取dit配置文件
	 *
	 * @param ditIniFilePath
	 */
	private void loadDitIni(String ditIniFilePath) {
		ditNozzleIfsfIni.init(ditIniFilePath);
		final Collection<Nozzle4Device> allNozzle4Device = ditNozzleIfsfIni.getAllNozzle4Device();
		if (!allNozzle4Device.isEmpty()) {
			for (Nozzle4Device nozzle4Device : allNozzle4Device) {
				deviceService.processNozzleIfsfData(nozzle4Device);
			}
		}
	}

	/**
	 * 枪罐枪机关系
	 */
	private void insertRel() {
		//第一次从Ini文件中读取加载进数据库
		//枪罐关联关系
		if (nozTankRelationDao.selectList(new QueryWrapper<>()).size() == 0) {
			List<NozTankRelationEntity> nozTanklists = new ArrayList<>();
			Date now = new Date();
			for (Map.Entry<Integer, Nozzle4Device> m : ditNozzleIfsfIni.getNozzleIniMap().entrySet()) {
				NozTankRelationEntity nozTankRelationEntity = new NozTankRelationEntity();
				nozTankRelationEntity.setStartTime(now);
				nozTankRelationEntity.setNozzleCode(m.getKey());
				nozTankRelationEntity.setTankNo(m.getValue().getTankNo());
				nozTanklists.add(nozTankRelationEntity);
			}
			nozTankRelationService.saveBatch(nozTanklists);
			//枪罐改变通知处理
			List<Integer> codeLists = nozTanklists.stream().map(NozTankRelationEntity::getNozzleCode).collect(Collectors.toList());
			QueryWrapper queryWrapper = new QueryWrapper();
			queryWrapper.in("nozzle_no", codeLists);
			balanceService.nozzleRelTankChanged(nozzle4DeviceService.list(queryWrapper));
		}
		//枪机关联关系
		if (nozRelDisDao.selectList(new QueryWrapper<>()).size() == 0) {
			List<NozRelDisEntity> lists = new ArrayList<>();
			for (Map.Entry<Integer, Nozzle4Device> m : ditNozzleIfsfIni.getNozzleIniMap().entrySet()) {
				NozRelDisEntity nozRelDisEntity = new NozRelDisEntity();
				nozRelDisEntity.setNozzleCode(m.getKey());
				lists.add(nozRelDisEntity);
			}
			nozRelDisService.saveBatch(lists);
		}
	}

	public void putErrorMap(){
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("type",4);
		queryWrapper.isNull("end_time");
		List<DbInventoryAlarm> list = alarmDAO.selectList(queryWrapper);
		if(AirUtils.hv(list)){
			list.stream().forEach(x->{
				errorMap.put(x.getTankNo(),ERROR_NUM);
			});
		}
	}

}
