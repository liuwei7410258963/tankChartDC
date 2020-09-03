package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @description: 配送报警信息
 * @author: SunBiaoLong
 * @create: 2019-12-05 13:01
 **/
@Data
public class DeliveryLossAlarm implements ExtraTypeProvider, IDitJsonDataBody {
//	201019P91_10013 报文字节长度 {“msgID":"9999","source":"加油站编码","milles":"传输时间
//		YYYY‐MM‐DD HH:MI:SS","data":[{"1":"配送单号","2":"油品编码","3":"收油量","4":"损耗量","5":"单 笔损耗告警","6":"采集时间","7":"营业日"},{"1":"配送单号","2":"油品编码","3":"收油量","4":"损耗量","5":"单笔损耗告警","6":"采集时间","7":"营业日"}]}

	/**
	 * 配送单号
	 */
	@JSONField(alternateNames = "1")
	private String deliveryNo;
	/**
	 * 油品编码
	 */
	@JSONField(alternateNames = "2")
	private String oilCode;
	/**
	 * 收油量
	 */
	@JSONField(alternateNames = "3")
	private Double receiveLitre;
	/**
	 * 损耗量
	 */
	@JSONField(alternateNames = "4")
	private Double loss;
	/**
	 * 损耗报警
	 * 1:告警
	 * 0-正常
	 */
	@JSONField(alternateNames = "5")
	private Integer deliveryAlarmCode;
	/**
	 * 时间
	 */
	@JSONField(alternateNames = "6", format = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	/**
	 * 营业日
	 */
	@JSONField(alternateNames = "7", format = "yyyy-MM-dd")
	private Date date;

	@Override
	public Type getExtraType(Object o, String key) {
		if ("3".equals(key) || "4".equals(key)) {
			return Double.class;
		}else if ("5".equals(key)){
			return Integer.class;
		}
		return null;
	}
}
