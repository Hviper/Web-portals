package com.example.springbootdemo1;

import com.example.springbootdemo1.Config.MyConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;


@ServletComponentScan(basePackages = "com.example.springbootdemo1.Filter")
@MapperScan("com.example.springbootdemo1.Mapper")
/**
 * 一般使用@EnableConfigurationProperties这个注解,加在启动类上,这样就知道哪些是自定义的配置文件,方便其他人使用.
 */
@EnableConfigurationProperties(MyConfig.class)   //将这个配置类注入到容器中，完成后续自动注入（@Autowired）到其他业务代码中
@SpringBootApplication
//@EnableScheduling         //开启定时任务
@EnableAsync             //开启异步注解
public class SpringbootDemo1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootDemo1Application.class, args);
//        System.out.println(run.getBean(Person.class));
        System.out.println("MyConfig配置如下："+run.getBean(MyConfig.class));
    }

}
