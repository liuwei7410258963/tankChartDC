package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.parser.DitMessageHeader;
import lombok.Data;

/**
 * @description: IFSF头部消息
 * @author: Longer
 * @create: 2019-11-12 09:56
 **/
@Data
public class IFSFHeader implements DitMessageHeader {

	/**
	 * 接收方子网
	 */
	private byte receiveSubnet;
	/**
	 * 接收方节点
	 */
	private byte receiveNode;
	/**
	 * 发送方子网
	 */
	private byte sendSubnet;
	/**
	 * 发送方节点
	 */
	private byte sendNode;
	/**
	 * 消息代码，0代表应用消息
	 */
	private byte messageCode;
	/**
	 * 消息状态
	 */
	private MessageStatus messageStatus;
	/**
	 * 消息长度--2字节表示长度
	 */
	private int bodyLength;
	/**
	 * 描述信息
	 */
	private String desc;
	/**
	 * 是否启用dit模拟器,默认不启用
	 */
	private boolean ditEmulatorEnable = false;
	/**
	 * dit模拟器发送数据时间
	 */
	private long millis;
	/**
	 * dit模拟器时间间隔
	 */
	private long millisGap;
}
