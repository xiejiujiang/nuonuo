package com.example.nuonuo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Ekanya.Esale.ESaleData;
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
import java.util.*;

@Service
public class BasicServiceImpl implements BasicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicServiceImpl.class);

    @Autowired
    private orderMapper orderMapper;

    //E看牙接口，根据 某个机构  查询患者档案。通过 时间 , 当天，然后 同步到 T+ 的客户档案里面  138  104  125
    @Override
    public Euser getEUserInfo(String officeId){
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("officeId",officeId);
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("startTime",today);//当日
            parma.put("endTime",today);//当日
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/crm/patient/query/window", parma,Authorization);
            //将这个json字符串转换成java对象，方便进行数据 处理！
            Euser euser = JSON.parseObject(result, Euser.class);
            return euser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //E看牙接口，通过 指定ID 查询患者档案。
    @Override
    public Euser getEUserInfoById(String patientId){
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("patientId",patientId);
            String Authorization = HttpClient.EAuthorization;
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/crm/patient/query/by-id", parma,Authorization);
            //将这个json字符串转换成java对象，方便进行数据 处理！
            Euser euser = JSON.parseObject(result, Euser.class);//这个地方需要改成单个的 EuserSingle
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
            List<String> jsonlist = MapToJson.getInComeString(euser);
            for(String json : jsonlist){
                LOGGER.info("调用T+ 创建 客户的JSON == " + json);
                result = HttpClient.HttpPost("/tplus/api/v2/partner/Create",//1个1个创建吧
                        json,
                        params.get("AppKey").toString(),
                        params.get("AppSecret").toString(),
                        params.get("access_token").toString());
                LOGGER.info("调用T+ 创建客户后的 result == " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    //-------------------------------------------------------------------------------------------//

    //E看牙接口，查询4-2划扣流水。生成T+销售订单。
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
            //先通过这个 eSaleRoot 查出所有要的东西？
            Map<String,Object> edatamap = new HashMap<String,Object>();
            for(ESaleData esaledata: eSaleRoot.getData()){
                String patientId = ""+esaledata.getPatientId();//患者ID
                if(edatamap.get(patientId) == null){
                    //不存在，说明第一次出现，就先存入
                    List<ESaleData> esalelist = new ArrayList<ESaleData>();
                    esalelist.add(esaledata);
                    edatamap.put(patientId,esalelist);
                }else {
                    //已经存在，需要判断，组合 后，再存入。
                    List<ESaleData> esalelist = (List<ESaleData>) edatamap.get(patientId);
                    esalelist.add(esaledata);
                    edatamap.put(patientId,esalelist);//再放回去
                }
            }

            Map<String,Object> docMap = new HashMap<String,Object>();
            while (edatamap.keySet().iterator().hasNext()){
                String patientId = edatamap.keySet().iterator().next(); // key
                List<ESaleData> esalelist = (List<ESaleData>)edatamap.get(patientId); // value
                String doctorid = ""+esalelist.get(0).getDoctorId();//医生ID
                String consultant = ""+getEUserInfoById(patientId).getData().get(0).getConsultant().getId();//咨询师
                //调用 员工查询 接口 可以获取 医生名称(已经写了serviceImpl)
                String dorctorMobile = getEyuangong(doctorid).getString("mobile");//医生
                String zixunshiMoble = getEyuangong(consultant).getString("mobile");//咨询师
                //再查到这个 员工在 T+里面的部门 和 员工 编码
                Map<String,Object> doctdecmap = orderMapper.getTdeparmtClerkByMobile(dorctorMobile);
                Map<String,Object> clertdecmap = orderMapper.getTdeparmtClerkByMobile(dorctorMobile);
                docMap.put(patientId+"-doc",doctdecmap);
                docMap.put(patientId+"-cle",clertdecmap);
            }

            for(String json : MapToJson.getSAOrderparamsJson(params,edatamap,docMap)){
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
    //参数应该是先从 T+的一个销售订单 生成的 生产加工单
    @Override
    public String createSCorderDetail(String Tsacode,Map<String,Object> params) {
        String result = "";
        try {
            //通过 Tsacode 查询出 销售订单的明细内容
            List<Map<String,Object>> Tsalist = orderMapper.getTSaListByCode(Tsacode);
            String json = MapToJson.getSCOrderparamsJson(Tsalist);
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


    //-----------------------------------------E——》T+工序汇报单--------------------------------------------------//
    //从T+的生产加工单 直接 到 工序汇报单
    @Override
    public String getGXHBOrderparamsJson(String Tsccode,Map<String, Object> params) {
        String result = "";
        try {
            //通过 Tsccode 查询出 生产加工单的明细内容
            List<Map<String,Object>> Tsclist = orderMapper.getTscListByCode(Tsccode);
            String json = MapToJson.getGXHBOrderparamsJson(Tsclist);
            result = HttpClient.HttpPost("/tplus/api/v2/ManufactureReportOpenApi/Create",
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
    //从T+的生产加工单 直接 到 材料出库单
    @Override
    public String getCLCKOrderparamsJson(String Tsccode,Map<String, Object> params) {
        String result = "";
        try {
            //通过 Tsccode 查询出 生产加工单的明细内容
            List<Map<String,Object>> Tsclist = orderMapper.getTscListByCode(Tsccode);
            String json = MapToJson.getCLCKOrderparamsJson(Tsclist);
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
    //从T+的销售订单 生成 销货单
    @Override
    public String getSASOrderparamsJson(String tsacode,Map<String, Object> params) {
        String result = "";
        try {
            //通过 Tsacode 查询出 销售订单的明细内容
            List<Map<String,Object>> Tsalist = orderMapper.getTSaListByCode(tsacode);
            String json = MapToJson.getSASOrderparamsJson(Tsalist);
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
    public JSONObject getEyuangong(String id){
        JSONObject job = null;
        try {
            Map<String,String> parma = new HashMap<String,String>();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("providerId",id);
            String Authorization = HttpClient.EAuthorization;
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/pms/provider/query/by-id", parma,Authorization);
            job = JSONObject.parseObject(result);
            //Eyuangong eyuangong = JSON.parseObject(result, Eyuangong.class);//还是接受到某个对象里面
            return job;
        }catch (Exception e){
            e.printStackTrace();
        }
        return job;
    }
}