package com.example.nuonuo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.tiancai.TcBHDresult;
import com.example.nuonuo.entity.tiancai.TcCKresult;
import com.example.nuonuo.entity.tiancai.TcMDresult;
import com.example.nuonuo.entity.tiancai.TcZXresult;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.service.TokenService;
import com.example.nuonuo.utils.Ekanya;
import com.example.nuonuo.utils.HttpClient;
import com.example.nuonuo.utils.Md5;
import com.example.nuonuo.utils.ObjectToXmlConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    String oldrefreshtoken = org.get("refresh_token");
                    parma.put("refreshToken",oldrefreshtoken);
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
                        updateMap.put("oldrefreshtoken",oldrefreshtoken);
                        orderMapper.updateOrgToken(updateMap);
                        LOGGER.error("------------ 成功 更新了一次数据库 T token-------------------- " );
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
                            LOGGER.error("------------ 成功 更新了一次数据库 T token-------------------- " );
                        }else{
                            LOGGER.error("----------------更新失败，检擦！！！---------------------- " + org.get("org_id").toString());
                        }
                    }
                }
            }catch (Exception ex){
                LOGGER.error("----------------T+ token更新 再来一次都TM失败了，检查一下看看！ ---------------------- " );
                ex.printStackTrace();
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
                String expiredTime = jso.getString("expiredTime");
                Map<String,String> ekanyaMap = new HashMap<String,String>();
                ekanyaMap.put("tenantid","027156bc-b8cf-4d72-bac4-0d720d8fb049");//对应是哪个账套的，E看牙也是一个账套对应一个机构（诊所/医院）
                ekanyaMap.put("token",etoken);
                ekanyaMap.put("expiredTime",expiredTime);
                orderMapper.updateEOrgToken(ekanyaMap);
            }else{
                LOGGER.error("----------------E看牙token更新失败，检擦！！！---------------------- ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public TcBHDresult getTCMDBHDList(String shopid, String busdate) {
        TcBHDresult mdbhdresult;
        String url = "http://xxxx.xx.fxscm.net/cldpoint/getStorebill4MDBD.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent","ENTa4cm");
        params.put("username","admin");
        params.put("password","0000");
        params.put("busdate",busdate);
        params.put("shopid",shopid);
        try {
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            mdbhdresult = JSONObject.parseObject(res, TcBHDresult.class);//门店报货单
        }catch (IOException e){
            LOGGER.error("获取天财门店报货单接口出错！确认参数和访问地址！！！");
            mdbhdresult = null;
        }
        return mdbhdresult;
    }

    @Override
    public TcMDresult getTCMDList() {
        TcMDresult mdbhdresult;
        String url = "http://xxxx.xx.fxscm.net/cldpoint/getShop.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent","ENTa4cm");
        params.put("username","admin");
        params.put("password","0000");
        try {
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            mdbhdresult = JSONObject.parseObject(res, TcMDresult.class);//门店
        }catch (IOException e){
            LOGGER.error("获取天财 门店 接口出错！确认参数和访问地址！！！");
            mdbhdresult = null;
        }
        return mdbhdresult;
    }

    @Override
    public TcZXresult getTCZXList() {
        TcZXresult mdbhdresult;
        String url = "http://xxxx.xx.fxscm.net/cldpoint/getRdc.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent","ENTa4cm");
        params.put("username","admin");
        params.put("password","0000");
        try {
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            mdbhdresult = JSONObject.parseObject(res, TcZXresult.class);//配送中心
        }catch (IOException e){
            LOGGER.error("获取天财 配送中心 接口出错！确认参数和访问地址！！！");
            mdbhdresult = null;
        }
        return mdbhdresult;
    }


    @Override
    public TcCKresult getTCCKList() {
        TcCKresult mdbhdresult;
        String url = "http://xxxx.xx.fxscm.net/cldpoint/getOrganStore.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent","ENTa4cm");
        params.put("username","admin");
        params.put("password","0000");
        try {
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            mdbhdresult = JSONObject.parseObject(res, TcCKresult.class);//仓库
        }catch (IOException e){
            LOGGER.error("获取天财 仓库 接口出错！确认参数和访问地址！！！");
            mdbhdresult = null;
        }
        return mdbhdresult;
    }


    @Override
    public String addHongrenDDByTcDD(Object oo) {
        /*String result;
        //将 天财的 入参oo（门店报货单/外销订货单） 转换成 弘人WMS入参对象后 再转换成 Xml格式 字符串
        com.example.nuonuo.entity.hongren.rukufahuo.RFRequest request = new com.example.nuonuo.entity.hongren.rukufahuo.RFRequest();

        TcBHDresult tcmdbhd = (TcBHDresult)oo;//举了个例子，这个是天财的门店报货单
        //request.setOrderLines(tcmdbhd.getData());//????

        Map<String,String> params = new HashMap<String,String>();
        String secret = "36255923b94a5f667519a30524637e9c";
        String body = ObjectToXmlConverter.convertObjectToXml(request);
        params.put("secret",secret);
        params.put("app_key","YJLF");
        params.put("customerId","YJLF");
        params.put("format","xml");
        params.put("method","mixorder.create");
        params.put("partner_id","");
        params.put("sign_method","md5");
        params.put("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        params.put("v","2.0");
        String sign = Md5.md5(secret+"app_key"+params.get("app_key")+"customerId"+params.get("customerId")+"format"+params.get("format")+"method"+params.get("method")
                +"partner_id"+params.get("partner_id")+"sign_method"+params.get("sign_method")+"timestamp"+params.get("timestamp")+"v"+params.get("v")+body+secret);
        params.put("sign",sign.toUpperCase());//大写！
        try {
            result = HttpClient.doPostXMLTEST("http://c-api.hr-network.cn/api/edi/qimen/service",params,body);
        }catch (Exception e){
            result = "天财订单下发给弘人WMS进行发货出库 失败！！！";
        }*/
        return "";
    }

}
