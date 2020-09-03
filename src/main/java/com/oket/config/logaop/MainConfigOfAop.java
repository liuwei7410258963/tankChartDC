package com.oket.config.logaop;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//配置
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAop {

    //切面类加入容器中
    @Bean
    public LogAspects logAspects(){
        return  new LogAspects();
    }
}
