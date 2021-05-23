package com.rrc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @Description: Controller日志记录
 * @Author: Wangql
 * @Date: Created in 17:09 2018/6/11
 * @Modified By:
 * @Version:
 */
@Aspect
@Order(-5)
@Component
@Slf4j
public class WebLogAspect {

    /**
     * @描述
     * @参数  []
     * @返回值  void
     * @创建人  wangql
     * @创建时间  2018/6/12
     * @修改人和其它信息
     **/
    @Pointcut("execution(public * com.puhuijia.web.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容

        log.info("WebLogAspect.doBefore()");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu=request.getParameterNames();

        while(enu.hasMoreElements()){
            String paraName = enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));

        }

    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint){
        // 处理完请求，返回内容
        log.info("WebLogAspect.doAfterReturning()");
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

    }
}