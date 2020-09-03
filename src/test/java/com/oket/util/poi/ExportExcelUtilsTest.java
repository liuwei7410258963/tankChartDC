package com.oket.util.poi;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExportExcelUtilsTest {

	@Test
	void exportExcel() {
		JSONObject jsonObject = new JSONObject();
		JsonTest jsonTest = new JsonTest();
		jsonTest.setName(1);
		JsonTest jsonTest2 = new JsonTest();
		jsonTest2.setName(2);
		jsonTest.setJsonTest(jsonTest);

		final JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(jsonTest));
		System.out.println(jsonObject1.getString("jsonTest.name"));
		final Object o = jsonObject1.getJSONObject("jsonTest").getString("name");
		System.out.println(o);
	}
}

@Data
class JsonTest {
	private Integer name;
	private JsonTest jsonTest;
}