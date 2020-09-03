package com.oket.tank4station.tankchart;

import com.oket.device.TankInfo;
import com.oket.device.service.TankInfoService;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.TankInventory;
import com.oket.tank4station.entity.UpdateTableInfo;
import com.oket.tank4station.service.VolumeTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 容积表管理器
 * @author: SunBiaoLong
 * @create: 2020-04-27 17:45
 **/
@Service
@Slf4j
public class TankChartManager {
	/**
	 * 容积表缓存
	 */
	private final static Map<Integer, DBTankChart> tankChartMap = new ConcurrentHashMap<>();
	@Autowired
	private TankInfoService tankInfoService;
	@Autowired
	private VolumeTableService volumeTableService;

	@PostConstruct
	public void init() {
		try {
			refreshCache();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 存入缓存
	 *
	 * @param tankNo
	 * @param usedTankChart
	 */
	public void putIntoCache(int tankNo, UpdateTableInfo usedTankChart) {
		if (usedTankChart != null && StringUtils.isNotEmpty(usedTankChart.getHV())) {
			final DBTankChart tankChart = DBTankChart.transform(usedTankChart.getHV());
			if (tankChart != null) {
				tankChartMap.put(tankNo, tankChart);
			}
		} else {
			log.error("保存容积表入缓存失败，油罐编号：" + tankNo + ",原数据：" + usedTankChart);
		}
	}


	/**
	 * 获取容积表信息
	 *
	 * @param tankNo
	 * @return
	 */
	public static DBTankChart getDBTankChart(int tankNo) {
		return tankChartMap.get(tankNo);
	}

	/**
	 * 获取毫米编号率
	 *
	 * @param firstInv
	 * @param lastInv
	 * @return
	 */
	public static double mmChangeRate(TankInventory firstInv, TankInventory lastInv) {
		if (firstInv.getLevel() == lastInv.getLevel()) {
			return getMMChangeRateByTankChart(firstInv);
		} else {
			return Math.abs((firstInv.getVolume() - lastInv.getVolume())
					/ (firstInv.getLevel() - lastInv.getLevel()));
		}
	}
	/**
	 * 获取毫米编号率
	 *
	 * @return
	 */
	public static double mmChangeRate(int tankNo,double firstLevel,double lastLevel,double firstVolume,double lastVolume) {
		if (firstLevel- lastLevel<0.1) {
			return getMMChangeRateByTankChart(tankNo,firstLevel);
		} else {
			return Math.abs((firstVolume -lastVolume)

					/ (firstLevel - lastLevel));
		}
	}

	/**
	 * 获取毫米变化率通过容积表
	 * @param inventory
	 * @return
	 */
	private static double getMMChangeRateByTankChart(TankInventory inventory) {
		return getMMChangeRateByTankChart(inventory.getTankNo(), inventory.getLevel());
	}
	/**
	 * 获取毫米变化率通过容积表
	 * @return
	 */
	private static double getMMChangeRateByTankChart(int tankNo,double level) {
		final DBTankChart dbTankChart = getDBTankChart(tankNo);
		if (dbTankChart != null) {
			double begin = 0.0;
			double end = 0.0;
			if (level <= 1.0) {
//					保证begin是正的
				begin = 0.0;
				end = 1.0;
			} else {
				begin = level - 1;
				end =level + 1;
			}
			return dbTankChart.getMMChangeRate(begin, end);
		} else {
			return 0.0;
		}
	}

	/**
	 * 刷新缓存
	 */
	public void refreshCache() {
		final List<TankInfo> allTank = tankInfoService.getAllTank();
		if (allTank != null && !allTank.isEmpty()) {
			for (TankInfo tankInfo : allTank) {
				final UpdateTableInfo usedTankChart = volumeTableService.getUsedTankChart(tankInfo.getTankNo());
				putIntoCache(tankInfo.getTankNo(), usedTankChart);
			}
		}
	}

}
