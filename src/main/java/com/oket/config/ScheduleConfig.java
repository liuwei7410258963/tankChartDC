package com.oket.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

/**
 * @description: 定时器配置类
 * @Scheduled 这个注解给我们带了很大的方便，我们只要加上该注解，并且根据需求设置好就可以使用定时任务了。
 * <p>
 * 但是，我们需要注意的是，@Scheduled 并不一定一定会按时执行。
 * <p>
 * 因为使用@Scheduled 的定时任务虽然是异步执行的，但是，不同的定时任务之间并不是并行的！！！！！！！！
 * <p>
 * 在其中一个定时任务没有执行完之前，其他的定时任务即使是到了执行时间，也是不会执行的，它们会进行排队。
 * <p>
 * 也就是如果你想你不同的定时任务互不影响，到时间就会执行，那么你最好将你的定时任务方法自己搞成异步方法，这样，
 * <p>
 * 定时任务其实就相当于调用了一个线程执行任务，一瞬间就结束了。当然，也可以勉强当做是任务都会定时执行。
 * <p>
 * 加上下面这个配置类就可以使定时任务变成异步的了
 * @author: SunBiaoLong
 * @create: 2020-05-11 09:33
 **/
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean
	public Executor taskExecutor() {
		//指定线程池大小
		return Executors.newScheduledThreadPool(1);
	}
}
