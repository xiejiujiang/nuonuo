package com.example.nuonuo.controller;

import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.service.BasicService;
import com.example.nuonuo.service.TokenService;
import com.example.nuonuo.service.impl.BasicServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableScheduling
@Controller
public class SaticScheduleTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaticScheduleTask.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BasicService basicService;

    @Autowired
    private orderMapper orderMapper;

    //每天上午6点，下午6点
    @Scheduled(cron = "0 15 15 * * ?")
    private void configureTasks() {
        LOGGER.info("-------------------- 执行静态定时任务开始: " + LocalDateTime.now() + "--------------------");
        System.err.println("-------------------- 执行静态定时任务开始: " + LocalDateTime.now() + "--------------------");
        try{
            //刷新畅捷通的token
            tokenService.refreshToken();
            System.err.println("------------- T token 成功 ------------");
            LOGGER.info("-------------- T token 成功  ------------");

            //刷新E看牙的token
            tokenService.refreshEToken();
            System.err.println("------------- E token 成功 ------------");
            LOGGER.info("-------------- E token 成功  ------------");

            //病人-往来单位 138  104  125
            Map<String,Object> tmap = orderMapper.getTMapByOfficeId("138");
            basicService.createWLDW(basicService.getEUserInfo("138"),tmap);

            //


        }catch (Exception e){
            e.printStackTrace();
        }
        System.err.println("-------------------- 执行静态定时任务结束: " + LocalDateTime.now() + "--------------------");
        LOGGER.info("-------------------- 执行静态定时任务结束: " + LocalDateTime.now() + "--------------------");
    }

}