package com.oket.tankchartdc.mina.dit;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2020-04-08 19:03
 **/
public abstract class AbstractDitMessage<T> {

	/**
	 * 编码器
	 *
	 * @return
	 */
	public byte[] encode() {
		return new byte[0];
	}

	/**
	 * 解密器
	 *
	 * @param bytes
	 * @return
	 */
	public abstract T decode(byte[] bytes);
}
