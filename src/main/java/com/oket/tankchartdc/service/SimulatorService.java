package com.oket.tankchartdc.service;

import com.oket.config.spring.SpringUtil;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.InventoryConsumer;
import com.oket.tankchartdc.mina.dit.DITEmulator;
import com.oket.tankchartdc.mina.dit.DitEmulatorProcess;
import com.oket.tankchartdc.mina.dit.ifsf.EmulatorInventory;
import com.oket.tankchartdc.upload.GlobalException;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @description: 模拟器service
 * @author: SunBiaoLong
 * @create: 2020-03-14 11:17
 **/
@Service
@Slf4j
@Data
@Scope(value = "singleton")
public class SimulatorService {
	/**
	 * 存入时间超时阈值
	 */
	private final static int OVERTIME_SECONDS = 2;
	public static boolean ifsfTestFlag;
	public static boolean jsonTestFlag;
	private DITEmulator ifsfEmulator;
	private DITEmulator jsonEmulator;
	private int ifsfPort = 0;
	private int jsonPort = 0;
	private Thread thread;
	private List<Inventory> inventoryList;
	private boolean enableDit = true;
	private String ifsfIp = "localhost";
	@Autowired
	private DitService ditService;
	/**
	 * 间隔-毫米
	 * 液位对应体积；默认1毫升17升
	 * 可以为负数
	 */
	private double levelGap = 5;
	/**
	 * 时间间隔-秒
	 */
	private int timeGap = 5;

	/**
	 * 0-ALL
	 * 1-液位
	 */
	private int scope;

	/**
	 * 发送时间间隔,默认5秒
	 */
	private int sendGap = 5;

	/*
	 * 开启连接
	 */
	public Boolean open(int port, String ip, int type) {
		DitEmulatorProcess.setEnableDitFileEmulator(true);
		//json建立连接
		if (type == 2) {
			jsonPort = port;
			jsonEmulator = new DITEmulator(ip, jsonPort);
			jsonTestFlag = jsonEmulator.isOpen();
			return jsonEmulator.isOpen();
		}
		//ifsf建立连接
		else if (type == 3) {
			ifsfPort = port;
			ifsfEmulator = new DITEmulator(ip, ifsfPort);
			ifsfTestFlag = ifsfEmulator.isOpen();
			return ifsfEmulator.isOpen();
		}
		return false;
	}

	/*
	 * 关闭连接
	 */
	public void close(int type) {
		//json关闭连接
		if (type == 2) {
			jsonEmulator.close();
			jsonTestFlag = false;
		}
		//ifsf关闭连接
		else if (type == 3) {
			ifsfEmulator.close();
			ifsfTestFlag = false;
		}
	}

	/**
	 * 发送信息
	 *
	 * @param object
	 * @param type
	 */
	public void send(Object object, TYPE type) {
		DITEmulator ditEmulator = null;
		int port = 0;
		switch (type) {

			case IFSF:
				ditEmulator = ifsfEmulator;
				port = getIfsfPort();
				break;
			case JSON:
				ditEmulator = jsonEmulator;
				port = getJsonPort();
				break;
			default:
		}
		if (ditEmulator != null && ditEmulator.getSession() != null&&ditEmulator.getSession().isActive()) {
			ditEmulator.getSession().write(object);
		}
		//没有连接的情况建立连接
		else {
			ditEmulator = new DITEmulator("localhost", port);
			DitEmulatorProcess.setEnableDitFileEmulator(true);
			if (ditEmulator.getSession() != null && ditEmulator.getSession().isActive()) {
				ditEmulator.getSession().write(object);
			}
			if (type == TYPE.IFSF) {
				if (ifsfEmulator == null) {
					ifsfEmulator = ditEmulator;
				}
				ifsfTestFlag = ifsfEmulator.isOpen();
			} else {
				if (jsonEmulator == null) {
					jsonEmulator = ditEmulator;
				}
				jsonTestFlag = jsonEmulator.isOpen();
			}
		}
	}

	/**
	 * 开始执行
	 *
	 * @param inventoryList
	 * @throws GlobalException
	 */
	public void start(List<Inventory> inventoryList) throws GlobalException {
		this.inventoryList = inventoryList;
		if (!inventoryList.isEmpty()) {
			thread = new Thread(new MyRunnable());
			thread.start();
		} else {
			throw new GlobalException("传入无效参数");
		}
	}

	/**
	 * 中断线程
	 */
	public void stop() {
		if (thread != null) {
			thread.interrupt();
		}
		if (ifsfEmulator != null) {
			ifsfEmulator.close();
		}
	}

	/**
	 * 获取当前模拟器的运行状态
	 *
	 * @return
	 */
	public Map<String, Object> getMessage() {
		Map<String, Object> map = new HashMap<>();
		map.put("发送器", thread == null || thread.isInterrupted() ? "线程中断或不存在" : "正在执行");
		map.put("数据", inventoryList);
		map.put("模拟器发送端", ifsfEmulator);
		return map;
	}

	private void send(Inventory inventory) throws InterruptedException {
		if (enableDit) {
			send(new EmulatorInventory(inventory), TYPE.IFSF);
		} else {
			if (!InventoryConsumer.getRealtimeDataBlockingQueue().offer(inventory, OVERTIME_SECONDS, TimeUnit.SECONDS)) {
				log.error("放入数据失败：" + inventory);
			}
		}
	}

	private int getIfsfPort() {
		if (ifsfPort == 0) {
			ifsfPort = SpringUtil.getBean(DitService.class).selectIfsf();
		}
		return ifsfPort;
	}

	private int getJsonPort() {
		if (jsonPort == 0) {
			jsonPort = SpringUtil.getBean(DitService.class).selectJson();
		}
		return jsonPort;
	}

	public enum TYPE {
		JSON, IFSF
	}

	/**
	 * 模拟数据实现类
	 */
	private class MyRunnable implements Runnable {
		@SneakyThrows
		@Override
		public void run() {

			while (!Thread.currentThread().isInterrupted()) {

				if (!inventoryList.isEmpty()) {
					for (Inventory inventory : inventoryList) {
						inventory.setTime(new Date(inventory.getTime().getTime() + timeGap * 1000));
						if (inventory.getLevel() <= 0) {
							inventory.setLevel(0);
							inventory.setVolume(0);
						}
						if (levelGap < 0) {
							if (inventory.getLevel() - levelGap > 0) {
								inventory.setLevel(inventory.getLevel() + levelGap);
								inventory.setVolume(inventory.getLevel() * 17);
							}
						} else {
							inventory.setLevel(inventory.getLevel() + levelGap);
							inventory.setVolume(inventory.getLevel() * 17);
						}

						try {
							send(inventory);
						} catch (InterruptedException e) {
							log.error(e.getMessage(), e);
						}
						try {
							Thread.sleep(sendGap * 1000);
						} catch (InterruptedException e) {
							log.error(e.getMessage(), e);
							Thread.currentThread().interrupt();
						}
					}

				} else {
					Thread.currentThread().interrupt();
					throw new GlobalException("无效参数");
				}

			}
		}
	}
}
