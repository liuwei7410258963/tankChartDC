package com.oket.common.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author ：lw
 * @date ：Created in 2020/3/31 11:09
 * @description：
 */
public class ServiceLocator
{
    private ApplicationContext context;
    private static ServiceLocator instance = new ServiceLocator();

    public static ServiceLocator getInstance()
    {
        return instance;
    }

    public Object getService(String service) {
        return this.context.getBean(service);
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public void autowireService(Object bean) {
        ((AbstractApplicationContext)this.context).getBeanFactory().autowireBeanProperties(bean, 1, false);
    }
}
