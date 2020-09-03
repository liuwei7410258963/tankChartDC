package com.oket.dispenser;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 付出数
 * @author: SunBiaoLong
 * @create: 2020-04-28 13:55
 **/
@Data
@Slf4j
public class TransientOut implements Cloneable {
	/**
	 * 油枪编号
	 */
	private int nozzleNo;
	/**
	 * 开始时间
	 */
	private long startTime;
	/**
	 * 结束时间
	 */
	private long endTime;
	/**
	 * 付出数
	 */
	private double out;
	/**
	 * 是否正在付油
	 */
	private boolean onFueling;

	public TransientOut(int nozzleNo, long startTime, boolean onFueling) {
		this.nozzleNo = nozzleNo;
		this.startTime = startTime;
		this.onFueling = onFueling;
		if (onFueling) {
			//结束时间一直在更新
			this.endTime = startTime;
		}
	}


	@Override
	public TransientOut clone() throws CloneNotSupportedException {
		Object out = null;
		try {
			out = super.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
		}
		return (TransientOut) out;
	}


}
