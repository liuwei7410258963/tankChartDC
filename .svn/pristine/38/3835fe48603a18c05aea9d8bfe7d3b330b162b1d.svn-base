package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 油罐液位数据
 * @author: Longer
 * @create: 2019-11-08 22:49
 **/
@Data
public class Inventory implements ExtraTypeProvider, IDitJsonDataBody {
	/**
	 * 液位仪品牌
	 */
	@JSONField(alternateNames = "1")
	private String liquidLevelMeterBrand;
	/**
	 * 探棒编号
	 */
	@JSONField(alternateNames = "2")
	private String probeNo;
	/**
	 * 油高
	 */
	@JSONField(alternateNames = "3")
	private Double oilHeight;
	/**
	 * 水高
	 */
	@JSONField(alternateNames = "4")
	private Double waterHeight;
	/**
	 * 油体积
	 */
	@JSONField(alternateNames = "5")
	private Double oilVolume;
	/**
	 * 油水总体积
	 */
	@JSONField(alternateNames = "6")
	private Double totalVolume;
	/**
	 * 温度
	 */
	@JSONField(alternateNames = "7")
	private Double temp;
	/**
	 * 采集时间
	 */
	@JSONField(alternateNames = "8", format = "yyyy-MM-dd HH:mm:ss")
	private Date submitTime;
	/**
	 * 油品编码
	 */
	@JSONField(alternateNames = "9")
	private String oilNo;
	/**
	 * 安全罐存
	 */
	@JSONField(alternateNames = "10")
	private Double safeStock;

	@Override
	public Type getExtraType(Object object, String key) {
		switch (key) {
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "10":
				return Double.class;
			default:
				break;
		}
		return null;
	}

	/**
	 * 转为tank4station中的inventory
	 * @param inventoryList
	 * @return
	 */
	public static List<com.oket.tank4station.Inventory> convertInventory(List<Inventory> inventoryList) {
		if (inventoryList == null || inventoryList.isEmpty()) {
			return null;
		} else {
			return inventoryList.parallelStream().map(inventory -> {
				//这里提交时间的时间戳作为id
				com.oket.tank4station.Inventory inventory21 = new com.oket.tank4station.Inventory(
						Integer.parseInt(inventory.getProbeNo()),
						inventory.getSubmitTime(),
						inventory.getOilHeight(),
						inventory.getWaterHeight(),
						inventory.getTemp(),
						inventory.getOilVolume(),
						0.0D
				);
				inventory21.setWaterVolume(inventory.totalVolume - inventory.oilVolume);
				return inventory21;
			}).collect(Collectors.toList());
		}
	}
}
