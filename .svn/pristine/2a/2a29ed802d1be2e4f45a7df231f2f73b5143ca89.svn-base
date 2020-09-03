package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @description: 枪罐关系
 * @author: Longer
 * @create: 2019-11-08 21:59
 **/
@Data
public class TankRelNozzle implements  IDitJsonDataBody {
	private Long id;
	/**
	 * 油罐号
	 */
	@JSONField(alternateNames = "1")
	private String tankNo;
	/**
	 * 油枪号
	 */
	@JSONField(alternateNames = "2")
	private String nozzleNo;
	/**
	 * 油品号
	 */
	@JSONField(alternateNames = "3")
	private String oilNo;
	/**
	 * 填报时间--和获取时间相同
	 */
	@JSONField(alternateNames = "4", format = "YYYY-MM-dd HH:mm:ss")
	private Date fillDate;
	/**
	 * 获取时间--和填报时间相同
	 */
	@JSONField(alternateNames = "5", format = "YYYY-MM-dd HH:mm:ss")
	private Date submitTime;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TankRelNozzle that = (TankRelNozzle) o;
		return Objects.equals(tankNo, that.tankNo) &&
				Objects.equals(nozzleNo, that.nozzleNo) &&
				Objects.equals(oilNo, that.oilNo) &&
				Objects.equals(fillDate, that.fillDate) &&
				Objects.equals(submitTime, that.submitTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tankNo, nozzleNo, oilNo, fillDate, submitTime);
	}
}
