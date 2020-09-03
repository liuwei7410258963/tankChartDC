package com.oket.util;

import com.alibaba.fastjson.JSONObject;
import com.oket.tank4station.AbstractLevelTrace;
import com.oket.tank4station.NewInventoryTrace;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerializeUtilsTest {

	@Test
	void serialize() {
		AbstractLevelTrace abstractLevelTrace = new NewInventoryTrace(null);
		SerializeUtils.serialize("D:/a.data", abstractLevelTrace);

	}


	@Test
	void json() throws IOException {
		AbstractLevelTrace abstractLevelTrace = new NewInventoryTrace(null);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("D:/a.data")));
		bufferedWriter.write(JSONObject.toJSONString(abstractLevelTrace));
		bufferedWriter.close();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("D:/a.data")));
		final String s = bufferedReader.readLine();
		final NewInventoryTrace newInventoryTrace = JSONObject.parseObject(s, NewInventoryTrace.class);
		System.out.println(newInventoryTrace);


	}
	@Test
	void restoreObject() {
		final Object o = SerializeUtils.restoreObject("D:/a.data");
		assert o != null;
		assertEquals(o.getClass(), NewInventoryTrace.class);
		System.out.println(o);

	}
}