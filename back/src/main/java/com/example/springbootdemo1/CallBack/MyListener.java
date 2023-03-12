package com.example.springbootdemo1.CallBack;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 拾光
 * @version 1.0
 */
@Component
public class MyListener implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(args.getNonOptionArgs());   //命令行参数  program arguments
        System.out.println("哈哈哈哈哈");
    }
}
