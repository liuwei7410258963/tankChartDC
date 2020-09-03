package com.oket.tank4station.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.oket.device.TankInfo;
import com.oket.device.dao.TankInfoDAO;
import com.oket.tank4station.AbstractLevelTrace;
import com.oket.tank4station.AbstractTankSession;
import com.oket.tank4station.InventoryException;
import com.oket.tank4station.TankInventory;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryAlarm;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tank4station.service.DbInventoryAlarmService;
import com.oket.tank4station.service.DbInventoryService;
import com.oket.tank4station.service.DbInventoryTraceService;
import com.oket.tank4station.service.InventoryPersistenceProcessor;
import com.oket.tank4station.websocket.BizWebSocketServer;
import com.oket.tankchartdc.DbInventoryCycle;
import com.oket.tankchartdc.GroupConsumer;
import com.oket.tankchartdc.service.BackToTankService;
import com.oket.tankchartdc.service.CorrectSamplesService;
import com.oket.tankchartdc.service.DbInventoryCycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @description: 液位数据处理器
 * @author: SunBiaoLong
 * @create: 2020-04-09 17:10
 **/
@Slf4j
@Service
public class InventorySpringProcessor implements InventoryPersistenceProcessor {
	/**
	 * 存入时间超时阈值
	 */
	private final static int OVERTIME_SECONDS = 2;
	@Autowired
	private DbInventoryTraceService dbInventoryTraceService;
	@Autowired
	private DbInventoryService dbInventoryService;
	@Autowired
	private DbInventoryAlarmService dbInventoryAlarmService;
	@Autowired
	private DbInventoryCycleService dbInventoryCycleService;
	@Autowired
	private BackToTankService backToTankService;
	@Resource
	private BizWebSocketServer bizWebSocketServer;
	@Autowired
	private TankInfoDAO tankInfoDAO;

	/**
	 * 获取开始和结束的液位数据
	 *
	 * @param lastTrace
	 */
	private void getStartAndEndInv(DbInventoryTrace lastTrace) {
		dbInventoryTraceService.getStartAndEndInv(lastTrace);
	}

	@Override
	public List<DbInventory> getInventories(int tankNo, Date startTime, Date endTime) {
		return dbInventoryService.getInventoryInGap(tankNo, startTime, endTime);
	}

	@Override
	public void mergeTrace(List<DbInventoryTrace> traceList) {
		dbInventoryTraceService.merge(traceList);
	}

	@Override
	public void updateInventoryLast(TankInventory inventory) {
		dbInventoryService.updateInventoryLast(inventory);
	}


	@Override
	public DbInventoryCycle getCycle(int tankNo) {
		return dbInventoryCycleService.getCycle(tankNo);
	}

	@Override
	public void processCycle(AbstractLevelTrace trace) {
		dbInventoryCycleService.processCycle(trace);
		if (!trace.isOnUnloading()) {
			backToTankService.process(trace);
		}
	}

	@Override
	public List<DbInventoryTrace> getTrace(int tankNo, Date startTime, Date endTime, boolean descByStartTime) {
		final List<DbInventoryTrace> trace1 = dbInventoryTraceService.getTrace(startTime, endTime, tankNo, descByStartTime);
		if (trace1 != null && !trace1.isEmpty()) {
			for (DbInventoryTrace trace : trace1) {
				getStartAndEndInv(trace);
			}
		}
		return trace1;
	}

	@Override
	public DbInventoryTrace getLastTrace(int tankNo, Date time) {
		final DbInventoryTrace beforeTrace = dbInventoryTraceService.getBeforeTrace(time, tankNo);
		getStartAndEndInv(beforeTrace);
		return beforeTrace;
	}

	/**
	 * 指定油罐的的指定时间的上一条数据
	 *
	 * @param tankNo
	 * @param time
	 * @return
	 */
	@Override
	public DbInventoryTrace getNextTrace(int tankNo, Date time) {
		final DbInventoryTrace beforeTrace = dbInventoryTraceService.getBeforeTrace(time, tankNo);
		getStartAndEndInv(beforeTrace);
		return beforeTrace;
	}

