package com.oket.dispenser.dao;

import com.oket.TankChartDCApplication;
import com.oket.dispenser.BzNozzleOut;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = TankChartDCApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class NozzleOutDaoTest {
	@Autowired(required = false)
	private NozzleOutDao nozzleOutDao;

	@Test
	public void testGetNotMatched(){
		final List<BzNozzleOut> notMatched = nozzleOutDao.getNotMatched();
		if (notMatched!=null){
			System.out.println(notMatched);
		}
	}
}