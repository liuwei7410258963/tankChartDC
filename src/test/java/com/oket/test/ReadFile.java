package com.oket.test;

import com.oket.oil.OilEntity;
import com.oket.oil.dao.OilDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2019-12-06 17:47
 **/
@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReadFile {
	@Resource
	private OilDAO oilDao;

	@Test
	public void initOil() throws IOException {
		File file = new File("./doc/ini.txt");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String s = bufferedReader.readLine();
		while (s != null && s.length() > 0) {
			final String[] split = s.split(";");
			OilEntity oilEntity = new OilEntity();
			oilEntity.setCode(split[0]);

			oilEntity.setFullName(split[1]);
			oilDao.insert(oilEntity);
			s = bufferedReader.readLine();
		}
	}


	public void tet(){
		write("D:\\ideawork\\tankChartDC\\socket_test\\1.log");

	}

	private void write(String filename){
		File file = new File(filename);

	}
}
