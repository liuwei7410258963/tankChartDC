package com.oket.tankchartdc.upload;

import lombok.extern.slf4j.Slf4j;

/**
 * 模拟发送数据线程
 */
@Slf4j
public class SimulatorSendThread extends Thread {


	/**
	 * 线程的锁
	 */
	public static final Object threadLock = new Object();
	/**
	 * 模拟发送控制类
	 */
	public Simulation simulation;
	/**
	 * 发送消息功能实现类
	 */
	private SendSocketMessage sendSocketMessage;

	public SimulatorSendThread(Simulation simulation) {
		this.simulation = simulation;
		sendSocketMessage = new SendSocketMessage();
	}

	/***
	 * 调用该方法实现线程的暂停
	 */
	public void pauseThread() {
		sendSocketMessage.setPause(true);
	}


	/**
	 * 调用该方法实现恢复线程的运行
	 */
	public void resumeThread() {
		sendSocketMessage.setPause(false);
		synchronized (threadLock) {
			threadLock.notify();
		}
	}

	/**
	 * 调用该方法实现恢复线程的运行
	 */
	public void stopThread() {
		sendSocketMessage.setSocketStop(true);
		if (sendSocketMessage.isPause()) {
			resumeThread();
		}
	}

	@Override
	public void run() {
		super.run();
		try {
			sendSocketMessage.readFileAndSend(simulation);
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
