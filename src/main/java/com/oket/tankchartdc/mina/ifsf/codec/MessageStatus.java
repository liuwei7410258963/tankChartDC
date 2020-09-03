package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.util.StringExUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 消息类型解析
 * @author: SunBiaoLong
 * @create: 2019-11-05 09:16
 **/
@Data
@NoArgsConstructor
public class MessageStatus {
	/**
	 * 读消息
	 */
	public static final String READ_MESSAGE = "000";
	/**
	 * 应答消息
	 */
	public static final String RESPONSE_MESSAGE = "001";
	/**
	 * 写消息
	 */
	public static final String WRITE_MESSAGE = "010";
	/**
	 * 主动消息
	 */
	public static final String ACTIVE_MESSAGE_WITH_CONFIRMATION = "011";
	/**
	 * 主动消息--无需应答
	 */
	public static final String ACTIVE_MESSAGE_WITHOUT_CONFIRMATION = "100";
	/**
	 * 确认消息
	 */
	public static final String CONFIRMATION_MESSAGE = "111";
	/**
	 * 类型
	 */
	private String type;
	/**
	 * token--一般不用
	 */
	private byte token;
	/**
	 * 原始数据
	 */
	private byte result;

	public MessageStatus(String type, byte token) {
		this.type = type;
		this.token = token;
	}

	/**
	 * 消息打包
	 *
	 * @param type  类型
	 * @param token token
	 * @return
	 */
	public static MessageStatus pack(String type, byte token) {
		MessageStatus messageStatus = new MessageStatus();
		String s1 = Integer.toBinaryString(token);
		if (s1.length() < 5) {
			String sppendString = "";
			for (int i = 0; i < 5 - s1.length(); i++) {
				sppendString += 0;
			}
			s1 = sppendString + s1;
		}
		type += s1;
		messageStatus.setType(type);
		messageStatus.setToken(token);
		messageStatus.setResult(StringExUtils.bitToByte(type));
		return messageStatus;
	}


	/**
	 * 解码--将byte转为消息类型实体
	 *
	 * @param result
	 * @return
	 */
	public static MessageStatus decode(byte result) {
		String s = Integer.toBinaryString(result);

		if (s.length() > 8) {
			s = s.substring(s.length() - 8);
		} else {
			while (s.length() < 8) {
				s = "0" + s;
			}
		}
		String type = s.substring(0, 3);
		byte token = StringExUtils.bitToByte("000" + s.substring(3));
		MessageStatus messageStatus = new MessageStatus(type, token);
		messageStatus.setResult(result);
		return messageStatus;
	}
}
