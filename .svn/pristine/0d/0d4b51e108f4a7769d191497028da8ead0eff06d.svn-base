package com.oket.tankchartdc.mina.ifsf.bean;

import com.oket.tank4station.Inventory;
import com.oket.tankchartdc.mina.ifsf.codec.InventoryDB;
import com.oket.util.DoubleUtils;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @description: 液位数据
 * @author: Longer
 * @create: 2019-11-12 19:07
 **/
@Data
public class IFSFInventory implements IDitIFSFDataBody {
	private double oilHeight;
	private double oilAndWaterVolume;
	private double oilVolume;
	private double temp;
	private double waterHeight;
	/**
	 * 数据时间
	 *
	 * @see IDitIFSFDataBody#TIME_FIELD_NAME
	 * 属性名不可以轻易修改
	 */
	private Date time;
	/**
	 *
	 */
	private String desc = "液位数据";

	/**
	 * 初始化构建
	 *
	 * @param dataMap
	 * @return
	 */
	public static IFSFInventory init(Map<String, Object> dataMap) {
		IFSFInventory ifsfInventory = new IFSFInventory();
		ifsfInventory.setOilHeight(
				Double.parseDouble((String) dataMap.get(InventoryDB.OIL_HEIGHT))
						/ Math.pow(10, 3));
		ifsfInventory.setWaterHeight(
				Double.parseDouble((String) dataMap.get(InventoryDB.WATER_HEIGHT))
						/ Math.pow(10, 3));
		ifsfInventory.setOilAndWaterVolume((Double) dataMap.get(InventoryDB.OIL_AND_WATER_VOLUME));
		ifsfInventory.setOilVolume((Double) dataMap.get(InventoryDB.OIL_VOLUME));
		ifsfInventory.setTemp((Double) dataMap.get(InventoryDB.TEMP));
		if (ifsfInventory.getTime()==null){
			ifsfInventory.setTime( new Date(System.currentTimeMillis()/1000*1000));
		}
		return ifsfInventory;
	}

	/**
	 * 转为业务中的罐存数据
	 *
	 * @param ifsfInventory
	 * @param tankNo
	 * @return
	 */
	public static Inventory convertInventory(IFSFInventory ifsfInventory, int tankNo) {
		if (ifsfInventory == null) {
			return null;
		} else {
			Date date = null;
			if (ifsfInventory.getTime() == null) {
				//数据库的时间会四舍五入.
				date = new Date(System.currentTimeMillis()/1000*1000);
			} else {
				date = new Date(ifsfInventory.getTime().getTime()/1000*1000);
			}
			Inventory inventory = new Inventory(
					tankNo,
					date,
					//保留一位小数即可
					DoubleUtils.round(ifsfInventory.oilHeight, 1),
					//保留一位小数即可
					DoubleUtils.round(ifsfInventory.waterHeight, 1),
					//温度保存两位
					DoubleUtils.round(ifsfInventory.temp, 2),
					//体积保存三位
					DoubleUtils.round(ifsfInventory.oilVolume, 3),
					0.0D);
			//体积保存三位
			inventory.setWaterVolume(DoubleUtils.round(ifsfInventory.oilAndWaterVolume - ifsfInventory.oilVolume, 3));
			return inventory;
		}
	}
}
