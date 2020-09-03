package com.oket.tankchartdc.mina.ifsf.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author SunBiaoLong
 * @description: dit ifsf数据体标识
 * @date 2019-12-07
 **/
public interface IDitIFSFDataBody {
	/**
	 * 数据时间的属性名--
	 * 处理dit模拟数据的时间时使用
	 */
	String TIME_FIELD_NAME = "time";

	/**
	 * 设置数据时间
	 *
	 * @param date
	 */
	void setTime(Date date);

	/**
	 * 获取数据时间
	 *
	 * @return
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	Date getTime();

	/**
	 * 获取描述信息
	 *
	 * @return
	 */
	String getDesc();
}
