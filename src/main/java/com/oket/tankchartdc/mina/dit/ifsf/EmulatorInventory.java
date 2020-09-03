package com.oket.tankchartdc.mina.dit.ifsf;

import com.oket.tank4station.Inventory;
import com.oket.tankchartdc.mina.dit.AbstractDitMessage;
import com.oket.tankchartdc.mina.ifsf.bean.IFSFInventory;
import com.oket.tankchartdc.mina.ifsf.codec.*;
import com.oket.util.MathExtend;
import com.oket.util.StringExUtils;

import java.nio.charset.StandardCharsets;


/**
 * @description: 模拟的罐存数据
 * @author: SunBiaoLong
 * @create: 2020-04-08 18:28
 **/
public class EmulatorInventory extends AbstractDitMessage<EmulatorInventory> {
	/**
	 * ifsf消息实体
	 */
	private IFSFMessage ifsfMessage;
	private Inventory inventory;

	public EmulatorInventory(Inventory inventory) {
		//校验
		this.inventory = inventory;
		initMessage(inventory);
	}

	/**
	 * 初始消息
	 *
	 * @param inventory
	 */
	private void initMessage(Inventory inventory) {
		IFSFMessage ifsfMessage = new IFSFMessage();
		ifsfMessage.setBody(initBody(inventory));
		ifsfMessage.setHeader(initHeader());
		this.ifsfMessage = ifsfMessage;
	}

	/**
	 * 初始化消息实体
	 *
	 * @param inventory
	 * @return
	 */
	private IFSFBody initBody(Inventory inventory) {
		IFSFBody body = new IFSFBody();
		body.setDataBaseAddressLength((byte) 1);
		body.setDataBaseAddress(new byte[]{(byte) (inventory.getTankNo() + 0x20)});
		body.setDitIFSFDataBody(convert(inventory));
		return body;
	}

	/**
	 * 初始化消息头
	 *
	 * @return
	 */
	private IFSFHeader initHeader() {
		IFSFHeader ifsfHeader = new IFSFHeader();
		ifsfHeader.setReceiveSubnet((byte) 0x02);
		ifsfHeader.setReceiveNode((byte) 0x01);
		ifsfHeader.setSendSubnet((byte) 0x09);
		ifsfHeader.setSendNode((byte) 0x01);
		ifsfHeader.setMessageCode((byte) 0x01);
		ifsfHeader.setMessageStatus(MessageStatus.pack(MessageStatus.ACTIVE_MESSAGE_WITHOUT_CONFIRMATION, (byte) 0));
		return ifsfHeader;
	}

	/**
	 * Inventory转为IFSFInventory
	 *
	 * @param inventory
	 * @return
	 */
	private IFSFInventory convert(Inventory inventory) {
		IFSFInventory ifsfInventory = new IFSFInventory();
		ifsfInventory.setOilAndWaterVolume(inventory.getWaterVolume() + inventory.getVolume());
		ifsfInventory.setOilHeight(inventory.getLevel());
		ifsfInventory.setOilVolume(inventory.getVolume());
		ifsfInventory.setTemp(inventory.getTemperature());
		ifsfInventory.setWaterHeight(inventory.getWaterLevel());
		return ifsfInventory;
	}

	@Override
	public byte[] encode() {
		if (ifsfMessage == null) {
			return null;
		}
		final byte[] bodyBytes = bodyEncode();
		final byte[] headerBytes = headerEncode();
		byte[] timeBytes = timeEncode();
		byte[] bytes = new byte[headerBytes.length + bodyBytes.length + (timeBytes == null ? 0 : timeBytes.length)];
		System.arraycopy(headerBytes, 0, bytes, 0, headerBytes.length);
		System.arraycopy(bodyBytes, 0, bytes, headerBytes.length, bodyBytes.length);
		if (timeBytes != null) {
			System.arraycopy(timeBytes, 0, bytes, headerBytes.length + bodyBytes.length, timeBytes.length);

		}
		return bytes;
	}

	/**
	 * 时间编码，格式
	 * 时间间隔长度a（1byte）+原始数据时间戳长度b（1byte）+时间间隔（a*byte）+原始数据时间戳(b*byte)
	 *
	 * @return
	 */
	private byte[] timeEncode() {
		if (inventory.getTime() != null) {
			String time = inventory.getTime().getTime() + "";
			final byte[] temp = (0 + time).getBytes(StandardCharsets.UTF_8);
			byte[] timeBytes = new byte[temp.length + 2];
			timeBytes[0] = 1;
			timeBytes[1] = (byte) time.length();
			System.arraycopy(temp, 0, timeBytes, 2, temp.length);
			return timeBytes;
		}
		return null;
	}

