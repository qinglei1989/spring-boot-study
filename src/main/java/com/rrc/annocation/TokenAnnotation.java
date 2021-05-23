package com.rrc.annocation;


import java.lang.annotation.*;

/**
 * @Description: token注解类
 * @Author: wangql
 * @Date: Created in 15:25 2018/6/6
 * @Modified By:
 * @Version: V3.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TokenAnnotation {
}
