package com.oket.tankchartdc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.tank4station.AbstractLevelTrace;
import com.oket.tank4station.LevelState;
import com.oket.tank4station.dao.DbInventorCycleDAO;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryAlarm;
import com.oket.tank4station.service.impl.InventorySpringProcessor;
import com.oket.tankchartdc.DbInventoryCycle;
import com.oket.tankchartdc.service.DbInventoryCycleService;
import com.oket.util.TimeUtils;
import com.oket.util.VarianceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 液位周期service
 * @author: SunBiaoLong
 * @create: 2020-03-17 14:18
 **/
@Slf4j
@Service
public class DbInventoryCycleServiceImpl extends ServiceImpl<DbInventorCycleDAO, DbInventoryCycle> implements DbInventoryCycleService {

	/**
	 * 当前的周期
	 */
	public static final Map<Integer, DbInventoryCycle> CURRENT_CYCLE_MAP = new ConcurrentHashMap<>();

	@Autowired
	private InventorySpringProcessor inventorySpringProcessor;

	/**
	 * 是否正在卸油
	 *
	 * @param tankNo
	 * @return
	 */
	public static boolean isUnloading(int tankNo) {
		final DbInventoryCycle dbInventoryCycle = CURRENT_CYCLE_MAP.get(tankNo);
		if (dbInventoryCycle == null) {
			return false;
		} else {
			if (dbInventoryCycle.getCycleState().equals(DbInventoryCycle.CycleState.UNLOADING)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 根据油罐获取当前的周期信息
	 *
	 * @param tankNo
	 * @return
	 */
	public static DbInventoryCycle getCurrentCycle(int tankNo) {
		return CURRENT_CYCLE_MAP.get(tankNo);
	}

	/**
	 * 存入缓存中
	 *
	 * @param tankNo
	 * @param cycle
	 */
	public static void putIntoMap(int tankNo, DbInventoryCycle cycle) {
		CURRENT_CYCLE_MAP.put(tankNo, cycle);
	}

	/**
	 * 是否正在卸油中
	 *
	 * @param trace
	 * @return
	 */
	public static boolean isUnloading(AbstractLevelTrace trace) {
		return trace.getLastLevel().getLevel() - trace.getFirstLevel().getLevel() > DbInventoryCycle.UNLOADING_LEVEL_GAP;
	}

	@Override
	public IPage<DbInventoryCycle> query(JSONObject req, boolean isPage) {
		// 参数根据需要判断来写
		IPage<DbInventoryCycle> page = new Page<>(req.getIntValue("pageNum"), req.getIntValue("pageRow"));
		//查询条件 根据需要写
		QueryWrapper<DbInventoryCycle> wrapper = new QueryWrapper<>();
		//查询条件
		if (req.getString("startTime") != null && req.getString("endTime") != null) {
			try {
				wrapper.lambda().ge(DbInventoryCycle::getStartTime, TimeUtils.parseToSecond(req.getString("startTime")));
				wrapper.lambda().and(w -> {
							try {
								w.le(DbInventoryCycle::getEndTime, TimeUtils.parseToSecond(req.getString("endTime")))
										.or().isNull(DbInventoryCycle::getEndTime);
							} catch (ParseException e) {
								log.error(e.getMessage(), e);
							}
						}
				);
			} catch (ParseException e) {
				log.error("", e);
			}
		}
		if (req.getString("tankNo") != null) {
			wrapper.lambda().eq(DbInventoryCycle::getTankNo, req.getString("tankNo"));
		}
		wrapper.lambda().orderByAsc(DbInventoryCycle::getDeliveryId);
		if (!isPage) {
			//不分页的数据
			page.setRecords(baseMapper.selectList(wrapper));
		} else {
			// 分页的数据
			page = baseMapper.selectPage(page, wrapper);
		}
		return page;
	}


	@Override
	public List<DbInventoryCycle> getCycle(int tankNo, Date startTime, Date endTime) {
		QueryWrapper<DbInventoryCycle> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(DbInventoryCycle::getTankNo, tankNo);
		wrapper.lambda().le(DbInventoryCycle::getStartTime, endTime);
		wrapper.lambda().and(w -> w.ge(DbInventoryCycle::getEndTime, startTime)
				.or().isNull(DbInventoryCycle::getEndTime)
		);
		wrapper.lambda().orderByDesc(DbInventoryCycle::getStartTime);
		return list(wrapper);
	}

	/**
	 * 获取周期信息
	 *
	 * @param tankNo 油罐编号
	 * @return
	 */
	@Override
	public DbInventoryCycle getCycle(int tankNo) {
		DbInventoryCycle currentCycle = getCurrentCycle(tankNo);
		if (currentCycle == null) {
			currentCycle = getCycleFromDb(tankNo);
			if (currentCycle != null) {
				putIntoMap(tankNo, currentCycle);
			}
		}
		return currentCycle;

	}


	@Override
	public void processCycle(AbstractLevelTrace trace) {
		if (trace == null || trace.getLastLevel().getTime() == null) {
			return;
		}
		final DbInventoryCycle currentCycle = getCycle(trace.getTankNo());
		if (trace.getCycleId() != 0) {
			if (currentCycle != null) {
				if (!currentCycle.getCycleState().equals(DbInventoryCycle.CycleState.SELLING)) {
					//卸油中的状态处理
					log.info("当前周期：" + currentCycle.getCycleState().name());
					switch (trace.getLevelState()) {
						case LEVEL_ASCENDING:
							if (!currentCycle.getCycleState().equals(DbInventoryCycle.CycleState.UNLOADING)) {
								if (currentCycle.getCycleState().equals(DbInventoryCycle.CycleState.ACCOMPLISH_UNLOADING)) {
									currentCycle.setUnloadFinishedTime(null);
									currentCycle.setUnloadFinishedLevel(null);
									currentCycle.setUnloadFinishedWaterLevel(null);
									currentCycle.setUnloadFinishedVolume(null);
									currentCycle.setUnloadFinishedTemperature(null);
									currentCycle.setUnloadFinishedWaterVolume(null);
									if (currentCycle.getUnloadVolume() != null) {
										currentCycle.setUnloadVolume(null);
									}
								}
								log.info("转为上升：" + currentCycle.getTankNo());
								currentCycle.setCycleState(DbInventoryCycle.CycleState.UNLOADING);
							}
							currentCycle.setNozzleOutWhenDelivery(currentCycle.getNozzleOutWhenDelivery() + trace.getSumNozzleOut());
							baseMapper.updateById(currentCycle);
							break;
						case LEVEL_WAVING:
							if (!currentCycle.getCycleState().equals(DbInventoryCycle.CycleState.ACCOMPLISH_UNLOADING)) {

								currentCycle.unloadFinished(trace.getLastLevel());
								log.info("波动结束,卸油完成：" + currentCycle.getTankNo());
							}
							currentCycle.setNozzleOutWhenDelivery(currentCycle.getNozzleOutWhenDelivery() + trace.getSumNozzleOut());
							baseMapper.updateById(currentCycle);
							break;
						case LEVEL_STABLE:
							if (!currentCycle.getCycleState().equals(DbInventoryCycle.CycleState.ACCOMPLISH_UNLOADING)) {
								log.info("卸油完成：" + currentCycle.getTankNo());
								if (currentCycle.getUnloadFinishedTime() == null) {
									currentCycle.unloadFinished(trace.getFirstLevel());
								}
								currentCycle.setCycleState(DbInventoryCycle.CycleState.ACCOMPLISH_UNLOADING);
							}
							currentCycle.setNozzleOutWhenDelivery(currentCycle.getNozzleOutWhenDelivery() + trace.getSumNozzleOut());
							baseMapper.updateById(currentCycle);
							break;
						case LEVEL_DESCENDING:
							currentCycle.setCycleState(DbInventoryCycle.CycleState.SELLING);
							baseMapper.updateById(currentCycle);
							break;
						default:
							log.error("无法确认周期：" + trace.getLevelState()
									+ ";startTime:" + trace.getFirstLevel().getTime().toLocaleString());
					}
				} else {
					boolean unloading = isUnloading(trace);
					//判断是否卸油，如卸油，应该新建周期
					if (unloading) {
						closeAndNew(trace, currentCycle);
					}
				}
			} else {
				log.error("无法找到周期");
			}
		} else {
			boolean unloading = isUnloading(trace);
			if (currentCycle == null) {
				createNewCycle(trace, unloading);
			} else {
				closeAndNew(trace, currentCycle, unloading);
			}
		}
	}

	/**
	 * 结束上一周期，并开启新的周期
	 *
	 * @param trace
	 * @param currentCycle
	 */
	private void closeAndNew(AbstractLevelTrace trace, DbInventoryCycle currentCycle) {
		closeAndNew(trace, currentCycle, true);
	}

	/**
	 * 结束上一周期，并开启新的周期
	 *
	 * @param trace
	 * @param currentCycle
	 * @param isUnloading  是否在卸油中
	 */
	private void closeAndNew(AbstractLevelTrace trace, DbInventoryCycle currentCycle, boolean isUnloading) {
		closeCycle(currentCycle, trace);
		createNewCycle(trace, isUnloading);
	}

	/**
	 * 创建新的周期
	 *
	 * @param trace
	 */
	private void createNewCycle(AbstractLevelTrace trace, boolean isUnloading) {
		DbInventoryCycle dbInventoryCycle = null;
		//是否卸油
		if (isUnloading && !trace.getLevelState().equals(LevelState.NEW)) {
			dbInventoryCycle = DbInventoryCycle.init(trace
					, DbInventoryCycle.CycleState.UNLOADING);
			dbInventoryCycle.setNozzleOutWhenDelivery(trace.getSumNozzleOut());
			log.info("初始化新建卸油周期：" + dbInventoryCycle);
		} else {
			dbInventoryCycle = DbInventoryCycle.init(trace
					, DbInventoryCycle.CycleState.SELLING);
			log.info("初始化新建销售周期：" + dbInventoryCycle);
		}
		this.save(dbInventoryCycle);
		trace.setCycleId(dbInventoryCycle.getId());
		trace.setOnUnloading(isUnloading);
		inventorySpringProcessor.saveOrUpdateTrace(trace.toDbTrace());
		putIntoMap(dbInventoryCycle.getTankNo(), dbInventoryCycle);
	}


	/**
	 * 结束上一周期
	 *
	 * @param lastCycle
	 * @param trace
	 */
	private void closeCycle(DbInventoryCycle lastCycle, AbstractLevelTrace trace) {
		lastCycle.closed(trace.getFirstLevel());
		log.info("结束上一周期：" + lastCycle);
		baseMapper.updateById(lastCycle);
	}


	@Override
	public DbInventoryCycle getCycleFromDb(int tankNo) {
		QueryWrapper<DbInventoryCycle> qw = new QueryWrapper<>();
		qw.lambda().eq(DbInventoryCycle::getTankNo, tankNo);
		qw.lambda().ne(DbInventoryCycle::getCycleState, DbInventoryCycle.CycleState.CLOSED);
		qw.last("LIMIT 1");
		return getOne(qw);
	}
}
