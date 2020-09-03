package com.oket.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @description: 读取yml属性的utils
 * @author: SunBiaoLong
 * @create: 2019-12-20 17:23
 **/
@Slf4j
public class YmlUtils {
	/**
	 * 读取配置的配置属性
	 * 默认从application.yml获取
	 *
	 * @param key
	 * @param ymlNames yml文件名，可以输入多个
	 * @return
	 */
	private static String getPropertyValue(String key, String... ymlNames) throws Exception {
		if (ymlNames == null || ymlNames.length == 0) {
			ymlNames = new String[]{"application.yml"};
		}
		Properties properties = getProperties(ymlNames);
		//获取yml里的参数
		String active = null;
		if (properties != null) {
			active = properties.getProperty(key);
		}
		log.debug("key:" + key + ";value:" + active);
		return active;
	}

	private static Properties getProperties(String... ymlNames) throws Exception {
		if (ymlNames == null || ymlNames.length == 0) {
			throw new Exception("无效的yml路径:" + Arrays.toString(ymlNames));
		}
		ClassPathResource[] classPathResources = new ClassPathResource[ymlNames.length];
		for (int i = 0; i < ymlNames.length; i++) {
			classPathResources[i] = new ClassPathResource(ymlNames[i]);
		}
		YamlPropertiesFactoryBean yamlMapFactoryBean = new YamlPropertiesFactoryBean();
		//可以加载多个yml文件
		yamlMapFactoryBean.setResources(classPathResources);

		return yamlMapFactoryBean.getObject();
	}

	public static void writeToYml(Map<String, String> map, String ymlPath) throws Exception {
		if (map == null || map.isEmpty()) {
			return;
		}


		Properties properties = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(ymlPath);
			final Set<Map.Entry<String, String>> entries = map.entrySet();
			final Iterator<Map.Entry<String, String>> iterator = entries.iterator();
			while (iterator.hasNext()) {
				final Map.Entry<String, String> next = iterator.next();
				properties.setProperty(next.getKey(), next.getValue());
			}
			properties.store(output, "server  modify " + new Date().toString());
			// 保存键值对到文件中
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}


	/**
	 * 获取int类型的属性值
	 *
	 * @param key
	 * @param ymlNames yml文件名，可以输入多个
	 * @return
	 * @throws Exception
	 */
	public static int getIntProperty(String key, String... ymlNames) throws Exception {
		String value = YmlUtils.getPropertyValue(key, ymlNames);
		if (value != null && value.length() > 0) {
			return Integer.parseInt(value);
		} else {
			throw new Exception(getErrorMessage(key));
		}
	}

	/**
	 * 获取String类型的属性值
	 *
	 * @param key
	 * @param ymlNames yml文件名，可以输入多个
	 * @return
	 * @throws Exception
	 */
	public static String getStringProperty(String key, String... ymlNames) throws Exception {
		String value = YmlUtils.getPropertyValue(key, ymlNames);
		if (value != null) {
			return value;
		} else {
			throw new Exception(getErrorMessage(key));
		}
	}

	/**
	 * 获取boolean类型的属性值
	 *
	 * @param key
	 * @param ymlNames yml文件名，可以输入多个
	 * @return
	 * @throws Exception
	 */
	public static boolean getBooleanProperty(String key, String... ymlNames) throws Exception {
		String value = YmlUtils.getPropertyValue(key, ymlNames);
		if (value != null) {
			return Boolean.parseBoolean(value);
		} else {
			throw new Exception(getErrorMessage(key));
		}
	}

	/**
	 * 获取boolean类型的属性值
	 *
	 * @param key
	 * @param ymlNames yml文件名，可以输入多个
	 * @return
	 * @throws Exception
	 */
	public static double getDoubleProperty(String key, String... ymlNames) throws Exception {
		String value = YmlUtils.getPropertyValue(key, ymlNames);
		if (value != null) {
			return Double.parseDouble(value);
		} else {
			return 0.0;
		}
	}

	/**
	 * 获取错误信息
	 *
	 * @param key
	 * @return
	 */
	private static String getErrorMessage(String key) {
		return "读取配置文件失败,没有配置对应的配置项,key:" + key;
	}
}
