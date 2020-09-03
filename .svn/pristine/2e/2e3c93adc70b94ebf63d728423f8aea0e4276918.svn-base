package com.oket.tankchartdc.mina.ifsf.bean;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class IFSFInventoryTest {
	@Test
	public void testNoFormat() {
		String json = "{\"value\":23.4545}";
		final NO no = JSONObject.parseObject(json, NO.class);
		System.out.println(no);
	}

	@Data
	public static class NO {
		@JSONField(format = "####.0")
		double value;
	}
}