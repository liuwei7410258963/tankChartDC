package com.oket.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @description: 序列化工具类
 * @author: SunBiaoLong
 * @create: 2020-04-11 09:32
 **/
@Slf4j
public class SerializeUtils {

	/**
	 * 序列化对象
	 * @param path
	 * @param object
	 */
	public static void serialize(String path, Object object) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File f = new File(path);
		if (f.isDirectory()) {
			f.mkdirs();
		} else {
			f.getParentFile().mkdirs();
		}
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
		} catch (IOException e) {
			log.error("对象序列化失败，对象：" + object+",路径："+path + ",error:" + e.getMessage(), e);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
	}
	public static Object restoreObject(String path) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object object = null;
		File f = new File(path);
		if (!f.exists()) {
			return null;
		}
		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			object = ois.readObject();
			return object;
		} catch (ClassNotFoundException | IOException e) {
			log.error("读取对象失败，路径：" +path + ",error:" + e.getMessage(), e);
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return object;
	}
}
