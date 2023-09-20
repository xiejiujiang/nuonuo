package com.example.nuonuo.controller;

import com.example.nuonuo.mapper.*;
import com.example.nuonuo.service.TokenService;
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
    private orderMapper orderMapper;

    @Autowired
    private TokenService tokenService;

    //每天上午6点，下午6点
    @Scheduled(cron = "0 0 18 * * ?")
    private void configureTasks() {
        System.err.println("-------------------- 执行静态定时任务开始: " + LocalDateTime.now() + "--------------------");
        try{
            //刷新畅捷通的token
            tokenService.refreshToken();

            //刷新E看牙的token
            tokenService.refreshEToken();

            //执行档案和当局的同步任务？




        }catch (Exception e){
            e.printStackTrace();
        }
        System.err.println("-------------------- 执行静态定时任务结束: " + LocalDateTime.now() + "--------------------");
    }

    public static void main(String[] args) {
        System.out.println("12313212131213");
    }
}