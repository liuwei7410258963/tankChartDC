package com.oket.tank4station.tankchart;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 容积表
 *
 * @author sunBiaolong
 * @date 2020-04-27
 */
public class DBTankChart extends AbstractTankChart {

	private static final long serialVersionUID = 2651093304251872424L;

	public DBTankChart() {
	}

	public DBTankChart(List<TankChartValue> list) {
		for (TankChartValue tankChartValue : list) {
			addTankChartValue(tankChartValue);
		}
	}

	public static DBTankChart transform(String h_v) {
		DBTankChart dbTankChart = null;
		if (StringUtils.isNotEmpty(h_v)) {
			List<TankChartValue> list = new ArrayList<TankChartValue>();
			String[] temp = h_v.split("\\|");
			for (String row : temp) {
				String[] str = row.split("\\,");
				if (str.length >= 2) {
					TankChartValue tankChartValue = new TankChartValue(Integer.parseInt(str[0]), Double.parseDouble(str[1]));
					list.add(tankChartValue);
				}
			}
			dbTankChart = new DBTankChart(list);
		}
		return dbTankChart;
	}

	/**
	 * 转成容积表文件格式的字符串
	 *
	 * @return
	 */
	public static String toHVString(List<TankChartValue> values) {
		StringBuffer strBuff = new StringBuffer();
		for (TankChartValue volume : values) {
			strBuff.append(volume.getHeight()).append(",").append(volume.getVolume()).append("\n");
		}
		return strBuff.toString();
	}
}

