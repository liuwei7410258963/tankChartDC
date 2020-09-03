package com.oket.tank4station.websocket;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

class BizMessageTypeTest {

	@Test
	void test(){
		System.out.println(JSONObject.toJSONString(BizMessageType.INVENTORY));
	}

}