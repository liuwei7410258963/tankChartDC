package com.oket.tankchartdc.mina.json.codec;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @description: dit json应答
 * @author: Longer
 * @create: 2019-11-11 16:19
 **/
@Data
public class DitJsonResponse {
	private static final Logger logger = LoggerFactory.getLogger(DitJsonResponse.class);

	/**
	 * 固定开始标识
	 */
	private static final String START_SIGN = "20101";
	public static final String FLAG_SUCCESS = "1";
	public static final String FLAG_FAILURE = "0";
	public static final String FLAG_MESSAGE_ERROR = "2";

	/**
	 * pid长度
	 */
	private byte pidLength;
	/**
	 * 根据数据处理服务平台规定的业务编
	 * 码，按照业务编码区分具体传输的业务
	 * 数据内容。
	 */
	private String pid;
	/**
	 * 1个字节的业务报文标识字节长度值
	 */
	private byte msgIDLength;
	/**
	 * 业务报文标识
	 * msgID ：客户端生成的
	 * 同业务类型的唯一消息标识（字符串内
	 * 容及字节长度由客户端决定，长度不能
	 * 大于255，建议规则统一。），服务器端直
	 * 接以字符串返回。
	 */
	private String msgID;
	/**
	 * 1=报文正确，SDBIP 处理成功；
	 * 0=报文正确，SDBIP 处理失败——即，
	 * 数据本身校验通过（请求报文、业务报
	 * 文体等格式基本正确），DIT 继续重发；
	 * 2=报文不完整或错误，SDBIP 拒绝处
	 * 理，DIT 跳过或删除当前数据，继续下
	 * 一条,其中 msgID 设为9999。
	 */
	private String flag;

	public static byte[] pack(DitJsonResponse response) {
		int length = START_SIGN.length() + 1 + response.getPidLength() + 1 + response.getMsgIDLength() + 1;
		byte[] bytes = new byte[length];
		byte[] startSignBytes = START_SIGN.getBytes(StandardCharsets.UTF_8);
		System.arraycopy(startSignBytes, 0, bytes, 0, startSignBytes.length);
		bytes[START_SIGN.length()] = response.getPidLength();
		byte[] pidBytes = response.getPid().getBytes(StandardCharsets.UTF_8);
		System.arraycopy(pidBytes, 0, bytes, START_SIGN.length() + 1, pidBytes.length);

		bytes[START_SIGN.length() + 1 + pidBytes.length] = response.getMsgIDLength();

		byte[] msgIDBytes = response.getMsgID().getBytes(StandardCharsets.UTF_8);
		System.arraycopy(msgIDBytes, 0, bytes, START_SIGN.length() + 1 + pidBytes.length + 1, msgIDBytes.length);
		bytes[bytes.length - 1] = response.getFlag().getBytes(StandardCharsets.UTF_8)[0];
		logger.debug("response:" + START_SIGN + response.getPidLength() + response.getPid() + msgIDBytes.length + response.getMsgID() + response.getFlag());
		return bytes;
	}
}