	/**
	 * 获取所有罐的最后一笔轨迹信息
	 * 慎用：：如果缺少数据会补充罐存数据
	 *
	 * @return
	 */
	@Override
	public List<DbInventoryTrace> getAllLastTrace() {
		final List<DbInventoryTrace> list = dbInventoryTraceService.getAllLastTrace();
		if (list != null && !list.isEmpty()) {
			for (DbInventoryTrace trace : list) {
				getStartAndEndInv(trace);
				if (trace.getFirstInventory() == null) {
					/*
					根据id查询不到对应的液位轨迹
					需要补充数据
					 */
					DbInventory dbInventory = new DbInventory();
					dbInventory.setId(trace.getStartId());
					dbInventory.setTime(trace.getStartTime());
					dbInventory.setLevel(trace.getStartLevel());
					dbInventory.setTemperature(trace.getStartTemperature());
					dbInventory.setVolume(trace.getStartVolume());
					dbInventory.setWaterLevel(trace.getStartWaterLevel());
					dbInventory.setWaterVolume(trace.getStartWaterVolume());
					dbInventory.setOilCode(trace.getOilCode());
					dbInventory.setOilName(trace.getOilName());
					dbInventory.setTankNo(trace.getTankNo());
					QueryWrapper<TankInfo> qw = new QueryWrapper<>();
					qw.lambda().eq(TankInfo::getTankNo, dbInventory.getTankNo()).last("LIMIT 1");
					final TankInfo tankInfo = tankInfoDAO.selectOne(qw);
					if (tankInfo != null && tankInfo.getMaxVolume() != null) {
						dbInventory.setMaxVolume(tankInfo.getMaxVolume());
					} else {
						dbInventory.setMaxVolume(30000);
					}
					dbInventoryService.save(dbInventory);
					trace.setFirstInventory(dbInventory);
				}
			}
		}
		return list;
	}

	@Override
	public void saveOrUpdateTraces(List<DbInventoryTrace> traces) {
		dbInventoryTraceService.saveOrUpdateBatch(traces);

	}

	@Override
	public void saveOrUpdateTrace(DbInventoryTrace trace) {
		dbInventoryTraceService.saveOrUpdate(trace);
	}

