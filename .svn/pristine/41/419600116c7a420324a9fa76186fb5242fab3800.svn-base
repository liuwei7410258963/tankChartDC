package com.oket.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Longer
 * @create: 2019-11-22 10:45
 **/


@Component
public class SpringUtil implements ApplicationContextAware {
	private final static Logger LOGGER = LoggerFactory.getLogger(SpringUtil.class);

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
		LOGGER.debug("---------------------------------------------------------------------");
		LOGGER.debug("---------------------------------------------------------------------");
		LOGGER.debug("---------------me.shijunjie.util.SpringUtil------------------------------------------------------");
		LOGGER.debug("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext=" + SpringUtil.applicationContext + "========");
		LOGGER.debug("---------------------------------------------------------------------");
	}

	//获取applicationContext
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	//通过name获取 Bean.
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	//通过class获取Bean.
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	//通过name,以及Clazz返回指定的Bean
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
}

