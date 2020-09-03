package com.oket.device.service.impl;

import com.oket.device.service.IFileProcess;
import com.oket.util.ReadWriteFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @description: 文件保存
 * @author: SunBiaoLong
 * @create: 2020-02-14 08:52
 **/
@Component
public class FileProcess implements IFileProcess {
	@Value("${baseFilePath}")
	private String baseFilePath;

	@Override
	public void writeToFile(String filePath, String fileName, String content) {
		File file = ReadWriteFile.checkFileExist(filePath, fileName);
		ReadWriteFile.writeFile(file, content);
	}
	@Override
	public void writeToFile(String filePath, String fileName, List<String> content) {
		File file = ReadWriteFile.checkFileExist(filePath, fileName);
		ReadWriteFile.writeFile(file, content);
	}

	@Override
	public void appendToFile(String filePath, String fileName, String content) {

		File file = ReadWriteFile.checkFileExist(filePath, fileName);
		ReadWriteFile.appendTxtFile(file, content);
	}

	@Override
	public void appendToFile(String filePath, String fileName, List<String> content) {

		File file = ReadWriteFile.checkFileExist(filePath, fileName);
		ReadWriteFile.appendTxtFile(file, content);
	}
	/**
	 * 	 * 获取文件路径
	 * @param stationName 中文油站名
	 * @param tankId 油站id，形如tank01_3787  _后面是id，唯一的
	 * @return
	 */
	public String getFilePath(String stationName, String tankId) {
		return stationName + File.separator + tankId + File.separator + "data" + File.separator;
	}
}
