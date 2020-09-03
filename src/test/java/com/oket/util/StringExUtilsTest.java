package com.oket.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringExUtilsTest {

	@Test
	void byteArrToBinStr() {
	}

	@Test
	void byteToBinStr() {
		byte a = -9;
		System.out.println(StringExUtils.byteToBinStr(a));
	}
}