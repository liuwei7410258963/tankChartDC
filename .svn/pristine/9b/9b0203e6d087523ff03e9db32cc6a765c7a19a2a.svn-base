package com.oket.tankchartdc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.tankchartdc.MatchAlarm;

/**
 * @description: 匹配异常service
 * @author: SunBiaoLong
 * @create: 2020-05-18 16:28
 **/
public interface MatchAlarmService extends IService<MatchAlarm> {
	/**
	 * 处理匹配信息
	 *
	 * @param matchAlarm
	 */
	void processMatchAlarm(MatchAlarm matchAlarm);

	/**
	 * 清空匹配异常缓存
	 */
	void clearMatchAlarmCache(long nozzleOutId );
}
