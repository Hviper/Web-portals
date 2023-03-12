package com.example.springbootdemo1.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 拾光
 * @version 1.0
 */
@Data
@ConfigurationProperties("myredis.task")
public class MyConfig {
    private String host;
    private int port;
    private Config config;
}
@Data
class Config{
    private String info;
    private int ip;
}