	private byte[] headerEncode() {
		byte[] header = new byte[IFSFCodecDecoder.HEADER_LENGTH];
		header[0] = ifsfMessage.getHeader().getReceiveSubnet();
		header[1] = ifsfMessage.getHeader().getReceiveNode();
		header[2] = ifsfMessage.getHeader().getSendSubnet();
		header[3] = ifsfMessage.getHeader().getSendNode();
		header[4] = ifsfMessage.getHeader().getMessageCode();
		header[5] = ifsfMessage.getHeader().getMessageStatus().getResult();
		final byte[] bytes = MathExtend.integerTo2Bytes(ifsfMessage.getHeader().getBodyLength());
		System.arraycopy(bytes, 0, header, 6, bytes.length);
		return header;
	}

	private byte[] bodyEncode() {
		final byte[] invBytes = inventoryEncode();
		byte[] bytes = new byte[2 + invBytes.length];
		bytes[0] = 1;
		bytes[1] = ifsfMessage.getBody().getDataBaseAddress()[0];
		System.arraycopy(invBytes, 0, bytes, 2, invBytes.length);
		ifsfMessage.getHeader().setBodyLength(bytes.length);
		return bytes;
	}

	private byte[] inventoryEncode() {
		//5个数据标志，5个数据内容长度，27个数据内容
		int v = 5 + 5 + 27;
		byte[] bytes = new byte[v];

		int index = 0;

		bytes[index++] = StringExUtils.hexStringToByte(InventoryDB.OIL_HEIGHT)[0];
		bytes[index++] = 4;
		String bcd = (int) (inventory.getLevel() * Math.pow(10, 3)) + "";
		byte[] temp = StringExUtils.str2Bcd(bcd, 8);
		System.arraycopy(temp, 0, bytes, index, temp.length);
		index += temp.length;


		bytes[index++] = StringExUtils.hexStringToByte(InventoryDB.WATER_HEIGHT)[0];
		bytes[index++] = 4;
		bcd = (int) (inventory.getWaterLevel() * Math.pow(10, 3)) + "";
		temp = StringExUtils.str2Bcd(bcd, 8);
		System.arraycopy(temp, 0, bytes, index, temp.length);
		index += temp.length;


		bytes[index++] = StringExUtils.hexStringToByte(InventoryDB.TEMP)[0];
		bytes[index++] = 3;
		index = doubleConvertToBytes(bytes, index, inventory.getTemperature(), 4);
		bytes[index++] = StringExUtils.hexStringToByte(InventoryDB.OIL_VOLUME)[0];
		bytes[index++] = 7;
		index = doubleConvertToBytes(bytes, index, inventory.getVolume(), 12);
		bytes[index++] = StringExUtils.hexStringToByte(InventoryDB.OIL_AND_WATER_VOLUME)[0];
		bytes[index++] = 7;
		doubleConvertToBytes(bytes, index, inventory.getVolume() + inventory.getWaterVolume(), 12);
		return bytes;
	}

	/**
	 * double类型的值转为byte数组
	 *
	 * @param bytes
	 * @param index
	 * @param value
	 * @param doubleLength
	 * @return
	 */
	private int doubleConvertToBytes(byte[] bytes, int index, double value, int doubleLength) {
		int pointIndex = 0;

		byte[] temp;
		String symbol = null;
		if (value < 0) {
			symbol = "1";
			value = -value;

		} else {
			symbol = "0";
		}
		String vvvv = value + "";
		int vL = vvvv.length();
		if (vvvv.contains(".")) {
			if (vL > doubleLength + 1) {
				throw new IllegalArgumentException("无效的数字，小数和整数加起来最多有四位：" + value);
			} else {
				pointIndex = vvvv.indexOf(".");
				vvvv = vvvv.replace(".", "");
			}
		}
		if (vvvv.length() < doubleLength) {
			pointIndex += doubleLength - vvvv.length();
		}
		String s = Integer.toBinaryString(pointIndex);
		while (s.length() < 7) {
			s = "0" + s;
		}
		temp = StringExUtils.str2Bcd(vvvv, doubleLength);
		s = symbol + s;
		final byte b = StringExUtils.bitToByte(s);
		bytes[index++] = b;
		System.arraycopy(temp, 0, bytes, index, temp.length);
		index += temp.length;
		return index;
	}

	@Override
	public EmulatorInventory decode(byte[] bytes) {
		return null;
	}
}
