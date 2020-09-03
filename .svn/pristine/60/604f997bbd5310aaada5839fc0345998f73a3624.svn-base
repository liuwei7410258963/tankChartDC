package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @description: 配送时的加油机付出量
 * @author: SunBiaoLong
 * @create: 2019-12-05 13:23
 **/
@Data
public class NozzleOutWhenDelivery implements ExtraTypeProvider, IDitJsonDataBody {
	//	201019P91_10035 报文字节长度 {“msgID":"9999","source":"加油站编码","milles":"请求时间
//		YYYY‐MM‐DD HH:MM:SS","data":[{"1":"油罐号","2":"收油量","3":"期间付出量","4":"收油开始时间","5":"收油结束时间", "6":"采集时间" , "7":"配送单号"}]}
	/**
	 * 油罐号
	 */
	@JSONField(alternateNames = "1")
	private String tankNo;
	/**
	 * 收油升数
	 */
	@JSONField(alternateNames = "2")
	private Double receiveLitre;
	/**
	 * 期间付出数
	 */
	@JSONField(alternateNames = "3")
	private Double nozzleOut;
	/**
	 * 开始时间
	 */
	@JSONField(alternateNames = "4", format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 结束时间
	 */
	@JSONField(alternateNames = "5", format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 时间
	 */
	@JSONField(alternateNames = "6", format = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	/**
	 * 配送单号
	 */
	@JSONField(alternateNames = "7")
	private String deliveryNo;

	@Override
	public Type getExtraType(Object o, String key) {
		if ("2".equals(key) || "3".equals(key)) {
			return Double.class;
		}
		return null;
	}
}
