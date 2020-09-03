package com.oket.delivery;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import com.oket.dispenser.BzNozzleOut;
import com.oket.tank4station.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @description: 卸油数据
 * @author: SunBiaoLong
 * @create: 2019-12-10 11:14
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "bz_delivery")
public class BzDelivery extends BaseEntity {
	/**
	 * 审核数据
	 */
	public static final int AUDIT_DATA_TYPE = 1;
	/**
	 * 损益告警数据
	 */
	public static final int ALARM_DATA_TYPE = 2;
	/**
	 * 加油机付出数
	 */
	public static final int NOZZLE_OUT_DATA_TYPE = 3;

	/**
	 * 配送单号
	 */
	private String deliveryNo;
	/**
	 * 油罐号
	 */
	private Integer tankNo;
	/**
	 * 油品编码
	 */
	private String oilCode;
	/**
	 * 油品名称
	 */
	private String oilName;
	/**
	 * 收油升数
	 */
	private Double volume;
	/**
	 * 收油时间
	 */
	@JSONField(format = "yyyy-MM-dd")
	private Date receiveTime;
	/**
	 * 审核人
	 */
	private String auditor;
	/**
	 * 审核时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
	private DeliveryType deliveryType;
	/**
	 * 损耗量
	 */
	private Double loss;
	/**
	 * 库发量
	 */
	private Double stockVolume;
	/**
	 * 损耗报警
	 * 1:告警
	 * 0-正常
	 */
	private LossAlarm lossAlarm;
	/**
	 * 期间付出数
	 */
	private Double nozzleOut;
	/**
	 * 期间付出数集合
	 */
	@TableField(exist = false)
	private List<BzNozzleOut> nozzleOuts;
	/**
	 * 付出数id，逗号分隔
	 */
	@TableField(exist = false)
	private String outIds;
	/**
	 * 卸油报告的开始时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date reportStartTime;
	/**
	 * 卸油报告的结束时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date reportEndTime;
	/**
	 * 开始液位id
	 */
	private Long startInventoryId;

	/**
	 * 开始液位
	 */
	@TableField(exist = false)
	private Inventory startInventory;
	/**
	 * 结束液位的id
	 */
	private Long endInventoryId;
	/**
	 * 结束液位
	 */
	@TableField(exist = false)
	private Inventory endInventory;
	private String remark;
	/**
	 * 1.审核数据
	 * 2.损益告警数据
	 * 3.卸油时的付出数
	 */
	@TableField(exist = false)
	private Integer receiveDataType;
	/**
	 * 开始罐存--需要按照时间排序
	 */
	@TableField(exist = false)
	private List<Inventory> startInventories;
	/**
	 * * mysql5.7之后可以直接保存json
	 */
	@TableField(exist = false)
	private String startInventoryIds;
	/**
	 * 结束罐存--需要按照时间排序
	 */
	@TableField(exist = false)
	private List<Inventory> endInventories;

	/**
	 * 结束液位id，逗号分隔
	 * mysql5.7之后可以直接保存json
	 */
	@TableField(exist = false)
	private String endInventoryIds;

	//导出用
	@TableField(exist = false)
	private String expReceiveTime;

	/**
	 * 获取开始卸油的液位时点时间
	 *
	 * @return
	 */
	public Date getStartTime() {
		if (startInventories != null && !startInventories.isEmpty()) {
			return startInventories.get(0).getTime();
		} else {
			return reportStartTime;
		}
	}

	/**
	 * 获取结束卸油的液位时点时间
	 *
	 * @return
	 */
	public Date getEndTime() {
		if (endInventories != null && !endInventories.isEmpty()) {
			return endInventories.get(endInventories.size() - 1).getTime();
		} else {
			return reportEndTime;
		}
	}

}
