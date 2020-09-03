package com.oket;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.oket.util.StringExUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * springboot启动类
 *
 * @SpringBootApplication springboot启动类注解
 * @EnableTransactionManagement 开启事务
 * @MapperScan Mybatis-plus扫描的包路径
 */
@EnableScheduling
//@EnableAsync
@SpringBootApplication(scanBasePackages = "com.oket")
@EnableTransactionManagement
@MapperScan({"com.oket.station.dao", "com.oket.device.dao", "com.oket.tank4station.dao", "com.oket.oil.dao", "com.oket.delivery.dao", "com.oket.dispenser.dao", "com.oket.tankchartdc.dao","com.oket.common.dao"})
public class TankChartDCApplication {
	private final static Logger log = LoggerFactory.getLogger(TankChartDCApplication.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(TankChartDCApplication.class, args);
		log.info("-----------------------启动完成-----------------------");

	}

//	public static void main(String[] args) {
//		new SpringApplicationBuilder(TankChartDCApplication.class)
//				.beanNameGenerator(new CustomGenerator())
//				.run(args);
//		log.info("-----------------------启动完成-----------------------");
//
//	}
//	public static class CustomGenerator implements BeanNameGenerator {
//
//		@Override
//		public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
//			return definition.getBeanClassName()+ StringExUtils.generateString(5);
//		}
//	}

	/**
	 * 访问根路径会转到index.html
	 *
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

	/**
	 * 设置fastjson为默认的序列化
	 *
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		//创建FastJson信息转换对象
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		//创建FastJson对象并设定序列化规则
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
		fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		//规则赋予转换对象
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		return new HttpMessageConverters(fastJsonHttpMessageConverter, new StringHttpMessageConverter(StandardCharsets.UTF_8));
	}
}
