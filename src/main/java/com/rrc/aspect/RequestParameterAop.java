package com.rrc.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RequestParameterAop
 * @Description TODO
 * @Author wang
 * @Date 2021/6/24 23:13
 * @Version 1.0
 **/
@Component
@Aspect
@Slf4j
public class RequestParameterAop {
    /**
     * @Description: 定义需要拦截的切面
     * @Return: void
     **/
    @Pointcut("execution(* com.rrc.service.impl.*ServiceImpl.*(..))")
    public void methodArgs() {

    }

    @AfterReturning("methodArgs()")
    public Object invoke(JoinPoint joinPoint) throws Throwable {
        Object result = null;

        Signature signature = joinPoint.getSignature();
        // 方法名
        String methodName = signature.getName();
        // 类名
        String serviceName = signature.getDeclaringTypeName();

        // 参数名数组
        String[] parameterNames = ((MethodSignature) signature).getParameterNames();
        // 构造参数组集合
        List<Object> argList = new ArrayList<>();
        for (Object arg : joinPoint.getArgs()) {
            // request/response无法使用toJSON
            if (arg instanceof HttpServletRequest) {
                argList.add("request");
            } else if (arg instanceof HttpServletResponse) {
                argList.add("response");
            } else {
                argList.add(JSON.toJSON(arg));
            }
        }
        try {
            log.info("{} -> 方法({}) -> 参数:{} - {}", serviceName, methodName, JSON.toJSON(parameterNames), JSON.toJSON(argList));
        } catch (Exception e) {
            log.error("参数获取失败: {}", e.getMessage());
        }
        //result = joinPoint.proceed();
        return result;
    }
}
