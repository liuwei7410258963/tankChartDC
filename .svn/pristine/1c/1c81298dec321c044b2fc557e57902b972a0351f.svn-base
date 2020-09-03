package com.oket.device;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 油罐
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-14
 */
@Data
@Accessors(chain = true)
@TableName("dev_tank_info")
public class TankInfo extends AbstractDeviceItem<Version> {
	private static final long serialVersionUID = 1L;
	/**
	 * 版本 Set<DeviceVersion>
	 */
	@TableField(exist = false)
	private List<Version> deviceVersions;
	/**
	 * 油罐编号
	 */
	private Integer tankNo;
	/**
	 * 油品Id
	 */
	@TableField("OIL_ID")
	private Integer oilId;
	/**
	 * 油品code
	 */
	private String oilCode;
	/**
	 * 油罐高度
	 */
	@TableField("HEIGHT")
	private Integer height;
	/**
	 * 油罐最大容积
	 */
	@TableField("MAX_VOLUME")
	private Double maxVolume;
	/**
	 * 直径
	 */
	@TableField("DIAMETER")
	private Integer diameter;
	/**
	 * 高液位报警值
	 */
	@TableField("HIGH_OIL_ALARM")
	private Integer highOilAlarm;
	/**
	 * 高液位预警值
	 */
	@TableField("HIGH_OIL_WARN")
	private Integer highOilWarn;
	/**
	 * 、
	 * 低液位报警值
	 */
	@TableField("LOW_OIL_ALARM")
	private Integer lowOilAlarm;
	/**
	 * 低液位预警值
	 */
	@TableField("LOW_OIL_WARN")
	private Integer lowOilWarn;
	/**
	 * 高水位报警
	 */
	@TableField("HIGH_WATER_ALARM")
	private Integer highWaterAlarm;
	/**
	 * 高温报警
	 */
	@TableField("HIGH_TEMP_ALARM")
	private Integer highTempAlarm;
	/**
	 * 低温报警
	 */
	@TableField("LOW_TEMP_ALARM")
	private Integer lowTempAlarm;
	/**
	 * 水盲区
	 */
	@TableField("WATER_BLIND")
	private Double waterBlind;
	/**
	 * 油盲区
	 */
	@TableField("OIL_BLIND")
	private Double oilBlind;
	/**
	 * 油品名称
	 */
	private String oilName;
	/**
	 * 主圆筒平均直径，单位mm
	 */
	@TableField("D1")
	private Double D1;
	/**
	 * D1主圆桶是否已知；默认主圆桶直径即为罐高，故默认已知；
	 */
	@TableField("TRUE_D1")
	private Boolean trueD1 = true;
	/**
	 * 封头直边内直径，默认与D1相同
	 */
	@TableField("D2")
	private Double D2;
	/**
	 * 封头直边直径D2是否已知；默认与D1相同且已知
	 */
	@TableField("TRUE_D2")
	private Boolean trueD2 = true;
	/**
	 * 主圆筒内长
	 */
	@TableField("L1")
	private Double L1;
	/**
	 * 主圆桶长度是否已知；默认未知
	 */
	@TableField("TRUE_L1")
	private Boolean trueL1 = false;
	/**
	 * 两端封头直边内长的平均值
	 */
	@TableField("L2")
	private Double L2;
	/**
	 * 封头直边圆桶长度已知；默认已知
	 */
	@TableField("TRUE_L2")
	private Boolean trueL2 = true;
	/**
	 * 两端封头内长的平均值
	 */
	@TableField("L3")
	private Double L3;
	/**
	 * 封头长度是否已知；默认未知
	 */
	@TableField("TRUE_L3")
	private Boolean trueL3 = false;
	/**
	 * 封头类型
	 */
	@TableField("HEAD_TYPE")
	private int headType;
	/**
	 * 封头类型是否已知；默认未知
	 */
	@TableField("TRUE_TYPE")
	private Boolean trueType = false;
	/**
	 * 液位仪厂家
	 */
	@JSONField(alternateNames = "1")
	private String probeManufactor;
	/**
	 * 液位仪类型
	 */
	@JSONField(alternateNames = "1")
	private String probeType;

	@TableField(exist = false)
	private String validity;//有效期

	@TableField(exist = false)
	private List<Integer> relNozLists;//关联的油枪list
}
