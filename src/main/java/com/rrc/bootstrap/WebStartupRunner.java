package com.rrc.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description: Springboot启动执行
 * @Author: wangql
 * @Date: Created in 17:33 2018/6/7
 * @Modified By:
 * @Version: V3.0
 */
@Component
public class WebStartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>SpringBoot 服务启动完毕<<<<<<<<<<<<<<<<<<");
    }

}
