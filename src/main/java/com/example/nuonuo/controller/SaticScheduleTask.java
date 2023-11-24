package com.example.nuonuo.controller;

import com.example.nuonuo.entity.Ekanya.EzhangdanByOfficeIdANDTIME.EZDData;
import com.example.nuonuo.entity.Ekanya.EzhangdanByOfficeIdANDTIME.EzhangdanByOfficeIdANDTIME;
import com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME.EzhifuData;
import com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME.EzhifudanByOfficeIdANDTIME;
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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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
    @Scheduled(cron = "0 6 6 * * ?")
    private void configureTasks() {
        LOGGER.info("-------------------- 执行静态定时任务开始: " + LocalDateTime.now() + "--------------------");
        try{
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String tomorrow = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

            //刷新畅捷通的token
            tokenService.refreshToken();
            LOGGER.info("-------------- T token 成功  ------------");

            //刷新E看牙的token
            //tokenService.refreshEToken();
            //LOGGER.info("-------------- E token 成功  ------------");

            //获取这个诊所对应的T+ token信息  金华江南馨 园口腔医院有限公司  138   -  [900002]金华江南馨园口腔医院有限公司
            //Map<String,Object> tmap = orderMapper.getTMapByOfficeId("138");

            //病人-往来单位 138  104  125
            //basicService.createWLDW(basicService.getEUserInfo("138"),tmap);


            //查询账单流水
            /*EzhangdanByOfficeIdANDTIME ezhangdan = basicService.getEzhangdanByOfficeIdANDTIME("104",today,tomorrow);
            Map<String,Object> ezdMap = new HashMap<String,Object>();//存入了所有 患者的 今天的 账单信息
            for(EZDData ezdData : ezhangdan.getData()){
                String patienid = ""+ezdData.getPatientId();
                ezdMap.put(patienid,ezdData);
            }*/

            //查询支付单流水
            /*EzhifudanByOfficeIdANDTIME ezhifu = basicService.getEzhifudanByOfficeIdANDTIME("138",today,tomorrow);
            //存入了所有 患者的 今天的支付单信息,一个患者也可以有多个
            Map<String,List<EzhifuData>> ezfMap = new HashMap<String,List<EzhifuData>>();
            for(EzhifuData ezhifuData : ezhifu.getData()){
                String patienid = ""+ezhifuData.getPatientId();
                if(ezfMap.get(patienid) == null){
                    List<EzhifuData> newList = new ArrayList<EzhifuData>();
                    newList.add(ezhifuData);
                    ezfMap.put(patienid,newList);
                }else{
                    List<EzhifuData> oldList = ezfMap.get(patienid);
                    oldList.add(ezhifuData);
                    ezfMap.put(patienid,oldList);
                }
            }*/
            //再来组合 销售订单 需要的数据！

            //最后 调用T+创建销售 订单
            //basicService.createSaorderDetail(ezfMap,tmap);



            //执行划扣——》  生产加工单？？？？
            //basicService.createSaorderDetail(basicService.getEplanDetail("104"),tmap);

            //basicService.createSCorderDetail("SO-2023-10-0001",tmap);

            //工序汇报单
            //basicService.getGXHBOrderparamsJson("MO-2023-07-0002",tmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        LOGGER.info("-------------------- 执行静态定时任务结束: " + LocalDateTime.now() + "--------------------");
    }

}