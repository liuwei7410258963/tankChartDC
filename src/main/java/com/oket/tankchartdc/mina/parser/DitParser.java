package com.oket.tankchartdc.mina.parser;

/**
 * @description: dit解析接口
 * @author: SunBiaoLong
 * @create: 2019-11-09 11:08
 **/
public interface DitParser<T extends DitMessageHeader, X extends DitMessage> {
	/**
	 * 解析头部数据
	 *
	 * @param headerBytes
	 * @return
	 */
	T parseHeader(byte[] headerBytes) throws ParserException;

	/**
	 * 解析消息数据
	 *
	 * @param bodyBytes
	 * @param header
	 * @return
	 */
	X parseMessage(byte[] bodyBytes, T header) throws ParserException, IllegalAccessException, InstantiationException;
}
