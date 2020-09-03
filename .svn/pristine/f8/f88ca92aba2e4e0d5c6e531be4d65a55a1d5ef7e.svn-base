package com.oket.tankchartdc.upload;

import com.oket.config.spring.SpringUtil;
import com.oket.tankchartdc.mina.dit.DitEmulatorProcess;
import com.oket.tankchartdc.mina.json.codec.DitJsonHeader;
import com.oket.tankchartdc.service.SimulatorService;
import com.oket.util.MathExtend;
import com.oket.util.StringExUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.oket.tankchartdc.controller.UploadController.speed;
import static com.oket.tankchartdc.controller.UploadController.status;

@Data
public class SendSocketMessage {
	private final static Logger log = LoggerFactory.getLogger(SendSocketMessage.class);
	//返回给前端的数据类型
	public static List<String> returnFileName;
	public static String returnfunctionType;
	public static int returnSpeed = 1;
	private boolean socketStop = false;
	private boolean pause = false;

	/**
	 * 启用原始时间
	 */
	private boolean enableOriginTime = true;

	private static void setReturnType(Simulation simulation) {
		returnFileName = simulation.getFileName();
		returnfunctionType = simulation.getFunctionType();
		returnSpeed = simulation.getSpeed();
	}

	/**
	 * 清空用户上次选择的类型
	 */
	public static void clearReturnType() {
		returnFileName = null;
		returnfunctionType = null;
		returnSpeed = 1;
	}

	/**
	 * @description: 读取文件推送数据
	 * @author: lw
	 * @create: 2019/12/7
	 **/
	public void readFileAndSend(Simulation simulation) {
		{
			//为下一次打开浏览器返回用户输入的类型赋值
			setReturnType(simulation);
			log.info("——————开始——————");
			try {
				//设置速度
				speed = simulation.getSpeed();
				//读取文件(字节流)
				log.info("开始读取文件");
				List<Message> socketList = new ArrayList<>();
				//可以处理多个文件
				for (String fileName : simulation.getFileName()) {
					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
					String str = null;
					Calendar calendar = Calendar.getInstance();
					//循环取出数据,组装list
					while ((str = in.readLine()) != null) {
						if (str.length() > 112 && str.contains("received")) {
							//根据条件拼装socketList（含ifsf和json数据）
							socketList(str, socketList, calendar, simulation);
						}
					}
				}
				//根据组装好的socketList推送数据
				sendSocketMessage(socketList);
				clearReturnType();
				status = "stop";
			} catch (Exception e) {
				log.error("出错了" + e.getMessage(), e);
			}
		}
	}

	/**
	 * @description: 根据组装好的socketList推送数据
	 * @author: lw
	 * @create: 2019/12/10
	 **/
	private void sendSocketMessage(List<Message> lists) throws Exception {
		long lastTime = 0;
		int i = 1;
		//开发环境时间间隔测试数据
		long devTestTime = 0;
		for (Message list : lists) {
			if (processPauseAndStop()) {
				break;
			}
			//第一条数据，不用sleep
			if (i == 1) {
				if (enableOriginTime) {
					devTestTime = 0;
				} else {
					devTestTime = System.currentTimeMillis() - list.getTime().getTime();

				}
				//发送socket数据
				send(list, devTestTime);
				lastTime = list.getTime().getTime();
			}
			//非第一条数据
			else {
				long value = list.getTime().getTime() - lastTime;
				lastTime = list.getTime().getTime();
				//发送数据，延时为当前条的时间-上一条的时间
				if (true) {
					log.debug("gap:" + value / speed + ";time:" + list.getTime().toLocaleString());
					if (!enableOriginTime) {
						TimeUnit.MILLISECONDS.sleep(value / speed);
					} else {
						//没有间隔运行会崩溃
						TimeUnit.MILLISECONDS.sleep( 100);
					}
				}
				if (processPauseAndStop()) {
					break;
				}
				//发送socket数据
				send(list, devTestTime);
			}
			i++;
		}
		log.info("模拟现场数据结束");
	}


	private boolean processPauseAndStop() {
		if (pause) {
			synchronized (SimulatorSendThread.threadLock) {
				try {
					SimulatorSendThread.threadLock.wait();
				} catch (InterruptedException e) {
					log.error("", e);
				}
			}
		}
		//停止推送数据
		if (socketStop) {
			return true;
		}
		return false;
	}

