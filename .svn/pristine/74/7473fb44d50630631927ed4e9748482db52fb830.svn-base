package com.oket.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class YmlUtilsTest {

	@Test
	void writeToYml() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("asdf", "fer");
		YmlUtils.writeToYml(map, "oil-expansion.rate.yml");
	}

	/**
	 * DEFAULT=0.00126
	 * GASOLINE=0.00126
	 * DIESEL=0.00081
	 * OTHER=0.00126
	 */
	@Test
	void getYml() throws Exception {
		double oilExpansionRate = YmlUtils.getDoubleProperty("DEFAULT", "oil-expansion-rate.yml");
		System.out.println(oilExpansionRate);
		oilExpansionRate = YmlUtils.getDoubleProperty("GASOLINE", "oil-expansion-rate.yml");
		System.out.println(oilExpansionRate);
		oilExpansionRate = YmlUtils.getDoubleProperty("DIESEL", "oil-expansion-rate.yml");
		System.out.println(oilExpansionRate);
		oilExpansionRate = YmlUtils.getDoubleProperty("OTHER", "oil-expansion-rate.yml");
		System.out.println(oilExpansionRate);
	}
}