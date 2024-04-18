package com.example.nuonuo.controller;


import com.example.nuonuo.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@Controller
public class SaticScheduleTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaticScheduleTask.class);

    @Autowired
    private TokenService tokenService;

    //每天凌晨4点执行
    @Scheduled(cron = "0 0 4 * * ?")
    private void configureTasks() {
        LOGGER.info("-------------------- 执行静态定时任务开始: " + LocalDateTime.now() + "--------------------");
        try{

            //刷新 t+ 的token
            //tokenService.refreshTToken();

            //刷新 美团 的token
            //tokenService.refreshMeiTuanToken();
        }catch (Exception e){
            e.printStackTrace();
        }
        LOGGER.info("-------------------- 执行静态定时任务结束: " + LocalDateTime.now() + "--------------------");
    }

}