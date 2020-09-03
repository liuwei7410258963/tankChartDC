package com.oket.tankchartdc.upload;

import com.oket.util.MathExtend;
import com.oket.util.StringExUtils;
import com.oket.util.TimeUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class SendSocketMessageTest {


	@Test
	public void testTime() {
		String time = "Fri Dec 13 13:10:42 CST 2019";
		Date date = new Date(time);
		date = new Date(date.getTime() - 14 * 60 * 60 * 1000);
		System.out.println(TimeUtils.formateToSecond(date));
	}

	@Test
	public void test2() {
		String originHex = "3230313031095039315F3130303036000000D77B2264617461223A5B7B2231223A22E7BBB4E5BEB7E8B7AFE789B9222C223130223A2230222C2232223A2231222C2233223A22313432392E3632222C2234223A22302E3030222C2235223A2231393130392E3830222C2236223A2231393130392E3830222C2237223A2232342E3436222C2238223A22323032302D30362D30352030303A30303A3233222C2239223A22333030383635227D5D2C226D73674944223A2231303738222C22736F75726365223A22424A3038222C2274696D65223A22323032302D30362D30352030303A30303A3233227D0A)";
		byte a = StringExUtils.hexStringToByte(originHex.substring(10, 12))[0];
		final String substring = originHex.substring(2 * (5 + 1 + a ), 2 * (5 + 1 + a + 4));
		System.out.println(2 * (5 + 1 + a));
		System.out.println(2 * (5 + 1 + a + 4));
		System.out.println(substring);
		int bodyL = MathExtend.bytes2Int4(StringExUtils.hexStringToByte(substring));
		if (bodyL + 2 * (5 + 1 + a + 4) != originHex.length()) {
			//setContentOnMiss(str, date, message, originHex);
		} else {
			//message.setContent(originHex.substring(0, originHex.length() - 1));
		}
	}

	@Test
	public void test() {
		Simulation simulation = new Simulation();
		simulation.setFileName(Arrays.asList(
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-09.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-10.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-11.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-12.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-13.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-14.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-15.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-16.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-17.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-18.log",
				"H:\\ideawork\\tankChartDC\\socket_test\\iotprocess-2019-12-19.log"));

		simulation.setFunctionType("液位数据,交易数据,离线交易数据");
		readFileToSocket(simulation);
	}

	/**
	 * @description: 读取文件推送socket数据
	 * @author: lw
	 * @create: 2019/12/7
	 **/
	public void readFileToSocket(Simulation simulation) {
		{
			log.info("——————开始——————");
			try {

				log.info("开始读取文件");
				List<InnerSocketTest> socketList = new ArrayList<>();
				//可以处理多个文件
				for (String fileName : simulation.getFileName()) {
					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
					String str = null;
					//循环取出数据,组装list
					while ((str = in.readLine()) != null) {
						if (str.length() > 112 && str.contains("received")) {
							//根据条件拼装socketList（含ifsf和json数据）
							socketList(str, socketList, simulation);
						}
					}
				}
				write(socketList);
			} catch (Exception e) {
				log.error("出错了" + e.getMessage(), e);
			}
		}
	}

	private void write(List<InnerSocketTest> socketList) {

		Map<Integer, List<String>> inv = new HashMap<>();
		Map<Integer, List<String>> out = new HashMap<>();


		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 5);
		map.put(2, 4);
		map.put(3, 4);
		map.put(4, 5);
		map.put(5, 4);
		map.put(6, 3);
		map.put(7, 2);
		map.put(8, 1);
		map.put(9, 2);
		map.put(10, 2);

		for (InnerSocketTest innerSocketTest : socketList) {
			if (innerSocketTest.type == 1) {
				final String message = innerSocketTest.getMessage();
//
				final String invTime = message.substring(0, "2019-12-09 12:48:17.515".length());
				final int indexOf = message.indexOf("], ditIFSFDataBody");
				StringBuffer stringBuffer = new StringBuffer();
				int tankNo = Integer.parseInt(message.substring(indexOf - 2, indexOf)) - 32;
				stringBuffer.append(tankNo).append(",").append(invTime).append(",");
				String substring = message.substring(message.indexOf("IFSFInventory("));
				substring = substring.substring(0, substring.indexOf(")), originHex="));
				substring = substring.replace("oilHeight=", "");
				substring = substring.replace(" oilAndWaterVolume=", "");
				substring = substring.replace(" oilVolume=", "");
				substring = substring.replace(" temp=", "");
				substring = substring.replace(" waterHeight=", "");
				substring = substring.replace("IFSFInventory(", "");
				stringBuffer.append(substring);
				List<String> strings = inv.computeIfAbsent(tankNo, k -> new ArrayList<>());
				strings.add(stringBuffer.toString());

			} else if (innerSocketTest.type == 2) {
				if (innerSocketTest.getMessage().contains("OilTransaction")) {
					final String message = innerSocketTest.getMessage();
					String substring = message.substring(message.indexOf("OilTransaction("));
					substring = substring.substring(0, substring.indexOf(")], ditApiEnum="));
					substring = substring.replace("OilTransaction(gunNo=", "");
					substring = substring.replace(" oilNo=", "");
					substring = substring.replace(" litre=", "");
					substring = substring.replace(" amount=", "");
					String replace = substring.substring(substring.indexOf(" startTime="),
							substring.indexOf(", endTime=")).replace(" startTime=", "");
					Date date = new Date(replace);
					//时间是有差异的.
					date = new Date(date.getTime() - 14 * 60 * 60 * 1000);

					String substring1 = substring.substring(0, substring.indexOf(" startTime="));
					substring1 += TimeUtils.formateToSecond(date);
					replace = substring.substring(substring.indexOf(" endTime="),
							substring.indexOf(", startPumpCode=")).replace(" endTime=", "");
					date = new Date(replace);
					date = new Date(date.getTime() - 14 * 60 * 60 * 1000);
					substring1 += "," + TimeUtils.formateToSecond(date);
					substring1 += substring.substring(substring.indexOf(", startPumpCode="));
					substring1 = substring1.replace(" startPumpCode=", "");
					substring1 = substring1.replace(" endPumpCode=", "");
					substring1 = substring1.replace(" unitPrice=", "");
					int nozzle = Integer.parseInt(substring1.substring(0, substring1.indexOf(",")));
					final Integer tankNo = map.get(nozzle);
					if (tankNo == null) {
						System.out.println("找不到油罐：" + nozzle);
						continue;
					}
					List<String> strings = out.computeIfAbsent(tankNo, k -> new ArrayList<>());
					strings.add(substring1);
				} else {
//						final String message = innerSocketTest.getMessage();
//						String substring = message.substring(message.indexOf("OfflineRecord"));
//TODO:离线数据现在没有
//						List<String> strings = inv.get(tankNo);
//						if (strings == null) {
//							strings = new ArrayList<>();
//							inv.put(tankNo, strings);
//						}
//						strings.add(substring);

				}
			}
		}

		BufferedWriter invWri = null;

		File files = new File("./doc/transfer");
		if (!files.exists()) {
			files.mkdir();
		}
		final Set<Map.Entry<Integer, List<String>>> entries = inv.entrySet();
		final Iterator<Map.Entry<Integer, List<String>>> iterator = entries.iterator();
		while (iterator.hasNext()) {
			final Map.Entry<Integer, List<String>> next = iterator.next();
			final Integer key = next.getKey();
			File file = new File("./doc/transfer/inventory_" + key + ".txt");
			try {
				invWri = new BufferedWriter(new FileWriter(file));
				final List<String> value = next.getValue();
				for (String s : value) {
					invWri.write(s);
					invWri.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (invWri != null) {
					try {
						invWri.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		BufferedWriter outWri = null;
		final Set<Map.Entry<Integer, List<String>>> outE = out.entrySet();
		final Iterator<Map.Entry<Integer, List<String>>> iterator2 = outE.iterator();
		while (iterator2.hasNext()) {
			final Map.Entry<Integer, List<String>> next = iterator2.next();
			final Integer key = next.getKey();
			try {
				File file = new File("./doc/transfer/out_" + key + ".txt");
				outWri = new BufferedWriter(new FileWriter(file));
				final List<String> value = next.getValue();
				for (String s : value) {
					outWri.write(s);
					outWri.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (outWri != null) {
					try {
						outWri.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}


		}

	}


	/**
	 * @description: 根据条件拼装socketList（含ifsf和json数据）
	 * @author: lw
	 * @create:
	 **/
	private void socketList(String str, List<InnerSocketTest> lists, Simulation simulation) throws
			ParseException, UnsupportedEncodingException {
		//ifsf数据
		if (str.contains("ReceiveDITHandler") || str.contains("IFSFHandler")) {
			IFSFList(str, lists, simulation);
		}
		//json数据
		else if (str.contains("DitJsonHandler")) {
			JSONList(str, lists, simulation);
		}
	}

	private void IFSFList(String str, List<InnerSocketTest> lists, Simulation simulation) throws
			ParseException {
		String[] functionTypeArr = simulation.getFunctionType().split(",");
		for (String arr : functionTypeArr) {
			switch (arr) {
				case "液位数据":
					if (str.contains("IFSFInventory")) {
						functionTypeIfsfList(str, lists);
					}
					break;

//				case "加油开始结束数据":
//					if (str.contains("FuelingPointAndHoseStatus") && str.contains("解析完成：")) {
//						functionTypeIfsfList(str, lists, calendar);
//					}
//					break;
//				case "加油中数据":
//					if (str.contains("IFSFOnFueling") && str.contains("解析完成：")) {
//						functionTypeIfsfList(str, lists, calendar);
//					}
//					break;
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
	private void functionTypeIfsfList(String str, List<InnerSocketTest> lists) throws
			ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(str.substring(0, 24));
		InnerSocketTest innerSocketTest = new InnerSocketTest();
		innerSocketTest.setType(1);
		innerSocketTest.setTime(date);
		innerSocketTest.setMessage(str);
		//组装list
		lists.add(innerSocketTest);
	}

	private void JSONList(String str, List<InnerSocketTest> lists, Simulation simulation) throws
			ParseException, UnsupportedEncodingException {
		String[] functionTypeArr = simulation.getFunctionType().split(",");
		for (String arr : functionTypeArr) {
			switch (arr) {
				case "离线交易":
					if (str.contains("离线交易") || str.contains("OfflineRecord")) {
						funtionTypeJsonList(str, lists);
					}
					break;
				case "交易数据":
					if (str.contains("交易数据") || str.contains("OilTransaction")) {
						funtionTypeJsonList(str, lists);
					}
					break;
			}
		}
	}

	/**
	 * @description: 符合条件组装该条数据（json）
	 * @author: lw
	 * @create:
	 **/
	private void funtionTypeJsonList(String str, List<InnerSocketTest> lists) throws
			ParseException, UnsupportedEncodingException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(str.substring(0, 24));
		InnerSocketTest innerSocketTest = new InnerSocketTest();
		innerSocketTest.setType(2);
		innerSocketTest.setTime(date);
		innerSocketTest.setMessage(str);
		if (!lists.isEmpty()) {
			for (int i = lists.size() - 1; i > lists.size() - 10 && i >= 0; i--) {
				final InnerSocketTest innerSocketTest1 = lists.get(i);
				//重复数据
				if (innerSocketTest1.getMessage().substring(innerSocketTest1.getMessage().indexOf("originHex="))
						.equals(innerSocketTest.message.substring(innerSocketTest.message.indexOf("originHex=")))) {
					return;
				}
			}
			lists.add(innerSocketTest);
		} else {
			//组装list
			lists.add(innerSocketTest);
		}

	}

	@Data
	static class InnerSocketTest {
		//1 ifsf 2json
		private int type;
		private Date time;
		private String message;
	}


}
