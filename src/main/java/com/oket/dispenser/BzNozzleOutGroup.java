package com.oket.dispenser;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.Status;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @description: 付油记录组
 * @author: SunBiaoLong
 * @create: 2020-04-02 11:21
 **/
@Data
@TableName("bz_nozzle_out_group")
public class BzNozzleOutGroup {
	/**
	 * 最大变化量，超过了需要确认
	 */
	public static final double MAX_VOLUME_GAP = 20.0D;

	/**
	 * 小批量加油升数
	 */
	public final static int SMALL_OUT = 10;
	public final static int SMALL_OUT_TIME = 10;
	/**
	 * 与上一条时间差不超过10秒，归为一条
	 */
	public final static int GAP_BETWEEN = 10;
	/**
	 * 小批量加油不超过60分钟，归为一条
	 */
	public final static int SMALL_OUT_GAP_BETWEEN = 60;
	/**
	 * 遇上一笔付油数据的间隔--10秒
	 */
	private final static int GAP_SECONDS = 10;
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	private Integer tankNo;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private Double volume;
	/**
	 * 持续时间
	 * 低于1分钟，按照一分钟记录
	 */
	@TableField(exist = false)
	private int minutes;

	public int getMinutes() {
		if (startTime != null && endTime != null) {
			long min = (endTime.getTime() - startTime.getTime()) / 1000 / 60;
			minutes = (int) min;
		}
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * 付油次数
	 */
	private int count;

	/**
	 * 是否启用，默认启用
	 */
	private Status status = Status.ENABLE;
	/**
	 * 是否需要确认
	 */
	private boolean needConfirm = false;
	/**
	 * 确认时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date confirmTime;

	/**
	 * 处理信息备注
	 */
	private String remark;

	@TableField(exist = false)
	private List<BzNozzleOut> bzNozzleOuts;
	/**
	 * 付油数据和付油组的关联关系
	 */
	@TableField(exist = false)
	private List<NozzleOutRelGroup> outRelGroups;

	/**
	 * 是否结束
	 */
	private boolean closed = false;

	public BzNozzleOutGroup() {
	}

	/**
	 * 计算分钟数
	 */
	public void calMinute() {
		if (startTime != null && endTime != null) {
			long min = (endTime.getTime() - startTime.getTime()) / 1000 / 60;
			minutes = (int) min;
		}
	}

	/**
	 * 付油组添加付油信息
	 *
	 * @param bzNozzleOut
	 */
	public void addNozzleOut(BzNozzleOut bzNozzleOut) {
		if (bzNozzleOut.getStartTime().getTime() < this.getStartTime().getTime()) {
			//向前扩展开始数据
			this.setStartTime(bzNozzleOut.getStartTime());
		}
		if (bzNozzleOut.getEndTime().getTime() > this.getEndTime().getTime()) {
			this.setEndTime(bzNozzleOut.getEndTime());
		}
		this.setCount(this.getCount() + 1);
		this.setVolume(this.getVolume() + bzNozzleOut.getVolume());
		calMinute();
	}

	/**
	 * 合并付油组
	 *
	 * @param bzNozzleOutGroup
	 */
	public void mergeGroup(BzNozzleOutGroup bzNozzleOutGroup) {
		if (bzNozzleOutGroup.getStartTime().getTime() < this.getStartTime().getTime()) {
			//向前扩展开始数据
			this.setStartTime(bzNozzleOutGroup.getStartTime());
		}
		if (bzNozzleOutGroup.getEndTime().getTime() > this.getEndTime().getTime()) {
			this.setEndTime(bzNozzleOutGroup.getEndTime());
		}
		this.setCount(this.getCount()+bzNozzleOutGroup.getCount());
		this.setVolume(this.getVolume() + bzNozzleOutGroup.getVolume());
		calMinute();
	}

	/**
	 * 构造器
	 *
	 * @param tankNo
	 * @param startTime
	 * @param endTime
	 * @param volume
	 * @param count
	 */
	public BzNozzleOutGroup(Integer tankNo, Date startTime, Date endTime, Double volume, int count) {
		this.tankNo = tankNo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.volume = volume;
		this.count = count;
		this.calMinute();
	}

	//校正图展示用
	@TableField(exist = false)
	private Long outGroupId;
	//校正图展示用
	@TableField(exist = false)
	private Long traceId;
	//校正图展示用
	@TableField(exist = false)
	private Long outRelTraceId;

}
