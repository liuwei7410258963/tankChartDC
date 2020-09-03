package com.oket.tankchartdc.mina.json.codec;

import com.oket.tankchartdc.mina.parser.DitMessageHeader;
import lombok.Data;

/**
 * @description: dit json报文头部消息
 * @author: SunBiaoLong
 * @create: 2019-11-09 10:28
 **/
@Data
public class DitJsonHeader implements DitMessageHeader {
	public static final String START_SIGN = "20101";
	/**
	 * pid长度
	 */
	private byte headLength;
	/**
	 * pid指的是消息类型
	 */
	private String pid;
	/**
	 * 消息体长度
	 */
	private int bodyLength;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * dit模拟器的中原始数据的发送时间和第一笔模拟器发送时间的时间差
	 */
	private long millisGap;
	/**
	 * 是否是dit模拟器数据启用，默认不启用
	 */
	private boolean ditEmulatorEnable = false;
}
