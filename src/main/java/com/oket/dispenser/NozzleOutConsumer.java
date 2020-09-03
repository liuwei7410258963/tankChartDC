package com.oket.dispenser;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.oket.dispenser.service.NozzleOutService;
import com.oket.station.bizservice.BalanceService;
import com.oket.tank4station.Inventory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @description: 付油消费者
 * @author: SunBiaoLong
 * @create: 2020-04-14 18:33
 **/
@Slf4j
public class NozzleOutConsumer {
	private static final BlockingQueue<BzNozzleOut> NOZZLE_OUT_QUEUE = new LinkedBlockingDeque<>(5);

	@Setter
	@Getter
	private BalanceService balanceService;

	ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
			.setNameFormat("OutConsumer-%d").build();


	private ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2, namedThreadFactory);

	public static BlockingQueue<BzNozzleOut> getNozzleOutQueue() {
		return NOZZLE_OUT_QUEUE;
	}

	public NozzleOutConsumer(BalanceService balanceService) {
		this.balanceService = balanceService;
	}

	public void consume() {
		scheduled.scheduleAtFixedRate(() -> {
					try {
						//有数据时直接从队列的队首取走，无数据时阻塞，在2s内有数据，取走，超过2s还没数据，返回失败
						BzNozzleOut data = NOZZLE_OUT_QUEUE.poll(2, TimeUnit.SECONDS);
						while (null != data) {
							balanceService.receiveNozzleOut(data);
							data = NOZZLE_OUT_QUEUE.poll(2, TimeUnit.SECONDS);
						}
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}

				},
				//18 * 1000表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
				20 * 1000, 50, TimeUnit.MILLISECONDS);

	}

}
