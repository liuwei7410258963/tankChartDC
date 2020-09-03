package com.oket.tankchartdc;

import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description: 匹配异常
 * @author: SunBiaoLong
 * @create: 2020-05-18 16:25
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "BZ_MATCH_ALARM")
public class MatchAlarm extends BaseEntity {

	/**
	 * 没有找到液位组
	 */
	public final static int NOT_FOUND_INVENTORY_TRACE = 1;
	/**
	 * 液位组中没有付油信息
	 */
	public final static int INVENTORY_TRACE_WITHOUT_NOZZLE_OUT = 2;

	/**
	 * 匹配多条付油信息失败
	 */
	public final static int MATCH_MORE_TRACE_FAILURE = 3;
	/**
	 * 类型
	 */
	private int type;

	/**
	 * 开始付油时间
	 */
	private Date startTime;
	/**
	 * 结束付油时间
	 */
	private Date endTime;
	/**
	 * 油罐编号
	 */
	private int tankNo;
	/**
	 * 油枪编号
	 */
	private int nozzleNo;

	/**
	 * 付油信息id
	 */
	private long nozzleOutId;

	/**
	 * 付油信息的id.
	 */
	private String traceIds;

}
