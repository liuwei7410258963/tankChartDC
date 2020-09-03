package com.oket.tankchartdc.service.impl;

import com.oket.tankchartdc.MatchAlarm;
import com.oket.tankchartdc.service.MatchAlarmService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MatchAlarmServiceImplTest {

	@Autowired
	private MatchAlarmService matchAlarmService;
	@Test
	public void save(){
		MatchAlarm matchAlarm = new MatchAlarm();
		matchAlarm.setTankNo(1);
		matchAlarm.setType(MatchAlarm.NOT_FOUND_INVENTORY_TRACE);
		matchAlarm.setNozzleOutId(1);
		matchAlarm.setStartTime(new Date());
		matchAlarm.setEndTime(new Date());
		matchAlarmService.save(matchAlarm);
	}
}