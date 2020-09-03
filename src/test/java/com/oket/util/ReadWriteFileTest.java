package com.oket.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadWriteFileTest {

	@Test
	void writeToCsv() {
		List<Object[]> values = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Object[] v = new Object[4];
			v[0] = 123.5;
			v[1] = "heel";
			v[2] = 123;
			v[3] ="2020-01-01 08:09:09";
			values.add(v);
		}
		try {
			ReadWriteFile.writeToCsv("./doc", "abc.csv",true, values);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}