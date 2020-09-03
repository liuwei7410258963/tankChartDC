package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.util.MathExtend;
import com.oket.util.StringExUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @description: 数据库
 * @author: Longer
 * @create: 2019-11-12 19:10
 **/
public class BaseDB {
	private static final Logger logger = LoggerFactory.getLogger(BaseDB.class);

	protected static int getDoubleValue(Map<String, Object> objectMap,
	                                    byte[] originBytes, int currentIndex,
	                                    String dataId) {
		//长度
		byte aByte = originBytes[currentIndex];
		byte[] num = new byte[aByte];
		System.arraycopy(originBytes, currentIndex + 1, num, 0, num.length);
		double doubleNum = getDoubleNum(num);
		logger.trace("doubleNum:" + doubleNum);
		objectMap.put(dataId, getDoubleNum(num));
		return currentIndex + 1 + aByte;
	}

	protected static int getBcdValue(Map<String, Object> objectMap,
	                                 byte[] originBytes, int currentIndex,
	                                 String dataId) {
		byte aByte = originBytes[currentIndex];
		byte[] bcd = new byte[aByte];
		System.arraycopy(originBytes, currentIndex + 1, bcd, 0, bcd.length);
		String bcdStr = StringExUtils.bcd2Str(bcd);
		logger.trace("bcdStr:" + bcdStr);
		objectMap.put(dataId, bcdStr);

		return currentIndex + 1 + aByte;
	}

	protected static int getBinValue(Map<String, Object> objectMap,
	                                 byte[] originBytes, int currentIndex,
	                                 String dataId) {
		//长度
		byte aByte = originBytes[currentIndex];
		objectMap.put(dataId, originBytes[currentIndex + 1]);
		logger.trace("binValue:" + originBytes[currentIndex + 1]);
		return currentIndex + 1 + aByte;
	}

	/**
	 * 获取带有小数的数字
	 *
	 * @param bytes
	 * @return
	 */
	public static double getDoubleNum(byte[] bytes) {
		String s = StringExUtils.byteToBinStr(bytes[0]);
		while (s.length() < 8) {
			s = "0" + s;
		}
		//符号，0-正，1-负
		char symbol = s.charAt(0);
		//小数位
		byte pointIndex = StringExUtils.bitToByte("0" + s.substring(1));
		byte[] numBytes = new byte[bytes.length - 1];
		System.arraycopy(bytes, 1, numBytes, 0, numBytes.length);

		String numStr = StringExUtils.bcd2Str(numBytes);
		int positivePart = Integer.parseInt(numStr.substring(0, pointIndex));
		int negativePart = Integer.parseInt(numStr.substring(pointIndex));
		double amount = positivePart + negativePart / Math.pow(10, numBytes.length * 2 - pointIndex);
		//负数
		if (symbol == '1') {
			amount = -amount;
		}

		return amount;
	}

	protected static int logicStatus(Map<String, Object> objectMap,
	                                 byte[] originBytes, int currentIndex,
	                                 String dataId) {
		currentIndex++;
		byte originByte = originBytes[currentIndex];
		String s = Integer.toBinaryString(originByte);
		while (s.length() < 8) {
			s = "0" + s;
		}
		int logicIndex = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				logicIndex = (8 - i);
				break;
			}
		}
		objectMap.put(dataId, logicIndex);
		logger.trace("logicValue:" + logicIndex);
		currentIndex++;
		return currentIndex;
	}

	protected static int otherData(byte[] originBytes, int currentIndex) {
		byte aByte = originBytes[currentIndex];
		return currentIndex + 1 + aByte;
	}

}
