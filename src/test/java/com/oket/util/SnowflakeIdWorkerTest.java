package com.oket.util;

import org.junit.jupiter.api.Test;

class SnowflakeIdWorkerTest {

	/**
	 * 测试
	 */
	@Test
	public void test() {
		SimpleSnowflakeIdWorker idWorker = new SimpleSnowflakeIdWorker();
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			System.out.println(Long.toBinaryString(id));
			System.out.println(id);
		}
	}
}