package com.oket.tank4station;

import com.oket.tank4station.dao.DbInventoryLastDAO;
import com.oket.tank4station.entity.DbInventoryAlarm;
import com.oket.tank4station.entity.DbInventoryLast;
import com.oket.tank4station.service.DbInventoryAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 罐存管理器
 * @author: SunBiaoLong
 * @create: 2019-11-27 20:52
 **/
@Component
@Slf4j
public class InventoryCacheManager {

	/**
	 * 最后一笔液位信息
	 */
	private final static Map<Integer, DbInventoryLast> LAST_INVENTORY_MAP = new ConcurrentHashMap<>();
	private final static Map<Integer, DbInventoryAlarm> LAST_INVENTORY_ALARM_MAP = new ConcurrentHashMap<>();
	@Resource
	private DbInventoryLastDAO dbInventoryLastDAO;
	@Autowired
	private DbInventoryAlarmService dbInventoryAlarmService;


	public static synchronized DbInventoryLast getLastInventory(Integer tankNo) {
		if (tankNo == null) {
			return null;
		}
		return LAST_INVENTORY_MAP.get(tankNo);
	}


	public static synchronized void putLastInventory(Integer tankNo, DbInventoryLast dbInventoryLast) {
		if (tankNo == null || dbInventoryLast == null) {
			return;
		}
		LAST_INVENTORY_MAP.put(tankNo, dbInventoryLast);
	}

	/**
	 * 获取所有罐存信息
	 *
	 * @return
	 */
	public static Collection<DbInventoryLast> getAllLastInventory() {
		return LAST_INVENTORY_MAP.values();
	}

	/**
	 * 获取所有报警信息
	 *
	 * @return
	 */
	public static Collection<DbInventoryAlarm> getAllInventoryAlarm() {
		return LAST_INVENTORY_ALARM_MAP.values();
	}

	/**
	 * 获取某个罐的报警信息
	 *
	 * @param tankNo
	 * @return
	 */
	public static synchronized DbInventoryAlarm getInventoryAlarm(Integer tankNo) {
		if (tankNo == null) {
			return null;
		}
		return LAST_INVENTORY_ALARM_MAP.get(tankNo);
	}

	/**
	 * 删除报警信息
	 *
	 * @param tankNo
	 */
	public static synchronized void removeInventoryAlarm(Integer tankNo) {
		if (tankNo == null) {
			return;
		}
		LAST_INVENTORY_ALARM_MAP.remove(tankNo);
	}


	/**
	 * 报警信息存入缓存
	 *
	 * @param tankNo
	 * @param dbInventoryAlarm
	 */
	public static synchronized void putInventoryAlarm(Integer tankNo, DbInventoryAlarm dbInventoryAlarm) {
		if (tankNo == null || dbInventoryAlarm == null) {
			return;
		}
		LAST_INVENTORY_ALARM_MAP.put(tankNo, dbInventoryAlarm);
	}

	/**
	 * 初始化缓存
	 */
	@PostConstruct
	public void init() {
		try {
			//		初始化最后一笔液位数据信息
			initLastCache();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}


	/**
	 * 初始化最后一笔液位数据信息
	 */
	private void initLastCache() {
		final List<DbInventoryLast> dbInventoryLasts = dbInventoryLastDAO.selectList(null);
		if (dbInventoryLasts != null && !dbInventoryLasts.isEmpty()) {
			dbInventoryAlarmService.initAlarm(dbInventoryLasts);
			for (DbInventoryLast dbInventoryLast : dbInventoryLasts) {
				final DbInventoryLast lastFromCache = LAST_INVENTORY_MAP.get(dbInventoryLast.getTankNo());
				if (lastFromCache != null) {
					if (lastFromCache.getTime().getTime() < dbInventoryLast.getTime().getTime()) {
						/*
						删除无用的多余数据
						 */
						log.error("同一个油罐出现相同最后一笔轨迹数据，删除前一个数据：" + lastFromCache + ";当前：" + dbInventoryLast);

						dbInventoryLastDAO.deleteById(lastFromCache.getId());
						LAST_INVENTORY_MAP.put(dbInventoryLast.getTankNo(), dbInventoryLast);
					} else {
						log.error("同一个油罐出现相同最后一笔轨迹数据，删除前一个数据：" + dbInventoryLast + ";当前：" + lastFromCache);
						dbInventoryLastDAO.deleteById(dbInventoryLast.getId());
					}
				} else {
					LAST_INVENTORY_MAP.put(dbInventoryLast.getTankNo(), dbInventoryLast);

				}
			}
		}
	}

}
