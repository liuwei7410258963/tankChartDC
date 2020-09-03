package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.ifsf.bean.IDitIFSFDataBody;
import com.oket.tankchartdc.mina.ifsf.bean.IFSFOfflineRecord;
import com.oket.tankchartdc.mina.ifsf.bean.IFSFTransaction;
import com.oket.tankchartdc.mina.json.bean.OfflineRecord;
import com.oket.tankchartdc.mina.parser.ParserException;
import com.oket.util.StringExUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @description: 交易数据库
 * @author: Longer
 * @_ENUMcreate: 2019-11-12 12:52
 **/
public class TransactionDB extends BaseDB {
	public final static byte SUBNET = 1;
	public static final Logger logger = LoggerFactory.getLogger(TransactionDB.class);

	/**
	 * 离线数据类型
	 */
	public static final String OFFLINE_DATA_ID = "C8";
	/**
	 * 金额数据类型
	 */
	public static final String AMOUNT_DATA_ID = "05";
	/**
	 * 体积数据类型
	 */
	public static final String VOLUME_DATA_ID = "06";
	/**
	 * 单价数据类型
	 */
	public static final String UNIT_PRICE_DATA_ID = "07";
	/**
	 * 状态数据类型
	 */
	public static final String LOGIC_HOSE_DATA_ID = "08";
	/**
	 * 油品数据类型
	 */
	public static final String OIL_NO_DATA_ID = "0A";
	/**
	 * 日期数据类型
	 */
	public static final String DATE_DATA_ID = "CA";
	/**
	 * 时间数据类型
	 */
	public static final String TIME_DATA_ID = "CB";
	/**
	 * 开始泵码数
	 */
	public static final String START_PUMP_CODE = "CC";
	/**
	 * 结束泵码数
	 */
	public static final String END_PUMP_CODE = "CD";

	/**
	 * 解析ifsf消息
	 *
	 * @param originBytes
	 * @param currentIndex
	 * @return
	 * @throws ParserException
	 */
	public static IDitIFSFDataBody parseDataBody(byte[] originBytes, int currentIndex) throws ParserException {
		Map<String, Object> dataMap = getDataMap(originBytes, currentIndex);
		Set<String> dataIds = dataMap.keySet();
		if (dataIds.contains(OFFLINE_DATA_ID)) {
			//离线数据
			return IFSFOfflineRecord.init(dataMap);
		} else if (dataIds.contains(VOLUME_DATA_ID) && dataIds.contains(LOGIC_HOSE_DATA_ID)) {
			//交易数据
			return IFSFTransaction.init(dataMap);
		}

		return null;
	}

	/**
	 * 解析byte，根据类型转为key-value形式
	 *
	 * @param originBytes
	 * @param currentIndex
	 * @return
	 * @throws ParserException
	 */
	private static Map<String, Object> getDataMap(byte[] originBytes, int currentIndex) throws ParserException {
		Map<String, Object> objectMap = new HashMap<>(0);
		while (currentIndex < originBytes.length) {
			currentIndex = getCurrentIndex(originBytes, currentIndex, objectMap);
		}
		return objectMap;
	}

	/**
	 * 1.解析当前数据
	 * 2.获取下次的开始的索引
	 *
	 * @param originBytes
	 * @param currentIndex
	 * @param objectMap
	 * @return
	 * @throws ParserException
	 */
	private static int getCurrentIndex(byte[] originBytes, int currentIndex, Map<String, Object> objectMap) throws ParserException {
		String dataId = StringExUtils.byteToHexString(originBytes[currentIndex]);
		currentIndex++;
		if (currentIndex==originBytes.length){
			return currentIndex;
		}
		logger.debug("dataId:0x" + dataId);
		switch (dataId) {
			case OFFLINE_DATA_ID:
				currentIndex++;
				objectMap.put(OFFLINE_DATA_ID, null);
				break;
			case AMOUNT_DATA_ID:
			case VOLUME_DATA_ID:
			case UNIT_PRICE_DATA_ID:
			case START_PUMP_CODE:
			case END_PUMP_CODE:
				currentIndex = getDoubleValue(objectMap, originBytes, currentIndex, dataId);
				break;
			case LOGIC_HOSE_DATA_ID:
				currentIndex = logicStatus(objectMap, originBytes, currentIndex, dataId);
				break;
			case OIL_NO_DATA_ID:
			case DATE_DATA_ID:
			case TIME_DATA_ID:
				currentIndex = getBcdValue(objectMap, originBytes, currentIndex, dataId);
				break;
			default:
				currentIndex = otherData(originBytes, currentIndex);
		}
		return currentIndex;
	}


}
