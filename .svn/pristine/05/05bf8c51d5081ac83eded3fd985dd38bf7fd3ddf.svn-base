package com.oket.util4biz;

import java.util.Date;

public class TimeInterval {
	public TimeInterval(Date startTime, Date endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	Date startTime;
	Date endTime;
	@Override
	public String toString() {
		String ret = String.format("%1$tY-%1$tm-%1$td %1$tT~%2$tm-%2$td %2$tT", startTime, endTime);
		return ret;
	}

	/**
	 * 给定的time时间是否在当前时间段内
	 *
	 * @param time
	 * @return
	 */
	public boolean isInclude(Date time) {
		boolean ret = false;
		if (time.before(endTime) && time.after(startTime)) {
			ret = true;
		}
		return ret;
	}
}