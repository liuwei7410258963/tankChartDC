package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Objects;

/**
 * @description: 油罐基本信息
 * @author: Longer
 * @create: 2019-11-08 22:43
 **/
@Data
public class Tank implements ExtraProcessor, ExtraTypeProvider, IDitJsonDataBody {
	private Long id;
	/**
	 * 油罐号
	 */
	@JSONField(alternateNames = "1")
	private String tankNo;
	/**
	 * 油品编号
	 */
	@JSONField(alternateNames = "2")
	private String oilNo;
	/**
	 * 油罐罐容
	 */
	@JSONField(alternateNames = "3")
	private Double tankVolume;
	/**
	 * 采集时间
	 */
	@JSONField(alternateNames = "4", format = "yyyy-MM-dd HH:mm:ss")
	private Date submitTime;
	/**
	 * 是否启用
	 */
//	@JSONField(alternateNames = "5")
	private Boolean active;

	@Override
	public void processExtra(Object object, String key, Object value) {
		if ("5".equals(key)) {
			Tank tank = (Tank) object;
//				A表示启用，P表示停用
			tank.setActive(Boolean.valueOf("A".equals(value) ? "true" : "false"));
		}
	}

	@Override
	public Type getExtraType(Object object, String key) {
		switch (key) {
			case "3":
				return Double.class;
			default:
				break;
		}
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Tank tank = (Tank) o;
		return Objects.equals(tankNo, tank.tankNo) &&
				Objects.equals(oilNo, tank.oilNo) &&
				Objects.equals(tankVolume, tank.tankVolume) &&
				Objects.equals(submitTime, tank.submitTime) &&
				Objects.equals(active, tank.active);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tankNo, oilNo, tankVolume, submitTime, active);
	}
}
