package com.oket.oil.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @description: 油品配置类型
 * @author: SunBiaoLong
 * @create: 2020-07-20 20:13
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "oil")
public class OilConfig {
	/**
	 * 从配置文件读取配置属性
	 * 油品类型：膨胀率
	 * 或 油品编码：膨胀率
	 */
	private Map<String, Double> expansionRate;
}
