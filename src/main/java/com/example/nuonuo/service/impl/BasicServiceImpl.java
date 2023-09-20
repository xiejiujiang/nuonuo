package com.example.nuonuo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Ekanya.Esale.ESaleRoot;
import com.example.nuonuo.entity.Ekanya.Euser.Euser;
import com.example.nuonuo.entity.Ekanya.Eyuangong.Eyuangong;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.service.BasicService;
import com.example.nuonuo.utils.HttpClient;
import com.example.nuonuo.utils.MapToJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasicServiceImpl implements BasicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicServiceImpl.class);

    @Autowired
    private orderMapper orderMapper;

    //E看牙接口，查询患者档案。通过 时间 , 当天，然后 同步到 T+ 的客户档案里面
    public Euser getEUserInfo(String officeId){
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("officeId",officeId);
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("startTime",today);//当日
            parma.put("endTime",today);//当日
            String Authorization = HttpClient.EAuthorization;
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/crm/patient/query/window", parma,Authorization);
            //将这个json字符串转换成java对象，方便进行数据 处理！
            Euser euser = JSON.parseObject(result, Euser.class);
            return euser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    //调用T+，创建基础档案-往来单位的接口
    //参数应该是先从 上面的E看牙 接口 查询之后，传入的。
    @Override
    public String createWLDW(Euser euser,Map<String,Object> params) {
        String result = "";
        try {
            String json = MapToJson.getInComeString(euser);
            result = HttpClient.HttpPost("/tplus/api/v2/partner/CreateBatch",//批量创建吧
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    orderMapper.getTokenByAppKey(params.get("AppKey").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    //-------------------------------------------------------------------------------------------//

    //E看牙接口，查询4-2划扣流水。生成T+销售订单。 但是，我觉得 应该 是 先调用 查询患者的账单
    @Override
    public ESaleRoot getEplanDetail(String officeId){
        try {
            Map<String,String> parma = new HashMap<String,String>();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("startTime",today);//当日
            parma.put("endTime",today);//当日
            parma.put("officeId",officeId); // 138 125  104
            String Authorization = HttpClient.EAuthorization;
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/billing/bill-deduction/flow", parma,Authorization);
            ESaleRoot eSaleRoot = JSON.parseObject(result, ESaleRoot.class);//还是接受到某个对象里面
            return eSaleRoot;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //调用T+，创建 销售订单 的接口
    //参数应该是先从 上面的E看牙 接口 查询之后，传入的。
    @Override
    public String createSaorderDetail(ESaleRoot eSaleRoot,Map<String, Object> params) {
        String result = "";
        try {
            for(String json : MapToJson.getSAOrderparamsJson(params,eSaleRoot)){
                result = HttpClient.HttpPost("/tplus/api/v2/saleOrder/Create",
                        json,
                        params.get("AppKey").toString(),
                        params.get("AppSecret").toString(),
                        orderMapper.getTokenByAppKey(params.get("AppKey").toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    //-----------------------------------------E——》T+生产加工单--------------------------------------------------//
    //调用T+，创建 生产加工单 的接口
    //参数应该是先从 上面的E看牙 接口 查询之后，传入的。
    @Override
    public String createSCorderDetail(Map<String, Object> params) {
        String result = "";
        try {
            String json = MapToJson.getSCOrderparamsJson(params);
            result = HttpClient.HttpPost("/tplus/api/v2/ManufactureOrderOpenApi/Create",
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    orderMapper.getTokenByAppKey(params.get("AppKey").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    //-----------------------------------------E——》T+材料出库单--------------------------------------------------//
    //参数应该是先从 上面的E看牙 接口 查询之后，传入的。
    @Override
    public String getCLCKOrderparamsJson(Map<String, Object> params) {
        String result = "";
        try {
            String json = MapToJson.getCLCKOrderparamsJson(params);
            result = HttpClient.HttpPost("/tplus/api/v2/materialDispatch/Create",
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    orderMapper.getTokenByAppKey(params.get("AppKey").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //-----------------------------------------E——》T+产成品入库单--------------------------------------------------//
    //参数应该是先从 上面的E看牙 接口 查询之后，传入的。
    @Override
    public String getCCPRKOrderparamsJson(Map<String, Object> params) {
        String result = "";
        try {
            String json = MapToJson.getCCPRKOrderparamsJson(params);
            result = HttpClient.HttpPost("/tplus/api/v2/productReceive/Create",
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    orderMapper.getTokenByAppKey(params.get("AppKey").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //-----------------------------------------E——》T+ 销货单--------------------------------------------------//
    //参数应该是先从 上面的E看牙 接口 查询之后，传入的。
    @Override
    public String getSASOrderparamsJson(Map<String, Object> params) {
        String result = "";
        try {
            String json = MapToJson.getSASOrderparamsJson(params);
            result = HttpClient.HttpPost("/tplus/api/v2/SaleDeliveryOpenApi/Create",
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    orderMapper.getTokenByAppKey(params.get("AppKey").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    // --------------------------------------------------- E看牙 员工信息查询接口 ---------------------------------------//
    @Override
    public Eyuangong getEyuangong(String id){
        try {
            Map<String,String> parma = new HashMap<String,String>();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("providerId",id);
            String Authorization = HttpClient.EAuthorization;
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/pms/provider/query/by-id", parma,Authorization);
            Eyuangong eyuangong = JSON.parseObject(result, Eyuangong.class);//还是接受到某个对象里面
            return eyuangong;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}