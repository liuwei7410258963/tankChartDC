/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LanguageUtil
 * Author:   Administrator
 * Date:     2018/6/13 13:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.oket.util.poi;

import java.util.HashMap;
import java.util.Map;

/**
 * 英文后台的导出的修改
 * 〈〉
 *
 * @author Administrator
 * @create 2018/6/13
 * @since 1.0.0
 */
public class LanguageUtil {

	private static Map<String, String> map = new HashMap<String, String>();

	static {
		map.put("启用", "Enable");
		map.put("禁用", "Disable");
		map.put("汽油", "Gasoline");
		map.put("柴油", "Diesel");
		map.put("其他", "Other");
		map.put("硬件版本号", "HardwareVersionNumber");
		map.put("升级版本号", "DisaUpgradeVersionNumberble");
		map.put("软件版本号", "SoftwareVersionNumbere");
		map.put("主程序", "TheMainProgram");
		map.put("升级程序", "Update");
		map.put("显示器升级", "DisplayUpgrade");
		map.put("是", "Yes");
		map.put("否", "No");
		map.put("管理员", "Administrator");
		map.put("未修改", "Unmodified");
		map.put("已修改", "TheModified");
		map.put("关停", "ShutDown");
		map.put("自动关停", "AutomaticallyShutDown");
		map.put("手动关停", "ManuallyShutDown");
		map.put("预留值", "TheReservedValues");
		map.put("手动启用", "ManuallyEnable");
		map.put("通用", "General");
		map.put("加油数据采集控制器", "RefuelingDataAcquisitionController");
		map.put("环境数据采集控制器", "EnvironmentDataAcquisitionController");
		map.put("通信故障", "CommunicationFailure");
		map.put("设备故障", "EquipmentFailure");
		map.put("无故障", "Trouble-free");
		map.put("储罐压力传感器", "TankPressureSensor");
		map.put("液阻压力传感器", "LiquidResistancePressureSensor");
		map.put("卸油区油气浓度传感器", "OilAndGasConcentrationSensorInUnloadingArea");
		map.put("处理装置排放浓度传感器", "TreatmentDeviceEmissionConcentrationSensor");
		map.put("储罐温度传感器", "TankTemperatureSensor");
		map.put("油气温度传感器", "OilGasTemperatureSensor");
		map.put("数据采集器", "DataCollector");
		map.put("上传服务器地址不可达", "TheUploadServerAddressIsNotReachable");
		map.put("与上传服务器连接超时", "TheConnectionToTheUploadServerHasTimedOut");
		map.put("参数配置异常", "ParameterConfigurationException");
		map.put("连接异常", "ConnectionException");
		map.put("表异常", "AbnormalTable");
		map.put("容量满", "FullCapacity");
		map.put("控制器（采集器）", "Controller (collector)");
		map.put("油气流量传感器", "OilGasFlowSensor");
		map.put("压力传感器", "PressureTransducer");
		map.put("浓度传感器", "ConcentrationSensor");
		map.put("温度传感器", "TemperatureSensor");
		map.put("控制台", "TheConsole");
		map.put("软件故障", "SoftwareFailures");
		map.put("数据库故障", "DatabaseFailure");
		map.put("正常", "Normal");
		map.put("枪", "TheGun");
		map.put("预警", "Warning");
		map.put("报警", "CallThePolice");
		map.put("无效", "Invalid");
		map.put("未上传", "NotUpload");
		map.put("已上传", "Uploaded");
		map.put("异常", "Abnormal");
		map.put("整点罐存", "IntegralPointCanSave");
		map.put("实时罐存", "Real-timeCanSave");
		map.put("自动交班罐存", "AutomaticShiftStorage");
		map.put("手动交班罐存", "ManualShiftCanStorage");


	}
	public static Object getValue(Object key) {
		if (key != null) {
			String value = map.get(key.toString());
			if (value != null) {
				return value;
			}
		}
		return key;
	}
}
