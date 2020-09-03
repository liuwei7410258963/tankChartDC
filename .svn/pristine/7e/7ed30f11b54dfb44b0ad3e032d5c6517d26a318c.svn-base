package com.oket.tankchartdc.mina.dit.ifsf;

import com.oket.tank4station.Inventory;
import com.oket.tankchartdc.mina.ifsf.bean.IFSFInventory;
import com.oket.tankchartdc.mina.ifsf.codec.*;
import org.junit.jupiter.api.Test;

class DitInventoryTest {

	@Test
	public void testInventory() throws Exception {
		Inventory inventory = new Inventory();
		inventory.setTankNo(1);

		inventory.setVolume(23422.3);
		inventory.setWaterVolume(1233.2);
		inventory.setLevel(1231.23);
		inventory.setWaterLevel(123.23);
		inventory.setTemperature(23.2);
		System.out.println("输入:"+ inventory);
		EmulatorInventory emulatorInventory = new EmulatorInventory(inventory);

		final byte[] encode = emulatorInventory.encode();
		final IFSFMessage parse = parse(encode);
		if (parse!=null) {
			final Inventory inventory21 = IFSFInventory.convertInventory((IFSFInventory) parse.getBody().getDitIFSFDataBody(), parse.getBody().getDataBaseAddress()[0] - 0x20);
			System.out.println("输出："+ inventory21);
		}
	}

	private IFSFMessage parse(byte[] bytes) throws Exception {

		byte[] headerBytes = new byte[IFSFCodecDecoder.HEADER_LENGTH];
		System.arraycopy(bytes, 0, headerBytes, 0, headerBytes.length);
		IFSFParser parser = new IFSFParser();
		IFSFHeader ifsfHeader = parser.parseHeader(headerBytes);
		System.out.println("bodyLength:" + ifsfHeader.getBodyLength() + ";sendSubnet:" + ifsfHeader.getSendSubnet() + ";sendNode:" + ifsfHeader.getSendNode());
		//判断是否接受数据完整
		if (ifsfHeader.getBodyLength() == bytes.length - headerBytes.length
				&& ifsfHeader.getBodyLength() > 0) {
			String type = ifsfHeader.getMessageStatus().getType();
			System.out.println("消息类型：" + type);
			if (type.equals(MessageStatus.ACTIVE_MESSAGE_WITH_CONFIRMATION)
					|| type.equals(MessageStatus.ACTIVE_MESSAGE_WITHOUT_CONFIRMATION)
					|| type.equals(MessageStatus.RESPONSE_MESSAGE)) {
				byte[] bodyBytes = new byte[ifsfHeader.getBodyLength()];
//			ioBuffer.get(bodyBytes, 0, bodyBytes.length);

				System.arraycopy(bytes, headerBytes.length, bodyBytes, 0, bodyBytes.length);

				IFSFMessage ifsfMessage = parser.parseMessage(bodyBytes, ifsfHeader);
				if (ifsfMessage != null) {
					ifsfMessage.setOriginBytes(headerBytes, bodyBytes);
					System.out.println("解析完成：" + ifsfMessage);
					return ifsfMessage;
				}else {
					throw new Exception("没有获取到消息实体");
				}

			} else {
				System.out.println("其他类型消息，不用管");
			}

		}else {
			throw new Exception("长度不相等：数据中的长度:" + headerBytes.length + ";实际长度：" + (bytes.length - headerBytes.length));
		}
		return null;
	}
}