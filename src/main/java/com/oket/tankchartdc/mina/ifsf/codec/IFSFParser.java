package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.ifsf.bean.IDitIFSFDataBody;
import com.oket.tankchartdc.mina.parser.DitParser;
import com.oket.tankchartdc.mina.parser.ParserException;
import com.oket.util.MathExtend;
import com.oket.util.StringExUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @description: 解析器
 * @author: Longer
 * @create: 2019-11-12 09:57
 **/
public class IFSFParser implements DitParser<IFSFHeader, IFSFMessage> {
	private static final Logger logger = LoggerFactory.getLogger(IFSFParser.class);

	@Override
	public IFSFHeader parseHeader(byte[] headerBytes) {
		IFSFHeader ifsfHeader = new IFSFHeader();
		ifsfHeader.setReceiveSubnet(headerBytes[0]);
		ifsfHeader.setReceiveNode(headerBytes[1]);
		ifsfHeader.setSendSubnet(headerBytes[2]);
		ifsfHeader.setSendNode(headerBytes[3]);
		ifsfHeader.setMessageCode(headerBytes[4]);
		MessageStatus messageStatus = MessageStatus.decode(headerBytes[5]);
		ifsfHeader.setMessageStatus(messageStatus);
		byte[] bodyLengthBytes = new byte[2];
		System.arraycopy(headerBytes, 6, bodyLengthBytes, 0, 2);
		ifsfHeader.setBodyLength(MathExtend.byte2Int(bodyLengthBytes));
		return ifsfHeader;
	}

	@Override
	public IFSFMessage parseMessage(byte[] bodyBytes, IFSFHeader header) throws ParserException {
		IFSFBody ifsfBody = new IFSFBody();
		int currentIndex = parseDatabaseLength(bodyBytes[0], ifsfBody);
		currentIndex = parseDatabase(bodyBytes, ifsfBody, currentIndex);
		return getMessage(header, ifsfBody, bodyBytes, currentIndex);
	}

	/**
	 * 解析ifsf数据库--
	 *
	 * @param bodyBytes
	 * @param ifsfBody
	 * @param currentIndex
	 * @return
	 */
	private int parseDatabase(byte[] bodyBytes, IFSFBody ifsfBody, int currentIndex) {
		byte[] dataBaseBytes = new byte[ifsfBody.getDataBaseAddressLength()];
		System.arraycopy(bodyBytes, currentIndex, dataBaseBytes, 0, dataBaseBytes.length);
		ifsfBody.setDataBaseAddress(dataBaseBytes);
		logger.debug("currentIndex:" + currentIndex
				+ ",database:0x" + StringExUtils.byteArrayToHexString(ifsfBody.getDataBaseAddress()));
		currentIndex += dataBaseBytes.length;
		return currentIndex;
	}

	/**
	 * 解析ifsf数据库地址长度
	 *
	 * @param bodyByte
	 * @param ifsfBody
	 * @return
	 */
	private int parseDatabaseLength(byte bodyByte, IFSFBody ifsfBody) {
		int currentIndex = 0;
		ifsfBody.setDataBaseAddressLength(bodyByte);
		logger.debug("currentIndex:" + 0 + ",databaseLength:" + ifsfBody.getDataBaseAddressLength());
		currentIndex++;
		return currentIndex;
	}

	/**
	 * 判断是属于哪个设备，哪个数据库
	 */
	public IFSFMessage getMessage(IFSFHeader header, IFSFBody ifsfBody, byte[] originBytes, int currentIndex) throws ParserException {
		byte[] dataBaseAddress = ifsfBody.getDataBaseAddress();
		//判断设备类型
		if (header.getSendSubnet() == 9) {
			//液位仪设备数据

			//判断属于那个数据库
			if (dataBaseAddress.length == 1
					&& dataBaseAddress[0] >= 0x21
					&& dataBaseAddress[0] <= 0x3F) {
				logger.debug("数据库类型为：液位仪数据库");
				IDitIFSFDataBody ditIFSFDataBody = InventoryDB.parseDataBody(originBytes, currentIndex);
				return buildMessage(header, ifsfBody, ditIFSFDataBody);
			}
		} else if (header.getSendSubnet() == 1) {
			//加油机数据

			//判断属于那个数据库
			if (dataBaseAddress.length == 4) {
				if (dataBaseAddress[0] >= 0x21
						&& dataBaseAddress[0] <= 0x24
						&& dataBaseAddress[1] == 0x21) {
					logger.debug("数据库类型为：交易数据库");

//					交易数据库
					IDitIFSFDataBody ditIFSFDataBody = TransactionDB.parseDataBody(originBytes, currentIndex);
					return buildMessage(header, ifsfBody, ditIFSFDataBody);
				}
			} else if (dataBaseAddress.length == 1) {
				if (dataBaseAddress[0] >= 0x21
						&& dataBaseAddress[0] <= 0x24) {
					logger.debug("数据库类型为：加油点数据库");

					//加油点数据库
					IDitIFSFDataBody ditIFSFDataBody = FuelingPointDB.parseDataBody(originBytes, currentIndex);
					return buildMessage(header, ifsfBody, ditIFSFDataBody);
				}
			}
		} else {
			//其他类型数据，不用管
		}
		return null;
	}

	/**
	 * 生成ifsfmessage实体
	 *
	 * @param header
	 * @param ifsfBody
	 * @param ditIFSFDataBody
	 * @return
	 */
	private IFSFMessage buildMessage(IFSFHeader header, IFSFBody ifsfBody, IDitIFSFDataBody ditIFSFDataBody) {
		ifsfBody.setDitIFSFDataBody(ditIFSFDataBody);
		if (header.isDitEmulatorEnable()) {
			processTime(ditIFSFDataBody, header.getMillis(), header.getMillisGap());
		}
		return new IFSFMessage(header, ifsfBody);
	}


	/**
	 * 处理时间，在dit模拟器启用的时候此方法执行
	 * 1.设置数据发送时间为传入的模拟数据时间
	 * 2.设置ifsf数据中包含的时间加上时间间隔
	 *
	 * @param object
	 * @param timeMillis
	 * @param gap
	 */
	public void processTime(Object object, long timeMillis, long gap) {
		final Class<?> aClass = object.getClass();
		final Field[] declaredFields = aClass.getDeclaredFields();
		if (declaredFields.length > 0) {
			for (Field declaredField : declaredFields) {
				if (declaredField.getType().equals(Date.class)) {
					declaredField.setAccessible(true);
					try {
						if (declaredField.getName().equals(IDitIFSFDataBody.TIME_FIELD_NAME)) {
							//数据发送时间设置为当前时间
							Date time = new Date(timeMillis);
							declaredField.set(object, time);
						} else {
							//ifsf包含的时间+时间间隔
							Date time = (Date) declaredField.get(object);
							if (time != null) {
								time.setTime(gap + time.getTime());
							}
						}
					} catch (IllegalAccessException e) {
						logger.error("dit模拟器处理时间失败", e);
					}
				}
			}
		}
	}
}


