package com.oket.tank4station.service.impl;

import com.oket.TankChartDCApplication;

import com.oket.tankchartdc.DbInventoryCycle;
import com.oket.tankchartdc.service.DbInventoryCycleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TankChartDCApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DbInventoryCycleServiceImplTest {
	@Autowired
	private DbInventoryCycleService dbInventoryCycleService;

	@Test
	public void saveCycle(){
		DbInventoryCycle dbInventoryCycle = new DbInventoryCycle();
		dbInventoryCycle.setTankNo(1);
		dbInventoryCycle.setOilName("95");
		System.out.println(dbInventoryCycleService.save(dbInventoryCycle));
	}
}