/**
 *
 */
package com.oket.dispenser;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * 油枪编号和挂墙时间唯一确定一笔交易
 * @update 2019年11月26日
 * @author 王恒
 * @since 2016-04-27
 *
 */
@TableName(value = "bz_nozzle_out")
@Data
@Slf4j
public class BzNozzleOut implements Cloneable, Comparable<BzNozzleOut>, Serializable {
	private static final long serialVersionUID = 2611393484060954526L;
	/** volume double 单笔加油数据付油量，单位L，精确到小数点2位 */
	protected double volume;
	/** startTime Date 加油开始时间（提枪时间），精确到秒*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date startTime;
	/**  * endTime Date 加油结束时间（挂枪时间），精确到秒 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date endTime;
	/**
	 * @see Nozzle#nozzleID4Station;
	 * nozzleNo String 加油枪编号，加油站给定的编号，非系统标识
	 */
	protected String nozzleNo;
	/*** double 加油机泵码总累计数，单位升，精确到小数点后2位  */
	protected Double pumpSum;

	/** 流水号*/
	@TableId(type = IdType.AUTO)
	protected Long id;
	/**
	 * 油品编码
	 */
	protected String oilCode;
	/**
	 * 油品名称
	 */
	protected String oilName;
	/**
	 * 销售数据类型
	 */
	protected NozzleOutType type;

	/**
	 * 对应的油罐
	 */
	protected Integer tankNo;

	/**
	 * 预留字段，用于区分该付油数据是否是异常波动期间内的付油数据
	 * 如果是，项目启动中不再进行匹配
	 */
	private Integer reservedField;

	/**
	 * 排序，如果时间相同，且油枪编号相同，泵码数相同才对象相同。且对重复装载的数据做清0，以便后续去除。
	 */
	@Override
	public int compareTo(BzNozzleOut o) {

		int c = getStartTime().compareTo(o.getStartTime());
		if (c == 0) {

			c = getEndTime().compareTo(o.getEndTime());
		}
		if (c == 0) {
			c = this.getPumpSum().compareTo(o.getPumpSum());
			if (c == 0) {
				if (o.getNozzleNo().equals(nozzleNo)) {
					log.info("RARHoseOut比较对象，数据出现重复：" + o.toString() + "  this=" + toString());
					o.setVolume(0);
				}
			}

		}
		return c;
	}


	//付油组关系的id,展示用
	@TableField(exist = false)
	private int relId;
	//付油id,展示用
	@TableField(exist = false)
	private String outId;
}
