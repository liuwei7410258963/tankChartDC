package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.ifsf.bean.IDitIFSFDataBody;
import com.oket.tankchartdc.mina.ifsf.bean.IFSFInventory;
import com.oket.tankchartdc.mina.parser.ParserException;
import com.oket.util.StringExUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @description: ifsf液位仪数据库
 * @author: Longer
 * @create: 2019-11-12 19:09
 **/
public class InventoryDB extends BaseDB {
	private static final Logger logger = LoggerFactory.getLogger(InventoryDB.class);
	/**
	 * 油高类型
	 */
	public static final String OIL_HEIGHT = "40";
	/**
	 * 油水总高类型
	 */
	public static final String OIL_AND_WATER_VOLUME = "41";
	/**
	 * 油高类型
	 */
	public static final String OIL_VOLUME = "42";
	/**
	 * 温度类型
	 */
	public static final String TEMP = "43";
	/**
	 * 水高类型
	 */
	public static final String WATER_HEIGHT = "44";

	/**
	 * 处理液位仪数据库中的数据，将byte数组转为实体
	 *
	 * @param originBytes
	 * @param currentIndex
	 * @return
	 * @throws ParserException
	 */
	public static IDitIFSFDataBody parseDataBody(byte[] originBytes, int currentIndex) throws ParserException {
		Map<String, Object> dataMap = getDataMap(originBytes, currentIndex);
		Set<String> dataIds = dataMap.keySet();
		List<String> strings = Arrays.asList(
				OIL_HEIGHT,
				OIL_AND_WATER_VOLUME,
				OIL_VOLUME,
				TEMP,
				WATER_HEIGHT);
		if (dataIds.containsAll(strings)) {
			//液位数据
			IFSFInventory ifsfInventory = IFSFInventory.init(dataMap);
			return ifsfInventory;
		} else {
			//其他数据。不要
		}

		return null;
	}

	/**
	 * 将获取的数据根据类型转为key-value形式
	 *
	 * @param originBytes  原式数据
	 * @param currentIndex 当前所在的索引
	 * @return
	 * @throws ParserException
	 */
	private static Map<String, Object> getDataMap(byte[] originBytes, int currentIndex) throws ParserException {
		Map<String, Object> objectMap = new HashMap<>(0);
		while (currentIndex < originBytes.length) {
			String dataId = StringExUtils.byteToHexString(originBytes[currentIndex]);
			currentIndex++;
			if (currentIndex==originBytes.length){
				break;
			}
			logger.debug("currentIndex:" + currentIndex + ";dataId:0x" + dataId);
			switch (dataId) {
				case OIL_HEIGHT:
				case WATER_HEIGHT:
					currentIndex = getBcdValue(objectMap, originBytes, currentIndex, dataId);
					break;
				case OIL_AND_WATER_VOLUME:
				case OIL_VOLUME:
				case TEMP:
					currentIndex = getDoubleValue(objectMap, originBytes, currentIndex, dataId);
					break;
				default:
					currentIndex = otherData(originBytes, currentIndex);

//					throw new ParserException("未找到数据标识，当前标识是:" + dataId + ";原发数据体:" + StringExUtils.byteArrayToHexString(originBytes));
			}
		}
		return objectMap;
	}
}
