package com.oket.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathExtendTest {
	@Test
	public void test(){
		System.out.println(MathExtend.bytes2Int4(StringExUtils.hexStringToByte("000000D4")
		));
	}

}