package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @description: 油品配送数据
 * @author: Longer
 * @create: 2019-11-08 23:18
 **/
@Data
public class Delivery implements ExtraTypeProvider, IDitJsonDataBody {
	/**
	 * 配送单号
	 */
	@JSONField(alternateNames = "1")
	private String deliveryNo;
	/**
	 * 油罐号
	 */
	@JSONField(alternateNames = "2")
	private String tankNo;
	/**
	 * 油品编码
	 */
	@JSONField(alternateNames = "3")
	private String oilNo;
	/**
	 * 收油升数
	 */
	@JSONField(alternateNames = "4")
	private Double litre;
	/**
	 * 收油时间
	 */
	@JSONField(alternateNames = "5")
	private Date receiveTime;
	/**
	 * 审核人
	 */
	@JSONField(alternateNames = "6")
	private String auditor;
	/**
	 * 审核时间
	 */
	@JSONField(alternateNames = "7", format = "YYYY-MM-dd HH:mm:ss")
	private Date auditorTime;
	/**
	 * 收油类型
	 * 1:收货
	 * 6:清罐/报废
	 * 7:站内调拨入库（倒罐）
	 * 8:站内调拨出库（倒罐）
	 * 10:站间调拨入库（移库）
	 * 11:站间调拨出库（移库）
	 */
	@JSONField(alternateNames = "8")
	private String receiveMode;
	/**
	 * 站内唯一id（serNum）
	 */
	@JSONField(alternateNames = "9")
	private String id;

	@Override
	public Type getExtraType(Object object, String key) {
		if ("4".equals(key)) {
			return Double.class;
		}
		return null;
	}
}
