package com.oket.device;

import org.junit.jupiter.api.Test;

import javax.sound.midi.SoundbankResource;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TankInfoTest {

	@Test
	void test(){
		TankInfo tankInfo = new TankInfo();
		tankInfo.setCheckTime(new Date());
		System.out.println(tankInfo.getCheckTime().toLocaleString());
	}

}