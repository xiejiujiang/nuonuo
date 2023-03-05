package com.example.nuonuo.controller;

import com.example.nuonuo.service.TokenService;
import com.example.nuonuo.utils.NuonuoTest;
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

    @Autowired
    private TokenService tokenService;

    //每隔20个小时 更新一次 token
    @Scheduled(cron = "* * 0/20 * * ?")
    private void configureTasks() {
        System.err.println("-------------------- 执行静态定时任务开始: " + LocalDateTime.now() + "--------------------");
        try{
            NuonuoTest.getToken();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.err.println("-------------------- 执行静态定时任务结束: " + LocalDateTime.now() + "--------------------");
    }
}