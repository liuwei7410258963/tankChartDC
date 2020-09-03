package com.oket.tankchartdc.mina.json.codec;

import com.alibaba.fastjson.JSONObject;
import com.oket.tankchartdc.mina.parser.DitMessage;
import lombok.Data;

/**
 * @description: dit json 数据
 * @author: SunBiaoLong
 * @create: 2019-11-09 11:37
 **/
@Data
public class DitJsonMessage implements DitMessage<DitJsonHeader, DitJsonBody> {
	/**
	 * 消息头数据
	 */
	private DitJsonHeader header;
	/**
	 * 消息体数据
	 */
	private DitJsonBody body;

	/**
	 * 原发数据 16进制
	 */
	private String originHex;
}
