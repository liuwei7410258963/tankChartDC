package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.oket.dispenser.Dispenser;
import com.oket.dispenser.Nozzle;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @description: 加油机油枪关系
 * @author: Longer
 * @create: 2019-11-13 15:44
 **/
@Data
public class NozzleRelDispenser implements ExtraTypeProvider, IDitJsonDataBody {
	private Long id;
	/**
	 * 逻辑加油枪id
	 */
	@JSONField(alternateNames = "1")
	private String nozzleId;

	/**
	 * 逻辑油枪状态
	 * 1:在线，0离线
	 */
	@JSONField(alternateNames = "2")
	private String hoseStatus;
	/**
	 * 逻辑油枪状态变化时间
	 */
	@JSONField(alternateNames = "3", format = "YYYY-MM-dd HH:mm:ss")
	private Date hoseStatusTime;

	/**
	 * 逻辑加油机id
	 */
	@JSONField(alternateNames = "4")
	private String dispenserID;
	/**
	 * 逻辑加油机品牌
	 */
	@JSONField(alternateNames = "5")
	private String dispenserBrand;
	/**
	 * 加油点
	 */
	private Byte fuelingPoint;

	@Override
	public Type getExtraType(Object object, String key) {
		return null;
	}

	/**
	 * 转化为Dispenser
	 *
	 * @param nozzleRelDispensers
	 * @return
	 */
	public static List<Dispenser> convertToDispenser(List<NozzleRelDispenser> nozzleRelDispensers) {
		if (nozzleRelDispensers == null || nozzleRelDispensers.isEmpty()) {
			return null;
		} else {
			List<Dispenser> dispensers = new ArrayList<>();

			for (NozzleRelDispenser nozzleRelDispenser : nozzleRelDispensers) {
				String dispenserIDStr = nozzleRelDispenser.getDispenserID();
				int dispenserID = Integer.parseInt(dispenserIDStr);
				Dispenser dispenser = null;
				for (Dispenser temp : dispensers) {
					if (temp.getDispenserID() == dispenserID) {
						dispenser = temp;
						break;
					}
				}
				if (dispenser == null) {
					dispenser = new Dispenser(dispenserID, dispenserIDStr, null);
					dispensers.add(dispenser);
				}
				Nozzle hose = new Nozzle();
				hose.setNozzleID(Integer.parseInt(nozzleRelDispenser.getNozzleId()));
				hose.setNozzleName(nozzleRelDispenser.getNozzleId());
				hose.setDispenserID(dispenserID);
				dispenser.addHose(hose);
			}
			return dispensers;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NozzleRelDispenser that = (NozzleRelDispenser) o;
		return Objects.equals(nozzleId, that.nozzleId) &&
				Objects.equals(hoseStatus, that.hoseStatus) &&
				Objects.equals(hoseStatusTime, that.hoseStatusTime) &&
				Objects.equals(dispenserID, that.dispenserID) &&
				Objects.equals(dispenserBrand, that.dispenserBrand) &&
				Objects.equals(fuelingPoint, that.fuelingPoint);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nozzleId, hoseStatus, hoseStatusTime, dispenserID, dispenserBrand, fuelingPoint);
	}
}
