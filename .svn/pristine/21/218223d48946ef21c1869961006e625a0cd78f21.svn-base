package com.oket.tankchartdc.mina.json.codec;

import com.alibaba.fastjson.annotation.JSONField;
import com.oket.tankchartdc.mina.json.bean.DITApiEnum;
import com.oket.tankchartdc.mina.json.bean.IDitJsonBody;
import com.oket.tankchartdc.mina.json.bean.IDitJsonDataBody;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description: dit json协议中json部分数据
 * @author: SunBiaoLong
 * @create: 2019-11-09 11:13
 **/
@Data
public class DitJsonBody<E extends IDitJsonDataBody> implements IDitJsonBody<E> {
	/**
	 * 业务id,0~9999,循环使用
	 */
	private String msgID;
	/**
	 * 加油站编号
	 */
	private String source;
	/**
	 * 时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	/**
	 * 业务数据
	 */
	private List<E> bizData;
	/**
	 * 类型
	 */
	private DITApiEnum ditApiEnum;
}
