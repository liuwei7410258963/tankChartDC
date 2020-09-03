package com.oket.tankchartdc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.tankchartdc.MatchAlarm;
import com.oket.tankchartdc.dao.MatchAlarmDAO;
import com.oket.tankchartdc.service.MatchAlarmService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 匹配异常service
 * @author: SunBiaoLong
 * @create: 2020-05-18 16:29
 **/
@Service
public class MatchAlarmServiceImpl extends ServiceImpl<MatchAlarmDAO, MatchAlarm> implements MatchAlarmService {
	private Map<Long, List<MatchAlarm>> map = new ConcurrentHashMap<>();


	@Override
	public void clearMatchAlarmCache(long nozzleOutId) {
		List<MatchAlarm> matchAlarms = map.get(nozzleOutId);
		if (matchAlarms != null) {
			map.remove(nozzleOutId);
		}
	}

	@Override
	public void processMatchAlarm(MatchAlarm matchAlarm) {
		final long nozzleOutId = matchAlarm.getNozzleOutId();
		List<MatchAlarm> matchAlarms = map.get(nozzleOutId);
		if (matchAlarms == null) {
			matchAlarms = new ArrayList<>();
			matchAlarms.add(matchAlarm);
			map.put(nozzleOutId, matchAlarms);
			this.save(matchAlarm);
		} else {
			for (MatchAlarm alarm : matchAlarms) {
				if (alarm.getType() == matchAlarm.getType()) {
					return;
				}
			}
			matchAlarms.add(matchAlarm);
			this.save(matchAlarm);
		}
	}
}
