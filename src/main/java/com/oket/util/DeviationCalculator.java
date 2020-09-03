package com.oket.util;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * DeviationCalculator
 *
 * @author wujh
 * @description 偏差计算，调用动态库运算
 * @date 2020/07/17
 */
public class DeviationCalculator {

	public static void loadDll(String dllFilePath){
		File file = new File(dllFilePath);
		System.load(file.getAbsolutePath());
	}
	/**
	 *计算系统偏差
	 * @param data
	 * @return
	 */
	public static native String calcDeviation(String data);
}
