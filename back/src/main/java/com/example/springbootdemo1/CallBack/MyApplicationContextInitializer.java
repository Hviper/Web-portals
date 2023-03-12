package com.example.springbootdemo1.CallBack;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author 拾光
 * @version 1.0
 * 该监听器不仅需要注入到容器中（可以不写），也需要将其写到配置文件META-INF/spring.factories文件中
 */
//@Component   //
public class MyApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        /**
         * 项目启动之前完成初始化代码工作
         */
        System.out.println("init.......");
    }
}
