package com.oket.config.logaop;

import com.alibaba.fastjson.JSONObject;
import com.oket.util.StringTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * AOP 管理日志
 * @author lw
 * @create 2019/11/26
 */
@Aspect
public class LogAspects {
    private final static Logger logger = LoggerFactory.getLogger(LogAspects.class);
    //抽取公共的切入点表达式
    @Pointcut("execution(public * com.oket.*.controller..*.*(..))")
    public void controllerMethod() {
    }

    @Before("controllerMethod()")
    public void logRequestInfo(JoinPoint joinPoint) throws Exception {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印请求内容
        logger.info("===============请求内容===============");
        logger.info("请求地址:"+ StringTools.getIP(request));
        logger.info("请求方式:"+request.getMethod());
        logger.info("请求类方法:"+joinPoint.getSignature());
        logger.info("请求类方法参数:"+ Arrays.toString(joinPoint.getArgs()));
        logger.info("===============请求内容===============");
    }


    @AfterReturning(returning = "jsonObject", pointcut = "controllerMethod()")
    public void logResultVOInfo(JSONObject jsonObject) throws Exception {
        logger.info("--------------返回内容----------------");
        logger.info("请求的结果："+jsonObject.toJSONString());
        logger.info("--------------返回内容----------------");
    }

    @AfterThrowing(value="controllerMethod()",throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e){
        logger.error("--------------出错了！---------------");
        logger.error(""+joinPoint.getSignature().getName()+"方法异常 ——错误信息是：{"+e+"}");
        logger.error("--------------出错了！----------------");
    }
}