	/**
	 * @description: 根据条件拼装socketList（含ifsf和json数据）
	 * @author: lw
	 * @create:
	 **/
	private void socketList(String str, List<Message> lists, Calendar calendar, Simulation simulation) throws ParseException, UnsupportedEncodingException {
		//ifsf数据
		if (str.contains("ReceiveDITHandler") || str.contains("IFSFHandler")) {
			IFSFList(str, lists, simulation);
		}
		//json数据
		else if (str.contains("DitJsonHandler")) {
			JSONList(str, lists, calendar, simulation);
		}
	}

	private void IFSFList(String str, List<Message> lists, Simulation simulation) throws ParseException {
		String[] functionTypeArr = simulation.getFunctionType().split(",");
		for (String arr : functionTypeArr) {
			switch (arr) {
				case "液位数据":
					if (str.contains("IFSFInventory")) {
						functionTypeIfsfList(str, lists);
					}
					break;
				case "正常交易数据":
					if (str.contains("IFSFTransaction")) {
						functionTypeIfsfList(str, lists);
					}
					break;
				case "脱机交易数据":
					if (str.contains("脱机交易数据")) {
						functionTypeIfsfList(str, lists);
					}
					break;
				case "加油开始结束数据":
					if (str.contains("FuelingPointAndHoseStatus")) {
						functionTypeIfsfList(str, lists);
					}
					break;
				case "加油中数据":
					if (str.contains("IFSFOnFueling")) {
						functionTypeIfsfList(str, lists);
					}
					break;
				default:
					break;
			}
		}
	}

