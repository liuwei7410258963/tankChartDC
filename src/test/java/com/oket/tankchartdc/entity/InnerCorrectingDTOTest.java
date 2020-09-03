package com.oket.tankchartdc.entity;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InnerCorrectingDTOTest {

	@Test
	public void getOutRate() {
		Random random = new Random(1);
		double v= 13 + random.nextDouble() * 4;
		System.out.println(v);

	}
}