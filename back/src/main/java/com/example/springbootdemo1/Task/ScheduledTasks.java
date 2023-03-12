package com.example.springbootdemo1.Task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 拾光
 * @version 1.0
 */
@Component
@Slf4j
public class ScheduledTasks {

    @Scheduled(cron = "0 0 0 0 1/1 ? *")
    public void service(){
        log.debug("定时任务执行。。。");
    }
}
