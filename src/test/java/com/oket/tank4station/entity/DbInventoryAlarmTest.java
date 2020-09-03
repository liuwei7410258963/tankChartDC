package com.oket.tank4station.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DbInventoryAlarmTest {
	@Test
	public void test(){
		LocalDateTime localDateTime =  LocalDateTime.now();

		System.out.println(localDateTime);
		System.out.println(new Date());

	}

}