	/**
	 * @description: 符合条件组装该条数据（ifsf）
	 * @author: lw
	 * @create:
	 **/
	private void functionTypeIfsfList(String str, List<Message> lists) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str.substring(0, 20));
		Message message = new Message();
		message.setType(1);
		message.setTime(date);
		String originHex = str.substring(str.indexOf("originHex=")).substring(10);
		message.setContent(originHex.substring(0, originHex.length() - 1));

		filterSameData(lists, message);
	}

	private void JSONList(String str, List<Message> lists, Calendar calendar, Simulation simulation) throws ParseException, UnsupportedEncodingException {
		String[] functionTypeArr = simulation.getFunctionType().split(",");
		for (String arr : functionTypeArr) {
			switch (arr) {
				case "枪罐关系":
					if (str.contains("枪罐关系") || str.contains("TankRelNozzle")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				case "配送数据":
					if (str.contains("配送数据") || str.contains("OfflineRecord")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				case "油罐基本信息":
					if (str.contains("油罐基本信息")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				case "离线交易":
					if (str.contains("离线交易") || str.contains("OfflineRecord")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				case "交易数据":
					if (str.contains("交易数据") || str.contains("OilTransaction")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				case "罐存数据":
					if (str.contains("罐存数据") || str.contains("Inventory")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				case "加油机油枪对应关系":
					if (str.contains("加油机油枪对应关系") || str.contains("NozzleRelDispenser")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				case "卸油数据":
					if (str.contains("Delivery") || str.contains("NozzleOutWhenDelivery") || str.contains("DeliveryLossAlarm")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				case "回罐数据":
					if (str.contains("P91_10012")) {
						funtionTypeJsonList(str, lists, calendar);
					}
					break;
				default:
					break;
			}
		}
	}

	/**
	 * @description: 符合条件组装该条数据（json）
	 * @author: lw
	 * @create:
	 **/
	private void funtionTypeJsonList(String str, List<Message> lists, Calendar calendar) throws ParseException, UnsupportedEncodingException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str.substring(0, 20));
		Message message = new Message();
		message.setType(2);
		String originHex = str.substring(str.indexOf("originHex=")).substring(10);
		byte a = StringExUtils.hexStringToByte(originHex.substring(10, 12))[0];
		message.setTime(date);
		if (originHex.length() < 10 + 2 + a * 2 + 4 * 2) {
			setContentOnMiss(str, message, originHex);
		} else {
			final String substring = originHex.substring(2 * (5 + 1 + a), 2 * (5 + 1 + a + 4));
			int bodyL=MathExtend.bytes2Int4(StringExUtils.hexStringToByte(substring));
			if ( 2 * (bodyL +5 + 1 + a + 4) != originHex.length()-1) {
				setContentOnMiss(str, message, originHex);
			} else {
				message.setContent(originHex.substring(0, originHex.length() - 1));
			}
		}
		filterSameData(lists, message);

	}

	/**
	 * 2020.6.4.之前版本的数据originHex是不全的，需要补充，之后的数据是全的
	 * @param str
	 * @param message
	 * @param originHex
	 * @throws UnsupportedEncodingException
	 */
	private void setContentOnMiss(String str, Message message, String originHex) throws UnsupportedEncodingException {
		byte pidLength = 9;

		DitJsonHeader ditJsonHeader = new DitJsonHeader();
		ditJsonHeader.setPid(
				str.substring(
						str.indexOf("pid=") + 4,
						str.indexOf(", bodyLength")
				)
		);
		ditJsonHeader.setBodyLength(
				Integer.valueOf(str.substring(
						str.indexOf("bodyLength=") + 11,
						str.indexOf(", desc=")
				))
		);
		ditJsonHeader.setHeadLength(pidLength);
		String hex = StringExUtils.byteToHexString(pidLength) + StringExUtils.byteArrayToHexString(ditJsonHeader.getPid().getBytes("GBK")) + StringExUtils.byteArrayToHexString(MathExtend.intToByteArray(ditJsonHeader.getBodyLength()));

		originHex = originHex.substring(0, 10) + hex + originHex.substring(10);
		message.setContent(originHex.substring(0, originHex.length() - 1));
	}

	/**
	 * 过滤相同消息
	 *
	 * @param lists
	 * @param message
	 */
	private void filterSameData(List<Message> lists, Message message) {
//		if (!lists.isEmpty()) {
//			for (int i = lists.size() - 1; i > lists.size() - 10 && i >= 0; i--) {
//				final Message temp = lists.get(i);
//				//重复数据
//				if (temp.content.equals(message.content)) {
//					return;
//				}
//			}
//			lists.add(message);
//		} else {
//			//组装list
//			lists.add(message);
//		}
		lists.add(message);
	}

	/**
	 * @description: 推送socket数据
	 * @author: lw
	 * @create:
	 **/

	private void send(Message list, long devTestTime) {
		//ifsf数据推送
		if (list.getType() == 1) {
			String nowTime = (list.getTime().getTime() + devTestTime) + "";
			byte[] bytes = null;
			if (DitEmulatorProcess.isEnableDitFileEmulator()) {
				bytes = StringExUtils.hexStringToByte(list.getContent()
						+ StringExUtils.byteArrayToHexString(new byte[]{(byte) (devTestTime + "").length(), (byte) nowTime.length()})
						+ StringExUtils.byteArrayToHexString((devTestTime + "" + nowTime).getBytes(StandardCharsets.UTF_8)));
			} else {
				bytes = StringExUtils.hexStringToByte(list.getContent());
			}
			SpringUtil.getBean(SimulatorService.class).send(StringExUtils.byteArrayToHexString(bytes), SimulatorService.TYPE.IFSF);
			log.debug("模拟器发送ifsf数据：时间" + new Date(list.getTime().getTime()).toLocaleString() + ";msg:" + list.getContent());
		}
		//json数据推送
		else if (list.getType() == 2) {
			byte[] bytes = null;
			if (DitEmulatorProcess.isEnableDitFileEmulator()) {
				bytes = StringExUtils.hexStringToByte(list.getContent()
						+ StringExUtils.byteToHexString((byte) (devTestTime + "").length())
						+ StringExUtils.byteArrayToHexString((devTestTime + "").getBytes(StandardCharsets.UTF_8)));
			} else {
				bytes = StringExUtils.hexStringToByte(list.getContent());
			}
			SpringUtil.getBean(SimulatorService.class).send(StringExUtils.byteArrayToHexString(bytes), SimulatorService.TYPE.JSON);
			//DitJsonHandler里写了原始数据推送
			log.debug("模拟器发送json数据：时间" + new Date(list.getTime().getTime()).toLocaleString() + ";msg:" + list.getContent());
		}
	}

	/**
	 * 发送消息类
	 */
	@Data
	static class Message {
		/**
		 * 类型1 ifsf 2json
		 */
		private int type;
		/**
		 * 原始数据接收时间
		 */
		private Date time;
		/**
		 * 消息
		 */
		private String content;
	}
}
