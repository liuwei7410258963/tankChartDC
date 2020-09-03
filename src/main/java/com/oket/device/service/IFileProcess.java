package com.oket.device.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * @description: 文件处理
 * @author: SunBiaoLong
 * @create: 2019-11-14 10:23
 **/
public interface IFileProcess {
	Logger logger = LoggerFactory.getLogger(IFileProcess.class);
	String IFSF_FILE_NAME = "ifsf";
	String JSON_FILE_NAME = "json";
	String INVENTORY = "inventory";
	String DISPENSER = "dispenser";
	String DISPENSER_STATUS = "dispenser_status";
	String CONFIGURATION = "config";
	String DELIVERY = "delivery";
	String TANK_REL_GUN = "tank_rel_gun";

	/**
	 * 油罐信息文件名称
	 */
	String TANK_INFO_NAME = "tankInfo.txt";
	String HOSEOFFSET_NAME = "hoseoffset.txt";
	/**
	 * 枪罐关系映射文件名
	 */
	String TANK_REL_NOZZLE_MAPPING_NAME = "mapping.txt";
	String FIXEDHOSEOUT_NAME = "fixedhoseout.csv";
	String HOSEOUT_NAME = "hoseout.csv";
	String INVENTORY_NAME = "inventory.csv";

	/**
	 * 将内容写入到文件
	 *
	 * @param filePath
	 * @param fileName
	 * @param content
	 */
	void writeToFile(String filePath, String fileName, String content);

	/**
	 *
	 * @param filePath
	 * @param fileName
	 * @param content
	 */
	void writeToFile(String filePath, String fileName, List<String> content);

	/**
	 *
	 * @param filePath
	 * @param fileName
	 * @param content
	 */
	void appendToFile(String filePath, String fileName, String content);

	/**
	 *
	 * @param filePath
	 * @param fileName
	 * @param content
	 */
	void appendToFile(String filePath, String fileName, List<String> content);

}
