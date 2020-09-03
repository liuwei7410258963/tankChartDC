package com.oket.tankchartdc.websocket;

import lombok.Data;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2019-11-14 17:14
 **/
@Data
public class ProtocolBean {
	/**
	 * dit模拟器
	 */
	public static final int TYPE_DIT_EMULATOR = 1;
	/**
	 * json数据接收器
	 */
	public static final int TYPE_JSON_ACCEPTOR = 2;
	/**
	 * ifsf数据接收器
	 */
	public static final int TYPE_IFSF_ACCEPTOR = 3;
	private int type;
	private int port;
	private String ip;
	private String id;

	public ProtocolBean(int type, int port, String ip, String id) {
		this.type = type;
		this.port = port;
		this.ip = ip;
		this.id = id;
	}
}
