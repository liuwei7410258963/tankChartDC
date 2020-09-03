package com.oket.tankchartdc.mina.parser;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2019-11-09 11:12
 **/
public interface DitMessage<T extends DitMessageHeader, K extends DitMessageBody> {
	/**
	 * 获取头部数据
	 *
	 * @return
	 */
	T getHeader();

	/**
	 * 设置头部数据
	 *
	 * @param header
	 */
	void setHeader(T header);

	/**
	 * 获取消息体数据
	 *
	 * @return
	 */
	K getBody();

	/**
	 * 获取消息类型数据
	 *
	 * @param body
	 */
	void setBody(K body);
	/**
	 * 获取16进制原始数据
	 * @return
	 */
	String getOriginHex();
	/**
	 * 设置16进制原始数据
	 * @param hex
	 */
	void setOriginHex(String hex);
}
