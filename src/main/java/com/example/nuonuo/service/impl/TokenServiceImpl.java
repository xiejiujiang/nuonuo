package com.example.nuonuo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.service.TokenService;
import com.example.nuonuo.utils.Ekanya;
import com.example.nuonuo.utils.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private orderMapper orderMapper;

    public String refreshToken(){
        try {
            List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
            if(orgList != null && orgList.size() != 0){
                for(Map<String,String> org : orgList){
                    Map<String,String> parma = new HashMap<String,String>();
                    parma.put("grantType","refresh_token");
                    parma.put("appKey",org.get("AppKey"));
                    parma.put("refreshToken",org.get("refresh_token"));
                    String result = HttpClient.doGeturlparams("https://openapi.chanjet.com/auth/refreshToken", parma,"");
                    //将返回的 result 解析出来，写回数据库！,并一定更新 最后的 更新时间 ,其实 只有 refresh_token,token,和 更新时间会变。
                    JSONObject jso = JSONObject.parseObject(result);
                    if("200".equals(jso.get("code").toString())){//调用成功，更新数据库！
                        JSONObject detail = JSONObject.parseObject(jso.get("result").toString());
                        String access_token = detail.get("access_token").toString();
                        String refresh_token = detail.get("refresh_token").toString();
                        String org_id = detail.get("org_id").toString();
                        Map<String,String> updateMap = new HashMap<String,String>();
                        updateMap.put("org_id",org_id);
                        updateMap.put("refresh_token",refresh_token);
                        updateMap.put("access_token",access_token);
                        orderMapper.updateOrgToken(updateMap);
                    }else{
                        LOGGER.error("----------------更新失败，检擦！！！---------------------- " + org.get("org_id").toString());
                    }
                }
            }
        }catch (Exception e){
            //e.printStackTrace();
            //如果出异常，就再来一次试试
            try{
                List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
                if(orgList != null && orgList.size() != 0){
                    for(Map<String,String> org : orgList){
                        Map<String,String> parma = new HashMap<String,String>();
                        parma.put("grantType","refresh_token");
                        parma.put("appKey",org.get("AppKey"));
                        parma.put("refreshToken",org.get("refresh_token"));
                        String result = HttpClient.doGeturlparams("https://openapi.chanjet.com/auth/refreshToken", parma,"");
                        //将返回的 result 解析出来，写回数据库！,并一定更新 最后的 更新时间 ,其实 只有 refresh_token,token,和 更新时间会变。
                        JSONObject jso = JSONObject.parseObject(result);
                        if("200".equals(jso.get("code").toString())){//调用成功，更新数据库！
                            JSONObject detail = JSONObject.parseObject(jso.get("result").toString());
                            String access_token = detail.get("access_token").toString();
                            String refresh_token = detail.get("refresh_token").toString();
                            String org_id = detail.get("org_id").toString();
                            Map<String,String> updateMap = new HashMap<String,String>();
                            updateMap.put("org_id",org_id);
                            updateMap.put("refresh_token",refresh_token);
                            updateMap.put("access_token",access_token);
                            orderMapper.updateOrgToken(updateMap);
                        }else{
                            LOGGER.error("----------------更新失败，检擦！！！---------------------- " + org.get("org_id").toString());
                        }
                    }
                }
            }catch (Exception ex){
                LOGGER.error("---------------- 再来一次都TM失败了，检查一下看看！ ---------------------- " );
            }
        }finally {
            return "success";
        }
    }

    //获取E看牙的token
    @Override
    public String refreshEToken() {
        try {
            Ekanya ekanya = new Ekanya("z7sctvS7Nd%2bP7iE4x0DU34trNfgZ0fXr8XLu6QiUmtN5cYbpnzvaJCNeuI2NAmza3%2bI4Z6bO5y7TVSZDl5zwljOeUr6G8mX%2fFrRHZso%2fRyhnXAtC6KsQxt88GSTJFPkVsIyyTBW8EBdK0kIPSfejOwt4PzwwHxusz9QDnfiPOALiTAMeq3OHpiYrtukGs7pmyLpcU6cPUeO%2bLheHvdl1Hv60enF2kPkdLsh7IE4tMSvlu7TGiC7eJx2f10ciEcvIAZatQxibMH0ViUDC5tuqkPa0E207p62yE7mrqcKieAaVGQMKxvRw5A6f2ULjg9at28NmfuBqvhhJYPRR8M3z%2fmzzRBLD0y%2b1XTyjutkLaJVcmmnH2tapl34kdscuw8adhTEJp%2fiHzOY4RQME0YFOQ2hqOAcgRrLh14N16dYODVQXs3aghnO173NulihX6R3m","027156bc-b8cf-4d72-bac4-0d720d8fb049");
            String result = HttpClient.doPostTestTwo("https://openapi-gw.linkedcare.cn/public/v1/auth/token",ekanya);

            //将返回的 result 解析出来，写回数据库！,并一定更新 最后的 更新时间
            JSONObject jso = JSONObject.parseObject(result);
            if(jso.get("error") == null || "null".equals(jso.get("error").toString())){//调用成功，更新数据库！
                String etoken = jso.get("token").toString();
                Map<String,String> ekanyaMap = new HashMap<String,String>();
                ekanyaMap.put("tenantid","");//对应是哪个账套的，E看牙也是一个账套对应一个机构（诊所/医院）
                ekanyaMap.put("etoken",etoken);
                orderMapper.updateOrgToken(ekanyaMap);
            }else{
                LOGGER.error("----------------E看牙token更新失败，检擦！！！---------------------- ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

}