	/**
	 * 保存的顺序不能改变
	 *
	 * @param needPersistence
	 * @throws InventoryException
	 */
	@Override
	public void persistence(AbstractTankSession.NeedPersistence needPersistence) throws InventoryException {
		if (needPersistence != null) {
			log.info("nP:{}", needPersistence);
			//保存罐存数据，每5分钟批量保存一次
			saveInventory(needPersistence);
			//保存cycle
			saveCycle(needPersistence.getCycles());
			//保存轨迹，当轨迹结束时endid赋值
			saveTrace(needPersistence);
			//保存异常信息
			saveAlarm(needPersistence);
			//设置罐存不用立刻保存
			needPersistence.setSaveNow(false);
			//清空已经保存好的数据
			needPersistence.getCycles().clear();
			needPersistence.getInventories().clear();
			needPersistence.getAlarms().clear();
			needPersistence.getTraces().clear();
		}
	}

	ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
			.setNameFormat("cal-%d").build();
	/**
	 * 异步计算数据的线程池
	 */
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5, namedThreadFactory);
	/**
	 * 计算数据操作延时--因为匹配需要一段时间，因此要有延迟
	 */
	private final int OPERATE_DELAY_TIME = 20;

	@Autowired
	private CorrectSamplesService correctSamplesService;

	/**
	 * 保存cycle
	 *
	 * @param cycles
	 */
	private void saveCycle(List<DbInventoryCycle> cycles) {
		if (!cycles.isEmpty()) {
			dbInventoryCycleService.saveOrUpdateBatch(cycles);
			for (DbInventoryCycle cycle : cycles) {
				//判断周期是否已经结束，如果结束利用算法进行修正油枪的付油数据
				if (cycle.getCycleState().equals(DbInventoryCycle.CycleState.CLOSED)) {
					executor.schedule(() -> {
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("deliveryIds", cycle.getDeliveryId() + "");
								jsonObject.put("tankNo", cycle.getTankNo());
								correctSamplesService.generateSamples(jsonObject);
							}
							, OPERATE_DELAY_TIME, TimeUnit.MINUTES);

				}
			}
		}
	}

	/**
	 * 保存异常信息
	 *
	 * @param needPersistence
	 */
	private void saveAlarm(AbstractTankSession.NeedPersistence needPersistence) {
		if (!needPersistence.getAlarms().isEmpty()) {
			dbInventoryAlarmService.saveBatch(needPersistence.getAlarms());
			for (DbInventoryAlarm alarm : needPersistence.getAlarms()) {
				bizWebSocketServer.sendMessageAlarm(alarm);
			}
		}
	}

	/**
	 * 保存轨迹信息
	 *
	 * @param needPersistence
	 */
	private void saveTrace(AbstractTankSession.NeedPersistence needPersistence) {
		if (needPersistence.getTraces() != null && !needPersistence.getTraces().isEmpty()) {
			List<DbInventoryTrace> dbTraces = new ArrayList<>();
			final List<AbstractLevelTrace> levelTraces = needPersistence.getTraces();
			//不能使用lambda的map
			for (AbstractLevelTrace trace : levelTraces) {
				initTraceCycle(needPersistence, trace);
				dbTraces.add(trace.toDbTrace());
			}
			dbInventoryTraceService.saveOrUpdateBatch(dbTraces);
			//将数据库实体的id赋值给业务实体的id
			for (int i = 0; i < dbTraces.size(); i++) {
				final AbstractLevelTrace abstractLevelTrace = levelTraces.get(i);
				final DbInventoryTrace trace = dbTraces.get(i);
				abstractLevelTrace.setId(trace.getId());
				if (abstractLevelTrace.isClosed()) {
					//1.卸油不去判断回罐
					//2.如果有需要匹配的数据，在匹配完成后再进行判断是否回罐
					if (!abstractLevelTrace.isOnUnloading()
							&& abstractLevelTrace.getSumOut() == 0) {
						backToTankService.process(abstractLevelTrace);
					}
					try {
						if (!GroupConsumer.getTraceQueue().offer(abstractLevelTrace, OVERTIME_SECONDS, TimeUnit.SECONDS)) {
							log.error("放入数据失败：" + trace);
						}
					} catch (InterruptedException e) {
						log.error(e.getMessage(), e);
					}
				}


			}
		}
	}

	/**
	 * 初始化轨迹的周期信息
	 *
	 * @param needPersistence
	 * @param trace
	 */
	private void initTraceCycle(AbstractTankSession.NeedPersistence needPersistence, AbstractLevelTrace trace) {
		//getCycleId==0表明周期没有持续化到数据库
		if (trace.getCycleId() == 0) {
			for (DbInventoryCycle cycle : needPersistence.getCycles()) {
				if (cycle.getDeliveryId() == trace.getDeliveryId()) {
					trace.setCycleId(cycle.getId());
					break;
				}
			}
		}
	}

	/**
	 * 保存罐存信息
	 *
	 * @param needPersistence
	 * @throws InventoryException
	 */
	private void saveInventory(AbstractTankSession.NeedPersistence needPersistence) throws InventoryException {
		if (!needPersistence.getTraces().isEmpty()) {
			//有轨迹的时候必须先保存液位信息
			needPersistence.setSaveNow(true);
		}
		dbInventoryService.saveInventory(needPersistence.getInventories(), needPersistence.getTankNo(), needPersistence.isSaveNow());

	}


}
