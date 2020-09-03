package com.oket.tankchartdc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.oket.dispenser.BzNozzleOut;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.tank4station.AbstractLevelTrace;
import com.oket.tankchartdc.service.MatchAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

/**
 * @description: 组合消费者
 * @author: SunBiaoLong
 * @create: 2020-04-14 13:10
 **/
@Slf4j
@Service
public class GroupConsumer {
	ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
			.setNameFormat("GroupConsumer-%d").build();

	/**
	 * 轨迹的队列
	 */
	private static final BlockingQueue<AbstractLevelTrace> TRACE_QUEUE = new LinkedBlockingDeque<>(3);
	/**
	 * 付油队列
	 */
	private static final BlockingQueue<BzNozzleOut> NOZZLE_OUT_QUEUE = new LinkedBlockingDeque<>(3);

	private ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(3, namedThreadFactory);
	@Autowired
	private NozzleOutGroupProcessor nozzleOutGroupProcessor;
	@Autowired
	private LevelTraceProcessor levelTraceProcessor;

	/**
	 * 获取轨迹队列
	 *
	 * @return
	 */
	public static BlockingQueue<AbstractLevelTrace> getTraceQueue() {
		return TRACE_QUEUE;
	}


	/**
	 * 获取付油队列
	 *
	 * @return
	 */
	public static BlockingQueue<BzNozzleOut> getNozzleOutQueue() {
		return NOZZLE_OUT_QUEUE;
	}

	/**
	 * 初始化后启动三个定时器
	 */
	@PostConstruct
	private void init() {
		try {
			levelTraceConsumer();
			nozzleOutConsumer();
			longTimeNoMatchProcess();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	/**
	 * 液位轨迹消费者
	 */

	public void levelTraceConsumer() {
		scheduled.scheduleAtFixedRate(() -> {
					try {
						AbstractLevelTrace levelTrace = TRACE_QUEUE.poll(2, TimeUnit.SECONDS);
						while (levelTrace != null) {
							levelTraceProcessor.processLevelTrace(levelTrace);
							//获取下一条数据，如果不为null继续处理，否则暂停50毫秒
							levelTrace = TRACE_QUEUE.poll(2, TimeUnit.SECONDS);
						}
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}

				},
				//18 * 1000表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
				18 * 1000, 50, TimeUnit.MILLISECONDS);

	}


	/**
	 * 付油轨迹消费者
	 */
	public void nozzleOutConsumer() {
		scheduled.scheduleAtFixedRate(() -> {
					try {
						BzNozzleOut bzNozzleOut = NOZZLE_OUT_QUEUE.poll(2, TimeUnit.SECONDS);
						while (bzNozzleOut != null) {
							log.info("收到付油信息：" + bzNozzleOut);
							if (bzNozzleOut.getVolume() > 0) {
								final boolean processNozzleOut = nozzleOutGroupProcessor.processNozzleOut(bzNozzleOut);
								if (!processNozzleOut) {
									nozzleOutGroupProcessor.addNozzleOutToCache(bzNozzleOut);
									log.error("无法匹配液位:" + bzNozzleOut);
								}
							}
							bzNozzleOut = NOZZLE_OUT_QUEUE.poll(2, TimeUnit.SECONDS);
						}
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}
				},
				//16 * 1000,表示首次执行任务的延迟时间，50表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
				16 * 1000, 50, TimeUnit.MILLISECONDS);

	}

	/**
	 * 处理长时间没有匹配的数据
	 */
	public void longTimeNoMatchProcess() {
		scheduled.scheduleAtFixedRate(() -> {
					try {
						nozzleOutGroupProcessor.longTimeNoMatchProcess();
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}

				},
				//0表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
				5, 5, TimeUnit.MINUTES);

	}

}
