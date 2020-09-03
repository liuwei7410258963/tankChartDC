package com.oket.tankchartdc.upload;

import lombok.Data;

import java.util.List;

/**
 * 模拟功能类
 */
@Data
public class Simulation {
	/**
	 * 文件名
	 */
	private List<String> fileName;
	/**
	 * 数据类型
	 */
	private String functionType;
	/**
	 * 倍速
	 */
	private int speed;
	/**
	 * 状态
	 */
	private String status;
}
