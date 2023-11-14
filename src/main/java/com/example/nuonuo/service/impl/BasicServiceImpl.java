package com.example.nuonuo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Ekanya.EpatienCZ.EpatienCZ;
import com.example.nuonuo.entity.Ekanya.EpatienCZ.EpatienCZData;
import com.example.nuonuo.entity.Ekanya.Esale.ESaleData;
import com.example.nuonuo.entity.Ekanya.Esale.ESaleRoot;
import com.example.nuonuo.entity.Ekanya.EsingleUser.EuserSingle;
import com.example.nuonuo.entity.Ekanya.Euser.Euser;
import com.example.nuonuo.entity.Ekanya.Eyuangong.Eyuangong;
import com.example.nuonuo.entity.Ekanya.EyuyueByID.EyuyueByID;
import com.example.nuonuo.entity.Ekanya.EzhangdanByOfficeIdANDTIME.EzhangdanByOfficeIdANDTIME;
import com.example.nuonuo.entity.Ekanya.EzhifuDetailByID.EzhifuDetailByID;
import com.example.nuonuo.entity.Ekanya.EzhifuDetailByID.EzhifuDetailData;
import com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME.EzhifuData;
import com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME.EzhifudanByOfficeIdANDTIME;
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
    public Euser getEUserInfo(String officeId,String startTime, String endTime){
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("officeId",officeId);
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("startTime",startTime);//当日
            parma.put("endTime",endTime);//当日
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


    //E看牙接口，通过 指定ID 查询 某个 患者档案。
    @Override
    public EuserSingle getEUserInfoByPatientId(String patientId){
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("patientId",patientId);
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/crm/patient/query/by-id", parma,Authorization);
            //将这个json字符串转换成java对象，方便进行数据 处理！
            EuserSingle euserSingle = JSON.parseObject(result, EuserSingle.class);
            return euserSingle;
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

    //查询E看牙的划扣流水
    @Override
    public ESaleRoot getEplanDetail(String officeId,String startTime, String endTime){
        try {
            Map<String,String> parma = new HashMap<String,String>();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("startTime",startTime);//当日
            parma.put("endTime",endTime);//当日
            parma.put("officeId",officeId); // 138 125  104
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/billing/bill-deduction/flow", parma,Authorization);
            ESaleRoot eSaleRoot = JSON.parseObject(result, ESaleRoot.class);//还是接受到某个对象里面
            return eSaleRoot;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //调用T+，创建 销售订单 的接口
    @Override
    public String createSaorderDetail(Map<String,List<EzhifuData>> ezfMap, Map<String, Object> params) {
        String result = "";
        try {
            //这个MAP只是来装表头上的 部门 和 员工
            Map<String,Object> docMap = new HashMap<String,Object>();

            //开始 迭代上面 这个 整理好的 MAP
            Set entrySet = ezfMap.entrySet();
            Iterator iterator = entrySet.iterator();
            while (iterator.hasNext()){
                Object oo = iterator.next();
                Map.Entry entry = (Map.Entry)oo;
                String patientId = entry.getKey().toString(); // key 这个病人

                EuserSingle epatientsingle = getEUserInfoByPatientId(patientId);
                String consultantid = ""+epatientsingle.getData().getConsultant().getId();//咨询师ID
                String privateId = epatientsingle.getData().getPrivateId();//病例号

                //调用 员工查询 接口 可以获取 医生名称(已经写了serviceImpl)
                List<EzhifuData> ezhifuDataList = (List<EzhifuData>)entry.getValue(); // value  以及这个病人 对应的 所有 划扣 项目！

                String doctorid = ""+ezhifuDataList.get(0).getItems().get(0).getDoctor().getId();
                JSONObject jsonDoctorData = JSONObject.parseObject(getEyuangong(doctorid).getString("data"));
                String dorctorName = jsonDoctorData.getString("name");//医生
                JSONObject jsonZixunshiData = JSONObject.parseObject(getEyuangong(consultantid).getString("data"));
                String zixunshiName = jsonZixunshiData.getString("name");//咨询师

                for(EzhifuData ezhifuData : ezhifuDataList){
                    String paymentId = ""+ezhifuData.getPaymentId();//支付单ID
                    EzhifuDetailByID EzhifuDetail = getEzhifudetailByID(paymentId);//支付明细
                    List<EzhifuDetailData> ezhifuDatadetail = EzhifuDetail.getData();
                    ezhifuData.setEzhifuDatadetail(ezhifuDatadetail);
                    for(int i = 0;i<ezhifuData.getItems().size(); i ++){
                        String itemName = ezhifuData.getItems().get(i).getItemName()  ;//查询对应在T+里面 的 存货编码
                        LOGGER.info("  itemName ===== " + itemName);
                        ezhifuData.getItems().get(i).setTinventoryCode(orderMapper.getTinventoryByName(itemName).get("code").toString());
                        ezhifuData.getItems().get(i).setTinventoryUnitName(orderMapper.getTinventoryByName(itemName).get("unitName").toString());
                    }
                }


                //再查到这个 员工在 T+里面的部门 和 员工 编码   对应账套？  但是 反正 单据也要对应到账套的。
                Map<String,Object> doctdecmap = orderMapper.getTdeparmtClerkByMobile(dorctorName);
                Map<String,Object> clertdecmap = orderMapper.getTdeparmtClerkByMobile(zixunshiName);
                docMap.put(patientId+"-doc",doctdecmap);
                docMap.put(patientId+"-cle",clertdecmap);
                docMap.put(patientId+"-privateId",privateId);
                String jzlx = getEyuyueByIDByID(""+ezhifuDataList.get(0).getAppointmentId()).getData().getCheckInType();
                docMap.put(patientId+"-jzlx",jzlx);//就诊类型
            }

            List<String> jonslist = MapToJson.getSAOrderparamsJson(ezfMap,docMap);
            for(int i = 0 ; i <= jonslist.size() ; i ++ ){
                String json = jonslist.get(i);
                LOGGER.info(" ============  请求T+销售订单JSON ==== " + json);
                /*result = HttpClient.HttpPost("/tplus/api/v2/saleOrder/Create",
                        json,
                        params.get("AppKey").toString(),
                        params.get("AppSecret").toString(),
                        params.get("access_token").toString());
                LOGGER.info(" ============  请求T+销售订单结果 ==== " + result);*/
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
            LOGGER.info(" ============  请求T+生产加工单JSON ==== " + json);
            result = HttpClient.HttpPost("/tplus/api/v2/ManufactureOrderOpenApi/Create",
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    params.get("access_token").toString());
            LOGGER.info(" ============  请求T+生产加工单结果 ==== " + result);
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
            LOGGER.info(" ============  请求T+工序汇报单JSON ==== " + json);
            result = HttpClient.HttpPost("/tplus/api/v2/ManufactureReportOpenApi/Create",
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    params.get("access_token").toString());
            LOGGER.info(" ============  请求T+工序汇报单结果 ==== " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //-----------------------------------------E——》T+材料出库单    不 对接！--------------------------------------------------//
    //从T+的生产加工单 直接 到 材料出库单
    /*@Override
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
    }*/

    //-----------------------------------------E——》T+产成品入库单 末工序自动入库！--------------------------------------------------//
    //参数应该是先从 上面的E看牙 接口 查询之后，传入的。
    /*@Override
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
    }*/

    //-----------------------------------------E——》T+ 销货单--------------------------------------------------//
    //从T+的销售订单 生成 销货单
    @Override
    public String getSASOrderparamsJson(String tsacode,Map<String, Object> params) {
        String result = "";
        try {
            //通过 Tsacode 查询出 销售订单的明细内容
            List<Map<String,Object>> Tsalist = orderMapper.getTSaListByCode(tsacode);
            String json = MapToJson.getSASOrderparamsJson(Tsalist);
            LOGGER.info(" ============  请求T+销货单JSON ==== " + json);
            result = HttpClient.HttpPost("/tplus/api/v2/SaleDeliveryOpenApi/Create",
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    params.get("access_token").toString());
            LOGGER.info(" ============  请求T+销货单结果 ==== " + result);
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
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/pms/provider/query/by-id", parma,Authorization);
            job = JSONObject.parseObject(result);
            //Eyuangong eyuangong = JSON.parseObject(result, Eyuangong.class);//还是接受到某个对象里面
            return job;
        }catch (Exception e){
            e.printStackTrace();
        }
        return job;
    }

    //--------------------------------------------------- E看牙 查询患者储值卡交易记录 ---------------------------------------//
    @Override
    public EpatienCZ getEpatientCZK(String officeId,String patientId){
        EpatienCZ epatienCZ = null;
        try {
            Map<String,String> parma = new HashMap<String,String>();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("officeId",officeId);
            parma.put("patientId",patientId);
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("/public/v2/gift/stored-value-card-transaction/query/by-patient", parma,Authorization);
            epatienCZ = JSON.parseObject(result, EpatienCZ.class);//还是接受到某个对象里面
            return epatienCZ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return epatienCZ;
    }

    //处理下 上面这个数据，然后再调用T+生成 预收款单
    @Override
    public String createTSKByEpatientCZK(EpatienCZ epatienCZ,Map<String, Object> params){
        String result = "";
        try {
            List<Map<String,Object>> czList = new ArrayList<Map<String,Object>>();
            for(EpatienCZData epatienCZData : epatienCZ.getData()){
                String type = ""+epatienCZData.getType();//只看 0
                String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String timestamp = epatienCZData.getTimestamp(); //今天
                if("0".equals(type) && today.equals(timestamp.substring(0,10))){
                    Map<String,Object> czmap = new HashMap<>();
                    czmap.put("amount",epatienCZData.getAmount());
                    czmap.put("chargeOrderId",epatienCZData.getChargeOrderId());//本次交易关联的收费单Id

                    //？czmap.get("SettleStyle")
                    //?czmap.get("BankAccount")

                    czmap.put("targetPatient",epatienCZData.getTargetPatient().getId());
                    czmap.put("notes",epatienCZData.getNotes());
                    czmap.put("operatorId",epatienCZData.getOperator().getId());
                    czmap.put("partnerCode",getEUserInfoByPatientId(""+epatienCZData.getTargetPatient().getId()).getData().getPrivateId());
                    czList.add(czmap);
                }
            }
            String json = MapToJson.getTskJSON(czList);
            LOGGER.info(" ============  请求T+收款单 JSON ==== " + json);
            result = HttpClient.HttpPost("/tplus/api/v2/receiveVoucher/Create",
                    json,
                    params.get("AppKey").toString(),
                    params.get("AppSecret").toString(),
                    params.get("access_token").toString());
            LOGGER.info(" ============  请求T+收款单 结果 ==== " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //--------------------------------------账单-------》 支付单------》 支付明细---------------------------------//
    @Override
    public EzhangdanByOfficeIdANDTIME getEzhangdanByOfficeIdANDTIME(String officeId, String startTime, String endTime) {
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("officeId",officeId);
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("startTime",startTime);//当日
            parma.put("endTime",endTime);//当日
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/billing/order/window", parma,Authorization);
            //将这个json字符串转换成java对象，方便进行数据 处理！
            EzhangdanByOfficeIdANDTIME ezd = JSON.parseObject(result, EzhangdanByOfficeIdANDTIME.class);
            return  ezd;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public EzhifudanByOfficeIdANDTIME getEzhifudanByOfficeIdANDTIME(String officeId, String startTime, String endTime) {
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("officeId",officeId);
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            parma.put("startTime",startTime);//当日
            parma.put("endTime",endTime);//当日
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/billing/payment/window", parma,Authorization);
            //将这个json字符串转换成java对象，方便进行数据 处理！
            EzhifudanByOfficeIdANDTIME ezf = JSON.parseObject(result, EzhifudanByOfficeIdANDTIME.class);
            return ezf;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //根据支付单Id查询支付明细
    @Override
    public EzhifuDetailByID getEzhifudetailByID(String id) {
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("ids",id);
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/billing/payment-method/by-payment-ids", parma,Authorization);
            //将这个json字符串转换成java对象，方便进行数据 处理！
            EzhifuDetailByID ezfdetail = JSON.parseObject(result, EzhifuDetailByID.class);
            return ezfdetail;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //根据 预约Id 查询预约信息（就诊类型）
    @Override
    public EyuyueByID getEyuyueByIDByID(String id) {
        try {
            Map<String,String> parma = new HashMap<String,String>();
            parma.put("appointmentId",id);//预约ID
            String Authorization = orderMapper.getEtoken();
            String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/pms/appointment/query/by-id", parma,Authorization);
            //将这个json字符串转换成java对象，方便进行数据 处理！
            EyuyueByID eyuyue = JSON.parseObject(result, EyuyueByID.class);
            return eyuyue;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}