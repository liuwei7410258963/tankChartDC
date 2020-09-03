package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.parser.DitMessage;
import com.oket.util.StringExUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: ifsf消息实体
 * @author: Longer
 * @create: 2019-11-12 09:57
 **/
@Data
@NoArgsConstructor
public class IFSFMessage implements DitMessage<IFSFHeader, IFSFBody> {
	/**
	 * ifsf消息头
	 */
	private IFSFHeader header;
	/**
	 * ifsf消息体
	 */
	private IFSFBody body;
	/**
	 * 原始数据（16进制）
	 */
	private String originHex;

	/**
	 * 构造器
	 *
	 * @param header
	 * @param body
	 */
	public IFSFMessage(IFSFHeader header, IFSFBody body) {
		this.header = header;
		this.body = body;
	}

	/**
	 * 设置获取到的原始数据
	 * @param headerBytes
	 * @param bodyBytes
	 */
	public void setOriginBytes(byte[] headerBytes, byte[] bodyBytes) {
		byte[] originBytes = new byte[headerBytes.length + bodyBytes.length];
		System.arraycopy(headerBytes, 0, originBytes, 0, headerBytes.length);
		System.arraycopy(bodyBytes, 0, originBytes, headerBytes.length, bodyBytes.length);
		this.setOriginHex(StringExUtils.byteArrayToHexString(originBytes));
	}
}
