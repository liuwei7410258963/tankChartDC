package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.ifsf.bean.FuelingPointAndHoseStatus;
import com.oket.tankchartdc.mina.ifsf.bean.IDitIFSFDataBody;
import com.oket.tankchartdc.mina.ifsf.bean.IFSFOnFueling;
import com.oket.tankchartdc.mina.parser.ParserException;
import com.oket.util.StringExUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: 加油点数据库
 * @author: Longer
 * @create: 2019-11-12 19:01
 **/
public class FuelingPointDB extends BaseDB {
	private static final Logger logger = LoggerFactory.getLogger(FuelingPointDB.class);

	public static final String RUNNING_TRANSACTION_MESSAGE = "66";
	public static final String AMOUNT = "22";
	public static final String VOLUME = "23";
	public static final String STATUS_MESSAGE = "64";
	public static final String FUELING_POINT_STATUS = "14";
	public static final String LOGIC_HOSE_STATUS = "15";
	public static final String ASSIGN_CONTROLLER = "16";


	public static IDitIFSFDataBody parseDataBody(byte[] originBytes, int currentIndex) throws ParserException {
		Map<String, Object> dataMap = getDataMap(originBytes, currentIndex);
		Set<String> dataIds = dataMap.keySet();
		if (dataIds.contains(RUNNING_TRANSACTION_MESSAGE)) {
			return IFSFOnFueling.init(dataMap);
		} else if (dataIds.contains(STATUS_MESSAGE)) {
			return FuelingPointAndHoseStatus.init(dataMap);
		}
		return null;
	}

	private static Map<String, Object> getDataMap(byte[] originBytes, int currentIndex) throws ParserException {
		Map<String, Object> objectMap = new HashMap<>(0);
		while (currentIndex < originBytes.length) {
			String dataId = StringExUtils.byteToHexString(originBytes[currentIndex]);
			logger.trace("dataId:0x" + dataId);
			currentIndex++;
			if (currentIndex==originBytes.length){
				break;
			}
			switch (dataId) {
				case AMOUNT:
				case VOLUME:
					currentIndex = getDoubleValue(objectMap, originBytes, currentIndex, dataId);
					break;
				case STATUS_MESSAGE:
					currentIndex++;
					objectMap.put(STATUS_MESSAGE, null);
					break;
				case RUNNING_TRANSACTION_MESSAGE:
					currentIndex++;
					objectMap.put(RUNNING_TRANSACTION_MESSAGE, null);
					break;
				case FUELING_POINT_STATUS:
					currentIndex = getBinValue(objectMap, originBytes, currentIndex, dataId);
					break;
				case LOGIC_HOSE_STATUS:
					//油枪状态解析
					currentIndex = logicStatus(objectMap, originBytes, currentIndex, dataId);
					break;
				case ASSIGN_CONTROLLER:
					//:控制器解析，目前不需要这个属性
					currentIndex += 3;
					break;
				default:
					currentIndex = otherData(originBytes, currentIndex);
//					throw new ParserException("未找到数据标识，当前标识是:" + dataId + ";原发数据:" + StringExUtils.byteArrayToHexString(originBytes));
			}
		}
		return objectMap;
	}
}
